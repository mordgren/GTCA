package net.mordgren.gtca.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;

import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.core.Direction;

import net.mordgren.gtca.common.machine.multiblock.electric.purification.PHNeutralizationPurificationUnitMachine;

import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class PHSensorHatchMachine extends MultiblockPartMachine {

    public static final int SIGNAL_ACIDIC = 0;
    public static final int SIGNAL_HOLD = 7;
    public static final int SIGNAL_ALKALINE = 15;

    public PHSensorHatchMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public boolean canShared() {
        return false;
    }

    @Nullable
    public PHNeutralizationPurificationUnitMachine getPHController() {
        for (IMultiController controller : getControllers()) {
            if (controller instanceof PHNeutralizationPurificationUnitMachine ph) {
                return ph;
            }
        }

        return null;
    }

    public int getCurrentPHHundredths() {
        PHNeutralizationPurificationUnitMachine controller = getPHController();

        if (controller == null) {
            return 700;
        }

        return controller.getCurrentPHHundredths();
    }

    public double getCurrentPH() {
        return getCurrentPHHundredths() / 100.0;
    }

    public int getSensorSignal() {
        PHNeutralizationPurificationUnitMachine controller = getPHController();

        // Safe idle state.
        if (controller == null || !controller.isPHCycleActive()) {
            return SIGNAL_HOLD;
        }

        int ph = controller.getCurrentPHHundredths();

        if (ph < 695) {
            return SIGNAL_ACIDIC;
        }

        if (ph > 705) {
            return SIGNAL_ALKALINE;
        }

        return SIGNAL_HOLD;
    }

    public String getPHStatus() {
        PHNeutralizationPurificationUnitMachine controller = getPHController();

        if (controller == null) {
            return "NO CONTROLLER";
        }

        if (!controller.isPHCycleActive()) {
            return "IDLE";
        }

        int ph = controller.getCurrentPHHundredths();

        if (ph < 695) {
            return "ACIDIC - ADD BASE";
        }

        if (ph > 705) {
            return "ALKALINE - ADD ACID";
        }

        return "NEUTRAL";
    }

    public void onPHChanged() {
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
    public boolean canConnectRedstone(Direction side) {
        return true;
    }

    @Override
    public Widget createUIWidget() {
        WidgetGroup group = new WidgetGroup(0, 0, 130, 55);

        group.addWidget(new LabelWidget(
                5, 5,
                () -> String.format(
                        Locale.ROOT,
                        "Current pH: %.2f",
                        getCurrentPH()
                )
        ));

        group.addWidget(new LabelWidget(
                5, 18,
                () -> "Status: " + getPHStatus()
        ));

        group.addWidget(new LabelWidget(
                5, 31,
                () -> "Redstone: " + getSensorSignal()
        ));

        group.addWidget(new LabelWidget(
                5, 44,
                "Target: 7.00 +/- 0.05"
        ));

        return group;
    }
}
