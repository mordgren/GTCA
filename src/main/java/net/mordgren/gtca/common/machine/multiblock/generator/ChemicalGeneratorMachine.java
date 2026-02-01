package net.mordgren.gtca.common.machine.multiblock.generator;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.ingredient.EnergyStack;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTMath;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.*;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Iterator;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ChemicalGeneratorMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            ChemicalGeneratorMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    private static final FluidStack OXYGEN_STACK = GTMaterials.Oxygen.getFluid(20);
    private static final FluidStack LIQUID_OXYGEN_STACK = GTMaterials.Oxygen.getFluid(FluidStorageKeys.LIQUID, 80);

    @Getter
    private final int tier;

    @DescSynced
    private boolean isOxygenBoosted = false;
    private int runningTimer = 0;

    public ChemicalGeneratorMachine(IMachineBlockEntity holder, int tier) {
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
        if (isOxygenBoosted) return GTValues.V[tier] * 2;
        else return GTValues.V[tier];
    }

    protected GTRecipe getBoostRecipe() {
        return GTRecipeBuilder.ofRaw().inputFluids(isExtreme() ? LIQUID_OXYGEN_STACK : OXYGEN_STACK).buildRawRecipe();
    }

    protected double getProductionBoost() {
        if (!isOxygenBoosted) return 1;
        return isExtreme() ? 2.0 : 1.5;
    }


    public static ModifierFunction recipeModifier(MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof ChemicalGeneratorMachine engineMachine)) {
            return RecipeModifier.nullWrongType(ChemicalGeneratorMachine.class, machine);
        }
        EnergyStack EUt = recipe.getOutputEUt();
        if (!EUt.isEmpty()) {
            int maxParallel = (int) (engineMachine.getOverclockVoltage() / EUt.getTotalEU()); // get maximum parallel
            int actualParallel = ParallelLogic.getParallelAmount(engineMachine, recipe, maxParallel);
            double eutMultiplier = actualParallel * engineMachine.getProductionBoost();

            return ModifierFunction.builder()
                    .inputModifier(ContentModifier.multiplier(actualParallel))
                    .outputModifier(ContentModifier.multiplier(actualParallel))
                    .eutMultiplier(eutMultiplier)
                    .parallels(actualParallel)
                    .build();
        }
        return ModifierFunction.NULL;
    }

        @Override
        public boolean onWorking() {
            boolean value = super.onWorking();
            // check boost fluid
            if (isBoostAllowed()) {
                var boosterRecipe = getBoostRecipe();
                this.isOxygenBoosted = RecipeHelper.matchRecipe(this, boosterRecipe).isSuccess() &&
                        RecipeHelper.handleRecipeIO(this, boosterRecipe, IO.IN, this.recipeLogic.getChanceCaches())
                                .isSuccess();
            }

            runningTimer++;
            if (runningTimer > 72000) runningTimer %= 72000; // reset once every hour of running

            return value;
        }

    @Override
    public boolean regressWhenWaiting() {
        return false;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        MultiblockDisplayText.Builder builder = MultiblockDisplayText.builder(textList, isFormed())
                .setWorkingStatus(recipeLogic.isWorkingEnabled(), recipeLogic.isActive());

        long lastEUt = recipeLogic.getLastRecipe() != null ?
                recipeLogic.getLastRecipe().getOutputEUt().getTotalEU() : 0;
        if (isExtreme()) {
            builder.addEnergyProductionLine(GTValues.V[tier + 1], lastEUt);
        } else {
            builder.addEnergyProductionAmpsLine(GTValues.V[tier] * 3, 3);
        }

        if (isActive() && isWorkingEnabled()) {
            builder.addCurrentEnergyProductionLine(lastEUt);
        }

        builder.addFuelNeededLine(getRecipeFluidInputInfo(), recipeLogic.getDuration());

        if (isFormed && isOxygenBoosted) {
            final var key = isExtreme() ? "gtceu.multiblock.large_combustion_engine.liquid_oxygen_boosted" :
                    "gtceu.multiblock.large_combustion_engine.oxygen_boosted";
            builder.addCustom(tl -> tl.add(Component.translatable(key).withStyle(ChatFormatting.AQUA)));
        }

        builder.addWorkingStatusLine();
    }

    @Nullable
    public String getRecipeFluidInputInfo() {
        GTRecipe recipe = recipeLogic.getLastRecipe();
        if (recipe == null) {
            Iterator<GTRecipe> iterator = recipeLogic.searchRecipe();
            recipe = iterator != null && iterator.hasNext() ? iterator.next() : null;
            if (recipe == null) return null;
        }
        FluidStack requiredFluidInput = RecipeHelper.getInputFluids(recipe).get(0);

        long ocAmount = getMaxVoltage() / recipe.getOutputEUt().getTotalEU();
        int neededAmount = GTMath.saturatedCast(ocAmount * requiredFluidInput.getAmount());
        return ChatFormatting.RED + FormattingUtil.formatNumbers(neededAmount) + "mB";
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}