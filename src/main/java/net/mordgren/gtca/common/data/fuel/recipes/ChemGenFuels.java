package net.mordgren.gtca.common.data.fuel.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

import java.util.ArrayList;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;

public class ChemGenFuels {
    public static void init(Consumer<FinishedRecipe> provider) {
        DiisononylPhthalate(provider);
        chemGenRecipes(provider);
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

    private static ArrayList<Object[]> Fuels;

    private static void fuelsArrayInit() {
        Fuels = new ArrayList<>();

        /// recipe name (костыль), fuel, amount, duration
        Fuels.add(new Object[]{"dsa", GTMaterials.DilutedSulfuricAcid, 20, 20});
    }

    private static void chemGenRecipes(Consumer<FinishedRecipe> provider) {
        fuelsArrayInit();
        for(Object[] fuelType : Fuels){
            int duration = parseInt(fuelType[3].toString());
            long amount = parseInt(fuelType[2].toString());
            FluidStack Fuel = ((Material)fuelType[1]).getFluid(amount);
            GTCARecipeTypes.CHEMICAL_GENERATOR.recipeBuilder(fuelType[0].toString())
                    .EUt(-32)
                    .duration(duration)
                    .inputFluids(Fuel)
                    .save(provider);

        }
    }
}
