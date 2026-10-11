package net.mordgren.gtca.common.machine.multiblock.electric.purification;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;


public class PurificationRecipeLogic extends RecipeLogic {

    private final PurificationUnitMachine purificationMachine;

    public PurificationRecipeLogic(PurificationUnitMachine machine) {
        super(machine);
        this.purificationMachine = machine;
    }

    @Override
    public void setupRecipe(GTRecipe recipe) {
        if (!purificationMachine.preparePurificationCycle(recipe)) {
            return;
        }

        super.setupRecipe(recipe);

        if (isWorking() && getProgress() == 0 && getLastRecipe() != null) {
            purificationMachine.beginPurificationCycle(getLastRecipe());
        } else {
            purificationMachine.abortPurificationPreparation();
        }
    }

    @Override
    public void handleRecipeWorking() {
        int oldProgress = getProgress();
        GTRecipe runningRecipe = getLastRecipe();

        super.handleRecipeWorking();

        if (runningRecipe != null && getProgress() > oldProgress) {
            purificationMachine.tickPurificationCycle(runningRecipe);
        }
    }

    @Override
    public void onRecipeFinish() {
        GTRecipe runningRecipe = getLastRecipe();
        if (runningRecipe == null) {
            super.onRecipeFinish();
            return;
        }

        boolean success = purificationMachine.resolvePurificationCycle(runningRecipe);
        GTRecipe completionRecipe = purificationMachine.createCompletionRecipe(runningRecipe, success);

        this.lastRecipe = completionRecipe;
        super.onRecipeFinish();

        if (completionRecipe != runningRecipe && this.lastRecipe == completionRecipe) {
            this.lastRecipe = runningRecipe;
        }
    }
}
