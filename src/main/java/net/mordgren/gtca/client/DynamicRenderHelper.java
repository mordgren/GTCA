package net.mordgren.gtca.client;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;

public class DynamicRenderHelper {

    public static DynamicRender<?, ?> getSpaceElevatorRenderer() {
        return SpaceElevatorRenderer.INSTANCE;
    }

}
