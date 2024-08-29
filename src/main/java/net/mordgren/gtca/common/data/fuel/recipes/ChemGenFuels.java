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
        Fuels.add(new Object[]{"aa", GTMaterials.AceticAcid, 8, 30});
        Fuels.add(new Object[]{"dha", GTMaterials.DilutedHydrochloricAcid, 15, 25});
        Fuels.add(new Object[]{"sa", GTMaterials.SulfuricAcid, 10, 56});
        Fuels.add(new Object[]{"na", GTMaterials.NitricAcid, 10, 75});
        Fuels.add(new Object[]{"ha", GTMaterials.HydrochloricAcid, 8, 76});
        Fuels.add(new Object[]{"merc", GTMaterials.Mercury, 12, 95});
        Fuels.add(new Object[]{"pa", GTMaterials.PhosphoricAcid, 9, 52});
        Fuels.add(new Object[]{"hfa", GTMaterials.HydrofluoricAcid, 8, 112});
        Fuels.add(new Object[]{"pta", GTMaterials.PhthalicAcid, 6, 90});
        Fuels.add(new Object[]{"fa", GTMaterials.FormicAcid, 6, 60});
        Fuels.add(new Object[]{"dino", GTCAMaterials.DiisononylPhthalate, 4, 263});
        Fuels.add(new Object[]{"dino", GTCAMaterials.MAPP, 10, 320});
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
