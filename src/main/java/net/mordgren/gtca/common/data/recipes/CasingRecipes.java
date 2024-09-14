package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCACasings;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;

public class CasingRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        casing_aebf(provider);
        casing_greenhouse(provider);
        dural_casing(provider);
        PRW_casing(provider);
        SHD_casing(provider);
        SHD_gearbox(provider);
        Vitallium_casing(provider);
        Inconnel718_casing(provider);
  }

    private static void casing_aebf(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("casing_aebf").EUt(GTValues.VA[GTValues.HV]).duration(80)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.TM20MnAlloy, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.TM20MnAlloy, 1))
                .outputItems(GTCACasings.CASING_AEBF, 2)
                .save(provider);
    }
    private static void casing_greenhouse(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("casing_greenhouse").EUt(GTValues.VA[GTValues.LV]).duration(80)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.CNFAlloy, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.CNFAlloy, 1))
                .outputItems(GTCACasings.CASING_GREENHOUSE, 2)
                .save(provider);
    }
    private static void dural_casing(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("dural_casing").EUt(GTValues.VA[GTValues.HV]).duration(100)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Dural, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Dural, 1))
                .outputItems(GTCACasings.DURAL_CASING, 2)
                .save(provider);
    }

    private static void PRW_casing(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("prw_casing").EUt(GTValues.VA[GTValues.HV]).duration(240)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.MAR_CE_M200, 4))
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Incoloy903, 4))
                .inputItems(GTCAHelper.getItem("frame", GTMaterials.NiobiumTitanium, 1))
                .outputItems(GTCACasings.PRW_Casing, 1)
                .save(provider);
    }

    private static void SHD_casing(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("shd_casing").EUt(GTValues.VA[GTValues.HV]).duration(260)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.MAR_M200, 4))
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Moltech, 4))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Moltech, 1))
                .outputItems(GTCACasings.SHD_CASING, 1)
                .save(provider);
    }
    private static void SHD_gearbox(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("shd_gearbox").EUt(GTValues.VA[GTValues.HV]).duration(260)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.MAR_CE_M200, 4))
                .inputItems(GTCAHelper.getItem("gear", GTCAMaterials.Moltech, 2))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.MAR_CE_M200, 1))
                .outputItems(GTCACasings.SHD_Gearbox, 1)
                .save(provider);

    }
    private static void Vitallium_casing(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("shd_gearbox").EUt(GTValues.VA[GTValues.HV]).duration(200)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Vitallium, 4))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Vitallium, 1))
                .outputItems(GTCACasings.VITALIUM_CASING, 2)
                .save(provider);

    }
    private static void Inconnel718_casing(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("shd_gearbox").EUt(GTValues.VA[GTValues.HV]).duration(280)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Inconel718, 4))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Inconel718, 1))
                .outputItems(GTCACasings.INCONEL718_CASING, 2)
                .save(provider);

    }


}




















