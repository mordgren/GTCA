package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule;

public class SpaceMinerMachine extends WorkableElectricMultiblockMachine implements ITieredMachine, IElevatorModule {

    private final int moduleTier;
    private boolean enabledByElevator = false;

    public SpaceMinerMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.moduleTier = tier;
    }

    // --- ITieredMachine ---
    @Override
    public int getTier() {
        return moduleTier;
    }

    // --- IElevatorModule ---
    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return ElevatorModuleKind.MINER;
    }

    @Override
    public void setEnabledByElevator(boolean enabled) {
        this.enabledByElevator = enabled;

        if (getLevel() != null && !getLevel().isClientSide) {
            recipeLogic.setWorkingEnabled(enabled);
        }
    }

    @Override
    public boolean isEnabledByElevator() {
        return enabledByElevator;
    }
}