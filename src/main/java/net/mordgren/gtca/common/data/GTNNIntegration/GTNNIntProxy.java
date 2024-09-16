package net.mordgren.gtca.common.data.GTNNIntegration;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTNNIntegration.recipes.AltFuelsGTNN;

import java.util.function.Consumer;

public class GTNNIntProxy {
    public static void init(Consumer<FinishedRecipe> provider){
        AltFuelsGTNN.init(provider);
    }
}
