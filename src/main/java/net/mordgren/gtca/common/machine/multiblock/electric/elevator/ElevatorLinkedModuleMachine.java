package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.misc.EnergyContainerList;

import java.util.List;

/*
Created by sensesgone
25.1.2026
 */

public abstract class ElevatorLinkedModuleMachine extends WorkableElectricMultiblockMachine implements IElevatorModule {

    private static final long WIRELESS_AMPERAGE = 64L;
    private static final long BUFFER_RESERVE_TICKS = 20L * 5L;

    protected final int moduleTier;
    protected boolean enabledByElevator = false;

    protected final NotifiableEnergyContainer wirelessEnergy;
    protected final EnergyContainerList wirelessEnergyList;

    private TickableSubscription gateSub = null;

    protected ElevatorLinkedModuleMachine(IMachineBlockEntity holder, int moduleTier) {
        super(holder);

        this.moduleTier = moduleTier;
        this.tier = moduleTier;

        long capacity = computeBufferCapacity(moduleTier);
        long voltage = getVoltageForTier(moduleTier);
        long amperage = computeWirelessAmperage(moduleTier);

        this.wirelessEnergy = NotifiableEnergyContainer.receiverContainer(
                this,
                capacity,
                voltage,
                amperage
        );
        this.wirelessEnergy.setSideInputCondition(side -> false);
        this.wirelessEnergy.setSideOutputCondition(side -> false);

        attachTraits(wirelessEnergy);

        this.wirelessEnergyList = new EnergyContainerList(List.of(wirelessEnergy));
        this.energyContainer = wirelessEnergyList;

        this.recipeLogic.setWorkingEnabled(false);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (!isRemote() && gateSub == null) {
            gateSub = subscribeServerTick(() -> {
                if (!enabledByElevator) {
                    recipeLogic.setWorkingEnabled(false);

                    if (recipeLogic.isActive()) {
                        recipeLogic.setStatus(RecipeLogic.Status.SUSPEND);
                    }
                }
            });
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        stopGateTick();
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();

        enabledByElevator = false;
        recipeLogic.setWorkingEnabled(false);

        if (recipeLogic.isActive()) {
            recipeLogic.setStatus(RecipeLogic.Status.SUSPEND);
        }
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();


        this.energyContainer = wirelessEnergyList;
        this.tier = moduleTier;

        if (getLevel() != null && !getLevel().isClientSide) {
            recipeLogic.setWorkingEnabled(enabledByElevator);
        }
    }

    private void stopGateTick() {
        if (gateSub != null) {
            gateSub.unsubscribe();
            gateSub = null;
        }
    }

    @Override
    public void setEnabledByElevator(boolean enabled) {
        this.enabledByElevator = enabled;

        if (getLevel() == null || getLevel().isClientSide) {
            return;
        }

        if (enabled) {
            recipeLogic.setWorkingEnabled(true);

            if (recipeLogic.isSuspend()) {
                if (recipeLogic.getLastRecipe() != null && recipeLogic.getMaxProgress() > 0) {
                    recipeLogic.setStatus(RecipeLogic.Status.WORKING);
                } else {
                    recipeLogic.setStatus(RecipeLogic.Status.IDLE);
                }
            }
            return;
        }

        recipeLogic.setWorkingEnabled(false);

        if (recipeLogic.isActive()) {
            recipeLogic.setStatus(RecipeLogic.Status.SUSPEND);
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

    public int getModuleTier() {
        return moduleTier;
    }

    public int getModuleMk() {
        return switch (moduleTier) {
            case GTValues.LuV -> 1;
            case GTValues.ZPM -> 2;
            case GTValues.UV -> 3;
            default -> 1;
        };
    }

    @Override
    public EnergyContainerList getEnergyContainer() {

        if (wirelessEnergyList != null) {
            return wirelessEnergyList;
        }
        return super.getEnergyContainer();
    }

    protected long getWirelessVoltage() {
        return getVoltageForTier(moduleTier);
    }

    protected long getWirelessMaxEUt() {
        return getWirelessVoltage() * computeWirelessAmperage(moduleTier);
    }

    @Override
    public long getMaxVoltage() {
        return getWirelessVoltage();
    }

    @Override
    public long getDisplayRecipeVoltage() {
        return getWirelessVoltage();
    }

    @Override
    public long getOverclockVoltage() {
        return getWirelessVoltage();
    }

    @Override
    public int getTier() {
        return moduleTier;
    }

    @Override
    public int getOverclockTier() {
        return moduleTier;
    }

    @Override
    public int getMaxOverclockTier() {
        return moduleTier;
    }

    @Override
    public int getMinOverclockTier() {
        return moduleTier;
    }

    @Override
    public void setOverclockTier(int tier) {

    }

    protected long computeWirelessAmperage(int tier) {

        return WIRELESS_AMPERAGE;
    }

    protected long computeBufferCapacity(int tier) {
        long maxEUt = getVoltageForTier(tier) * computeWirelessAmperage(tier);
        return maxEUt * BUFFER_RESERVE_TICKS;
    }

    protected long getVoltageForTier(int tier) {
        if (tier >= 0 && tier < GTValues.V.length) {
            return GTValues.V[tier];
        }
        return GTValues.V[GTValues.LuV];
    }
}
