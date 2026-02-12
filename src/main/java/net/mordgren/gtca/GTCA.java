package net.mordgren.gtca;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.Platform;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.mordgren.gtca.client.GTCAClient;
import net.mordgren.gtca.common.data.*;
import net.mordgren.gtca.common.data.machines.GTCAMachineUtils;
import net.mordgren.gtca.common.data.machines.GTCAMachines;
import net.mordgren.gtca.common.data.materials.GTCAMaterialSet;
import net.mordgren.gtca.common.data.materials.GTMaterialAdjustments;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfoRecipeCapability;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningKeyRecipeCapability;
import net.mordgren.gtca.common.registry.GTCARegistration;
import net.mordgren.gtca.config.ConfigHandler;
import net.mordgren.gtca.data.GTCADataGen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.resources.ResourceLocation;

@Mod(GTCA.MOD_ID)
public class GTCA {
    public static final String MOD_ID = "gtca", NAME = "GTCommunityAdditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static MaterialRegistry MATERIAL_REGISTRY;
    public static boolean GTNNINT = ModList.get().isLoaded("gtnn");

    public GTCA() {
        GTCA.init();

        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        bus.addListener(this::onCommonSetup);
        bus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        bus.addGenericListener(RecipeConditionType.class, this::registerRecipeConditions);
        bus.addGenericListener(MachineDefinition.class, this::registerMachines);
        bus.addGenericListener(RecipeCapability.class, this::registerRecipeCapabilities);

        if (Platform.isClient()) {
            GTCAClient.init(bus);
        }
    }

    @SubscribeEvent
    public void registerMaterials(MaterialEvent event) {
        GTCAMaterials.init();
        GTMaterialAdjustments.init();
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpaceMiningKeyRecipeCapability.registerMapIngredientType();

            GTCAItems.initTierMappings();
        });
    }

    public static void init() {
        GTCAItems.init();
        GTCABlocks.init();
        ConfigHandler.init();
        GTCADataGen.init();
        GTCARegistration.REGISTRATE.registerRegistrate();
        GTCAMaterialSet.init();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, FormattingUtil.toLowerCaseUnder(path));
    }

    @SubscribeEvent
    public void registerMaterialRegistry(MaterialRegistryEvent event) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(GTCA.MOD_ID);
    }

    public void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        GTCARecipeTypes.init();
    }

    public void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        GTCAMachines.init();
        GTCAMachineUtils.init();
    }

    public void registerRecipeConditions(GTCEuAPI.RegisterEvent<String, RecipeConditionType<?>> event) {
        GTCARecipeConditions.init();
    }

    public void registerRecipeCapabilities(GTCEuAPI.RegisterEvent.String<RecipeCapability<?>> event) {
        event.register(SpaceMiningInfoRecipeCapability.CAP.name, SpaceMiningInfoRecipeCapability.CAP);
        event.register(SpaceMiningKeyRecipeCapability.CAP.name, SpaceMiningKeyRecipeCapability.CAP);
    }
}