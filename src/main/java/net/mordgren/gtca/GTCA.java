package net.mordgren.gtca;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mordgren.gtca.common.data.GTCAMachines;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import org.slf4j.Logger;
import net.minecraft.resources.ResourceLocation;

import java.security.Provider;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GTCA.MOD_ID)
public class GTCA {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "gtca";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static GTRegistrate GTCA_REGISTRATE = GTRegistrate.create(GTCA.MOD_ID);


    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public GTCA() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
//        modEventBus.addListener(this::addCreative);


//        GTCA.init();
//        GTCA.init();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::addMaterialRegistries);
        modEventBus.addListener(this::addMaterials);
        modEventBus.addListener(this::modifyMaterials);
        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);

        // Most other events are fired on Forge's bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    private void addMaterials(MaterialEvent event) {
        GTCAMaterials.init();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }

    // You MUST have this for custom materials.
    // Remember to register them not to GT's namespace, but your own.
    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(GTCA.MOD_ID);
    }


    // This is optional, though.
    private void modifyMaterials(PostMaterialEvent event) {
        //CustomMaterials.modify();
    }

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        GTCARecipeTypes.init();
    }

    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        GTCAMachines.init();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
