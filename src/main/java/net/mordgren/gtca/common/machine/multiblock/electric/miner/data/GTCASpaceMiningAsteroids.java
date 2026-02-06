package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.AsteroidBuilder;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.registry.SpaceMiningRegistry;

public final class GTCASpaceMiningAsteroids {

    private GTCASpaceMiningAsteroids() {}

    public static void init() {
        GTCA.LOGGER.info("[SpaceMining] GTCASpaceMiningAsteroids.init() called");

        /**
         * ---------------------------------- MK1 RECIPES ----------------------------------
         * Ores to add:
         * Arsenic
         * Bismuth
         * Gallium
         * Iridium
         * NetherStar
         * Vintenum
         * Naquadah En
         * Naquadria
         * Tungsten
         * Titanium
         * Uranium 238/235
         * Plutonium 239
         * Strange Crystal
         * Ohriharukon
         * Mytril
         * Endium
         * Chrome
         * Osmium
         * Bedrockium
         * Space Plutonium
         * Space Neutronium
         * -----------------------------------------------------------------------------------
         */

        AsteroidBuilder.asteroid(GTCA.id("asteroid/coal"))
                .eut(1920)
                .timeSeconds(180)
                .requiresModuleMk(1)
                .distance(1, 40)
                .sizeStacks(30, 120)
                .minCWU(20)
                .weight(200)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK7_ZPM)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Coal, 0.70)
                .ore(GTMaterials.Graphite, 0.30)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/salt"))
                .eut(1920)
                .timeSeconds(180)
                .requiresModuleMk(1)
                .distance(1, 250)
                .sizeStacks(30, 120)
                .minCWU(20)
                .weight(300)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK5_IV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.TUNGSTEN_STEEL)
                .ore(GTMaterials.Salt, 0.40)
                .ore(GTMaterials.RockSalt, 0.20)
                .ore(GTMaterials.Saltpeter, 0.40)
                .buildAndRegister();


        AsteroidBuilder.asteroid(GTCA.id("asteroid/iron"))
                .eut(1920)
                .timeSeconds(180)
                .requiresModuleMk(1)
                .distance(1, 180)
                .sizeStacks(30, 150)
                .minCWU(10)
                .weight(600)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK7_ZPM)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Iron, 0.40)
                .ore(GTMaterials.Gold, 0.20)
                .ore(GTMaterials.Magnetite, 0.10)
                .ore(GTMaterials.Pyrite, 0.10)
                .ore(GTMaterials.BasalticMineralSand, 0.05)
                .ore(GTMaterials.GraniticMineralSand, 0.05)
                .buildAndRegister();


        AsteroidBuilder.asteroid(GTCA.id("asteroid/copper"))
                .eut(1920)
                .timeSeconds(180)
                .requiresModuleMk(1)
                .distance(3, 12)
                .sizeStacks(30, 150)
                .minCWU(10)
                .weight(500)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK6_LuV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.TUNGSTEN_STEEL)
                .ore(GTMaterials.Copper, 0.50)
                .ore(GTMaterials.Chalcopyrite, 0.30)
                .ore(GTMaterials.Malachite, 0.20)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/lead"))
                .eut(1920)
                .timeSeconds(180)
                .requiresModuleMk(1)
                .distance(5, 150)
                .sizeStacks(30, 100)
                .minCWU(20)
                .weight(220)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK8_UV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Lead, 0.30)
                .ore(GTMaterials.Arsenic, 0.25)
                .ore(GTMaterials.Barium, 0.25)
                .ore(GTMaterials.Lepidolite, 0.20)
                .buildAndRegister();


        AsteroidBuilder.asteroid(GTCA.id("asteroid/adamantium"))
                .eut(1920)
                .timeSeconds(250)
                .requiresModuleMk(1)
                .distance(5, 120)
                .sizeStacks(30, 120)
                .minCWU(30)
                .weight(300)
                .droneTiers(DroneTier.MK4_EV, DroneTier.MK7_ZPM)
                .drillRange(DrillMaterialTier.TITANIUM, DrillMaterialTier.NAQUADAH)
                .ore(GTCAMaterials.Adamantium, 0.25)
                .ore(GTMaterials.Bismuth, 0.20)
                .ore(GTMaterials.Antimony, 0.20)
                .ore(GTMaterials.Gallium, 0.20)
                .ore(GTMaterials.Lithium, 0.15)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/casserite"))
                .eut(1920)
                .timeSeconds(150)
                .requiresModuleMk(1)
                .distance(2, 100)
                .sizeStacks(50, 200)
                .minCWU(10)
                .weight(400)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK5_IV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.TUNGSTEN_STEEL)
                .ore(GTMaterials.Cassiterite, 0.70)
                .ore(GTMaterials.Tin, 0.15)
                .ore(GTMaterials.Asbestos, 0.15)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/aluminuim"))
                .eut(1920)
                .timeSeconds(110)
                .requiresModuleMk(1)
                .distance(5, 20)
                .sizeStacks(10, 20)
                .minCWU(20)
                .weight(120)
                .droneTiers(DroneTier.MK2_MV, DroneTier.MK4_EV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.TITANIUM)
                .ore(GTMaterials.Aluminium, 0.50)
                .ore(GTMaterials.Bauxite, 0.35)
                .ore(GTMaterials.Rutile, 0.15)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/aluminium_monazite"))
                .eut(1920)
                .timeSeconds(190)
                .requiresModuleMk(1)
                .distance(40, 120)
                .sizeStacks(10, 80)
                .minCWU(60)
                .weight(250)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK6_LuV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Aluminium, 0.35)
                .ore(GTMaterials.Bauxite, 0.15)
                .ore(GTMaterials.Bastnasite, 0.25)
                .ore(GTMaterials.Monazite, 0.25)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/nickel"))
                .eut(1920)
                .timeSeconds(165)
                .requiresModuleMk(1)
                .distance(5,20)
                .sizeStacks(20,40)
                .minCWU(20)
                .weight(170)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK5_IV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.TUNGSTEN_STEEL)
                .ore(GTMaterials.Nickel, 0.40)
                .ore(GTMaterials.Pentlandite, 0.30)
                .ore(GTMaterials.Garnierite, 0.30)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/magnesium"))
                .eut(1920)
                .timeSeconds(120)
                .requiresModuleMk(1)
                .distance(10,200)
                .sizeStacks(10,80)
                .minCWU(60)
                .weight(170)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK6_LuV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Magnesium, 0.60)
                .ore(GTMaterials.Manganese, 0.40)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/lapis"))
                .eut(7680)
                .timeSeconds(190)
                .requiresModuleMk(1)
                .distance(20,200)
                .sizeStacks(10,50)
                .minCWU(60)
                .weight(250)
                .droneTiers(DroneTier.MK3_HV, DroneTier.MK8_UV)
                .drillRange(DrillMaterialTier.TITANIUM, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Lapis, 0.60)
                .ore(GTMaterials.Calcite, 0.20)
                .ore(GTMaterials.Lazurite, 0.10)
                .ore(GTMaterials.Sodalite, 0.10)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/cobalt_iridium"))
                .eut(7680)
                .timeSeconds(175)
                .requiresModuleMk(1)
                .distance(30,100)
                .sizeStacks(20,90)
                .minCWU(180) // wth
                .weight(150)
                .droneTiers(DroneTier.MK4_EV, DroneTier.MK9_UHV)
                .drillRange(DrillMaterialTier.TITANIUM, DrillMaterialTier.NAQUADAH_ALLOY)
                .ore(GTMaterials.Cobalt, 0.90)
                .ore(GTMaterials.Iridium, 0.10)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/lapis"))
                .eut(7680)
                .timeSeconds(190)
                .requiresModuleMk(1)
                .distance(20,200)
                .sizeStacks(10,50)
                .minCWU(60)
                .weight(250)
                .droneTiers(DroneTier.MK3_HV, DroneTier.MK8_UV)
                .drillRange(DrillMaterialTier.TITANIUM, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Lapis, 0.60)
                .ore(GTMaterials.Calcite, 0.20)
                .ore(GTMaterials.Lazurite, 0.10)
                .ore(GTMaterials.Sodalite, 0.10)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/ruby"))
                .eut(30720)
                .timeSeconds(290)
                .requiresModuleMk(1)
                .distance(17,40)
                .sizeStacks(30,160)
                .minCWU(60)
                .weight(180)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK6_LuV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.TUNGSTEN_STEEL)
                .ore(GTMaterials.Ruby, 0.15)
                .ore(GTMaterials.Emerald, 0.15)
                .ore(GTMaterials.Sapphire, 0.15)
                .ore(GTMaterials.GreenSapphire, 0.15)
                .ore(GTMaterials.Diamond, 0.075)
                .ore(GTMaterials.Opal, 0.075)
                .ore(GTMaterials.Amethyst, 0.075)
                .ore(GTMaterials.Topaz, 0.10)
                .ore(GTMaterials.BlueTopaz, 0.05)
                .ore(GTMaterials.Bauxite, 0.05)
                .ore(GTMaterials.NetherStar, 0.01)
                .ore(GTCAMaterials.Vinteum, 0.04)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/tellurium"))
                .eut(30720)
                .timeSeconds(210)
                .requiresModuleMk(1)
                .distance(40,240)
                .sizeStacks(20,80)
                .minCWU(90)
                .weight(100)
                .droneTiers(DroneTier.MK5_IV, DroneTier.MK9_UHV)
                .drillRange(DrillMaterialTier.TUNGSTEN_STEEL, DrillMaterialTier.NAQUADAH_ALLOY)
                .ore(GTMaterials.Tellurium, 0.15)
                .ore(GTMaterials.Thulium, 0.10)
                .ore(GTMaterials.Tantalum, 0.15)
                .ore(GTMaterials.Lutetium, 0.5)
                .ore(GTMaterials.Redstone, 0.55)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/phosphate"))
                .eut(30720)
                .timeSeconds(190)
                .requiresModuleMk(1)
                .distance(60,250)
                .sizeStacks(20,150)
                .minCWU(60)
                .weight(150)
                .droneTiers(DroneTier.MK5_IV, DroneTier.MK10_UEV)
                .drillRange(DrillMaterialTier.TUNGSTEN_STEEL, DrillMaterialTier.NEUTRONIUM)
                .ore(GTMaterials.Phosphate, 0.45)
                .ore(GTMaterials.TricalciumPhosphate, 0.25)
                .ore(GTMaterials.Sulfur, 0.30)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/naquadah"))
                .eut(30720)
                .timeSeconds(180)
                .requiresModuleMk(1)
                .distance(50,150)
                .sizeStacks(20,80)
                .minCWU(200) // damn
                .weight(200)
                .droneTiers(DroneTier.MK5_IV, DroneTier.MK8_UV)
                .drillRange(DrillMaterialTier.TUNGSTEN_STEEL, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Naquadah, 0.40)
                .ore(GTMaterials.NaquadahEnriched, 0.35)
                .ore(GTMaterials.Naquadria, 0.25)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/tungsten"))
                .eut(30720)
                .timeSeconds(150)
                .requiresModuleMk(1)
                .distance(60,200)
                .sizeStacks(30,70)
                .minCWU(120)
                .weight(100)
                .droneTiers(DroneTier.MK1_LV, DroneTier.MK6_LuV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.TUNGSTEN_STEEL)
                .ore(GTMaterials.Tungsten, 0.30)
                .ore(GTMaterials.Titanium, 0.30)
                .ore(GTMaterials.Neodymium, 0.20)
                .ore(GTMaterials.Molybdenum, 0.15)
                .ore(GTMaterials.Tungstate, 0.05)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/uranium"))
                .eut(30720)
                .timeSeconds(210)
                .requiresModuleMk(1)
                .distance(30,70)
                .sizeStacks(40,80)
                .minCWU(120)
                .weight(150)
                .droneTiers(DroneTier.MK3_HV, DroneTier.MK7_ZPM)
                .drillRange(DrillMaterialTier.TITANIUM, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Uranium238, 0.25)
                .ore(GTMaterials.Uranium235, 0.20)
                .ore(GTMaterials.Plutonium239, 0.25)
                .ore(GTMaterials.Plutonium241, 0.20)
                .ore(GTMaterials.Thorium, 0.10)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/mystery"))
                .eut(122880)
                .timeSeconds(110)
                .requiresModuleMk(1)
                .distance(65,120)
                .sizeStacks(30,60)
                .minCWU(300)
                .weight(220)
                .droneTiers(DroneTier.MK5_IV, DroneTier.MK10_UEV)
                .drillRange(DrillMaterialTier.TUNGSTEN_STEEL, DrillMaterialTier.NEUTRONIUM)
                .ore(GTCAMaterials.StrangeCrystal, 0.75)
                .ore(GTCAMaterials.Mytryl, 0.05)
                .ore(GTCAMaterials.Ohriharukon, 0.10)
                .ore(GTCAMaterials.Endium, 0.10)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/chrome"))
                .eut(30720)
                .timeSeconds(60)
                .requiresModuleMk(1)
                .distance(10,20)
                .sizeStacks(16,32)
                .minCWU(40)
                .weight(100)
                .droneTiers(DroneTier.MK2_MV, DroneTier.MK6_LuV)
                .drillRange(DrillMaterialTier.STEEL, DrillMaterialTier.TUNGSTEN_STEEL)
                .ore(GTMaterials.Chromium, 0.50)
                .ore(GTMaterials.Chromite, 0.30)
                .ore(GTMaterials.Ruby, 0.20)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/platinum"))
                .eut(30720)
                .timeSeconds(60)
                .requiresModuleMk(1)
                .distance(10,50)
                .sizeStacks(20,40)
                .minCWU(60)
                .weight(130)
                .droneTiers(DroneTier.MK3_HV, DroneTier.MK7_ZPM)
                .drillRange(DrillMaterialTier.TITANIUM, DrillMaterialTier.NAQUADAH)
                .ore(GTMaterials.Platinum, 0.60)
                .ore(GTMaterials.Palladium, 0.20)
                .ore(GTMaterials.Iridium, 0.15)
                .ore(GTMaterials.Osmium, 0.05)
                .buildAndRegister();

        AsteroidBuilder.asteroid(GTCA.id("asteroid/spneutron"))
                .eut(122880)
                .timeSeconds(200)
                .requiresModuleMk(1)
                .distance(150,200)
                .sizeStacks(10,50)
                .minCWU(240)
                .weight(100)
                .droneTiers(DroneTier.MK8_UV, DroneTier.MK10_UEV)
                .drillRange(DrillMaterialTier.NAQUADAH, DrillMaterialTier.NEUTRONIUM)
                .ore(GTCAMaterials.CosmicNeutronium, 0.60)
                .ore(GTCAMaterials.CosmicPlutonium, 0.20)
                .ore(GTMaterials.Neutronium, 0.15)
                .ore(GTCAMaterials.Bedrockium, 0.05) // xd
                .buildAndRegister();



        GTCA.LOGGER.info("[SpaceMining] Registered asteroids = {}", SpaceMiningRegistry.size());
    }
}
