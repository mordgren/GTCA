package net.mordgren.gtca.common.data.GTNNIntegration.fallbackrecipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.LARGE_CHEMICAL_RECIPES;

public class AltFuelsFallbackGTNN {
    public static void init(Consumer<FinishedRecipe> provider){
        e85Fuel(provider);
    }

    private static void e85Fuel(Consumer<FinishedRecipe> provider) {
        LARGE_CHEMICAL_RECIPES.recipeBuilder("e85fuel").EUt(480).duration(100)
                .inputFluids(GTMaterials.Ethanol.getFluid(17000))
                .inputFluids(GTMaterials.LightFuel.getFluid(3000))
                .inputFluids(GTMaterials.EthylTertButylEther.getFluid(100))
                .outputFluids(GTCAMaterials.E85Fuel.getFluid(20000))
                .save(provider);
    }
}
