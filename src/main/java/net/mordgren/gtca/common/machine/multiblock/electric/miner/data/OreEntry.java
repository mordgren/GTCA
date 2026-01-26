package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import java.util.Objects;

public record OreEntry(Material material, double percent01) {

    public OreEntry {
        Objects.requireNonNull(material, "material");

        if (Double.isNaN(percent01) || Double.isInfinite(percent01)) {
            throw new IllegalArgumentException("percent01 must be a finite number");
        }
        if (percent01 <= 0.0 || percent01 > 1.0) {
            throw new IllegalArgumentException("percent01 must be in (0.0, 1.0]");
        }
    }
}
