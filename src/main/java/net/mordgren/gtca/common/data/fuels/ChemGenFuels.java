package net.mordgren.gtca.common.data.fuels;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import java.util.function.Consumer;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.CHEMICAL_GENERATOR;

public class ChemGenFuels {
    public static void init(Consumer<FinishedRecipe> provider) {
        test(provider);
    }

    private static void test (Consumer<FinishedRecipe> provider) {
        CHEMICAL_GENERATOR.recipeBuilder("test").EUt(-32).duration(90)
                .inputFluids(GTMaterials.Water.getFluid(10))
                .save(provider);
    }
}
