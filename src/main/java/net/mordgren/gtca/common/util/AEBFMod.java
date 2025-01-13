package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import org.jetbrains.annotations.NotNull;

import static com.gregtechceu.gtceu.api.recipe.OverclockingLogic.getCoilEUtDiscount;

public class AEBFMod {
//    public static GTRecipe aebfOverclock(MetaMachine machine, @NotNull GTRecipe recipe, @NotNull OCParams params,
//                                        @NotNull OCResult result) {
//        if (machine instanceof CoilWorkableElectricMultiblockMachine coilMachine) {
//            final var blastFurnaceTemperature = coilMachine.getCoilType().getCoilTemperature() +
//                    100 * Math.max(0, coilMachine.getTier() - GTValues.MV);
//            if (!recipe.data.contains("ebf_temp") || recipe.data.getInt("ebf_temp") > blastFurnaceTemperature) {
//                return null;
//            }
//            if (RecipeHelper.getRecipeEUtTier(recipe) > coilMachine.getTier()) {
//                return null;
//            }
//            var re = RecipeHelper.applyOverclock(
//                    new OverclockingLogic((p, r, maxVoltage) -> OverclockingLogic.heatingCoilOC(
//                            params, result, maxVoltage,
//                            blastFurnaceTemperature,
//                            recipe.data.contains("ebf_temp") ? recipe.data.getInt("ebf_temp") : 0)),
//                    recipe, coilMachine.getOverclockVoltage(), params, result);
//            result.setDuration((int) result.getDuration() / 2);
//            return re;
//        }
//        return null;
//    }

    public static @NotNull ModifierFunction aebfOverclock(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof CoilWorkableElectricMultiblockMachine coilMachine)) {
            return RecipeModifier.nullWrongType(CoilWorkableElectricMultiblockMachine.class, machine);
        }

        int blastFurnaceTemperature = coilMachine.getCoilType().getCoilTemperature() +
                (100 * Math.max(0, coilMachine.getTier() - GTValues.MV));
        int recipeTemp = recipe.data.getInt("ebf_temp");
        if (!recipe.data.contains("ebf_temp") || recipeTemp > blastFurnaceTemperature) {
            return ModifierFunction.NULL;
        }

        if (RecipeHelper.getRecipeEUtTier(recipe) > coilMachine.getTier()) {
            return ModifierFunction.NULL;
        }

        var discount = ModifierFunction.builder()
                .eutMultiplier(getCoilEUtDiscount(recipeTemp, blastFurnaceTemperature))
                .durationMultiplier(0.5)
                .build();

        OverclockingLogic logic = (p, v) -> OverclockingLogic.heatingCoilOC(p, v, recipeTemp, blastFurnaceTemperature);
        var oc = logic.getModifier(machine, recipe, coilMachine.getOverclockVoltage());

        return oc.compose(discount);
    }
}