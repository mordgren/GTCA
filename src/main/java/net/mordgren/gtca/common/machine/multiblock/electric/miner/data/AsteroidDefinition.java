package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record AsteroidDefinition(
        ResourceLocation id,

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
        // Нормализация: если baseline не задан — делаем равным minDrone
        if (baselineDrone == null) baselineDrone = minDrone;
        ores = List.copyOf(ores);
    }
}