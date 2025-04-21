package net.mordgren.gtca.common.data.recipes;

import java.util.function.Consumer;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GCYMRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static net.mordgren.gtca.common.data.GTCAMaterials.*;

public class AlloyRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        LowMidTier(provider);
        NewOne(provider);
    }

    private static void LowMidTier(Consumer<FinishedRecipe> provider) {

        MIXER_RECIPES.recipeBuilder("tm_20_mn_alloy").EUt(GTValues.VA[GTValues.IV]).duration(380)
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 4))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 1))
                .inputItems(GTCAHelper.getItem("dust", Manganese, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.TM20MnAlloy, 6))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("c_n_f_alloy").EUt(GTValues.VA[GTValues.LV]).duration(240)
                .circuitMeta(4)
                .inputItems(GTCAHelper.getItem("dust", Nickel, 5))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 2))
                .inputItems(GTCAHelper.getItem("dust", Iron, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.CNFAlloy, 8))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("dural").EUt(GTValues.VA[GTValues.MV]).duration(210)
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 9))
                .inputItems(GTCAHelper.getItem("dust", Copper, 2))
                .inputItems(GTCAHelper.getItem("dust", Magnesium, 1))
                .inputItems(GTCAHelper.getItem("dust", Manganese, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Dural, 13))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("nimonic80a").EUt(GTValues.VA[GTValues.EV]).duration(380)
                .inputItems(GTCAHelper.getItem("dust", Nickel, 8))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 3))
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 2))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 1))
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Nimonic80A, 15))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("moltech").EUt(GTValues.VA[GTValues.EV]).duration(210)
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 8))
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 2))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Moltech, 11))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("vitallium").EUt(GTValues.VA[GTValues.EV]).duration(200)
                .circuitMeta(20)
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 6))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 3))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Vitallium, 10))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("incoloy846").EUt(GTValues.VA[GTValues.HV]).duration(200)
                .inputItems(GTCAHelper.getItem("dust", Iron, 12))
                .inputItems(GTCAHelper.getItem("dust", Nickel, 10))
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 8))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 4))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 2))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Incoloy846, 36))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("incoloy903").EUt(GTValues.VA[GTValues.EV]).duration(190)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.Incoloy846, 36))
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Incoloy903, 37))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("heavy_metal_mixture").EUt(GTValues.VA[GTValues.EV]).duration(170)
                .inputItems(GTCAHelper.getItem("dust", Niobium, 2))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 9))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 2))
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 5))
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 10))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.HeavyMetalMixture, 28))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("mar_m200").EUt(GTValues.VA[GTValues.EV]).duration(220)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.HeavyMetalMixture, 28))
                .inputItems(GTCAHelper.getItem("dust", Nickel, 18))
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 13))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.MAR_M200, 59))
                .save(provider);


        BLAST_RECIPES.recipeBuilder("mar_ce_m200").EUt(GTValues.VA[GTValues.ZPM]).duration(470)
                .circuitMeta(2)
                .blastFurnaceTemp(7100)
                .inputItems(GTCAHelper.getItem("ingot", GTCAMaterials.MAR_M200, 16))
                .inputFluids(Cerium.getFluid(144))
                .inputItems(GTCAHelper.getItem("dust", LithiumChloride, 1))
                .outputItems(GTCAHelper.getItem("hotIngot", GTCAMaterials.MAR_CE_M200, 17))
                .save(provider);

        BLAST_RECIPES.recipeBuilder("mar_ce_m200_dust").EUt(GTValues.VA[GTValues.ZPM]).duration(470)
                .circuitMeta(1)
                .blastFurnaceTemp(7100)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.MAR_CE_M200, 1))
                .outputItems(GTCAHelper.getItem("hotIngot", GTCAMaterials.MAR_CE_M200, 1))
                .save(provider);

        BLAST_RECIPES.recipeBuilder("mar_ce_m200_adv").EUt(GTValues.VA[GTValues.ZPM]).duration(380)
                .circuitMeta(2)
                .blastFurnaceTemp(7100)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.MAR_CE_M200, 1))
                .inputFluids(GTMaterials.Helium.getFluid(100))
                .outputItems(GTCAHelper.getItem("hotIngot", GTCAMaterials.MAR_CE_M200, 1))
                .save(provider);

        VACUUM_RECIPES.recipeBuilder("mar_ce_m200").EUt(GTValues.VA[GTValues.MV]).duration(220)
                .inputItems(GTCAHelper.getItem("hotIngot", GTCAMaterials.MAR_CE_M200, 1))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.MAR_CE_M200, 1))
                .save(provider);


        GCYMRecipeTypes.ALLOY_BLAST_RECIPES.recipeBuilder("zirconium_carbide").EUt(GTValues.VA[GTValues.EV]).duration(200)
                .blastFurnaceTemp(4700)
                .inputItems(GTCAHelper.getItem("dust", Zirconium, 1))
                .inputItems(GTCAHelper.getItem("dust", Carbon, 1))
                .outputFluids(GTCAMaterials.ZirconiumCarbide.getFluid(288))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("tantalloy60").EUt(GTValues.VA[GTValues.HV]).duration(360)
                .circuitMeta(4)
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 2))
                .inputItems(GTCAHelper.getItem("dust", Tantalum, 23))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Tantalloy60, 25))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("tantalloy61").EUt(GTValues.VA[GTValues.HV]).duration(210)
                .circuitMeta(4)
                .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.Tantalloy60, 1))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 6))
                .inputItems(GTCAHelper.getItem("dust", Yttrium, 4))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Tantalloy61, 11))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("incoloy020").EUt(GTValues.VA[GTValues.HV]).duration(185)
                .circuitMeta(2)
                .inputItems(GTCAHelper.getItem("dust", Iron, 10))
                .inputItems(GTCAHelper.getItem("dust", Copper, 1))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 5))
                .inputItems(GTCAHelper.getItem("dust", Nickel, 9))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Incoloy020, 25))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("incoloyMA323").EUt(GTValues.VA[GTValues.EV]).duration(190)
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 3))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 5))
                .inputItems(GTCAHelper.getItem("dust", Iron, 16))
                .inputItems(GTCAHelper.getItem("dust", Yttrium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.IncoloyMA323, 25))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("incoloyds").EUt(GTValues.VA[GTValues.HV]).duration(210)
                .circuitMeta(3)
                .inputItems(GTCAHelper.getItem("dust", Iron, 23))
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 9))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 9))
                .inputItems(GTCAHelper.getItem("dust", Nickel, 9))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.IncoloyDS, 50))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("inconel690").EUt(GTValues.VA[GTValues.HV]).duration(220)
                .inputItems(GTCAHelper.getItem("dust", Chromium, 1))
                .inputItems(GTCAHelper.getItem("dust", Niobium, 2))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 2))
                .inputItems(GTCAHelper.getItem("dust", Nichrome, 3))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Inconel690, 8))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("eglin_steel_compound").EUt(GTValues.VA[GTValues.LV]).duration(170)
                .circuitMeta(5)
                .inputItems(GTCAHelper.getItem("dust", Iron, 4))
                .inputItems(GTCAHelper.getItem("dust", Kanthal, 1))
                .inputItems(GTCAHelper.getItem("dust", Invar, 5))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.EglinSteelCompound, 10))
                .save(provider);
    }

    private static void NewOne(Consumer<FinishedRecipe> provider) {

        MIXER_RECIPES.recipeBuilder("electrical_steel").EUt(GTValues.VA[GTValues.LV]).duration(160)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("dust", Iron, 1))
                .inputItems(GTCAHelper.getItem("dust", Silicon, 1))
                .inputItems(GTCAHelper.getItem("dust", Coal, 1))
                .outputItems(GTCAHelper.getItem("dust", ElectricalSteel, 3))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("dark_steel").EUt(GTValues.VA[GTValues.MV]).duration(200)
                .circuitMeta(5)
                .inputItems(GTCAHelper.getItem("dust", ElectricalSteel, 1))
                .inputItems(GTCAHelper.getItem("dust", Coal, 1))
                .inputItems(GTCAHelper.getItem("dust", Obsidian, 1))
                .outputItems(GTCAHelper.getItem("dust", DarkSteel, 3))
                .save(provider);

        MIXER_RECIPES.recipeBuilder("end_steel").EUt(GTValues.VA[GTValues.EV]).duration(190)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("dust", DarkSteel, 1))
                .inputItems(GTCAHelper.getItem("dust", Endstone, 1))
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 1))
                .outputItems(GTCAHelper.getItem("dust", EndSteel, 3))
                .save(provider);

        MIXER_RECIPES.recipeBuilder("melodic_alloy").EUt(GTValues.VA[GTValues.EV]).duration(290)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("dust", EndSteel, 1))
                .inputItems(GTCAHelper.getItem("dust", EnderEye, 1))
                .inputItems(GTCAHelper.getItem("dust", Ohriharukon, 1))
                .outputItems(GTCAHelper.getItem("dust", MelodicAlloy, 3))
                .save(provider);

        MIXER_RECIPES.recipeBuilder("stellar_alloy").EUt(GTValues.VA[GTValues.IV]).duration(300)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("dust", MelodicAlloy, 1))
                .inputItems(GTCAHelper.getItem("dust", NetherStar, 1))
                .inputItems(GTCAHelper.getItem("dust", Naquadah, 1))
                .outputItems(GTCAHelper.getItem("dust", StellarAlloy, 3))
                .save(provider);


        MIXER_RECIPES.recipeBuilder("enriched_holmium").EUt(GTValues.VA[GTValues.ZPM]).duration(350)
                .circuitMeta(4)
                .inputItems(GTCAHelper.getItem("dust", Holmium, 1))
                .inputItems(GTCAHelper.getItem("dust", NaquadahEnriched, 4))
                .outputItems(GTCAHelper.getItem("dust", EnrichedHolmium, 5))
                .save(provider);

        LASER_ENGRAVER_RECIPES.recipeBuilder("celestial_tungsten").EUt(GTValues.VA[GTValues.UEV]).duration(3600)
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 6))
                .notConsumable(GTCAItems.QuantumAnomaly.asStack())
                .outputItems(GTCAHelper.getItem("dust", CelestialTungsten, 1))
                .save(provider);

        MIXER_RECIPES.recipeBuilder("stellite_79").EUt(GTValues.VA[GTValues.EV]).duration(290)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 7))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 7))
                .inputItems(GTCAHelper.getItem("dust", Manganese, 4))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 2))
                .outputItems(GTCAHelper.getItem("dust", Stellite79, 20))
                .save(provider);

        MIXER_RECIPES.recipeBuilder("silicon_carbide").EUt(GTValues.VA[GTValues.LV]).duration(100)
                .circuitMeta(4)
                .inputItems(GTCAHelper.getItem("dust", Carbon, 1))
                .inputItems(GTCAHelper.getItem("dust", Silicon, 1))
                .outputItems(GTCAHelper.getItem("dust", SiliconCarbide, 2))
                .save(provider);

        MIXER_RECIPES.recipeBuilder("berwollium").EUt(GTValues.VA[GTValues.EV]).duration(210)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("dust", Beryllium, 4))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 2))
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 1))
                .outputItems(GTCAHelper.getItem("dust", Berwollium, 7))
                .save(provider);

        MIXER_RECIPES.recipeBuilder("adamantium_alloy").EUt(GTValues.VA[GTValues.LuV]).duration(490)
                .circuitMeta(1)
                .inputItems(GTCAHelper.getItem("dust", Adamantium, 5))
                .inputItems(GTCAHelper.getItem("dust", NaquadahEnriched, 2))
                .inputItems(GTCAHelper.getItem("dust", Lanthanum, 3))
                .outputItems(GTCAHelper.getItem("dust", AdamantiumAlloy, 10))
                .save(provider);


    }

}
