package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

public enum ElevatorModuleKind {
    MINER("Miner", true),
    ASSEMBLER("Assembler", false),
    PUMP("Pump", false),
    UNKNOWN("Unknown", false);

    private final String displayName;
    private final boolean requiresComputation;

    ElevatorModuleKind(String displayName, boolean requiresComputation) {
        this.displayName = displayName;
        this.requiresComputation = requiresComputation;
    }

    public String displayName() {
        return displayName;
    }

    public boolean requiresComputation() {
        return requiresComputation;
    }
}