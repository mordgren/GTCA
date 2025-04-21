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

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTCAMetals {
    public static void init(){

        ///MISC
        GTCAMaterials.SiliconTetrachloride = new Material.Builder(GTCA.id("silicon_tetrachloride"))
                .dust()
                .color(0x6e7592)
                .components(Silicon, 1, Chlorine, 4)
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
                .components(GTMaterials.Mercury, 3, GTMaterials.Barium, 2, GTMaterials.Calcium, 5, GTMaterials.Copper, 12, GTMaterials.Bismuth, 6 ,GTMaterials.Oxygen, 14)
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
                .setFormula("Hg3Ba2Ca5Cu12Bi6O14",true);

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
                        MaterialFlags.GENERATE_LONG_ROD,
                        MaterialFlags.GENERATE_FRAME

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
                        MaterialFlags.GENERATE_BOLT_SCREW,
                        MaterialFlags.GENERATE_GEAR

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

        GTCAMaterials.Germanite = new Material.Builder(GTCA.id("germanite"))
                .dust(3)
                .flags
                        (
                                MaterialFlags.DECOMPOSITION_BY_ELECTROLYZING,
                                MaterialFlags.NO_SMASHING,
                                MaterialFlags.NO_SMELTING
                        )
                .iconSet(FINE)
                .ore(2, 1)
                .color(0x464646).secondaryColor(0x595858)
                .components(GTMaterials.Zinc, 2, GTMaterials.Iron, 2, GTMaterials.Germanium, 1, GTMaterials.Sulfur, 4)
                .buildAndRegister()
                .setFormula("(ZnFe)2GeS4",true);




     //   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        GTCAMaterials.Nitinol60 = new Material.Builder(GTCA.id("nitinol60"))
                .color(0x9f8ab8).secondaryColor(0xb69dd2)
                .components(GTMaterials.Titanium, 3, GTMaterials.Nickel, 2)
                .flags
                        (
                                MaterialFlags.GENERATE_PLATE,
                                MaterialFlags.GENERATE_BOLT_SCREW,
                                MaterialFlags.GENERATE_GEAR
                        )
                .fluid()
                .blastTemp(5925, BlastProperty.GasTier.MID, GTValues.VA[GTValues.IV], 6250)
                .buildAndRegister();

        GTCAMaterials.Inconel792 = new Material.Builder(GTCA.id("inconel792"))
                .color(0x51b559).secondaryColor(0x63dc6c)
                .components(GTMaterials.Nickel, 2, GTMaterials.Niobium, 1, GTMaterials.Aluminium, 2, GTMaterials.Nichrome, 1)
                .fluid()
                .blastTemp(3700, BlastProperty.GasTier.MID, GTValues.VA[GTValues.HV], 750)
                .buildAndRegister();

        GTCAMaterials.CinobiteA241 = new Material.Builder(GTCA.id("cinobite_a241"))
                .color(0x6e6945).secondaryColor(0xc7bd79)
                .components(GTCAMaterials.Zeron182, 16, GTMaterials.Naquadria, 7, GTMaterials.Samarium, 5, GTMaterials.Aluminium, 3, GTMaterials.Tin, 2, GTMaterials.Titanium, 12, GTMaterials.Osmiridium, 6, GTMaterials.Mercury, 2)
                .fluid()
                .blastTemp(7625, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.ZPM], 1850)
                .buildAndRegister();

        GTCAMaterials.Pikyonium64Y = new Material.Builder(GTCA.id("pikyonium64_y"))
                .color(0x4900d1)
                .components(GTCAMaterials.Inconel792, 8, GTCAMaterials.EglinSteel, 5, NaquadahEnriched, 4, GTMaterials.Cerium, 3, GTMaterials.Antimony, 2, GTMaterials.Platinum, 2, GTMaterials.Yttrium, 1, GTMaterials.TungstenSteel, 4)
                .fluid()
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR
                )
                .blastTemp(7125, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.ZPM], 1900)
                .buildAndRegister();


        GTCAMaterials.Neutronex = new Material.Builder(GTCA.id("neutronex"))
                .color(0x1e151c)
                .components(GTMaterials.NaquadahEnriched, 6, GTMaterials.Aluminium, 18, GTMaterials.Silicon, 6, GTMaterials.Platinum, 6, GTMaterials.Uranium238, 2)
                .fluid()
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_BOLT_SCREW
                )
                .blastTemp(6999, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.ZPM], 1700)
                .buildAndRegister();

        GTCAMaterials.DuraniumX = new Material.Builder(GTCA.id("duranium_x"))
                .color(0x45674a)
                .components(GTMaterials.Uranium235, 10, GTMaterials.Plutonium241, 8, GTMaterials.Molybdenum, 8, GTMaterials.Tungsten, 8, GTMaterials.Iron, 6, GTMaterials.Chromium, 4, GTMaterials.NaquadahEnriched, 4)
                .fluid()
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_BOLT_SCREW
                )
                .blastTemp(8100, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.ZPM], 1600)
                .buildAndRegister();

        GTCAMaterials.Berwollium = new Material.Builder(GTCA.id("berwollium"))
                .color(0xb7c0ac)
                .components(GTMaterials.Beryllium, 4, GTMaterials.Molybdenum, 2, GTMaterials.Tungsten, 1)
                .fluid()
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_BOLT_SCREW
                )
                .blastTemp(8100, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.EV], 990)
                .buildAndRegister();

        GTCAMaterials.AdamantiumAlloy = new Material.Builder(GTCA.id("adamantium_alloy"))
                        .color(0xd2d4da)
                        .components(GTCAMaterials.Adamantium, 5, GTMaterials.NaquadahEnriched, 2,GTMaterials.Lanthanum, 3)
                        .fluid()
                        .flags(
                                MaterialFlags.GENERATE_PLATE,
                                MaterialFlags.GENERATE_GEAR,
                                MaterialFlags.GENERATE_FRAME,
                                MaterialFlags.GENERATE_BOLT_SCREW
                        )
                .blastTemp(8100, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.LuV], 1200)
                        .buildAndRegister();

        GTCAMaterials.ArtheriumSn = new Material.Builder(GTCA.id("artherium_sn"))
                .color(0x003aff)
                .components(GTCAMaterials.AdamantiumAlloy, 12, GTCAMaterials.Orundum, 9, GTMaterials.Tin, 8, GTMaterials.Arsenic, 7, GTMaterials.Caesium, 4, GTMaterials.Osmiridium, 3)
                .fluid()
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_BOLT_SCREW
                )
                .blastTemp(8100, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.LuV], 900)
                .buildAndRegister()
                .setFormula("(Ad5Nq2La3)12Or9Sn8As7Cs4(Ir3Os)3", true);


        GTCAMaterials.ElectricalSteel = new Material.Builder(GTCA.id("electrical_steel"))
                .color(0xadadad)
                .components(Iron, 1, Carbon, 1, Silicon, 1)
                .fluid()
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_ROD
                )
                .blastTemp(860, BlastProperty.GasTier.LOW, GTValues.VA[GTValues.MV], 400)
                .buildAndRegister()
                .setFormula("FeCSi",true);

        GTCAMaterials.DarkSteel = new Material.Builder(GTCA.id("dark_steel"))
                .color(0x786f6f)
                .components(GTCAMaterials.ElectricalSteel, 1, Obsidian, 1)
                .fluid()
                .iconSet(SHINY)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_ROD
                )
                .blastTemp(1200, BlastProperty.GasTier.LOW, GTValues.VA[GTValues.MV])
                .buildAndRegister()
                .setFormula("(FeCSi)C(MgFeSi2O8)",true);

        GTCAMaterials.EndSteel = new Material.Builder(GTCA.id("end_steel"))
                .color(0xadb699)
                .components(Endstone, 1, GTCAMaterials.DarkSteel, 1, Tungsten, 1)
                .fluid()
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_ROD
                )
                .blastTemp(3400, BlastProperty.GasTier.MID, GTValues.VA[GTValues.HV], 990)
                .buildAndRegister()
                .setFormula("?(FeCSi)C(MgFeSi2O8)W?",true);

        GTCAMaterials.MelodicAlloy = new Material.Builder(GTCA.id("melodic_alloy"))
                .color(0x6a5286)
                .components(GTCAMaterials.EndSteel, 1,GTCAMaterials.Ohriharukon, 1, EnderEye, 1)
                .fluid()
                .iconSet(SHINY)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_ROD
                )
                .blastTemp(3900, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.EV], 1540)
                .buildAndRegister()
                .setFormula("?(FeCSi)C(MgFeSi2O8)W?(BeK4N5Ma6)Oh",true);

        GTCAMaterials.StellarAlloy = new Material.Builder(GTCA.id("stellar_alloy"))
                .color(0xe1e1e1)
                .components(GTCAMaterials.MelodicAlloy, 1, NetherStar, 1, NaquadahEnriched, 1)
                .fluid()
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_ROD
                )
                .blastTemp(7199, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.LuV], 2200)
                .buildAndRegister()
                .setFormula("((?(FeCSi)C(MgFeSi2O8)W?)(BeK4N5Ma6)(CSMa)Oh)Nq+",true);

        GTCAMaterials.EnrichedHolmium = new Material.Builder(GTCA.id("enriched_holmium"))
                .color(0x0017ff)
                .components(NaquadahEnriched, 8, Holmium, 2)
                .fluid()
                .iconSet(SHINY)
                .flags(
                        MaterialFlags.GENERATE_FOIL,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_ROD
                )
                .blastTemp(6940, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.LuV], 2100)
                .buildAndRegister()
                .setFormula("Nq+8Ho2",true);

        GTCAMaterials.Stellite79 = new Material.Builder(GTCA.id("stellite_79"))
                .color(0xff5151)
                .components(Cobalt, 7, Chromium, 7, Manganese, 4, Titanium, 2)
                .fluid()
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_ROD
                )
                .blastTemp(3500, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.EV], 2000)
                .buildAndRegister()
                .setFormula("Co7Cr7Mn4Ti2",true);

        GTCAMaterials.SiliconCarbide = new Material.Builder(GTCA.id("silicon_carbide"))
                .color(0x012d06)
                .ingot()
                .iconSet(SHINY)
                .components(Silicon, 1, Carbon, 1)
                .buildAndRegister()
                .setFormula("SiC",true);

        GTCAMaterials.QuantumAlloy = new Material.Builder(GTCA.id("quantum_alloy"))
                .color(0xffc2f1)
                .components(GTCAMaterials.Stellite79, 3,GTCAMaterials.SiliconCarbide, 1, Gallium, 1, Americium, 1, Palladium, 1, Bismuth, 1, Germanium, 1 )
                .fluid()
                .iconSet(RADIOACTIVE)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_ROD
                )
                .blastTemp(9002, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.UV], 4900)
                .buildAndRegister()
                .setFormula("(Co7Cr7Mn4Ti2)(SiC)GaAmPdBiGe",true);

        GTCAMaterials.CelestialTungsten = new Material.Builder(GTCA.id("celestial_tungsten"))
                .dust()
                .ingot()
                .flags(
                        MaterialFlags.NO_SMELTING,
                        MaterialFlags.GENERATE_BOLT_SCREW,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_FRAME
                )
                .color(0x343434)
                .buildAndRegister()
                .setFormula("✦✧✦",true);

        // MISC


        GTCAMaterials.StrangeCrystal = new Material.Builder(GTCA.id("strange_crystal"))
                .gem()
                .iconSet(DIAMOND)
                .flags(
                        MaterialFlags.NO_SMELTING,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.NO_UNIFICATION
                )
                .color(0x74a271)
                .buildAndRegister()
                .setFormula("???",true);

        GTCAMaterials.UnknownCrystal = new Material.Builder(GTCA.id("unknown_crystal"))
                .gem()
                .iconSet(EMERALD)
                .flags(
                        MaterialFlags.NO_SMELTING,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.NO_UNIFICATION
                )
                .color(0x5ca483)
                .buildAndRegister()
                .setFormula("???",true);

        GTCAMaterials.QuiteCertainCrystal = new Material.Builder(GTCA.id("certain_crystal"))
                .gem()
                .iconSet(RUBY)
                .flags(
                        MaterialFlags.NO_SMELTING,
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_LENS,
                        MaterialFlags.NO_UNIFICATION
                        )
                .color(0x26714f)
                .buildAndRegister()
                .setFormula("???",true);


    }
}
