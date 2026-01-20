package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.mordgren.gtca.common.data.GTCABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule;

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

    // -------- persisted / synced --------
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

    // -------- runtime --------
    private final List<BlockPos> moduleControllersAll = new ArrayList<>();
    private final List<BlockPos> moduleControllersValid = new ArrayList<>();
    private final Set<BlockPos> moduleControllersValidSet = new HashSet<>();
    private final List<ModuleInfo> moduleInfos = new ArrayList<>();

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
        clearModules();
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        if (rescanSub == null) {
            rescanSub = subscribeServerTick(this::onServerTickSubscribed);
        }

        Iterable<BlockPos> cache = safeGetCache();
        if (cache != null) {
            recalcMotorTierAndSlots(cache);
            rebuildModules(cache);
            applyModuleEnabling();
        } else {
            motorTier = 1;
            unlockedModuleSlots = slotsForMotorTier(1);
            clearModules();
        }

        rescanTimer = 0;
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();

        disableAllKnownModules();

        motorTier = 1;
        unlockedModuleSlots = 0;

        clearModules();
        rescanTimer = 0;
    }

    // ---------------- tick ----------------

    private void onServerTickSubscribed() {
        if (getLevel() == null) return;
        if (!isFormed()) return;

        Iterable<BlockPos> cache = safeGetCache();
        if (cache == null) return;

        // перескан раз в секунду
        rescanTimer++;
        if (rescanTimer >= 20) {
            rescanTimer = 0;

            recalcMotorTierAndSlots(cache);
            rebuildModules(cache);
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

    private void clearModules() {
        moduleControllersAll.clear();
        moduleControllersValid.clear();
        moduleControllersValidSet.clear();
        moduleInfos.clear();
        modulesFound = 0;
        modulesValid = 0;
        modulesActive = 0;
    }

    // ---------------- getters (UI / debug) ----------------

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

    public List<BlockPos> getModuleControllersAll() {
        return List.copyOf(moduleControllersAll);
    }

    public Set<BlockPos> getModuleControllersValidSet() {
        return Set.copyOf(moduleControllersValidSet);
    }

    public List<ModuleInfo> getModuleInfos() {
        return List.copyOf(moduleInfos);
    }

    /** Для additionalDisplay */
    public String debugModuleAtPublic(BlockPos pos) {
        return debugModuleAt(pos);
    }

    // ---------------- motor tier ----------------

    private void recalcMotorTierAndSlots(Iterable<BlockPos> cache) {
        int newMotorTier = computeMotorTierMin(cache);
        motorTier = newMotorTier;
        unlockedModuleSlots = slotsForMotorTier(newMotorTier);
    }

    private int computeMotorTierMin(Iterable<BlockPos> cache) {
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

    private void rebuildModules(Iterable<BlockPos> cache) {
        moduleControllersAll.clear();
        moduleControllersValid.clear();
        moduleControllersValidSet.clear();
        moduleInfos.clear();

        if (getLevel() == null || !isFormed() || cache == null) {
            modulesFound = 0;
            modulesValid = 0;
            modulesActive = 0;
            return;
        }

        for (BlockPos pos : cache) {
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);
            IElevatorModule module = asElevatorModule(mm);
            if (module == null) continue;

            BlockPos cp = pos.immutable();
            moduleControllersAll.add(cp);

            ElevatorModuleKind kind = module.getElevatorModuleKind();
            boolean valid = isModuleValidByHatches(cp, kind);

            if (valid) {
                moduleControllersValid.add(cp);
                moduleControllersValidSet.add(cp);
            }
        }


        moduleControllersAll.sort(Comparator.comparingLong(BlockPos::asLong));
        moduleControllersValid.sort(Comparator.comparingLong(BlockPos::asLong));

        modulesFound = moduleControllersAll.size();
        modulesValid = moduleControllersValid.size();
        modulesActive = Math.min(unlockedModuleSlots, modulesValid);


        Set<BlockPos> activeSet = new HashSet<>();
        for (int i = 0; i < moduleControllersValid.size() && i < modulesActive; i++) {
            activeSet.add(moduleControllersValid.get(i));
        }


        for (BlockPos p : moduleControllersAll) {
            MetaMachine mm = MetaMachine.getMachine(getLevel(), p);
            IElevatorModule module = asElevatorModule(mm);

            ElevatorModuleKind kind = (module != null) ? module.getElevatorModuleKind() : null;

            boolean valid = moduleControllersValidSet.contains(p);
            boolean active = activeSet.contains(p);


            moduleInfos.add(new ModuleInfo(p, kind != null ? kind.name() : "UNKNOWN", valid, active));
        }
    }

    private void applyModuleEnabling() {
        if (getLevel() == null || !isFormed()) return;


        for (BlockPos pos : moduleControllersAll) {
            MetaMachine mm = MetaMachine.getMachine(getLevel(), pos);
            setModuleEnabled(mm, false);
        }


        for (int i = 0; i < moduleControllersValid.size() && i < modulesActive; i++) {
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

    // -------- Stage 1 helpers --------

    private IElevatorModule asElevatorModule(MetaMachine mm) {
        return (mm instanceof IElevatorModule m) ? m : null;
    }

    private void setModuleEnabled(MetaMachine mm, boolean enabled) {
        IElevatorModule module = asElevatorModule(mm);
        if (module != null) {
            module.setEnabledByElevator(enabled);
        }
    }

    // ---------------- module validation by hatches ----------------

    private boolean isModuleValidByHatches(BlockPos controllerPos, ElevatorModuleKind kind) {
        if (getLevel() == null || kind == null) return false;

        Direction facing = getControllerFacing(controllerPos);
        if (facing == null) return false;

        BlockPos back = controllerPos.relative(facing.getOpposite());


        BlockPos[] slots = new BlockPos[]{
                back.above(1),
                back,
                back.below(1),
                back.below(2)
        };

        PartAbility[] required = requiredAbilities(kind);

        for (PartAbility need : required) {
            if (!hasAbilityInSlots(need, slots)) return false;
        }

        return true;
    }

    private PartAbility[] requiredAbilities(ElevatorModuleKind kind) {
        return switch (kind) {
            case MINER -> new PartAbility[]{
                    PartAbility.IMPORT_ITEMS,
                    PartAbility.EXPORT_ITEMS,
                    PartAbility.IMPORT_FLUIDS,
                    PartAbility.COMPUTATION_DATA_RECEPTION
            };
            case ASSEMBLER -> new PartAbility[]{
                    PartAbility.IMPORT_ITEMS,
                    PartAbility.EXPORT_ITEMS,
                    PartAbility.IMPORT_FLUIDS
            };
            case PUMP -> new PartAbility[]{
                    PartAbility.EXPORT_FLUIDS
            };
        };
    }

    private boolean hasAbilityInSlots(PartAbility ability, BlockPos[] slots) {
        if (getLevel() == null) return false;
        for (BlockPos p : slots) {
            Block b = getLevel().getBlockState(p).getBlock();
            if (ability.isApplicable(b)) return true;
        }
        return false;
    }

    private Direction getControllerFacing(BlockPos controllerPos) {
        if (getLevel() == null) return null;

        BlockState state = getLevel().getBlockState(controllerPos);
        Block block = state.getBlock();

        if (block instanceof IMachineBlock mb) {
            return mb.getFrontFacing(state);
        }

        if (state.hasProperty(HorizontalDirectionalBlock.FACING)) {
            return state.getValue(HorizontalDirectionalBlock.FACING);
        }

        return null;
    }

    // ---------------- diagnostics ----------------

    private String debugModuleAt(BlockPos controllerPos) {
        if (getLevel() == null) return "no level";

        MetaMachine mm = MetaMachine.getMachine(getLevel(), controllerPos);
        IElevatorModule module = asElevatorModule(mm);

        if (module == null) return "not a module controller";

        ElevatorModuleKind kind = module.getElevatorModuleKind();

        Direction facing = getControllerFacing(controllerPos);
        if (facing == null) return "no facing property";

        BlockPos back = controllerPos.relative(facing.getOpposite());
        BlockPos[] slots = new BlockPos[]{
                back.above(1),
                back,
                back.below(1),
                back.below(2)
        };

        PartAbility[] required = requiredAbilities(kind);

        List<String> missing = new ArrayList<>();
        for (PartAbility need : required) {
            if (!hasAbilityInSlots(need, slots)) {
                missing.add(need.getName());
            }
        }

        if (missing.isEmpty()) return "ok";
        return "missing: " + String.join(", ", missing);
    }

    // ---------------- data holder ----------------

    public static final class ModuleInfo {
        public final BlockPos pos;
        public final String kind;
        public final boolean valid;
        public final boolean active;
        public final boolean formed;

        public ModuleInfo(BlockPos pos, String kind, boolean valid, boolean active) {
            this.pos = pos;
            this.kind = kind;
            this.valid = valid;
            this.active = active;
            this.formed = valid;
        }
    }
}



