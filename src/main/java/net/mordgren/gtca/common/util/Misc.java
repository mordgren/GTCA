package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

import java.util.function.Consumer;

public class Misc {
    public static void init(Consumer<FinishedRecipe> provider) {
        miscRecipes(provider);
    }

    public static void miscRecipes(Consumer<FinishedRecipe> provider) {
        GTCARecipeTypes.WOOD_SQUEEZER.recipeBuilder("spruce_oil_log").duration(900).EUt(120)
                .inputItems(Items.SPRUCE_LOG,16)
                .outputFluids(GTCAMaterials.SpruceOil.getFluid(1500))
                .chancedOutput((GTCAHelper.getItem("dust", GTMaterials.Wood, 16)), 2500,0)
                .save(provider);

        GTCARecipeTypes.WOOD_SQUEEZER.recipeBuilder("spruce_oil_sapling").duration(440).EUt(60)
                .inputItems(Items.SPRUCE_SAPLING,16)
                .outputFluids(GTCAMaterials.SpruceOil.getFluid(375))
                .save(provider);
    }
}
