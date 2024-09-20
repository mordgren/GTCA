package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class GTCAPlasmas {
    public static void init(){


        GTCAMaterials.CelestialPlasma = new Material.Builder(GTCA.id("celestial_plasma"))
                .plasma()
                .color(0xffffff)
                .buildAndRegister()
                .setFormula("✦✧✦",true);


    }
}
