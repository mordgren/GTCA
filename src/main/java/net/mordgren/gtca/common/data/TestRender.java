//package net.mordgren.gtca.common.data;
//
//import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
//import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
//import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import com.mojang.serialization.Codec;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.phys.AABB;
//import net.mordgren.gtca.GTCA;
//import net.mordgren.gtca.common.machine.multiblock.electric.SpaceElavatorMachine;
//import org.joml.Matrix4f;
//
//public class SpaceElevatorRenderer extends DynamicRender<SpaceElavatorMachine, SpaceElevatorRenderer> {
//
//    public static final SpaceElevatorRenderer INSTANCE = new SpaceElevatorRenderer();
//    public static final Codec<SpaceElevatorRenderer> CODEC = Codec.unit(SpaceElevatorRenderer.INSTANCE);
//    public static final DynamicRenderType<WorkableElectricMultiblockMachine, SpaceElevatorRenderer> TYPE = new DynamicRenderType<>(SpaceElevatorRenderer.CODEC);
//
//
//    private static final ResourceLocation BEAM_TEXTURE = new ResourceLocation(GTCA.MOD_ID, "casing/space_elevator_cable");
//
//    private SpaceElevatorRenderer() {}
//
//    @Override
//    public DynamicRenderType<WorkableElectricMultiblockMachine, SpaceElevatorRenderer> getType() {
//        return TYPE;
//    }
//
//    @Override
//    public void render(WorkableElectricMultiblockMachine machine, float partialTick, PoseStack poseStack,
//                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
//        if (!machine.isFormed()) {
//            return;
//        }
//
//        poseStack.pushPose();
//        poseStack.translate(4, 0, 4);
//
//        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(BEAM_TEXTURE));
//
//        renderCylinder(poseStack, consumer, packedLight, 256, 0.5f);
//
//        poseStack.popPose();
//    }
//
//    private void renderCylinder(PoseStack poseStack, VertexConsumer consumer, int light, int height, float radius) {
//        int segments = 16;
//        Matrix4f matrix = poseStack.last().pose();
//
//        for (int i = 0; i < segments; i++) {
//            double angle1 = 2 * Math.PI * i / segments;
//            double angle2 = 2 * Math.PI * (i + 1) / segments;
//
//            float x1 = (float) (Math.cos(angle1) * radius);
//            float z1 = (float) (Math.sin(angle1) * radius);
//            float x2 = (float) (Math.cos(angle2) * radius);
//            float z2 = (float) (Math.sin(angle2) * radius);
//
//            float y0 = 0;
//            float y1 = height;
//
//            float u0 = (float) i / segments;
//            float u1 = (float) (i + 1) / segments;
//
//
//            consumer.vertex(matrix, x1, y0, z1).color(255, 255, 255, 200).uv(u0, 0).uv2(light).endVertex();
//            consumer.vertex(matrix, x1, y1, z1).color(255, 255, 255, 200).uv(u0, 1).uv2(light).endVertex();
//            consumer.vertex(matrix, x2, y1, z2).color(255, 255, 255, 200).uv(u1, 1).uv2(light).endVertex();
//            consumer.vertex(matrix, x2, y0, z2).color(255, 255, 255, 200).uv(u1, 0).uv2(light).endVertex();
//        }
//    }
//
//    @Override
//    public int getViewDistance() {
//        return 512;
//    }
//
//    @Override
//    public boolean shouldRenderOffScreen(WorkableElectricMultiblockMachine machine) {
//        return true;
//    }
//
//    @Override
//    public AABB getRenderBoundingBox(WorkableElectricMultiblockMachine machine) {
//        return new AABB(machine.getPos()).inflate(getViewDistance());
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
