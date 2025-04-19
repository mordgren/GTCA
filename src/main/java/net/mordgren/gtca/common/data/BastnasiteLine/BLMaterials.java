package net.mordgren.gtca.common.data.BastnasiteLine;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.mordgren.gtca.GTCA;

public class BLMaterials {

    public static Material MuddyBastnasiteRareEarthSolution;
    public static Material SteamCrackedBastnasiteMud;
    public static Material HexafluorosilicicAcid;
    public static Material Sodiumfluorosilicate;
    public static Material ConditionedBastnasiteMud;
    public static Material DilutedBastnasiteMud;
    public static Material FilteredBastnasiteMud;
    public static Material BastnasiteRareEarthOxides; //Dust
    public static Material AcidLeachedBastnasiteRareEarthOxides; //Dust
    public static Material RoastedRareEarthOxides; //Dust
    public static Material WetRareEarthOxides; //Dust
    public static Material CeriumOxidisedRareEarthOxides; //Dust
    public static Material BastnasiteRarerEarthOxides; //Dust
    public static Material NitrogenatedBastnasiteRarerEarthOxides;
    public static Material BastnasiteRarerEarthOxideSuspension;
    public static Material SamaricRareEarthConcentrate; // Dust
    public static Material FluorinatedSamaricConcentrate; // Dust
    public static Material SamariumTerbiumMixture; // Dust
    public static Material NitrogenatedSamariumTerbiumMixture; // Dust
    public static Material TerbiumNitrate; // Dust
    public static Material SamariumResidue; // Dust
    public static Material CeriumDioxide; // Dust
    public static Material CeriumChloride; // Dust
    public static Material CeriumOxalate; // Dust
    public static Material Oxalate;
    public static Material CeriumIIIOxide; // Dust
    public static Material NeodymiumRareEarthConcentrate; // Dust
    public static Material LanthaniumChloride; // Dust
    public static Material NeodymiumOxide; // Dust

