package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

public enum PlasmaTier {
    HELIUM(1.0, 800, GTMaterials.Helium),
    BISMUTH(1.2, 650, GTMaterials.Bismuth),
    RADON(1.4, 500, GTMaterials.Radon),
    TECHNETIUM(1.6, 350, GTMaterials.Technetium),
    PLUTONIUM_241(1.8, 250, GTMaterials.Plutonium241);

    private final double lootMultiplier;
    private final int usageMb;
    private final Material material;

    PlasmaTier(double lootMultiplier, int usageMb, Material material) {
        this.lootMultiplier = lootMultiplier;
        this.usageMb = usageMb;
        this.material = material;
    }

    public double lootMultiplier() {
        return lootMultiplier;
    }

    public int usageMb() {
        return usageMb;
    }

    public Material material() {
        return material;
    }

    public FluidStack plasma(int amount) {
        var prop = material.getProperty(PropertyKey.FLUID);
        if (prop == null) return FluidStack.EMPTY;

        Fluid fluid = prop.get(FluidStorageKeys.PLASMA);
        if (fluid == null) return FluidStack.EMPTY;

        return new FluidStack(fluid, Math.max(1, amount));
    }

    public FluidStack plasma() {
        return plasma(usageMb);
    }
}
