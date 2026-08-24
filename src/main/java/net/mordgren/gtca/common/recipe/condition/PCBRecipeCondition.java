package net.mordgren.gtca.common.recipe.condition;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.mordgren.gtca.common.data.GTCARecipeConditions;
import net.mordgren.gtca.common.machine.multiblock.electric.PCBFactoryMachine;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class PCBRecipeCondition extends RecipeCondition<PCBRecipeCondition> {

    public static final Codec<PCBRecipeCondition> CODEC = RecordCodecBuilder.create(instance -> RecipeCondition
            .isReverse(instance).and(
                    Codec.INT.fieldOf("casingTier").forGetter(it -> it.tier))
            .apply(instance, PCBRecipeCondition::new));

    public static final PCBRecipeCondition INSTANCE = new PCBRecipeCondition();

    private int tier = 0;

    public static final int MKI = 8;
    public static final int MKII = 9;
    public static final int MKIII = 10;

    public static final Map<Integer, String> CASING_TIERS = Map.of(
            MKI, "gtca.recipe.condition.tier_casing.tier.mki",
            MKII, "gtca.recipe.condition.tier_casing.tier.mkii",
            MKIII, "gtca.recipe.condition.tier_casing.tier.mkiii"
    );

    public PCBRecipeCondition(int tier) {
        this.tier = Mth.clamp(tier, 8, 10);
    }

    public PCBRecipeCondition(boolean isReverse, int tier) {
        super(isReverse);
        this.tier = Mth.clamp(tier, 8, 10);
    }

    public PCBRecipeCondition() {}

    @Override
    public RecipeConditionType<PCBRecipeCondition> getType() {
        return GTCARecipeConditions.PCB_CONDITION;
    }

    @Override
    public Component getTooltips() {
        return Component.translatable(
                "gtca.recipe.condition.tier_casing.tooltip",
                Component.translatable(CASING_TIERS.get(tier))
        );
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe recipe, @NotNull RecipeLogic recipeLogic) {
        if (recipeLogic.machine instanceof PCBFactoryMachine pcbFactory) {
            return pcbFactory.getTier() >= tier;
        }
        return false;
    }

    @Override
    public PCBRecipeCondition createTemplate() {
        return new PCBRecipeCondition();
    }
}
