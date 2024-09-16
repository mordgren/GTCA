package net.mordgren.gtca.common.data;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import static net.mordgren.gtca.GTCARegistration.REGISTRATE;

public class GTCAItems {
    public static ItemEntry<Item> test = REGISTRATE.item("test", Item::new).register();

    public static void init() {
    }
}
