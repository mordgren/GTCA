package net.mordgren.gtca.common.data.recipes;

import java.util.function.Consumer;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class AlloyRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        TM20MnAlloy(provider);
        CNFAlloy(provider);
        Dural(provider);
        Nimonic80A(provider);
        Moltech(provider);
        Vitallium(provider);
        Incoloy903(provider);
        Incoloy846(provider);
        HeavyMetalMixture(provider);
        MAR_M200(provider);
        MAR_Ce_M200(provider);

    }

    private static void TM20MnAlloy(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("tm_20_mn_alloy").EUt(GTValues.VA[GTValues.IV]).duration(380)
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 4))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 1))
                .inputItems(GTCAHelper.getItem("dust", Manganese, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.TM20MnAlloy, 6))
                .save(provider);
    }

    private static void CNFAlloy(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("c_n_f_alloy").EUt(GTValues.VA[GTValues.LV]).duration(240)
                .circuitMeta(2)
                .inputItems(GTCAHelper.getItem("dust", Nickel, 5))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 2))
                .inputItems(GTCAHelper.getItem("dust", Iron, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.CNFAlloy, 8))
                .save(provider);
    }

    private static void Dural(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("dural").EUt(GTValues.VA[GTValues.MV]).duration(210)
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 9))
                .inputItems(GTCAHelper.getItem("dust", Copper, 2))
                .inputItems(GTCAHelper.getItem("dust", Magnesium, 1))
                .inputItems(GTCAHelper.getItem("dust", Manganese, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Dural, 13))
                .save(provider);
    }

    private static void Nimonic80A(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("nimonic80a").EUt(GTValues.VA[GTValues.EV]).duration(380)
                .inputItems(GTCAHelper.getItem("dust", Nickel, 8))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 3))
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 2))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 1))
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Nimonic80A, 15))
                .save(provider);
    }

    private static void Moltech(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("moltech").EUt(GTValues.VA[GTValues.EV]).duration(210)
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 8))
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 2))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Moltech, 11))
                .save(provider);
    }

    private static void Vitallium(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("vitallium").EUt(GTValues.VA[GTValues.EV]).duration(200)
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 6))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 3))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Vitallium, 10))
                .save(provider);
    }
    private static void Incoloy846(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("incoloy846").EUt(GTValues.VA[GTValues.HV]).duration(200)
                .inputItems(GTCAHelper.getItem("dust",Iron ,12 ))
                .inputItems(GTCAHelper.getItem("dust",Nickel , 10 ))
                .inputItems(GTCAHelper.getItem("dust",Cobalt ,8 ))
                .inputItems(GTCAHelper.getItem("dust",Titanium ,4 ))
                .inputItems(GTCAHelper.getItem("dust",Molybdenum ,2 ))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Incoloy846, 36))
                .save(provider);
    }

    private static void Incoloy903(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("incoloy903").EUt(GTValues.VA[GTValues.EV]).duration(190)
                .inputItems(GTCAHelper.getItem("dust",GTCAMaterials.Incoloy846 ,36 ))
                .inputItems(GTCAHelper.getItem("dust",Aluminium ,1 ))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Incoloy903, 37))
                .save(provider);
    }

    private static void HeavyMetalMixture(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("heavy_metal_mixture").EUt(GTValues.VA[GTValues.EV]).duration(170)
                .inputItems(GTCAHelper.getItem("dust",Niobium , 2))
                .inputItems(GTCAHelper.getItem("dust",Chromium ,9 ))
                .inputItems(GTCAHelper.getItem("dust",Titanium ,2 ))
                .inputItems(GTCAHelper.getItem("dust",Aluminium , 5))
                .inputItems(GTCAHelper.getItem("dust",Cobalt ,10 ))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.HeavyMetalMixture, 28 ))
                .save(provider);

    }

    private static void MAR_M200(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("mar_m200").EUt(GTValues.VA[GTValues.EV]).duration(220)
                .inputItems(GTCAHelper.getItem("dust",GTCAMaterials.HeavyMetalMixture , 28))
                .inputItems(GTCAHelper.getItem("dust",Nickel ,18 ))
                .inputItems(GTCAHelper.getItem("dust",Tungsten , 13))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.MAR_M200, 59 ))
                .save(provider);

    }

    private static void MAR_Ce_M200(Consumer<FinishedRecipe> provider) {
        BLAST_RECIPES.recipeBuilder("mar_ce_m200").EUt(GTValues.VA[GTValues.ZPM]).duration(470)
                .circuitMeta(2)
                .blastFurnaceTemp(7100)
                .inputItems(GTCAHelper.getItem("ingot",GTCAMaterials.MAR_M200, 16 ))
                .inputFluids(Cerium.getFluid(144))
                .inputItems(GTCAHelper.getItem("dust", LithiumChloride ,1 ))
                .outputItems(GTCAHelper.getItem("hotIngot", GTCAMaterials.MAR_CE_M200, 17 ))
                .save(provider);

        BLAST_RECIPES.recipeBuilder("mar_ce_m200_dust").EUt(GTValues.VA[GTValues.ZPM]).duration(470)
                .circuitMeta(1)
                .blastFurnaceTemp(7100)
                .inputItems(GTCAHelper.getItem("dust",GTCAMaterials.MAR_CE_M200, 1 ))
                .outputItems(GTCAHelper.getItem("hotIngot", GTCAMaterials.MAR_CE_M200, 1 ))
                .save(provider);

        BLAST_RECIPES.recipeBuilder("mar_ce_m200_adv").EUt(GTValues.VA[GTValues.ZPM]).duration(470)
                .circuitMeta(2)
                .blastFurnaceTemp(7100)
                .inputItems(GTCAHelper.getItem("dust",GTCAMaterials.MAR_CE_M200, 1 ))
                .inputFluids(GTMaterials.Helium.getFluid(100))
                .outputItems(GTCAHelper.getItem("hotIngot", GTCAMaterials.MAR_CE_M200, 1 ))
                .save(provider);

        VACUUM_RECIPES.recipeBuilder("mar_ce_m200").EUt(GTValues.VA[GTValues.MV]).duration(220)
                .inputItems(GTCAHelper.getItem("hotIngot", GTCAMaterials.MAR_CE_M200, 1))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.MAR_CE_M200, 1))
                .save(provider);
    }



}
