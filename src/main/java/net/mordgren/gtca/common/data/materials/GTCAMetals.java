package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.common.data.GTElements;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;

public class GTCAMetals {
    public static void init(){

        ///MISC
        GTMaterials.NiobiumTitanium.addFlags(MaterialFlags.GENERATE_FRAME);
        GTMaterials.Titanium.addFlags(MaterialFlags.GENERATE_FOIL);
        GTMaterials.Stellite100.addFlags(MaterialFlags.GENERATE_GEAR, MaterialFlags.GENERATE_RING);

        GTMaterials.Zirconium = new Material.Builder(GTCEu.id("zirconium_gtca"))
                .dust()
                .color(0xb99b7e).secondaryColor(0x271813).iconSet(METALLIC)
                .element(GTElements.Zr)
                .buildAndRegister();

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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
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
                .iconSet(METALLIC)
                .buildAndRegister();

        GTCAMaterials.HeavyMetalMixture = new Material.Builder(GTCA.id("heavy_metal_mixture"))
                .dust()
                .components(GTMaterials.Niobium, 2, GTMaterials.Chromium, 9, GTMaterials.Titanium, 2, GTMaterials.Aluminium, 5, GTMaterials.Cobalt, 10)
                .color(0x051053)
                .iconSet(MaterialIconSet.FLINT)
                .buildAndRegister();

        GTCAMaterials.Tantalloy60 = new Material.Builder(GTCA.id("tantalloy60"))
                .components(GTMaterials.Tungsten, 2,GTMaterials.Tantalum, 23)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_BOLT_SCREW
                )
                .dust()
                .fluid()
                .blastTemp(3300, BlastProperty.GasTier.MID, GTValues.VA[GTValues.HV], 750)
                .color(0xcfe2e8)
                .iconSet(METALLIC)
                .buildAndRegister();

