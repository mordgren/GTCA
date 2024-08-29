package net.mordgren.gtca.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.blocks.recipes.casings.CasingRecipes;
import net.mordgren.gtca.common.data.blocks.recipes.machines.GTCAMachinesRecipes;
import net.mordgren.gtca.common.data.chemicals.recipes.ChemGenChemicals;
import net.mordgren.gtca.common.data.fuel.recipes.AltFuels;
import net.mordgren.gtca.common.data.fuel.recipes.ChemGenFuels;
import net.mordgren.gtca.common.data.fuel.recipes.AltSteam;
import net.mordgren.gtca.common.data.greenhouse.recipes.GreenHouseRecipes;
import net.mordgren.gtca.common.data.heatexchanger.recipes.HERecipes;
import net.mordgren.gtca.common.data.metals.recipes.AlloyRecipes;
import net.mordgren.gtca.common.data.polymerizer.recipes.PolymerizerRecipes;
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
