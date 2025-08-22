package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.IComponentItem;
import com.gregtechceu.gtceu.api.item.component.ElectricStats;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.world.item.Item;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.registry.GTCARegistration;
import net.mordgren.gtca.common.item.ScrapBoxBehaviour;

import static com.gregtechceu.gtceu.common.data.models.GTModels.overrideModel;
import static net.mordgren.gtca.common.registry.GTCARegistration.REGISTRATE;
import com.gregtechceu.gtceu.common.data.models.GTModels;

public class GTCAItems {
    static{GTCARegistration.REGISTRATE.creativeModeTab(() -> GTCACreativeModTab.MAIN);}

    public static ItemEntry<Item> Electron = REGISTRATE.item("electron", Item::new).register();

    public static ItemEntry<Item> Graviton = REGISTRATE.item("graviton", Item::new).register();

    public static ItemEntry<Item> HeliumIon = REGISTRATE.item("helium_ion", Item::new).register();

    public static ItemEntry<Item> HydrogenIon = REGISTRATE.item("hydrogen_ion", Item::new).register();

    public static ItemEntry<Item> Neutron = REGISTRATE.item("neutron", Item::new).register();

    public static ItemEntry<Item> Proton = REGISTRATE.item("proton", Item::new).register();

    public static ItemEntry<Item> UnknownParticle = REGISTRATE.item("unknown_particle", Item::new).register();

    public static ItemEntry<Item> QuantumAnomaly = REGISTRATE.item("quantum_anomaly", Item::new).register();

    public static ItemEntry<Item> PNEresistor = REGISTRATE.item("pne_resistor", Item::new).register();

// Nanites

    public static ItemEntry<Item> GoldNanites  = REGISTRATE.item("gold_nanites", Item::new).register();
    public static ItemEntry<Item> SilverNanites = REGISTRATE.item("silver_nanites", Item::new).register();
    public static ItemEntry<Item> CarbonNanites = REGISTRATE.item("carbon_nanites", Item::new).register();

    // forth

    public static ItemEntry<Item> TungstenCarbideGrindBall = REGISTRATE.item("grinding_ball_tungsten_carbide", Item::new).properties(p -> p.stacksTo(1)).register();
    public static ItemEntry<Item> NqGrindBall = REGISTRATE.item("grinding_ball_nq", Item::new).properties(p -> p.stacksTo(1)).register();
    public static ItemEntry<Item> TungstenCarbideGrindballRaw = REGISTRATE.item("grinding_ball_tungsten_carbide_raw", Item::new).register();
    public static ItemEntry<Item> NqGrindBallRaw = REGISTRATE.item("grinding_ball_nq_raw", Item::new).register();

    public static ItemEntry<Item> MilledSphalerite = REGISTRATE.item("milled_sphalerite", Item::new).register();
    public static ItemEntry<Item> MilledChalcopyrite = REGISTRATE.item("milled_chalcopyrite", Item::new).register();
    public static ItemEntry<Item> MilledNickel = REGISTRATE.item("milled_nickel", Item::new).register();
    public static ItemEntry<Item> MilledPlatinum = REGISTRATE.item("milled_platinum", Item::new).register();
    public static ItemEntry<Item> MilledPentlandite = REGISTRATE.item("milled_pentlandite", Item::new).register();
    public static ItemEntry<Item> MilledRedstone = REGISTRATE.item("milled_redstone", Item::new).register();
    public static ItemEntry<Item> MilledSpessartine = REGISTRATE.item("milled_spessartine", Item::new).register();
    public static ItemEntry<Item> MilledGrossular = REGISTRATE.item("milled_grossular", Item::new).register();
    public static ItemEntry<Item> MilledAlmandine = REGISTRATE.item("milled_almandine", Item::new).register();
    public static ItemEntry<Item> MilledPyrope = REGISTRATE.item("milled_pyrope", Item::new).register();
    public static ItemEntry<Item> MilledMonazite = REGISTRATE.item("milled_monazite", Item::new).register();

    public static ItemEntry<Item> CrushedSpruce = REGISTRATE.item("crushed_spruce", Item::new).register();

