package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

public enum DrillMaterialTier {
    STEEL(1),
    TITANIUM(2),
    TUNGSTEN_STEEL(3),
    NAQUADAH(4),
    NAQUADAH_ALLOY(5),
    NEUTRONIUM(6),
    COSMIC_NEUTRONIUM(7); // пока не используешь — но пусть будет

    private final int tier;

    DrillMaterialTier(int tier) {
        this.tier = tier;
    }

    public int tier() {
        return tier;
    }

    public boolean isBetween(DrillMaterialTier min, DrillMaterialTier max) {
        return this.tier >= min.tier && this.tier <= max.tier;
    }
}
