package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTElements;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class GTCAPlasmas {
    public static void init(){


        GTCAMaterials.CelestialPlasma = new Material.Builder(GTCA.id("celestial_plasma"))
                .plasma()
                .color(0xffffff)
                .buildAndRegister()
                .setFormula("✦✧✦",true);

        GTCAMaterials.HydrogenPlasma = new Material.Builder(GTCA.id("hydrogen_plasma"))
                .plasma(10000)
                .color(0x003bd7)
                .element(GTElements.H)
                .buildAndRegister();

        GTCAMaterials.SulfurPlasma = new Material.Builder(GTCA.id("sulfur_plasma"))
                .plasma(10000)
                .color(0xe5ff53)
                .element(GTElements.S)
                .buildAndRegister();

        GTCAMaterials.CalciumPlasma = new Material.Builder(GTCA.id("calcium_plasma"))
                .plasma(10000)
                .color(0xfffaed)
                .element(GTElements.Ca)
                .buildAndRegister();

        GTCAMaterials.TitaniumPlasma = new Material.Builder(GTCA.id("titanium_plasma"))
                .plasma(10000)
                .color(0xf0a8ed)
                .element(GTElements.Ti)
                .buildAndRegister();

        GTCAMaterials.ZincPlasma = new Material.Builder(GTCA.id("zinc_plasma"))
                .plasma(10000)
                .color(0xEBEBFA)
                .element(GTElements.Zn)
                .buildAndRegister();
    }
}
