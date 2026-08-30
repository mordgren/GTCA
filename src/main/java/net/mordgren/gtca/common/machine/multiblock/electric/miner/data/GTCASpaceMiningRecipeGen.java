package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.MiningParts;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.SpaceMiningRegistry;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

public final class GTCASpaceMiningRecipeGen {

    private GTCASpaceMiningRecipeGen() {}

    public static void generate(Consumer<FinishedRecipe> provider) {
        GTCA.LOGGER.info("[SpaceMining] Generating SPACE_MINER recipes, asteroids={}", SpaceMiningRegistry.size());

        int attempted = 0;
        int saved = 0;
        int skipped = 0;

        Set<String> ids = new HashSet<>();

        for (AsteroidDefinition asteroid : SpaceMiningRegistry.all()) {
            Result result = generateForAsteroid(asteroid, provider, ids);

            attempted += result.attempted;
            saved += result.saved;
            skipped += result.skipped;
        }

        GTCA.LOGGER.info("[SpaceMining] Done. attempted={} saved={} skipped={}", attempted, saved, skipped);
    }

    private record Result(int attempted, int saved, int skipped) {}

    private static Result generateForAsteroid(AsteroidDefinition asteroid,
                                              Consumer<FinishedRecipe> provider,
                                              Set<String> ids) {
        int attempted = 0;
        int saved = 0;
        int skipped = 0;

        String asteroidKey = asteroid.id().getPath().replace('/', '_');

        for (DroneTier drone : DroneTier.values()) {
            if (!drone.isBetween(asteroid.minDrone(), asteroid.maxDrone())) {
                skipped++;
                continue;
            }

            DrillMaterialTier drill = drone.requiredDrillTier();

            if (!drill.isBetween(asteroid.minDrill(), asteroid.maxDrill())) {
                skipped++;
                continue;
            }

            long eutLong = adjustedEUt(asteroid, drone);
            int eut = (int) Math.min(Integer.MAX_VALUE, Math.max(1L, eutLong));

            int duration = adjustedDurationTicks(asteroid, drone);
            int sizeMin = adjustedSizeMin(asteroid, drone);
            int sizeMax = adjustedSizeMax(asteroid, drone);

            int avgStacks = Math.max(1, (sizeMin + sizeMax) / 2);

            String droneKey = drone.name().toLowerCase(Locale.ROOT);

            for (PlasmaTier plasma : PlasmaTier.values()) {
                String plasmaKey = plasma.name().toLowerCase(Locale.ROOT);

                FluidStack plasmaStack = plasma.plasma();
                if (plasmaStack.isEmpty()) {
                    skipped++;
                    GTCA.LOGGER.warn("[SpaceMining] Missing plasma: asteroid={} drone={} plasma={} material={}",
                            asteroid.id(), drone.name(), plasma.name(), plasma.material());
                    continue;
                }

                String recipeId = "space_miner_" + asteroidKey + "_" + droneKey + "_" + plasmaKey;

                if (!ids.add(recipeId)) {
                    skipped++;
                    GTCA.LOGGER.error("[SpaceMining] Duplicate recipe id: {}", recipeId);
                    continue;
                }

                attempted++;

                try {
                    var builder = GTCARecipeTypes.SPACE_MINER.recipeBuilder(recipeId)
                            .EUt(eut)
                            .duration(duration)
                            .circuitMeta(asteroid.circuitConfiguration())
                            .notConsumable(drone.droneStack(1))
                            .inputItems(MiningParts.drillTip(drill, 4))
                            .inputItems(MiningParts.drillRod(drill, 4))
                            .inputFluids(plasmaStack)
                            .CWUt(asteroid.minCWU())
                            .addData(SpaceMiningRecipeDataKeys.ASTEROID_ID, asteroid.id().toString())
                            .addData(SpaceMiningRecipeDataKeys.ASTEROID_NAME, prettyAsteroidName(asteroid))
                            .addData(SpaceMiningRecipeDataKeys.CIRCUIT, asteroid.circuitConfiguration())
                            .addData(SpaceMiningRecipeDataKeys.REQUIRED_MODULE_MK, asteroid.requiredModuleMk())
                            .addData(SpaceMiningRecipeDataKeys.DISTANCE_MIN, asteroid.distanceMin())
                            .addData(SpaceMiningRecipeDataKeys.DISTANCE_MAX, asteroid.distanceMax())
                            .addData(SpaceMiningRecipeDataKeys.SIZE_MIN_STACKS, sizeMin)
                            .addData(SpaceMiningRecipeDataKeys.SIZE_MAX_STACKS, sizeMax)
                            .addData(SpaceMiningRecipeDataKeys.MIN_CWU, asteroid.minCWU())
                            .addData(SpaceMiningRecipeDataKeys.WEIGHT, asteroid.weight());


                    boolean hasAnyOutput = false;

                    List<OreEntry> ores = asteroid.ores();
                    if (ores.size() > 9) {
                        ores = ores.subList(0, 9);
                    }

                    double sum = 0.0;
                    for (OreEntry ore : ores) {
                        sum += Math.max(0.0, ore.percent01());
                    }
                    if (sum <= 0.0) {
                        skipped++;
                        GTCA.LOGGER.error("[SpaceMining] Invalid ore percent sum for {}", asteroid.id());
                        continue;
                    }

                    for (OreEntry ore : ores) {
                        double share = Math.max(0.0, ore.percent01()) / sum;

                        int minOut = outputAmount(sizeMin, share, plasma);
                        int maxOut = outputAmount(sizeMax, share, plasma);

                        if (maxOut < minOut) {
                            int tmp = minOut;
                            minOut = maxOut;
                            maxOut = tmp;
                        }

                        ItemStack previewStack = GTCAHelper.getItem("raw", ore.material(), 1);

                        if (previewStack.isEmpty()) {
                            GTCA.LOGGER.warn("[SpaceMining] Empty output skipped: recipe={} material={} range={}..{}",
                                    recipeId, ore.material().getName(), minOut, maxOut);
                            continue;
                        }

                        if (minOut == maxOut) {
                            builder.outputItems(GTCAHelper.getItem("raw", ore.material(), minOut));
                        } else {
                            builder.outputItemsRanged(previewStack, UniformInt.of(minOut, maxOut));
                        }

                        hasAnyOutput = true;
                    }

                    if (!hasAnyOutput) {
                        skipped++;
                        GTCA.LOGGER.error("[SpaceMining] No outputs for recipe={}", recipeId);
                        continue;
                    }

                    builder.save(provider);
                    saved++;

                } catch (Throwable t) {
                    skipped++;
                    GTCA.LOGGER.error("[SpaceMining] Exception building recipe={} asteroid={} drone={} plasma={}",
                            recipeId, asteroid.id(), drone.name(), plasma.name(), t);
                }
            }
        }

        return new Result(attempted, saved, skipped);
    }

