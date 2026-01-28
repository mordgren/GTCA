package net.mordgren.gtca.common.machine.multiblock.electric.miner.capability;

import com.gregtechceu.gtceu.api.registry.GTRegistries;
import net.mordgren.gtca.GTCA;

public final class GTCASpaceMiningCapabilities {

    private static boolean INIT = false;
    public static final SpaceMiningInfoRecipeCapability SPACE_MINING_INFO = SpaceMiningInfoRecipeCapability.CAP;
    private GTCASpaceMiningCapabilities() {}
    public static void init() {
        if (INIT) return;
        INIT = true;
        GTRegistries.RECIPE_CAPABILITIES.unfreeze();
        try {
            GTRegistries.RECIPE_CAPABILITIES.register("gtca:space_mining_info", SPACE_MINING_INFO);
            GTCA.LOGGER.info("[SpaceMining] Registered recipe capability: gtca:space_mining_info");
        } finally {
            GTRegistries.RECIPE_CAPABILITIES.freeze();
        }
    }
}
