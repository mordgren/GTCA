package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

public enum PlasmaTier {
    HELIUM(1.0),
    BISMUTH(1.2),
    RADON(1.4),
    TECHNETIUM(1.6),
    PLUTONIUM_241(1.8);

    private final double lootMultiplier;

    PlasmaTier(double lootMultiplier) {
        this.lootMultiplier = lootMultiplier;
    }

    public double lootMultiplier() {
        return lootMultiplier;
    }
}
