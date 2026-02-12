package net.mordgren.gtca.common.machine.multiblock.electric.miner.capability;

import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;

import java.util.Objects;


public final class SpaceMiningKeyMapIngredient extends AbstractMapIngredient {

    private final String asteroidId;
    private final int hash;

    public SpaceMiningKeyMapIngredient(SpaceMiningKey key) {
        this.asteroidId = key == null ? "" : key.uniqueAsteroidPart();
        this.hash = this.asteroidId.hashCode();
    }

    @Override
    protected int hash() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SpaceMiningKeyMapIngredient that = (SpaceMiningKeyMapIngredient) obj;
        return Objects.equals(this.asteroidId, that.asteroidId);
    }

    @Override
    public String toString() {
        return "SpaceMiningKeyMapIngredient{asteroidId='" + asteroidId + "'}";
    }
}