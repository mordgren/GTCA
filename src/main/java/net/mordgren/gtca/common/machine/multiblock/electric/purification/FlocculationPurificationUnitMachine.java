package net.mordgren.gtca.common.machine.multiblock.electric.purification;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.mordgren.gtca.GTCA;

import java.util.ArrayList;
import java.util.List;


public class FlocculationPurificationUnitMachine extends PurificationUnitMachine {

    public static final int PAC_INPUT_INDEX = 1;
    public static final int WASTE_OUTPUT_INDEX = 1;

    public static final long CHEMICAL_PER_LEVEL = 100_000L;
    public static final int SUCCESS_PER_LEVEL_BP = 1_000; // +10.00%
    public static final int CONSUME_INTERVAL_TICKS = 20;

    private static final ManagedFieldHolder FLOCCULATION_MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            FlocculationPurificationUnitMachine.class,
            PurificationUnitMachine.MANAGED_FIELD_HOLDER
    );

    @Persisted
    @DescSynced
    private long inputChemicalConsumed = 0L;

    @Persisted
    @DescSynced
    private int purificationTicks = 0;

    @Persisted
    @DescSynced
    private boolean chemicalDrainFailed = false;

    @Persisted
    @DescSynced
    private long lastWasteOutputAmount = 0L;

    public FlocculationPurificationUnitMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return FLOCCULATION_MANAGED_FIELD_HOLDER;
    }

    @Override
    protected int getWaterTier() {
        return 3;
    }

    @Override
    protected void onPurificationCycleStart(GTRecipe recipe) {
        FluidIngredient pac = getRecipePACIngredient(recipe);
        if (pac == null || pac.isEmpty()) {
            return;
        }


        inputChemicalConsumed = Math.max(0L, pac.getAmount());
        consumeAllAvailablePAC(recipe);
    }

    @Override
    protected void onPurificationCycleTick(GTRecipe recipe) {
        purificationTicks++;
        if (purificationTicks % CONSUME_INTERVAL_TICKS == 0) {
            consumeAllAvailablePAC(recipe);
        }
    }

    private void consumeAllAvailablePAC(GTRecipe recipe) {
        FluidIngredient pac = getRecipePACIngredient(recipe);
        if (pac == null || pac.isEmpty()) return;

        long available = countMatchingInputFluid(pac);
        if (available <= 0L) return;

        long remaining = available;
        while (remaining > 0L) {
            int chunk = (int) Math.min((long) Integer.MAX_VALUE, remaining);
            if (!consumeInputFluid(recipe, pac, chunk)) {
                GTCA.LOGGER.error(
                        "[WaterLine/Flocculation] Failed to consume {} L PAC; forcing cycle failure.",
                        chunk
                );
                chemicalDrainFailed = true;
                return;
            }
            inputChemicalConsumed += chunk;
            remaining -= chunk;
        }
    }

    @Override
    protected boolean determineCycleSuccess(GTRecipe recipe) {
        if (chemicalDrainFailed) {
            setSuccessChanceBasisPoints(0);
            return false;
        }

        setSuccessChanceBasisPoints(calculateFinalChanceBasisPoints());
        return super.determineCycleSuccess(recipe);
    }

    @Override
    protected int getDisplayedSuccessChanceBasisPoints() {
        if (recipeLogic.isActive()) {
            return calculateFinalChanceBasisPoints();
        }
        return successChanceBasisPoints;
    }

    public int calculateFinalChanceBasisPoints() {
        int levels = getBoostLevels();
        long overflow = getOverflowAmount();

        int beforePenalty = clampChance(successChanceBasisPoints + levels * SUCCESS_PER_LEVEL_BP);
        if (overflow <= 0L) return beforePenalty;

        double overflowRatio = overflow / (double) CHEMICAL_PER_LEVEL;
        double penaltyMultiplier = Math.pow(2.0, -10.0 * overflowRatio);
        return clampChance((int) Math.round(beforePenalty * penaltyMultiplier));
    }

    @Override
    GTRecipe createCompletionRecipe(GTRecipe runningRecipe, boolean success) {
        int levels = getBoostLevels();
        long wasteAmount = levels * CHEMICAL_PER_LEVEL;
        lastWasteOutputAmount = wasteAmount;

        Content wasteTemplate = getWasteOutputTemplate(runningRecipe);
        if (wasteAmount <= 0L || wasteTemplate == null) {
            return super.createCompletionRecipe(runningRecipe, success);
        }

        GTRecipe completion = runningRecipe.copy();

        FluidIngredient waste = FluidRecipeCapability.CAP.of(wasteTemplate.content).copy();
        waste.setAmount((int) Math.min((long) Integer.MAX_VALUE, wasteAmount));

        Content dynamicWaste = new Content(
                waste,
                wasteTemplate.chance,
                wasteTemplate.maxChance,
                wasteTemplate.tierChanceBoost
        );

        if (!success) {
            completion.outputs.clear();
            completion.outputs.put(FluidRecipeCapability.CAP, new ArrayList<>(List.of(dynamicWaste)));
            return completion;
        }

        List<Content> fluidOutputs = new ArrayList<>(
                completion.outputs.getOrDefault(FluidRecipeCapability.CAP, List.of())
        );

        if (fluidOutputs.size() > WASTE_OUTPUT_INDEX) {
            fluidOutputs.set(WASTE_OUTPUT_INDEX, dynamicWaste);
        } else {
            fluidOutputs.add(dynamicWaste);
        }

        completion.outputs.put(FluidRecipeCapability.CAP, fluidOutputs);
        return completion;
    }

    private FluidIngredient getRecipePACIngredient(GTRecipe recipe) {
        if (recipe == null) return null;
        var fluidInputs = recipe.inputs.get(FluidRecipeCapability.CAP);
        if (fluidInputs == null || fluidInputs.size() <= PAC_INPUT_INDEX) return null;
        return FluidRecipeCapability.CAP.of(fluidInputs.get(PAC_INPUT_INDEX).content);
    }

    private Content getWasteOutputTemplate(GTRecipe recipe) {
        if (recipe == null) return null;
        var fluidOutputs = recipe.outputs.get(FluidRecipeCapability.CAP);
        if (fluidOutputs == null || fluidOutputs.size() <= WASTE_OUTPUT_INDEX) return null;
        return fluidOutputs.get(WASTE_OUTPUT_INDEX);
    }

    public int getBoostLevels() {
        return (int) Math.min(Integer.MAX_VALUE, inputChemicalConsumed / CHEMICAL_PER_LEVEL);
    }

    public long getOverflowAmount() {
        return inputChemicalConsumed % CHEMICAL_PER_LEVEL;
    }

    public double getOverflowRatio() {
        return getOverflowAmount() / (double) CHEMICAL_PER_LEVEL;
    }

    public double getOverflowPenaltyMultiplier() {
        long overflow = getOverflowAmount();
        if (overflow <= 0L) return 1.0;
        return Math.pow(2.0, -10.0 * getOverflowRatio());
    }

    public long getInputChemicalConsumed() {
        return inputChemicalConsumed;
    }

    public long getLastWasteOutputAmount() {
        return lastWasteOutputAmount;
    }

    @Override
    protected void resetPurificationState() {
        inputChemicalConsumed = 0L;
        purificationTicks = 0;
        chemicalDrainFailed = false;
        lastWasteOutputAmount = 0L;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        textList.add(Component.literal("PAC consumed this cycle: " + inputChemicalConsumed + " L")
                .withStyle(ChatFormatting.GRAY));
        textList.add(Component.literal(
                "Boost levels: " + getBoostLevels() + " ( +" + (getBoostLevels() * 10) + "% )"
        ).withStyle(ChatFormatting.GREEN));

        if (getOverflowAmount() > 0L) {
            textList.add(Component.literal(String.format(
                    "Overflow: %d L | penalty x%.5f",
                    getOverflowAmount(),
                    getOverflowPenaltyMultiplier()
            )).withStyle(ChatFormatting.RED));
        } else {
            textList.add(Component.literal("Overflow: 0 L | no penalty")
                    .withStyle(ChatFormatting.GRAY));
        }

        textList.add(Component.literal("Projected waste: " +
                (getBoostLevels() * CHEMICAL_PER_LEVEL) + " L")
                .withStyle(ChatFormatting.DARK_AQUA));
    }
}
