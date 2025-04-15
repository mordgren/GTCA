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
        advancedPcb(provider);
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
//        plasticPcbT1.add(new Object[]{1322, 661, 92160, 9.85, 64, GTMaterials.SiliconeRubber, 42}); NEED KEVLAR
//        plasticPcbT1.add(new Object[]{1414, 707, 368640, 8.05, 91, GTMaterials.StyreneButadieneRubber, 45}); NEED RADON POLYMER
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
//        plasticPcbT2.add(new Object[]{1322, 661, 368640, 8.2, 77, GTMaterials.SiliconeRubber, 42}); NEED KEVLAR
//        plasticPcbT2.add(new Object[]{1414, 707, 1474560, 6.7, 108, GTMaterials.StyreneButadieneRubber, 45}); NEED RADON POLYMER
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
//        plasticPcbT3.add(new Object[]{1322, 661, 368640, 6.6, 91, GTMaterials.SiliconeRubber, 42}); NEED KEVLAR
//        plasticPcbT3.add(new Object[]{1414, 707, 1474560, 5.4, 128, GTMaterials.StyreneButadieneRubber, 45}); NEED RADON POLYMER
    }

    private static void plasticPcb(Consumer<FinishedRecipe> provider) {
        plasticPcbT1Init();
        plasticPcbT2Init();
        plasticPcbT3Init();
        int t1 = 0;
        /// Tier 1
        for (Object[] objectType : plasticPcbT1) {
            int amountFluid1 = parseInt(objectType[0].toString());
            int amountFluid2 = parseInt(objectType[1].toString());
            int EUt = parseInt(objectType[2].toString());
            int duration = Math.round(Float.parseFloat(objectType[3].toString()) * 20);
            int amountPcb = parseInt(objectType[4].toString());
            int amountFoil = parseInt(objectType[6].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[5], 1));
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("t1a_plastic"+t1).EUt(EUt).duration(duration).circuitMeta(1)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKI))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.AnnealedCopper, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Copper, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid1))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid2))
                    .outputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
            t1++;
        }
        /// Tier 2
        int t2 = 0;
        for (Object[] objectType : plasticPcbT2) {
            int amountFluid1 = parseInt(objectType[0].toString());
            int amountFluid2 = parseInt(objectType[1].toString());
            int EUt = parseInt(objectType[2].toString());
            int duration = Math.round(Float.parseFloat(objectType[3].toString()) * 20);
            int amountPcb = parseInt(objectType[4].toString());
            int amountFoil = parseInt(objectType[6].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[5], 1));
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("t2a_plastic"+t2).EUt(EUt).duration(duration).circuitMeta(2)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKII))
                    .notConsumable(GTCAItems.SilverNanites.asStack(1))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.AnnealedCopper, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Copper, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid1))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid2))
                    .outputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
            t2++;
        }
        /// Tier 3
        int t3 = 0;
        for (Object[] objectType : plasticPcbT3) {
            int amountFluid1 = parseInt(objectType[0].toString());
            int amountFluid2 = parseInt(objectType[1].toString());
            int EUt = parseInt(objectType[2].toString());
            int duration = Math.round(Float.parseFloat(objectType[3].toString()) * 20);
            int amountPcb = parseInt(objectType[4].toString());
            int amountFoil = parseInt(objectType[6].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[5], 1));
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("t3a_plastic"+t3).EUt(EUt).duration(duration).circuitMeta(2)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKIII))
                    .notConsumable(GTCAItems.GoldNanites.asStack(1))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.AnnealedCopper, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Copper, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid1))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid2))
                    .outputItems(GTItems.PLASTIC_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
            t3++;
        }
    }

    private static ArrayList<Object[]> advancedPcbT1;
    private static ArrayList<Object[]> advancedPcbT2;
    private static ArrayList<Object[]> advancedPcbT3;

    private static void advancedPcbT1Init() {
        advancedPcbT1 = new ArrayList<>();
        /// fluid amount, eu/t, duration, pcb amount out, plate material, foil amount
        advancedPcbT1.add(new Object[]{500, 90, 33.25, 8, GTMaterials.PolyvinylChloride, 16});
        advancedPcbT1.add(new Object[]{707, 360, 27.15, 12, GTMaterials.Polytetrafluoroethylene, 22});
        advancedPcbT1.add(new Object[]{866, 1440, 22.15, 16, GTMaterials.Epoxy, 27});
        advancedPcbT1.add(new Object[]{1000, 5760, 18.1, 23, GTMaterials.ReinforcedEpoxyResin, 32});
        advancedPcbT1.add(new Object[]{1118, 23040, 14.8, 32, GTMaterials.Polybenzimidazole, 35});
//        advancedPcbT1.add(new Object[]{1224, 92160, 12.05, 46, GTMaterials.SiliconeRubber, 39}); NEED KEVLAR
//        advancedPcbT1.add(new Object[]{1322, 368640, 9.85, 64, GTMaterials.StyreneButadieneRubber, 42}); NEED RADON POLYMER
    }

    private static void advancedPcbT2Init() {
        advancedPcbT2 = new ArrayList<>();
        /// fluid amount, eu/t, duration, pcb amount out, plate material, foil amount
        advancedPcbT2.add(new Object[]{500, 360, 27.7, 10, GTMaterials.PolyvinylChloride, 16});
        advancedPcbT2.add(new Object[]{707, 1440, 22.6, 14, GTMaterials.Polytetrafluoroethylene, 22});
        advancedPcbT2.add(new Object[]{866, 5760, 18.45, 20, GTMaterials.Epoxy, 27});
        advancedPcbT2.add(new Object[]{1000, 23040, 15.1, 27, GTMaterials.ReinforcedEpoxyResin, 32});
        advancedPcbT2.add(new Object[]{1118, 92160, 12.3, 39, GTMaterials.Polybenzimidazole, 35});
//        advancedPcbT2.add(new Object[]{1224, 368640, 10.05, 54, GTMaterials.SiliconeRubber, 39}); NEED KEVLAR
//        advancedPcbT2.add(new Object[]{1322, 1474560, 8.2, 77, GTMaterials.StyreneButadieneRubber, 42}); NEED RADON POLYMER
    }

    private static void advancedPcbT3Init() {
        advancedPcbT3 = new ArrayList<>();
        /// fluid amount, eu/t, duration, pcb amount out, plate material, foil amount
        advancedPcbT3.add(new Object[]{500, 360, 22.15, 12, GTMaterials.PolyvinylChloride, 16});
        advancedPcbT3.add(new Object[]{707, 1440, 18.1, 16, GTMaterials.Polytetrafluoroethylene, 22});
        advancedPcbT3.add(new Object[]{866, 5760, 14.8, 23, GTMaterials.Epoxy, 27});
        advancedPcbT3.add(new Object[]{1000, 23040, 12.05, 32, GTMaterials.ReinforcedEpoxyResin, 32});
        advancedPcbT3.add(new Object[]{1118, 92160, 9.85, 46, GTMaterials.Polybenzimidazole, 35});
//        advancedPcbT3.add(new Object[]{1224, 368640, 8.05, 64, GTMaterials.SiliconeRubber, 39}); NEED KEVLAR
//        advancedPcbT3.add(new Object[]{1322, 1474560, 6.6, 91, GTMaterials.StyreneButadieneRubber, 42}); NEED RADON POLYMER
    }

    private static void advancedPcb(Consumer<FinishedRecipe> provider) {
        advancedPcbT1Init();
        advancedPcbT2Init();
        advancedPcbT3Init();
        int t1 = 0;
        /// Tier 1
        for (Object[] objectType : advancedPcbT1) {
            int amountFluid = parseInt(objectType[0].toString());
            int EUt = parseInt(objectType[1].toString());
            int duration = Math.round(Float.parseFloat(objectType[2].toString()) * 20);
            int amountPcb = parseInt(objectType[3].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[4], 1));
            int amountFoil = parseInt(objectType[5].toString());
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("t1b_advanced"+t1).EUt(EUt).duration(duration).circuitMeta(1)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKI))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Gold, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Electrum, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid))
                    .outputItems(GTItems.ADVANCED_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
            t1++;
        }
        /// Tier 2
        int t2 = 0;
        for (Object[] objectType : advancedPcbT2) {
            int amountFluid = parseInt(objectType[0].toString());
            int EUt = parseInt(objectType[1].toString());
            int duration = Math.round(Float.parseFloat(objectType[2].toString()) * 20);
            int amountPcb = parseInt(objectType[3].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[4], 1));
            int amountFoil = parseInt(objectType[5].toString());
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("t2b_advanced"+t2).EUt(EUt).duration(duration).circuitMeta(2)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKII))
                    .notConsumable(GTCAItems.SilverNanites.asStack(1))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Gold, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Electrum, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid))
                    .outputItems(GTItems.ADVANCED_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
            t2++;
        }
        /// Tier 3
        int t3 = 0;
        for (Object[] objectType : advancedPcbT3) {
            int amountFluid = parseInt(objectType[0].toString());
            int EUt = parseInt(objectType[1].toString());
            int duration = Math.round(Float.parseFloat(objectType[2].toString()) * 20);
            int amountPcb = parseInt(objectType[3].toString());
            ItemStack plate = (GTCAHelper.getItem("plate", (Material) objectType[4], 1));
            int amountFoil = parseInt(objectType[5].toString());
            GTCARecipeTypes.PCB_FACTORY.recipeBuilder("t3b_advanced"+t3).EUt(EUt).duration(duration).circuitMeta(3)
                    .addCondition(GTCARecipeInit.setTier(PCBRecipeCondition.MKIII))
                    .notConsumable(GTCAItems.GoldNanites.asStack(1))
                    .inputItems(plate)
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Gold, amountFoil))
                    .inputItems(GTCAHelper.getItem("foil",GTMaterials.Electrum, amountFoil))
                    .inputFluids(GTMaterials.SulfuricAcid.getFluid(amountFluid))
                    .inputFluids(GTMaterials.Iron3Chloride.getFluid(amountFluid))
                    .outputItems(GTItems.ADVANCED_CIRCUIT_BOARD.asStack(amountPcb))
                    .save(provider);
            t3++;
        }
    }
}
