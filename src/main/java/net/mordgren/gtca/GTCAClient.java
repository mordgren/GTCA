package net.mordgren.gtca;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;
import net.mordgren.gtca.common.data.SpaceElevatorRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = GTCA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GTCAClient {

    private GTCAClient() {}
    public static void init(IEventBus modBus) {
        modBus.register(GTCAClient.class);
        DynamicRenderManager.register(GTCA.id("space_elevator"), SpaceElevatorRenderer.TYPE);
    }

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {

    }

    public static void init() {
    }
}