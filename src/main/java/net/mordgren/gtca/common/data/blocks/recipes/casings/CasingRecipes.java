package net.mordgren.gtca.common.data.blocks.recipes.casings;

import com.gregtechceu.gtceu.api.GTValues;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.blocks.GTCACasings;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;

public class CasingRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        casing_aebf(provider);
        casing_greenhouse(provider);
        dural_casing(provider);
    }

    private static void casing_aebf(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("casing_aebf").EUt(GTValues.VA[GTValues.HV]).duration(80)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.TM20MnAlloy, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.TM20MnAlloy, 1))
                .outputItems(GTCACasings.CASING_AEBF)
                .save(provider);
    }
    private static void casing_greenhouse(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("casing_greenhouse").EUt(GTValues.VA[GTValues.LV]).duration(80)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.CNFAlloy, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.CNFAlloy, 1))
                .outputItems(GTCACasings.CASING_GREENHOUSE)
                .save(provider);
    }
    private static void dural_casing(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("dural_casing").EUt(GTValues.VA[GTValues.HV]).duration(100)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Dural, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Dural, 1))
                .outputItems(GTCACasings.DURAL_CASING)
                .save(provider);
    }
}
