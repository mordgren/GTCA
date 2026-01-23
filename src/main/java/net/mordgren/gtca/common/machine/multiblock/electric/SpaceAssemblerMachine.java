package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule;

public class SpaceAssemblerMachine extends WorkableElectricMultiblockMachine implements ITieredMachine, IElevatorModule {

    private final int moduleTier;
    private boolean enabledByElevator = false;

    public SpaceAssemblerMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.moduleTier = tier;
    }

    @Override
    public int getTier() {
        return moduleTier;
    }

    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return ElevatorModuleKind.ASSEMBLER;
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

