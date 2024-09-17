package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import net.mordgren.gtca.common.data.GTCAMaterials;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class MaterialFlagAddition {
    public static void init() {

        OreProperty oreProp = GTCAMaterials.RedZircon.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Zirconium);
        oreProp.setWashedIn(SodiumPersulfate);

        oreProp = (OreProperty) GTCAMaterials.RedFuchsite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Chromium);
        oreProp.setWashedIn(SodiumPersulfate);

        oreProp = (OreProperty) GTCAMaterials.GreenFuchsite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Aluminium);
        oreProp.setWashedIn(SodiumPersulfate);

        oreProp = (OreProperty) GTCAMaterials.Fayalite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Iron);
        oreProp.setWashedIn(SodiumPersulfate);

    }
}



