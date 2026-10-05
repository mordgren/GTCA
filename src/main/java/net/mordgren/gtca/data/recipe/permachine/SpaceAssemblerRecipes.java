package net.mordgren.gtca.data.recipe.permachine;

import com.gregtechceu.gtceu.api.GTValues;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.SpaceAssemblerMachine;

import java.util.function.Consumer;

public final class SpaceAssemblerRecipes {

    private SpaceAssemblerRecipes() {}

    public static void init(Consumer<FinishedRecipe> provider) {

    }

    public static void add(Consumer<FinishedRecipe> provider,
                           String id,
                           int requiredMk,
                           int duration,
                           ItemStack output,
                           ItemStack[] itemInputs,
                           FluidStack[] fluidInputs) {
        add(provider, id, requiredMk, duration, GTValues.V[tierForMk(requiredMk)], output, itemInputs, fluidInputs);
    }

    public static void add(Consumer<FinishedRecipe> provider,
                           String id,
                           int requiredMk,
                           int duration,
                           long eut,
                           ItemStack output,
                           ItemStack[] itemInputs,
                           FluidStack[] fluidInputs) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Space Assembler recipe id is empty");
        }
        if (output == null || output.isEmpty()) {
            GTCA.LOGGER.warn("[SpaceAssembler] Skipped recipe {} because output is empty", id);
            return;
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Space Assembler duration must be > 0 for " + id);
        }
        if (eut <= 0) {
            throw new IllegalArgumentException("Space Assembler EU/t must be > 0 for " + id);
        }

        var builder = GTCARecipeTypes.SPACE_ASSEMBLER.recipeBuilder("space_assembler_" + id)
                .EUt(eut)
                .duration(duration)
                .outputItems(output)
                .addData(SpaceAssemblerMachine.REQUIRED_MODULE_MK_KEY, requiredMk);

        if (itemInputs != null && itemInputs.length > 0) {
            builder.inputItems(itemInputs);
        }

        if (fluidInputs != null && fluidInputs.length > 0) {
            builder.inputFluids(fluidInputs);
        }

        builder.save(provider);
    }

    private static int tierForMk(int mk) {
        return switch (mk) {
            case 1 -> GTValues.LuV;
            case 2 -> GTValues.ZPM;
            case 3 -> GTValues.UV;
            default -> throw new IllegalArgumentException("Unsupported Space Assembler MK: " + mk);
        };
    }
}
