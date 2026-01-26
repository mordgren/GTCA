package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.world.item.ItemStack;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpaceMinerMachine extends WorkableElectricMultiblockMachine implements IElevatorModule {

    public final int moduleTier;
    private boolean enabledByElevator = false;

    @Persisted
    private final NotifiableEnergyContainer wirelessEnergy;

    public SpaceMinerMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.moduleTier = tier;

        long cap = wirelessCapacityForTier(tier);
        long voltage = GTValues.V[tier];

        this.wirelessEnergy = NotifiableEnergyContainer.receiverContainer(this, cap, voltage, 1);


        this.wirelessEnergy.setSideInputCondition(side -> false);
        this.wirelessEnergy.setSideOutputCondition(side -> false);


        this.recipeLogic.setWorkingEnabled(false);
    }

    // ---- IElevatorModule ----

    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return ElevatorModuleKind.MINER;
    }

    @Override
    public void setEnabledByElevator(boolean enabled) {
        this.enabledByElevator = enabled;

        if (getLevel() != null && !getLevel().isClientSide) {
            this.recipeLogic.setWorkingEnabled(enabled);
        }
    }


    @Override
    public boolean isEnabledByElevator() {
        return enabledByElevator;
    }

    @Override
    public boolean requiresComputation() {
        return true;
    }

    @Override
    public NotifiableEnergyContainer getWirelessEnergyContainer() {
        return wirelessEnergy;
    }

    // ---- capacity helper ----
    private static long wirelessCapacityForTier(int tier) {

        int mk = switch (tier) {
            case GTValues.LuV -> 1;
            case GTValues.ZPM -> 2;
            case GTValues.UV  -> 3;
            default -> 1;
        };

        long cap = 786_432L;
        for (int i = 1; i < mk; i++) cap *= 4L;
        return cap;
    }
    private static final Logger LOG = LogManager.getLogger("GTCA-SpaceMiner");

    private TickableSubscription debugSub = null;
    private int debugTimer = 0;
    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        if (debugSub == null) {
            debugSub = subscribeServerTick(this::debugTick);
        }
        debugTimer = 0;
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        debugSub = null;
        debugTimer = 0;
    }

    @Override
    public void onUnload() {
        super.onUnload();
        debugSub = null;
        debugTimer = 0;
    }
    private void debugTick() {
        if (getLevel() == null || getLevel().isClientSide) return;
        if (!isFormed()) return;

        if (++debugTimer < 20) return; // раз в секунду
        debugTimer = 0;

        LOG.info("[SpaceMiner] tick OK | formed={} | pos={}", isFormed(), getPos());
    }
}