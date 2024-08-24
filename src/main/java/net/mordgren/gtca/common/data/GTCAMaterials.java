package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import net.mordgren.gtca.common.data.chemicals.materials.GTCAChemicals;
import net.mordgren.gtca.common.data.fuel.materials.GTCAFuels;
import net.mordgren.gtca.common.data.metals.materials.GTCAMetals;

public class GTCAMaterials {
    public static void init() {
        GTCAFuels.init();
        GTCAMetals.init();
        GTCAChemicals.init();
    }

    // Fuels

    public static Material HighPressureSteam;
    public static Material E85Fuel;

    // Chemicals

    public static Material DymethylEther;
    public static Material Propyne;
    public static Material Propadiene;
    public static Material MAPP;

    // Catalyst

    public static Material AluminosilicateCatalyst;

    // Alloys

    public static Material TM20MnAlloy;
    public static Material CNFAlloy;
    public static Material Dural;
    public static Material Nimonic80A;
    public static Material Moltech;
    public static Material Vitallium;
}
