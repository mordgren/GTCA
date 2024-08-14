package net.mordgren.gtca.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.fuels.AltFuels;
import net.mordgren.gtca.common.data.fuels.ChemGenFuels;
import net.mordgren.gtca.common.data.fuels.HighPressureSteam;
import java.util.function.Consumer;

public class GTCARecipeInit {
        public static void init(Consumer<FinishedRecipe> provider) {
            HighPressureSteam.init(provider);
            AltFuels.init(provider);
            ChemGenFuels.init(provider);
            miscRecipes(provider);
        }

        private static void miscRecipes(Consumer<FinishedRecipe> provider) {
        }
    }
