package net.mordgren.gtca.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.recipes.*;
import net.mordgren.gtca.common.data.recipes.fuel.AltFuels;
import net.mordgren.gtca.common.data.recipes.fuel.ChemGenFuels;
import net.mordgren.gtca.common.data.recipes.fuel.AltSteam;
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

            ICORecipes.init(provider);

            Misc.init();
            Misc.miscRecipes(provider);
        }
    }
