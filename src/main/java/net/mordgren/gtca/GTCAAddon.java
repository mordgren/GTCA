package net.mordgren.gtca;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCARecipeInit;

import java.util.function.Consumer;
@SuppressWarnings("unused")
@com.gregtechceu.gtceu.api.addon.GTAddon
public class GTCAAddon implements IGTAddon {
        @Override
        public GTRegistrate getRegistrate() {
                return GTCA.GTCA_REGISTRATE;
        }

        @Override
        public void initializeAddon() {

        }

        @Override
        public void addRecipes(Consumer<FinishedRecipe> provider) {
                GTCARecipeInit.init(provider);
        }

        @Override
        public String addonModId() {
                return GTCA.MOD_ID;
        }

        @Override
        public void registerTagPrefixes() {
                //CustomTagPrefixes.init();
        }
}
//@Override
//        public void registerOreVeins() {
//            JCOres.init();
//        }
