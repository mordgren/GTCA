package net.mordgren.gtca.common.machine.multiblock.electric.purification;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import net.mordgren.gtca.common.machine.multiblock.part.ThermalControlHatchMachine;
import net.mordgren.gtca.common.machine.multiblock.part.ThermalFluidInputHatchMachine;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExtremeTemperaturePurificationUnitMachine
        extends PurificationUnitMachine
        implements IThermalControlSource {

    public static final int CONSUME_INTERVAL_TICKS = 20;

    public static final int MAX_PLASMA_PER_SECOND = 10;
    public static final int MAX_COOLANT_PER_SECOND = 100;

    public static final int PLASMA_TEMP_PER_LITER = 100;
    public static final int COOLANT_TEMP_PER_LITER = -5;

    public static final int HEATING_POINT = 10_000;
    public static final int MAX_SAFE_TEMPERATURE = 12_500;

    public static final int SUCCESS_PER_CYCLE_BP = 3_300; // +33%
    public static final int STEAM_MULTIPLIER = 60;

    private static final ManagedFieldHolder EXTREME_TEMP_MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(
                    ExtremeTemperaturePurificationUnitMachine.class,
                    PurificationUnitMachine.MANAGED_FIELD_HOLDER
            );

    @Persisted
    @DescSynced
    private int currentTemperatureK = 0;

    @Persisted
    @DescSynced
    private int completedThermalCycles = 0;

    @Persisted
    @DescSynced
    private int purificationTicks = 0;

    @Persisted
    @DescSynced
    private boolean coolingPhase = false;

    @Persisted
    @DescSynced
    private boolean ruinedCycle = false;

    @Persisted
    @DescSynced
    private boolean thermalCycleActive = false;

    @Persisted
    @DescSynced
    private int lastPlasmaConsumed = 0;

    @Persisted
    @DescSynced
    private int lastCoolantConsumed = 0;

    @Nullable
    private ThermalFluidInputHatchMachine plasmaHatch;

    @Nullable
    private ThermalFluidInputHatchMachine coolantHatch;

    @Nullable
    private ThermalControlHatchMachine thermalSensor;

    public ExtremeTemperaturePurificationUnitMachine(
            IMachineBlockEntity holder,
            Object... args
    ) {
        super(holder, args);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return EXTREME_TEMP_MANAGED_FIELD_HOLDER;
    }

    @Override
    protected int getWaterTier() {
        return 5;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        plasmaHatch = null;
        coolantHatch = null;
        thermalSensor = null;

        for (var part : getParts()) {

            if (part instanceof ThermalFluidInputHatchMachine hatch) {

                switch (hatch.getKind()) {
                    case HELIUM_PLASMA ->
                            plasmaHatch = hatch;

                    case SUPER_COOLANT ->
                            coolantHatch = hatch;
                }

                continue;
            }

            if (part instanceof ThermalControlHatchMachine sensor) {
                thermalSensor = sensor;
            }
        }

        notifyThermalParts();
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();

        plasmaHatch = null;
        coolantHatch = null;
        thermalSensor = null;
    }

    @Override
    protected void onPurificationCycleStart(GTRecipe recipe) {
        currentTemperatureK = 0;
        completedThermalCycles = 0;
        purificationTicks = 0;

        coolingPhase = false;
        ruinedCycle = false;
        thermalCycleActive = true;

        lastPlasmaConsumed = 0;
        lastCoolantConsumed = 0;

        notifyThermalParts();
    }

    @Override
    protected void onPurificationCycleTick(GTRecipe recipe) {
        if (!thermalCycleActive) {
            return;
        }

        purificationTicks++;

        if (purificationTicks % CONSUME_INTERVAL_TICKS != 0) {
            return;
        }

        lastPlasmaConsumed =
                plasmaHatch == null
                        ? 0
                        : plasmaHatch.consumeUpTo(MAX_PLASMA_PER_SECOND);

        lastCoolantConsumed =
                coolantHatch == null
                        ? 0
                        : coolantHatch.consumeUpTo(MAX_COOLANT_PER_SECOND);

        int temperatureDelta =
                lastPlasmaConsumed * PLASMA_TEMP_PER_LITER +
                        lastCoolantConsumed * COOLANT_TEMP_PER_LITER;

        currentTemperatureK =
                Math.max(
                        0,
                        currentTemperatureK + temperatureDelta
                );

        if (currentTemperatureK > MAX_SAFE_TEMPERATURE) {
            ruinedCycle = true;
        }


        if (!coolingPhase) {

            if (currentTemperatureK >= HEATING_POINT) {
                coolingPhase = true;
            }

        } else {

            if (currentTemperatureK == 0) {
                completedThermalCycles++;
                coolingPhase = false;
            }
        }

        notifyThermalParts();
    }

    @Override
    protected boolean determineCycleSuccess(GTRecipe recipe) {
        if (ruinedCycle) {
            setSuccessChanceBasisPoints(0);
            return false;
        }

        setSuccessChanceBasisPoints(
                calculateProjectedSuccessChance()
        );

        return super.determineCycleSuccess(recipe);
    }

    @Override
    protected int getDisplayedSuccessChanceBasisPoints() {
        if (thermalCycleActive) {
            return calculateProjectedSuccessChance();
        }

        return successChanceBasisPoints;
    }

    private int calculateProjectedSuccessChance() {
        return clampChance(
                successChanceBasisPoints +
                        completedThermalCycles * SUCCESS_PER_CYCLE_BP
        );
    }

    @Override
    protected void onPurificationCycleResolved(
            GTRecipe recipe,
            boolean success
    ) {
        thermalCycleActive = false;
        notifyThermalParts();
    }

    @Override
    protected void resetPurificationState() {
        currentTemperatureK = 0;
        completedThermalCycles = 0;
        purificationTicks = 0;

        coolingPhase = false;
        ruinedCycle = false;
        thermalCycleActive = false;

        lastPlasmaConsumed = 0;
        lastCoolantConsumed = 0;

        notifyThermalParts();
    }

    @Override
    GTRecipe createCompletionRecipe(
            GTRecipe runningRecipe,
            boolean success
    ) {
        if (!ruinedCycle) {
            return super.createCompletionRecipe(
                    runningRecipe,
                    success
            );
        }

        GTRecipe completion = runningRecipe.copy();

        completion.outputs.clear();

        int waterInputAmount =
                getPrimaryWaterInputAmount(runningRecipe);

        if (waterInputAmount <= 0) {
            return completion;
        }

        long calculatedSteam =
                (long) waterInputAmount * STEAM_MULTIPLIER;

        int steamAmount =
                (int) Math.min(
                        Integer.MAX_VALUE,
                        calculatedSteam
                );

        FluidIngredient steam =
                FluidIngredient.of(
                        GTMaterials.Steam.getFluid(steamAmount)
                );

        int maxChance =
                ChanceLogic.getMaxChancedValue();

        Content steamOutput =
                new Content(
                        steam,
                        maxChance,
                        maxChance,
                        0
                );

        completion.outputs.put(
                FluidRecipeCapability.CAP,
                new ArrayList<>(List.of(steamOutput))
        );

        return completion;
    }

    private int getPrimaryWaterInputAmount(GTRecipe recipe) {
        if (recipe == null) {
            return 0;
        }

        var fluidInputs =
                recipe.inputs.get(
                        FluidRecipeCapability.CAP
                );

        if (fluidInputs == null ||
                fluidInputs.isEmpty()) {
            return 0;
        }

        FluidIngredient water =
                FluidRecipeCapability.CAP.of(
                        fluidInputs.get(0).content
                );

        return Math.max(
                0,
                water.getAmount()
        );
    }

    @Override
    public int getCurrentTemperatureK() {
        return currentTemperatureK;
    }

    @Override
    public boolean isThermalCycleActive() {
        return thermalCycleActive;
    }

    @Override
    public boolean isCoolingPhase() {
        return coolingPhase;
    }

    @Override
    public boolean isBatchRuined() {
        return ruinedCycle;
    }

    @Override
    public int getCompletedThermalCycles() {
        return completedThermalCycles;
    }

    private void notifyThermalParts() {

        if (plasmaHatch != null) {
            plasmaHatch.onThermalStateChanged();
        }

        if (coolantHatch != null) {
            coolantHatch.onThermalStateChanged();
        }

        if (thermalSensor != null) {
            thermalSensor.onThermalDataChanged();
        }
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        textList.add(
                Component.literal(
                        "Temperature: " +
                                currentTemperatureK +
                                " K"
                ).withStyle(
                        getTemperatureColor()
                )
        );

        textList.add(
                Component.literal(
                        "Thermal State: " +
                                getThermalStateText()
                ).withStyle(
                        ruinedCycle
                                ? ChatFormatting.RED
                                : ChatFormatting.YELLOW
                )
        );

        textList.add(
                Component.literal(
                        "Completed Cycles: " +
                                completedThermalCycles
                ).withStyle(ChatFormatting.AQUA)
        );

        if (thermalCycleActive) {
            textList.add(
                    Component.literal(
                            "Plasma: " +
                                    lastPlasmaConsumed +
                                    " L/s | Coolant: " +
                                    lastCoolantConsumed +
                                    " L/s"
                    ).withStyle(ChatFormatting.GRAY)
            );
        }

        if (ruinedCycle) {
            textList.add(
                    Component.literal(
                            "BATCH RUINED - OUTPUT WILL BE STEAM"
                    ).withStyle(
                            ChatFormatting.RED,
                            ChatFormatting.BOLD
                    )
            );
        }
    }

    private String getThermalStateText() {
        if (ruinedCycle) {
            return "RUINED";
        }

        if (!thermalCycleActive) {
            return "IDLE";
        }

        return coolingPhase
                ? "COOLING"
                : "HEATING";
    }

    private ChatFormatting getTemperatureColor() {
        if (currentTemperatureK > MAX_SAFE_TEMPERATURE) {
            return ChatFormatting.RED;
        }

        if (currentTemperatureK >= 11_000) {
            return ChatFormatting.GOLD;
        }

        if (currentTemperatureK >= HEATING_POINT) {
            return ChatFormatting.YELLOW;
        }

        return ChatFormatting.AQUA;
    }
}
