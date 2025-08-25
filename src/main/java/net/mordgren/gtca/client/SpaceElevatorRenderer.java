package net.mordgren.gtca.client;

import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.client.util.ModelUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.model.data.ModelData;
import net.mordgren.gtca.GTCA;
import org.joml.Quaternionf;

import java.util.List;

public class SpaceElevatorRenderer extends DynamicRender<WorkableElectricMultiblockMachine, SpaceElevatorRenderer> {

    public static final SpaceElevatorRenderer INSTANCE = new SpaceElevatorRenderer();
    public static final Codec<SpaceElevatorRenderer> CODEC = Codec.unit(SpaceElevatorRenderer.INSTANCE);
    public static final DynamicRenderType<WorkableElectricMultiblockMachine, SpaceElevatorRenderer> TYPE = new DynamicRenderType<>(SpaceElevatorRenderer.CODEC);


    public static final ResourceLocation BEAM_MODEL = GTCA.id("obj/se_beam");

    static final RandomSource random = RandomSource.create(0L);
    private static BakedModel beamModel = null ;


    private SpaceElevatorRenderer() {
        ModelUtils.registerBakeEventListener(true, event -> {
            beamModel = event.getModels().get(BEAM_MODEL);

        });
    }



    @Override
    public DynamicRenderType<WorkableElectricMultiblockMachine, SpaceElevatorRenderer> getType() {
        return TYPE;
    }

    @Override
    public void render(WorkableElectricMultiblockMachine machine, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (!machine.isFormed())
            return;

        float totalTick = (Minecraft.getInstance().level.getGameTime() + partialTick);
        VertexConsumer consumer = buffer.getBuffer(Sheets.translucentCullBlockSheet());
        poseStack.pushPose();
        poseStack.translate(4, 0, 4);
        renderBeam(poseStack, consumer, totalTick, packedLight, packedOverlay);
        poseStack.popPose();
    }

    private void renderBeam(PoseStack poseStack, VertexConsumer consumer,
                            float totalTick, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        Quaternionf rot = new Quaternionf()
                .rotateXYZ(0.0f, 0.0f, 0f)
                .rotateAxis(0, 0f, 1f, 1);
        poseStack.mulPose(rot);

        poseStack.scale(75.6f, 3f, 5f);

        PoseStack.Pose pose = poseStack.last();

        List<BakedQuad> quads = beamModel.getQuads(null, null, random, ModelData.EMPTY, null);
        for (BakedQuad quad : quads) {
            consumer.putBulkData(pose, quad, 1f, 1f, 1f, 0.65f, packedLight, packedOverlay, false);
        }
        poseStack.popPose();
    }

    private void renderModel(PoseStack poseStack, VertexConsumer consumer, BakedModel model, float r, float g, float b,
                             float a, int light, int overlay) {
        if (model == null) return;
        PoseStack.Pose pose = poseStack.last();
        List<BakedQuad> quads = model.getQuads(null, null, random, ModelData.EMPTY, null);
        for (BakedQuad quad : quads) {
            consumer.putBulkData(pose, quad, r, g, b, a, light, overlay, false);
        }
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
        return new AABB(machine.getPos()).inflate(getViewDistance());
    }
}

































