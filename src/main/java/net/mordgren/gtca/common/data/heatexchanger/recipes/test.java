package net.mordgren.gtca.common.data.heatexchanger.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

import java.util.function.Consumer;

public class test {
    public static void init(Consumer<FinishedRecipe> provider){
        GTCARecipeTypes.EXTREME_HEAT_EXCHANGER.recipeBuilder("test").duration(20)
                .inputFluids(GTMaterials.Water.getFluid(20000))
                .outputFluids(GTMaterials.Steam.getFluid(10000))
                .save(provider);

        GTCARecipeTypes.SHD_STEAM_TURBINE.recipeBuilder("test").duration(100).EUt(-32)
                .inputFluids(GTMaterials.Water.getFluid(10))
                .outputFluids(GTMaterials.DistilledWater.getFluid(10))
                .save(provider);
    }
}
