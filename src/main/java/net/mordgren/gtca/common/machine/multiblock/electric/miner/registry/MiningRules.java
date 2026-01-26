package net.mordgren.gtca.common.machine.multiblock.electric.miner.registry;

import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.DrillMaterialTier;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.DroneTier;

public final class MiningRules {
    private MiningRules() {}

    public static boolean isDrillAllowed(DroneTier drone, DrillMaterialTier drill) {
        return drill.isAtMost(drone.requiredDrillTier());
    }
}
