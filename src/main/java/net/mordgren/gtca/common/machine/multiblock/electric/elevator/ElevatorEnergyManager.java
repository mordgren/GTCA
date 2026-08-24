package net.mordgren.gtca.common.machine.multiblock.electric.elevator;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import net.mordgren.gtca.common.machine.multiblock.electric.SpaceElevatorMachine;
import java.util.ArrayList;
import java.util.List;
public class ElevatorEnergyManager {
    private final SpaceElevatorMachine elevator;
    /**
    Created by sensesgone
    25.1.2026
     **/
    public ElevatorEnergyManager(SpaceElevatorMachine elevator) {
        this.elevator = elevator;
    }
    public void tickEnergyDistribution() {
        if (elevator.getLevel() == null || elevator.getLevel().isClientSide) return;
        if (!elevator.isFormed()) return;
        var elevatorEnergy = elevator.getEnergyContainerSafe();
        if (elevatorEnergy == null) return;
        long available = elevatorEnergy.getEnergyStored();
        if (available <= 0) return;
        List<Req> requests = new ArrayList<>();
        for (var s : elevator.getSlotInfos()) {
            if (!s.present || !s.active) continue;
            MetaMachine mm = MetaMachine.getMachine(elevator.getLevel(), s.pos);
            if (!(mm instanceof IElevatorModule mod)) continue;
            IEnergyContainer buf = mod.getWirelessEnergyContainer();
            if (buf == null) continue;
            long need = buf.getEnergyCapacity() - buf.getEnergyStored();
            if (need > 0) {
                requests.add(new Req(mod, buf, need));
            }
        }
        if (requests.isEmpty()) return;
        long totalNeed = 0;
        for (Req r : requests) totalNeed += r.need;
        if (totalNeed <= 0) return;
        long toSendTotal = Math.min(available, totalNeed);
        long sent = 0;
        for (int i = 0; i < requests.size(); i++) {
            Req r = requests.get(i);
            long share;
            if (i == requests.size() - 1) {
                share = toSendTotal - sent;
            } else {
                share = (toSendTotal * r.need) / totalNeed;
            }
            if (share <= 0) continue;
            long extracted = -elevatorEnergy.changeEnergy(-share); // changeEnergy возвращает delta (отриц.)
            if (extracted <= 0) continue;
            long inserted = r.buf.changeEnergy(extracted);
            long leftover = extracted - inserted;
            if (leftover > 0) {
                elevatorEnergy.changeEnergy(leftover);
            }
            sent += inserted;
            if (sent >= toSendTotal) break;
        }
    }
    private record Req(IElevatorModule mod, IEnergyContainer buf, long need) {}
}