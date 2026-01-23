package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

public interface IElevatorModule {

    ElevatorModuleKind getElevatorModuleKind();
    void setEnabledByElevator(boolean enabled);
    boolean isEnabledByElevator();
    default boolean requiresComputation() { return false; }
    default boolean isValidForElevator() { return true; }
}
