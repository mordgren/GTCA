package net.mordgren.gtca.common.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.GTCASpaceMiningAsteroids;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.GTCASpaceMiningRecipeGen;
import net.mordgren.gtca.data.recipe.*;
import net.mordgren.gtca.data.recipe.BLRecipes;
import net.mordgren.gtca.integration.gtnn.GTNNIntProxy;
import net.mordgren.gtca.data.recipe.fuel.AltFuels;
import net.mordgren.gtca.data.recipe.fuel.ChemGenFuels;
import net.mordgren.gtca.data.recipe.fuel.AltSteam;
import net.mordgren.gtca.data.recipe.permachine.*;
import net.mordgren.gtca.data.recipe.MiscRecipes;
import net.mordgren.gtca.common.recipe.condition.PCBRecipeCondition;

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
            NanoForgeRecipes.init(provider);

            BLRecipes.init(provider);

            GreenHouseRecipes.init(provider);
            PolymerizerRecipes.init(provider);
            HERecipes.init(provider);
            CometRecipes.init(provider);
            ICORecipes.init(provider);
            FrothLineRecipes.init(provider);
            TMForgeRecipes.init(provider);
            PCBFRecipes.init(provider);
            GTCASpaceMiningAsteroids.init();

            CasingRecipes.init(provider);
            GTCAMachinesRecipes.init(provider);
            GTCASpaceMiningRecipeGen.generate(provider);

            MiscRecipes.init(provider);

            if (GTCA.GTNNINT) {
                GTNNIntProxy.init(provider);
            } else {
                GTNNIntProxy.fallbackinit(provider);
                GTCA.LOGGER.info("GT-- not found, some recipe are not loaded, or replaced.");
            }
        }

        public static PCBRecipeCondition setTier(int tier){
            return new PCBRecipeCondition(tier);
        }
    }
