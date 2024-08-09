package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.mordgren.gtca.GTCA;


public class GTCAMaterials {
    public static void init() {

    }
    public static final Material HighPressureSteam = new Material.Builder(GTCA.id("high_pressure_steam"))
            .fluid()
            .color(0x777777)
            .buildAndRegister();

    public static final Material E85Fuel = new Material.Builder(GTCA.id("e85_fuel"))
            .fluid()
            .color(0xccddff)
            .buildAndRegister();

    public static final Material DymethylEther = new Material.Builder(GTCA.id("dymethyl_ether"))
            .gas()
            .color(0xffe6ff)
            .buildAndRegister();

    public static final Material AluminosilicateCatalyst = new Material.Builder(GTCA.id("aluminosilicate_catalyst"))
            .dust()
            .color(0x284d8a).iconSet(MaterialIconSet.FLINT)
            .buildAndRegister();
}
