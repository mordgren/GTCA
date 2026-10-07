package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorLinkedModuleMachine;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;

import java.util.List;

public class SpaceAssemblerMachine extends ElevatorLinkedModuleMachine {

    public static final String REQUIRED_MODULE_MK_KEY = "required_module_mk";

    public SpaceAssemblerMachine(IMachineBlockEntity holder, int moduleTier) {
        super(holder, moduleTier);
    }

    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return ElevatorModuleKind.ASSEMBLER;
    }

    @Override
    public boolean requiresComputation() {
        return false;
    }

    @Override
    public boolean isValidForElevator() {
        return isFormed();
    }

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if (!(machine instanceof SpaceAssemblerMachine assembler)) {
            return RecipeModifier.nullWrongType(
                    SpaceAssemblerMachine.class,
                    machine
            );
        }

        if (!assembler.isFormed()) {
            return ModifierFunction.cancel(
                    Component.literal("Space Assembler structure is not formed")
            );
        }

        if (!assembler.isEnabledByElevator()) {
            return ModifierFunction.cancel(
                    Component.literal("Space Assembler is not enabled by Space Elevator")
            );
        }

        int requiredMk = getRequiredModuleMk(recipe);
        int currentMk = assembler.getModuleMk();

        if (requiredMk > currentMk) {
            return ModifierFunction.cancel(
                    Component.literal(
                            "Requires Space Assembler MK" + requiredMk +
                                    ", current MK" + currentMk
                    )
            );
        }

        return ModifierFunction.IDENTITY;
    }

    public static int getRequiredModuleMk(GTRecipe recipe) {
        if (recipe == null || recipe.data == null) {
            return 1;
        }

        if (!recipe.data.contains(REQUIRED_MODULE_MK_KEY)) {
            return 1;
        }

        int mk = recipe.data.getInt(REQUIRED_MODULE_MK_KEY);

        return Math.max(1, Math.min(3, mk));
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        textList.add(
                Component.literal("Space Assembler Module: MK" + getModuleMk())
                        .withStyle(ChatFormatting.AQUA)
        );

        textList.add(
                Component.literal(
                                "Elevator Link: " +
                                        (isEnabledByElevator() ? "Active" : "Disabled")
                        )
                        .withStyle(
                                isEnabledByElevator()
                                        ? ChatFormatting.GREEN
                                        : ChatFormatting.RED
                        )
        );
    }
}
