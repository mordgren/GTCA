package net.mordgren.gtca.common.data.chemicals.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class GTCAChemicals {

    public static void init() {

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

        GTCAMaterials.MAPP = new Material.Builder(GTCA.id("mapp"))
                .fluid()
                .color(0x686868)
                .buildAndRegister()
                .setFormula("5(C3H4)3(C3H4)2(C3H8)(C4H10)",true);









    }


}
