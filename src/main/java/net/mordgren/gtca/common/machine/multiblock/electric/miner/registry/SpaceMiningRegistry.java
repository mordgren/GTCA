package net.mordgren.gtca.common.machine.multiblock.electric.miner.registry;

import net.minecraft.resources.ResourceLocation;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.AsteroidDefinition;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class SpaceMiningRegistry {

    private static final Map<ResourceLocation, AsteroidDefinition> ASTEROIDS = new LinkedHashMap<>();

    private SpaceMiningRegistry() {}

    public static void clear() {
        ASTEROIDS.clear();
    }

    public static void register(AsteroidDefinition def) {
        Objects.requireNonNull(def, "def");

        ResourceLocation id = def.id();

        if (ASTEROIDS.containsKey(id)) {
            throw new IllegalStateException("Asteroid already registered: " + id);
        }

        ASTEROIDS.put(id, def);
        GTCA.LOGGER.info("[SpaceMining] Registered asteroid {}", id);
    }

    public static AsteroidDefinition get(ResourceLocation id) {
        return ASTEROIDS.get(id);
    }

    public static Collection<AsteroidDefinition> all() {
        return Collections.unmodifiableCollection(ASTEROIDS.values());
    }

    public static int size() {
        return ASTEROIDS.size();
    }
}