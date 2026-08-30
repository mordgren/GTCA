package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;

public enum DrillMaterialTier {
    STEEL(1),
    TITANIUM(2),
    TUNGSTEN_STEEL(3),
    NAQUADAH(4),
    NAQUADAH_ALLOY(5),
    NEUTRONIUM(6);

    private final int level;

    DrillMaterialTier(int level) {
        this.level = level;
    }

    public int level() {
        return level;
    }

    public boolean isAtMost(DrillMaterialTier other) {
        return this.level <= other.level;
    }

    public boolean isBetween(DrillMaterialTier min, DrillMaterialTier max) {
        return this.level >= min.level && this.level <= max.level;
    }

    public Material material() {
        return switch (this) {
            case STEEL -> GTMaterials.Steel;
            case TITANIUM -> GTMaterials.Titanium;
            case TUNGSTEN_STEEL -> GTMaterials.TungstenSteel;
            case NAQUADAH -> GTMaterials.Naquadah;
            case NAQUADAH_ALLOY -> GTMaterials.NaquadahAlloy;
            case NEUTRONIUM -> GTMaterials.Neutronium;
        };
    }
}
