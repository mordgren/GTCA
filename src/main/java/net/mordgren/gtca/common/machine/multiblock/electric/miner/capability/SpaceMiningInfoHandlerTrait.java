package net.mordgren.gtca.common.machine.multiblock.electric.miner.capability;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class SpaceMiningInfoHandlerTrait extends NotifiableRecipeHandlerTrait<SpaceMiningInfo> {

    public SpaceMiningInfoHandlerTrait(MetaMachine machine) {
        super(machine);
    }

    @Override
    public RecipeCapability<SpaceMiningInfo> getCapability() {
        return SpaceMiningInfoRecipeCapability.CAP;
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

        return Collections.emptyList();
    }

    @Override
    public @Nullable List<SpaceMiningInfo> handleRecipeInner(@NotNull IO io,
                                                             @NotNull GTRecipe recipe,
                                                             @NotNull List<SpaceMiningInfo> left,
                                                             boolean simulate) {
        if (io != IO.IN) return left;

        int mk = getMachineMk();

        for (SpaceMiningInfo req : left) {
            if (req == null) continue;
            if (mk < req.requiredModuleMk()) {
                return left;
            }
        }
        return null;
    }

    private int getMachineMk() {
        Object m = getMachine();
        if (m == null) return 0;


        Integer moduleMk = tryGetIntField(m, "moduleMk");
        if (moduleMk != null) return moduleMk;


        Integer moduleTier = tryGetIntField(m, "moduleTier");
        if (moduleTier != null) {

            return switch (moduleTier) {
                case 6 -> 1;
                case 7 -> 2;
                case 8 -> 3;
                default -> moduleTier;
            };
        }

        return 0;
    }

    private static Integer tryGetIntField(Object obj, String name) {
        try {
            var f = obj.getClass().getField(name);
            Object r = f.get(obj);
            return (r instanceof Integer i) ? i : null;
        } catch (Throwable ignored) {
            try {
                var f = obj.getClass().getDeclaredField(name);
                f.setAccessible(true);
                Object r = f.get(obj);
                return (r instanceof Integer i) ? i : null;
            } catch (Throwable ignored2) {
                return null;
            }
        }
    }
}
