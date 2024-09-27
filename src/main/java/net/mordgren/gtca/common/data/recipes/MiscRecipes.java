package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCABlocks;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static net.mordgren.gtca.common.data.GTCAMaterials.*;
import static net.mordgren.gtca.common.data.GTCAMaterials.HastelloyN;

public class MiscRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        cellRecipes(provider);
    }

    public static void cellRecipes(Consumer<FinishedRecipe> provider) {
        ///PROTON CELL
        ASSEMBLY_LINE_RECIPES.recipeBuilder("proton_cell_empty")
                .inputItems(GTCAHelper.getItem("plate", Nitinol60, 16))
                .inputItems(GTCAHelper.getItem("cableGtOctal", YttriumBariumCuprate, 32))
                .inputItems(GTCAHelper.getItem("gear", Nitinol60, 4))
                .inputItems(GTItems.VOLTAGE_COIL_EV.asStack(64))
                .inputFluids(Zeron182.getFluid(1152))
                .inputFluids(HastelloyN.getFluid(2304))
                .inputFluids(LafiumCompound.getFluid(2304))
                .outputItems(GTCAItems.PROTON_CELL_EMPTY.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTCAItems.UnknownParticle.asStack())
                        .duration(1125)
                        .EUt(VA[EV]))
                .duration(2400).EUt(32768)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("proton_cell")
                .inputItems(GTCAItems.PROTON_CELL_EMPTY.asStack())
                .inputItems(GTCAItems.Proton.asStack(16))
                .inputItems(GTCAHelper.getItem("plate", Zeron182, 16))
                .inputItems(CustomTags.LuV_CIRCUITS, 8)
                .inputItems(GTCAHelper.getItem("wireGtHex", YttriumBariumCuprate, 32))
                .inputItems(GTCAHelper.getItem("bolt", TriniumNaquadahCarbonite, 8))
                .inputItems(GTCAHelper.getItem("screw", Nitinol60, 8))
                .inputItems(GTItems.ENERGIUM_DUST.asStack(16))
                .inputFluids(Zeron182.getFluid(2304))
                .inputFluids(HastelloyN.getFluid(4608))
                .inputFluids(LafiumCompound.getFluid(2304))
                .outputItems(GTCAItems.PROTON_CELL.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTCAItems.Proton.asStack())
                        .duration(1125)
                        .EUt(VA[EV]))
                .duration(2400).EUt(32768)
                .save(provider);

        CANNER_RECIPES.recipeBuilder("proton_capacitor").duration(200).EUt(VA[EV])
                .inputItems(GTBlocks.BATTERY_EMPTY_TIER_II.asStack())
                .inputItems(GTCAItems.PROTON_CELL.asStack())
                .outputItems(GTCABlocks.BATTERY_PROTON_CELL).save(provider);
        PACKER_RECIPES.recipeBuilder("proton_capacitor").duration(200).EUt(30).circuitMeta(2)
                .inputItems(GTCABlocks.BATTERY_PROTON_CELL)
                .outputItems(GTBlocks.BATTERY_EMPTY_TIER_II.asStack())
                .outputItems(GTCAItems.PROTON_CELL.asStack()).save(provider);

        /// ELECTRON CELL
        ASSEMBLY_LINE_RECIPES.recipeBuilder("electron_cell_empty")
                .inputItems(GTCAHelper.getItem("plate", Zeron182, 16))
                .inputItems(GTCAHelper.getItem("cableGtOctal", Naquadah, 32))
                .inputItems(GTCAHelper.getItem("gear", Zeron182, 4))
                .inputItems(GTItems.VOLTAGE_COIL_IV.asStack(64))
                .inputItems(GTItems.ENERGIUM_DUST.asStack(16))
                .inputFluids(Pikyonium64Y.getFluid(1152))
                .inputFluids(CinobiteA241.getFluid(2304))
                .outputItems(GTCAItems.ELECTRON_CELL_EMPTY.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTCAItems.PROTON_CELL_EMPTY.asStack())
                        .duration(1125)
                        .EUt(VA[EV]))
                .duration(2400).EUt(131072)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("electron_cell")
                .inputItems(GTCAItems.ELECTRON_CELL_EMPTY.asStack())
                .inputItems(GTCAItems.Electron.asStack(16))
                .inputItems(GTCAHelper.getItem("plate", Pikyonium64Y, 16))
                .inputItems(CustomTags.ZPM_CIRCUITS, 16)
                .inputItems(GTCAHelper.getItem("wireGtHex", Naquadah, 32))
                .inputItems(GTCAHelper.getItem("bolt", NaquadahAlloy, 8))
                .inputItems(GTCAHelper.getItem("screw", Zeron182, 8))
                .inputItems(GTItems.ENERGIUM_DUST.asStack(32))
                .inputFluids(Pikyonium64Y.getFluid(2304))
                .inputFluids(CinobiteA241.getFluid(2304))
                .inputFluids(TriniumNaquadahCarbonite.getFluid(2304))
                .outputItems(GTCAItems.ELECTRON_CELL.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTCAItems.Electron.asStack())
                        .duration(1125)
                        .EUt(VA[EV]))
                .duration(2400).EUt(131072)
                .save(provider);

        CANNER_RECIPES.recipeBuilder("electron_capacitor").duration(400).EUt(VA[EV])
                .inputItems(GTBlocks.BATTERY_EMPTY_TIER_II.asStack())
                .inputItems(GTCAItems.ELECTRON_CELL.asStack())
                .outputItems(GTCABlocks.BATTERY_ELECTRON_CELL).save(provider);
        PACKER_RECIPES.recipeBuilder("electron_capacitor").duration(200).EUt(30).circuitMeta(2)
                .inputItems(GTCABlocks.BATTERY_ELECTRON_CELL)
                .outputItems(GTBlocks.BATTERY_EMPTY_TIER_II.asStack())
                .outputItems(GTCAItems.ELECTRON_CELL.asStack()).save(provider);
    }
}
