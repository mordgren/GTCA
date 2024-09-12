package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class GTCAMetals {
    public static void init(){

        ///MISC
        GTMaterials.NiobiumTitanium.addFlags(MaterialFlags.GENERATE_FRAME);

        ///ALLOYS
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



        GTCAMaterials.Nimonic80A = new Material.Builder(GTCA.id("nimonic80a"))
                .components(GTMaterials.Nickel, 8, GTMaterials.Chromium, 3, GTMaterials.Cobalt, 2, GTMaterials.Titanium, 1, GTMaterials.Aluminium, 1)
                .flags(
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .fluid()
                .blastTemp(3400, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 320)
                .color(0x9f3f3f)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();

        GTCAMaterials.Moltech = new Material.Builder(GTCA.id("moltech"))
                .components(GTMaterials.Molybdenum, 8, GTMaterials.Tungsten, 2, GTMaterials.Titanium, 1)
                .flags(
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_GEAR
                )
                .dust()
                .fluid()
                .blastTemp(3800, BlastProperty.GasTier.MID, GTValues.VA[GTValues.IV], 280)
                .color(0x4d315e)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();

        GTCAMaterials.Vitallium = new Material.Builder(GTCA.id("vitallium"))
                .components(GTMaterials.Cobalt, 6, GTMaterials.Chromium, 3, GTMaterials.Molybdenum, 1)
                .flags(
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .fluid()
                .blastTemp(3000, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 240)
                .color(0xACA6E6).secondaryColor(0xD2D2EB)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();

        GTCAMaterials.Inconel718 = new Material.Builder(GTCA.id("inconel718"))
                .components(GTMaterials.Nickel, 5, GTMaterials.Chromium, 2, GTMaterials.Iron, 2, GTMaterials.Niobium, 2, GTMaterials.Molybdenum, 1, GTMaterials.Titanium, 1, GTMaterials.Aluminium, 1)
                .flags(
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .fluid()
                .blastTemp(3800, BlastProperty.GasTier.MID, GTValues.VA[GTValues.IV], 300)
                .color(0xb5d9a6).secondaryColor(0xD2D2EB)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();

        GTCAMaterials.Incoloy903 = new Material.Builder(GTCA.id("incoloy903"))
                .components(GTMaterials.Iron, 12,GTMaterials.Nickel, 10, GTMaterials.Cobalt, 8, GTMaterials.Titanium, 4, GTMaterials.Molybdenum, 2, GTMaterials.Aluminium, 1)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .fluid()
                .blastTemp(3700, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 360)
                .color(0xa7ad81)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();


        GTCAMaterials.MAR_M200 = new Material.Builder(GTCA.id("mar_m200"))
                .components(GTMaterials.Niobium, 2, GTMaterials.Chromium, 9, GTMaterials.Titanium, 2, GTMaterials.Aluminium, 5, GTMaterials.Cobalt, 10, GTMaterials.Tungsten, 13, GTMaterials.Nickel, 18)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .fluid()
                .blastTemp(4200, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 380)
                .color(0x494949)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();


        GTCAMaterials.MAR_CE_M200 = new Material.Builder(GTCA.id("mar_ce_m200"))
                .components(GTMaterials.Niobium, 2, GTMaterials.Chromium, 9, GTMaterials.Titanium, 2, GTMaterials.Aluminium, 5, GTMaterials.Cobalt, 10, GTMaterials.Tungsten, 13, GTMaterials.Nickel, 18, GTMaterials.Cerium, 1)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.DISABLE_ALLOY_BLAST,
                        MaterialFlags.NO_SMELTING

                )
                .color(0x2a2a2a)
                .dust()
                .blastTemp(5399, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 380)
                .iconSet(MaterialIconSet.METALLIC)
                .rotorStats(170, 120, 4.0f, 117230 )
                .toolStats(ToolProperty.Builder.of(57.0F, 15.0F, 5042, 5)
                        .attackSpeed(0.3F).enchantability(33).build())
                .buildAndRegister()
                .setFormula("(Nb2Cr9Al5Ti2Co10W13Ni18)16Ce",true);

        GTCAMaterials.Incoloy846 = new Material.Builder(GTCA.id("incoloy846"))
                .components(GTMaterials.Iron, 12,GTMaterials.Nickel, 10, GTMaterials.Cobalt, 8, GTMaterials.Titanium, 4, GTMaterials.Molybdenum, 2)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .fluid()
                .blastTemp(3400, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 360)
                .color(0x96bcb3)
                .iconSet(MaterialIconSet.METALLIC)
                .buildAndRegister();

        GTCAMaterials.HeavyMetalMixture = new Material.Builder(GTCA.id("heavy_metal_mixture"))
                .dust()
                .components(GTMaterials.Niobium, 2, GTMaterials.Chromium, 9, GTMaterials.Titanium, 2, GTMaterials.Aluminium, 5, GTMaterials.Cobalt, 10)
                .color(0x051053)
                .iconSet(MaterialIconSet.FLINT)
                .buildAndRegister();
    }
}
