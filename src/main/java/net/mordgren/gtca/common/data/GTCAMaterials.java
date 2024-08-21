package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import net.mordgren.gtca.common.data.fuel.materials.GTCAFuels;
import net.mordgren.gtca.common.data.metals.materials.GTCAMetals;

public class GTCAMaterials {
    public static void init() {
        GTCAFuels.init();
        GTCAMetals.init();
    }

    public static Material HighPressureSteam;
    public static Material E85Fuel;
    public static Material DymethylEther;
    public static Material AluminosilicateCatalyst;


    public static Material TM20MnAlloy;
    public static Material CNFAlloy;
    public static Material Dural;
}
