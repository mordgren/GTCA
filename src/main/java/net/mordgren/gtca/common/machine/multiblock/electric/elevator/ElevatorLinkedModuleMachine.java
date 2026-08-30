package net.mordgren.gtca.common.machine.multiblock.electric.elevator;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;

/*
Created by sensesgone
25.1.2026
 */
    public abstract class ElevatorLinkedModuleMachine extends WorkableElectricMultiblockMachine implements IElevatorModule {

        protected final int moduleTier;
        protected boolean enabledByElevator = false;

        protected final NotifiableEnergyContainer wirelessEnergy;

        private TickableSubscription gateSub = null;

        protected ElevatorLinkedModuleMachine(IMachineBlockEntity holder, int moduleTier) {
            super(holder);

            this.moduleTier = moduleTier;

            long cap = computeBufferCapacity(moduleTier);
            long maxV = GTValues.VEX[moduleTier];

            this.wirelessEnergy = NotifiableEnergyContainer.receiverContainer(this, cap, maxV, 64);


            this.wirelessEnergy.setSideInputCondition(side -> false);
            this.wirelessEnergy.setSideOutputCondition(side -> false);

            attachTraits(this.wirelessEnergy);


            this.recipeLogic.setWorkingEnabled(false);
        }

        @Override
        public void onLoad() {
            super.onLoad();

            if (!isRemote() && gateSub == null) {
                gateSub = subscribeServerTick(() -> {
                    if (!enabledByElevator && !recipeLogic.isSuspend()) {
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

        protected long computeBufferCapacity(int tier) {
            int mk = switch (tier) {
                case GTValues.LuV -> 1;
                case GTValues.ZPM -> 2;
                case GTValues.UV  -> 3;
                default -> 1;
            };

            long base = 786_432L;
            long cap = base;

            for (int i = 1; i < mk; i++) {
                cap *= 4L;
            }

            // TEMP TEST:
            return cap * 10L;
        }
    }

