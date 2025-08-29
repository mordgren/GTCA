package net.mordgren.gtca.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAElements;
import net.mordgren.gtca.common.data.GTCAMaterials;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;


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
                .fluidPipeProperties(130_000, 7000, true, true, true, true)
                .radioactiveHazard(10)
                .ore()
                .buildAndRegister();

        GTCAMaterials.Orundum = new Material.Builder(GTCA.id("orundum"))
                .iconSet(MaterialIconSet.METALLIC)
                .color(0xff0000)
                .ingot()
                .liquid(new FluidBuilder().temperature(125_000))
                .appendFlags(EXT_METAL, GENERATE_BOLT_SCREW, GENERATE_FRAME, GENERATE_GEAR, GENERATE_LONG_ROD, NO_SMELTING)
                .element(GTCAElements.Or)
                .ore()
                .buildAndRegister();

        GTCAMaterials.Ohriharukon = new Material.Builder(GTCA.id("ohriharukon"))
                .color(0x004d25)
                .ingot()
                .iconSet(MaterialIconSet.ROUGH)
                .liquid(new FluidBuilder().temperature(1465))
                .appendFlags(EXT_METAL, GENERATE_BOLT_SCREW, GENERATE_FRAME, GENERATE_GEAR, GENERATE_LONG_ROD, NO_SMELTING)
                .element(GTCAElements.Oh)
                .ore()
                .buildAndRegister();

        GTCAMaterials.CosmicNeutronium = new Material.Builder(GTCA.id("cosmic_neutronium"))
                .color(0x0a0a0a)
                .ingot()
                .liquid(new FluidBuilder().temperature(9900))
                .appendFlags(EXT_METAL, GENERATE_BOLT_SCREW, GENERATE_FRAME, GENERATE_GEAR, GENERATE_LONG_ROD, GENERATE_FOIL, GENERATE_PLATE, GENERATE_ROUND, NO_SMELTING, GENERATE_SPRING, GENERATE_FINE_WIRE, GENERATE_SPRING_SMALL)
                .ore()
                .blastTemp(9900, BlastProperty.GasTier.HIGHEST, VA[UHV])
                .cableProperties(8388608, 96, 92)
                .rotorStats(800, 450, 12.0f, 4024000)
                .element(GTCAElements.SpNt)
                .iconSet(GTCAMaterialSet.COSMIC_NEUTRONIUM)
                .buildAndRegister();

        GTCAMaterials.CosmicPlutonium = new Material.Builder(GTCA.id("cosmic_plutonium"))
                .color(0x3e1a1a)
                .ingot()
                .liquid(new FluidBuilder().temperature(9900))
                .appendFlags(EXT_METAL, GENERATE_BOLT_SCREW, GENERATE_FRAME, GENERATE_GEAR, GENERATE_LONG_ROD, GENERATE_FOIL, GENERATE_PLATE, GENERATE_ROUND, NO_SMELTING)
                .ore()
                .blastTemp(9000, BlastProperty.GasTier.HIGHEST, VA[ZPM])
                .element(GTCAElements.SpPu)
                .buildAndRegister();

    }
}
