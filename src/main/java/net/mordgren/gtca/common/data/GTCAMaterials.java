package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import net.mordgren.gtca.common.data.materials.*;

public class GTCAMaterials {
    public static void init() {
        GTCAFuels.init();
        GTCAMetals.init();
        GTCAChemicals.init();
        MaterialFlagAddition.init();
        GTCAFluids.init();
        GTCAPlasmas.init();
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
    public static Material SpruceOil;

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
    public static Material Inconel690;;
    public static Material EglinSteel;
    public static Material ZirconiumCarbide;
    public static Material HastealloyN;
    public static Material Inconel625;
    public static Material Zeron182;
    public static Material LafiumCompound;
    public static Material TrunuimNaquadah;
    public static Material TrunuimNaquadahCarbonite;

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

    // Plasma

    public static Material CelestialPlasma;
    public static Material HydrogenPlasma;
    public static Material SulfurPlasma;
    public static Material CalciumPlasma;
    public static Material TitaniumPlasma;
    public static Material ZincPlasma;


    // Other Fluids

    public static Material EnderFluid;



}
