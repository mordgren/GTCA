package net.mordgren.gtca.common.data.heatexchanger.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

import java.util.ArrayList;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;

public class HERecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        heRecipes(provider);
    }

    private static ArrayList<Object[]> inpHot;

    private static void inpHotInit() {
        inpHot = new ArrayList<Object[]>();

        /// Plasma material, plasma amount, coolant amount, sc steam output amount, recipe name(kostyl`)
        inpHot.add(new Object[]{GTMaterials.Argon, 800, 1228800, 1966080, "argon"});
        inpHot.add(new Object[]{GTMaterials.Helium, 800, 1228800, 1966080, "helium"});
        inpHot.add(new Object[]{GTMaterials.Iron, 1500, 5806068, 9289710, "iron"});
        inpHot.add(new Object[]{GTMaterials.Nickel, 1500, 6013434, 9621495, "nickel"});
        inpHot.add(new Object[]{GTMaterials.Nitrogen, 1000, 2419200, 3870720, "nitrogen"});
        inpHot.add(new Object[]{GTMaterials.Oxygen, 1000, 2457600, 3932160, "oxygen"});
    }

    private static void heRecipes(Consumer<FinishedRecipe> provider) {
        inpHotInit();
        for(Object[] plasmaType : inpHot){
            long amountPlasma = parseInt(plasmaType[1].toString());
            long amountDistill = parseInt(plasmaType[2].toString());
            long amountSteam = parseInt(plasmaType[3].toString());
            FluidStack PlasmaIn = ((Material)plasmaType[0]).getFluid(FluidStorageKeys.PLASMA, amountPlasma);
            FluidStack PlasmaOut = ((Material)plasmaType[0]).getFluid(amountPlasma);
            FluidStack DistilledWater = (GTMaterials.DistilledWater.getFluid(amountDistill));
            FluidStack SCSteam = (GTCAMaterials.SuperCriticalSteam.getFluid(amountSteam));
            GTCARecipeTypes.EXTREME_HEAT_EXCHANGER.recipeBuilder(plasmaType[4].toString())
                    .duration(20)
                    .inputFluids(DistilledWater)
                    .inputFluids(PlasmaIn)
                    .outputFluids(GTCAMaterials.SuperheatedSteam.getFluid(0))
                    .outputFluids(SCSteam)
                    .outputFluids(PlasmaOut)
                    .save(provider);

        }
    }
}
