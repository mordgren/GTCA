package net.mordgren.gtca.common.data.polymerizer.recipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.Salt;
import static com.gregtechceu.gtceu.common.data.GTMaterials.SodiumSulfide;

public class PolymerizerRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        small(provider);
    }
    private static void small(Consumer<FinishedRecipe> provider) {

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyvinylacetate").EUt(32).duration(160)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .inputFluids(GTMaterials.VinylAcetate.getFluid(144))
                .outputFluids(GTMaterials.PolyvinylAcetate.getFluid(252))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyvinylchloride").EUt(32).duration(160)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .inputFluids(GTMaterials.VinylChloride.getFluid(144))
                .outputFluids(GTMaterials.PolyvinylChloride.getFluid(252))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyethylene").EUt(32).duration(160)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .inputFluids(GTMaterials.Ethylene.getFluid(144))
                .outputFluids(GTMaterials.Polyethylene.getFluid(252))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("ptfe").EUt(32).duration(160)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .inputFluids(GTMaterials.Tetrafluoroethylene.getFluid(144))
                .outputFluids(GTMaterials.Polytetrafluoroethylene.getFluid(252))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyphenylenesulfide").EUt(360).duration(240)
                .circuitMeta(1)
                .inputItems(GTCAHelper.getItem("dust", SodiumSulfide, 1))
                .inputFluids(GTMaterials.Oxygen.getFluid(8000))
                .inputFluids(GTMaterials.Dichlorobenzene.getFluid(1000))
                .outputFluids(GTMaterials.PolyphenyleneSulfide.getFluid(2000))
                .outputItems(GTCAHelper.getItem("dust", Salt, 4))
                .save(provider);
    }
}
