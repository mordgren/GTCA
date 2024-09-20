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

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static net.mordgren.gtca.common.data.GTCAMaterials.*;

public class isamillRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
            basic(provider);
    }
    private static void basic(Consumer<FinishedRecipe> provider) {


        ASSEMBLY_LINE_RECIPES.recipeBuilder("isamill_controller")
                .inputItems(GTCACasings.ISAMILL_GEARBOX,4)
                .inputItems(GTMachines.HULL[GTValues.LuV].asStack(4))
                .inputItems(GTItems.COMPONENT_GRINDER_TUNGSTEN,16)
                .inputItems(CustomTags.LuV_CIRCUITS, 8)
                .inputItems(GTCAHelper.getItem("gear", Inconel625, 8))
                .inputItems(GTCAHelper.getItem("plate", Inconel625, 32))
                .inputItems(GTCAHelper.getItem("plate", Zeron182, 32))
                .inputItems(GTCAHelper.getItem("screw", Zeron182, 64))
                .inputItems(GTCAHelper.getItem("fineWire", NiobiumTitanium, 64))
                .inputItems(GTCAHelper.getItem("fineWire", NiobiumTitanium, 64))
                .inputItems(GTCAHelper.getItem("foil", Titanium, 32))
                .inputFluids(GTCAMaterials.Zeron182.getFluid(2304))
                .inputFluids(GTCAMaterials.LafiumCompound.getFluid(4608))
                .inputFluids(GTCAMaterials.TrunuimNaquadahCarbonite.getFluid(4608))
                .outputItems(GTCAMachines.ISAMILL.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTMachines.MACERATOR[GTValues.LuV].asStack())
                        .duration(2100)
                        .EUt(VA[LuV]))
                .duration(4000).EUt(32720)
                .save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("isamill_airtake")
                .inputItems(GTMachines.HULL[GTValues.IV].asStack(2))
                .inputItems(GTCAHelper.getItem("plate", Inconel625, 16))
                .inputItems(GTCAHelper.getItem("ring",GTCAMaterials.IncoloyMA323, 8))
                .inputItems(GTCAHelper.getItem("screw", GTCAMaterials.IncoloyMA323, 8))
                .inputItems(GTCAHelper.getItem("plate", HSSE, 8))
                .inputFluids(Aluminium.getFluid(1152))
                .duration(2000).EUt(1920)
                .circuitMeta(7)
                .outputItems(GTCACasings.ISAMILL_AIR_INTAKE.asStack(2))
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
                .outputItems(GTCACasings.ISAMILL_CASING.asStack(2))
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
                .outputItems(GTCACasings.ISAMILL_GEARBOX.asStack(1))
                .save(provider);
                    }
            }

