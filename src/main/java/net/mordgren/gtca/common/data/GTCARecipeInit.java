package net.mordgren.gtca.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.recipes.CasingRecipes;
import net.mordgren.gtca.common.data.recipes.GTCAMachinesRecipes;
import net.mordgren.gtca.common.data.recipes.ChemGenChemicals;
import net.mordgren.gtca.common.data.recipes.fuel.AltFuels;
import net.mordgren.gtca.common.data.recipes.fuel.ChemGenFuels;
import net.mordgren.gtca.common.data.recipes.fuel.AltSteam;
import net.mordgren.gtca.common.data.recipes.GreenHouseRecipes;
import net.mordgren.gtca.common.data.recipes.HERecipes;
import net.mordgren.gtca.common.data.recipes.AlloyRecipes;
import net.mordgren.gtca.common.data.recipes.PolymerizerRecipes;
import net.mordgren.gtca.common.util.Misc;

import java.util.function.Consumer;

public class GTCARecipeInit {
        public static void init(Consumer<FinishedRecipe> provider) {
            AltSteam.init(provider);
            AltFuels.init(provider);
            ChemGenFuels.init(provider);
            ChemGenChemicals.init(provider);

            AlloyRecipes.init(provider);

            GreenHouseRecipes.init(provider);

            PolymerizerRecipes.init(provider);

            CasingRecipes.init(provider);
            GTCAMachinesRecipes.init(provider);

            HERecipes.init(provider);

            Misc.init();
            Misc.miscRecipes(provider);
        }
    }
