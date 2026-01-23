package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.mordgren.gtca.common.data.GTCABlocks;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class SpaceElevatorMachine extends WorkableElectricMultiblockMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(SpaceElevatorMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    // ---------------- persisted/synced ----------------

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

    // ---------------- runtime ----------------
    private TickableSubscription scanSub = null;
    private int scanTimer = 0;

    private final List<SlotInfo> slotInfos = new ArrayList<>();

    public SpaceElevatorMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    // =========================================================
    //  SLOT LAYOUT (LOCAL OFFSETS)
    // =========================================================

    private static final List<BlockPos> MODULE_SLOTS_LOCAL = List.of(
            new BlockPos( 2, 0,  5),
            new BlockPos( 0, 0,  5),
            new BlockPos(-2, 0,  5),

            new BlockPos(-8, 0, -1),
            new BlockPos(-8, 0, -3),
            new BlockPos(-8, 0, -5),

            new BlockPos(-2, 0, -11),
            new BlockPos( 0, 0, -11),
            new BlockPos( 2, 0, -11),

            new BlockPos( 8, 0, -5),
            new BlockPos( 8, 0, -3),
            new BlockPos( 8, 0, -1)
    );

    // ---------------- lifecycle ----------------

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        if (scanSub == null) {
            scanSub = subscribeServerTick(this::onServerTickSubscribed);
        }

        scanTimer = 0;
        rescanAll();
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();

        disableAllModulesInSlots();

        motorTier = 1;
        unlockedModuleSlots = 0;
        modulesFound = 0;
        modulesValid = 0;
        modulesActive = 0;
        slotInfos.clear();
        scanTimer = 0;
    }

    @Override
    public void onUnload() {
        super.onUnload();
        scanSub = null;
        scanTimer = 0;
        slotInfos.clear();
    }

    private void onServerTickSubscribed() {
        if (getLevel() == null) return;
        if (!isFormed()) return;

        scanTimer++;
        if (scanTimer >= 20) { // раз в секунду
            scanTimer = 0;
            rescanAll();
        }
    }

    // ---------------- public getters (UI) ----------------

    public int getMotorTier() { return motorTier; }
    public int getUnlockedModuleSlots() { return unlockedModuleSlots; }
    public int getModulesFound() { return modulesFound; }
    public int getModulesValid() { return modulesValid; }
    public int getModulesActive() { return modulesActive; }

    public Direction getElevatorFacingPublic() {
        return getElevatorFacing();
    }

    public List<SlotInfo> getSlotInfos() {
        return List.copyOf(slotInfos);
    }

    // =========================================================
    //  DISPLAY (pretty)
    // =========================================================
    public void addElevatorDisplayText(List<Component> list) {

        list.add(Component.literal("Space Elevator").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));

        list.add(Component.literal("Formed: ").withStyle(ChatFormatting.GRAY)
                .append(Component.literal(String.valueOf(isFormed()))
                        .withStyle(isFormed() ? ChatFormatting.GREEN : ChatFormatting.RED)));

        if (!isFormed()) return;

        list.add(Component.literal("Motor Tier: ").withStyle(ChatFormatting.GRAY)
                .append(Component.literal("MK" + motorTier).withStyle(ChatFormatting.GOLD))
                .append(Component.literal("  |  Slots: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(String.valueOf(unlockedModuleSlots)).withStyle(ChatFormatting.GOLD)));

        list.add(Component.literal("Modules: ").withStyle(ChatFormatting.GRAY)
                .append(Component.literal(String.valueOf(modulesFound)).withStyle(ChatFormatting.WHITE))
                .append(Component.literal("  |  Valid: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(String.valueOf(modulesValid)).withStyle(ChatFormatting.GREEN))
                .append(Component.literal("  |  Active: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(String.valueOf(modulesActive)).withStyle(ChatFormatting.AQUA)));

        // Legend
        list.add(Component.literal("Legend: ").withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.literal("P").withStyle(ChatFormatting.YELLOW))
                .append(Component.literal("=Present ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal("F").withStyle(ChatFormatting.GREEN))
                .append(Component.literal("=Formed ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal("V").withStyle(ChatFormatting.AQUA))
                .append(Component.literal("=Valid ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal("A").withStyle(ChatFormatting.LIGHT_PURPLE))
                .append(Component.literal("=Active").withStyle(ChatFormatting.GRAY)));

        list.add(Component.literal("Types: ").withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.literal("Miner ").withStyle(ChatFormatting.BLUE))
                .append(Component.literal("Assembler ").withStyle(ChatFormatting.LIGHT_PURPLE))
                .append(Component.literal("Pump").withStyle(ChatFormatting.AQUA)));

        // 12 точек без координат
        for (SlotInfo s : slotInfos) {
            int num = s.index + 1;

            String dot = s.present ? "●" : "○";
            ChatFormatting dotColor = s.active
                    ? ChatFormatting.GREEN
                    : s.present ? ChatFormatting.YELLOW : ChatFormatting.DARK_GRAY;

            Component line = Component.literal(String.format("%02d ", num)).withStyle(ChatFormatting.DARK_GRAY)
                    .append(Component.literal(dot).withStyle(dotColor))
                    .append(Component.literal(" ").withStyle(ChatFormatting.DARK_GRAY))
                    .append(Component.literal("[").withStyle(ChatFormatting.DARK_GRAY))
                    .append(flag(s.present, "P", ChatFormatting.YELLOW))
                    .append(flag(s.formed, "F", ChatFormatting.GREEN))
                    .append(flag(s.valid, "V", ChatFormatting.AQUA))
                    .append(flag(s.active, "A", ChatFormatting.LIGHT_PURPLE))
                    .append(Component.literal("] ").withStyle(ChatFormatting.DARK_GRAY))
                    .append(kindComponent(s.kind));

            list.add(line);
        }
    }

    private Component flag(boolean state, String ch, ChatFormatting onColor) {
        return Component.literal(state ? ch : "-").withStyle(state ? onColor : ChatFormatting.DARK_GRAY);
    }

    private Component kindComponent(ElevatorModuleKind kind) {
        if (kind == null) {
            return Component.literal("Empty").withStyle(ChatFormatting.DARK_GRAY);
        }

        return switch (kind) {
            case MINER -> Component.literal("Miner").withStyle(ChatFormatting.BLUE);
            case ASSEMBLER -> Component.literal("Assembler").withStyle(ChatFormatting.LIGHT_PURPLE);
            case PUMP -> Component.literal("Pump").withStyle(ChatFormatting.AQUA);
            default -> Component.literal(kind.name()).withStyle(ChatFormatting.GRAY); // на всякий случай
        };
    }

    // =========================================================
    //  MAIN RESCAN
    // =========================================================
    private void rescanAll() {
        recalcMotorTierAndSlots();
        rebuildSlots();
        applyEnabling();
    }

    // ---------------- motor tier logic ----------------

    private void recalcMotorTierAndSlots() {
        int newMotorTier = computeMotorTierMinFromCache();
        motorTier = newMotorTier;
        unlockedModuleSlots = slotsForMotorTier(newMotorTier);
    }

    private int computeMotorTierMinFromCache() {
        if (getLevel() == null) return 1;

        Iterable<BlockPos> cache = safeGetCache();
        if (cache == null) return 1;

        int minTier = Integer.MAX_VALUE;
        boolean foundAny = false;

        for (BlockPos pos : cache) {
            Block b = getLevel().getBlockState(pos).getBlock();
            int t = tierOfMotorBlock(b);
            if (t > 0) {
                foundAny = true;
                if (t < minTier) minTier = t;
            }
        }
        return foundAny ? minTier : 1;
    }

    private Iterable<BlockPos> safeGetCache() {
        try {
            return getMultiblockState().getCache();
        } catch (NullPointerException ignored) {
            return null;
        }
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

    // ---------------- slot scanning ----------------

    private void rebuildSlots() {
        slotInfos.clear();
        modulesFound = 0;
        modulesValid = 0;

        if (getLevel() == null || !isFormed()) {
            modulesActive = 0;
            return;
        }

        Direction elevatorFacing = getElevatorFacing();
        BlockPos base = getPos();

        for (int i = 0; i < MODULE_SLOTS_LOCAL.size(); i++) {
            BlockPos local = MODULE_SLOTS_LOCAL.get(i);
            BlockPos worldPos = base.offset(
                    rotateLocalX(local, elevatorFacing),
                    local.getY(),
                    rotateLocalZ(local, elevatorFacing)
            );

            MetaMachine mm = MetaMachine.getMachine(getLevel(), worldPos);
            IElevatorModule mod = asElevatorModule(mm);

            boolean present = (mod != null);
            boolean formed = present && isMachineFormed(mm);
            boolean valid = formed;

            ElevatorModuleKind kind = present ? safeGetKind(mod) : null;

            if (present) modulesFound++;
            if (valid) modulesValid++;

            slotInfos.add(new SlotInfo(i, worldPos, present, formed, valid, false, kind));
        }


        modulesActive = Math.min(unlockedModuleSlots, modulesValid);

        int activeLeft = modulesActive;
        for (SlotInfo info : slotInfos) {
            if (activeLeft <= 0) break;
            if (info.valid) {
                info.active = true;
                activeLeft--;
            }
        }
    }

    private ElevatorModuleKind safeGetKind(IElevatorModule mod) {
        try {
            return mod.getElevatorModuleKind();
        } catch (Throwable t) {
            return null;
        }
    }

    private void applyEnabling() {
        if (getLevel() == null || !isFormed()) return;


        for (SlotInfo s : slotInfos) {
            if (!s.present) continue;
            MetaMachine mm = MetaMachine.getMachine(getLevel(), s.pos);
            setModuleEnabled(mm, false);
        }


        for (SlotInfo s : slotInfos) {
            if (!s.present) continue;
            MetaMachine mm = MetaMachine.getMachine(getLevel(), s.pos);
            if (s.active) setModuleEnabled(mm, true);
        }
    }

    private void disableAllModulesInSlots() {
        if (getLevel() == null) return;

        Direction elevatorFacing = getElevatorFacing();
        BlockPos base = getPos();

        for (BlockPos local : MODULE_SLOTS_LOCAL) {
            BlockPos worldPos = base.offset(
                    rotateLocalX(local, elevatorFacing),
                    local.getY(),
                    rotateLocalZ(local, elevatorFacing)
            );
            MetaMachine mm = MetaMachine.getMachine(getLevel(), worldPos);
            setModuleEnabled(mm, false);
        }
    }

    // ---------------- helpers ----------------

    private IElevatorModule asElevatorModule(MetaMachine mm) {
        return (mm instanceof IElevatorModule m) ? m : null;
    }

    private void setModuleEnabled(MetaMachine mm, boolean enabled) {
        IElevatorModule mod = asElevatorModule(mm);
        if (mod != null) {
            mod.setEnabledByElevator(enabled);
        }
    }

    private boolean isMachineFormed(MetaMachine mm) {
        if (mm instanceof WorkableMultiblockMachine w) {
            return w.isFormed();
        }
        return false;
    }

    private Direction getElevatorFacing() {
        if (getLevel() == null) return Direction.SOUTH;

        BlockState state = getLevel().getBlockState(getPos());
        Block block = state.getBlock();

        if (block instanceof IMachineBlock mb) {
            return mb.getFrontFacing(state);
        }
        if (state.hasProperty(HorizontalDirectionalBlock.FACING)) {
            return state.getValue(HorizontalDirectionalBlock.FACING);
        }
        return Direction.SOUTH;
    }

    // SOUTH: (x,z)
    // NORTH: (-x,-z)
    // EAST:  (z,-x)
    // WEST:  (-z,x)
    private int rotateLocalX(BlockPos local, Direction facing) {
        int x = local.getX();
        int z = local.getZ();
        return switch (facing) {
            case SOUTH -> x;
            case NORTH -> -x;
            case EAST  -> z;
            case WEST  -> -z;
            default    -> x;
        };
    }

    private int rotateLocalZ(BlockPos local, Direction facing) {
        int x = local.getX();
        int z = local.getZ();
        return switch (facing) {
            case SOUTH -> z;
            case NORTH -> -z;
            case EAST  -> -x;
            case WEST  -> x;
            default    -> z;
        };
    }

    // ---------------- data holder ----------------

    public static final class SlotInfo {
        public final int index;
        public final BlockPos pos;
        public final boolean present;
        public final boolean formed;
        public final boolean valid;
        public boolean active;
        public final ElevatorModuleKind kind; // null = empty

        public SlotInfo(int index, BlockPos pos, boolean present, boolean formed, boolean valid, boolean active,
                        ElevatorModuleKind kind) {
            this.index = index;
            this.pos = pos;
            this.present = present;
            this.formed = formed;
            this.valid = valid;
            this.active = active;
            this.kind = kind;
        }
    }
}


