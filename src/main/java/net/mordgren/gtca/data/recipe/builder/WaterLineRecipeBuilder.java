package net.mordgren.gtca.data.recipe.builder;

import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.purification.ClarifierPurificationUnitMachine;
import net.mordgren.gtca.common.machine.multiblock.electric.purification.PurificationUnitMachine;
import net.mordgren.gtca.common.machine.multiblock.electric.purification.data.PurificationRecipeDataKeys;


public final class WaterLineRecipeBuilder {

    public static final int FIXED_CYCLE_TICKS = 20 * 120;

    private WaterLineRecipeBuilder() {}

    public static GTRecipeBuilder phNeutralization(String id) {
        return base(
                GTCARecipeTypes.PH_NEUTRALIZATION_PURIFICATION_UNIT,
                id,
                0
        );
    }

    public static GTRecipeBuilder clarifier(String id, int baseSuccessChanceBP) {
        return base(GTCARecipeTypes.CLARIFIER_PURIFICATION_UNIT, id, baseSuccessChanceBP);
    }

    public static GTRecipeBuilder ozonation(String id, int baseSuccessChanceBP) {
        return base(GTCARecipeTypes.OZONATION_PURIFICATION_UNIT, id, baseSuccessChanceBP);
    }

    public static GTRecipeBuilder flocculation(String id, int baseSuccessChanceBP) {
        return base(GTCARecipeTypes.FLOCCULATION_PURIFICATION_UNIT, id, baseSuccessChanceBP);
    }

    public static GTRecipeBuilder activatedCarbonFilter(GTRecipeBuilder builder, ItemStack filter) {
        return builder.chancedInput(filter, ClarifierPurificationUnitMachine.FILTER_CONSUME_CHANCE, 0);
    }

    private static GTRecipeBuilder base(GTRecipeType type, String id, int baseSuccessChanceBP) {
        int chance = Math.max(0, Math.min(PurificationUnitMachine.CHANCE_SCALE, baseSuccessChanceBP));
        return type.recipeBuilder(id)
                .duration(FIXED_CYCLE_TICKS)
                .addData(PurificationRecipeDataKeys.BASE_SUCCESS_CHANCE_BP, chance);
    }
}
