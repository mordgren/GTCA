package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAElements;
import net.mordgren.gtca.common.data.GTCAMaterials;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.NO_SMELTING;
import static com.gregtechceu.gtceu.common.data.GTMaterials.EXT2_METAL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.EXT_METAL;

public class GTCAElementMetals {
    public static void init(){

        GTCAMaterials.Adamantium = new Material.Builder(GTCA.id("adamantium"))
                .color(0x8fa5c9)
                .ingot(6)
                .liquid(new FluidBuilder().temperature(933))
                .appendFlags(EXT2_METAL, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_RING, GENERATE_FRAME,
                        GENERATE_SPRING)
                .element(GTCAElements.Ad)
                .toolStats(ToolProperty.Builder.of(200.0F, 150.0F, 127535, 6)
                        .attackSpeed(0.5F).enchantability(33).build())
                .rotorStats(500, 300, 12.0f, 877360)
                .fluidPipeProperties(120_000, 7000, true, true, true, true)
                .radioactiveHazard(10)
                .buildAndRegister();

        GTCAMaterials.Orundum = new Material.Builder(GTCA.id("orundum"))
                .color(0xff0000)
                .ingot()
                .liquid(new FluidBuilder().temperature(120_000))
                .appendFlags(EXT_METAL, GENERATE_BOLT_SCREW, GENERATE_FRAME, GENERATE_GEAR, GENERATE_LONG_ROD, NO_SMELTING)
                .element(GTCAElements.Or)
                .buildAndRegister();
    }
}
