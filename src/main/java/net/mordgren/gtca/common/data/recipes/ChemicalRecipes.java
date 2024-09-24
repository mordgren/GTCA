package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;

public class ChemicalRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        carbonDisulfide(provider);
        chemGenChemicals(provider);
        mappline(provider);
    }

    private static void carbonDisulfide(Consumer<FinishedRecipe> provider){
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("carbon_disulfide").duration(12000).EUt(30)
                .blastFurnaceTemp(1500)
                .inputItems(GTCAHelper.getItem("gem", GTMaterials.Coke, 8))
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Sulfur, 16))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.DarkAsh, 1))
                .outputFluids(GTCAMaterials.CarbonDisulfide.getFluid(4000))
                .save(provider);
    }

    private static void chemGenChemicals(Consumer<FinishedRecipe> provider){
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("isononyl_alcohol").EUt(30).duration(180)
                .circuitMeta(3)
                .inputFluids(GTCAMaterials.Octene.getFluid(1000))
                .inputFluids(GTMaterials.CarbonMonoxide.getFluid(1000))
                .inputFluids(GTMaterials.Hydrogen.getFluid(1000))
                .outputFluids(GTCAMaterials.IsononylAlcohol.getFluid(1500))
                .save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("octane_to_octene").EUt(30).duration(80)
                .circuitMeta(5)
                .inputFluids(GTMaterials.Octane.getFluid(100))
                .outputFluids(GTCAMaterials.Octene.getFluid(90))
                .save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("oxylene_raw_oil").EUt(30).duration(20)
                .circuitMeta(5)
                .inputFluids(GTMaterials.RawOil.getFluid(25))
                .outputFluids(GTCAMaterials.Oxylene.getFluid(25))
                .save(provider);

        GTRecipeTypes.DISTILLERY_RECIPES.recipeBuilder("oxylene_heavy_oil").EUt(30).duration(20)
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

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("phthalic_anhydride_chem").EUt(30).duration(120)
                .circuitMeta(3)
                .inputFluids(GTCAMaterials.Oxylene.getFluid(1000))
                .inputFluids(GTMaterials.Oxygen.getFluid(3000))
                .outputFluids(GTCAMaterials.PhthalicAnhydride.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .save(provider);
    }

    private static void mappline(Consumer<FinishedRecipe> provider) {

        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder("propyne").EUt(80).duration(160)
                .inputFluids(GTMaterials.Propane.getFluid(2500))
                .outputFluids(GTCAMaterials.Propyne.getFluid(2000))
                .outputItems(GTMaterials.Carbon, 2)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("propadiene").EUt(480).duration(220)
                .inputFluids(GTMaterials.Propane.getFluid(1000))
                .inputFluids(GTMaterials.Butene.getFluid(1000))
                .notConsumable(GTCAHelper.getItem("dust", GTMaterials.Nickel, 1))
                .outputFluids(GTCAMaterials.Propadiene.getFluid(800))
                .outputFluids(GTMaterials.Cyclohexane.getFluid(1200))
                .save(provider);

        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("mapp_lcr").EUt(480).duration(210)
                .circuitMeta(24)
                .inputFluids(GTCAMaterials.Propyne.getFluid(5000))
                .inputFluids(GTCAMaterials.Propadiene.getFluid(3000))
                .inputFluids(GTMaterials.Propane.getFluid(2000))
                .inputFluids(GTMaterials.Butane.getFluid(1000))
                .outputFluids(GTCAMaterials.MAPP.getFluid(12000))
                .save(provider);
    }
}
