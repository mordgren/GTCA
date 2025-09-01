package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyTooltip;
import com.gregtechceu.gtceu.api.gui.fancy.TooltipsPanel;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.mordgren.gtca.common.data.GTCABlocks;
import net.mordgren.gtca.common.data.machines.GTCAMachines;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpacePumpMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {

    public SpacePumpMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.tier = tier;
    }
    public final int tier;

    public static ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof SpacePumpMachine seModule)) {
            return RecipeModifier.nullWrongType(SpacePumpMachine.class, machine);
        }
        if (!seModule.isAttached()) {
            return ModifierFunction.IDENTITY;
        }
        return ModifierFunction.NULL;
    }

    private boolean isAttached() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                // Skip the controller block itself
                if (i == 0 && j == 0) continue;
                var blockPos = RelativeDirection.offsetPos(getPos(), getFrontFacing(), getUpwardsFacing(), isFlipped(),
                        i, j, 1);
                var blockState = this.getLevel().getBlockState(blockPos);
                if (!blockState.is(GTCAMachines.SPACE_ELEVATOR.getBlock()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void attachTooltips(TooltipsPanel tooltipsPanel) {
        super.attachTooltips(tooltipsPanel);
        tooltipsPanel.attachTooltips(new IFancyTooltip.Basic(
                () -> GuiTextures.INDICATOR_NO_STEAM.get(false),
                () -> List.of(Component.translatable("gtceu.multiblock.large_combustion_engine.obstructed")
                        .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))),
                this::isAttached,
                () -> null));
    }

}
