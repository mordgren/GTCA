package net.mordgren.gtca.common.data;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import static net.mordgren.gtca.GTCARegistration.REGISTRATE;

public class GTCAItems {
    public static ItemEntry<Item> Electron = REGISTRATE.item("electron", Item::new).register();
    public static ItemEntry<Item> Graviton = REGISTRATE.item("graviton", Item::new).register();
    public static ItemEntry<Item> HeliumIon = REGISTRATE.item("helium_ion", Item::new).register();
    public static ItemEntry<Item> HydrogenIon = REGISTRATE.item("hydrogen_ion", Item::new).register();
    public static ItemEntry<Item> Neutron = REGISTRATE.item("neutron", Item::new).register();
    public static ItemEntry<Item> Proton = REGISTRATE.item("proton", Item::new).register();
    public static ItemEntry<Item> UnknownParticle = REGISTRATE.item("unknown_particle", Item::new).register();

    public static void init() {
    }
}
