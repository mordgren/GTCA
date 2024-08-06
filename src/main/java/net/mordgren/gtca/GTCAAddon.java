package net.mordgren.gtca;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;

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
        public String addonModId() {
            return GTCA.MOD_ID;
        }

        @Override
        public void registerTagPrefixes() {
            //CustomTagPrefixes.init();
        }

//@Override
//        public void registerOreVeins() {
//            JCOres.init();
//        }

//        @Override
//        public void addRecipes(Consumer<FinishedRecipe> provider) {
//            JCRecipes.init(provider);
//        }

        // If you have custom ingredient types, uncomment this & change to match your capability.
        // KubeJS WILL REMOVE YOUR RECIPES IF THESE ARE NOT REGISTERED.
    /*
    public static final ContentJS<Double> PRESSURE_IN = new ContentJS<>(NumberComponent.ANY_DOUBLE, GregitasRecipeCapabilities.PRESSURE, false);
    public static final ContentJS<Double> PRESSURE_OUT = new ContentJS<>(NumberComponent.ANY_DOUBLE, GregitasRecipeCapabilities.PRESSURE, true);

    @Override
    public void registerRecipeKeys(KJSRecipeKeyEvent event) {
        event.registerKey(CustomRecipeCapabilities.PRESSURE, Pair.of(PRESSURE_IN, PRESSURE_OUT));
    }
    */


}
