package net.mordgren.gtca.common.data.recipes.permachine;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import static com.gregtechceu.gtceu.common.data.GTItems.FERTILIZER;
import java.util.ArrayList;
import java.util.function.Consumer;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.GREEN_HOUSE;

public class GreenHouseRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        treeRecipes(provider);
        plantRecipes(provider);
        rubberTreeRecipes(provider);
    }

    private static ArrayList<Item[]> Trees;
    private static ArrayList<Object[]> Plants;


    private static void treesArrayInit() {
        Trees = new ArrayList<>();

        Trees.add(new Item[]{Items.OAK_SAPLING, Items.OAK_LOG});
        Trees.add(new Item[]{Items.SPRUCE_SAPLING, Items.SPRUCE_LOG});
        Trees.add(new Item[]{Items.BIRCH_SAPLING, Items.BIRCH_LOG});
        Trees.add(new Item[]{Items.JUNGLE_SAPLING, Items.JUNGLE_LOG});
        Trees.add(new Item[]{Items.ACACIA_SAPLING, Items.ACACIA_LOG});
        Trees.add(new Item[]{Items.DARK_OAK_SAPLING, Items.DARK_OAK_LOG});
        Trees.add(new Item[]{Items.MANGROVE_PROPAGULE, Items.MANGROVE_LOG});
        Trees.add(new Item[]{Items.CHERRY_SAPLING, Items.CHERRY_LOG});
    }

    private static void plantsArrayInit() {
        Plants = new ArrayList<>();

        Plants.add(new Object[]{Items.PUMPKIN_SEEDS, Items.PUMPKIN, 6});
        Plants.add(new Object[]{Items.BEETROOT_SEEDS, Items.BEETROOT, 16});
        Plants.add(new Object[]{Items.SWEET_BERRIES, Items.SWEET_BERRIES, 16});
        Plants.add(new Object[]{Items.GLOW_BERRIES, Items.GLOW_BERRIES, 8});
        Plants.add(new Object[]{Items.WHEAT_SEEDS, Items.WHEAT, 16});
        Plants.add(new Object[]{Items.MELON_SEEDS, Items.MELON, 6});
        Plants.add(new Object[]{Items.CARROT, Items.CARROT, 12});
        Plants.add(new Object[]{Items.SUGAR_CANE, Items.SUGAR_CANE, 12});
        Plants.add(new Object[]{Items.KELP, Items.KELP, 12});
        Plants.add(new Object[]{Items.CACTUS, Items.CACTUS, 12});
        Plants.add(new Object[]{Items.BROWN_MUSHROOM, Items.BROWN_MUSHROOM, 12});
        Plants.add(new Object[]{Items.RED_MUSHROOM, Items.RED_MUSHROOM, 12});
        Plants.add(new Object[]{Items.NETHER_WART, Items.NETHER_WART, 12});
        Plants.add(new Object[]{Items.BAMBOO, Items.BAMBOO, 16});
    }

    private static void treeRecipes(Consumer<FinishedRecipe> provider) {
        treesArrayInit();
        for(Item[] woodType : Trees){
            GREEN_HOUSE.recipeBuilder(woodType[1].toString())
                    .EUt(40)
                    .duration(1200)
                    .circuitMeta(1)
                    .inputFluids(GTMaterials.Water.getFluid(1000))
                    .notConsumable(woodType[0])
                    .outputItems(woodType[1], 64)
                    .outputItems(woodType[0], 6)
                    .save(provider);

            GREEN_HOUSE.recipeBuilder(woodType[1].toString() + "_fertilizer")
                    .EUt(60)
                    .duration(900)
                    .circuitMeta(2)
                    .inputFluids(GTMaterials.Water.getFluid(1000))
                    .notConsumable(woodType[0])
                    .inputItems(FERTILIZER, 4)
                    .outputItems(woodType[1], 64)
                    .outputItems(woodType[1], 64)
                    .outputItems(woodType[0], 12)
                    .save(provider);

        }
    }


    private static void plantRecipes(Consumer<FinishedRecipe> provider) {
        plantsArrayInit();
        for(Object[] seedType : Plants) {
            GREEN_HOUSE.recipeBuilder(seedType[1].toString())
                    .EUt(40)
                    .duration(1200)
                    .circuitMeta(1)
                    .inputFluids(GTMaterials.Water.getFluid(1000))
                    .notConsumable((Item) seedType[0])
                    .outputItems((Item) seedType[1], (int)seedType[2])
                    .save(provider);

            GREEN_HOUSE.recipeBuilder(seedType[1].toString() + "_fertilizer")
                    .EUt(60)
                    .duration(900)
                    .circuitMeta(2)
                    .inputFluids(GTMaterials.Water.getFluid(1000))
                    .notConsumable((Item) seedType[0])
                    .inputItems(FERTILIZER, 4)
                    .outputItems((Item) seedType[1], 2 * (int) seedType[2])
                    .save(provider);
        }
    }

    private static void rubberTreeRecipes(Consumer<FinishedRecipe> provider) {
        GREEN_HOUSE.recipeBuilder("rubber_tree")
                .EUt(40)
                .duration(1200)
                .circuitMeta(1)
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .notConsumable(GTBlocks.RUBBER_SAPLING.asStack())
                .outputItems(GTBlocks.RUBBER_LOG, 16)
                .outputItems(GTBlocks.RUBBER_SAPLING, 3)
                .outputItems(GTItems.STICKY_RESIN, 4)
                .save(provider);

        GREEN_HOUSE.recipeBuilder("rubber_tree_fertilizer")
                .EUt(60)
                .duration(900)
                .circuitMeta(2)
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .notConsumable(GTBlocks.RUBBER_SAPLING.asStack())
                .inputItems(FERTILIZER, 4)
                .outputItems(GTBlocks.RUBBER_LOG, 32)
                .outputItems(GTBlocks.RUBBER_SAPLING, 6)
                .outputItems(GTItems.STICKY_RESIN, 8)
                .save(provider);
    }

}
