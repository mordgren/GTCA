package net.mordgren.gtca.common.machine.multiblock.electric.purification;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.mordgren.gtca.GTCA;

import java.util.List;

/**
 * Water Tier 2 - Ozonation Purification Unit.
 *
 * Port rule used by GTCA:
 * - fluid input #0 = water / previous purification tier
 * - fluid input #1 = Ozone Gas
 * - every full doubling of the recipe's base Ozone amount adds +20% success
 * - only the largest complete doubled amount is consumed
 * - more than 1,024,000 L of matching Ozone in the inputs explodes the machine
 *
 * We intentionally identify Ozone by the recipe's second fluid ingredient
 * instead of hardcoding a Material, because GTCEu 7.5.3 itself has no Ozone
 * material and GTCA's real Water Line materials/recipes can be supplied later.
 */
public class OzonationPurificationUnitMachine extends PurificationUnitMachine implements IExplosionMachine {

    public static final int OZONE_INPUT_INDEX = 1;
    public static final int OZONE_BONUS_PER_DOUBLING_BP = 2_000; // +20.00%
    public static final long MAX_SAFE_OZONE = 1_024_000L;
    public static final float OZONE_EXPLOSION_POWER = 6.0f;

    private static final ManagedFieldHolder OZONATION_MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            OzonationPurificationUnitMachine.class,
            PurificationUnitMachine.MANAGED_FIELD_HOLDER
    );

    @Persisted
    @DescSynced
    private int ozoneBaseAmount = 0;

    @Persisted
    @DescSynced
    private int ozoneEffectiveAmount = 0;

    @Persisted
    @DescSynced
    private int ozoneDoublings = 0;

    public OzonationPurificationUnitMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return OZONATION_MANAGED_FIELD_HOLDER;
    }

    @Override
    protected boolean onPurificationCyclePreStart(GTRecipe recipe) {
        FluidIngredient ozone = getRecipeOzoneIngredient(recipe);
        if (ozone == null || ozone.isEmpty() || ozone.getAmount() <= 0) {
            return true;
        }

        ozoneBaseAmount = ozone.getAmount();
        long available = countMatchingInputFluid(ozone);

        // GTNH tooltip: MORE than 1,024,000 L is unsafe. Exactly 1,024,000 L is safe.
        if (available > MAX_SAFE_OZONE) {
            GTCA.LOGGER.warn(
                    "[WaterLine/Ozonation] Unsafe Ozone amount: {} L > {} L. Exploding machine.",
                    available,
                    MAX_SAFE_OZONE
            );

            if (getLevel() != null && !getLevel().isClientSide) {
                doExplosion(OZONE_EXPLOSION_POWER);
            }
            return false;
        }

        long effective = ozoneBaseAmount;
        int doublings = 0;

        while (effective <= MAX_SAFE_OZONE / 2L && available >= effective * 2L) {
            effective *= 2L;
            doublings++;
        }

        ozoneEffectiveAmount = (int) Math.min(Integer.MAX_VALUE, effective);
        ozoneDoublings = doublings;
        return true;
    }

    @Override
    protected void onPurificationCycleStart(GTRecipe recipe) {
        if (ozoneBaseAmount <= 0 || ozoneEffectiveAmount <= 0) return;

        FluidIngredient ozone = getRecipeOzoneIngredient(recipe);
        if (ozone == null || ozone.isEmpty()) return;


        int extraOzone = ozoneEffectiveAmount - ozoneBaseAmount;
        if (extraOzone > 0 && !consumeInputFluid(recipe, ozone, extraOzone)) {
            GTCA.LOGGER.error(
                    "[WaterLine/Ozonation] Failed to consume {} L extra Ozone after recipe start; forcing cycle failure.",
                    extraOzone
            );
            setSuccessChanceBasisPoints(0);
            return;
        }

        addSuccessChanceBasisPoints(ozoneDoublings * OZONE_BONUS_PER_DOUBLING_BP);
    }

    private FluidIngredient getRecipeOzoneIngredient(GTRecipe recipe) {
        if (recipe == null) return null;

        var fluidInputs = recipe.inputs.get(FluidRecipeCapability.CAP);
        if (fluidInputs == null || fluidInputs.size() <= OZONE_INPUT_INDEX) {
            return null;
        }

        return FluidRecipeCapability.CAP.of(fluidInputs.get(OZONE_INPUT_INDEX).content);
    }

    @Override
    protected int getWaterTier() {
        return 2;
    }

    @Override
    protected void resetPurificationState() {
        ozoneBaseAmount = 0;
        ozoneEffectiveAmount = 0;
        ozoneDoublings = 0;
    }

    public int getOzoneBaseAmount() {
        return ozoneBaseAmount;
    }

    public int getOzoneEffectiveAmount() {
        return ozoneEffectiveAmount;
    }

    public int getOzoneDoublings() {
        return ozoneDoublings;
    }

    public double getOzoneBonusPercent() {
        return ozoneDoublings * 20.0;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        if (ozoneBaseAmount > 0) {
            textList.add(Component.literal(
                    "Ozone base: " + ozoneBaseAmount + " L | used: " + ozoneEffectiveAmount + " L"
            ).withStyle(ChatFormatting.GRAY));
            textList.add(Component.literal(String.format(
                    "Ozone bonus: +%.0f%% (%d doubling%s)",
                    getOzoneBonusPercent(),
                    ozoneDoublings,
                    ozoneDoublings == 1 ? "" : "s"
            )).withStyle(ChatFormatting.GREEN));
        } else {
            textList.add(Component.literal("Ozone bonus: waiting for recipe")
                    .withStyle(ChatFormatting.GRAY));
        }

        textList.add(Component.literal("Safety limit: <= 1,024,000 L Ozone")
                .withStyle(ChatFormatting.RED));
    }

}
