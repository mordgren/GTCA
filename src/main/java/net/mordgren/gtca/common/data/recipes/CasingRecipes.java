package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;
import net.mordgren.gtca.common.data.GTCABlocks;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ALLOY_SMELTER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static net.mordgren.gtca.common.data.GTCAMaterials.*;

public class CasingRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        regCasingRecipes(provider);
  }

    private static void regCasingRecipes(Consumer<FinishedRecipe> provider) {
        ASSEMBLER_RECIPES.recipeBuilder("casing_aebf").EUt(GTValues.VA[GTValues.HV]).duration(80)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.TM20MnAlloy, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.TM20MnAlloy, 1))
                .outputItems(GTCABlocks.CASING_AEBF, 2)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("casing_greenhouse").EUt(GTValues.VA[GTValues.LV]).duration(80)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.CNFAlloy, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.CNFAlloy, 1))
                .outputItems(GTCABlocks.CASING_GREENHOUSE, 2)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("dural_casing").EUt(GTValues.VA[GTValues.HV]).duration(100)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Dural, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Dural, 1))
                .outputItems(GTCABlocks.DURAL_CASING, 2)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("prw_casing").EUt(GTValues.VA[GTValues.HV]).duration(240)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.MAR_CE_M200, 4))
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Incoloy903, 4))
                .inputItems(GTCAHelper.getItem("frame", GTMaterials.NiobiumTitanium, 1))
                .outputItems(GTCABlocks.PRW_Casing, 1)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("shd_casing").EUt(GTValues.VA[GTValues.HV]).duration(260)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.MAR_M200, 4))
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Moltech, 4))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Moltech, 1))
                .outputItems(GTCABlocks.SHD_CASING, 1)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("shd_gearbox").EUt(GTValues.VA[GTValues.HV]).duration(260)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.MAR_CE_M200, 4))
                .inputItems(GTCAHelper.getItem("gear", GTCAMaterials.Moltech, 2))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.MAR_CE_M200, 1))
                .outputItems(GTCABlocks.SHD_Gearbox, 1)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("vitallium_casing").EUt(GTValues.VA[GTValues.HV]).duration(100)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Vitallium, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Vitallium, 1))
                .outputItems(GTCABlocks.VITALLIUM_CASING, 2)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("inconel718_casing").EUt(GTValues.VA[GTValues.EV]).duration(140)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Inconel718, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Inconel718, 1))
                .outputItems(GTCABlocks.INCONEL718_CASING, 2)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("tantalloy61_casing").EUt(GTValues.VA[GTValues.HV]).duration(120)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Tantalloy61, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Tantalloy61, 1))
                .outputItems(GTCABlocks.TANTALLOY61_CASING, 2)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("nimonic80a_casing").EUt(GTValues.VA[GTValues.EV]).duration(140)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Nimonic80A, 6))
                .inputItems(GTCAHelper.getItem("frame", GTCAMaterials.Nimonic80A, 1))
                .outputItems(GTCABlocks.NIMONIC80A_CASING, 2)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("isamill_intake")
                .inputItems(GTMachines.HULL[GTValues.IV].asStack(2))
                .inputItems(GTCAHelper.getItem("plate", Inconel625, 16))
                .inputItems(GTCAHelper.getItem("ring", GTCAMaterials.IncoloyMA323, 8))
                .inputItems(GTCAHelper.getItem("screw", GTCAMaterials.IncoloyMA323, 8))
                .inputItems(GTCAHelper.getItem("plate", HSSE, 8))
                .inputFluids(Aluminium.getFluid(1152))
                .duration(2000).EUt(1920)
                .circuitMeta(7)
                .outputItems(GTCABlocks.ISAMILL_AIR_INTAKE.asStack(2))
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("isamill_casing")
                .inputItems(GTMachines.HULL[GTValues.IV].asStack(1))
                .inputItems(GTCAHelper.getItem("plate", Inconel625, 8))
                .inputItems(GTCAHelper.getItem("rod", Zeron182, 4))
                .inputItems(GTCAHelper.getItem("gear", HSSG, 4))
                .inputItems(GTCAHelper.getItem("screw", Zeron182, 8))
                .inputFluids(Titanium.getFluid(576))
                .duration(1800).EUt(32720)
                .circuitMeta(7)
                .outputItems(GTCABlocks.ISAMILL_CASING.asStack(2))
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("isamill_gearbox")
                .inputItems(GTBlocks.CASING_TITANIUM_GEARBOX.asStack(2))
                .inputItems(GTCAHelper.getItem("gear", Inconel625, 4))
                .inputItems(GTCAHelper.getItem("plate", Inconel625, 8))
                .inputItems(GTCAHelper.getItem("gear", HSSG, 4))
                .inputItems(GTCAHelper.getItem("bolt", Zeron182, 16))
                .inputFluids(TungstenSteel.getFluid(1152))
                .duration(200).EUt(32720)
                .circuitMeta(7)
                .outputItems(GTCABlocks.ISAMILL_GEARBOX.asStack(1))
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("cyclotron_coil").EUt(7680).duration(2400)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.IncoloyMA323, 8))
                .inputItems(GTCAHelper.getItem("bolt", GTCAMaterials.Tantalloy61, 16))
                .inputItems(GTCAHelper.getItem("screw",  GTCAMaterials.Incoloy020, 32))
                .inputItems(GTBlocks.COIL_NICHROME, 1)
                .inputItems(GTItems.VOLTAGE_COIL_IV,8)
                .inputItems(GTItems.FIELD_GENERATOR_EV, 1)
                .inputFluids(GTCAMaterials.HG1223.getFluid(720))
                .outputItems(GTCABlocks.CYCLOTRON_COIL, 1)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("comet_casing").EUt(1920).duration(1200)
                .inputItems(GTBlocks.CASING_ALUMINIUM_FROSTPROOF, 1)
                .inputItems(GTItems.VOLTAGE_COIL_EV,4)
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.IncoloyDS, 8))
                .inputItems(GTCAHelper.getItem("screw", GTCAMaterials.Inconel690, 16))
                .inputItems(GTCAHelper.getItem("longRod", GTCAMaterials.EglinSteel, 4))
                .inputItems(GTItems.ELECTRIC_PISTON_HV,2)
                .inputFluids(GTCAMaterials.ZirconiumCarbide.getFluid(1152))
                .outputItems(GTCABlocks.COMET_CASING, 1)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("flcri_casing").EUt(30720).duration(2400)
                .circuitMeta(7)
                .inputItems(GTMachines.HULL[GTValues.IV].asStack())
                .inputItems(GTCAHelper.getItem("plate", WatertightSteel, 8))
                .inputItems(GTCAHelper.getItem("ring", Stellite100, 8))
                .inputItems(GTCAHelper.getItem("doublePlate", HSSG, 4))
                .inputItems(GTCAHelper.getItem("screw", HastelloyN, 8))
                .inputFluids(StainlessSteel.getFluid(1152))
                .outputItems(GTCABlocks.FLCR_CASING_TYPE_I, 1)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("flcrii_casing").EUt(30720).duration(1200)
                .circuitMeta(6)
                .inputItems(GTCAHelper.getItem("plate", Inconel690, 4))
                .inputItems(GTCAHelper.getItem("plate", Ultimet, 2))
                .inputItems(GTCAHelper.getItem("frame", Moltech, 1))
                .outputItems(GTCABlocks.FLCR_CASING_TYPE_II, 1)
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder("reinforced_glass").EUt(4).duration(400)
                .inputItems(GTItems.CARBON_FIBER_PLATE.asStack())
                .inputItems(Blocks.GLASS.asItem(), 3)
                .outputItems(GTCABlocks.REINFORCED_GLASS, 4)
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder("reinforced_glass_dust").EUt(4).duration(400)
                .inputItems(GTItems.CARBON_FIBER_PLATE.asStack())
                .inputItems(GTCAHelper.getItem("dust", Glass,3))
                .outputItems(GTCABlocks.REINFORCED_GLASS, 4)
                .save(provider);
    }
}




















