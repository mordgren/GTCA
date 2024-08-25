package net.mordgren.gtca.common.data.fuel.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import java.util.function.Consumer;

public class ChemGenFuels {
    public static void init(Consumer<FinishedRecipe> provider) {
        DiisononylPhthalate(provider);
    }

    private static void DiisononylPhthalate (Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("diisononyl_phthalate").EUt(60).duration(260)
                .circuitMeta(3)
                .inputFluids(GTCAMaterials.PhthalicAnhydride.getFluid(1000))
                .inputFluids(GTCAMaterials.IsononylAlcohol.getFluid(2000))
                .outputFluids(GTCAMaterials.DiisononylPhthalate.getFluid(3000))
                .outputFluids(GTMaterials.Hydrogen.getFluid(1000))
                .save(provider);
    }
}
