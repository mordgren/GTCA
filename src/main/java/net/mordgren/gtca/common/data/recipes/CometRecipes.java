package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.fluids.GTFluid;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.COMET_CYCLOTRON;


public class CometRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        test(provider);

    }


    private static void test(Consumer<FinishedRecipe> provider) {
            COMET_CYCLOTRON.recipeBuilder("test").EUt(30).duration(300)
                    .inputFluids(Water.getFluid(1000))
                    .outputItems(GTBlocks.DARK_CONCRETE, 10)
                    .save(provider);



    }



}
