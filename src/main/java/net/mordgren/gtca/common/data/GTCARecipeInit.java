package net.mordgren.gtca.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.blocks.recipes.casings.CasingRecipes;
import net.mordgren.gtca.common.data.fuel.recipes.AltFuels;
import net.mordgren.gtca.common.data.fuel.recipes.ChemGenFuels;
import net.mordgren.gtca.common.data.fuel.recipes.HighPressureSteam;
import net.mordgren.gtca.common.data.greenhouse.recipes.GreenHouseRecipes;
import net.mordgren.gtca.common.data.metals.recipes.AlloyRecipes;
import java.util.function.Consumer;

public class GTCARecipeInit {
        public static void init(Consumer<FinishedRecipe> provider) {
            HighPressureSteam.init(provider);
            AltFuels.init(provider);
            ChemGenFuels.init(provider);

            AlloyRecipes.init(provider);

            GreenHouseRecipes.init(provider);

            CasingRecipes.init(provider);
            miscRecipes(provider);
        }

        private static void miscRecipes(Consumer<FinishedRecipe> provider) {
        }
    }
