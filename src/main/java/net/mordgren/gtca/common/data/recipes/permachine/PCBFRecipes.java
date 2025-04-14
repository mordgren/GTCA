package net.mordgren.gtca.common.data.recipes.permachine;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeInit;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;
import net.mordgren.gtca.common.util.PCBRecipeCondition;

import java.util.function.Consumer;

public class PCBFRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        test(provider);
    }

    private static void test(Consumer<FinishedRecipe> provider){
        GTCARecipeTypes.PCB_FACTORY.recipeBuilder("test").EUt(10).duration(30)
                .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKI))
                .inputItems(GTCAHelper.getItem("ingot", GTCAMaterials.Adamantium, 1))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.MAR_CE_M200, 1))
                .save(provider);

        GTCARecipeTypes.PCB_FACTORY.recipeBuilder("test2").EUt(10).duration(30)
                .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKII))
                .inputItems(GTCAHelper.getItem("ingot", GTCAMaterials.Incoloy903, 1))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.HastelloyN, 1))
                .save(provider);

        GTCARecipeTypes.PCB_FACTORY.recipeBuilder("test3").EUt(10).duration(30)
                .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKIII))
                .inputItems(GTCAHelper.getItem("ingot", GTCAMaterials.CNFAlloy, 1))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.IncoloyDS, 1))
                .save(provider);

    }
}
