package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.mordgren.gtca.common.data.GTCABlocks;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleManager;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorEnergyManager;
import com.gregtechceu.gtceu.api.misc.EnergyContainerList;

import java.util.List;

public class SpaceElevatorMachine extends WorkableElectricMultiblockMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(SpaceElevatorMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    private final ElevatorEnergyManager energyManager = new ElevatorEnergyManager(this);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Persisted @DescSynced private int motorTier = 1;
    @Persisted @DescSynced private int unlockedModuleSlots = 6;
    @Persisted @DescSynced private int modulesFound = 0;
    @Persisted @DescSynced private int modulesValid = 0;
    @Persisted @DescSynced private int modulesActive = 0;

    private final ElevatorModuleManager moduleManager = new ElevatorModuleManager(this);

    private TickableSubscription scanSub = null;
    private int scanTimer = 0;

    public SpaceElevatorMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        if (scanSub == null) {
            scanSub = subscribeServerTick(this::onServerTickSubscribed);
            energyManager.tickEnergyDistribution();
        }

        scanTimer = 0;
        rescanAll();
    }

    public EnergyContainerList getEnergyContainerSafe() {
        if (this.energyContainer == null) {
            this.energyContainer = getEnergyContainer();
        }
        return this.energyContainer;
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();

        moduleManager.disableAllModulesInSlots();

        motorTier = 1;
        unlockedModuleSlots = 0;
        modulesFound = 0;
        modulesValid = 0;
        modulesActive = 0;
        scanTimer = 0;
    }

    @Override
    public void onUnload() {
        super.onUnload();
        scanSub = null;
        scanTimer = 0;
    }

    private void onServerTickSubscribed() {
        if (getLevel() == null) return;
        if (!isFormed()) return;

        distributeWirelessEnergy();

        scanTimer++;
        if (scanTimer >= 20) {
            scanTimer = 0;
            rescanAll();
        }
    }

    private void rescanAll() {
        recalcMotorTierAndSlots();

        moduleManager.rescanSlots(unlockedModuleSlots);
        moduleManager.applyEnabling();

        modulesFound = moduleManager.getModulesFound();
        modulesValid = moduleManager.getModulesValid();
        modulesActive = moduleManager.getModulesActive();
    }

    // -------- getters for UI/display --------
    public int getMotorTier() { return motorTier; }
    public int getUnlockedModuleSlots() { return unlockedModuleSlots; }
    public int getModulesFound() { return modulesFound; }
    public int getModulesValid() { return modulesValid; }
    public int getModulesActive() { return modulesActive; }

    public List<ElevatorModuleManager.SlotInfo> getSlotInfos() {
        return moduleManager.getSlotInfos();
    }

    // -------- motor tier logic --------

    private void recalcMotorTierAndSlots() {
        int newMotorTier = computeMotorTierMinFromCache();
        motorTier = newMotorTier;
        unlockedModuleSlots = slotsForMotorTier(newMotorTier);
    }

    private int computeMotorTierMinFromCache() {
        if (getLevel() == null) return 1;

        Iterable<BlockPos> cache = safeGetCache();
        if (cache == null) return 1;

        int minTier = Integer.MAX_VALUE;
        boolean foundAny = false;

        for (BlockPos pos : cache) {
            Block b = getLevel().getBlockState(pos).getBlock();
            int t = tierOfMotorBlock(b);
            if (t > 0) {
                foundAny = true;
                if (t < minTier) minTier = t;
            }
        }
        return foundAny ? minTier : 1;
    }

    private Iterable<BlockPos> safeGetCache() {
        try {
            return getMultiblockState().getCache();
        } catch (NullPointerException ignored) {
            return null;
        }
    }

    private static int tierOfMotorBlock(Block b) {
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK1.get()) return 1;
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK2.get()) return 2;
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK3.get()) return 3;
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK4.get()) return 4;
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK5.get()) return 5;
        return 0;
    }

    public static int slotsForMotorTier(int tier) {
        return switch (tier) {
            case 1 -> 6;
            case 2 -> 12;
            case 3 -> 15;
            case 4 -> 18;
            case 5 -> 24;
            default -> 6;
        };
    }

    private void distributeWirelessEnergy() {
        if (getLevel() == null || !isFormed()) return;


        if (this.energyContainer == null) {
            this.energyContainer = getEnergyContainer();
        }
        if (this.energyContainer == null) return;

        long available = this.energyContainer.getEnergyStored();
        if (available <= 0) return;


        for (var s : moduleManager.getSlotInfos()) {
            if (available <= 0) break;
            if (!s.present || !s.formed || !s.valid || !s.active) continue;

            MetaMachine mm = MetaMachine.getMachine(getLevel(), s.pos);
            if (!(mm instanceof net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule mod)) continue;

            var dst = mod.getWirelessEnergyContainer();
            if (dst == null) continue;

            long need = dst.getEnergyCanBeInserted();
            if (need <= 0) continue;

            long toSend = Math.min(need, available);


            long removedDelta = this.energyContainer.changeEnergy(-toSend); // отрицательное
            long removed = -removedDelta;
            if (removed <= 0) continue;


            long inserted = dst.changeEnergy(removed); // положительное
            if (inserted < removed) {

                this.energyContainer.changeEnergy(removed - inserted);
            }

            available -= inserted;
        }
    }
}