package net.mordgren.gtca.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTNNIntegration.GTNNIntFallbackProxy;
import net.mordgren.gtca.common.data.recipes.*;
import net.mordgren.gtca.common.data.GTNNIntegration.GTNNIntProxy;
import net.mordgren.gtca.common.data.recipes.fuel.AltFuels;
import net.mordgren.gtca.common.data.recipes.fuel.ChemGenFuels;
import net.mordgren.gtca.common.data.recipes.fuel.AltSteam;
import net.mordgren.gtca.common.data.recipes.permachine.*;
import net.mordgren.gtca.common.util.Misc;

import java.util.function.Consumer;

public class GTCARecipeInit {
        public static void init(Consumer<FinishedRecipe> provider) {
            AltSteam.init(provider);
            AltFuels.init(provider);
            ChemGenFuels.init(provider);
//            ChemGenChemicals.init(provider);
            ChemicalRecipes.init(provider);
            AlloyRecipes.init(provider);
            PlasmaRecipes.init(provider);

            GreenHouseRecipes.init(provider);
            PolymerizerRecipes.init(provider);
            HERecipes.init(provider);
            CometRecipes.init(provider);
            ICORecipes.init(provider);
            FrothLineRecipes.init(provider);
//            FLCRRecipes.init(provider);

            CasingRecipes.init(provider);
            GTCAMachinesRecipes.init(provider);

            Misc.init(provider);

            if (GTCA.GTNNINT) {
                GTNNIntProxy.init(provider);
            } else {
                GTNNIntFallbackProxy.init(provider);
                GTCA.LOGGER.info("GT-- not found, some recipes are not loaded, or replaced.");
            }
        }
    }