    public static void initMaterials(){

        BLMaterials.MuddyBastnasiteRareEarthSolution = new Material.Builder(GTCA.id("muddy_bastnasite_rare_earth_solution"))
                .fluid()
                .color(0x9b6633)
                .buildAndRegister().setFormula("??LaCeY??", true);

        BLMaterials.SteamCrackedBastnasiteMud = new Material.Builder(GTCA.id("steam_cracked_bastnasite_mud"))
                .fluid()
                .color(0x996533)
                .buildAndRegister().setFormula("??LaCeY??", true);

        BLMaterials.HexafluorosilicicAcid = new Material.Builder(GTCA.id("hexa_fluorosilicic_acid"))
                .fluid()
                .color(0x255789)
                .buildAndRegister().setFormula("H2SiF6", true);

        BLMaterials.Sodiumfluorosilicate = new Material.Builder(GTCA.id("sodiumfluorosilicate"))
                .fluid()
                .color(0xa16a34)
                .buildAndRegister().setFormula("Na2SiF6", true);

        BLMaterials.ConditionedBastnasiteMud = new Material.Builder(GTCA.id("conditioned_bastnasite_mud"))
                .fluid()
                .color(0xa56d35)
                .buildAndRegister().setFormula("??LaCeY??", true);

        BLMaterials.DilutedBastnasiteMud = new Material.Builder(GTCA.id("diluted_bastnasite_mud"))
                .fluid()
                .color(0xa56d35)
                .buildAndRegister().setFormula("??LaCeY??", true);

        BLMaterials.FilteredBastnasiteMud = new Material.Builder(GTCA.id("filtered_bastnasite_mud"))
                .fluid()
                .color(0xa56d35)
                .buildAndRegister().setFormula("??LaCeY??", true);

        BLMaterials.BastnasiteRareEarthOxides = new Material.Builder(GTCA.id("bastnasite_rare_earth_oxides_dust"))
                .dust()
                .color(0x774d25).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??LaCeY??", true);

        BLMaterials.AcidLeachedBastnasiteRareEarthOxides = new Material.Builder(GTCA.id("acid_leached_rare_earth_oxides_dust"))
                .dust()
                .color(0x774d25).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??LaCeY??", true);

        BLMaterials.RoastedRareEarthOxides = new Material.Builder(GTCA.id("roasted_rare_earth_oxides_dust"))
                .dust()
                .color(0x572d19).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();

        BLMaterials.WetRareEarthOxides = new Material.Builder(GTCA.id("wet_rare_earth_oxides_dust"))
                .dust()
                .color(0x572d19).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();

        BLMaterials.CeriumOxidisedRareEarthOxides = new Material.Builder(GTCA.id("cerium_oxidised_rare_earth_oxides"))
                .dust()
                .color(0x582d1b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();

        BLMaterials.BastnasiteRarerEarthOxides = new Material.Builder(GTCA.id("bastnasite_rarer_earth_oxides_dust"))
                .dust()
                .color(0x582d1b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();

        BLMaterials.NitrogenatedBastnasiteRarerEarthOxides = new Material.Builder(GTCA.id("nitrogenated_bastnasite_rarer_earth_oxides"))
                .fluid()
                .color(0x7b4731)
                .buildAndRegister();

        BLMaterials.BastnasiteRarerEarthOxideSuspension = new Material.Builder(GTCA.id("bastnasite_rarer_earth_oxides_suspension"))
                .fluid()
                .color(0x7b4731)
                .buildAndRegister();

        BLMaterials.SamaricRareEarthConcentrate = new Material.Builder(GTCA.id("samaric_rare_earth_concentrate_dust"))
                .dust()
                .color(0x633423).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmHoTb??", true);

        BLMaterials.FluorinatedSamaricConcentrate = new Material.Builder(GTCA.id("fluorinated_samaric_concentrate_dust"))
                .dust()
                .color(0x8e656b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmHo??", true);

        BLMaterials.SamariumTerbiumMixture = new Material.Builder(GTCA.id("samarium_terbium_mixture_dust"))
                .dust()
                .color(0x7d666c).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmTb??", true);

        BLMaterials.NitrogenatedSamariumTerbiumMixture = new Material.Builder(GTCA.id("nitrogenated_samarium_terbium_mixture_dust"))
                .dust()
                .color(0x7f676e).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmTb??NH4NO3", true);

        BLMaterials.TerbiumNitrate = new Material.Builder(GTCA.id("terbium_nitrate_dust"))
                .dust()
                .color(0x5f8f00).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("TbNO3", true);

        BLMaterials.SamariumResidue = new Material.Builder(GTCA.id("samarium_residue_dust"))
                .dust()
                .color(0x87847e).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??SmGd??", true);

        BLMaterials.CeriumDioxide = new Material.Builder(GTCA.id("cerium_dioxide_dust"))
                .dust()
                .color(0x909090).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("CeO2", true);

        BLMaterials.CeriumChloride = new Material.Builder(GTCA.id("cerium_chloride_dust"))
                .dust()
                .color(0x888888).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("CeCl3", true);

        BLMaterials.CeriumOxalate = new Material.Builder(GTCA.id("cerium_oxalate_dust"))
                .dust()
                .color(0x8c8c7b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("Ce2(C2O4)3", true);

        BLMaterials.Oxalate = new Material.Builder(GTCA.id("oxalate"))
                .fluid()
                .color(0x5da243)
                .buildAndRegister();

        BLMaterials.CeriumIIIOxide = new Material.Builder(GTCA.id("cerium_iii_dust"))
                .dust()
                .color(0x949482).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("Ce2O3", true);

        BLMaterials.NeodymiumRareEarthConcentrate = new Material.Builder(GTCA.id("neodymium_rare_earth_concentrate_dust"))
                .dust()
                .color(0x5d3121).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("??LaNdPr??", true);

        BLMaterials.LanthaniumChloride = new Material.Builder(GTCA.id("lanthanium_chloride_dust"))
                .dust()
                .color(0x30413b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("LaCl3", true);

        BLMaterials.NeodymiumOxide = new Material.Builder(GTCA.id("neodymium_oxide_dust"))
                .dust()
                .color(0x30413b).iconSet(MaterialIconSet.DULL)
                .buildAndRegister().setFormula("Nd2O3", true);
    }
}
