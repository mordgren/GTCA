package net.mordgren.gtca.integration.gtnn.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import dev.arbor.gtnn.common.recipe.PlantCasingCondition;
import dev.arbor.gtnn.data.GTNNMaterials;
import dev.arbor.gtnn.data.GTNNRecipeTypes;
import dev.arbor.gtnn.data.GTNNRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class BLRecipesGTNN {

    public static void gtnnint(Consumer<FinishedRecipe> provider){

        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("oxalate").EUt(240).duration(4050)
                .circuitMeta(9)
                .inputItems(GTCAHelper.getItem("dust", GTNNMaterials.VanadiumPentoxide, 1))
                .inputFluids(GTMaterials.Ethanol.getFluid(9000))
                .inputFluids(GTMaterials.Oxygen.getFluid(45000))
                .outputFluids(GTCAMaterials.Oxalate.getFluid(9000))
                .outputFluids(GTMaterials.Water.getFluid(18000))
                .save(provider);

        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("oxalate_sugar").EUt(120).duration(600)
                .circuitMeta(1)
                .notConsumable(GTCAHelper.getItem("dust", GTNNMaterials.VanadiumPentoxide, 1))
                .inputItems(Items.SUGAR, 24)
                .inputFluids(GTMaterials.NitricAcid.getFluid(6000))
                .outputFluids(GTCAMaterials.Oxalate.getFluid(3000))
                .outputFluids(GTMaterials.NitricOxide.getFluid(6000))
                .save(provider);

        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("hans").EUt(120).duration(1200)
                .circuitMeta(21)
                .addCondition(GTNNRecipes.setPlantCasing(PlantCasingCondition.STEEL))
                .inputFluids(GTMaterials.Ammonia.getFluid(4000))
                .inputFluids(GTMaterials.NitricAcid.getFluid(4000))
                .outputFluids(GTCAMaterials.HydratedAmmoniumNitrateSlurry.getFluid(5184))
                .save(provider);

        GTNNRecipeTypes.INSTANCE.getDEHYDRATOR_RECIPES().recipeBuilder("ans").EUt(480).duration(1800)
                .circuitMeta(8)
                .inputFluids(GTCAMaterials.HydratedAmmoniumNitrateSlurry.getFluid(1152))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.AmmoniumNitrate, 8))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .save(provider);
    }
}
