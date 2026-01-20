package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule;

public class SpacePumpMachine extends WorkableElectricMultiblockMachine implements ITieredMachine, IElevatorModule {

    public final int tier;
    private boolean enabledByElevator = false;

    public SpacePumpMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.tier = tier;
    }

    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return ElevatorModuleKind.PUMP;
    }
    private boolean cachedPlayerWorkingEnabled = true;
    @Override
    public void setEnabledByElevator(boolean enabled) {
        if (this.enabledByElevator == enabled) return;
        this.enabledByElevator = enabled;

        if (getLevel() == null || getLevel().isClientSide) return;

        if (!enabled) {

            cachedPlayerWorkingEnabled = recipeLogic.isWorkingEnabled();
            recipeLogic.setWorkingEnabled(false);
        } else {

            recipeLogic.setWorkingEnabled(cachedPlayerWorkingEnabled);
        }
    }

    @Override
    public boolean isEnabledByElevator() {
        return enabledByElevator;
    }

    @Override
    public boolean requiresComputation() {
        return false;
    }
    private TickableSubscription elevatorGateSub;

    @Override
    public void onLoad() {
        super.onLoad();
        if (elevatorGateSub == null) {
            elevatorGateSub = subscribeServerTick(() -> {
                if (!enabledByElevator && recipeLogic.isWorkingEnabled()) {
                    recipeLogic.setWorkingEnabled(false);
                }
            });
        }
    }
    @Override
    public void onUnload() {
        super.onUnload();
        elevatorGateSub = null;
    }

}






