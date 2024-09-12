package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;

import java.util.function.Consumer;


public class ChemGenChemicals {
    public static void init(Consumer<FinishedRecipe> provider) {
        Oxylene(provider);
        Octene(provider);
        IsononylAlcohol(provider);
        PhthalicAnhydride(provider);
    }

    private static void IsononylAlcohol (Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("isononyl_alcohol").EUt(30).duration(180)
                .circuitMeta(3)
                .inputFluids(GTCAMaterials.Octene.getFluid(1000))
                .inputFluids(GTMaterials.CarbonMonoxide.getFluid(1000))
                .inputFluids(GTMaterials.Hydrogen.getFluid(1000))
                .outputFluids(GTCAMaterials.IsononylAlcohol.getFluid(1500))
                .save(provider);
    }

    private static void Octene (Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("octane_to_octene").EUt(30).duration(80)
                .circuitMeta(5)
                .inputFluids(GTMaterials.Octane.getFluid(100))
                .outputFluids(GTCAMaterials.Octene.getFluid(90))
                .save(provider);
    }

    private static void Oxylene (Consumer<FinishedRecipe> provider) {

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("oxylene_raw_oil").EUt(30).duration(20)
                .circuitMeta(5)
                .inputFluids(GTMaterials.RawOil.getFluid(25))
                .outputFluids(GTCAMaterials.Oxylene.getFluid(25))
                .save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("Oxylene_heavy_oil").EUt(30).duration(20)
                .circuitMeta(5)
                .inputFluids(GTMaterials.OilHeavy.getFluid(25))
                .outputFluids(GTCAMaterials.Oxylene.getFluid(40))
                .save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("oxylene_light_oil").EUt(30).duration(20)
                .circuitMeta(5)
                .inputFluids(GTMaterials.OilLight.getFluid(25))
                .outputFluids(GTCAMaterials.Oxylene.getFluid(50))
                .save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("oxylene_oil").EUt(30).duration(20)
                .circuitMeta(5)
                .inputFluids(GTMaterials.Oil.getFluid(25))
                .outputFluids(GTCAMaterials.Oxylene.getFluid(30))
                .save(provider);
    }

    private static void PhthalicAnhydride (Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("phthalic_anhydride_chem").EUt(30).duration(120)
                .circuitMeta(3)
                .inputFluids(GTCAMaterials.Oxylene.getFluid(1000))
                .inputFluids(GTMaterials.Oxygen.getFluid(3000))
                .outputFluids(GTCAMaterials.PhthalicAnhydride.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .save(provider);
    }
}