        GTCAMaterials.Tantalloy61 = new Material.Builder(GTCA.id("tantalloy61"))
                .components(GTCAMaterials.Tantalloy60, 1, GTMaterials.Titanium, 6, GTMaterials.Yttrium, 4)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_BOLT_SCREW,
                        MaterialFlags.GENERATE_GEAR
                )
                .dust()
                .fluid()
                .blastTemp(3305, BlastProperty.GasTier.MID, GTValues.VA[GTValues.HV], 750)
                .color(0xc1d3d9)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("(W2Ta23)Ti6Y4",true);

        GTCAMaterials.Incoloy020 = new Material.Builder(GTCA.id("incoloy_020"))
                .components(GTMaterials.Iron, 10, GTMaterials.Copper, 1, GTMaterials.Chromium, 5, GTMaterials.Nickel, 9)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_BOLT_SCREW
                )
                .dust()
                .color(0xa2a8ca)
                .fluid()
                .blastTemp(2900, BlastProperty.GasTier.MID, GTValues.VA[GTValues.HV], 650)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Fe10CuCr5Ni9",true);

        GTCAMaterials.IncoloyMA323 = new Material.Builder(GTCA.id("incoloy_ma323"))
                .components(GTMaterials.Iron, 16, GTMaterials.Aluminium, 3, GTMaterials.Chromium, 5, GTMaterials.Yttrium, 1)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_BOLT_SCREW,
                        MaterialFlags.GENERATE_RING
                )
                .dust()
                .color(0x979aad)
                .fluid()
                .blastTemp(3600, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 700)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Fe16Al3Cr5Y",true);

        GTCAMaterials.HG1223 = new Material.Builder(GTCA.id("hg_1223"))
                .components(GTMaterials.Mercury, 1, GTMaterials.Barium, 2, GTMaterials.Calcium, 2, GTMaterials.Copper, 3, GTMaterials.Oxygen, 8)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .color(0x445bea)
                .fluid()
                .blastTemp(4900, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.LuV], 690)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("HgBa2Ca2Cu3O8",true);

        GTCAMaterials.IncoloyDS = new Material.Builder(GTCA.id("incoloy_ds"))
                .components(GTMaterials.Iron, 23, GTMaterials.Cobalt, 9, GTMaterials.Chromium, 9, GTMaterials.Nickel, 9)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .color(0x3f925f)
                .fluid()
                .blastTemp(2800, BlastProperty.GasTier.MID, GTValues.VA[GTValues.HV], 700)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Fe23Co9Cr9Ni9",true);

        GTCAMaterials.Inconel690 = new Material.Builder(GTCA.id("inconel_690"))
                .components(GTMaterials.Chromium, 1, GTMaterials.Niobium, 2, GTMaterials.Molybdenum, 2, GTMaterials.Nichrome, 3)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_BOLT_SCREW
                )
                .dust()
                .color(0xc5b1cd)
                .fluid()
                .blastTemp(2800, BlastProperty.GasTier.MID, GTValues.VA[GTValues.HV], 700)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("CrNb2Mo2(Ni4Cr)3",true);

        GTCAMaterials.EglinSteelCompound = new Material.Builder(GTCA.id("eglin_steel_compound"))
                .components(GTMaterials.Iron, 4, GTMaterials.Kanthal, 1, GTMaterials.Invar, 5)
                .flags(
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING
                )
                .dust()
                .color(0x5b3e1c)
                .iconSet(MaterialIconSet.FLINT)
                .buildAndRegister()
                .setFormula("Fe4(FeAlCr)(Fe2Ni)5",true);

        GTCAMaterials.EglinSteel = new Material.Builder(GTCA.id("eglin_steel"))
                .components(GTCAMaterials.EglinSteelCompound, 10, GTMaterials.Sulfur, 1, GTMaterials.Silicon, 4, GTMaterials.Carbon, 1)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_BOLT_SCREW,
                        MaterialFlags.GENERATE_LONG_ROD

                )
                .dust()
                .color(0x5a452c)
                .fluid()
                .blastTemp(4900, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.IV], 850)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("(Fe4(FeAlCr)(Fe2Ni)5)10SSi4C",true);


        GTCAMaterials.ZirconiumCarbide = new Material.Builder(GTCA.id("zirconium_carbide"))
                .components(GTMaterials.Zirconium, 1, GTMaterials.Carbon, 1)
                .flags(
                       MaterialFlags.DISABLE_ALLOY_BLAST
                )
                .color(0xb89b7a)
                .fluid()
                .blastTemp(3300, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.LuV], 1100)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("ZrC",true);

        GTCAMaterials.HastelloyN = new Material.Builder(GTCA.id("hastelloy_n"))
                .components(GTMaterials.Yttrium, 2, GTMaterials.Molybdenum, 4, GTMaterials.Chromium, 2, GTMaterials.Titanium, 2, GTMaterials.Nickel, 25)
                .flags(
                       MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_BOLT_SCREW

                )
                .color(0x5c838b)
                .fluid()
                .blastTemp(5300, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 1100)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Y2Mo4Cr2Ti2Ni25",true);


        GTCAMaterials.Inconel625 = new Material.Builder(GTCA.id("inconel_625"))
                .components(GTMaterials.Nickel, 3, GTMaterials.Chromium, 7, GTMaterials.Molybdenum, 10, GTMaterials.Invar, 10, GTMaterials.Nichrome, 13)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_BOLT_SCREW,
                        MaterialFlags.GENERATE_GEAR

                )
                .color(0xb4eeb4)
                .fluid()
                .blastTemp(4900, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 1670)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Ni3Cr7Mo10(Fe2Ni)10(Ni4Cr)13",true);


        GTCAMaterials.Zeron182 = new Material.Builder(GTCA.id("zeron_182"))
                .components(GTMaterials.Chromium, 13, GTMaterials.Nickel, 3, GTMaterials.Molybdenum, 2, GTMaterials.Copper, 10, GTMaterials.Tungsten, 2, GTMaterials.Steel, 20)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_BOLT_SCREW

                )
                .color(0xe1b325)
                .fluid()
                .blastTemp(6100, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.LuV], 2100)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Cr13Ni3Mo2Cu10W2Fe20",true);

        GTCAMaterials.LafiumCompound = new Material.Builder(GTCA.id("lafium_compound"))
                .components(GTCAMaterials.HastelloyN, 4, GTMaterials.Naquadah, 2, GTMaterials.Samarium, 1, GTMaterials.Tungsten, 2, GTMaterials.Silver, 1, GTMaterials.Aluminium, 3, GTMaterials.Nickel, 4, GTMaterials.Carbon, 1)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_BOLT_SCREW

                )
                .color(0x6d6abc)
                .fluid()
                .blastTemp(6300, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.LuV], 1000)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("(Y2Mo4Cr2Ti2Ni25)Nq2SmW2AgAl3Ni4C",true);

        GTCAMaterials.TriniumNaquadah = new Material.Builder(GTCA.id("trinium_naquadah"))
                .components(GTMaterials.Trinium, 5, GTMaterials.Naquadah,9)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_BOLT_SCREW
                )
                .color(0x606060)
                .fluid()
                .blastTemp(6100, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.LuV], 900)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("Ke5Nq9",true);

        GTCAMaterials.TriniumNaquadahCarbonite = new Material.Builder(GTCA.id("trinium_naquadah_carbonite"))
                .components(GTMaterials.Trinium, 5, GTMaterials.Naquadah,9, GTMaterials.Carbon, 1)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.DECOMPOSITION_BY_CENTRIFUGING,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_BOLT_SCREW

                )
                .color(0x654949)
                .fluid()
                .blastTemp(6100, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.LuV], 300)
                .iconSet(METALLIC)
                .buildAndRegister()
                .setFormula("(Ke5Nq9)9C",true);








        // Ores

        GTCAMaterials.RedZircon = new Material.Builder(GTCA.id("red_zircon"))
                .dust(3)
                .gem()
                .flags
                        (
                                MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING,
                                MaterialFlags.NO_SMASHING,
                                MaterialFlags.NO_SMELTING,
                                MaterialFlags.HIGH_SIFTER_OUTPUT
                        )
                .iconSet(RUBY)
                .ore(2, 1)
                .color(0xfc4444).secondaryColor(0xf17676)
                .components(GTMaterials.Zirconium, 1, GTMaterials.SiliconDioxide, 1,GTMaterials.Oxygen, 2)
                .buildAndRegister()
                .setFormula("ZrSiO4",true);


        GTCAMaterials.GreenFuchsite = new Material.Builder(GTCA.id("green_fuchsite"))
                .dust(2)
                .gem()
                .flags
                        (
                                MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING,
                                MaterialFlags.NO_SMASHING,
                                MaterialFlags.NO_SMELTING,
                                MaterialFlags.HIGH_SIFTER_OUTPUT
                        )
                .iconSet(GEM_HORIZONTAL)
                .ore(2, 1)
                .color(0x81c974).secondaryColor(0x28ff01)
                .components(GTMaterials.Potassium, 1, GTMaterials.Aluminium, 3,GTMaterials.Silicon, 3, GTMaterials.Oxygen, 12, GTMaterials.Hydrogen, 2)
                .buildAndRegister()
                .setFormula("KAl3Si3O10(OH)2",true);


        GTCAMaterials.Fayalite = new Material.Builder(GTCA.id("fayalite"))
                .dust(2)
                .gem()
                .flags
                        (
                                MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING,
                                MaterialFlags.NO_SMASHING,
                                MaterialFlags.NO_SMELTING,
                                MaterialFlags.HIGH_SIFTER_OUTPUT
                        )
                .iconSet(LAPIS)
                .ore(2, 1)
                .color(0x4b4b4b).secondaryColor(0x706f58)
                .components(GTMaterials.Iron, 2, GTMaterials.SiliconDioxide, 1,GTMaterials.Oxygen, 2)
                .buildAndRegister()
                .setFormula("Fe2SiO4",true);



        GTCAMaterials.RedFuchsite = new Material.Builder(GTCA.id("red_fuchsite"))
                .dust(2)
                .gem()
                .flags
                        (
                                MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING,
                                MaterialFlags.NO_SMASHING,
                                MaterialFlags.NO_SMELTING,
                                MaterialFlags.HIGH_SIFTER_OUTPUT
                        )
                .iconSet(GEM_HORIZONTAL)
                .ore(2, 1)
                .color(0xc43b3b).secondaryColor(0xf99ebf)
                .components(GTMaterials.Potassium, 1, GTMaterials.Chromium, 3,GTMaterials.Silicon, 3, GTMaterials.Oxygen, 12, GTMaterials.Hydrogen, 2)
                .buildAndRegister()
                .setFormula("KCr3Si3O10(OH)2",true);


        GTCAMaterials.Nitinol60 = new Material.Builder(GTCA.id("nitinol60"))
                .color(0x9f8ab8).secondaryColor(0xb69dd2)
                .components(GTMaterials.Titanium, 3, GTMaterials.Nickel, 2)
                .fluid()
                .blastTemp(5925, BlastProperty.GasTier.MID, GTValues.VA[GTValues.IV], 6250)
                .buildAndRegister();

        GTCAMaterials.Inconel792 = new Material.Builder(GTCA.id("inconel792"))
                .color(0x51b559).secondaryColor(0x63dc6c)
                .components(GTMaterials.Nickel, 2, GTMaterials.Niobium, 1, GTMaterials.Aluminium, 2, GTMaterials.Nichrome, 1)
                .fluid()
                .blastTemp(3700, BlastProperty.GasTier.MID, GTValues.VA[GTValues.HV], 750)
                .buildAndRegister();
    }
}
