package net.mordgren.gtca.data.recipe.builder;

import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.SpaceAssemblerMachine;

public final class SpaceAssemblerRecipeBuilder {

    private SpaceAssemblerRecipeBuilder() {
    }

    public static GTRecipeBuilder mk1(String id) {
        return create(id, 1);
    }

    public static GTRecipeBuilder mk2(String id) {
        return create(id, 2);
    }

    public static GTRecipeBuilder mk3(String id) {
        return create(id, 3);
    }

    public static GTRecipeBuilder create(String id, int requiredMk) {
        if (requiredMk < 1 || requiredMk > 3) {
            throw new IllegalArgumentException(
                    "Space Assembler MK must be between 1 and 3, got: " + requiredMk
            );
        }

        return GTCARecipeTypes.SPACE_ASSEMBLER
                .recipeBuilder("space_assembler_" + id)
                .addData(
                        SpaceAssemblerMachine.REQUIRED_MODULE_MK_KEY,
                        requiredMk
                );
    }
}