    public static ItemEntry<Item> Scrap = REGISTRATE.item("scrap", Item::new).register();
    public static ItemEntry<ScrapBoxBehaviour> ScrapBox = REGISTRATE.item("scrapbox", props -> new ScrapBoxBehaviour(props)).register();

    public static ItemEntry<ComponentItem> PROTON_CELL = REGISTRATE
            .item("proton_cell", ComponentItem::create)
            .model(overrideModel(GTCA.id("battery"),5))
            .onRegister(attach(ElectricStats.createRechargeableBattery(4_915_200_000L, GTValues.LuV)))
            .tag(CustomTags.LuV_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> ELECTRON_CELL = REGISTRATE
            .item("electron_cell", ComponentItem::create)
            .model(overrideModel(GTCA.id("battery"),5))
            .onRegister(attach(ElectricStats.createRechargeableBattery(19_660_800_000L, GTValues.ZPM)))
            .tag(CustomTags.ZPM_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> QUARK_ENTANGLEMENT = REGISTRATE
            .item("quark_entanglement", ComponentItem::create)
            .model(overrideModel(GTCA.id("battery"),5))
            .onRegister(attach(ElectricStats.createRechargeableBattery(78_643_200_000L, GTValues.UV)))
            .tag(CustomTags.UV_BATTERIES)
            .register();

    public static ItemEntry<ComponentItem> GRAVITON_ANOMALY = REGISTRATE
            .item("graviton_anomaly", ComponentItem::create)
            .model(overrideModel(GTCA.id("battery"),5))
            .onRegister(attach(ElectricStats.createRechargeableBattery(1_024_000_000_000_000L, GTValues.UHV)))
            .tag(CustomTags.UHV_BATTERIES)
            .register();

    public static ItemEntry<Item> PROTON_CELL_EMPTY = REGISTRATE.item("proton_cell_empty", Item::new).register();
    public static ItemEntry<Item> ELECTRON_CELL_EMPTY = REGISTRATE.item("electron_cell_empty", Item::new).register();
    public static ItemEntry<Item> QUARK_ENTANGLEMENT_EMPTY = REGISTRATE.item("quark_entanglement_empty", Item::new).register();
    public static ItemEntry<Item> GRAVITON_ANOMALY_EMPTY = REGISTRATE.item("graviton_anomaly_empty", Item::new).register();

    // drones

    public static ItemEntry<Item> MinerDroneHV = REGISTRATE.item("hv_drone", Item::new).register();
    public static ItemEntry<Item> MinerDroneEV = REGISTRATE.item("ev_drone", Item::new).register();
    public static ItemEntry<Item> MinerDroneIV = REGISTRATE.item("iv_drone", Item::new).register();
    public static ItemEntry<Item> MinerDroneLuV = REGISTRATE.item("luv_drone", Item::new).register();
    public static ItemEntry<Item> MinerDroneZPM = REGISTRATE.item("zpm_drone", Item::new).register();

    // wafers

    public static ItemEntry<Item> EUROPIUM_BOULE = REGISTRATE.item("europium_doped_monocrystalyne_silicon_boule", Item::new).register();
    public static ItemEntry<Item> EUROPIUM_WAFER = REGISTRATE.item("europium_waffle", Item::new).register();
    public static ItemEntry<Item> NANO_POWER_IC = REGISTRATE.item("nano_power_ic", Item::new).register();
    public static ItemEntry<Item> NPIC_WAFER = REGISTRATE.item("npic_wafer", Item::new).register();
    public static ItemEntry<Item> PPIC_WAFER = REGISTRATE.item("ppic_wafer", Item::new).register();
    public static ItemEntry<Item> PIKO_WAFER = REGISTRATE.item("piko_wafer_ic", Item::new).register();
    public static ItemEntry<Item> NANOTUBE_SPOOL = REGISTRATE.item("nanotube_spool", Item::new).register();


    private static <T extends IComponentItem> NonNullConsumer<T> attach(IItemComponent components) {
        return item -> item.attachComponents(components);






    }


    public static void init(){
    }
}
