package net.mordgren.gtca.integration.gtnn.fallbackrecipes;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

public class BLRecipesFallBackGTNN {

    public static void gtnnintfallback(Consumer<FinishedRecipe> provider){

        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("oxalate").EUt(240).duration(4050)
                .circuitMeta(9)
                .inputItems(GTCAHelper.getItem("dust", GTMaterials.Vanadium, 1))
                .inputFluids(GTMaterials.Ethanol.getFluid(9000))
                .inputFluids(GTMaterials.Oxygen.getFluid(50000))
                .outputFluids(GTCAMaterials.Oxalate.getFluid(9000))
                .outputFluids(GTMaterials.Water.getFluid(20000))
                .save(provider);

        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder("oxalate_sugar").EUt(120).duration(600)
                .circuitMeta(1)
                .notConsumable(GTCAHelper.getItem("dust", GTMaterials.Vanadium, 1))
                .inputItems(Items.SUGAR, 24)
                .inputFluids(GTMaterials.NitricAcid.getFluid(6000))
                .inputFluids(GTMaterials.Oxygen.getFluid(5000))
                .outputFluids(GTCAMaterials.Oxalate.getFluid(3000))
                .outputFluids(GTMaterials.NitricOxide.getFluid(6000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("hans").EUt(120).duration(1200)
                .circuitMeta(21)
                .inputFluids(GTMaterials.Ammonia.getFluid(4000))
                .inputFluids(GTMaterials.NitricAcid.getFluid(4000))
                .outputFluids(GTCAMaterials.HydratedAmmoniumNitrateSlurry.getFluid(5184))
                .save(provider);

        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("ans").EUt(480).duration(1800)
                .circuitMeta(8)
                .blastFurnaceTemp(800)
                .inputFluids(GTCAMaterials.HydratedAmmoniumNitrateSlurry.getFluid(1152))
                .outputItems(GTCAHelper.getItem("dust", GTCAMaterials.AmmoniumNitrate, 8))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .save(provider);
    }
}
