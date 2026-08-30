package net.mordgren.gtca.common.machine.multiblock.electric.miner;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorLinkedModuleMachine;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.SpaceMiningRecipeDataKeys;

import java.util.List;

public class SpaceMinerMachine extends ElevatorLinkedModuleMachine {

    public SpaceMinerMachine(IMachineBlockEntity holder, int moduleTier) {
        super(holder, moduleTier);
    }

    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return ElevatorModuleKind.MINER;
    }

    @Override
    public boolean requiresComputation() {
        return true;
    }

    @Override
    public boolean isValidForElevator() {
        return isFormed();
    }

    public int getModuleMk() {
        return tierToModuleMk(moduleTier);
    }

    public static int tierToModuleMk(int tier) {
        return switch (tier) {
            case GTValues.LuV -> 1;
            case GTValues.ZPM -> 2;
            case GTValues.UV -> 3;
            default -> 0;
        };
    }

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if (!(machine instanceof SpaceMinerMachine miner)) {
            return RecipeModifier.nullWrongType(SpaceMinerMachine.class, machine);
        }

        if (!miner.isFormed()) {
            return ModifierFunction.cancel(Component.literal("Space Miner structure is not formed"));
        }

        if (!miner.isEnabledByElevator()) {
            return ModifierFunction.cancel(Component.literal("Space Miner is not enabled by Space Elevator"));
        }

        int requiredMk = getRequiredModuleMk(recipe);
        int currentMk = miner.getModuleMk();

        if (requiredMk > currentMk) {
            return ModifierFunction.cancel(Component.literal(
                    "Requires Space Miner MK" + requiredMk + ", current MK" + currentMk
            ));
        }

        return ModifierFunction.IDENTITY;
    }

    private static int getRequiredModuleMk(GTRecipe recipe) {
        if (recipe.data == null) {
            return 1;
        }

        if (!recipe.data.contains(SpaceMiningRecipeDataKeys.REQUIRED_MODULE_MK)) {
            return 1;
        }

        return Math.max(1, recipe.data.getInt(SpaceMiningRecipeDataKeys.REQUIRED_MODULE_MK));
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        textList.add(Component.literal("Space Miner Module: MK" + getModuleMk())
                .withStyle(ChatFormatting.AQUA));

        textList.add(Component.literal("Elevator Link: " + (isEnabledByElevator() ? "Active" : "Disabled"))
                .withStyle(isEnabledByElevator() ? ChatFormatting.GREEN : ChatFormatting.RED));
    }
}
