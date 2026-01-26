package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.AsteroidBuilder;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.SpaceMiningRegistry;

public final class GTCASpaceMiningAsteroids {

    private GTCASpaceMiningAsteroids() {}

    public static void init() {
        GTCA.LOGGER.info("[SpaceMining] GTCASpaceMiningAsteroids.init() called");

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

        GTCA.LOGGER.info("[SpaceMining] Registered asteroids = {}", SpaceMiningRegistry.size());
    }
}
