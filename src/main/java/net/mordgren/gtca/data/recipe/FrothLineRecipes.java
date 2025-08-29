package net.mordgren.gtca.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraftforge.fluids.FluidStack;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.ArrayList;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class FrothLineRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        isamillRecipes(provider);
        grindingBalls(provider);
        frothRecipes(provider);
        frothProcessing(provider);
    }

    private static ArrayList<Object[]> Materials;

    private static void setMaterials(){
        Materials = new ArrayList<>();

        Materials.add(new Object[]{"sphalerite", GTValues.VA[LuV], GTCAItems.MilledSphalerite});
        Materials.add(new Object[]{"chalcopyrite", GTValues.VA[IV], GTCAItems.MilledChalcopyrite});
        Materials.add(new Object[]{"nickel", GTValues.VA[IV], GTCAItems.MilledNickel});
        Materials.add(new Object[]{"platinum", GTValues.VA[LuV], GTCAItems.MilledPlatinum});
        Materials.add(new Object[]{"pentlandite", GTValues.VA[LuV], GTCAItems.MilledPentlandite});
        Materials.add(new Object[]{"redstone", GTValues.VA[IV], GTCAItems.MilledRedstone});
        Materials.add(new Object[]{"spessartine", GTValues.VA[LuV], GTCAItems.MilledSpessartine});
        Materials.add(new Object[]{"grossular", GTValues.VA[LuV], GTCAItems.MilledGrossular});
        Materials.add(new Object[]{"almandine", GTValues.VA[LuV], GTCAItems.MilledAlmandine});
        Materials.add(new Object[]{"pyrope", GTValues.VA[EV], GTCAItems.MilledPyrope});
        Materials.add(new Object[]{"monazite", GTValues.VA[ZPM], GTCAItems.MilledMonazite});
    }

    private static void isamillRecipes(Consumer<FinishedRecipe> provider) {
        setMaterials();
        for(Object[] holder : Materials) {
            int eut = (int)holder[1];
            String name = (String) holder[0];
            ItemEntry<Item> item = (ItemEntry<Item>) holder[2];

            GTCARecipeTypes.ISAMILL.recipeBuilder(name + "_block_nqball").duration(2400).EUt(eut)
                    .circuitMeta(10)
                    .inputItems(GTCAHelper.getByTag("forge", "ores/" + name), 16)
                    .notConsumable(GTCAItems.NqGrindBall)
                    .outputItems(item, 64)
                    .save(provider);

            GTCARecipeTypes.ISAMILL.recipeBuilder(name + "_crushed_nqball").duration(1200).EUt(eut)
                    .circuitMeta(10)
                    .inputItems(GTCAHelper.getByTag("forge", "crushed_ores/" + name),16)
                    .notConsumable(GTCAItems.NqGrindBall)
                    .outputItems(item, 32)
                    .save(provider);

            GTCARecipeTypes.ISAMILL.recipeBuilder(name + "_block_tcball").duration(3000).EUt(eut)
                    .circuitMeta(11)
                    .inputItems(GTCAHelper.getByTag("forge", "ores/" + name), 16)
                    .notConsumable(GTCAItems.TungstenCarbideGrindBall)
                    .outputItems(item, 48)
                    .save(provider);

            GTCARecipeTypes.ISAMILL.recipeBuilder(name + "_crushed_tcball").duration(1500).EUt(eut)
                    .circuitMeta(11)
                    .inputItems(GTCAHelper.getByTag("forge", "crushed_ores/" + name),16)
                    .notConsumable(GTCAItems.TungstenCarbideGrindBall)
                    .outputItems(item, 16)
                    .save(provider);
        }
    }

    private static void grindingBalls(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.COMPRESSOR_RECIPES.recipeBuilder("nq_ball_raw").duration(600).EUt(VA[IV])
                .inputItems(GTCAHelper.getItem("block", GTMaterials.NaquadahEnriched,16))
                .outputItems(GTCAItems.NqGrindBallRaw, 1)
                .save(provider);

        GTRecipeTypes.COMPRESSOR_RECIPES.recipeBuilder("tc_ball_raw").duration(600).EUt(VA[EV])
                .inputItems(GTCAHelper.getItem("block", GTMaterials.TungstenCarbide,16))
                .outputItems(GTCAItems.TungstenCarbideGrindballRaw, 1)
                .save(provider);

        GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder("nq_ball").duration(20).EUt(30)
                .inputItems(GTCAItems.NqGrindBallRaw,1)
                .inputItems(GTBlocks.INDUSTRIAL_TNT.asItem(), 64)
                .inputItems(GTBlocks.INDUSTRIAL_TNT.asItem(), 64)
                .outputItems(GTCAItems.NqGrindBall)
                .save(provider);

        GTRecipeTypes.IMPLOSION_RECIPES.recipeBuilder("tc_ball").duration(20).EUt(30)
                .inputItems(GTCAItems.TungstenCarbideGrindballRaw,1)
                .inputItems(GTBlocks.INDUSTRIAL_TNT.asItem(), 64)
                .inputItems(GTBlocks.INDUSTRIAL_TNT.asItem(), 64)
                .outputItems(GTCAItems.TungstenCarbideGrindBall)
                .save(provider);

    }


    private static ArrayList<Object[]> MaterialsPEX;
    private static ArrayList<Object[]> MaterialsSEX;

    private static void setSPEX() {
        MaterialsPEX = new ArrayList<>();
        MaterialsSEX = new ArrayList<>();

        MaterialsSEX.add(new Object[]{"sphalerite", GTValues.VA[LuV], GTCAItems.MilledSphalerite, GTCAMaterials.SphaleriteFroth, 4000});
        MaterialsSEX.add(new Object[]{"chalcopyrite", GTValues.VA[IV], GTCAItems.MilledChalcopyrite, GTCAMaterials.ChalcopyriteFroth, 4000});
        MaterialsPEX.add(new Object[]{"nickel", GTValues.VA[IV], GTCAItems.MilledNickel, GTCAMaterials.NickelFroth, 4000});
        MaterialsPEX.add(new Object[]{"platinum", GTValues.VA[LuV], GTCAItems.MilledPlatinum, GTCAMaterials.PlatinumFroth, 4000});
        MaterialsSEX.add(new Object[]{"pentlandite", GTValues.VA[LuV], GTCAItems.MilledPentlandite, GTCAMaterials.PentlanditeFroth, 4000});
        MaterialsSEX.add(new Object[]{"redstone", GTValues.VA[IV], GTCAItems.MilledRedstone, GTCAMaterials.RedstoneFroth, 4000});
        MaterialsPEX.add(new Object[]{"spessartine", GTValues.VA[LuV], GTCAItems.MilledSpessartine, GTCAMaterials.SpessartineFroth, 4000});
        MaterialsPEX.add(new Object[]{"grossular", GTValues.VA[LuV], GTCAItems.MilledGrossular, GTCAMaterials.GrossularFroth, 4000});
        MaterialsSEX.add(new Object[]{"almandine", GTValues.VA[IV], GTCAItems.MilledAlmandine, GTCAMaterials.AlmandineFroth, 4000});
        MaterialsSEX.add(new Object[]{"pyrope", GTValues.VA[EV], GTCAItems.MilledPyrope, GTCAMaterials.PyropeFroth, 4000});
        MaterialsPEX.add(new Object[]{"monazite", GTValues.VA[LuV], GTCAItems.MilledMonazite, GTCAMaterials.MonaziteFroth, 4000});
    }

    private static void frothRecipes(Consumer<FinishedRecipe> provider) {
        setSPEX();
        for (Object[] holder : MaterialsPEX) {
            String id = (String) holder[0];
            int eut = (int) holder[1];
            ItemEntry<Item> item = (ItemEntry<Item>) holder[2];
            FluidStack frothOut = ((Material) holder[3]).getFluid(6000);
            FluidStack SpruceOil = (GTCAMaterials.SpruceOil.getFluid((int)holder[4]));

            GTCARecipeTypes.FLCR.recipeBuilder(id).duration(4800).EUt(eut)
                    .inputItems(GTCAHelper.getItem("dust", GTCAMaterials.PotassiumEthylXanthate, 16))
                    .inputItems(item, 64)
                    .inputItems(item, 32)
                    .inputFluids(SpruceOil)
                    .outputFluids(frothOut)
                    .save(provider);
        }
        for (Object[] holder : MaterialsSEX) {
            String id = (String) holder[0];
            int eut = (int) holder[1];
            ItemEntry<Item> item = (ItemEntry<Item>) holder[2];
            FluidStack frothOut = ((Material) holder[3]).getFluid(4000);
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


    //  buffed isa line second time xd

    private static void frothProcessing(Consumer<FinishedRecipe> provider){

        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("pyrope_froth").EUt(VA[EV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.PyropeFroth.getFluid(4000))
                .blastFurnaceTemp(3500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Magnesium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Magnesium, 46))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Manganese, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Manganese, 6))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Borax, 60))
                .save(provider);

        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("chalcopyrite_froth").EUt(VA[IV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.ChalcopyriteFroth.getFluid(4000))
                .blastFurnaceTemp(4500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Copper, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Copper, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Copper, 52))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iron, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iron, 56))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Cadmium, 50))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Indium, 60))
                .save(provider);

        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("nickel_froth").EUt(VA[IV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.NickelFroth.getFluid(4000))
                .blastFurnaceTemp(4500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Nickel, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Nickel, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Nickel, 22))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Cobalt, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Cobalt, 56))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Rhodium, 58))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Ruthenium, 28))
                .save(provider);

        //Missing firestone(gtnh), dysprosium(registered in gtceu)
        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("redstone_froth").EUt(VA[IV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.RedstoneFroth.getFluid(4000))
                .blastFurnaceTemp(4500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Redstone, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Redstone, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Redstone, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Gold, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Gold, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Gold, 60))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Chromium, 60))
                .save(provider);

        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("sphalerite_froth").EUt(VA[LuV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.SphaleriteFroth.getFluid(4000))
                .blastFurnaceTemp(5500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Zinc, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Zinc, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Zinc, 52))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iron, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iron, 56))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Indium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Germanium, 16))
                .save(provider);

        //Missing selenium(registered in gtceu), tellurium(registered in gtceu)
        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("platinum_froth").EUt(VA[LuV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.PlatinumFroth.getFluid(4000))
                .blastFurnaceTemp(5500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Platinum, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Platinum, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Rhodium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Rhodium, 12))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iridium, 40))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Ruthenium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Palladium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Palladium, 32))
                .save(provider);

        //Missing hafnium(registered in gtceu), promethium(registered in gtceu)
        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("pentlandite_froth").EUt(VA[LuV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.PentlanditeFroth.getFluid(4000))
                .blastFurnaceTemp(5500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iron, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iron, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iron, 22))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Nickel, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Nickel, 36))
                .save(provider);

        //Missing strontium(registered in gtceu)
        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("spessartine_froth").EUt(VA[LuV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.SpessartineFroth.getFluid(4000))
                .blastFurnaceTemp(5500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Manganese, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Manganese, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Manganese, 22))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Aluminium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Aluminium, 26))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Iridium, 48))
                .save(provider);

        //Missing thallium(gtnh)
        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("grossular_froth").EUt(VA[LuV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.GrossularFroth.getFluid(4000))
                .blastFurnaceTemp(5500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Calcium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Calcium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Aluminium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Aluminium, 46))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Tungsten, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Tungsten, 60))
                .save(provider);

        //Missing ytterbium(registered in gtceu)
        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("almandine_froth").EUt(VA[LuV]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.AlmandineFroth.getFluid(4000))
                .blastFurnaceTemp(5500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Aluminium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Aluminium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Aluminium, 22))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Magnesium, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Magnesium, 11))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Yttrium, 25))
                .save(provider);

        //Missing erbium(registered in gtceu)
        GTCARecipeTypes.THERMAL_REACTOR.recipeBuilder("monazite_froth").EUt(VA[ZPM]).duration(2400)
                .outputFluids(GTCAMaterials.RedMudSlurry.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .inputFluids(GTCAMaterials.MonaziteFroth.getFluid(4000))
                .blastFurnaceTemp(7500)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Lanthanum, 64))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Lutetium, 48))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Europium, 48))
                .save(provider);

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("red_mud_slurry").EUt(30).duration(600)
                .inputFluids(GTCAMaterials.RedMudSlurry.getFluid(1000))
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.Iron,1), 3000,300)
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.Copper,1), 3000,300)
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.Tin,1), 2000,300)
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.Sulfur,1), 2000,300)
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.Nickel,1), 1000,300)
                .chancedOutput(GTCAHelper.getItem("dust", GTMaterials.Lead,1), 1000,300)
                .outputFluids(GTMaterials.Water.getFluid(500))
                .save(provider);
    }
}
