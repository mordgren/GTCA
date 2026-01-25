package net.mordgren.gtca.common.machine.multiblock.electric.elevator;

import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.mordgren.gtca.common.machine.multiblock.electric.SpaceElevatorMachine;

import java.util.ArrayList;
import java.util.List;

public class ElevatorModuleManager {

    /*
    Created by sensesgone
    25.1.2026
     */

    private final SpaceElevatorMachine elevator;

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

    private final List<SlotInfo> slotInfos = new ArrayList<>();

    private int modulesFound = 0;
    private int modulesValid = 0;
    private int modulesActive = 0;

    public ElevatorModuleManager(SpaceElevatorMachine elevator) {
        this.elevator = elevator;
    }

    public int getModulesFound() { return modulesFound; }
    public int getModulesValid() { return modulesValid; }
    public int getModulesActive() { return modulesActive; }
    public List<SlotInfo> getSlotInfos() { return List.copyOf(slotInfos); }

    public void rescanSlots(int unlockedModuleSlots) {
        slotInfos.clear();
        modulesFound = 0;
        modulesValid = 0;
        modulesActive = 0;

        if (elevator.getLevel() == null || !elevator.isFormed()) return;

        Direction facing = getElevatorFacing();
        BlockPos base = elevator.getPos();

        for (int i = 0; i < MODULE_SLOTS_LOCAL.size(); i++) {
            BlockPos local = MODULE_SLOTS_LOCAL.get(i);
            BlockPos worldPos = base.offset(
                    rotateLocalX(local, facing),
                    local.getY(),
                    rotateLocalZ(local, facing)
            );

            MetaMachine mm = MetaMachine.getMachine(elevator.getLevel(), worldPos);
            IElevatorModule mod = asElevatorModule(mm);

            boolean present = (mod != null);
            boolean formed = present && isMachineFormed(mm);
            boolean valid = formed && (mod == null || mod.isValidForElevator());
            ElevatorModuleKind kind = present ? safeKind(mod) : ElevatorModuleKind.UNKNOWN;

            if (present) modulesFound++;
            if (valid) modulesValid++;

            slotInfos.add(new SlotInfo(i, worldPos, present, formed, valid, false, kind));
        }

        modulesActive = Math.min(unlockedModuleSlots, modulesValid);

        int left = modulesActive;
        for (SlotInfo s : slotInfos) {
            if (left <= 0) break;
            if (s.valid) {
                s.active = true;
                left--;
            }
        }
    }

    public void applyEnabling() {
        if (elevator.getLevel() == null || !elevator.isFormed()) return;

        for (SlotInfo s : slotInfos) {
            if (!s.present) continue;
            MetaMachine mm = MetaMachine.getMachine(elevator.getLevel(), s.pos);
            setModuleEnabled(mm, false);
        }

        for (SlotInfo s : slotInfos) {
            if (!s.present || !s.active) continue;
            MetaMachine mm = MetaMachine.getMachine(elevator.getLevel(), s.pos);
            setModuleEnabled(mm, true);
        }
    }

    public void disableAllModulesInSlots() {
        if (elevator.getLevel() == null) return;

        Direction facing = getElevatorFacing();
        BlockPos base = elevator.getPos();

        for (BlockPos local : MODULE_SLOTS_LOCAL) {
            BlockPos worldPos = base.offset(
                    rotateLocalX(local, facing),
                    local.getY(),
                    rotateLocalZ(local, facing)
            );
            MetaMachine mm = MetaMachine.getMachine(elevator.getLevel(), worldPos);
            setModuleEnabled(mm, false);
        }
    }

    private ElevatorModuleKind safeKind(IElevatorModule mod) {
        try {
            var k = mod.getElevatorModuleKind();
            return k == null ? ElevatorModuleKind.UNKNOWN : k;
        } catch (Throwable t) {
            return ElevatorModuleKind.UNKNOWN;
        }
    }

    private IElevatorModule asElevatorModule(MetaMachine mm) {
        return (mm instanceof IElevatorModule m) ? m : null;
    }

    private void setModuleEnabled(MetaMachine mm, boolean enabled) {
        IElevatorModule mod = asElevatorModule(mm);
        if (mod != null) mod.setEnabledByElevator(enabled);
    }

    private boolean isMachineFormed(MetaMachine mm) {
        return (mm instanceof WorkableMultiblockMachine w) && w.isFormed();
    }

    private Direction getElevatorFacing() {
        if (elevator.getLevel() == null) return Direction.SOUTH;

        BlockState state = elevator.getLevel().getBlockState(elevator.getPos());
        Block block = state.getBlock();

        if (block instanceof IMachineBlock mb) {
            return mb.getFrontFacing(state);
        }
        if (state.hasProperty(HorizontalDirectionalBlock.FACING)) {
            return state.getValue(HorizontalDirectionalBlock.FACING);
        }
        return Direction.SOUTH;
    }

    // SOUTH:(x,z), NORTH:(-x,-z), EAST:(z,-x), WEST:(-z,x)
    private int rotateLocalX(BlockPos local, Direction facing) {
        int x = local.getX();
        int z = local.getZ();
        return switch (facing) {
            case SOUTH -> x;
            case NORTH -> -x;
            case EAST -> z;
            case WEST -> -z;
            default -> x;
        };
    }

    private int rotateLocalZ(BlockPos local, Direction facing) {
        int x = local.getX();
        int z = local.getZ();
        return switch (facing) {
            case SOUTH -> z;
            case NORTH -> -z;
            case EAST -> -x;
            case WEST -> x;
            default -> z;
        };
    }

    public static final class SlotInfo {
        public final int index;
        public final BlockPos pos;
        public final boolean present;
        public final boolean formed;
        public final boolean valid;
        public boolean active;
        public final ElevatorModuleKind kind;

        public SlotInfo(int index, BlockPos pos, boolean present, boolean formed, boolean valid, boolean active, ElevatorModuleKind kind) {
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
