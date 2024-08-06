package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import net.mordgren.gtca.GTCA;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTCAMaterials {
    public static void init() {

    }
    public static final Material HighPressureSteam = new Material.Builder(GTCA.id("high_pressure_steam"))
            .fluid()
            .color(0x777777)
            .buildAndRegister();
}
