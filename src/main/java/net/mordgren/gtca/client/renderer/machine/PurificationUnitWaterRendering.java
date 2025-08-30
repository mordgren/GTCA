package net.mordgren.gtca.client.renderer.machine;

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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.model.data.ModelData;
import net.mordgren.gtca.GTCA;
import org.joml.Quaternionf;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;


@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PurificationUnitWaterRendering extends DynamicRender<WorkableElectricMultiblockMachine, PurificationUnitWaterRendering> {

    public static final PurificationUnitWaterRendering INSTANCE = new PurificationUnitWaterRendering();
    public static final Codec<PurificationUnitWaterRendering> CODEC = Codec.unit(PurificationUnitWaterRendering.INSTANCE);
    public static final DynamicRenderType<WorkableElectricMultiblockMachine, PurificationUnitWaterRendering> TYPE = new DynamicRenderType<>(PurificationUnitWaterRendering.CODEC);

    public static final ResourceLocation WATER_MODEL = GTCA.id("obj/water_overlay");

    static final RandomSource random = RandomSource.create(0L);
    private static BakedModel waterModel = null;

    private PurificationUnitWaterRendering() {
        ModelUtils.registerBakeEventListener(true, event -> {
            waterModel = event.getModels().get(WATER_MODEL);
        });
    }

    @Override
    public DynamicRenderType<WorkableElectricMultiblockMachine, PurificationUnitWaterRendering> getType() {
        return TYPE;
    }

    @Override
    public void render(WorkableElectricMultiblockMachine machine, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (!machine.isFormed()) {
            return;
        }
        float totalTick = (Minecraft.getInstance().level.getGameTime() + partialTick);
        VertexConsumer consumer = buffer.getBuffer(Sheets.translucentCullBlockSheet());
        poseStack.pushPose();
        renderWater(poseStack, consumer, totalTick, packedLight, packedOverlay);
        poseStack.popPose();
    }

    public void renderWater(PoseStack poseStack, VertexConsumer consumer,
                           float totalTick, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5f, 1f, 2.5f);
        Quaternionf rot = new Quaternionf()
                .rotateXYZ(0f, 0f, 0f)
                .rotateAxis(0, 0.1f, 1f, 1f);
        poseStack.mulPose(rot);
        poseStack.scale(1f, 1f, 1f);
        PoseStack.Pose pose = poseStack.last();
        List<BakedQuad> quads = waterModel.getQuads(null, null, random, ModelData.EMPTY, null);
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

