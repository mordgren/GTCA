package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import lombok.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ChemGenProps extends WorkableElectricMultiblockMachine implements ITieredMachine {

    private static final FluidStack OXYGEN_STACK = GTMaterials.Oxygen.getFluid(20 * FluidHelper.getBucket() / 1000);
    private static final FluidStack LIQUID_OXYGEN_STACK = GTMaterials.Oxygen.getFluid(FluidStorageKeys.LIQUID,
            80 * FluidHelper.getBucket() / 1000);

    @Getter
    private final int tier;
    private boolean isOxygenBoosted = false;

    public ChemGenProps(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.tier = tier;
    }

    private boolean isExtreme() {
        return getTier() > GTValues.EV;
    }

    public boolean isBoostAllowed() {
        return getMaxVoltage() >= GTValues.V[getTier() + 1];
    }

    @Override
    public long getOverclockVoltage() {
        if (isOxygenBoosted)
            return GTValues.V[tier] * 2;
        else
            return GTValues.V[tier];
    }

    protected GTRecipe getBoostRecipe() {
        return GTRecipeBuilder.ofRaw().inputFluids(isExtreme() ? LIQUID_OXYGEN_STACK : OXYGEN_STACK).buildRawRecipe();
    }

    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @NotNull GTRecipe recipe) {
        if (machine instanceof ChemGenProps engineMachine) {
            var EUt = RecipeHelper.getOutputEUt(recipe);
            if (EUt > 0)  {
                var maxParallel = (int) (engineMachine.getOverclockVoltage() / EUt);
                var parallelResult = GTRecipeModifiers.fastParallel(engineMachine, recipe, maxParallel, false);
                if (engineMachine.isOxygenBoosted) {
                    recipe = parallelResult.getFirst() == recipe ? recipe.copy() : parallelResult.getFirst();
                    long eut = (long) (EUt * parallelResult.getSecond() * (engineMachine.isExtreme() ? 2 : 1.5));
                    recipe.tickOutputs.put(EURecipeCapability.CAP, List.of(new Content(eut, ChanceLogic.getMaxChancedValue(), ChanceLogic.getMaxChancedValue(), 0, null, null)));
                } else {
                    recipe = parallelResult.getFirst();
                }
                return recipe;
            }
        }
        return null;
    }

    @Override
    public boolean onWorking() {
        boolean value = super.onWorking();
        val totalContinuousRunningTime = recipeLogic.getTotalContinuousRunningTime();
        if ((totalContinuousRunningTime == 1 || totalContinuousRunningTime % 20 == 0) && isBoostAllowed()) {
            var boosterRecipe = getBoostRecipe();
            this.isOxygenBoosted = boosterRecipe.matchRecipe(this).isSuccess() &&
                    boosterRecipe.handleRecipeIO(IO.IN, this, this.recipeLogic.getChanceCaches());
        }
        return value;
    }

    @Override
    public boolean dampingWhenWaiting() {
        return false;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (isFormed()) {
            if (isBoostAllowed()) {
                if (!isExtreme()) {
                    if (isOxygenBoosted) {
                        textList.add(Component.translatable("gtceu.multiblock.large_combustion_engine.oxygen_boosted"));
                    } else {
                        textList.add(Component
                                .translatable("gtceu.multiblock.large_combustion_engine.supply_oxygen_to_boost"));
                    }
                } else {
                    if (isOxygenBoosted) {
                        textList.add(Component
                                .translatable("gtceu.multiblock.large_combustion_engine.liquid_oxygen_boosted"));
                    } else {
                        textList.add(Component.translatable(
                                "gtceu.multiblock.large_combustion_engine.supply_liquid_oxygen_to_boost"));
                    }
                }
            } else {
                textList.add(Component.translatable("gtceu.multiblock.large_combustion_engine.boost_disallowed"));
            }
        }
    }
}