package net.mordgren.gtca.common.machine.multiblock.electric.purification;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import net.mordgren.gtca.common.machine.multiblock.electric.purification.data.PurificationRecipeDataKeys;

import java.util.List;

public abstract class PurificationUnitMachine extends WorkableElectricMultiblockMachine {

    public static final int CHANCE_SCALE = 10_000;

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            PurificationUnitMachine.class,
            WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER
    );

    @Persisted
    @DescSynced
    protected int successChanceBasisPoints = CHANCE_SCALE;

    @Persisted
    @DescSynced
    protected boolean lastCycleSucceeded = true;

    @Persisted
    @DescSynced
    protected boolean hasCompletedCycle = false;

    protected PurificationUnitMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    protected RecipeLogic createRecipeLogic(Object... args) {
        return new PurificationRecipeLogic(this);
    }

    protected abstract int getWaterTier();

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        resetPurificationState();
        successChanceBasisPoints = CHANCE_SCALE;
    }

    final boolean preparePurificationCycle(GTRecipe recipe) {
        resetPurificationState();
        return onPurificationCyclePreStart(recipe);
    }

    final void abortPurificationPreparation() {
        resetPurificationState();
        successChanceBasisPoints = CHANCE_SCALE;
    }

    final void beginPurificationCycle(GTRecipe recipe) {
        successChanceBasisPoints = readBaseSuccessChance(recipe);
        onPurificationCycleStart(recipe);
    }

    final void tickPurificationCycle(GTRecipe recipe) {
        onPurificationCycleTick(recipe);
    }

    final boolean resolvePurificationCycle(GTRecipe recipe) {
        lastCycleSucceeded = determineCycleSuccess(recipe);
        hasCompletedCycle = true;
        onPurificationCycleResolved(recipe, lastCycleSucceeded);
        return lastCycleSucceeded;
    }

    GTRecipe createCompletionRecipe(GTRecipe runningRecipe, boolean success) {
        if (success) return runningRecipe;

        GTRecipe failed = runningRecipe.copy();
        failed.outputs.clear();
        return failed;
    }

    protected boolean onPurificationCyclePreStart(GTRecipe recipe) {
        return true;
    }

    protected void onPurificationCycleStart(GTRecipe recipe) {}

    protected void onPurificationCycleTick(GTRecipe recipe) {}

    protected void onPurificationCycleResolved(GTRecipe recipe, boolean success) {}

    /** Reset only unit-specific live-cycle state. Last-cycle information is preserved. */
    protected void resetPurificationState() {}

    protected boolean determineCycleSuccess(GTRecipe recipe) {
        int chance = clampChance(successChanceBasisPoints);
        if (chance <= 0) return false;
        if (chance >= CHANCE_SCALE) return true;
        return getLevel() != null && getLevel().random.nextInt(CHANCE_SCALE) < chance;
    }

    protected int readBaseSuccessChance(GTRecipe recipe) {
        if (recipe == null || recipe.data == null ||
                !recipe.data.contains(PurificationRecipeDataKeys.BASE_SUCCESS_CHANCE_BP)) {
            return CHANCE_SCALE;
        }
        return clampChance(recipe.data.getInt(PurificationRecipeDataKeys.BASE_SUCCESS_CHANCE_BP));
    }


    protected int getDisplayedSuccessChanceBasisPoints() {
        return successChanceBasisPoints;
    }

    public int getSuccessChanceBasisPoints() {
        return successChanceBasisPoints;
    }

    public double getSuccessChancePercent() {
        return successChanceBasisPoints / 100.0;
    }

    public boolean didLastCycleSucceed() {
        return lastCycleSucceeded;
    }

    protected void setSuccessChanceBasisPoints(int value) {
        successChanceBasisPoints = clampChance(value);
    }

    protected void addSuccessChanceBasisPoints(int amount) {
        setSuccessChanceBasisPoints(successChanceBasisPoints + amount);
    }

    protected static int clampChance(int value) {
        return Math.max(0, Math.min(CHANCE_SCALE, value));
    }

    protected long countMatchingInputFluid(FluidIngredient ingredient) {
        if (ingredient == null || ingredient.isEmpty()) return 0L;

        long amount = 0L;
        for (var handler : getCapabilitiesFlat(IO.IN, FluidRecipeCapability.CAP)) {
            if (!handler.shouldSearchContent()) continue;

            for (Object content : handler.getContents()) {
                if (content instanceof FluidStack stack && !stack.isEmpty() && ingredient.test(stack)) {
                    amount += stack.getAmount();
                }
            }
        }
        return amount;
    }

    protected boolean consumeInputFluid(GTRecipe recipe, FluidIngredient ingredient, int amount) {
        if (amount <= 0) return true;
        if (recipe == null || ingredient == null || ingredient.isEmpty()) return false;

        FluidIngredient requested = ingredient.copy();
        requested.setAmount(amount);

        List<?> remaining = List.of(requested);
        for (var handler : getCapabilitiesFlat(IO.IN, FluidRecipeCapability.CAP)) {
            if (!handler.shouldSearchContent()) continue;
            remaining = handler.handleRecipe(IO.IN, recipe, remaining, true);
            if (remaining == null || remaining.isEmpty()) break;
        }
        if (remaining != null && !remaining.isEmpty()) return false;

        remaining = List.of(requested);
        for (var handler : getCapabilitiesFlat(IO.IN, FluidRecipeCapability.CAP)) {
            if (!handler.shouldSearchContent()) continue;
            remaining = handler.handleRecipe(IO.IN, recipe, remaining, false);
            if (remaining == null || remaining.isEmpty()) return true;
        }

        return remaining == null || remaining.isEmpty();
    }

    protected boolean consumeOneNonConsumableItem(GTRecipe recipe) {
        if (recipe == null) return false;

        var itemContents = recipe.inputs.get(ItemRecipeCapability.CAP);
        if (itemContents == null || itemContents.isEmpty()) return false;

        for (var content : itemContents) {
            if (content.chance != 0) continue;

            Ingredient ingredient = ItemRecipeCapability.CAP.of(content.content);
            if (ingredient == null || ingredient.isEmpty()) continue;

            Ingredient oneItem = ingredient instanceof SizedIngredient sized
                    ? SizedIngredient.create(sized.getInner(), 1)
                    : SizedIngredient.create(ingredient, 1);

            for (var handler : getCapabilitiesFlat(IO.IN, ItemRecipeCapability.CAP)) {
                if (!handler.shouldSearchContent()) continue;

                var simulatedLeft = handler.handleRecipe(IO.IN, recipe, List.of(oneItem), true);
                if (simulatedLeft != null && !simulatedLeft.isEmpty()) continue;

                var realLeft = handler.handleRecipe(IO.IN, recipe, List.of(oneItem), false);
                if (realLeft == null || realLeft.isEmpty()) return true;
            }
        }
        return false;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        textList.add(Component.literal("Water Tier: " + getWaterTier())
                .withStyle(ChatFormatting.AQUA));

        if (recipeLogic.isActive()) {
            textList.add(Component.literal(String.format(
                    "Current purification chance: %.2f%%",
                    getDisplayedSuccessChanceBasisPoints() / 100.0
            )).withStyle(ChatFormatting.YELLOW));
        } else if (hasCompletedCycle) {
            textList.add(Component.literal("Last purification cycle: " +
                    (lastCycleSucceeded ? "SUCCESS" : "FAILED"))
                    .withStyle(lastCycleSucceeded ? ChatFormatting.GREEN : ChatFormatting.RED));

            textList.add(Component.literal(String.format(
                    "Last calculated chance: %.2f%%",
                    getDisplayedSuccessChanceBasisPoints() / 100.0
            )).withStyle(ChatFormatting.GRAY));
        } else {
            textList.add(Component.literal("Purification status: waiting for recipe")
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
