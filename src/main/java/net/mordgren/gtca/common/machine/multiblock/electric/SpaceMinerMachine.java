package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule;

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
}