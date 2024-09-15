package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.logic.OCParams;
import com.gregtechceu.gtceu.api.recipe.logic.OCResult;
import org.jetbrains.annotations.NotNull;

public class AEBFMod {
    public static GTRecipe aebfOverclock(MetaMachine machine, @NotNull GTRecipe recipe, @NotNull OCParams params,
                                        @NotNull OCResult result) {
        if (machine instanceof CoilWorkableElectricMultiblockMachine coilMachine) {
            final var blastFurnaceTemperature = coilMachine.getCoilType().getCoilTemperature() +
                    100 * Math.max(0, coilMachine.getTier() - GTValues.MV);
            if (!recipe.data.contains("ebf_temp") || recipe.data.getInt("ebf_temp") > blastFurnaceTemperature) {
                return null;
            }
            if (RecipeHelper.getRecipeEUtTier(recipe) > coilMachine.getTier()) {
                return null;
            }
            var re = RecipeHelper.applyOverclock(
                    new OverclockingLogic((p, r, maxVoltage) -> OverclockingLogic.heatingCoilOC(
                            params, result, maxVoltage,
                            blastFurnaceTemperature,
                            recipe.data.contains("ebf_temp") ? recipe.data.getInt("ebf_temp") : 0)),
                    recipe, coilMachine.getOverclockVoltage(), params, result);
            result.setDuration((int) result.getDuration() / 2);
            return re;
        }
        return null;
    }
}