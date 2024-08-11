package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import org.jetbrains.annotations.NotNull;

public class AEBFMod {
    public static GTRecipe aebfOverclock(MetaMachine machine, @NotNull GTRecipe recipe) {
        if (machine instanceof CoilWorkableElectricMultiblockMachine coilMachine) {
            int blastFurnaceTemperature = coilMachine.getCoilType().getCoilTemperature() + 100 * Math.max(0, coilMachine.getTier() - 2);
            if (recipe.data.contains("ebf_temp") && recipe.data.getInt("ebf_temp") <= blastFurnaceTemperature) {
                var duration1 = (int) (recipe.duration - (recipe.duration * 0.01 * 50));

                return RecipeHelper.getRecipeEUtTier(recipe) > coilMachine.getTier() ? null : RecipeHelper.applyOverclock(new OverclockingLogic((recipe1, recipeEUt, maxVoltage, duration, amountOC) -> OverclockingLogic.heatingCoilOverclockingLogic(Math.abs(recipeEUt), maxVoltage, duration1, amountOC, blastFurnaceTemperature, recipe.data.contains("ebf_temp") ? recipe.data.getInt("ebf_temp") : 0)), recipe, coilMachine.getOverclockVoltage());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}