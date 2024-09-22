package net.mordgren.gtca.common.util;

import net.minecraft.data.recipes.FinishedRecipe;
import java.util.function.Consumer;

public class Misc {
    public static void init(Consumer<FinishedRecipe> provider) {
        miscRecipes(provider);
    }

    public static void miscRecipes(Consumer<FinishedRecipe> provider) {
    }
}
