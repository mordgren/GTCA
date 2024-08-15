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
    }

    private static void TM20MnAlloy(Consumer<FinishedRecipe> provider) {
        MIXER_RECIPES.recipeBuilder("tm_20_mn_alloy").EUt(GTValues.VA[GTValues.IV]).duration(120)
                .inputItems(GTCAHelper.getItem("dust", Tungsten, 4))
                .inputItems(GTCAHelper.getItem("dust", Molybdenum, 1))
                .inputItems(GTCAHelper.getItem("dust", Manganese, 1))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.TM20MnAlloy, 6))
                .save(provider);
    }
}
