package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTMaterialAdjustments {
    public static void init() {

        addDust(Zirconium);
        addDust(Terbium);
        addDust(Technetium);

        addIngot(Holmium);
        addIngot(Germanium);
        addIngot(Gadolinium);

        addTool(Naquadah);

        Carbon.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);
        NiobiumTitanium.addFlags(MaterialFlags.GENERATE_FRAME);
        Titanium.addFlags(MaterialFlags.GENERATE_FOIL);
        Stellite100.addFlags(MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_RING);
        Duranium.addFlags(GENERATE_FRAME);
        RedSteel.addFlags(GENERATE_FOIL);

        ensureFluidKey(AmmoniumChloride, FluidStorageKeys.LIQUID);
        ensureFluidKey(Technetium, FluidStorageKeys.LIQUID);
        ensureFluidKey(Bismuth, FluidStorageKeys.LIQUID);
        ensureFluidKey(Plutonium241, FluidStorageKeys.LIQUID);

        ensureFluidKey(Technetium, FluidStorageKeys.PLASMA);
        ensureFluidKey(Bismuth, FluidStorageKeys.PLASMA);
        ensureFluidKey(Plutonium241, FluidStorageKeys.PLASMA);
        ensureFluidKey(Radon, FluidStorageKeys.PLASMA);

    }

    public static void addDust(Material material) {
        if (!material.hasProperty(PropertyKey.DUST)) {
            material.setProperty(PropertyKey.DUST, new DustProperty());
        }
    }
    public static void addIngot(Material material) {
        if (!material.hasProperty(PropertyKey.INGOT)) {
            material.setProperty(PropertyKey.INGOT, new IngotProperty());
        }
    }
    public static void addOre(Material material) {
        if (!material.hasProperty(PropertyKey.ORE)) {
            material.setProperty(PropertyKey.ORE, new OreProperty());
        }
    }
    public static void addTool(Material material) {
        if (!material.hasProperty(PropertyKey.TOOL)) {
            material.setProperty(PropertyKey.TOOL, new ToolProperty());
        }
    }
    private static void ensureFluidKey(Material material, FluidStorageKey key) {
        if (material == null) return;
        FluidProperty prop = material.getProperty(PropertyKey.FLUID);
        if (prop == null) {

            prop = new FluidProperty();
            prop.enqueueRegistration(key, new FluidBuilder());
            material.setProperty(PropertyKey.FLUID, prop);
            return;
        }
        if (prop.get(key) != null) return;
        if (prop.getStorage().getQueuedBuilder(key) != null) return;
        prop.enqueueRegistration(key, new FluidBuilder());
    }
}





