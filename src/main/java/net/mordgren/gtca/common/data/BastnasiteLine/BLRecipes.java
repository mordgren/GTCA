package net.mordgren.gtca.common.data.BastnasiteLine;

import static com.gregtechceu.gtceu.api.GTValues.*;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class BLRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        chemChain(provider);
        mainChain(provider);
    }

    private static void chemChain(Consumer<FinishedRecipe> provider){
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(GTCA.id("hexa_fluorosilicic_acid")).EUt(VA[MV]).duration(400)
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Silicon, 1))
                .inputFluids(GTMaterials.HydrofluoricAcid.getFluid(6000))
                .outputFluids(BLMaterials.HexafluorosilicicAcid.getFluid(1000))
                .outputFluids(GTMaterials.Hydrogen.getFluid(4000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(GTCA.id("sodiumfluorosilicate")).EUt(VA[HV]).duration(600)
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Salt, 4))
                .inputFluids(BLMaterials.HexafluorosilicicAcid.getFluid( 1000))
                .outputFluids(BLMaterials.Sodiumfluorosilicate.getFluid(1000))
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(2000))
                .save(provider);
    }

    private static void mainChain(Consumer<FinishedRecipe> provider){

        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder(GTCA.id("crushed_to_muddy")).EUt(VA[EV]).duration(400)
                .blastFurnaceTemp(800)
                .inputItems(GTCAHelper.getItem("crushedOre", GTMaterials.Bastnasite, 2))
                .inputFluids(GTMaterials.NitricAcid.getFluid(700))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.SiliconDioxide, 1))
                .outputFluids(BLMaterials.MuddyBastnasiteRareEarthSolution.getFluid(400))
                .save(provider);

        GTRecipeTypes.CRACKING_RECIPES.recipeBuilder(GTCA.id("muddy_crack")).EUt(VA[HV]).duration(24)
                .circuitMeta(1)
                .inputFluids(BLMaterials.MuddyBastnasiteRareEarthSolution.getFluid(40))
                .inputFluids(GTMaterials.Steam.getFluid(40))
                .outputFluids(BLMaterials.SteamCrackedBastnasiteMud.getFluid(80))
                .save(provider);

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(GTCA.id("muddy_crack_to_conditioned")).EUt(VA[MV]).duration(800)
                .circuitMeta(6)
                .inputFluids(BLMaterials.Sodiumfluorosilicate.getFluid(320))
                .inputFluids(BLMaterials.SteamCrackedBastnasiteMud.getFluid(1000))
                .outputFluids(BLMaterials.ConditionedBastnasiteMud.getFluid(1320))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(GTCA.id("conditioned_to_diluted")).EUt(VA[EV]).duration(1000)
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Saltpeter, 1))
                .inputFluids(GTMaterials.Water.getFluid(10000))
                .inputFluids(BLMaterials.ConditionedBastnasiteMud.getFluid(1000))
                .outputFluids(BLMaterials.DilutedBastnasiteMud.getFluid(11000))
                .save(provider);
    }
}
