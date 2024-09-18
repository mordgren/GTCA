package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCACasings;
import net.mordgren.gtca.common.data.GTCAMachines;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static net.mordgren.gtca.common.data.GTCAMaterials.*;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.COMET_CYCLOTRON;

public class CometRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        common(provider);

    }


    private static void common(Consumer<FinishedRecipe> provider) {

            ASSEMBLER_RECIPES.recipeBuilder("comet_controller").EUt(7680).duration(6000)
                    .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Incoloy020, 8))
                    .inputItems(GTCAHelper.getItem("gear", GTCAMaterials.Tantalloy61, 2))
                    .inputItems(GTCAHelper.getItem("screw", GTCAMaterials.IncoloyMA956, 16))
                    .inputItems(CustomTags.IV_CIRCUITS, 16)
                    .inputItems(GTMachines.HULL[GTValues.IV].asStack())
                    .inputItems(GTCACasings.CYCLOTRON_COIL, 2)
                    .inputFluids(GTCAMaterials.Incoloy020.getFluid(1296))
                    .outputItems(GTCAMachines.COMET_CYCLOTRON.asStack())
                    .save(provider);

             ASSEMBLER_RECIPES.recipeBuilder("cyclotron_coil").EUt(7680).duration(2400)
                     .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.IncoloyMA956, 8))
                     .inputItems(GTCAHelper.getItem("screw", GTCAMaterials.Tantalloy61, 16))
                     .inputItems(GTBlocks.COIL_NICHROME, 1)
                     .inputItems(GTItems.VOLTAGE_COIL_IV,8)
                     .inputItems(GTItems.FIELD_GENERATOR_EV, 1)
                     .inputFluids(GTCAMaterials.HG1223.getFluid(720))
                     .outputItems(GTCACasings.CYCLOTRON_COIL, 1)
                     .save(provider);

            ASSEMBLER_RECIPES.recipeBuilder("comet_casing").EUt(1920).duration(1200)
                    .inputItems(GTBlocks.CASING_ALUMINIUM_FROSTPROOF, 1)
                    .inputItems(GTItems.VOLTAGE_COIL_EV,4)
                    .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.IncoloyDS, 8))
                    .inputItems(GTCAHelper.getItem("screw", GTCAMaterials.Inconel690, 16))
                    .inputItems(GTCAHelper.getItem("longRod", GTCAMaterials.EglinSteel, 4))
                    .inputItems(GTItems.ELECTRIC_PISTON_HV,2)
                    .inputFluids(GTCAMaterials.ZirconiumCarbide.getFluid(1152))
                    .outputItems(GTCACasings.COMET_CASING, 1)
                    .save(provider);




    }



}
