package net.mordgren.gtca.common.data.recipes.permachine;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.mordgren.gtca.common.util.GTCAHelper;
import java.util.function.Consumer;

import static net.mordgren.gtca.common.data.GTCARecipeTypes.INDUSTRIAL_COKE_OVEN;

public class ICORecipes {

    public static void init(Consumer<FinishedRecipe> provider){
        logsRecipes(provider);
        logsNitrogenRecipes(provider);
        cokeRecipes(provider);
        biomassRecipes(provider);
        miscRecipes(provider);
    }


    private static void logsRecipes(Consumer<FinishedRecipe> provider) {
            INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_coalgas")
                    .EUt(30)
                    .duration(288)
                    .circuitMeta(20)
                    .inputFluids(GTMaterials.Steam.getFluid(250))
                    .inputItems(ItemTags.LOGS, 5)
                    .outputItems(Items.CHARCOAL, 6)
                    .outputFluids(GTMaterials.CoalGas.getFluid(360))
                    .save(provider);

            INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_creosote")
                    .EUt(64)
                    .duration(128)
                    .circuitMeta(1)
                    .inputItems(ItemTags.LOGS, 4)
                    .outputItems(Items.CHARCOAL, 5)
                    .outputFluids(GTMaterials.Creosote.getFluid(1000))
                    .save(provider);

            INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_byproducts")
                    .EUt(64)
                    .duration(128)
                    .circuitMeta(3)
                    .inputItems(ItemTags.LOGS, 4)
                    .outputItems(Items.CHARCOAL, 5)
                    .outputFluids(GTMaterials.CharcoalByproducts.getFluid(1000))
                    .save(provider);

            INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_woodgas")
                    .EUt(64)
                    .duration(128)
                    .circuitMeta(5)
                    .inputItems(ItemTags.LOGS, 4)
                    .outputItems(Items.CHARCOAL, 5)
                    .outputFluids(GTMaterials.WoodGas.getFluid(375))
                    .save(provider);

            INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_woodvinegar")
                    .EUt(64)
                    .duration(128)
                    .circuitMeta(7)
                    .inputItems(ItemTags.LOGS, 4)
                    .outputItems(Items.CHARCOAL, 5)
                    .outputFluids(GTMaterials.WoodVinegar.getFluid(750))
                    .save(provider);

            INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_woodtar")
                    .EUt(64)
                    .duration(128)
                    .circuitMeta(9)
                    .inputItems(ItemTags.LOGS, 4)
                    .outputItems(Items.CHARCOAL, 5)
                    .outputFluids(GTMaterials.WoodTar.getFluid(375))
                    .save(provider);

            INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_heavyoil")
                    .EUt(192)
                    .duration(64)
                    .circuitMeta(11)
                    .inputItems(ItemTags.LOGS, 4)
                    .outputItems(GTCAHelper.getItem("dust", GTMaterials.Ash, 1))
                    .outputFluids(GTMaterials.OilHeavy.getFluid(50))
                    .save(provider);
    }

    private static void logsNitrogenRecipes(Consumer<FinishedRecipe> provider) {
        INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_nitrogen_creosote")
                .EUt(96)
                .duration(64)
                .circuitMeta(2)
                .inputItems(ItemTags.LOGS, 4)
                .inputFluids(GTMaterials.Nitrogen.getFluid(250))
                .outputItems(Items.CHARCOAL, 5)
                .outputFluids(GTMaterials.Creosote.getFluid(1000))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_nitrogen_byproducts")
                .EUt(96)
                .duration(64)
                .circuitMeta(4)
                .inputItems(ItemTags.LOGS, 4)
                .inputFluids(GTMaterials.Nitrogen.getFluid(250))
                .outputItems(Items.CHARCOAL, 5)
                .outputFluids(GTMaterials.CharcoalByproducts.getFluid(1000))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_nitrogen_woodgas")
                .EUt(96)
                .duration(64)
                .circuitMeta(6)
                .inputItems(ItemTags.LOGS, 4)
                .inputFluids(GTMaterials.Nitrogen.getFluid(250))
                .outputItems(Items.CHARCOAL, 5)
                .outputFluids(GTMaterials.WoodGas.getFluid(375))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_nitrogen_woodvinegar")
                .EUt(96)
                .duration(64)
                .circuitMeta(8)
                .inputItems(ItemTags.LOGS, 4)
                .inputFluids(GTMaterials.Nitrogen.getFluid(250))
                .outputItems(Items.CHARCOAL, 5)
                .outputFluids(GTMaterials.WoodVinegar.getFluid(750))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("logs_nitrogen_woodtar")
                .EUt(96)
                .duration(64)
                .circuitMeta(10)
                .inputItems(ItemTags.LOGS, 4)
                .inputFluids(GTMaterials.Nitrogen.getFluid(250))
                .outputItems(Items.CHARCOAL, 5)
                .outputFluids(GTMaterials.WoodTar.getFluid(375))
                .save(provider);
    }

