package net.mordgren.gtca.client.renderer.machine;

import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.client.util.ModelUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.model.data.ModelData;

import net.mordgren.gtca.GTCA;

import org.joml.Quaternionf;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SpaceElevatorRenderer extends DynamicRender<WorkableElectricMultiblockMachine, SpaceElevatorRenderer> {

    public static final SpaceElevatorRenderer INSTANCE = new SpaceElevatorRenderer();
    public static final Codec<SpaceElevatorRenderer> CODEC = Codec.unit(SpaceElevatorRenderer.INSTANCE);
    public static final DynamicRenderType<WorkableElectricMultiblockMachine, SpaceElevatorRenderer> TYPE =
            new DynamicRenderType<>(SpaceElevatorRenderer.CODEC);

    public static final ResourceLocation BEAM_MODEL = GTCA.id("obj/se_beam");
    public static final ResourceLocation SHUTTLE_MODEL = GTCA.id("obj/shuttle");

    private static final RandomSource random = RandomSource.create(0L);
    private static BakedModel beamModel = null;
    private static BakedModel shuttleModel = null;

    private SpaceElevatorRenderer() {
        ModelUtils.registerBakeEventListener(true, event -> {
            beamModel = event.getModels().get(BEAM_MODEL);
            shuttleModel = event.getModels().get(SHUTTLE_MODEL);
        });
    }

    @Override
    public DynamicRenderType<WorkableElectricMultiblockMachine, SpaceElevatorRenderer> getType() {
        return TYPE;
    }

    @Override
    public void render(WorkableElectricMultiblockMachine machine, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (!machine.isFormed()) return;
        if (beamModel == null || shuttleModel == null) return;

        Level level = Minecraft.getInstance().level;
        if (level == null) return;

        float totalTick = (level.getGameTime() + partialTick);
        VertexConsumer consumer = buffer.getBuffer(Sheets.translucentCullBlockSheet());
        poseStack.pushPose();
        applyFacingTransform(machine, poseStack);
        renderBeam(poseStack, consumer, totalTick, packedLight, packedOverlay);
        renderShuttle(poseStack, consumer, totalTick, packedLight, packedOverlay);
        poseStack.popPose();
    }
    private void applyFacingTransform(WorkableElectricMultiblockMachine machine, PoseStack poseStack) {
        Direction facing = getMachineFacing(machine);


        float yawDeg = -facing.toYRot();

        poseStack.translate(0.5, 0.0, 0.5);
        poseStack.mulPose(new Quaternionf().rotateY(yawDeg * Mth.DEG_TO_RAD));
        poseStack.translate(-0.5, 0.0, -0.5);
    }

    private Direction getMachineFacing(WorkableElectricMultiblockMachine machine) {
        Level level = machine.getLevel();
        if (level == null) return Direction.SOUTH;

        BlockPos pos = machine.getPos();
        BlockState state = level.getBlockState(pos);


        RotationState rot = machine.getDefinition().getRotationState();
        if (rot != null && rot.property != null && state.hasProperty(rot.property)) {
            return state.getValue(rot.property);
        }


        if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            return state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        }

        return Direction.SOUTH;
    }

    public void renderBeam(PoseStack poseStack, VertexConsumer consumer,
                           float totalTick, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, -3.5f);
        poseStack.scale(1f, 16f, 1f);

        PoseStack.Pose pose = poseStack.last();
        List<BakedQuad> quads = beamModel.getQuads(null, null, random, ModelData.EMPTY, null);
        for (BakedQuad quad : quads) {
            consumer.putBulkData(pose, quad, 1f, 1f, 1f, 1f, packedLight, packedOverlay, false);
        }

        poseStack.popPose();
    }

    public void renderShuttle(PoseStack poseStack, VertexConsumer consumer,
                              float totalTick, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5f, 50f, -3.5f);
        Quaternionf rot = new Quaternionf()
                .rotateAxis(totalTick * Mth.TWO_PI / 1200f, 0f, 1f, 0f);
        poseStack.mulPose(rot);
        poseStack.scale(3.5f, 3.5f, 3.5f);

        PoseStack.Pose pose = poseStack.last();
        List<BakedQuad> quads = shuttleModel.getQuads(null, null, random, ModelData.EMPTY, null);
        for (BakedQuad quad : quads) {
            consumer.putBulkData(pose, quad, 1f, 1f, 1f, 1f, packedLight, packedOverlay, false);
        }

        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public boolean shouldRenderOffScreen(WorkableElectricMultiblockMachine machine) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(WorkableElectricMultiblockMachine machine) {
        return new AABB(machine.getPos()).inflate(getViewDistance(), 16, getViewDistance());
    }
}

































