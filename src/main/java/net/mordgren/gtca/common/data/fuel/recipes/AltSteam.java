package net.mordgren.gtca.common.data.fuel.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.DistilledWater;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Steam;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.STEAM_TURBINE_FUELS;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.STEAM_PRESSURIZER;

public class AltSteam {
    public static void init(Consumer<FinishedRecipe> provider) {
        highPressureSteam(provider);
    }

    private static void highPressureSteam (Consumer<FinishedRecipe> provider) {
        STEAM_PRESSURIZER.recipeBuilder("hp_steam").EUt(240).duration(2)
                .inputFluids(Steam.getFluid(6400))
                .outputFluids(GTCAMaterials.HighPressureSteam.getFluid(640))
                .save(provider);

        STEAM_TURBINE_FUELS.recipeBuilder("hp_steam").EUt(-32).duration(25)
                .inputFluids(GTCAMaterials.HighPressureSteam.getFluid(64))
                .outputFluids(DistilledWater.getFluid(2))
                .save(provider);
    }
}
