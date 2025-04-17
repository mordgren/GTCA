package net.mordgren.gtca.common.data.BastnasiteLine;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.mordgren.gtca.GTCA;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;

public class BLMaterials {

    public static Material MuddyBastnasiteRareEarthSolution;
    public static Material SteamCrackedBastnasiteMud;
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
    public static Material SamaricRareEarthConcentrare; // Dust
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
                .dust()
                .color(0xb99b7e).iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
    }
}
