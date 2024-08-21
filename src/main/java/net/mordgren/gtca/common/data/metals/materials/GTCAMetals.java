package net.mordgren.gtca.common.data.metals.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class GTCAMetals {
    public static void init(){

        GTCAMaterials.TM20MnAlloy = new Material.Builder(GTCA.id("tm_20_mn_alloy"))
                .components(GTMaterials.Tungsten, 4, GTMaterials.Molybdenum, 1, GTMaterials.Manganese, 1)
                .flags(
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_PLATE
                )
                .dust()
                .fluid()
                .color(0x7c96c6)
                .iconSet(MaterialIconSet.METALLIC)
                .blastTemp(6100, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.IV], 660)
                .buildAndRegister();

        // greengreen xdxd

        GTCAMaterials.CNFAlloy = new Material.Builder(GTCA.id("c_n_f_alloy"))
                .components(GTMaterials.Nickel, 5, GTMaterials.Chromium, 2, GTMaterials.Iron, 1)
                .flags(
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.EXCLUDE_PLATE_COMPRESSOR_RECIPE
                )
                .ingot()
                .dust()
                .fluid()
                .color(0xf3f3f3)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();



        GTCAMaterials.Dural = new Material.Builder(GTCA.id("dural"))
                .components(GTMaterials.Aluminium, 9, GTMaterials.Copper, 2, GTMaterials.Magnesium, 1, GTMaterials.Manganese, 1)
                .flags(
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .fluid()
                .blastTemp(2800, BlastProperty.GasTier.LOW, GTValues.VA[GTValues.HV], 360)
                .color(0xb2b2b2)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();

    }
}