    private static void cokeRecipes(Consumer<FinishedRecipe> provider){
        INDUSTRIAL_COKE_OVEN.recipeBuilder("coke_creosote")
                .EUt(64)
                .duration(32)
                .circuitMeta(1)
                .inputItems(Items.COAL, 1)
                .outputItems(GTCAHelper.getItem("gem", GTMaterials.Coke, 1))
                .outputFluids(GTMaterials.Creosote.getFluid(500))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("coke_creosote_nitrogen")
                .EUt(96)
                .duration(32)
                .circuitMeta(2)
                .inputItems(Items.COAL, 2)
                .inputFluids(GTMaterials.Nitrogen.getFluid(125))
                .outputItems(GTCAHelper.getItem("gem", GTMaterials.Coke, 2))
                .outputFluids(GTMaterials.Creosote.getFluid(1000))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("coke_coalgas")
                .EUt(120)
                .duration(288)
                .circuitMeta(22)
                .inputItems(Items.COAL, 8)
                .inputFluids(GTMaterials.Steam.getFluid(500))
                .outputItems(GTCAHelper.getItem("gem", GTMaterials.Coke, 5))
                .outputFluids(GTMaterials.CoalGas.getFluid(1440))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("coke_coalgasx2")
                .EUt(240)
                .duration(600)
                .inputItems(Items.COAL, 6)
                .inputItems(GTCAHelper.getItem("gem", GTMaterials.Coke, 3))
                .inputFluids(GTMaterials.Steam.getFluid(1000))
                .outputItems(GTCAHelper.getItem("gem", GTMaterials.Coke, 7))
                .outputFluids(GTMaterials.CoalGas.getFluid(2520))
                .save(provider);
    }

    private static void biomassRecipes(Consumer<FinishedRecipe> provider){
        INDUSTRIAL_COKE_OVEN.recipeBuilder("fermented_biomass")
                .EUt(10)
                .duration(16)
                .circuitMeta(2)
                .inputFluids(GTMaterials.Biomass.getFluid(200))
                .outputFluids(GTMaterials.FermentedBiomass.getFluid(200))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("biochaff")
                .EUt(10)
                .duration(160)
                .circuitMeta(2)
                .inputItems(GTItems.BIO_CHAFF, 1)
                .inputFluids(GTMaterials.Water.getFluid(1500))
                .outputFluids(GTMaterials.FermentedBiomass.getFluid(1500))
                .save(provider);
    }

    private static void miscRecipes(Consumer<FinishedRecipe> provider){
        INDUSTRIAL_COKE_OVEN.recipeBuilder("coke_block_creosote")
                .EUt(64)
                .duration(256)
                .circuitMeta(1)
                .inputItems(Items.COAL_BLOCK, 1)
                .outputItems(GTCAHelper.getItem("block", GTMaterials.Coke, 1))
                .outputFluids(GTMaterials.Creosote.getFluid(4000))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("charcoal_dust")
                .EUt(64)
                .duration(256)
                .circuitMeta(1)
                .inputItems(Items.SUGAR, 23)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Charcoal, 12))
                .outputFluids(GTMaterials.Water.getFluid(1500))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("coke_block_creosote_nitrogen")
                .EUt(96)
                .duration(128)
                .circuitMeta(2)
                .inputItems(Items.COAL_BLOCK, 1)
                .inputFluids(GTMaterials.Nitrogen.getFluid(125))
                .outputItems(GTCAHelper.getItem("block", GTMaterials.Coke, 1))
                .outputFluids(GTMaterials.Creosote.getFluid(4000))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("charcoal_dust_nitrogen")
                .EUt(96)
                .duration(128)
                .circuitMeta(2)
                .inputItems(Items.SUGAR, 23)
                .inputFluids(GTMaterials.Nitrogen.getFluid(500))
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.Charcoal, 12))
                .outputFluids(GTMaterials.Water.getFluid(1500))
                .save(provider);

        INDUSTRIAL_COKE_OVEN.recipeBuilder("coal_coaltar")
                .EUt(120)
                .duration(288)
                .circuitMeta(8)
                .inputItems(Items.COAL, 6)
                .outputItems(GTCAHelper.getItem("dust", GTMaterials.DarkAsh, 1))
                .outputFluids(GTMaterials.CoalTar.getFluid(1100))
                .save(provider);
    }
}
