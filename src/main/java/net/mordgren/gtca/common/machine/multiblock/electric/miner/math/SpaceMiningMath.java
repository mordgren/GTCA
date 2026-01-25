package net.mordgren.gtca.common.machine.multiblock.electric.miner.math;

import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.AsteroidDefinition;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.DroneTier;

public final class SpaceMiningMath {

    private SpaceMiningMath() {}

    public static long adjustedEUt(AsteroidDefinition a, DroneTier drone) {
        DroneTier base = a.baselineDrone();
        double k = Math.sqrt((double) drone.index() / (double) base.index());
        return Math.max(1L, Math.round(a.baseEUt() * k));
    }

    public static int adjustedDurationTicks(AsteroidDefinition a, DroneTier drone) {
        DroneTier base = a.baselineDrone();
        double k = Math.sqrt((double) base.index() / (double) drone.index());
        return Math.max(1, (int) Math.round(a.baseDurationTicks() * k));
    }

    public static int adjustedSizeMin(AsteroidDefinition a, DroneTier drone) {
        DroneTier base = a.baselineDrone();
        int delta = Math.max(0, drone.index() - base.index());
        int add = (int) ((1L << Math.min(delta, 30)) - 1L);
        return a.baseSizeMinStacks() + add;
    }

    public static int adjustedSizeMax(AsteroidDefinition a, DroneTier drone) {
        DroneTier base = a.baselineDrone();
        int delta = Math.max(0, drone.index() - base.index());
        int add = (int) ((1L << Math.min(delta, 30)) - 1L);
        return a.baseSizeMaxStacks() + add;
    }
}
