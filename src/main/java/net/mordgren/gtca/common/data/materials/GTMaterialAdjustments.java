package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.DustProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.GENERATE_FOIL;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.GENERATE_FRAME;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTMaterialAdjustments {
    public static void init() {

        NiobiumTitanium.addFlags(MaterialFlags.GENERATE_FRAME);
        Titanium.addFlags(MaterialFlags.GENERATE_FOIL);
        Stellite100.addFlags(MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_RING);
        Duranium.addFlags(GENERATE_FRAME);
        RedSteel.addFlags(GENERATE_FOIL);


        addDust(Zirconium);
        addDust(Terbium);
        addIngot(Holmium);
        addIngot(Germanium);
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
}



