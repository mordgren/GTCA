package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class BastnasiteLine {
    public static void init(Consumer<FinishedRecipe> provider){
        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("crushed_ore_to_muddy").EUt(1920).duration(400)
                .blastFurnaceTemp(800)
                .inputItems(GTCAHelper.getItem("crushedOre", GTMaterials.Bastnasite, 2))
                .inputFluids(GTMaterials.NitricAcid.getFluid(700))
                .outputItems(GTMaterials.SiliconDioxide, 1)
                .outputFluids(GTCAMaterials.MuddyBastnasiteRareEarthSolution.getFluid(400))
                .save(provider);

    }
}
