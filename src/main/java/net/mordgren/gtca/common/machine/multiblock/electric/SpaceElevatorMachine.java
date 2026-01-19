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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // modules: found/valid lists
    private final List<BlockPos> moduleControllersAll = new ArrayList<>();
    private final List<BlockPos> moduleControllersValid = new ArrayList<>();
    private final Set<BlockPos> moduleControllersValidSet = new HashSet<>();

    private int modulesFound = 0;
    private int modulesValid = 0;
    private int modulesActive = 0;

    // periodic rescan (subscription-based)
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

        // подписка на серверный тик
        if (rescanSub == null) {
            rescanSub = subscribeServerTick(this::onServerTickSubscribed);
        }

        // первичный расчёт
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

        moduleControllersAll.clear();
        moduleControllersValid.clear();
        moduleControllersValidSet.clear();

        modulesFound = 0;
        modulesValid = 0;
        modulesActive = 0;

        rescanTimer = 0;
    }

    // ---------------- subscribed tick ----------------

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

    public int getModulesValid() {
        return modulesValid;
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
            clearModules();
            return;
        }
        rebuildModulesSafe(cache);
    }

    private void rebuildModulesSafe(Iterable<BlockPos> cache) {
        clearModules();

        if (getLevel() == null || !isFormed()) {
            return;
        }
        if (cache == null) {
            return;
        }

        for (BlockPos pos : cache) {
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);
            if (isElevatorModuleController(mm)) {
                BlockPos cp = pos.immutable();
                moduleControllersAll.add(cp);

                if (isModuleStructureValid(cp)) {
                    moduleControllersValid.add(cp);
                    moduleControllersValidSet.add(cp);
                }
            }
        }

        // стабильный порядок
        moduleControllersAll.sort(Comparator.comparingLong(BlockPos::asLong));
        moduleControllersValid.sort(Comparator.comparingLong(BlockPos::asLong));

        modulesFound = moduleControllersAll.size();
        modulesValid = moduleControllersValid.size();
        modulesActive = Math.min(unlockedModuleSlots, modulesValid);
    }

    private void clearModules() {
        moduleControllersAll.clear();
        moduleControllersValid.clear();
        moduleControllersValidSet.clear();
        modulesFound = 0;
        modulesValid = 0;
        modulesActive = 0;
    }

    private void applyModuleEnabling() {
        if (getLevel() == null || !isFormed()) return;

        // Сначала выключаем всё найденное
        for (BlockPos pos : moduleControllersAll) {
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);
            setModuleEnabled(mm, false);
        }

        // Потом включаем только первые N валидных
        for (int i = 0; i < moduleControllersValid.size(); i++) {
            boolean enable = i < modulesActive;
            if (!enable) break;

            BlockPos pos = moduleControllersValid.get(i);
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);
            setModuleEnabled(mm, true);
        }
    }

    private void disableAllKnownModules() {
        if (getLevel() == null) return;

        for (BlockPos pos : moduleControllersAll) {
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);
            setModuleEnabled(mm, false);
        }
    }

    private void setModuleEnabled(MetaMachine mm, boolean enabled) {
        if (mm instanceof SpaceMinerMachine m) m.setEnabledByElevator(enabled);
        else if (mm instanceof SpacePumpMachine m) m.setEnabledByElevator(enabled);
        else if (mm instanceof SpaceAssemblerMachine m) m.setEnabledByElevator(enabled);
    }

    private static boolean isElevatorModuleController(MetaMachine machine) {
        return machine instanceof SpaceMinerMachine
                || machine instanceof SpacePumpMachine
                || machine instanceof SpaceAssemblerMachine;
    }

    // ---------------- module structure validation (MVP) ----------------

    private boolean isModuleStructureValid(BlockPos controllerPos) {
        if (getLevel() == null) return false;

        // MVP: 4 блока вверх от контроллера должны быть кейсингами лифта
        for (int dy = 1; dy <= 4; dy++) {
            BlockPos p = controllerPos.above(dy);
            Block b = getLevel().getBlockState(p).getBlock();
            if (b != GTCABlocks.SPACE_ELEVATOR_CASING.get()) {
                return false;
            }
        }
        return true;
    }
}
