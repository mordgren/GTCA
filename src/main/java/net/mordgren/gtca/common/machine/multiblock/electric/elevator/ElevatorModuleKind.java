package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

public enum ElevatorModuleKind {
    MINER(true),
    ASSEMBLER(false),
    PUMP(false),
    UNKNOWN(false);

    private final boolean requiresComputation;

    ElevatorModuleKind(boolean requiresComputation) {
        this.requiresComputation = requiresComputation;
    }

    public boolean requiresComputation() {
        return requiresComputation;
    }
}