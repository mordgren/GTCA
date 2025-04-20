package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.ActiveBlock;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.machine.multiblock.IBatteryData;
import com.gregtechceu.gtceu.common.block.BatteryBlock;
import com.gregtechceu.gtceu.common.data.GTModels;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
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
import net.mordgren.gtca.GTCARegistration;
import net.mordgren.gtca.common.util.GTCACreativeModTab;
import net.mordgren.gtca.common.util.battery.GTCABatteryBlock;

import java.util.function.Supplier;


import static net.mordgren.gtca.GTCARegistration.REGISTRATE;


public class GTCABlocks {
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
            GTCA.id("block/casing/flcr_1"));

    public static final BlockEntry<Block> FLCR_CASING_TYPE_II = createCasingBlock("flcr_casing_type_ii",
            GTCA.id("block/casing/flcr_2"));

    public static BlockEntry<Block> ULTRA_INDUCTIVE_CASING = createCasingBlock("u_i_casing",
            GTCA.id("block/casing/u_i_casing"));

    public static BlockEntry<ActiveBlock> STABILIZED_TRANSMUTATION_CORE= createActiveCasing("stabilized_transmutation_core",
            "block/variant/stabilized_transmutation_core");

    public static BlockEntry<Block> P_N_PROTECTIVE_CASING = createCasingBlock("p_n_casing",
            GTCA.id("block/casing/p_n_casing"));

    public static BlockEntry<Block> BASIC_PHOTOLITHOGRAPHIC_FRAMEWORK_CASING = createCasingBlock("bpf_casing",
            GTCA.id("block/casing/bpf_casing"));

    public static BlockEntry<Block> REINFORCED_PHOTOLITHOGRAPHIC_FRAMEWORK_CASING = createCasingBlock("reinforced_rpp_casing",
            GTCA.id("block/casing/reinforced_rpp_casing"));

    public static BlockEntry<Block> RADIANT_PROOF_PHOTOLITHOGRAPHIC_FRAMEWORK_CASING = createCasingBlock("radiant_proof_reinforced_casing",
            GTCA.id("block/casing/radiant_proof_reinforced_casing"));

    public static BlockEntry<Block> RADIANT_NAQUADAH_ALLOY_AIR_INTAKE_CASING = createCasingBlock("air_intake_rnac",
            GTCA.id("block/casing/air_intake_rnac"));

    public static BlockEntry<Block> RADIANT_NAQUADAH_ALLOY_CASING = createCasingBlock("rnac_casing",
            GTCA.id("block/casing/rnac_casing"));


    //

    public static BlockEntry<Block> DRIST = createCasingBlock("dristnya",
            GTCA.id("block/casing/dristnya"));
    //

    // Glass

    public static final BlockEntry<Block> BORSILICATE_YTTRIUM_GLASS = createGlassCasingBlock("borsilicate_yttrium_thorium_glass",
            GTCA.id("block/casing/transparent/thorium_yttrium_glass_block"));

    public static final BlockEntry<Block> REINFORCED_GLASS = createGlassCasingBlock("reinforced_glass",
            GTCA.id("block/casing/transparent/reinforced_glass"));

    public static final BlockEntry<Block> BORSILICATE_REINFORCED_IRIDIUM_GLASS = createGlassCasingBlock("borsilicate_reinforced_iridium_glass",
            GTCA.id("block/casing/transparent/borsilicate_reinforced_iridium_glass"));

    // Battery

    public static final BlockEntry<BatteryBlock> BATTERY_PROTON_CELL = createBatteryBlock(
            GTCABatteryBlock.BatteryPartType.PROTON_CELL);

    public static final BlockEntry<BatteryBlock> BATTERY_ELECTRON_CELL = createBatteryBlock(
            GTCABatteryBlock.BatteryPartType.ELECTRON_CELL);

    public static final BlockEntry<BatteryBlock> BATTERY_QUARK_CELL = createBatteryBlock(
            GTCABatteryBlock.BatteryPartType.QUARK_ENTANGLEMENT);

    public static final BlockEntry<BatteryBlock> BATTERY_GRAVITON_CELL = createBatteryBlock(
            GTCABatteryBlock.BatteryPartType.GRAVITON_ANOMALY);

    // Sided casing

    public static BlockEntry<Block> P_N_E_CAPACITOR = createSidedCasingBlock(
            "p_n_e_capacitor",
            GTCA.id("block/sided_casing/p_n_e_capacitor_side"),
            GTCA.id("block/sided_casing/p_n_e_capacitor_top")
    );

    public static BlockEntry<Block> P_N_E_LASER_ACTIVATOR = createSidedCasingBlock(
            "p_n_e_laser_activator",
            GTCA.id("block/sided_casing/p_n_e_laser_activator_side"),
            GTCA.id("block/sided_casing/p_n_e_laser_activator_top")
    );

    @SuppressWarnings("removal")
    private static BlockEntry<BatteryBlock> createBatteryBlock(IBatteryData batteryData) {
        BlockEntry<BatteryBlock> batteryBlock = REGISTRATE.block("%s_battery".formatted(batteryData.getBatteryName()),
                        p -> new BatteryBlock(p, batteryData))
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .properties(p -> p.isValidSpawn((state, level, pos, entityType) -> false))
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate(
                        GTCABlocks.createBatteryBlockModel("%s_battery".formatted(batteryData.getBatteryName()),batteryData)
                        )
                .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
//                .onRegister(compassNodeExist(GTCompassSections.BLOCKS, "pss_battery"))
                .build()
                .register();
        GTCEuAPI.PSS_BATTERIES.put(batteryData, batteryBlock);
        return batteryBlock;
    }

    private static NonNullBiConsumer<DataGenContext<Block, BatteryBlock>, RegistrateBlockstateProvider> createBatteryBlockModel(String name, IBatteryData batteryData) {
        return (ctx, prov) -> {
            prov.simpleBlock((Block)ctx.getEntry(), prov.models().cubeBottomTop(name,
                    GTCA.id("block/casing/battery/" + batteryData.getBatteryName() + "/side"),
                    GTCA.id("block/casing/battery/" + batteryData.getBatteryName() + "/top"),
                    GTCA.id("block/casing/battery/" + batteryData.getBatteryName() + "/top")));
        };
    }

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


    public static BlockEntry<Block> createSidedCasingBlock(String name, ResourceLocation sideTexture, ResourceLocation topTexture) {
        return createSidedCasingBlock(name, Block::new, sideTexture, topTexture,
                () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    @SuppressWarnings("removal")
    public static BlockEntry<Block> createSidedCasingBlock(String name,
                                                           NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                           ResourceLocation sideTexture,
                                                           ResourceLocation topTexture,
                                                           NonNullSupplier<? extends Block> properties,
                                                           Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(), prov.models()
                        .cubeBottomTop(name, sideTexture, topTexture, topTexture)))
                .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    @SuppressWarnings("removal")
    public static BlockEntry<ActiveBlock> createActiveCasing(String name, String baseModelPath) {
        return REGISTRATE.block(name, ActiveBlock::new)
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate(GTModels.createActiveModel(GTCA.id(baseModelPath)))
                .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .model((ctx, prov) -> prov.withExistingParent(prov.name(ctx), GTCA.id(baseModelPath)))
                .build()
                .register();
    }

    public static void init(){
        GTCARegistration.REGISTRATE.creativeModeTab(() -> GTCACreativeModTab.MAIN);
    }
}
