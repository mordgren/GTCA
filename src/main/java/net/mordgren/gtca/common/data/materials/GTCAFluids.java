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

    }
}
