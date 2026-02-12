package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfo;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfoRecipeCapability;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningKey;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningKeyRecipeCapability;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.MiningParts;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.SpaceMiningRegistry;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

public final class GTCASpaceMiningRecipeGen {

    private GTCASpaceMiningRecipeGen() {}

    public static void generate(Consumer<FinishedRecipe> provider) {
        int asteroids = SpaceMiningRegistry.size();
        GTCA.LOGGER.info("[SpaceMining] Generating SPACE_MINER recipes, asteroids={}", asteroids);

        // counters
        int attempted = 0;
        int saved = 0;

        int skipDrone = 0;
        int skipDrill = 0;
        int skipPlasmaEmpty = 0;
        int skipOutputEmpty = 0;
        int skipNoOutputs = 0;
        int dupId = 0;
        int exceptions = 0;

        Set<String> ids = new HashSet<>();

        for (AsteroidDefinition a : SpaceMiningRegistry.all()) {
            try {
                Result r = generateForAsteroid(a, provider, ids);
                attempted += r.attempted;
                saved += r.saved;
                skipDrone += r.skipDrone;
                skipDrill += r.skipDrill;
                skipPlasmaEmpty += r.skipPlasmaEmpty;
                skipOutputEmpty += r.skipOutputEmpty;
                skipNoOutputs += r.skipNoOutputs;
                dupId += r.dupId;
                exceptions += r.exceptions;
            } catch (Throwable t) {
                exceptions++;
                GTCA.LOGGER.error("[SpaceMining] FAILED asteroid={} (uncaught)", a.id(), t);
            }
        }

        GTCA.LOGGER.info(
                "[SpaceMining] Done. attempted={} saved={} | skipDrone={} skipDrill={} skipPlasmaEmpty={} skipOutputEmpty={} skipNoOutputs={} dupId={} exceptions={}",
                attempted, saved, skipDrone, skipDrill, skipPlasmaEmpty, skipOutputEmpty, skipNoOutputs, dupId, exceptions
        );
    }

    private record Result(
            int attempted, int saved,
            int skipDrone, int skipDrill, int skipPlasmaEmpty,
            int skipOutputEmpty, int skipNoOutputs,
            int dupId, int exceptions
    ) {}

    private static Result generateForAsteroid(AsteroidDefinition a,
                                              Consumer<FinishedRecipe> provider,
                                              Set<String> ids) {

        int attempted = 0;
        int saved = 0;

        int skipDrone = 0;
        int skipDrill = 0;
        int skipPlasmaEmpty = 0;
        int skipOutputEmpty = 0;
        int skipNoOutputs = 0;
        int dupId = 0;
        int exceptions = 0;

        for (DroneTier drone : DroneTier.values()) {
            if (!drone.isBetween(a.minDrone(), a.maxDrone())) { skipDrone++; continue; }

            DrillMaterialTier requiredDrill = drone.requiredDrillTier();
            if (requiredDrill == null) { skipDrill++; continue; }
            if (!requiredDrill.isBetween(a.minDrill(), a.maxDrill())) { skipDrill++; continue; }

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
            String droneKey = drone.name().toLowerCase(Locale.ROOT);


            for (PlasmaTier plasma : PlasmaTier.values()) {

                FluidStack plasmaStack = plasma.plasma(plasma.usageMb());
                if (plasmaStack.isEmpty()) {
                    skipPlasmaEmpty++;
                    GTCA.LOGGER.warn("[SpaceMining] plasma empty: asteroid={} drone={} plasma={} (material={})",
                            a.id(), drone.name(), plasma.name(), plasma.material());
                    continue;
                }

                String rid = "space_miner_" + asteroidKey + "_" + droneKey + "_" + plasma.name().toLowerCase(Locale.ROOT);

                // duplicate id check (если это случится — у тебя реально будут “пропадать” сотни рецептов)
                if (!ids.add(rid)) {
                    dupId++;
                    GTCA.LOGGER.error("[SpaceMining] DUPLICATE RECIPE ID: {} (asteroid={}, drone={}, plasma={})",
                            rid, a.id(), drone.name(), plasma.name());
                    continue;
                }

                attempted++;

                try {
                    var b = GTCARecipeTypes.SPACE_MINER.recipeBuilder(rid)
                            .EUt(eut)
                            .duration(dur)
                            .notConsumable(drone.droneStack(1))
                            .inputItems(MiningParts.drillTip(requiredDrill, 4))
                            .inputItems(MiningParts.drillRod(requiredDrill, 4))
                            .inputFluids(plasmaStack);

                    SpaceMiningInfoRecipeCapability.putInfo(b, info);
                    SpaceMiningKeyRecipeCapability.putKey(b, SpaceMiningKey.of(a.id()));
                    trySetCWUt(b, a.minCWU());

                    boolean hasAnyOutput = false;

                    for (OreEntry ore : ores) {
                        double baseItems = avgStacks * 64.0 * ore.percent01();
                        int out = Math.max(1, (int) Math.round(baseItems * plasma.lootMultiplier()));

                        ItemStack stack = GTCAHelper.getItem("raw", ore.material(), out);

                        if (stack.isEmpty()) {
                            skipOutputEmpty++;
                            GTCA.LOGGER.error("[SpaceMining] EMPTY OUTPUT: rid={} asteroid={} drone={} plasma={} material={} amount={}",
                                    rid, a.id(), drone.name(), plasma.name(), ore.material().getName(), out);
                            // не добавляем пустоту
                            continue;
                        }

                        b.outputItems(stack);
                        hasAnyOutput = true;
                    }

                    if (!hasAnyOutput) {
                        skipNoOutputs++;
                        GTCA.LOGGER.error("[SpaceMining] NO OUTPUTS AFTER BUILD: rid={} asteroid={} drone={} plasma={}",
                                rid, a.id(), drone.name(), plasma.name());
                        continue;
                    }

                    b.save(provider);
                    saved++;

                } catch (Throwable t) {
                    exceptions++;
                    GTCA.LOGGER.error("[SpaceMining] EXCEPTION building rid={} asteroid={} drone={} plasma={}",
                            rid, a.id(), drone.name(), plasma.name(), t);
                }
            }
        }

        return new Result(attempted, saved, skipDrone, skipDrill, skipPlasmaEmpty, skipOutputEmpty, skipNoOutputs, dupId, exceptions);
    }

    // ------------------ CWU reflection ------------------
    private static void trySetCWUt(Object recipeBuilder, int cwu) {
        if (recipeBuilder == null) return;
        try {
            Method m = recipeBuilder.getClass().getMethod("CWUt", int.class);
            m.invoke(recipeBuilder, cwu);
        } catch (Throwable ignored) {}
    }

    // ------------------ math ------------------
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