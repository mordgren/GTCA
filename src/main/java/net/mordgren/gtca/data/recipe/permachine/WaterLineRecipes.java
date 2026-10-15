package net.mordgren.gtca.data.recipe.permachine;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.data.recipe.builder.WaterLineRecipeBuilder;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Air;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static net.mordgren.gtca.data.recipe.builder.WaterLineRecipeBuilder.extremeTemperature;

public final class WaterLineRecipes {

    private WaterLineRecipes() {}

    public static void init(Consumer<FinishedRecipe> provider) {
        registerWaterRecipes(provider);
        registerWaterLineChemistry(provider);
    }

    // ============================================================
    // WATER PURIFICATION TIER 1 - 7
    // ============================================================

    private static void registerWaterRecipes(Consumer<FinishedRecipe> provider) {

        // Tier 1 - Clarifier
        var clarifier = WaterLineRecipeBuilder
                .clarifier("purified_water_grade_1", 7_000)
                .EUt(VA[LuV])
                .inputFluids(GTMaterials.Water.getFluid(1_000))
                .outputFluids(GTCAMaterials.PurifiedWaterGradeI.getFluid(900))
                .chancedOutput(new ItemStack(Items.STICK), 1_000, 0)
                .chancedOutput(TagPrefix.dust, GTMaterials.Stone, 500, 0)
                .chancedOutput(new ItemStack(Items.GOLD_NUGGET), 100, 0);

        WaterLineRecipeBuilder.activatedCarbonFilter(clarifier, GTCAItems.CARBON_FILTER.asStack())
                .save(provider);

        // Tier 2 - Ozonation
        WaterLineRecipeBuilder
                .ozonation("purified_water_grade_2", 2_000)
                .EUt(VA[LuV])
                .inputFluids(GTCAMaterials.PurifiedWaterGradeI.getFluid(1_000))
                .inputFluids(GTCAMaterials.Ozone.getFluid(128_000))
                .outputFluids(GTCAMaterials.PurifiedWaterGradeII.getFluid(900))
                .chancedOutput(TagPrefix.dust, GTMaterials.Manganese, 500, 0)
                .chancedOutput(TagPrefix.dust, GTMaterials.Iron, 500, 0)
                .chancedOutput(TagPrefix.dust, GTMaterials.Sulfur, 500, 0)
                .save(provider);

        // Tier 3 - Flocculation
        WaterLineRecipeBuilder
                .flocculation("purified_water_grade_3", 1_000)
                .EUt(VA[ZPM])
                .inputFluids(GTCAMaterials.PurifiedWaterGradeII.getFluid(1_000))
                .inputFluids(GTCAMaterials.PolyAluminiumChloride.getFluid(100_000))
                .outputFluids(GTCAMaterials.PurifiedWaterGradeIII.getFluid(900))
                .outputFluids(GTCAMaterials.FlocculationWasteLiquid.getFluid(100_000))
                .chancedOutput(new ItemStack(Items.CLAY_BALL), 1_000, 0)
                .chancedOutput(TagPrefix.dust, GTMaterials.QuartzSand, 500, 0)
                .chancedOutput(TagPrefix.dust, GTMaterials.PolyvinylChloride, 100, 0)
                .save(provider);

        // Tier 4 - pH Neutralization
        WaterLineRecipeBuilder
                .phNeutralization("purified_water_grade_4")
                .EUt(VA[ZPM])
                .inputFluids(GTCAMaterials.PurifiedWaterGradeIII.getFluid(1_000))
                .outputFluids(GTCAMaterials.PurifiedWaterGradeIV.getFluid(900))
                .save(provider);

        // Tier 5 - Extreme Temperature
                WaterLineRecipeBuilder
        .extremeTemperature("purified_water_grade_5")
                .EUt(VA[UV])
                .inputFluids(GTCAMaterials.PurifiedWaterGradeIV.getFluid(1_000))
                .outputFluids(GTCAMaterials.PurifiedWaterGradeV.getFluid(900))
                .save(provider);
    }

    private static void registerWaterLineChemistry(Consumer<FinishedRecipe> provider) {

        // Activated Carbon Filter Mesh
        ASSEMBLER_RECIPES
                .recipeBuilder("activated_carbon_filter_mesh")
                .inputItems(TagPrefix.dust, GTMaterials.ActivatedCarbon, 8)
                .inputItems(TagPrefix.wireFine, GTMaterials.StainlessSteel, 8)
                .inputItems(TagPrefix.foil, GTMaterials.Polytetrafluoroethylene, 4)
                .outputItems(GTCAItems.CARBON_FILTER.asStack())
                .duration(200)
                .EUt(VA[EV])
                .save(provider);

        // Sodium Aluminate
        CHEMICAL_RECIPES
                .recipeBuilder("waterline_sodium_aluminate")
                .circuitMeta(24)
                .inputItems(TagPrefix.dust, GTMaterials.Aluminium, 16)
                .inputItems(TagPrefix.dust, GTMaterials.SodiumHydroxide, 48)
                .inputFluids(GTMaterials.Water.getFluid(16_000))
                .outputItems(TagPrefix.dust, GTCAMaterials.SodiumAluminate, 64)
                .outputFluids(GTMaterials.Hydrogen.getFluid(48_000))
                .duration(400)
                .EUt(VA[HV])
                .save(provider);

        // Aluminium Hydroxide
        CHEMICAL_RECIPES
                .recipeBuilder("waterline_aluminium_hydroxide")
                .circuitMeta(1)
                .inputItems(TagPrefix.dust, GTCAMaterials.SodiumAluminate, 4)
                .inputFluids(GTMaterials.Water.getFluid(2_000))
                .outputItems(TagPrefix.dust, GTCAMaterials.AluminiumHydroxide, 4)
                .outputItems(TagPrefix.dust, GTMaterials.SodiumHydroxide, 3)
                .duration(50)
                .EUt(VA[MV])
                .save(provider);

        // Polyaluminium Chloride
        CHEMICAL_RECIPES
                .recipeBuilder("waterline_polyaluminium_chloride")
                .inputItems(TagPrefix.dust, GTCAMaterials.AluminiumHydroxide, 8)
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(3_000))
                .outputFluids(GTCAMaterials.PolyAluminiumChloride.getFluid(1_000))
                .outputFluids(GTMaterials.Water.getFluid(3_000))
                .duration(80)
                .EUt(VA[EV])
                .save(provider);

        // Flocculation Waste Recycling
        DISTILLATION_RECIPES
                .recipeBuilder("waterline_flocculation_waste_recycling")
                .inputFluids(GTCAMaterials.FlocculationWasteLiquid.getFluid(10_000))
                .outputItems(TagPrefix.dust, GTMaterials.Aluminium, 20)
                .outputFluids(GTMaterials.Oxygen.getFluid(30_000))
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(30_000))
                .duration(20)
                .EUt(VA[EV])
                .save(provider);

        // Ozone - Distillation
        DISTILLATION_RECIPES
                .recipeBuilder("air_to_ozone")
                .inputFluids(Air.getFluid(10_000))
                .outputFluids(GTCAMaterials.Ozone.getFluid(1_500))
                .duration(185)
                .circuitMeta(2)
                .EUt(VA[EV])
                .save(provider);
    }
}

