package net.mordgren.gtca.common.machine.multiblock.electric.miner.card;

import net.minecraft.resources.ResourceLocation;

public record SpaceMiningTarget(
        ResourceLocation id,
        String displayName,
        String sector,
        int requiredModuleMk,
        int circuitConfiguration,
        String description
) {
    public SpaceMiningTarget {
        if (circuitConfiguration < 1 || circuitConfiguration > 32) {
            throw new IllegalArgumentException(
                    "Space Mining target circuit configuration must be in range 1..32: "
                            + id + " -> " + circuitConfiguration
            );
        }
    }
}
