package net.mordgren.gtca.common.data.GTNNIntegration;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTNNIntegration.fallbackrecipes.AltFuelsFallbackGTNN;
import net.mordgren.gtca.common.data.GTNNIntegration.fallbackrecipes.ChemicalRecipesFallbackGTNN;

import java.util.function.Consumer;

public class GTNNIntFallbackProxy {
    public static void init(Consumer<FinishedRecipe> provider){
        ChemicalRecipesFallbackGTNN.init(provider);
        AltFuelsFallbackGTNN.init(provider);
    }
}
