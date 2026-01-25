package net.mordgren.gtca.common.machine.multiblock.electric.miner.registry;

import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.AsteroidDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

public final class SpaceMiningRegistry {

    private static final Map<ResourceLocation, AsteroidDefinition> ASTEROIDS = new LinkedHashMap<>();

    private SpaceMiningRegistry() {}

    public static void register(AsteroidDefinition def) {
        Objects.requireNonNull(def, "def");
        var id = def.id();
        if (ASTEROIDS.containsKey(id)) {
            throw new IllegalStateException("Asteroid already registered: " + id);
        }
        ASTEROIDS.put(id, def);
    }

    public static AsteroidDefinition get(ResourceLocation id) {
        return ASTEROIDS.get(id);
    }

    public static Collection<AsteroidDefinition> all() {
        return Collections.unmodifiableCollection(ASTEROIDS.values());
    }
}
