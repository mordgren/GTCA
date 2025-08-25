package net.mordgren.gtca.client;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.client.renderer.machine.SpaceElevatorRenderer;

public class GTCAClient {

    private GTCAClient() {}

    public static void init(IEventBus modBus) {
        modBus.register(GTCAClient.class);

        DynamicRenderManager.register(GTCA.id("space_elevator"), SpaceElevatorRenderer.TYPE);
    }

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {;
        event.register(SpaceElevatorRenderer.BEAM_MODEL);
        event.register(SpaceElevatorRenderer.SHUTTLE_MODEL);
    }
}
