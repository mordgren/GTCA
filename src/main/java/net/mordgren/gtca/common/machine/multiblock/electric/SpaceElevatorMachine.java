package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;


import net.mordgren.gtca.common.data.GTCABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpaceElevatorMachine extends WorkableElectricMultiblockMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(SpaceElevatorMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

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

    // modules
    private final List<BlockPos> moduleControllers = new ArrayList<>();
    private int modulesFound = 0;
    private int modulesActive = 0;

    private TickableSubscription rescanSub = null;
    private int rescanTimer = 0;

    public SpaceElevatorMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    // ---------------- lifecycle ----------------

    @Override
    public void onUnload() {
        super.onUnload();

        rescanSub = null;
        rescanTimer = 0;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();


        if (rescanSub == null) {
            rescanSub = subscribeServerTick(this::onServerTickSubscribed);
        }

        recalcMotorTierAndSlotsSafe();
        rebuildModulesSafe();
        applyModuleEnabling();

        rescanTimer = 0;
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();


        disableAllKnownModules();

        motorTier = 1;
        unlockedModuleSlots = 0;

        moduleControllers.clear();
        modulesFound = 0;
        modulesActive = 0;

        rescanTimer = 0;
    }



    private void onServerTickSubscribed() {
        if (getLevel() == null) return;
        if (!isFormed()) return;


        Iterable<BlockPos> cache = safeGetCache();
        if (cache == null) return;

        rescanTimer++;
        if (rescanTimer >= 20) { // раз в секунду
            rescanTimer = 0;

            recalcMotorTierAndSlotsSafe(cache);
            rebuildModulesSafe(cache);
            applyModuleEnabling();
        }
    }


    private Iterable<BlockPos> safeGetCache() {
        try {
            return getMultiblockState().getCache();
        } catch (NullPointerException ignored) {
            return null;
        }
    }

    // ---------------- getters ----------------

    public int getMotorTier() {
        return motorTier;
    }

    public int getUnlockedModuleSlots() {
        return unlockedModuleSlots;
    }

    public int getModulesFound() {
        return modulesFound;
    }

    public int getModulesActive() {
        return modulesActive;
    }

    // ---------------- motor tier ----------------

    private void recalcMotorTierAndSlotsSafe() {
        Iterable<BlockPos> cache = safeGetCache();
        if (cache == null) {
            this.motorTier = 1;
            this.unlockedModuleSlots = slotsForMotorTier(1);
            return;
        }
        recalcMotorTierAndSlotsSafe(cache);
    }

    private void recalcMotorTierAndSlotsSafe(Iterable<BlockPos> cache) {
        int newMotorTier = safeComputeMotorTierMin(cache);
        this.motorTier = newMotorTier;
        this.unlockedModuleSlots = slotsForMotorTier(newMotorTier);
    }

    private int safeComputeMotorTierMin(Iterable<BlockPos> cache) {
        if (getLevel() == null) return 1;
        if (cache == null) return 1;

        int minTier = Integer.MAX_VALUE;
        boolean foundAnyMotor = false;

        for (BlockPos pos : cache) {
            Block block = getLevel().getBlockState(pos).getBlock();
            int tier = tierOfMotorBlock(block);
            if (tier > 0) {
                foundAnyMotor = true;
                if (tier < minTier) minTier = tier;
            }
        }

        return foundAnyMotor ? minTier : 1;
    }

    private static int tierOfMotorBlock(Block b) {
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK1.get()) return 1;
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK2.get()) return 2;
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK3.get()) return 3;
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK4.get()) return 4;
        if (b == GTCABlocks.SPACE_ELEVATOR_MOTOR_MK5.get()) return 5;
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

    // ---------------- modules scan/enabling ----------------

    private void rebuildModulesSafe() {
        Iterable<BlockPos> cache = safeGetCache();
        if (cache == null) {
            moduleControllers.clear();
            modulesFound = 0;
            modulesActive = 0;
            return;
        }
        rebuildModulesSafe(cache);
    }

    private void rebuildModulesSafe(Iterable<BlockPos> cache) {
        moduleControllers.clear();

        if (getLevel() == null || !isFormed()) {
            modulesFound = 0;
            modulesActive = 0;
            return;
        }
        if (cache == null) {
            modulesFound = 0;
            modulesActive = 0;
            return;
        }

        for (BlockPos pos : cache) {
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);
            if (isElevatorModuleController(mm)) {
                moduleControllers.add(pos.immutable());
            }
        }

        moduleControllers.sort(Comparator.comparingLong(BlockPos::asLong));
        modulesFound = moduleControllers.size();
        modulesActive = Math.min(unlockedModuleSlots, modulesFound);
    }

    private void applyModuleEnabling() {
        if (getLevel() == null || !isFormed()) return;

        for (int i = 0; i < moduleControllers.size(); i++) {
            BlockPos pos = moduleControllers.get(i);
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);

            boolean enable = i < modulesActive;

            if (mm instanceof SpaceMinerMachine m) m.setEnabledByElevator(enable);
            else if (mm instanceof SpacePumpMachine m) m.setEnabledByElevator(enable);
            else if (mm instanceof SpaceAssemblerMachine m) m.setEnabledByElevator(enable);
        }
    }

    private void disableAllKnownModules() {
        if (getLevel() == null) return;

        for (BlockPos pos : moduleControllers) {
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);
            if (mm instanceof SpaceMinerMachine m) m.setEnabledByElevator(false);
            else if (mm instanceof SpacePumpMachine m) m.setEnabledByElevator(false);
            else if (mm instanceof SpaceAssemblerMachine m) m.setEnabledByElevator(false);
        }
    }

    private static boolean isElevatorModuleController(MetaMachine machine) {
        return machine instanceof SpaceMinerMachine
                || machine instanceof SpacePumpMachine
                || machine instanceof SpaceAssemblerMachine;
    }
}
