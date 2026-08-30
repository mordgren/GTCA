package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Objects;

public record AsteroidDefinition(
        ResourceLocation id,

        int circuitConfiguration,

        long baseEUt,
        int baseDurationTicks,

        int requiredModuleMk,

        int distanceMin,
        int distanceMax,

        int baseSizeMinStacks,
        int baseSizeMaxStacks,

        int minCWU,
        int weight,

        DroneTier minDrone,
        DroneTier maxDrone,
        DroneTier baselineDrone,

        DrillMaterialTier minDrill,
        DrillMaterialTier maxDrill,

        List<OreEntry> ores
) {
    public AsteroidDefinition {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(minDrone, "minDrone");
        Objects.requireNonNull(maxDrone, "maxDrone");
        Objects.requireNonNull(minDrill, "minDrill");
        Objects.requireNonNull(maxDrill, "maxDrill");

        if (circuitConfiguration < 1 || circuitConfiguration > 32) {
            throw new IllegalArgumentException("circuitConfiguration must be in 1..32: " + id);
        }

        if (baselineDrone == null) baselineDrone = minDrone;

        if (baseEUt <= 0) throw new IllegalArgumentException("baseEUt must be > 0");
        if (baseDurationTicks <= 0) throw new IllegalArgumentException("baseDurationTicks must be > 0");
        if (requiredModuleMk < 1) throw new IllegalArgumentException("requiredModuleMk must be >= 1");
        if (distanceMin > distanceMax) throw new IllegalArgumentException("distanceMin > distanceMax");
        if (baseSizeMinStacks > baseSizeMaxStacks) throw new IllegalArgumentException("baseSizeMinStacks > baseSizeMaxStacks");
        if (minCWU < 0) throw new IllegalArgumentException("minCWU must be >= 0");
        if (weight <= 0) throw new IllegalArgumentException("weight must be > 0");

        ores = ores == null ? List.of() : List.copyOf(ores);
        if (ores.isEmpty()) throw new IllegalArgumentException("ores is empty");

        if (!baselineDrone.isBetween(minDrone, maxDrone)) {
            throw new IllegalArgumentException("baselineDrone must be between minDrone and maxDrone");
        }
    }
}
