package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.ArrayList;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class IsamillRecipes {

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

    public static void init(Consumer<FinishedRecipe> provider) {
            isamill(provider);
            grindingBalls(provider);
    }

    private static void isamill(Consumer<FinishedRecipe> provider) {
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
}
