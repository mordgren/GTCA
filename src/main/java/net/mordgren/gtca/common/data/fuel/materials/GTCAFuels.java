package net.mordgren.gtca.common.data.fuel.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.common.data.GTMaterials;
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

        GTCAMaterials.DiisononylPhthalate = new Material.Builder(GTCA.id("diisononyl_phthalate"))
                .fluid()
                .components(GTMaterials.Carbon, 26, GTMaterials.Hydrogen, 42, GTMaterials.Oxygen, 4)
                .color(0x262626)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();

        GTCAMaterials.MAPP = new Material.Builder(GTCA.id("mapp"))
                .fluid()
                .color(0x686868)
                .buildAndRegister()
                .setFormula("5(C3H4)3(C3H4)2(C3H8)(C4H10)",true);

        GTCAMaterials.SuperheatedSteam = new Material.Builder(GTCA.id("sh_steam"))
                .gas(600)
                .color(0xe6e6e6)
                .buildAndRegister();

        GTCAMaterials.SuperCriticalSteam = new Material.Builder(GTCA.id("sc_steam"))
                .gas(648)
                .color(0x212121)
                .buildAndRegister();

    }
}
