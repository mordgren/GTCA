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
        batch(provider);
        advanced(provider);
    }
    private static void small(Consumer<FinishedRecipe> provider) {

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyvinylacetate").EUt(30).duration(160)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .inputFluids(GTMaterials.VinylAcetate.getFluid(144))
                .outputFluids(GTMaterials.PolyvinylAcetate.getFluid(288))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyvinylchloride").EUt(30).duration(160)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .inputFluids(GTMaterials.VinylChloride.getFluid(144))
                .outputFluids(GTMaterials.PolyvinylChloride.getFluid(288))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyethylene").EUt(30).duration(160)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .inputFluids(GTMaterials.Ethylene.getFluid(144))
                .outputFluids(GTMaterials.Polyethylene.getFluid(288))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("ptfe").EUt(30).duration(160)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .inputFluids(GTMaterials.Tetrafluoroethylene.getFluid(144))
                .outputFluids(GTMaterials.Polytetrafluoroethylene.getFluid(288))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyphenylenesulfide").EUt(360).duration(240)
                .circuitMeta(1)
                .inputItems(GTCAHelper.getItem("dust", SodiumSulfide, 3))
                .inputFluids(GTMaterials.Oxygen.getFluid(8000))
                .inputFluids(GTMaterials.Dichlorobenzene.getFluid(1000))
                .outputFluids(GTMaterials.PolyphenyleneSulfide.getFluid(2000))
                .outputItems(GTCAHelper.getItem("dust", Salt, 4))
                .save(provider);
    }


    private static void batch(Consumer<FinishedRecipe> provider) {

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyvinylacetate_batch").EUt(30).duration(640)
                .circuitMeta(2)
                .inputFluids(GTMaterials.Oxygen.getFluid(10000))
                .inputFluids(GTMaterials.VinylAcetate.getFluid(1440))
                .outputFluids(GTMaterials.PolyvinylAcetate.getFluid(2520))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyvinylchloride_batch").EUt(30).duration(640)
                .circuitMeta(2)
                .inputFluids(GTMaterials.Oxygen.getFluid(10000))
                .inputFluids(GTMaterials.VinylChloride.getFluid(1440))
                .outputFluids(GTMaterials.PolyvinylChloride.getFluid(2520))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyethylene_batch").EUt(30).duration(640)
                .circuitMeta(2)
                .inputFluids(GTMaterials.Oxygen.getFluid(10000))
                .inputFluids(GTMaterials.Ethylene.getFluid(1440))
                .outputFluids(GTMaterials.Polyethylene.getFluid(2520))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("ptfe_batch").EUt(30).duration(640)
                .circuitMeta(2)
                .inputFluids(GTMaterials.Oxygen.getFluid(10000))
                .inputFluids(GTMaterials.Tetrafluoroethylene.getFluid(1440))
                .outputFluids(GTMaterials.Polytetrafluoroethylene.getFluid(2520))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyphenylenesulfide_batch").EUt(360).duration(960)
                .circuitMeta(2)
                .inputItems(GTCAHelper.getItem("dust", SodiumSulfide, 30))
                .inputFluids(GTMaterials.Oxygen.getFluid(80000))
                .inputFluids(GTMaterials.Dichlorobenzene.getFluid(10000))
                .outputFluids(GTMaterials.PolyphenyleneSulfide.getFluid(20000))
                .outputItems(GTCAHelper.getItem("dust", Salt, 40))
                .save(provider);
    }

    private static void advanced(Consumer<FinishedRecipe> provider) {

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyvinylacetate_advanced").EUt(30).duration(240)
                .circuitMeta(3)
                .inputFluids(GTMaterials.Oxygen.getFluid(7500))
                .inputFluids(GTMaterials.VinylAcetate.getFluid(2160))
                .inputFluids(GTMaterials.TitaniumTetrachloride.getFluid(100))
                .outputFluids(GTMaterials.PolyvinylAcetate.getFluid(5040))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyvinylchloride_advanced").EUt(30).duration(240)
                .circuitMeta(3)
                .inputFluids(GTMaterials.Oxygen.getFluid(7500))
                .inputFluids(GTMaterials.VinylChloride.getFluid(2160))
                .inputFluids(GTMaterials.TitaniumTetrachloride.getFluid(100))
                .outputFluids(GTMaterials.PolyvinylChloride.getFluid(5040))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("polyethylene_advanced").EUt(30).duration(240)
                .circuitMeta(3)
                .inputFluids(GTMaterials.Oxygen.getFluid(7500))
                .inputFluids(GTMaterials.Ethylene.getFluid(2160))
                .inputFluids(GTMaterials.TitaniumTetrachloride.getFluid(100))
                .outputFluids(GTMaterials.Polyethylene.getFluid(5040))
                .save(provider);

        GTCARecipeTypes.POLYMERIZER.recipeBuilder("ptfe_advanced").EUt(30).duration(260)
                .circuitMeta(3)
                .inputFluids(GTMaterials.Oxygen.getFluid(7500))
                .inputFluids(GTMaterials.Tetrafluoroethylene.getFluid(2160))
                .inputFluids(GTMaterials.TitaniumTetrachloride.getFluid(100))
                .outputFluids(GTMaterials.Polytetrafluoroethylene.getFluid(5040))
                .save(provider);





    }



}
