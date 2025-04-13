package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class PCBRecipeCondition extends RecipeCondition {
    @Override
    public RecipeConditionType<?> getType() {
        return null;
    }

    @Override
    public Component getTooltips() {
        return null;
    }

    @Override
    public boolean test(@NotNull GTRecipe recipe, @NotNull RecipeLogic recipeLogic) {
        return false;
    }

    @Override
    public RecipeCondition createTemplate() {
        return null;
    }
}
