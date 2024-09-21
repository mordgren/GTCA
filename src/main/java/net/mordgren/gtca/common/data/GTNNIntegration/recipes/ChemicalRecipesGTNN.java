package net.mordgren.gtca.common.data.GTNNIntegration.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import dev.arbor.gtnn.api.recipe.PlantCasingCondition;
import dev.arbor.gtnn.data.GTNNRecipeTypes;
import dev.arbor.gtnn.data.GTNNRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class ChemicalRecipesGTNN {
    public static void init(Consumer<FinishedRecipe> provider){
        carbonDisulfide(provider);
        SPEX(provider);
    }

    private static void carbonDisulfide(Consumer<FinishedRecipe> provider){
        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("carbon_disulfide").duration(6000).EUt(30)
                .circuitMeta(20)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.ALUMINIUM))
                .inputItems(GTCAHelper.getItem("tinyDust", GTMaterials.Aluminium, 1))
                .inputItems(GTCAHelper.getItem("tinyDust", GTMaterials.Nickel, 1))
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Sulfur, 4))
                .inputFluids(GTMaterials.CoalGas.getFluid(1000))
                .outputFluids(GTCAMaterials.CarbonDisulfide.getFluid(2000))
                .save(provider);
    }

    private static void SPEX(Consumer<FinishedRecipe> provider) {
        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("sex").duration(1200).EUt(120)
                .circuitMeta(17)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.TITANIUM))
                .inputFluids(GTCAMaterials.CarbonDisulfide.getFluid(1000))
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.SodiumEthoxide, 9))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.SodiumEthylXanthate, 12))
                .save(provider);

        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("pex").duration(1200).EUt(120)
                .circuitMeta(17)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.TITANIUM))
                .inputFluids(GTCAMaterials.CarbonDisulfide.getFluid(1000))
                .inputFluids(GTMaterials.Ethanol.getFluid(1000))
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.PotassiumHydroxide, 3))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.PotassiumEthylXanthate, 12))
                .outputFluids(GTMaterials.Water.getFluid(1000))
                .save(provider);

        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("se").duration(400).EUt(120)
                .circuitMeta(16)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.ALUMINIUM))
                .inputFluids(GTMaterials.Ethanol.getFluid(1000))
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Sodium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.SodiumEthoxide, 9))
                .outputFluids(GTMaterials.Hydrogen.getFluid(1000))
                .save(provider);
    }
}
