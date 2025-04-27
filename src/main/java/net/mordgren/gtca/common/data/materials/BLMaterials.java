package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class BLMaterials {
    
    public static void init(){

        GTCAMaterials.MuddyBastnasiteRareEarthSolution = new Material.Builder(GTCA.id("muddy_bastnasite_rare_earth_solution"))
                .fluid()
                .color(0x9b6633)
                .buildAndRegister().setFormula("??LaCeY??", true);

        GTCAMaterials.SteamCrackedBastnasiteMud = new Material.Builder(GTCA.id("steam_cracked_bastnasite_mud"))
                .fluid()
                .color(0x996533)
                .buildAndRegister().setFormula("??LaCeY??", true);

        GTCAMaterials.HexafluorosilicicAcid = new Material.Builder(GTCA.id("hexa_fluorosilicic_acid"))
                .fluid()
                .color(0x255789)
                .buildAndRegister().setFormula("H2SiF6", true);

        GTCAMaterials.Sodiumfluorosilicate = new Material.Builder(GTCA.id("sodiumfluorosilicate"))
                .fluid()
                .color(0xa16a34)
                .buildAndRegister().setFormula("Na2SiF6", true);

        GTCAMaterials.ConditionedBastnasiteMud = new Material.Builder(GTCA.id("conditioned_bastnasite_mud"))
                .fluid()
                .color(0xa56d35)
                .buildAndRegister().setFormula("??LaCeY??", true);

        GTCAMaterials.DilutedBastnasiteMud = new Material.Builder(GTCA.id("diluted_bastnasite_mud"))
                .fluid()
                .color(0xa56d35)
                .buildAndRegister().setFormula("??LaCeY??", true);

        GTCAMaterials.FilteredBastnasiteMud = new Material.Builder(GTCA.id("filtered_bastnasite_mud"))
                .fluid()
                .color(0xa56d35)
                .buildAndRegister().setFormula("??LaCeY??", true);

        GTCAMaterials.BastnasiteRareEarthOxides = new Material.Builder(GTCA.id("bastnasite_rare_earth_oxides"))
                .dust()
                .color(0x774d25).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??LaCeY??", true);

        GTCAMaterials.AcidLeachedBastnasiteRareEarthOxides = new Material.Builder(GTCA.id("acid_leached_rare_earth_oxides"))
                .dust()
                .color(0x774d25).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??LaCeY??", true);

        GTCAMaterials.RoastedRareEarthOxides = new Material.Builder(GTCA.id("roasted_rare_earth_oxides"))
                .dust()
                .color(0x572d19).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();

        GTCAMaterials.WetRareEarthOxides = new Material.Builder(GTCA.id("wet_rare_earth_oxides"))
                .dust()
                .color(0x572d19).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();

        GTCAMaterials.CeriumOxidisedRareEarthOxides = new Material.Builder(GTCA.id("cerium_oxidised_rare_earth_oxides"))
                .dust()
                .color(0x582d1b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();

        GTCAMaterials.BastnasiteRarerEarthOxides = new Material.Builder(GTCA.id("bastnasite_rarer_earth_oxides"))
                .dust()
                .color(0x582d1b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();

        GTCAMaterials.NitrogenatedBastnasiteRarerEarthOxides = new Material.Builder(GTCA.id("nitrogenated_bastnasite_rarer_earth_oxides"))
                .fluid()
                .color(0x7b4731)
                .buildAndRegister();

        GTCAMaterials.BastnasiteRarerEarthOxideSuspension = new Material.Builder(GTCA.id("bastnasite_rarer_earth_oxides_suspension"))
                .fluid()
                .color(0x7b4731)
                .buildAndRegister();

        GTCAMaterials.SamaricRareEarthConcentrate = new Material.Builder(GTCA.id("samaric_rare_earth_concentrate"))
                .dust()
                .color(0x633423).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmHoTb??", true);

        GTCAMaterials.FluorinatedSamaricConcentrate = new Material.Builder(GTCA.id("fluorinated_samaric_concentrate"))
                .dust()
                .color(0x8e656b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmHo??", true);

        GTCAMaterials.SamariumTerbiumMixture = new Material.Builder(GTCA.id("samarium_terbium_mixture"))
                .dust()
                .color(0x7d666c).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmTb??", true);

        GTCAMaterials.NitrogenatedSamariumTerbiumMixture = new Material.Builder(GTCA.id("nitrogenated_samarium_terbium_mixture"))
                .dust()
                .color(0x7f676e).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmTb??NH4NO3", true);

        GTCAMaterials.TerbiumNitrate = new Material.Builder(GTCA.id("terbium_nitrate"))
                .dust()
                .color(0x5f8f00).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("TbNO3", true);

        GTCAMaterials.SamariumResidue = new Material.Builder(GTCA.id("samarium_residue"))
                .dust()
                .color(0x87847e).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmGd??", true);

        GTCAMaterials.CeriumDioxide = new Material.Builder(GTCA.id("cerium_dioxide"))
                .dust()
                .color(0x909090).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("CeO2", true);

        GTCAMaterials.CeriumChloride = new Material.Builder(GTCA.id("cerium_chloride"))
                .dust()
                .color(0x888888).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("CeCl3", true);

        GTCAMaterials.CeriumOxalate = new Material.Builder(GTCA.id("cerium_oxalate"))
                .dust()
                .color(0x8c8c7b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("Ce2(C2O4)3", true);

        GTCAMaterials.Oxalate = new Material.Builder(GTCA.id("oxalate"))
                .fluid()
                .color(0x5da243)
                .buildAndRegister();

        GTCAMaterials.CeriumIIIOxide = new Material.Builder(GTCA.id("cerium_iii_oxide"))
                .dust()
                .color(0x949482).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("Ce2O3", true);

        GTCAMaterials.NeodymiumRareEarthConcentrate = new Material.Builder(GTCA.id("neodymium_rare_earth_concentrate"))
                .dust()
                .color(0x5d3121).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??LaNdPr??", true);

        GTCAMaterials.LanthaniumChloride = new Material.Builder(GTCA.id("lanthanium_chloride"))
                .dust()
                .color(0x30413b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("LaCl3", true);

        GTCAMaterials.NeodymiumOxide = new Material.Builder(GTCA.id("neodymium_oxide"))
                .dust()
                .color(0x30413b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("Nd2O3", true);

        GTCAMaterials.CalciumFluoride = new Material.Builder(GTCA.id("calcium_fluoride"))
                .dust()
                .fluid()
                .color(0xbebbbb).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("CaF2", true);

        GTCAMaterials.AmmoniumNitrate = new Material.Builder(GTCA.id("ammonium_nitrate"))
                .dust()
                .color(0xaa3abf).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("N2H4O3", true);

        GTCAMaterials.HydratedAmmoniumNitrateSlurry = new Material.Builder(GTCA.id("hydrated_ammonium_nitrate_slurry"))
                .fluid()
                .color(0xaa3abf)
                .buildAndRegister();
    }
}
