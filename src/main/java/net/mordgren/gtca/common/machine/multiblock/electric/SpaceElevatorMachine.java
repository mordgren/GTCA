package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import net.mordgren.gtca.common.data.GTCABlocks;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorEnergyManager;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleManager;

import com.gregtechceu.gtceu.api.misc.EnergyContainerList;

import java.util.Collection;
import java.util.List;

public class SpaceElevatorMachine extends WorkableElectricMultiblockMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(
                    SpaceElevatorMachine.class,
                    WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER
            );

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Persisted
    @DescSynced
    private int motorTier = 1;


    @Persisted
    @DescSynced
    private int unlockedModuleSlots = 6;


    @Persisted
    @DescSynced
    private int modulesFound = 0;

    @Persisted
    @DescSynced
    private int modulesValid = 0;

    @Persisted
    @DescSynced
    private int modulesActive = 0;

    private final ElevatorModuleManager moduleManager = new ElevatorModuleManager(this);
    private final ElevatorEnergyManager energyManager = new ElevatorEnergyManager(this);

    private TickableSubscription elevatorTickSub = null;
    private int scanTimer = 0;

    public SpaceElevatorMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        scanTimer = 0;

        if (!isRemote()) {
            rescanAll();

            if (elevatorTickSub == null) {
                elevatorTickSub = subscribeServerTick(this::serverTickElevator);
            }
        }
    }

    @Override
    public void onStructureInvalid() {
        if (!isRemote()) {
            moduleManager.disableAllModulesInSlots();
        }

        unsubscribe(elevatorTickSub);
        elevatorTickSub = null;
        scanTimer = 0;

        motorTier = 1;
        unlockedModuleSlots = 0;
        modulesFound = 0;
        modulesValid = 0;
        modulesActive = 0;

        super.onStructureInvalid();
    }

    @Override
    public void onUnload() {
        unsubscribe(elevatorTickSub);
        elevatorTickSub = null;
        scanTimer = 0;

        super.onUnload();
    }


    private void serverTickElevator() {
        if (getLevel() == null) return;
        if (getLevel().isClientSide) return;
        if (!isFormed()) return;

        energyManager.tickEnergyDistribution();

        scanTimer++;
        if (scanTimer >= 20) {
            scanTimer = 0;
            rescanAll();
        }
    }


    private void rescanAll() {
        if (getLevel() == null) return;
        if (getLevel().isClientSide) return;
        if (!isFormed()) return;

        recalcMotorTierAndSlots();

        moduleManager.rescanSlots(unlockedModuleSlots);
        moduleManager.applyEnabling();

        modulesFound = moduleManager.getModulesFound();
        modulesValid = moduleManager.getModulesValid();
        modulesActive = moduleManager.getModulesActive();
    }


    public EnergyContainerList getEnergyContainerSafe() {
        if (this.energyContainer == null) {
            this.energyContainer = getEnergyContainer();
        }
        return this.energyContainer;
    }

    public int getMotorTier() {
        return motorTier;
    }

    public int getUnlockedModuleSlots() {
        return unlockedModuleSlots;
    }

    public int getModulesFound() {
        return modulesFound;
    }

    public int getModulesValid() {
        return modulesValid;
    }

    public int getModulesActive() {
        return modulesActive;
    }

    public List<ElevatorModuleManager.SlotInfo> getSlotInfos() {
        return moduleManager.getSlotInfos();
    }

    private void recalcMotorTierAndSlots() {
        int newMotorTier = computeMotorTierFromStructure();

        motorTier = newMotorTier;
        unlockedModuleSlots = slotsForMotorTier(newMotorTier);
    }


    private int computeMotorTierFromStructure() {
        if (getLevel() == null) return 1;
        if (!isFormed()) return 1;

        Collection<BlockPos> cache = getStructureCacheSafe();
        if (cache == null || cache.isEmpty()) return 1;

        int minTier = Integer.MAX_VALUE;
        boolean foundAnyMotor = false;

        for (BlockPos pos : cache) {
            Block block = getLevel().getBlockState(pos).getBlock();
            int tier = getMotorBlockTier(block);

            if (tier <= 0) continue;

            foundAnyMotor = true;
            if (tier < minTier) {
                minTier = tier;
            }
        }

        return foundAnyMotor ? minTier : 1;
    }

    private Collection<BlockPos> getStructureCacheSafe() {
        try {
            if (getMultiblockState() == null) return null;
            return getMultiblockState().getCache();
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static int getMotorBlockTier(Block block) {
        if (block == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK1.get()) return 1;
        if (block == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK2.get()) return 2;
        if (block == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK3.get()) return 3;
        if (block == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK4.get()) return 4;
        if (block == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK5.get()) return 5;

        return 0;
    }

    public static int slotsForMotorTier(int tier) {
        return switch (tier) {
            case 1 -> 6;
            case 2 -> 12;
            case 3 -> 15;
            case 4 -> 18;
            case 5 -> 24;
            default -> 6;
        };
    }
}