package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.*;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.AsteroidBuilder;

public final class GTCASpaceMiningAsteroids {

    public static void init() {
        AsteroidBuilder.asteroid(GTCA.id("asteroid/coal"))
                .eut(1920)
                .timeSeconds(10)
                .requiresModuleMk(1)
                .distance(1, 40)
                .sizeStacks(30, 120)
                .minCWU(20)
                .weight(200)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK7_ZPM)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Coal, 0.70)
                .ore(GTMaterials.Graphite, 0.30)
                .buildAndRegister();
    }
}
