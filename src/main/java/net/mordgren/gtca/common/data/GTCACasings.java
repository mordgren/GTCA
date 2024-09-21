package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTModels;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.mordgren.gtca.GTCA;

import java.util.function.Supplier;

import static net.mordgren.gtca.GTCARegistration.REGISTRATE;


public class GTCACasings {
    public static final BlockEntry<Block> CASING_AEBF = createCasingBlock("casing_aebf",
            GTCA.id("block/casing/casing_aebf"));

    public static final BlockEntry<Block> CASING_GREENHOUSE = createCasingBlock("casing_greenhouse",
            GTCA.id("block/casing/casing_greenhouse"));

    public static final BlockEntry<Block> DURAL_CASING = createCasingBlock("dural_casing",
            GTCA.id("block/casing/dural_casing"));

    public static final BlockEntry<Block> VITALLIUM_CASING = createCasingBlock("vitallium_casing",
            GTCA.id("block/casing/vitallium_casing"));

    public static final BlockEntry<Block> NIMONIC80A_CASING = createCasingBlock("nimonic80a_casing",
            GTCA.id("block/casing/nimonic80a_casing"));

    public static final BlockEntry<Block> SHD_CASING = createCasingBlock("shd_casing",
            GTCA.id("block/casing/shd_casing"));

    public static final BlockEntry<Block> INCONEL718_CASING = createCasingBlock("inconel718_casing",
            GTCA.id("block/casing/inconel718_casing"));

    public static final BlockEntry<Block> PRW_Casing = createCasingBlock("pressure_resistant_wall",
            GTCA.id("block/casing/pressure_resistant_wall"));

    public static final BlockEntry<Block> SHD_Gearbox = createCasingBlock("shd_gearbox",
            GTCA.id("block/casing/machine_casing_gearbox_shd"));

    public static final BlockEntry<Block> TANTALLOY61_CASING = createCasingBlock("tantalloy61_casing",
            GTCA.id("block/casing/tantalloy61_casing"));

    public static final BlockEntry<Block> COMET_CASING = createCasingBlock("comet_casing",
            GTCA.id("block/casing/comet_casing"));

    public static final BlockEntry<Block> CYCLOTRON_COIL = createCasingBlock("cyclotron_coil",
            GTCA.id("block/casing/cyclotron_coil"));

    public static final BlockEntry<Block> ISAMILL_CASING = createCasingBlock("isa_mill_casing",
            GTCA.id("block/casing/isa_mill_casing"));

    public static final BlockEntry<Block> ISAMILL_AIR_INTAKE = createCasingBlock("isa_mill_airintake",
            GTCA.id("block/casing/isa_mill_air_intake_2"));

    public static final BlockEntry<Block> ISAMILL_GEARBOX = createCasingBlock("isa_mill_gearbox",
            GTCA.id("block/casing/isa_mill_gearbox"));

    public static final BlockEntry<Block> FLCR_CASING_TYPE_I = createCasingBlock("flcr_casing_type_i",
            GTCA.id("block/casing/rc_casing_type1"));

    // Glass


    public static final BlockEntry<Block> BORSILICATE_YTTRIUM_GLASS = createGlassCasingBlock("borsilicate_yttrium_thorium_glass",
            GTCA.id("block/casing/transparent/thorium_yttrium_glass_block"));

    public static final BlockEntry<Block> REINFORCED_GLASS = createGlassCasingBlock("reinforced_glass",
            GTCA.id("block/casing/transparent/reinforced_glass"));

    public static BlockEntry<Block> createGlassCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, GlassBlock::new, texture, () -> Blocks.GLASS,
                () -> RenderType::translucent);
    }

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }


    @SuppressWarnings("removal")
    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate(GTModels.cubeAllModel(name, texture))
                .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static void init(){}
}
