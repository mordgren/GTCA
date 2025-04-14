package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;


public class GTCARecipeConditions {

    public static RecipeConditionType<PCBRecipeCondition> PCB_CONDITION = GTRegistries.RECIPE_CONDITIONS.register("pcb_factory",
            new RecipeConditionType<>(PCBRecipeCondition::new, PCBRecipeCondition.CODEC));

    public static void init() {}
}
