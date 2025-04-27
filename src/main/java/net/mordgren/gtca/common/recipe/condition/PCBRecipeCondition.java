package net.mordgren.gtca.common.recipe.condition;

import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.mordgren.gtca.common.data.GTCARecipeConditions;
import net.mordgren.gtca.common.machine.multiblock.electric.PCBFactoryMachine;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class PCBRecipeCondition extends RecipeCondition {

    public static final Codec<PCBRecipeCondition> CODEC = RecordCodecBuilder.create(instance -> RecipeCondition
            .isReverse(instance).and(
                    Codec.INT.fieldOf("casingTier").forGetter(it -> it.tier))
            .apply(instance, PCBRecipeCondition::new));

    public static final PCBRecipeCondition INSTANCE = new PCBRecipeCondition();

    private int tier = 0;

    public static final int MKI = 8;
    public static final int MKII = 9;
    public static final int MKIII = 10;

    public static Map<Integer, String> CASING_TIERS = Map.of(
            MKI, "gtca.recipe.condition.tier_casing.tier.mki",
            MKII, "gtca.recipe.condition.tier_casing.tier.mkii",
            MKIII, "gtca.recipe.condition.tier_casing.tier.mkiii");

    public PCBRecipeCondition(int tier) {
        this.tier = Mth.clamp(tier, 8, 10);
    }

    public PCBRecipeCondition(Boolean isReverse, int tier) {
        super(isReverse);
        this.tier = Mth.clamp(tier, 8, 10);
    }

    public PCBRecipeCondition() {}

    @Override
    public RecipeConditionType<?> getType() {
        return GTCARecipeConditions.PCB_CONDITION;
    }

    @Override
    public Component getTooltips() {
        return Component.translatable(
                "gtca.recipe.condition.tier_casing.tooltip",
                Component.translatable(CASING_TIERS.get(tier)));
    }

    @Override
    public boolean test(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        if (recipeLogic.machine instanceof PCBFactoryMachine pcbProps) {
            return pcbProps.getTier() >= tier;
        }
        return false;
    }

    @Override
    public RecipeCondition createTemplate() {
        return new PCBRecipeCondition();
    }

    @Override
    public @NotNull JsonObject serialize() {
        JsonObject value = super.serialize();
        value.addProperty("casingTier", tier);
        return value;
    }

    @Override
    public RecipeCondition deserialize(@NotNull JsonObject config) {
        super.deserialize(config);
        this.tier = GsonHelper.getAsInt(config, "casingTier", 0);
        return this;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeInt(tier);
    }

    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        this.tier = buf.readInt();
        return this;
    }

}
