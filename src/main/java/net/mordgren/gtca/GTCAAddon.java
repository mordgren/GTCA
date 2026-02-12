package net.mordgren.gtca;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAElements;
import net.mordgren.gtca.common.data.GTCAOres;
import net.mordgren.gtca.common.data.GTCARecipeInit;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfoRecipeCapability;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningKeyRecipeCapability;
import net.mordgren.gtca.common.registry.GTCARegistration;
import net.mordgren.gtca.common.data.GTCASounds;

import java.util.function.Consumer;
@SuppressWarnings("unused")
@com.gregtechceu.gtceu.api.addon.GTAddon
public class GTCAAddon implements IGTAddon {

        @Override
        public GTRegistrate getRegistrate() {
                return GTCARegistration.REGISTRATE;
        }

        @Override
        public void registerTagPrefixes() {
        }

        @Override
        public void initializeAddon() {
                GTCA.LOGGER.info("GT Community Additions addon has loaded!");
        }

        @Override
        public void registerElements() {
                GTCAElements.init();
        }

        @Override
        public String addonModId() {
                return GTCA.MOD_ID;
        }

        @Override
        public void addRecipes(Consumer<FinishedRecipe> provider) {
                GTCARecipeTypes.init();
                GTCARecipeInit.init(provider);
        }

        @Override
        public void registerRecipeCapabilities() {
                GTRegistries.RECIPE_CAPABILITIES.register("gtca:space_mining_info", SpaceMiningInfoRecipeCapability.CAP);
                GTRegistries.RECIPE_CAPABILITIES.register("gtca:space_mining_key", SpaceMiningKeyRecipeCapability.CAP);
        }

        @Override
        public void registerRecipeKeys(KJSRecipeKeyEvent event) {
        }

        @Override
        public void registerOreVeins() {
                GTCAOres.init();
        }

        @Override
        public void registerSounds() {
                GTCASounds.init();
        }
}
