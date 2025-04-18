package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.registry.registrate.SoundEntryBuilder;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import net.mordgren.gtca.GTCA;



public class GTCASounds {

    public static void init(){}

    public static final SoundEntry TG_FORGE = sound("tg_forge").build();
    public static final SoundEntry NANOFORGE = sound("nanoforge").build();

    public static SoundEntryBuilder sound(String name) {
        return new SoundEntryBuilder(GTCA.id(name));
    }
}
