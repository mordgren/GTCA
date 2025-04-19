package net.mordgren.gtca.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.BastnasiteLine.BLProxy;
import net.mordgren.gtca.common.data.recipes.*;
import net.mordgren.gtca.common.data.GTNNIntegration.GTNNIntProxy;
import net.mordgren.gtca.common.data.recipes.fuel.AltFuels;
import net.mordgren.gtca.common.data.recipes.fuel.ChemGenFuels;
import net.mordgren.gtca.common.data.recipes.fuel.AltSteam;
import net.mordgren.gtca.common.data.recipes.permachine.*;
import net.mordgren.gtca.common.data.recipes.MiscRecipes;
import net.mordgren.gtca.common.util.PCBRecipeCondition;

import java.util.function.Consumer;

public class GTCARecipeInit {
        public static void init(Consumer<FinishedRecipe> provider) {
            AltSteam.init(provider);
            AltFuels.init(provider);
            ChemGenFuels.init(provider);
            ChemicalRecipes.init(provider);
            AlloyRecipes.init(provider);
            PlasmaRecipes.init(provider);
            SilamidLine.init(provider);

            BLProxy.init(provider);

            GreenHouseRecipes.init(provider);
            PolymerizerRecipes.init(provider);
            HERecipes.init(provider);
            CometRecipes.init(provider);
            ICORecipes.init(provider);
            FrothLineRecipes.init(provider);
            TMForgeRecipes.init(provider);
            PCBFRecipes.init(provider);

            CasingRecipes.init(provider);
            GTCAMachinesRecipes.init(provider);

            MiscRecipes.init(provider);

            if (GTCA.GTNNINT) {
                GTNNIntProxy.init(provider);
            } else {
                GTNNIntProxy.fallbackinit(provider);
                GTCA.LOGGER.info("GT-- not found, some recipes are not loaded, or replaced.");
            }
        }

        public static PCBRecipeCondition setTier(int tier){
            return new PCBRecipeCondition(tier);
        }
    }
