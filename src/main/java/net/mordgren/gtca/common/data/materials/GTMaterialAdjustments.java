package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;

import javax.tools.Tool;

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
        addFluid(AmmoniumChloride);
        addTool(Naquadah);
        addPlasma(Technetium);
        addPlasma(Bismuth);
        addPlasma(Plutonium241);


        Carbon.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);
        NiobiumTitanium.addFlags(MaterialFlags.GENERATE_FRAME);
        Titanium.addFlags(MaterialFlags.GENERATE_FOIL);
        Stellite100.addFlags(MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_RING);
        Duranium.addFlags(GENERATE_FRAME);
        RedSteel.addFlags(GENERATE_FOIL);

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

    public static void addFluid(Material material) {
        if (!material.hasProperty(PropertyKey.FLUID)) {
            material.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));
        }
    }
    public static void addPlasma(Material material) {
        FluidProperty prop;
        if (!material.hasProperty(PropertyKey.FLUID)) {
            prop = new FluidProperty();
            material.setProperty(PropertyKey.FLUID, prop);
        } else {
            prop = material.getProperty(PropertyKey.FLUID);
        }
        try {
            if (material.getFluidBuilder(FluidStorageKeys.PLASMA) != null) return;
        } catch (IllegalArgumentException ignored) {
        }
        try {
            prop.enqueueRegistration(FluidStorageKeys.PLASMA, new FluidBuilder().state(FluidState.PLASMA));
        } catch (IllegalArgumentException ignored) {
        }

    }}



