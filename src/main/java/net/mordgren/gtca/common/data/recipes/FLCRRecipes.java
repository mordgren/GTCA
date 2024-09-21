package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.ArrayList;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class FLCRRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        frothRecipes(provider);
    }

    private static ArrayList<Object[]> MaterialsPEX;
    private static ArrayList<Object[]> MaterialsSEX;

    private static void setMaterials() {
        MaterialsPEX = new ArrayList<>();
        MaterialsSEX = new ArrayList<>();

        MaterialsSEX.add(new Object[]{"sphalerite", GTValues.VA[LuV], GTCAItems.MilledSphalerite, GTCAMaterials.SphaleriteFroth, 14000});
        MaterialsSEX.add(new Object[]{"chalcopyrite", GTValues.VA[IV], GTCAItems.MilledChalcopyrite, GTCAMaterials.ChalcopyriteFroth, 12000});
        MaterialsPEX.add(new Object[]{"nickel", GTValues.VA[IV], GTCAItems.MilledNickel, GTCAMaterials.NickelFroth, 25000});
        MaterialsPEX.add(new Object[]{"platinum", GTValues.VA[LuV], GTCAItems.MilledPlatinum, GTCAMaterials.PlatinumFroth, 35000});
        MaterialsSEX.add(new Object[]{"pentlandite", GTValues.VA[LuV], GTCAItems.MilledPentlandite, GTCAMaterials.PentlanditeFroth, 14000});
        MaterialsSEX.add(new Object[]{"redstone", GTValues.VA[IV], GTCAItems.MilledRedstone, GTCAMaterials.RedstoneFroth, 13000});
        MaterialsPEX.add(new Object[]{"spessartine", GTValues.VA[LuV], GTCAItems.MilledSpessartine, GTCAMaterials.SpessartineFroth, 35000});
        MaterialsPEX.add(new Object[]{"grossular", GTValues.VA[LuV], GTCAItems.MilledGrossular, GTCAMaterials.GrossularFroth, 28000});
        MaterialsSEX.add(new Object[]{"almandine", GTValues.VA[IV], GTCAItems.MilledAlmandine, GTCAMaterials.AlmandineFroth, 18000});
        MaterialsSEX.add(new Object[]{"pyrope", GTValues.VA[EV], GTCAItems.MilledPyrope, GTCAMaterials.PyropeFroth, 8000});
        MaterialsPEX.add(new Object[]{"monazite", GTValues.VA[LuV], GTCAItems.MilledMonazite, GTCAMaterials.MonaziteFroth, 30000});
    }

    private static void frothRecipes(Consumer<FinishedRecipe> provider) {
        setMaterials();
        for (Object[] holder : MaterialsPEX) {
            String id = (String) holder[0];
            int eut = (int) holder[1];
            ItemEntry<Item> item = (ItemEntry<Item>) holder[2];
            FluidStack frothOut = ((Material) holder[3]).getFluid(1000);
            FluidStack SpruceOil = (GTCAMaterials.SpruceOil.getFluid((int)holder[4]));

            GTCARecipeTypes.FLCR.recipeBuilder(id).duration(9600).EUt(eut)
                    .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.PotassiumEthylXanthate, 32))
                    .inputItems(item, 64)
                    .inputItems(item, 64)
                    .inputItems(item, 64)
                    .inputItems(item, 64)
                    .inputFluids(SpruceOil)
                    .outputFluids(frothOut)
                    .save(provider);
        }
        for (Object[] holder : MaterialsSEX) {
            String id = (String) holder[0];
            int eut = (int) holder[1];
            ItemEntry<Item> item = (ItemEntry<Item>) holder[2];
            FluidStack frothOut = ((Material) holder[3]).getFluid(1000);
            FluidStack SpruceOil = (GTCAMaterials.SpruceOil.getFluid((int)holder[4]));

            GTCARecipeTypes.FLCR.recipeBuilder(id).duration(9600).EUt(eut)
                    .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.SodiumEthylXanthate, 32))
                    .inputItems(item, 64)
                    .inputItems(item, 64)
                    .inputItems(item, 64)
                    .inputItems(item, 64)
                    .inputFluids(SpruceOil)
                    .outputFluids(frothOut)
                    .save(provider);
        }
    }
}
