package net.mordgren.gtca.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.TankWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class PHAcidDosingHatchMachine extends MultiblockPartMachine {

    public static final int CAPACITY = 128_000;

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(
                    PHAcidDosingHatchMachine.class,
                    MultiblockPartMachine.MANAGED_FIELD_HOLDER
            );

    @Persisted
    public final NotifiableFluidTank tank;

    public PHAcidDosingHatchMachine(IMachineBlockEntity holder) {
        super(holder);

        tank = new NotifiableFluidTank(
                this,
                1,
                CAPACITY,
                IO.NONE,
                IO.IN
        );

        tank.setFilter(this::isHydrochloricAcid);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    private boolean isHydrochloricAcid(FluidStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }

        FluidStack hcl = GTMaterials.HydrochloricAcid.getFluid(1);

        return hcl != null
                && !hcl.isEmpty()
                && stack.isFluidEqual(hcl);
    }

    public int getStoredAmount() {
        return tank.getFluidInTank(0).getAmount();
    }

    public int consume(int requested) {
        if (requested < 10) {
            return 0;
        }

        int available = getStoredAmount();

        int amount = Math.min(requested, available);


        amount = (amount / 10) * 10;

        if (amount <= 0) {
            return 0;
        }

        FluidStack request =
                GTMaterials.HydrochloricAcid.getFluid(amount);

        FluidStack drained = tank.drainInternal(
                request,
                IFluidHandler.FluidAction.EXECUTE
        );

        return drained.getAmount();
    }

    @Override
    public boolean canShared() {
        return false;
    }

    @Override
    public Widget createUIWidget() {
        WidgetGroup group =
                new WidgetGroup(0, 0, 110, 63);

        group.addWidget(
                new ImageWidget(
                        4,
                        4,
                        102,
                        55,
                        GuiTextures.DISPLAY
                )
        );

        TankWidget tankWidget =
                new TankWidget(
                        tank.getStorages()[0],
                        84,
                        22,
                        18,
                        18,
                        true,
                        true
                )
                        .setShowAmount(true)
                        .setDrawHoverTips(true)
                        .setBackground(GuiTextures.FLUID_SLOT);

        group.addWidget(tankWidget);

        group.addWidget(
                new LabelWidget(
                        8,
                        8,
                        "Hydrochloric Acid"
                )
        );

        group.addWidget(
                new LabelWidget(
                        8,
                        22,
                        () -> getStoredAmount() + " L"
                )
        );

        group.addWidget(
                new LabelWidget(
                        8,
                        32,
                        "10 L = -0.01 pH"
                )
        );

        group.setBackground(
                GuiTextures.BACKGROUND_INVERSE
        );

        return group;
    }
}
