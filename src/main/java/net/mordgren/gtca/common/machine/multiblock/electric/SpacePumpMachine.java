package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorLinkedModuleMachine;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;

import java.util.List;

public class SpacePumpMachine extends ElevatorLinkedModuleMachine {

    public static final long EU_PER_PARALLEL = 65_536L;

    public SpacePumpMachine(IMachineBlockEntity holder, int moduleTier) {
        super(holder, moduleTier);
    }

    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return ElevatorModuleKind.PUMP;
    }

    @Override
    public boolean isValidForElevator() {
        return isFormed();
    }


    public int getMaxPumpParallel() {
        return maxParallelForMk(getModuleMk());
    }

    public long getPumpMaxPower() {
        return EU_PER_PARALLEL * getMaxPumpParallel();
    }

    public static int maxParallelForMk(int mk) {
        return switch (mk) {
            case 1 -> 4;
            case 2 -> 16;
            case 3 -> 256;
            default -> 1;
        };
    }


    @Override
    protected long computeWirelessAmperage(int tier) {
        return switch (tier) {
            case GTValues.LuV -> 8L;
            case GTValues.ZPM -> 8L;
            case GTValues.UV  -> 32L;
            default -> 1L;
        };
    }

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if (!(machine instanceof SpacePumpMachine pump)) {
            return RecipeModifier.nullWrongType(SpacePumpMachine.class, machine);
        }

        if (!pump.isFormed()) {
            return ModifierFunction.cancel(Component.literal("Space Pump structure is not formed"));
        }

        if (!pump.isEnabledByElevator()) {
            return ModifierFunction.cancel(Component.literal("Space Pump is not enabled by Space Elevator"));
        }

        int parallelLimit = pump.getMaxPumpParallel();

        int parallels = ParallelLogic.getParallelAmountWithoutEU(pump, recipe, parallelLimit);

        if (parallels <= 0) {
            return ModifierFunction.cancel(Component.literal("Space Pump cannot fit recipe outputs"));
        }


        return ModifierFunction.builder()
                .parallels(parallels)
                .outputModifier(ContentModifier.multiplier(parallels))
                .eutMultiplier(parallels)
                .build();
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        textList.add(Component.literal("Space Pump Module: MK" + getModuleMk())
                .withStyle(ChatFormatting.AQUA));

        textList.add(Component.literal("Elevator Link: " + (isEnabledByElevator() ? "Active" : "Disabled"))
                .withStyle(isEnabledByElevator() ? ChatFormatting.GREEN : ChatFormatting.RED));

        textList.add(Component.literal("Max Pump Parallels: x" + getMaxPumpParallel())
                .withStyle(ChatFormatting.YELLOW));

        textList.add(Component.literal("Max Pump Power: " + String.format("%,d", getPumpMaxPower()) + " EU/t")
                .withStyle(ChatFormatting.GRAY));
    }
}
