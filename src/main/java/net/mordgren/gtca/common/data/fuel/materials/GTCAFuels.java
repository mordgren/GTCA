package net.mordgren.gtca.common.data.fuel.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class GTCAFuels {
    public static void init() {

        GTCAMaterials.HighPressureSteam = new Material.Builder(GTCA.id("high_pressure_steam"))
                .fluid()
                .color(0x777777)
                .buildAndRegister();

        GTCAMaterials.E85Fuel = new Material.Builder(GTCA.id("e85_fuel"))
                .fluid()
                .color(0xccddff)
                .buildAndRegister();

        GTCAMaterials.DymethylEther = new Material.Builder(GTCA.id("dymethyl_ether"))
                .gas()
                .color(0xffe6ff)
                .buildAndRegister();

        GTCAMaterials.AluminosilicateCatalyst = new Material.Builder(GTCA.id("aluminosilicate_catalyst"))
                .dust()
                .color(0x284d8a).iconSet(MaterialIconSet.FLINT)
                .buildAndRegister();

    }
}
