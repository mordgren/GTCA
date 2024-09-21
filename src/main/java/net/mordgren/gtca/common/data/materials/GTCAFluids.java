package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;

public class GTCAFluids {
    public static void init(){

        GTCAMaterials.EnderFluid = new Material.Builder(GTCA.id("ender_fluid"))
                .fluid()
                .components(GTMaterials.EnderPearl, 1)
                .flags(
                        MaterialFlags.DISABLE_DECOMPOSITION
                )
                .color(0x074852)
                .buildAndRegister();

        GTCAMaterials.SphaleriteFroth = new Material.Builder(GTCA.id("sphalerite_froth")).fluid().color(0xdadada).buildAndRegister();
        GTCAMaterials.ChalcopyriteFroth = new Material.Builder(GTCA.id("chalcopyrite_froth")).fluid().color(0x896726).buildAndRegister();
        GTCAMaterials.NickelFroth = new Material.Builder(GTCA.id("nickel_froth")).fluid().color(0xababd4).buildAndRegister();
        GTCAMaterials.PlatinumFroth = new Material.Builder(GTCA.id("platinum_froth")).fluid().color(0xececba).buildAndRegister();
        GTCAMaterials.PentlanditeFroth = new Material.Builder(GTCA.id("pentlandite_froth")).fluid().color(0x8e8109).buildAndRegister();
        GTCAMaterials.RedstoneFroth = new Material.Builder(GTCA.id("redstone_froth")).fluid().color(0xa80606).buildAndRegister();
        GTCAMaterials.SpessartineFroth = new Material.Builder(GTCA.id("spessartine_froth")).fluid().color(0xd75757).buildAndRegister();
        GTCAMaterials.GrossularFroth = new Material.Builder(GTCA.id("grossular_froth")).fluid().color(0xad5904).buildAndRegister();
        GTCAMaterials.AlmandineFroth = new Material.Builder(GTCA.id("almandine_froth")).fluid().color(0xd60606).buildAndRegister();
        GTCAMaterials.PyropeFroth = new Material.Builder(GTCA.id("pyrope_froth")).fluid().color(0x672e56).buildAndRegister();
        GTCAMaterials.MonaziteFroth = new Material.Builder(GTCA.id("monazite_froth")).fluid().color(0x2f402f).buildAndRegister();
        GTCAMaterials.SpruceOil = new Material.Builder(GTCA.id("spruce_oil")).fluid().color(0x552d0b).buildAndRegister();
    }
}
