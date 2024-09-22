package net.mordgren.gtca.common.data.GTNNIntegration;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTNNIntegration.recipes.AltFuelsGTNN;
import net.mordgren.gtca.common.data.GTNNIntegration.recipes.ChemicalRecipesGTNN;
import net.mordgren.gtca.common.data.GTNNIntegration.recipes.MiscRecipesGTNN;

import java.util.function.Consumer;

public class GTNNIntProxy {
    public static void init(Consumer<FinishedRecipe> provider){
        AltFuelsGTNN.init(provider);
        ChemicalRecipesGTNN.init(provider);
        MiscRecipesGTNN.init(provider);
    }
}
