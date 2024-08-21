package net.mordgren.gtca.common.data.metals.recipes;

import java.util.function.Consumer;
import com.gregtechceu.gtceu.api.GTValues;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MIXER_RECIPES;

public class AlloyRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        TM20MnAlloy(provider);
        CNFAlloy(provider);
        Dural(provider);
        Nimonic80A(provider);
        Moltech(provider);
    }

    private static void TM20MnAlloy(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("tm_20_mn_alloy").EUt(GTValues.VA[GTValues.IV]).duration(180)
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 4))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 1))
                .inputItems(GTCAHelper.getItem("dust", Manganese, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.TM20MnAlloy, 6))
                .save(provider);
    }

    private static void CNFAlloy(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("c_n_f_alloy").EUt(GTValues.VA[GTValues.LV]).duration(120)
                .inputItems(GTCAHelper.getItem("dust", Nickel, 5))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 2))
                .inputItems(GTCAHelper.getItem("dust", Iron, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.CNFAlloy, 8))
                .save(provider);


    }

    private static void Dural(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("dural").EUt(GTValues.VA[GTValues.MV]).duration(120)
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 9))
                .inputItems(GTCAHelper.getItem("dust", Copper, 2))
                .inputItems(GTCAHelper.getItem("dust", Magnesium, 1))
                .inputItems(GTCAHelper.getItem("dust", Manganese, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Dural, 13))
                .save(provider);


    }

    private static void Nimonic80A(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("dural").EUt(GTValues.VA[GTValues.EV]).duration(200)
                .inputItems(GTCAHelper.getItem("dust", Nickel, 8))
                .inputItems(GTCAHelper.getItem("dust", Chromium, 3))
                .inputItems(GTCAHelper.getItem("dust", Cobalt, 2))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 1))
                .inputItems(GTCAHelper.getItem("dust", Aluminium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Nimonic80A, 15))
                .save(provider);


    }

    private static void Moltech(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("dural").EUt(GTValues.VA[GTValues.EV]).duration(200)
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 8))
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 2))
                .inputItems(GTCAHelper.getItem("dust", Titanium, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.Moltech, 11))
                .save(provider);


    }
}
