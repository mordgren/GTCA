package net.mordgren.gtca.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;

import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.core.Direction;
import net.minecraft.util.Mth;

import net.mordgren.gtca.common.machine.multiblock.electric.purification.IThermalControlSource;

import org.jetbrains.annotations.Nullable;

public class ThermalControlHatchMachine extends MultiblockPartMachine {

    public static final int MIN_ACTIVE_SIGNAL = 7;
    public static final int MAX_ACTIVE_SIGNAL = 15;

    public static final int SENSOR_MAX_TEMPERATURE = 12_500;

    public ThermalControlHatchMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    // ------------------------------------------------------------
    // CONTROLLER
    // ------------------------------------------------------------

    @Nullable
    public IThermalControlSource getThermalController() {
        for (IMultiController controller : getControllers()) {
            if (controller instanceof IThermalControlSource thermal) {
                return thermal;
            }
        }

        return null;
    }

    // ------------------------------------------------------------
    // DATA
    // ------------------------------------------------------------

    public int getCurrentTemperatureK() {
        IThermalControlSource controller = getThermalController();

        return controller == null
                ? 0
                : controller.getCurrentTemperatureK();
    }

    public int getCompletedCycles() {
        IThermalControlSource controller = getThermalController();

        return controller == null
                ? 0
                : controller.getCompletedThermalCycles();
    }

    public String getThermalState() {
        IThermalControlSource controller = getThermalController();

        if (controller == null) {
            return "NO CONTROLLER";
        }

        if (controller.isBatchRuined()) {
            return "RUINED";
        }

        if (!controller.isThermalCycleActive()) {
            return "IDLE";
        }

        return controller.isCoolingPhase()
                ? "COOLING"
                : "HEATING";
    }

    // ------------------------------------------------------------
    // REDSTONE SENSOR
    // ------------------------------------------------------------

    public int getSensorSignal() {
        IThermalControlSource controller = getThermalController();

        if (controller == null || !controller.isThermalCycleActive()) {
            return 0;
        }

        int temperature = Mth.clamp(
                controller.getCurrentTemperatureK(),
                0,
                SENSOR_MAX_TEMPERATURE
        );

        double normalized =
                temperature / (double) SENSOR_MAX_TEMPERATURE;

        int signal =
                MIN_ACTIVE_SIGNAL +
                        (int) Math.round(
                                normalized *
                                        (MAX_ACTIVE_SIGNAL - MIN_ACTIVE_SIGNAL)
                        );

        return Mth.clamp(
                signal,
                MIN_ACTIVE_SIGNAL,
                MAX_ACTIVE_SIGNAL
        );
    }

    public void onThermalDataChanged() {
        if (!isRemote()) {
            updateSignal();
        }
    }

    @Override
    public int getOutputSignal(@Nullable Direction side) {
        return getSensorSignal();
    }

    @Override
    public int getOutputDirectSignal(Direction direction) {
        return getSensorSignal();
    }

    @Override
    public int getAnalogOutputSignal() {
        return getSensorSignal();
    }

    @Override
    public boolean canConnectRedstone(Direction side) {
        return true;
    }

    // ------------------------------------------------------------
    // UI
    // ------------------------------------------------------------

    @Override
    public Widget createUIWidget() {
        WidgetGroup group = new WidgetGroup(0, 0, 120, 50);

        group.addWidget(
                new LabelWidget(
                        8,
                        7,
                        () -> "Temperature: " +
                                getCurrentTemperatureK() +
                                " K"
                )
        );

        group.addWidget(
                new LabelWidget(
                        8,
                        21,
                        () -> "State: " +
                                getThermalState()
                )
        );

        group.addWidget(
                new LabelWidget(
                        8,
                        35,
                        () -> "Cycles: " +
                                getCompletedCycles()
                )
        );

        return group;
    }

    @Override
    public boolean canShared() {
        return false;
    }
}
