package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

public abstract class ElevatorLinkedModuleMachine extends WorkableElectricMultiblockMachine implements IElevatorModule {

    @Persisted
    @DescSynced
    protected boolean enabledByElevator = false;

    // запоминаем, что игрок хотел (working enabled) до того как лифт выключил модуль
    @Persisted
    protected boolean cachedPlayerWorkingEnabled = true;

    private TickableSubscription gateSub;

    protected ElevatorLinkedModuleMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (gateSub == null) {
            gateSub = subscribeServerTick(this::enforceElevatorGate);
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        gateSub = null;
    }

    @Override
    public void setEnabledByElevator(boolean enabled) {
        if (this.enabledByElevator == enabled) return;
        this.enabledByElevator = enabled;

        // только сервер
        if (getLevel() == null || getLevel().isClientSide) return;

        if (!enabled) {
            // запоминаем, что игрок хотел, и гасим работу
            cachedPlayerWorkingEnabled = recipeLogic.isWorkingEnabled();
            recipeLogic.setWorkingEnabled(false);
        } else {
            // возвращаем желание игрока (а не всегда true)
            recipeLogic.setWorkingEnabled(cachedPlayerWorkingEnabled);
        }
    }

    @Override
    public boolean isEnabledByElevator() {
        return enabledByElevator;
    }

    private void enforceElevatorGate() {
        if (getLevel() == null || getLevel().isClientSide) return;

        if (!enabledByElevator) {
            // игрок мог попытаться снова включить через GUI — запрещаем
            if (recipeLogic.isWorkingEnabled()) {
                recipeLogic.setWorkingEnabled(false);
            }
        }
    }
}
