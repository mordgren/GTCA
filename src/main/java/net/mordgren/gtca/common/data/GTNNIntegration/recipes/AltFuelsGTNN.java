package net.mordgren.gtca.common.data.GTNNIntegration.recipes;

import dev.arbor.gtnn.api.recipe.PlantCasingCondition;
import dev.arbor.gtnn.data.GTNNRecipeTypes;
import dev.arbor.gtnn.data.GTNNRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class AltFuelsGTNN {
    public static void init(Consumer<FinishedRecipe> provider) {
        GTNNRecipeTypes.INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("e85fuel_cp").EUt(480).duration(60)
                .circuitMeta(20)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingCondition.STAINLESS_STEEL))
                .inputFluids(Ethanol.getFluid(17050))
                .inputFluids(LightFuel.getFluid(3000))
                .inputFluids(EthylTertButylEther.getFluid(50))
                .outputFluids(GTCAMaterials.E85Fuel.getFluid(20000))
                .save(provider);

        GTNNRecipeTypes.INSTANCE.getDEHYDRATOR_RECIPES().recipeBuilder("dymethyl_ether").EUt(1920).duration(360)
                .inputFluids(Methanol.getFluid(4000))
                .chancedInput((GTCAHelper.getItem("dust", GTCAMaterials.AluminosilicateCatalyst, 1)), 7400, 0)
                .chancedOutput((GTCAHelper.getItem("dust", Zeolite, 1)),2000,0)
                .outputFluids(GTCAMaterials.DymethylEther.getFluid(12000))
                .save(provider);
    }
}
