package net.mordgren.gtca.data.recipe;

import static com.gregtechceu.gtceu.api.GTValues.*;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class BLRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        chemChain(provider);
        mainChain(provider);
        ceDioChain(provider);
        neodChain(provider);
    }

    private static void chemChain(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("hexa_fluorosilicic_acid").EUt(VA[MV]).duration(400)
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Silicon, 1))
                .inputFluids(GTMaterials.HydrofluoricAcid.getFluid(6000))
                .outputFluids(GTCAMaterials.HexafluorosilicicAcid.getFluid(1000))
                .outputFluids(GTMaterials.Hydrogen.getFluid(4000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("sodiumfluorosilicate").EUt(VA[HV]).duration(600)
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Salt, 4))
                .inputFluids(GTCAMaterials.HexafluorosilicicAcid.getFluid(1000))
                .outputFluids(GTCAMaterials.Sodiumfluorosilicate.getFluid(1000))
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(2000))
                .save(provider);

    }

    private static void mainChain(Consumer<FinishedRecipe> provider){

        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("crushed_to_muddy").EUt(VA[EV]).duration(400)
                .blastFurnaceTemp(800)
                .inputItems(GTCAHelper.getItem("crushedOre", GTMaterials.Bastnasite, 2))
                .inputFluids(GTMaterials.NitricAcid.getFluid(700))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.SiliconDioxide, 1))
                .outputFluids(GTCAMaterials.MuddyBastnasiteRareEarthSolution.getFluid(400))
                .save(provider);

        GTRecipeTypes.CRACKING_RECIPES.recipeBuilder("muddy_crack").EUt(VA[HV]).duration(24)
                .circuitMeta(1)
                .inputFluids(GTCAMaterials.MuddyBastnasiteRareEarthSolution.getFluid(40))
                .inputFluids(GTMaterials.Steam.getFluid(40))
                .outputFluids(GTCAMaterials.SteamCrackedBastnasiteMud.getFluid(80))
                .save(provider);

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("muddy_crack_to_conditioned").EUt(VA[MV]).duration(800)
                .circuitMeta(6)
                .inputFluids(GTCAMaterials.Sodiumfluorosilicate.getFluid(320))
                .inputFluids(GTCAMaterials.SteamCrackedBastnasiteMud.getFluid(1000))
                .outputFluids(GTCAMaterials.ConditionedBastnasiteMud.getFluid(1320))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("conditioned_to_diluted").EUt(VA[EV]).duration(1000)
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Saltpeter, 1))
                .inputFluids(GTMaterials.Water.getFluid(10000))
                .inputFluids(GTCAMaterials.ConditionedBastnasiteMud.getFluid(1000))
                .outputFluids(GTCAMaterials.DilutedBastnasiteMud.getFluid(11000))
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("diluted_to_filtered").EUt(240).duration(400)
                .inputFluids(GTCAMaterials.DilutedBastnasiteMud.getFluid(1000))
                .outputFluids(GTCAMaterials.FilteredBastnasiteMud.getFluid(400))
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.SiliconDioxide, 1), 9000, 0)
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.Rutile, 1), 7500, 0)
                .chancedOutput(GTCAHelper.getItem("dust", GTCAMaterials.RedZircon, 1), 1000, 0)
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.Ilmenite, 1), 500, 0)
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("filtered_to_oxides").EUt(600).duration(500).circuitMeta(1)
                .blastFurnaceTemp(1400)
                .inputFluids(GTCAMaterials.FilteredBastnasiteMud.getFluid(1000))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.BastnasiteRareEarthOxides, 1))
                .save(provider);

        GTRecipeTypes.CHEMICAL_BATH_RECIPES.recipeBuilder("to_acid_leached").EUt(30).duration(200)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.BastnasiteRareEarthOxides, 1))
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(500))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.AcidLeachedBastnasiteRareEarthOxides, 1))
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("acidleached_to_roasted").EUt(120).duration(600).circuitMeta(1)
                .blastFurnaceTemp(1200)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.AcidLeachedBastnasiteRareEarthOxides, 1))
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.RoastedRareEarthOxides, 1))
                .outputFluids(GTMaterials.Fluorine.getFluid(13))
                .save(provider);

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("roasted_to_wet").EUt(30).duration(100)
                .circuitMeta(7)
                .inputFluids(GTMaterials.Water.getFluid(200))
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.RoastedRareEarthOxides, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.WetRareEarthOxides, 1))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("wet_to_ceriumoxidised").EUt(480).duration(300)
                .inputFluids(GTMaterials.Fluorine.getFluid(4000))
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.WetRareEarthOxides, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumOxidisedRareEarthOxides, 1))
                .outputFluids(GTMaterials.HydrofluoricAcid.getFluid(4000))
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("ceriumoxidised_to_ceriumdioxide").EUt(480).duration(600)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumOxidisedRareEarthOxides, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.BastnasiteRarerEarthOxides, 1))
                .chancedOutput(GTCAHelper.getItem("dust", GTCAMaterials.CeriumDioxide, 1), 9000, 1)
                .save(provider);

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("rarer_to_nitrogenated").EUt(480).duration(300)
                .inputFluids(GTMaterials.NitricAcid.getFluid(400))
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.BastnasiteRarerEarthOxides, 1))
                .outputFluids(GTCAMaterials.NitrogenatedBastnasiteRarerEarthOxides.getFluid(1000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("nitrogenated_to_suspension").EUt(480).duration(700)
                .inputFluids(GTMaterials.Acetone.getFluid(1000))
                .inputFluids(GTCAMaterials.NitrogenatedBastnasiteRarerEarthOxides.getFluid(1000))
                .outputFluids(GTCAMaterials.BastnasiteRarerEarthOxideSuspension.getFluid(1000))
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("suspension_to_concentrate").EUt(480).duration(900)
                .inputFluids(GTCAMaterials.BastnasiteRarerEarthOxideSuspension.getFluid(1000))
                .outputFluids(GTMaterials.Acetone.getFluid(450))
                .chancedOutput(GTCAHelper.getItem("dust", GTCAMaterials.NeodymiumRareEarthConcentrate, 1), 8000, 1)
                .chancedOutput(GTCAHelper.getItem("dust", GTCAMaterials.SamaricRareEarthConcentrate, 1), 5000, 1)
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("samaric_to_flsamaric").EUt(480).duration(300)
                .circuitMeta(1)
                .inputFluids(GTMaterials.HydrofluoricAcid.getFluid(2000))
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.SamaricRareEarthConcentrate, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.FluorinatedSamaricConcentrate, 1))
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("flsamaric_to_ho").EUt(1920).duration(1220)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.FluorinatedSamaricConcentrate, 8))
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Calcium, 4))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Holmium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.SamariumTerbiumMixture, 4))
                .outputFluids(GTCAMaterials.CalciumFluoride.getFluid(12000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("smtb_to_nitrosmtb").EUt(480).duration(600)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.SamariumTerbiumMixture, 1))
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.AmmoniumNitrate, 9))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.NitrogenatedSamariumTerbiumMixture, 1))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("nitrosmtb_to_tnd_and_srd").EUt(1920).duration(3200)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.NitrogenatedSamariumTerbiumMixture, 4))
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Copper, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.TerbiumNitrate, 2))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.SamariumResidue, 2))
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("tnd_to_terbium").EUt(90).duration(32)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.TerbiumNitrate, 5))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Terbium, 1))
                .outputFluids(GTMaterials.Oxygen.getFluid(3000))
                .outputFluids(GTMaterials.Nitrogen.getFluid(1000))
                .save(provider);

        GTRecipeTypes.SIFTER_RECIPES.recipeBuilder("residue_to_smgd").EUt(1920).duration(133)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.SamariumResidue, 3))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Samarium, 2))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Gadolinium, 1))
                .save(provider);
    }

    private static void ceDioChain(Consumer<FinishedRecipe> provider) {

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("cedio_to_cecl").EUt(450).duration(300)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumDioxide, 3))
                .inputFluids(GTMaterials.Hydrogen.getFluid(1000))
                .inputFluids(GTMaterials.AmmoniumChloride.getFluid(3000))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumChloride, 4))
                .outputFluids(GTMaterials.Steam.getFluid(2000))
                .outputFluids(GTMaterials.Ammonia.getFluid(3000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("cecl_to_ceox").EUt(450).duration(300)
                .circuitMeta(1)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumChloride, 8))
                .inputFluids(GTCAMaterials.Oxalate.getFluid(3000))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumOxalate, 5))
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(6000))
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("ceox_to_ceiii").EUt(480).duration(200)
                .blastFurnaceTemp(800)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumOxalate, 5))
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Carbon, 3))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumIIIOxide, 5))
                .outputFluids(GTMaterials.CarbonMonoxide.getFluid(9000))
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("ceiii_to_cerium").EUt(60).duration(70)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.CeriumIIIOxide, 5))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Cerium, 2))
                .outputFluids(GTMaterials.Oxygen.getFluid(3000))
                .save(provider);

    }

    private static void neodChain(Consumer<FinishedRecipe> provider){

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("neod_to_cl").EUt(800).duration(900)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.NeodymiumRareEarthConcentrate, 2))
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(2000))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.LanthaniumChloride, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.NeodymiumOxide, 1))
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("lacl_to_la").EUt(60).duration(54)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.LanthaniumChloride, 4))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Lanthanum, 1))
                .outputFluids(GTMaterials.Chlorine.getFluid(3000))
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("neodox_to_neod").EUt(60).duration(72)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.NeodymiumOxide, 5))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Neodymium, 2))
                .outputFluids(GTMaterials.Oxygen.getFluid(3000))
                .save(provider);
    }

}
