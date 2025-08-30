package net.mordgren.gtca.client.renderer.machine;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;

public class GTCADynamicRenderHelpers {

         public static DynamicRender<?, ?> getSpaceElevatorRenderer() {
             return SpaceElevatorRenderer.INSTANCE;
         }
         public static DynamicRender<?, ?> getPurificationWaterRenderer() {
             return PurificationUnitWaterRendering.INSTANCE;
         }
}














