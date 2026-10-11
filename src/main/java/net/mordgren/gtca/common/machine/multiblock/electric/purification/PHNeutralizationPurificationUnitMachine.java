package net.mordgren.gtca.common.machine.multiblock.electric.purification;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import net.mordgren.gtca.common.machine.multiblock.part.PHAcidDosingHatchMachine;
import net.mordgren.gtca.common.machine.multiblock.part.PHBaseDosingHatchMachine;
import net.mordgren.gtca.common.machine.multiblock.part.PHSensorHatchMachine;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class PHNeutralizationPurificationUnitMachine extends PurificationUnitMachine {

    public static final int PH_MIN_INITIAL = 450;
    public static final int PH_MAX_INITIAL = 950;

    public static final int PH_TARGET = 700;
    public static final int PH_SUCCESS_MIN = 695;
    public static final int PH_SUCCESS_MAX = 705;

    public static final int PH_ABSOLUTE_MIN = 0;
    public static final int PH_ABSOLUTE_MAX = 1400;

    public static final int REACTION_INTERVAL_TICKS = 20;

    private static final ManagedFieldHolder PH_MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(
                    PHNeutralizationPurificationUnitMachine.class,
                    PurificationUnitMachine.MANAGED_FIELD_HOLDER
            );

    @Persisted
    @DescSynced
    private int phHundredths = PH_TARGET;

    @Persisted
    @DescSynced
    private int startingPHHundredths = PH_TARGET;

    @Persisted
    @DescSynced
    private int purificationTicks = 0;

    @Persisted
    @DescSynced
    private boolean phCycleActive = false;

    @Persisted
    @DescSynced
    private boolean safetyTripped = false;

    @Persisted
    @DescSynced
    private boolean autoDosingEnabled = false;

    @Nullable
    private PHSensorHatchMachine phSensor;

    @Nullable
    private PHBaseDosingHatchMachine baseDoser;

    @Nullable
    private PHAcidDosingHatchMachine acidDoser;

    public PHNeutralizationPurificationUnitMachine(
            IMachineBlockEntity holder,
            Object... args
    ) {
        super(holder, args);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return PH_MANAGED_FIELD_HOLDER;
    }

    @Override
    protected int getWaterTier() {
        return 4;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        phSensor = null;
        baseDoser = null;
        acidDoser = null;

        for (var part : getParts()) {

            if (part instanceof PHSensorHatchMachine sensor) {
                phSensor = sensor;
                continue;
            }

            if (part instanceof PHBaseDosingHatchMachine base) {
                baseDoser = base;
                continue;
            }

            if (part instanceof PHAcidDosingHatchMachine acid) {
                acidDoser = acid;
            }
        }

        notifySensor();
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();

        phSensor = null;
        baseDoser = null;
        acidDoser = null;
    }

    @Override
    protected void onPurificationCycleStart(GTRecipe recipe) {
        purificationTicks = 0;
        safetyTripped = false;
        phCycleActive = true;

        if (getLevel() != null) {
            phHundredths =
                    PH_MIN_INITIAL +
                            getLevel().random.nextInt(
                                    PH_MAX_INITIAL - PH_MIN_INITIAL + 1
                            );
        } else {
            phHundredths = PH_TARGET;
        }

        startingPHHundredths = phHundredths;

        notifySensor();
    }

    @Override
    protected void onPurificationCycleTick(GTRecipe recipe) {
        if (!phCycleActive || safetyTripped) {
            return;
        }

        purificationTicks++;

        if (purificationTicks % REACTION_INTERVAL_TICKS != 0) {
            return;
        }

        if (autoDosingEnabled) {
            performAutomaticDosing();
        } else {
            performManualDosing();
        }

        checkSafetyLimits();

        notifySensor();
    }

    @Override
    protected boolean determineCycleSuccess(GTRecipe recipe) {

        if (safetyTripped) {
            setSuccessChanceBasisPoints(0);
            return false;
        }

        boolean success =
                phHundredths >= PH_SUCCESS_MIN &&
                        phHundredths <= PH_SUCCESS_MAX;

        setSuccessChanceBasisPoints(
                success ? CHANCE_SCALE : 0
        );

        return success;
    }

    @Override
    protected int getDisplayedSuccessChanceBasisPoints() {
        if (!phCycleActive) {
            return successChanceBasisPoints;
        }

        return isPHInSuccessRange()
                ? CHANCE_SCALE
                : 0;
    }

    @Override
    protected void onPurificationCycleResolved(
            GTRecipe recipe,
            boolean success
    ) {
        phCycleActive = false;

        notifySensor();
    }

    @Override
    protected void resetPurificationState() {
        purificationTicks = 0;
        phCycleActive = false;
        safetyTripped = false;
        notifySensor();
    }

    private void performManualDosing() {

        int consumedBase = 0;
        int consumedAcid = 0;

        if (baseDoser != null) {
            int storedBase = baseDoser.getStoredAmount();

            if (storedBase > 0) {
                consumedBase = baseDoser.consume(storedBase);
            }
        }

        if (acidDoser != null) {
            int storedAcid = acidDoser.getStoredAmount();

            if (storedAcid >= 10) {
                consumedAcid = acidDoser.consume(storedAcid);
            }
        }


        phHundredths += consumedBase;


        phHundredths -= consumedAcid / 10;
    }

    private void performAutomaticDosing() {

        if (phHundredths < PH_TARGET) {
            autoDoseBase();
        } else if (phHundredths > PH_TARGET) {
            autoDoseAcid();
        }
    }


    private void autoDoseBase() {

        if (baseDoser == null) {
            return;
        }

        int requiredDust =
                PH_TARGET - phHundredths;

        if (requiredDust <= 0) {
            return;
        }

        int consumed =
                baseDoser.consume(requiredDust);

        phHundredths += consumed;
    }

    private void autoDoseAcid() {

        if (acidDoser == null) {
            return;
        }

        int requiredSteps =
                phHundredths - PH_TARGET;

        if (requiredSteps <= 0) {
            return;
        }

        int requiredAcid =
                requiredSteps * 10;

        int consumed =
                acidDoser.consume(requiredAcid);

        phHundredths -= consumed / 10;
    }

    private void checkSafetyLimits() {

        if (phHundredths >= PH_ABSOLUTE_MIN &&
                phHundredths <= PH_ABSOLUTE_MAX) {
            return;
        }

        safetyTrip();
    }

    private void safetyTrip() {

        safetyTripped = true;
        phCycleActive = false;

        setSuccessChanceBasisPoints(0);

        lastCycleSucceeded = false;
        hasCompletedCycle = true;

        notifySensor();

        recipeLogic.interruptRecipe();
        recipeLogic.setWorkingEnabled(false);
    }


    public int getCurrentPHHundredths() {
        return phHundredths;
    }

    public double getCurrentPH() {
        return phHundredths / 100.0;
    }

    public int getStartingPHHundredths() {
        return startingPHHundredths;
    }

    public boolean isPHCycleActive() {
        return phCycleActive;
    }

    public boolean isSafetyTripped() {
        return safetyTripped;
    }

    public boolean isPHInSuccessRange() {
        return phHundredths >= PH_SUCCESS_MIN &&
                phHundredths <= PH_SUCCESS_MAX;
    }

    public boolean isAutoDosingEnabled() {
        return autoDosingEnabled;
    }

    public void setAutoDosingEnabled(boolean enabled) {
        autoDosingEnabled = enabled;
    }

    private void notifySensor() {
        if (phSensor != null) {
            phSensor.onPHChanged();
        }
    }

    @Override
    protected InteractionResult onScrewdriverClick(
            Player playerIn,
            InteractionHand hand,
            Direction gridSide,
            BlockHitResult hitResult
    ) {
        if (!isRemote()) {
            autoDosingEnabled = !autoDosingEnabled;

            playerIn.displayClientMessage(
                    Component.literal(
                            "pH Auto Dosing: " +
                                    (autoDosingEnabled ? "ENABLED" : "DISABLED")
                    ).withStyle(
                            autoDosingEnabled
                                    ? ChatFormatting.GREEN
                                    : ChatFormatting.YELLOW
                    ),
                    true
            );
        }

        return InteractionResult.sidedSuccess(
                playerIn.level().isClientSide
        );
    }


    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        textList.add(
                Component.literal(String.format(
                        Locale.ROOT,
                        "Current pH: %.2f",
                        getCurrentPH()
                )).withStyle(getPHColor())
        );

        if (phCycleActive) {

            textList.add(
                    Component.literal(String.format(
                            Locale.ROOT,
                            "Starting pH: %.2f",
                            startingPHHundredths / 100.0
                    )).withStyle(ChatFormatting.GRAY)
            );

            textList.add(
                    Component.literal(
                            "pH state: " + getPHStateText()
                    ).withStyle(getPHColor())
            );

        } else if (safetyTripped) {

            textList.add(
                    Component.literal(
                            "SAFETY TRIP: pH outside 0.00 - 14.00"
                    ).withStyle(
                            ChatFormatting.RED,
                            ChatFormatting.BOLD
                    )
            );

            textList.add(
                    Component.literal(
                            "Use a Soft Mallet to re-enable the machine."
                    ).withStyle(ChatFormatting.RED)
            );
        }

        textList.add(
                Component.literal(
                        "Auto dosing: " +
                                (autoDosingEnabled ? "ENABLED" : "DISABLED")
                ).withStyle(
                        autoDosingEnabled
                                ? ChatFormatting.GREEN
                                : ChatFormatting.YELLOW
                )
        );

        if (baseDoser != null) {
            textList.add(
                    Component.literal(
                            "NaOH available: " +
                                    baseDoser.getStoredAmount() +
                                    " items"
                    ).withStyle(ChatFormatting.GRAY)
            );
        } else {
            textList.add(
                    Component.literal(
                            "NaOH Dosing Hatch: MISSING"
                    ).withStyle(ChatFormatting.RED)
            );
        }

        if (acidDoser != null) {
            textList.add(
                    Component.literal(
                            "HCl available: " +
                                    acidDoser.getStoredAmount() +
                                    " L"
                    ).withStyle(ChatFormatting.GRAY)
            );
        } else {
            textList.add(
                    Component.literal(
                            "HCl Dosing Hatch: MISSING"
                    ).withStyle(ChatFormatting.RED)
            );
        }

        if (phSensor != null) {
            textList.add(
                    Component.literal(
                            "pH Sensor Redstone: " +
                                    phSensor.getSensorSignal()
                    ).withStyle(ChatFormatting.DARK_AQUA)
            );
        } else {
            textList.add(
                    Component.literal(
                            "pH Sensor: not installed"
                    ).withStyle(ChatFormatting.DARK_GRAY)
            );
        }
    }

    private String getPHStateText() {

        if (phHundredths < PH_SUCCESS_MIN) {
            return "ACIDIC - ADD BASE";
        }

        if (phHundredths > PH_SUCCESS_MAX) {
            return "ALKALINE - ADD ACID";
        }

        return "NEUTRAL";
    }

    private ChatFormatting getPHColor() {

        if (phHundredths < PH_SUCCESS_MIN) {
            return ChatFormatting.RED;
        }

        if (phHundredths > PH_SUCCESS_MAX) {
            return ChatFormatting.AQUA;
        }

        return ChatFormatting.GREEN;
    }
}
