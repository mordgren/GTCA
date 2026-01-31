package net.mordgren.gtca.common.machine.multiblock.electric.miner.debuq;

import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

@Mod.EventBusSubscriber(modid = GTCA.MOD_ID)
public final class SpaceMiningRecipeDebug {

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent e) {
        try {
            RecipeManager rm = e.getServer().getRecipeManager();
            RecipeType<?> type = GTCARecipeTypes.SPACE_MINER;

            int count = tryCountByTypeMethod(rm, type);
            if (count >= 0) {
                GTCA.LOGGER.info("[SpaceMining][Debug] Loaded SPACE_MINER recipes = {} (via byType-like method)", count);
                return;
            }

            count = tryCountFromInternalMap(rm, type);
            if (count >= 0) {
                GTCA.LOGGER.info("[SpaceMining][Debug] Loaded SPACE_MINER recipes = {} (via internal recipes map)", count);
                return;
            }

            GTCA.LOGGER.warn("[SpaceMining][Debug] Could not determine SPACE_MINER recipe count (no suitable access found).");

        } catch (Throwable t) {
            GTCA.LOGGER.error("[SpaceMining][Debug] Failed to read recipes from RecipeManager", t);
        }
    }

    private static int tryCountByTypeMethod(RecipeManager rm, RecipeType<?> type) {
        try {
            for (Method m : rm.getClass().getMethods()) {
                if (m.getParameterCount() != 1) continue;
                if (!RecipeType.class.isAssignableFrom(m.getParameterTypes()[0])) continue;

                Class<?> ret = m.getReturnType();
                if (!Map.class.isAssignableFrom(ret)) continue;

                Object res = m.invoke(rm, type);
                if (res instanceof Map<?, ?> map) return map.size();
            }
        } catch (Throwable ignored) {}
        return -1;
    }


    private static int tryCountFromInternalMap(RecipeManager rm, RecipeType<?> type) {
        try {
            for (Field f : rm.getClass().getDeclaredFields()) {
                if (!Map.class.isAssignableFrom(f.getType())) continue;
                f.setAccessible(true);

                Object obj = f.get(rm);
                if (!(obj instanceof Map<?, ?> outer)) continue;

                Object inner = outer.get(type);
                if (inner instanceof Map<?, ?> innerMap) return innerMap.size();
            }
        } catch (Throwable ignored) {}
        return -1;
    }
}
