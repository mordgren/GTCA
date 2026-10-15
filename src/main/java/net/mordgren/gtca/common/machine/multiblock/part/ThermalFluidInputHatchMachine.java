package net.mordgren.gtca.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.TankWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.machine.multiblock.electric.purification.IThermalControlSource;

import org.jetbrains.annotations.Nullable;

public class ThermalFluidInputHatchMachine extends MultiblockPartMachine {

    public static final int CAPACITY = 128_000;

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(
                    ThermalFluidInputHatchMachine.class,
                    MultiblockPartMachine.MANAGED_FIELD_HOLDER
            );

    public enum Kind {
        HELIUM_PLASMA("Helium Plasma"),
        SUPER_COOLANT("Super Coolant");

        private final String displayName;

        Kind(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() {
            return displayName;
        }
    }

    private final Kind kind;

    @Persisted
    private final NotifiableFluidTank tank;

    public ThermalFluidInputHatchMachine(
            IMachineBlockEntity holder,
            Kind kind
    ) {
        super(holder);

        this.kind = kind;

        tank = new NotifiableFluidTank(
                this,
                1,
                CAPACITY,
                IO.NONE,
                IO.IN
        );

        tank.setFilter(this::isValidFluid);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public Kind getKind() {
        return kind;
    }

    public NotifiableFluidTank getTank() {
        return tank;
    }

    // ------------------------------------------------------------
    // Controller
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
    // Fluid
    // ------------------------------------------------------------

    private boolean isValidFluid(FluidStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }

        FluidStack target = getTargetFluid(1);

        return !target.isEmpty()
                && stack.isFluidEqual(target);
    }

    private FluidStack getTargetFluid(int amount) {
        return switch (kind) {
            case HELIUM_PLASMA ->
                    GTMaterials.Helium.getFluid(
                            FluidStorageKeys.PLASMA,
                            amount
                    );

            case SUPER_COOLANT ->
                    GTCAMaterials.SuperCoolant.getFluid(amount);
        };
    }

    public int getStoredAmount() {
        return tank.getFluidInTank(0).getAmount();
    }

    public int consumeUpTo(int requested) {
        if (requested <= 0) {
            return 0;
        }

        int amount = Math.min(
                requested,
                getStoredAmount()
        );

        if (amount <= 0) {
            return 0;
        }

        FluidStack request =
                getTargetFluid(amount);

        FluidStack drained =
                tank.drainInternal(
                        request,
                        IFluidHandler.FluidAction.EXECUTE
                );

        return drained.getAmount();
    }

    // ------------------------------------------------------------
    // Demand signal
    // ------------------------------------------------------------

    public int getDemandSignal() {
        IThermalControlSource controller =
                getThermalController();

        if (controller == null) {
            return 0;
        }

        if (!controller.isThermalCycleActive()) {
            return 0;
        }

        if (controller.isBatchRuined()) {
            return 0;
        }

        return switch (kind) {
            case HELIUM_PLASMA ->
                    controller.isCoolingPhase()
                            ? 0
                            : 15;

            case SUPER_COOLANT ->
                    controller.isCoolingPhase()
                            ? 15
                            : 0;
        };
    }

    public void onThermalStateChanged() {
        if (!isRemote()) {
            updateSignal();
        }
    }

    @Override
    public int getOutputSignal(@Nullable net.minecraft.core.Direction side) {
        return getDemandSignal();
    }

    @Override
    public int getOutputDirectSignal(net.minecraft.core.Direction direction) {
        return getDemandSignal();
    }

    @Override
    public int getAnalogOutputSignal() {
        return getDemandSignal();
    }

    @Override
    public boolean canConnectRedstone(net.minecraft.core.Direction side) {
        return true;
    }

    // ------------------------------------------------------------
    // UI
    // ------------------------------------------------------------


    @Override
    public Widget createUIWidget() {
        WidgetGroup group = new WidgetGroup(0, 0, 60, 46);

        group.addWidget(
                new TankWidget(
                        tank.getStorages()[0],
                        21,
                        14,
                        18,
                        18,
                        true,
                        true
                )
                        .setShowAmount(true)
                        .setDrawHoverTips(true)
                        .setBackground(GuiTextures.FLUID_SLOT)
        );

        group.setBackground(GuiTextures.BACKGROUND_INVERSE);

        return group;
    }

    @Override
    public boolean canShared() {
        return false;
    }
}
