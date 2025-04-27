package net.mordgren.gtca.integration.gtnn;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.integration.gtnn.fallbackrecipes.AltFuelsFallbackGTNN;
import net.mordgren.gtca.integration.gtnn.fallbackrecipes.BLRecipesFallBackGTNN;
import net.mordgren.gtca.integration.gtnn.fallbackrecipes.ChemicalRecipesFallbackGTNN;
import net.mordgren.gtca.integration.gtnn.fallbackrecipes.MiscRecipesFallbackGTNN;
import net.mordgren.gtca.integration.gtnn.recipes.AltFuelsGTNN;
import net.mordgren.gtca.integration.gtnn.recipes.BLRecipesGTNN;
import net.mordgren.gtca.integration.gtnn.recipes.ChemicalRecipesGTNN;
import net.mordgren.gtca.integration.gtnn.recipes.MiscRecipesGTNN;

import java.util.function.Consumer;

public class GTNNIntProxy {
    public static void init(Consumer<FinishedRecipe> provider){
        AltFuelsGTNN.init(provider);
        ChemicalRecipesGTNN.init(provider);
        MiscRecipesGTNN.init(provider);
        BLRecipesGTNN.gtnnint(provider);
    }

    public static void fallbackinit(Consumer<FinishedRecipe> provider){
        ChemicalRecipesFallbackGTNN.init(provider);
        AltFuelsFallbackGTNN.init(provider);
        MiscRecipesFallbackGTNN.init(provider);
        BLRecipesFallBackGTNN.gtnnintfallback(provider);
    }
}
