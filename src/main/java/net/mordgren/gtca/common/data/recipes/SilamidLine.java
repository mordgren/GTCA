package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class SilamidLine {

    public static void init(Consumer<FinishedRecipe> provider) {

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("silicon_tetrachloride").EUt(30).duration(1000)
                .circuitMeta(1)
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Silicon, 1))
                .inputFluids(GTMaterials.Chlorine.getFluid(4000))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.SiliconTetrachloride, 1))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("silicon_tetrachloride_to_silicon_nitride").EUt(1920).duration(370)
                .circuitMeta(1)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.SiliconTetrachloride, 1))
                .inputFluids(GTMaterials.Ammonia.getFluid(6000))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.SiliconNitride, 1))
                .outputFluids(GTMaterials.AmmoniumChloride.getFluid(3000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("silicon_nitride_to_dyhydromini_ahh").EUt(1920).duration(280)
                .circuitMeta(2)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.SiliconNitride, 1))
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .outputFluids(GTCAMaterials.Dihydrodiaminosilane.getFluid(1000))
                .outputFluids(GTMaterials.Ammonia.getFluid(2000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("dihydro_ahh_to_silamid").EUt(1920).duration(490)
                .circuitMeta(1)
                .inputFluids(GTCAMaterials.Dihydrodiaminosilane.getFluid(1000))
                .inputFluids(GTMaterials.Nitrogen.getFluid(100))
                .outputFluids(GTCAMaterials.Silamid.getFluid(1000))
                .save(provider);
    }
}
