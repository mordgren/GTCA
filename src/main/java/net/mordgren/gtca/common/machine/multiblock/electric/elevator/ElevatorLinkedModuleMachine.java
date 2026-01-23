package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;

public abstract class ElevatorLinkedModuleMachine extends WorkableElectricMultiblockMachine implements IElevatorModule {

    protected final int moduleTier;
    protected boolean enabledByElevator = false;

    protected final NotifiableEnergyContainer wirelessEnergy;
    private TickableSubscription gateSub = null;

    protected ElevatorLinkedModuleMachine(IMachineBlockEntity holder, int moduleTier) {
        super(holder);
        this.moduleTier = moduleTier;

        long cap = computeBufferCapacity(moduleTier);
        long maxV = GTValues.VEX[moduleTier]; // чтобы tier вычислялся красиво
        this.wirelessEnergy = NotifiableEnergyContainer.receiverContainer(this, cap, maxV, 64);


        attachTraits(this.wirelessEnergy);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (gateSub == null) {
            gateSub = subscribeServerTick(() -> {
                if (!enabledByElevator && recipeLogic.isWorkingEnabled()) {
                    recipeLogic.setWorkingEnabled(false);
                }
            });
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        gateSub = null;
    }

    @Override
    public void setEnabledByElevator(boolean enabled) {
        this.enabledByElevator = enabled;

        if (getLevel() != null && !getLevel().isClientSide) {
            recipeLogic.setWorkingEnabled(enabled);
        }
    }

    @Override
    public boolean isEnabledByElevator() {
        return enabledByElevator;
    }

    @Override
    public NotifiableEnergyContainer getWirelessEnergyContainer() {
        return wirelessEnergy;
    }

    protected long computeBufferCapacity(int tier) {
        int mk = switch (tier) {
            case GTValues.LuV -> 1;
            case GTValues.ZPM -> 2;
            case GTValues.UV  -> 3;
            default -> 1;
        };

        long base = 786_432L;
        long cap = base;
        for (int i = 1; i < mk; i++) cap *= 4L;
        return cap;
    }
}