    private static long adjustedEUt(AsteroidDefinition asteroid, DroneTier drone) {
        DroneTier base = asteroid.baselineDrone();

        double multiplier = Math.sqrt((double) drone.index() / (double) base.index());

        return Math.max(1L, Math.round(asteroid.baseEUt() * multiplier));
    }

    private static int outputAmount(int sizeStacks, double share, PlasmaTier plasma) {
        double items = sizeStacks * 64.0 * share * plasma.lootMultiplier();
        long rounded = Math.round(items);

        return (int) Math.max(1L, Math.min((long) Integer.MAX_VALUE, rounded));
    }

    private static String prettyAsteroidName(AsteroidDefinition asteroid) {
        String path = asteroid.id().getPath();

        int slash = path.lastIndexOf('/');
        if (slash >= 0 && slash + 1 < path.length()) {
            path = path.substring(slash + 1);
        }

        path = path.replace('_', ' ');

        String[] words = path.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isBlank()) continue;

            if (!result.isEmpty()) {
                result.append(' ');
            }

            result.append(Character.toUpperCase(word.charAt(0)));

            if (word.length() > 1) {
                result.append(word.substring(1));
            }
        }

        if (result.isEmpty()) {
            return asteroid.id().toString();
        }

        return result + " Asteroid";
    }

    private static int adjustedDurationTicks(AsteroidDefinition asteroid, DroneTier drone) {
        DroneTier base = asteroid.baselineDrone();

        double multiplier = Math.sqrt((double) base.index() / (double) drone.index());

        return Math.max(1, (int) Math.round(asteroid.baseDurationTicks() * multiplier));
    }

    private static int adjustedSizeMin(AsteroidDefinition asteroid, DroneTier drone) {
        DroneTier base = asteroid.baselineDrone();

        int delta = Math.max(0, drone.index() - base.index());
        int add = (int) ((1L << Math.min(delta, 30)) - 1L);

        return asteroid.baseSizeMinStacks() + add;
    }

    private static int adjustedSizeMax(AsteroidDefinition asteroid, DroneTier drone) {
        DroneTier base = asteroid.baselineDrone();

        int delta = Math.max(0, drone.index() - base.index());
        int add = (int) ((1L << Math.min(delta, 30)) - 1L);

        return asteroid.baseSizeMaxStacks() + add;
    }
}
