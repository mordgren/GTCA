package net.mordgren.gtca.common.machine.multiblock.electric.purification;

public interface IThermalControlSource {

    int getCurrentTemperatureK();

    boolean isThermalCycleActive();

    boolean isCoolingPhase();

    boolean isBatchRuined();

    int getCompletedThermalCycles();

    default boolean isHeatingPhase() {
        return isThermalCycleActive()
                && !isCoolingPhase()
                && !isBatchRuined();
    }
}
