package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraftforge.fluids.FluidStack;

public enum PlasmaTier {
    HELIUM(1.0, GTMaterials.Helium),
    BISMUTH(1.2, GTMaterials.Bismuth),
    RADON(1.4, GTMaterials.Radon),
    TECHNETIUM(1.6, GTMaterials.Technetium),
    PLUTONIUM_241(1.8, GTMaterials.Plutonium241);

    private final double lootMultiplier;
    private final Material material;

    PlasmaTier(double lootMultiplier, Material material) {
        this.lootMultiplier = lootMultiplier;
        this.material = material;
    }

    public double lootMultiplier() {
        return lootMultiplier;
    }

    public Material material() {
        return material;
    }

    /** amount — в mB */
    public FluidStack plasma(int amount) {
        return material.getFluid(FluidStorageKeys.PLASMA, amount);
    }
}
