package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;

import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.GTCASpaceMiningCapabilities;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfo;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfoRecipeCapability;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.MiningParts;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.SpaceMiningRegistry;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

public final class GTCASpaceMiningRecipeGen {

    private GTCASpaceMiningRecipeGen() {}

    public static void generate(Consumer<FinishedRecipe> provider) {
        GTCA.LOGGER.info("[SpaceMining] Generating SPACE_MINER recipes, asteroids={}", SpaceMiningRegistry.size());
        GTCASpaceMiningCapabilities.init();
        for (AsteroidDefinition a : SpaceMiningRegistry.all()) {
            generateForAsteroid(a, provider);
        }

        GTCA.LOGGER.info("[SpaceMining] Done generating SPACE_MINER recipes.");
    }

    private static void generateForAsteroid(AsteroidDefinition a, Consumer<FinishedRecipe> provider) {

        for (DroneTier drone : DroneTier.values()) {
            if (!drone.isBetween(a.minDrone(), a.maxDrone())) continue;

            DrillMaterialTier requiredDrill = drone.requiredDrillTier();
            if (requiredDrill == null) continue;
            if (!requiredDrill.isBetween(a.minDrill(), a.maxDrill())) continue;

            long eutL = adjustedEUt(a, drone);
            int eut = (int) Math.min(Integer.MAX_VALUE, Math.max(1L, eutL));
            int dur = adjustedDurationTicks(a, drone);

            int sizeMin = adjustedSizeMin(a, drone);
            int sizeMax = adjustedSizeMax(a, drone);
            int avgStacks = Math.max(1, (sizeMin + sizeMax) / 2);

            SpaceMiningInfo info = new SpaceMiningInfo(
                    a.requiredModuleMk(),
                    a.distanceMin(),
                    a.distanceMax(),
                    sizeMin,
                    sizeMax,
                    a.weight()
            );

            List<OreEntry> ores = a.ores();
            if (ores.size() > 9) ores = ores.subList(0, 9);

            String asteroidKey = a.id().getPath().replace('/', '_');
            String droneKey = drone.name().toLowerCase();

            for (PlasmaTier plasma : PlasmaTier.values()) {
                FluidStack plasmaStack = plasma.plasma(1000);
                if (plasmaStack.isEmpty()) {
                    GTCA.LOGGER.warn("[SpaceMining] Missing plasma fluid for tier={} material={}",
                            plasma.name(), plasma.material());
                    continue;
                }

                String rid = "space_miner_" + asteroidKey + "_" + droneKey + "_" + plasma.name().toLowerCase();

                var b = GTCARecipeTypes.SPACE_MINER.recipeBuilder(rid)
                        .EUt(eut)
                        .duration(dur)
                        .notConsumable(drone.droneStack(1))
                        .inputItems(MiningParts.drillTip(requiredDrill, 4))
                        .inputItems(MiningParts.drillRod(requiredDrill, 4))
                        .inputFluids(plasmaStack);

                SpaceMiningInfoRecipeCapability.putInfo(b, info);
                trySetCWUt(b, a.minCWU());

                for (OreEntry ore : ores) {
                    double baseItems = avgStacks * 64.0 * ore.percent01();
                    int out = Math.max(1, (int) Math.round(baseItems * plasma.lootMultiplier()));
                    b.outputItems(GTCAHelper.getItem("raw", ore.material(), out));
                }

                b.save(provider);
            }
        }
    }

    private static void trySetCWUt(Object recipeBuilder, int cwu) {
        if (recipeBuilder == null) return;
        try {
            Method m = recipeBuilder.getClass().getMethod("CWUt", int.class);
            m.invoke(recipeBuilder, cwu);
        } catch (Throwable ignored) {
        }
    }

    private static long adjustedEUt(AsteroidDefinition a, DroneTier drone) {
        DroneTier base = a.baselineDrone();
        double k = Math.sqrt((double) drone.index() / (double) base.index());
        return Math.max(1L, Math.round(a.baseEUt() * k));
    }

    private static int adjustedDurationTicks(AsteroidDefinition a, DroneTier drone) {
        DroneTier base = a.baselineDrone();
        double k = Math.sqrt((double) base.index() / (double) drone.index());
        return Math.max(1, (int) Math.round(a.baseDurationTicks() * k));
    }

    private static int adjustedSizeMin(AsteroidDefinition a, DroneTier drone) {
        DroneTier base = a.baselineDrone();
        int delta = Math.max(0, drone.index() - base.index());
        int add = (int) ((1L << Math.min(delta, 30)) - 1L);
        return a.baseSizeMinStacks() + add;
    }

    private static int adjustedSizeMax(AsteroidDefinition a, DroneTier drone) {
        DroneTier base = a.baselineDrone();
        int delta = Math.max(0, drone.index() - base.index());
        int add = (int) ((1L << Math.min(delta, 30)) - 1L);
        return a.baseSizeMaxStacks() + add;
    }
}