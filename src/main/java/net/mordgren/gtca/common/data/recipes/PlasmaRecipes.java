package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;

import java.util.function.Consumer;

public class PlasmaRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        fusion(provider);
        plasmaGen(provider);
    }

    private static void fusion(Consumer<FinishedRecipe> provider){
        /// HYDROGEN PLASMA IN COMET RECIPES

        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("sulfur_plasma").duration(32).EUt(10240)
                .fusionStartEU(240_000_000)
                .inputFluids(GTMaterials.Aluminium.getFluid(16))
                .inputFluids(GTMaterials.Lithium.getFluid(16))
                .outputFluids(GTCAMaterials.SulfurPlasma.getFluid(144))
                .save(provider);

        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("calcium_plasma").duration(128).EUt(7680)
                .fusionStartEU(120_000_000)
                .inputFluids(GTMaterials.Magnesium.getFluid(128))
                .inputFluids(GTMaterials.Oxygen.getFluid(128))
                .outputFluids(GTCAMaterials.CalciumPlasma.getFluid(16))
                .save(provider);

        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("titanium_plasma").duration(160).EUt(49152)
                .fusionStartEU(100_000_000)
                .inputFluids(GTMaterials.Aluminium.getFluid(144))
                .inputFluids(GTMaterials.Fluorine.getFluid(144))
                .outputFluids(GTCAMaterials.TitaniumPlasma.getFluid(144))
                .save(provider);

        GTRecipeTypes.FUSION_RECIPES.recipeBuilder("zinc_plasma").duration(16).EUt(49152)
                .fusionStartEU(180_000_000)
                .inputFluids(GTMaterials.Copper.getFluid(72))
                .inputFluids(GTMaterials.Tritium.getFluid(250))
                .outputFluids(GTCAMaterials.ZincPlasma.getFluid(144))
                .save(provider);
    }

    private static void plasmaGen(Consumer<FinishedRecipe> provider){
        GTRecipeTypes.PLASMA_GENERATOR_FUELS.recipeBuilder("sulfur_plasma").duration(83).EUt(-2048)
                .inputFluids(GTCAMaterials.SulfurPlasma.getFluid(1))
                .save(provider);

        GTRecipeTypes.PLASMA_GENERATOR_FUELS.recipeBuilder("hydrogen_plasma").duration(10).EUt(-2048)
                .inputFluids(GTCAMaterials.HydrogenPlasma.getFluid(1))
                .save(provider);

        GTRecipeTypes.PLASMA_GENERATOR_FUELS.recipeBuilder("calcium_plasma").duration(92).EUt(-2048)
                .inputFluids(GTCAMaterials.CalciumPlasma.getFluid(1))
                .save(provider);

        GTRecipeTypes.PLASMA_GENERATOR_FUELS.recipeBuilder("titanium_plasma").duration(96).EUt(-2048)
                .inputFluids(GTCAMaterials.TitaniumPlasma.getFluid(1))
                .save(provider);

        GTRecipeTypes.PLASMA_GENERATOR_FUELS.recipeBuilder("zinc_plasma").duration(110).EUt(-2048)
                .inputFluids(GTCAMaterials.ZincPlasma.getFluid(1))
                .save(provider);
    }
}
