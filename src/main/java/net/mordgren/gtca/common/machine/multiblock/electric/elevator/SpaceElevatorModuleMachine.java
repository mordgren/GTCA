package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;

/*
Created by sensesgone
Space Elevator module shell v2
 */

public class SpaceElevatorModuleMachine extends ElevatorLinkedModuleMachine {

    private final ElevatorModuleKind kind;

    public SpaceElevatorModuleMachine(IMachineBlockEntity holder, int moduleTier, ElevatorModuleKind kind) {
        super(holder, moduleTier);
        this.kind = kind == null ? ElevatorModuleKind.UNKNOWN : kind;
    }

    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return kind;
    }

    @Override
    public boolean requiresComputation() {
        return kind == ElevatorModuleKind.MINER;
    }

    @Override
    public boolean isValidForElevator() {
        return true;
    }
}