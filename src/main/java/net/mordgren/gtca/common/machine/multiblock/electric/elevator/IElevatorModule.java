package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

import com.gregtechceu.gtceu.api.capability.IEnergyContainer;

public interface IElevatorModule {

    ElevatorModuleKind getElevatorModuleKind();

    void setEnabledByElevator(boolean enabled);
    boolean isEnabledByElevator();
    default boolean requiresComputation() { return false; }
    IEnergyContainer getWirelessEnergyContainer();

    default boolean isValidForElevator() { return true; }
}


