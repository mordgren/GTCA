package net.mordgren.gtca.common.machine.multiblock.electric.miner.logic;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfo;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfoRecipeCapability;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class SpaceMinerRandomLootRecipeLogic extends RecipeLogic {

    @Persisted private boolean lootRolled = false;
    @Persisted private long lootSeed = 0L;
    @Persisted private int rolledStacks = 0;

    public SpaceMinerRandomLootRecipeLogic(IRecipeLogicMachine machine) {
        super(machine);
    }

    private boolean isServerSide() {
        var lvl = this.machine.self().getLevel();
        return lvl != null && !lvl.isClientSide;
    }

    @Override
    public void setupRecipe(GTRecipe recipe) {
        super.setupRecipe(recipe);

        if (!isServerSide()) return;


        lootRolled = false;
        rolledStacks = 0;
        lootSeed = 0L;

        rollLoot(recipe);
    }

    private void rollLoot(GTRecipe recipe) {
        if (recipe == null) return;

        SpaceMiningInfo info = getInfo(recipe);
        if (info == null) {
            lootRolled = false;
            return;
        }

        int min = Math.max(1, info.sizeMinStacks());
        int max = Math.max(min, info.sizeMaxStacks());
        var mm = this.machine.self();
        var level = mm.getLevel();
        long pos = mm.getPos().asLong();
        long salt = (level == null) ? System.nanoTime() : (level.random.nextLong() ^ System.nanoTime());
        int recipeHash = (recipe.id == null) ? 0 : recipe.id.hashCode();

        lootSeed = 0x9E3779B97F4A7C15L ^ pos ^ salt ^ recipeHash;
        rolledStacks = new SplittableRandom(lootSeed).nextInt(min, max + 1);
        lootRolled = true;
    }

    @Override
    public void onRecipeFinish() {
        if (lastRecipe == null) return;

        if (!isServerSide()) {
            super.onRecipeFinish();
            return;
        }

        if (!lootRolled) {
            rollLoot(lastRecipe);
        }

        SpaceMiningInfo info = getInfo(lastRecipe);
        if (info == null) {
            super.onRecipeFinish();
            resetRoll();
            return;
        }

        int min = Math.max(1, info.sizeMinStacks());
        int max = Math.max(min, info.sizeMaxStacks());
        int avg = Math.max(1, (min + max) / 2);

        int stacks = lootRolled ? Math.max(1, rolledStacks) : avg;
        double factor = stacks / (double) avg;

        List<Content> base = lastRecipe.outputs.get(ItemRecipeCapability.CAP);
        if (base == null || base.isEmpty()) {
            super.onRecipeFinish();
            resetRoll();
            return;
        }


        List<Content> scaled = scaleItemOutputs(base, factor);

        List<Content> saved = lastRecipe.outputs.put(ItemRecipeCapability.CAP, scaled);
        try {
            ActionResult sim = RecipeHelper.handleRecipe(
                    machine, lastRecipe, IO.OUT, lastRecipe.outputs, this.chanceCaches, false, true
            );
            if (!sim.isSuccess()) {
                setWaiting(sim.reason() != null ? sim.reason() : Component.literal("Output is full"));
                return;
            }

            System.out.println("[GTCA] avg=" + avg + " stacks=" + stacks + " factor=" + factor);

            super.onRecipeFinish();

        } finally {
            if (saved != null) {
                lastRecipe.outputs.put(ItemRecipeCapability.CAP, saved);
            } else {
                lastRecipe.outputs.remove(ItemRecipeCapability.CAP);
            }
        }

        resetRoll();
    }

    private void resetRoll() {
        lootRolled = false;
        rolledStacks = 0;
        lootSeed = 0L;
    }

    private List<Content> scaleItemOutputs(List<Content> base, double factor) {
        List<Content> scaled = new ArrayList<>(base.size());

        for (Content c : base) {
            Object raw = c.getContent();

            if (raw instanceof ItemStack is) {
                ItemStack copy = is.copy();

                long newCountL = Math.round(copy.getCount() * factor);
                int newCount = (int) Math.max(1L, Math.min((long) Integer.MAX_VALUE, newCountL));

                copy.setCount(newCount);
                scaled.add(new Content(copy, c.chance, c.maxChance, c.tierChanceBoost));
                continue;
            }

            if (raw instanceof SizedIngredient si) {
                long newAmtL = Math.round(si.getAmount() * factor);
                int newAmt = (int) Math.max(1L, Math.min((long) Integer.MAX_VALUE, newAmtL));

                SizedIngredient newIng = SizedIngredient.create(si.getInner(), newAmt);
                scaled.add(new Content(newIng, c.chance, c.maxChance, c.tierChanceBoost));
                continue;
            }

            scaled.add(new Content(raw, c.chance, c.maxChance, c.tierChanceBoost));
        }


        if (!scaled.isEmpty()) {
            Object raw0 = scaled.get(0).getContent();
            if (raw0 instanceof ItemStack s0) {
                System.out.println("[GTCA] scaled[0] count=" + s0.getCount());
            } else if (raw0 instanceof SizedIngredient si0) {
                System.out.println("[GTCA] scaled[0] amount=" + si0.getAmount());
            } else {
                System.out.println("[GTCA] scaled[0] type=" + raw0.getClass().getName());
            }
        }

        return scaled;
    }

    private SpaceMiningInfo getInfo(GTRecipe recipe) {
        if (recipe == null) return null;
        List<Content> list = recipe.getOutputContents(SpaceMiningInfoRecipeCapability.CAP);
        if (list == null || list.isEmpty()) {
            list = recipe.getInputContents(SpaceMiningInfoRecipeCapability.CAP);
        }
        if (list == null || list.isEmpty()) return null;
        Object raw = list.get(0).getContent();
        return (raw instanceof SpaceMiningInfo info) ? info : null;
    }
}