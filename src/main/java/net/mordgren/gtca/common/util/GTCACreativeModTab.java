package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCAMachines;

import static net.mordgren.gtca.GTCARegistration.REGISTRATE;

public class GTCACreativeModTab {
    public static RegistryEntry<CreativeModeTab> MAIN = REGISTRATE.defaultCreativeTab("main",
                    builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("main", REGISTRATE))
                            .icon(() -> GTCAMachines.COMET_CYCLOTRON.asStack())
                            .title(REGISTRATE.addLang("itemGroup", GTCA.id("main"), "GT Community Additions"))
                            .build())
            .register();
}