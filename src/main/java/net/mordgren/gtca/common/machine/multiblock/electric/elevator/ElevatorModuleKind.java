package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

public enum ElevatorModuleKind {
    NONE,
    MINER,
    ASSEMBLER,
    PUMP;

    public String shortName() {
        return switch (this) {
            case MINER -> "MINER";
            case ASSEMBLER -> "ASM";
            case PUMP -> "PUMP";
            default -> "NONE";
        };
    }
}