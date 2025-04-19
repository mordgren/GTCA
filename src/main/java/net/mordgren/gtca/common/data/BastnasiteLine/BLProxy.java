package net.mordgren.gtca.common.data.BastnasiteLine;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.util.ConfigHandler;

import java.util.function.Consumer;

public class BLProxy {
    public static void init(){
        if (ConfigHandler.INSTANCE.enableBastnasiteLine) {
            BLMaterials.initMaterials();
        }
    }

    public static void init(Consumer<FinishedRecipe> provider) {
        if (ConfigHandler.INSTANCE.enableBastnasiteLine) {
            BLRecipes.init(provider);
        }
    }
}
