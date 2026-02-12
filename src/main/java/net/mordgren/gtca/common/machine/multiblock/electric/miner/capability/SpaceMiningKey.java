package net.mordgren.gtca.common.machine.multiblock.electric.miner.capability;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Objects;


public final class SpaceMiningKey {

    private final String asteroidId;
    private final String droneKey;
    private final String plasmaKey;

    public SpaceMiningKey(@NotNull String asteroidId) {
        this(asteroidId, "", "");
    }

    public SpaceMiningKey(@NotNull String asteroidId, @NotNull String droneKey, @NotNull String plasmaKey) {
        this.asteroidId = normalize(asteroidId);
        this.droneKey = normalize(droneKey);
        this.plasmaKey = normalize(plasmaKey);
    }

    public static SpaceMiningKey of(@NotNull ResourceLocation asteroidId, @NotNull String droneKey, @NotNull String plasmaKey) {
        return new SpaceMiningKey(asteroidId.toString(), droneKey, plasmaKey);
    }

    public static SpaceMiningKey of(@NotNull ResourceLocation asteroidId) {
        return new SpaceMiningKey(asteroidId.toString(), "", "");
    }

    public String asteroidId() {
        return asteroidId;
    }

    public String droneKey() {
        return droneKey;
    }

    public String plasmaKey() {
        return plasmaKey;
    }


    public String uniqueAsteroidPart() {
        return asteroidId;
    }

    public boolean isWildcard() {
        return asteroidId.isEmpty();
    }

    public boolean matches(@Nullable SpaceMiningKey other) {
        if (other == null) return false;
        if (this.isWildcard() || other.isWildcard()) return true;
        return Objects.equals(this.asteroidId, other.asteroidId);
    }

    private static String normalize(String s) {
        if (s == null) return "";
        return s.trim().toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return "SpaceMiningKey{" +
                "asteroidId='" + asteroidId + '\'' +
                ", droneKey='" + droneKey + '\'' +
                ", plasmaKey='" + plasmaKey + '\'' +
                '}';
    }
}