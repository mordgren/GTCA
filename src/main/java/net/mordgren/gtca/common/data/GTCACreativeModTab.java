package net.mordgren.gtca.common.data;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.mordgren.gtca.GTCA;

import static net.mordgren.gtca.common.registry.GTCARegistration.REGISTRATE;

public class GTCACreativeModTab {

    public static RegistryEntry<CreativeModeTab> MAIN = REGISTRATE.defaultCreativeTab("main",
                    builder -> builder
                            .icon(() -> Items.NETHER_STAR.getDefaultInstance())
                            .title(REGISTRATE.addLang("itemGroup", GTCA.id("main"), "GT Community Additions"))
                            .build())
            .register();

    public static void init() {}
}