package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import net.mordgren.gtca.common.data.GTCAMaterials;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class MaterialFlagAddition {
    public static void init() {
        OreProperty oreProp = GTCAMaterials.Neutronite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Sulfur);
        oreProp.setWashedIn(SodiumPersulfate);
    }
}
