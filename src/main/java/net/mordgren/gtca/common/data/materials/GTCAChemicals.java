package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class GTCAChemicals {

    public static void init() {

        GTCAMaterials.AluminosilicateCatalyst = new Material.Builder(GTCA.id("aluminosilicate_catalyst"))
                .dust()
                .color(0x284d8a).iconSet(MaterialIconSet.FLINT)
                .buildAndRegister();

        GTCAMaterials.Propyne = new Material.Builder(GTCA.id("propyne"))
                .fluid()
                .components(GTMaterials.Carbon, 3, GTMaterials.Hydrogen, 4)
                .color(0xcdb1b1)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();

        GTCAMaterials.Propadiene = new Material.Builder(GTCA.id("propadiene"))
                .fluid()
                .components(GTMaterials.Carbon, 3, GTMaterials.Hydrogen, 4)
                .color(0xad9797)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();

        GTCAMaterials.IsononylAlcohol = new Material.Builder(GTCA.id("isononyl_alcohol"))
                .fluid()
                .components(GTMaterials.Carbon, 9, GTMaterials.Hydrogen, 19, GTMaterials.Oxygen, 1, GTMaterials.Hydrogen, 1)
                .color(0xffffff)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();

        GTCAMaterials.PhthalicAnhydride = new Material.Builder(GTCA.id("phthalic_anhydride"))
                .fluid()
                .components(GTMaterials.Carbon, 8,GTMaterials.Hydrogen, 4, GTMaterials.Oxygen, 3)
                .color(0x558000)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();

        GTCAMaterials.Oxylene = new Material.Builder(GTCA.id("oxylene"))
                .fluid()
                .components(GTMaterials.Carbon, 8, GTMaterials.Hydrogen, 10)
                .color(0x669999)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();

        GTCAMaterials.Octene = new Material.Builder(GTCA.id("octene"))
                .fluid()
                .components(GTMaterials.Carbon, 8, GTMaterials.Hydrogen, 16)
                .color(0xff9f80)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();
    }
}
