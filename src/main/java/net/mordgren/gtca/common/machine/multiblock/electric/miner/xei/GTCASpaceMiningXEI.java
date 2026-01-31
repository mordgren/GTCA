package net.mordgren.gtca.common.machine.multiblock.electric.miner.xei;

import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import net.minecraft.resources.ResourceLocation;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

public final class GTCASpaceMiningXEI {

    private static boolean INIT = false;
    public static final ResourceLocation HIDDEN_CATEGORY_ID = GTCA.id("space_miner_hidden");
    public static final GTRecipeCategory HIDDEN_CATEGORY =
            new GTRecipeCategory(HIDDEN_CATEGORY_ID.toString(), GTCARecipeTypes.SPACE_MINER)
                    .setXEIVisible(false);

    private GTCASpaceMiningXEI() {}

    public static void init() {
        if (INIT) return;
        INIT = true;
        GTRegistries.RECIPE_CATEGORIES.register(HIDDEN_CATEGORY_ID, HIDDEN_CATEGORY);
    }
}
