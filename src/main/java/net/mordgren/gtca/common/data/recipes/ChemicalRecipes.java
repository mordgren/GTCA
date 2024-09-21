package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class ChemicalRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        carbonDisulfide(provider);
    }

    private static void carbonDisulfide(Consumer<FinishedRecipe> provider){
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("carbon_disulfide").duration(12000).EUt(30)
                .blastFurnaceTemp(1500)
                .inputItems(GTCAHelper.getItem("gem", GTMaterials.Coke, 8))
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Sulfur, 16))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.DarkAsh, 1))
                .outputFluids(GTCAMaterials.CarbonDisulfide.getFluid(4000))
                .save(provider);
    }
}
