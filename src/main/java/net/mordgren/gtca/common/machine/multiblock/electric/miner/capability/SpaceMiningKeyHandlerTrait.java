package net.mordgren.gtca.common.machine.multiblock.electric.miner.capability;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import net.minecraft.resources.ResourceLocation;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.AsteroidDefinition;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.SpaceMiningRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class SpaceMiningKeyHandlerTrait extends NotifiableRecipeHandlerTrait<SpaceMiningKey> {

    public SpaceMiningKeyHandlerTrait(MetaMachine machine) {
        super(machine);
    }

    @Override
    public RecipeCapability<SpaceMiningKey> getCapability() {
        return SpaceMiningKeyRecipeCapability.CAP;
    }

    @Override
    public IO getHandlerIO() {
        return IO.IN;
    }

    @Override
    public double getTotalContentAmount() {
        return 0.0;
    }

    @Override
    public @NotNull List<Object> getContents() {
        SpaceMiningKey selected = getSelectedKeyFromMachine();


        if (selected != null && !selected.isWildcard()) {
            return List.of(selected);
        }
        List<Object> out = new ArrayList<>();
        for (AsteroidDefinition a : SpaceMiningRegistry.all()) {
            out.add(SpaceMiningKey.of(a.id()));
        }
        return out;
    }

    @Override
    public @Nullable List<SpaceMiningKey> handleRecipeInner(@NotNull IO io,
                                                            @NotNull GTRecipe recipe,
                                                            @NotNull List<SpaceMiningKey> left,
                                                            boolean simulate) {
        if (io != IO.IN) return left;

        SpaceMiningKey selected = getSelectedKeyFromMachine();
        if (selected == null || selected.isWildcard()) return null;

        for (SpaceMiningKey req : left) {
            if (req == null) continue;
            if (!req.matches(selected)) return left;
        }
        return null;
    }


    private @Nullable SpaceMiningKey getSelectedKeyFromMachine() {
        Object m = getMachine();
        if (m == null) return null;


        String s = tryGetStringField(m,
                "selectedAsteroidId",
                "selectedAsteroidKey",
                "asteroidId",
                "asteroidKey",
                "currentAsteroidId",
                "currentAsteroidKey"
        );
        if (s != null && !s.isBlank()) return new SpaceMiningKey(s);


        ResourceLocation rl = tryGetRLField(m,
                "selectedAsteroid",
                "currentAsteroid",
                "asteroid"
        );
        if (rl != null) return SpaceMiningKey.of(rl);

        return null;
    }

    private static @Nullable String tryGetStringField(Object obj, String... names) {
        for (String n : names) {
            try {
                var f = obj.getClass().getField(n);
                Object r = f.get(obj);
                if (r instanceof String str) return str;
            } catch (Throwable ignored) { }
            try {
                var f = obj.getClass().getDeclaredField(n);
                f.setAccessible(true);
                Object r = f.get(obj);
                if (r instanceof String str) return str;
            } catch (Throwable ignored) { }
        }
        return null;
    }

    private static @Nullable ResourceLocation tryGetRLField(Object obj, String... names) {
        for (String n : names) {
            try {
                var f = obj.getClass().getField(n);
                Object r = f.get(obj);
                if (r instanceof ResourceLocation rl) return rl;
            } catch (Throwable ignored) { }
            try {
                var f = obj.getClass().getDeclaredField(n);
                f.setAccessible(true);
                Object r = f.get(obj);
                if (r instanceof ResourceLocation rl) return rl;
            } catch (Throwable ignored) { }
        }
        return null;
    }
}