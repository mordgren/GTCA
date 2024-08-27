package net.mordgren.gtca.common.data.blocks;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.util.CasingBuilder;

public class GTCACasings {
    public static final BlockEntry<Block> CASING_AEBF = CasingBuilder.createCasingBlock("casing_aebf",
            GTCA.id("block/casing/casing_aebf"));

    public static final BlockEntry<Block> CASING_GREENHOUSE = CasingBuilder.createCasingBlock("casing_greenhouse",
            GTCA.id("block/casing/casing_greenhouse"));

    public static final BlockEntry<Block> DURAL_CASING = CasingBuilder.createCasingBlock("dural_casing",
            GTCA.id("block/casing/dural_casing"));

    public static final BlockEntry<Block> VITALIUM_CASING = CasingBuilder.createCasingBlock("vitalium_casing",
            GTCA.id("block/casing/vitalium_casing"));

    public static final BlockEntry<Block> NIMONIC_CASING = CasingBuilder.createCasingBlock("nimonic_casing",
            GTCA.id("block/casing/nimonic_casing"));

    public static final BlockEntry<Block> SCT_CASING = CasingBuilder.createCasingBlock("heavy_duty_casing",
            GTCA.id("block/casing/SCT_casing"));
}
