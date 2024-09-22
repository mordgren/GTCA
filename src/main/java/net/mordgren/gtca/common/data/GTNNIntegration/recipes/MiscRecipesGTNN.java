package net.mordgren.gtca.common.data.GTNNIntegration.recipes;


import com.gregtechceu.gtceu.common.data.GTMaterials;
import dev.arbor.gtnn.api.recipe.PlantCasingCondition;
import dev.arbor.gtnn.data.GTNNRecipeTypes;
import dev.arbor.gtnn.data.GTNNRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class MiscRecipesGTNN {
    public static void init(Consumer<FinishedRecipe> provider){
        crushedSpruce(provider);
        spruceOil(provider);
    }

    private static void crushedSpruce(Consumer<FinishedRecipe> provider){
        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("crushed_leaves").duration(200).EUt(30)
                .circuitMeta(14)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.STAINLESS_STEEL))
                .inputItems(Items.SPRUCE_LEAVES, 1)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(2),5000,0)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(2),5000,0)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(2),2500,0)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(2),2500,0)
                .save(provider);

        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("crushed_sapling").duration(200).EUt(60)
                .circuitMeta(14)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.STAINLESS_STEEL))
                .inputItems(Items.SPRUCE_SAPLING, 1)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(4),7500,0)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(4),7500,0)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(4),2500,0)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(4),2500,0)
                .save(provider);

        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("crushed_log").duration(200).EUt(120)
                .circuitMeta(14)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.STAINLESS_STEEL))
                .inputItems(Items.SPRUCE_LOG, 1)
                .outputItems(GTCAItems.CrushedSpruce, 16)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(16),7500,0)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(16),5000,0)
                .chancedOutput(GTCAItems.CrushedSpruce.asStack(16),2500,0)
                .save(provider);
    }

    private static void spruceOil(Consumer<FinishedRecipe> provider){
        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("spruce_oil_shsteam").duration(900).EUt(120)
                .circuitMeta(18)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.TITANIUM))
                .inputItems(GTCAItems.CrushedSpruce, 64)
                .inputFluids(GTCAMaterials.SuperheatedSteam.getFluid(5000))
                .chancedOutput(GTCAHelper.getItem("tinyDust", GTMaterials.Ash,5),3000,0)
                .chancedOutput(GTCAHelper.getItem("tinyDust", GTMaterials.Ash,5),3000,0)
                .chancedOutput(GTCAHelper.getItem("tinyDust", GTMaterials.DarkAsh,5),3000,0)
                .chancedOutput(GTCAHelper.getItem("tinyDust", GTMaterials.DarkAsh,5),3000,0)
                .outputFluids(GTCAMaterials.SpruceOil.getFluid(1500))
                .save(provider);

        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("spruce_oil_steam").duration(1200).EUt(120)
                .circuitMeta(16)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.TITANIUM))
                .inputItems(GTCAItems.CrushedSpruce, 64)
                .inputFluids(GTMaterials.Steam.getFluid(5000))
                .chancedOutput(GTCAHelper.getItem("tinyDust", GTMaterials.Ash,5),2000,0)
                .chancedOutput(GTCAHelper.getItem("tinyDust", GTMaterials.Ash,5),2000,0)
                .chancedOutput(GTCAHelper.getItem("tinyDust", GTMaterials.DarkAsh,5),2000,0)
                .chancedOutput(GTCAHelper.getItem("tinyDust", GTMaterials.DarkAsh,5),2000,0)
                .outputFluids(GTCAMaterials.SpruceOil.getFluid(500))
                .save(provider);
    }

}
