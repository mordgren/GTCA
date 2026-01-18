package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import net.mordgren.gtca.common.data.materials.*;

public class GTCAMaterials {
    public static void init() {
        GTCAFuels.init();
        GTCAElementMetals.init();
        GTCAMetals.init();
        GTCAChemicals.init();
        GTCAFluids.init();
        GTCAPlasmas.init();
        MaterialFlagAddition.init();
        BLMaterials.init();
    }

    // Fuels

    public static Material HighPressureSteam;
    public static Material E85Fuel;
    public static Material DiisononylPhthalate;
    public static Material SuperCriticalSteam;
    public static Material SuperheatedSteam;



    // Chemicals

    public static Material DymethylEther;
    public static Material Propyne;
    public static Material Propadiene;
    public static Material MAPP;
    public static Material IsononylAlcohol;
    public static Material PhthalicAnhydride;
    public static Material Oxylene;
    public static Material Octene;
    public static Material CarbonDisulfide;
    public static Material SphaleriteFroth;
    public static Material ChalcopyriteFroth;
    public static Material NickelFroth;
    public static Material PlatinumFroth;
    public static Material PentlanditeFroth;
    public static Material RedstoneFroth;
    public static Material SpessartineFroth;
    public static Material GrossularFroth;
    public static Material AlmandineFroth;
    public static Material PyropeFroth;
    public static Material MonaziteFroth;
    public static Material RedMudSlurry;
    public static Material SpruceOil;
    public static Material SiliconTetrachloride;
    public static Material SiliconNitride;
    public static Material Dihydrodiaminosilane;
    public static Material Silamid;
    public static Material RadonPolymer;
    public static Material UUMatterAmplifier;

    // Catalyst

    public static Material AluminosilicateCatalyst;

    // Alloys

    public static Material TM20MnAlloy;
    public static Material CNFAlloy;
    public static Material Dural;
    public static Material Nimonic80A;
    public static Material Moltech;
    public static Material Vitallium;
    public static Material Inconel718;
    public static Material Incoloy903;
    public static Material MAR_M200;
    public static Material MAR_CE_M200;
    public static Material Incoloy846;
    public static Material Tantalloy60;
    public static Material Tantalloy61;
    public static Material Incoloy020;
    public static Material IncoloyMA323;
    public static Material HG1223;
    public static Material IncoloyDS;
    public static Material Inconel690;
    public static Material EglinSteel;
    public static Material ZirconiumCarbide;
    public static Material HastelloyN;
    public static Material Inconel625;
    public static Material Zeron182;
    public static Material LafiumCompound;
    public static Material TriniumNaquadah;
    public static Material TriniumNaquadahCarbonite;
    public static Material Nitinol60;
    public static Material Inconel792;
    public static Material CinobiteA241;
    public static Material Pikyonium64Y;

    public static Material Neutronex;
    public static Material DuraniumX;
    public static Material Berwollium;

    public static Material AdamantiumAlloy;
    public static Material ArtheriumSn;

    public static Material ElectricalSteel;
    public static Material DarkSteel;
    public static Material EndSteel;
    public static Material MelodicAlloy;
    public static Material StellarAlloy;
    public static Material EnrichedHolmium;
    public static Material Stellite79;
    public static Material SiliconCarbide;
    public static Material QuantumAlloy;
    public static Material CelestialTungsten;
    public static Material HasteAlloy276;

    // Misc

    public static Material StrangeCrystal;
    public static Material UnknownCrystal;
    public static Material QuiteCertainCrystal;


    // Dusts

    public static Material HeavyMetalMixture;
    public static Material EglinSteelCompound;
    public static Material SodiumEthylXanthate;
    public static Material SodiumEthoxide;
    public static Material PotassiumEthylXanthate;

    // Ores

    public static Material RedZircon;
    public static Material GreenFuchsite;
    public static Material Fayalite;
    public static Material RedFuchsite;
    public static Material Germanite;

    // Plasma

    public static Material CelestialTungstenPlasma;
    public static Material HydrogenPlasma;
    public static Material SulfurPlasma;
    public static Material CalciumPlasma;
    public static Material TitaniumPlasma;
    public static Material ZincPlasma;


    // Other Fluids

    public static Material EnderFluid;
    public static Material PahoehoeLava;

    // Elements
    public static Material Adamantium;
    public static Material Orundum;
    public static Material Ohriharukon;
    public static Material CosmicNeutronium;
    public static Material CosmicPlutonium;

    // Bastnasite Line
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
    public static Material CalciumFluoride;
    public static Material SamariumTerbiumMixture; // Dust
    public static Material AmmoniumNitrate;
    public static Material HydratedAmmoniumNitrateSlurry;
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

    // Water Grade 1-8

    public static Material PurifiedWaterGradeI;
    public static Material PurifiedWaterGradeII;
    public static Material PurifiedWaterGradeIII;
    public static Material PurifiedWaterGradeIV;
    public static Material PurifiedWaterGradeV;
    public static Material PurifiedWaterGradeVI;
    public static Material PurifiedWaterGradeVII;
    public static Material PurifiedWaterGradeVIII;


}
