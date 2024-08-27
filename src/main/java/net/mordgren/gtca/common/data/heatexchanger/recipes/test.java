package net.mordgren.gtca.common.data.heatexchanger.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

import java.util.function.Consumer;

public class test {
    public static void init(Consumer<FinishedRecipe> provider){
        GTCARecipeTypes.HEAT_EXCHANGER.recipeBuilder("test").duration(20)
                .inputFluids(GTMaterials.Water.getFluid(20000))
                .outputFluids(GTMaterials.Steam.getFluid(10000))
                .save(provider);
    }
}
