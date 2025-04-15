package net.mordgren.gtca.common.data.recipes.permachine;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.data.GTCARecipeInit;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;
import net.mordgren.gtca.common.util.PCBRecipeCondition;

import java.util.ArrayList;
import java.util.function.Consumer;
import static java.lang.Integer.parseInt;

public class PCBFRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        plasticPcb(provider);
    }


    /// GTNH - GTCeum
    /// Plastic circuit board - plastic circuit board
    /// Advanced circuit board - epoxy pcb
    /// more advanced circuit board - fiber reinforced pcb
    /// elite circuit board - multi-layer fiber-reinforced pcb
    /// extreme wetware life support circuit board - wetware pcb

    private static ArrayList<Object[]> plasticPcbT1;
    private static ArrayList<Object[]> plasticPcbT2;
    private static ArrayList<Object[]> plasticPcbT3;

    private static void plasticPcbT1Init() {
        plasticPcbT1 = new ArrayList<>();
        /// sulfuric acid amount, iron3cl amount, eu/t, duration, pcb amount out, plate material, foil amount
        plasticPcbT1.add(new Object[]{500, 250, 22, 33.25, 8, GTMaterials.Polyethylene, 16});
        plasticPcbT1.add(new Object[]{707, 353, 90, 27.15, 12, GTMaterials.PolyvinylChloride, 22});
        plasticPcbT1.add(new Object[]{866, 433, 360, 22.15, 16, GTMaterials.Polytetrafluoroethylene, 27});
        plasticPcbT1.add(new Object[]{1000, 500, 1440, 18.1, 23, GTMaterials.Epoxy, 32});
        plasticPcbT1.add(new Object[]{1118, 559, 5760, 14.8, 32, GTMaterials.ReinforcedEpoxyResin, 35});
        plasticPcbT1.add(new Object[]{1224, 612, 23040, 12.05, 46, GTMaterials.Polybenzimidazole, 39});
        plasticPcbT1.add(new Object[]{1322, 661, 92160, 9.85, 64, GTMaterials.SiliconeRubber, 42});
        plasticPcbT1.add(new Object[]{1414, 707, 368640, 8.05, 91, GTMaterials.StyreneButadieneRubber, 45});
    }
    private static void plasticPcbT2Init() {
        plasticPcbT2 = new ArrayList<>();
        /// sulfuric acid amount, iron3cl amount, eu/t, duration, pcb amount out, plate material, foil amount
        plasticPcbT2.add(new Object[]{500, 250, 90, 27.7, 10, GTMaterials.Polyethylene, 16});
        plasticPcbT2.add(new Object[]{707, 353, 360, 22.6, 14, GTMaterials.PolyvinylChloride, 22});
        plasticPcbT2.add(new Object[]{866, 433, 1440, 18.45, 20, GTMaterials.Polytetrafluoroethylene, 27});
        plasticPcbT2.add(new Object[]{1000, 500, 5760, 15.1, 27, GTMaterials.Epoxy, 32});
        plasticPcbT2.add(new Object[]{1118, 559, 23040, 12.3, 39, GTMaterials.ReinforcedEpoxyResin, 35});
        plasticPcbT2.add(new Object[]{1224, 612, 92160, 10.05, 54, GTMaterials.Polybenzimidazole, 39});
        plasticPcbT2.add(new Object[]{1322, 661, 368640, 8.2, 77, GTMaterials.SiliconeRubber, 42});
        plasticPcbT2.add(new Object[]{1414, 707, 1474560, 6.7, 108, GTMaterials.StyreneButadieneRubber, 45});
    }
    private static void plasticPcbT3Init() {
        plasticPcbT3 = new ArrayList<>();
        /// sulfuric acid amount, iron3cl amount, eu/t, duration, pcb amount out, plate material, foil amount
        plasticPcbT3.add(new Object[]{500, 250, 90, 22.15, 12, GTMaterials.Polyethylene, 16});
        plasticPcbT3.add(new Object[]{707, 353, 360, 18.1, 16, GTMaterials.PolyvinylChloride, 22});
        plasticPcbT3.add(new Object[]{866, 433, 1440, 14.8, 23, GTMaterials.Polytetrafluoroethylene, 27});
        plasticPcbT3.add(new Object[]{1000, 500, 5760, 12.05, 32, GTMaterials.Epoxy, 32});
        plasticPcbT3.add(new Object[]{1118, 559, 23040, 9.85, 46, GTMaterials.ReinforcedEpoxyResin, 35});
        plasticPcbT3.add(new Object[]{1224, 612, 92160, 8.05, 64, GTMaterials.Polybenzimidazole, 39});
        plasticPcbT3.add(new Object[]{1322, 661, 368640, 6.6, 91, GTMaterials.SiliconeRubber, 42});
        plasticPcbT3.add(new Object[]{1414, 707, 1474560, 5.4, 128, GTMaterials.StyreneButadieneRubber, 45});
    }

    private static void plasticPcb(Consumer<FinishedRecipe> provider) {
        plasticPcbT1Init();
        plasticPcbT2Init();
        plasticPcbT3Init();
        int t1 = 0;
        /// Tier 1
        for (Object[] objectType : plasticPcbT1) {
            t1 += 1;
            int amountFluid1 = parseInt(objectType[0].toString());
            int amountFluid2 = parseInt(objectType[1].toString());
            int EUt = parseInt(objectType[2].toString());
            int duration = Math.round(Float.parseFloat(objectType[3].toString()) * 20);
            int amountPcb = parseInt(objectType[4].toString());
            int amountFoil = parseInt(objectType[6].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[5], 1));
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("plastic_mki_"+t1).EUt(EUt).duration(duration).circuitMeta(1)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKI))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.AnnealedCopper, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Copper, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid1))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid2))
                    .outputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
        }
        /// Tier 2
        int t2 = 0;
        for (Object[] objectType : plasticPcbT2) {
            t2 += 1;
            int amountFluid1 = parseInt(objectType[0].toString());
            int amountFluid2 = parseInt(objectType[1].toString());
            int EUt = parseInt(objectType[2].toString());
            int duration = Math.round(Float.parseFloat(objectType[3].toString()) * 20);
            int amountPcb = parseInt(objectType[4].toString());
            int amountFoil = parseInt(objectType[6].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[5], 1));
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("plastic_mkii_"+t2).EUt(EUt).duration(duration).circuitMeta(2)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKII))
                    .notConsumable(GTCAItems.SilverNanites.asStack(1))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.AnnealedCopper, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Copper, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid1))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid2))
                    .outputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
        }
        /// Tier 3
        int t3 = 0;
        for (Object[] objectType : plasticPcbT3) {
            t3 += 1;
            int amountFluid1 = parseInt(objectType[0].toString());
            int amountFluid2 = parseInt(objectType[1].toString());
            int EUt = parseInt(objectType[2].toString());
            int duration = Math.round(Float.parseFloat(objectType[3].toString()) * 20);
            int amountPcb = parseInt(objectType[4].toString());
            int amountFoil = parseInt(objectType[6].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[5], 1));
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("plastic_mkiii_"+t3).EUt(EUt).duration(duration).circuitMeta(2)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKIII))
                    .notConsumable(GTCAItems.GoldNanites.asStack(1))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.AnnealedCopper, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Copper, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid1))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid2))
                    .outputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
        }
    }
}
