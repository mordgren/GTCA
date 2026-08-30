package net.mordgren.gtca.common.machine.multiblock.electric.elevator;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.mordgren.gtca.common.machine.multiblock.electric.SpaceElevatorMachine;
import java.util.List;
public final class SpaceElevatorDisplay {
    private SpaceElevatorDisplay() {}
    public static void addDisplay(SpaceElevatorMachine se, List<Component> list) {
        list.add(Component.literal("Space Elevator").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));
        list.add(lineKeyValue("Formed: ",
                String.valueOf(se.isFormed()),
                se.isFormed() ? ChatFormatting.GREEN : ChatFormatting.RED));
        if (!se.isFormed()) return;
        list.add(Component.empty()
                .append(Component.literal("Motor Tier: ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal("MK" + se.getMotorTier()).withStyle(ChatFormatting.GOLD))
                .append(Component.literal("  |  Slots: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(String.valueOf(se.getUnlockedModuleSlots())).withStyle(ChatFormatting.GOLD)));
        list.add(Component.empty()
                .append(Component.literal("Modules: ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal(String.valueOf(se.getModulesFound())).withStyle(ChatFormatting.WHITE))
                .append(Component.literal("  |  Valid: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(String.valueOf(se.getModulesValid())).withStyle(ChatFormatting.GREEN))
                .append(Component.literal("  |  Active: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(String.valueOf(se.getModulesActive())).withStyle(ChatFormatting.AQUA)));
        list.add(Component.empty()
                .append(Component.literal("Brief: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal("P").withStyle(ChatFormatting.YELLOW))
                .append(Component.literal("=Present ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal("F").withStyle(ChatFormatting.GREEN))
                .append(Component.literal("=Formed ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal("V").withStyle(ChatFormatting.AQUA))
                .append(Component.literal("=Valid ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal("A").withStyle(ChatFormatting.LIGHT_PURPLE))
                .append(Component.literal("=Active").withStyle(ChatFormatting.GRAY)));
        list.add(Component.empty()
                .append(Component.literal("Types: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal("Miner ").withStyle(ChatFormatting.BLUE))
                .append(Component.literal("Assembler ").withStyle(ChatFormatting.LIGHT_PURPLE))
                .append(Component.literal("Pump").withStyle(ChatFormatting.AQUA)));
        int shown = 0;
        for (var s : se.getSlotInfos()) {
            if (shown++ >= 12) break;
            MutableComponent flags = Component.empty()
                    .append(flag('P', s.present, ChatFormatting.YELLOW))
                    .append(flag('F', s.formed, ChatFormatting.GREEN))
                    .append(flag('V', s.valid, ChatFormatting.AQUA))
                    .append(flag('A', s.active, ChatFormatting.LIGHT_PURPLE));
            MutableComponent type = Component.literal(shortTypeName(s.kind)).withStyle(typeColor(s.kind));
            list.add(Component.empty()
                    .append(Component.literal(String.format("#%02d ", s.index + 1)).withStyle(ChatFormatting.DARK_GRAY))
                    .append(Component.literal("[").withStyle(ChatFormatting.DARK_GRAY))
                    .append(flags)
                    .append(Component.literal("] ").withStyle(ChatFormatting.DARK_GRAY))
                    .append(type)
            );
        }
    }
    private static MutableComponent lineKeyValue(String key, String value, ChatFormatting valueColor) {
        return Component.empty()
                .append(Component.literal(key).withStyle(ChatFormatting.GRAY))
                .append(Component.literal(value).withStyle(valueColor));
    }
    private static MutableComponent flag(char c, boolean on, ChatFormatting color) {
        return Component.literal(String.valueOf(c)).withStyle(on ? color : ChatFormatting.DARK_GRAY);
    }
    private static String shortTypeName(ElevatorModuleKind kind) {
        return switch (kind) {
            case MINER -> "Miner";
            case ASSEMBLER -> "Assembler";
            case PUMP -> "Pump";
            default -> "-";
        };
    }
    private static ChatFormatting typeColor(ElevatorModuleKind kind) {
        return switch (kind) {
            case MINER -> ChatFormatting.BLUE;
            case ASSEMBLER -> ChatFormatting.LIGHT_PURPLE;
            case PUMP -> ChatFormatting.AQUA;
            default -> ChatFormatting.DARK_GRAY;
        };
    }
    public static void addModuleDisplay(SpaceElevatorModuleMachine module, List<Component> list) {
        var energy = module.getWirelessEnergyContainer();

        list.add(Component.literal("Space Elevator Module")
                .withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD));

        list.add(Component.empty()
                .append(Component.literal("Type: ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal(module.getElevatorModuleKind().displayName())
                        .withStyle(typeColor(module.getElevatorModuleKind())))
                .append(Component.literal("  |  MK: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(String.valueOf(module.getModuleMk())).withStyle(ChatFormatting.GOLD)));

        list.add(Component.empty()
                .append(Component.literal("Formed: ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal(String.valueOf(module.isFormed()))
                        .withStyle(module.isFormed() ? ChatFormatting.GREEN : ChatFormatting.RED))
                .append(Component.literal("  |  Elevator: ").withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(module.isEnabledByElevator() ? "Enabled" : "Disabled")
                        .withStyle(module.isEnabledByElevator() ? ChatFormatting.GREEN : ChatFormatting.RED)));

        list.add(Component.empty()
                .append(Component.literal("Energy: ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal(energy.getEnergyStored() + " / " + energy.getEnergyCapacity() + " EU")
                        .withStyle(ChatFormatting.YELLOW)));

        list.add(Component.empty()
                .append(Component.literal("Power Input: ").withStyle(ChatFormatting.GRAY))
                .append(Component.literal("Space Elevator Wireless")
                        .withStyle(ChatFormatting.LIGHT_PURPLE)));

        if (module.requiresComputation()) {
            list.add(Component.empty()
                    .append(Component.literal("Computation: ").withStyle(ChatFormatting.GRAY))
                    .append(Component.literal("Requires CWU/t")
                            .withStyle(ChatFormatting.AQUA)));
        } else {
            list.add(Component.empty()
                    .append(Component.literal("Computation: ").withStyle(ChatFormatting.GRAY))
                    .append(Component.literal("Not required")
                            .withStyle(ChatFormatting.DARK_GRAY)));
        }
    }
}