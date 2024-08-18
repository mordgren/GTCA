package net.mordgren.gtca.common.data.blocks;



import com.gregtechceu.gtceu.common.data.GTFluids;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMaterials;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.DistilledWater;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Steam;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.STEAM_TURBINE_FUELS;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.GREEN_HOUSE;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.STEAM_PRESSURIZER;


class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


class Items {
    public static final Item OAK_SAPLING = new Item("OAK_SAPLING");
    public static final Item OAK_LOG = new Item("OAK_LOG");
    public static final Item SPRUCE_SAPLING = new Item("SPRUCE_SAPLING");
    public static final Item SPRUCE_LOG = new Item("SPRUCE_LOG");
    public static final Item BIRCH_SAPLING = new Item("BIRCH_SAPLING");
    public static final Item BIRCH_LOG = new Item("BIRCH_LOG");
    public static final Item JUNGLE_SAPLING = new Item("JUNGLE_SAPLING");
    public static final Item JUNGLE_LOG = new Item("JUNGLE_LOG");
    public static final Item ACACIA_SAPLING = new Item("ACACIA_SAPLING");
    public static final Item ACACIA_LOG = new Item("ACACIA_LOG");
    public static final Item DARK_OAK_SAPLING = new Item("DARK_OAK_SAPLING");
    public static final Item DARK_OAK_LOG = new Item("DARK_OAK_LOG");
    public static final Item MANGROVE_PROPAGULE = new Item("MANGROVE_PROPAGULE");
    public static final Item MANGROVE_LOG = new Item("MANGROVE_LOG");
    public static final Item CHERRY_SAPLING = new Item("CHERRY_SAPLING");
    public static final Item CHERRY_LOG = new Item("CHERRY_LOG");
}


class Tree {
    private Item sapling;
    private int saplingCount;
    private Item log;
    private int logCount;

    public Tree(Item sapling, int saplingCount, Item log, int logCount) {
        this.sapling = sapling;
        this.saplingCount = saplingCount;
        this.log = log;
        this.logCount = logCount;
    }

    public Item getSapling() {
        return sapling;
    }

    public int getSaplingCount() {
        return saplingCount;
    }

    public Item getLog() {
        return log;
    }

    public int getLogCount() {
        return logCount;
    }
}


public class GreenHouse {
    private static List<Tree> trees;

    public GreenHouse() {
        trees = new ArrayList<>();
        initializeTrees();
    }


    private void initializeTrees() {
        trees.add(new Tree(Items.OAK_SAPLING, 1, Items.OAK_LOG, 24));
        trees.add(new Tree(Items.SPRUCE_SAPLING, 1, Items.SPRUCE_LOG, 24));
        trees.add(new Tree(Items.BIRCH_SAPLING, 1, Items.BIRCH_LOG, 24));
        trees.add(new Tree(Items.JUNGLE_SAPLING, 1, Items.JUNGLE_LOG, 24));
        trees.add(new Tree(Items.ACACIA_SAPLING, 1, Items.ACACIA_LOG, 24));
        trees.add(new Tree(Items.DARK_OAK_SAPLING, 1, Items.DARK_OAK_LOG, 24));
        trees.add(new Tree(Items.MANGROVE_PROPAGULE, 1, Items.MANGROVE_LOG, 24));
        trees.add(new Tree(Items.CHERRY_SAPLING, 1, Items.CHERRY_LOG, 24));

        for (Tree woodtype : trees) {




        }
    }


    private static void test (Consumer<FinishedRecipe> provider) {
        GREEN_HOUSE.recipeBuilder("logs").EUt(40).duration(600)
                .inputFluids(GTMaterials.Water.getFluid(1500))
                .inputItems()
                .save(provider);

    }



                }

