package net.mordgren.gtca.common.data.recipes.permachine;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

import java.util.function.Consumer;

public class PCBFRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        test(provider);
    }

    private static void test(Consumer<FinishedRecipe> provider){
        GTCARecipeTypes.PCB_FACTORY.recipeBuilder("test").EUt(0).duration(30)
    }
}
