package net.mordgren.gtca.common.data.machines;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.config.ConfigHolder;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCABlocks;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.PCBFactoryMachine;
// import net.mordgren.gtca.common.machine.multiblock.electric.SpacePumpMachine;
import net.mordgren.gtca.common.machine.multiblock.generator.ChemicalGeneratorMachine;
import net.mordgren.gtca.common.registry.GTCARegistration;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.GTValues.V;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_GRATE;
import static com.gregtechceu.gtceu.common.data.GTBlocks.PLASTCRETE;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.defaultEnvironmentRequirement;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.workableTiered;
import static net.mordgren.gtca.common.data.GTCAMaterials.Incoloy903;
import static net.mordgren.gtca.common.registry.GTCARegistration.REGISTRATE;

public class GTCAMachineUtils {

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff,
                                                             String lang,
                                                             int... tiers) {
        return registerTieredMachines(name,
                (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> {
                    if (hasPollutionDebuff) {
                        builder.recipeModifiers(GTRecipeModifiers.ENVIRONMENT_REQUIREMENT
                                                .apply(GTMedicalConditions.CARBON_MONOXIDE_POISONING, 100 * tier),
                                        GTRecipeModifiers.OC_NON_PERFECT)
                                .conditionalTooltip(defaultEnvironmentRequirement(),
                                        ConfigHolder.INSTANCE.gameplay.environmentalHazards);
                    } else {
                        builder.recipeModifier(GTRecipeModifiers.OC_NON_PERFECT);
                    }
                    return builder
                            .langValue("%s %s %s".formatted(VLVH[tier], lang, VLVT[tier]))
                            .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCA.id(name), recipeType))
                            .rotationState(RotationState.NON_Y_AXIS)
                            .recipeType(recipeType)
                            .workableTieredHullModel(GTCA.id("block/machines/" + name))
                            .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                    tankScalingFunction.apply(tier), true))
                            .register();
                },
                tiers);
    }

    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition, ?>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = GTCARegistration.REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static MultiblockMachineDefinition registerChemicalGenerator(String name, int tier,
                                                                        Supplier<? extends Block> casing,
                                                                        Supplier<? extends Block> gear,
                                                                        Supplier<? extends Block> firebox,
                                                                        Supplier<? extends Block> pipe,
                                                                        ResourceLocation casingTexture,
                                                                        ResourceLocation overlayModel,
                                                                        String lang) {
        return REGISTRATE.multiblock(name, holder -> new ChemicalGeneratorMachine(holder, tier))
                .rotationState(RotationState.NON_Y_AXIS)
                .langValue(lang)
                .recipeType(GTCARecipeTypes.CHEMICAL_GENERATOR)
                .generator(true)
                .recipeModifier(ChemicalGeneratorMachine::recipeModifier, true)
                .appearanceBlock(casing)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("III", "PPP", "III")
                        .aisle("III", "P#P", "III")
                        .aisle("III", "PPP", "III")
                        .aisle("III", "CSC", "III")
                        .aisle("III", "FGF", "III")
                        .aisle("IMI", "IDI", "III")
                        .where('M', controller(blocks(definition.getBlock())))
                        .where('P', blocks(GTBlocks.CASING_PTFE_INERT.get()))
                        .where('#', blocks(GTBlocks.CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                        .where('C', blocks(GTBlocks.COIL_CUPRONICKEL.get()))
                        .where('S', blocks(pipe.get()))
                        .where('F', blocks(firebox.get()))
                        .where('G', blocks(gear.get()))
                        .where('I', blocks(casing.get()).setMinGlobalLimited(30)
                                .or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true))
                                .or(autoAbilities(true, true, false)))
                        .where('D',
                                ability(PartAbility.OUTPUT_ENERGY,
                                        Stream.of(ULV, LV, MV, HV, EV, IV, LuV, ZPM, UV, UHV).filter(t -> t >= tier)
                                                .mapToInt(Integer::intValue).toArray())
                                        .addTooltips(Component.translatable("gtceu.multiblock.pattern.error.limited.1",
                                                GTValues.VN[tier])))
                        .build())
                .recoveryItems(
                        () -> new ItemLike[]{
                                GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
                .workableCasingModel(casingTexture, overlayModel)
                .tooltips(
                        Component.translatable("gtceu.universal.tooltip.base_production_eut", V[tier]),
                        tier > EV ?
                                Component.translatable("gtceu.machine.large_combustion_engine.tooltip.boost_extreme",
                                        V[tier] * 4) :
                                Component.translatable("gtceu.machine.large_combustion_engine.tooltip.boost_regular",
                                        V[tier] * 3))
                .register();
    }

    public static MultiblockMachineDefinition registerPcbFactory(String name, int tier,
                                                                 Supplier<? extends Block> casing,
                                                                 Supplier<? extends Block> casing2,
                                                                 Material frame,
                                                                 Material frame2,
                                                                 ResourceLocation casingTexture,
                                                                 ResourceLocation overlayModel,
                                                                 String lang) {
        if (tier == UV) {
            return REGISTRATE.multiblock(name, holder -> new PCBFactoryMachine(holder, tier))
                    .langValue(lang)
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTCARecipeTypes.PCB_FACTORY)
                    .appearanceBlock(casing)
                    .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                    .pattern(definition ->
                            FactoryBlockPattern.start()
                                    .aisle("FOOOOOF", "FOOOOOF", "FCCCCCF", "FCCCCCF", "FAAAAAF", "AAAAAAA")
                                    .aisle("CPPPPPC", "C#####C", "C#####C", "C#####C", "CCCCCCC", "FAAAAAF")
                                    .aisle("CPPPPPC", "L#III#L", "L#####L", "C#####C", "CCCCCCC", "FAAAAAF")
                                    .aisle("CPPPPPC", "L#III#L", "L#####L", "C#####C", "CCCCCCC", "FFFFFFF")
                                    .aisle("CPPPPPC", "L#III#L", "L#####L", "C#####C", "CGGGGGC", "FAAAAAF")
                                    .aisle("CPPPPPC", "C#####C", "C#####C", "C#####C", "CGGGGGC", "FAAAAAF")
                                    .aisle("FCCXCCF", "FGGGGGF", "FGGGGGF", "FGGGGGF", "FFFFFFF", "AAAAAAA")
                                    .where('#', Predicates.air())
                                    .where('A', Predicates.any())
                                    .where("X", Predicates.controller(Predicates.blocks(definition.get())))
                                    .where('F', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, frame)))
                                    .where('I', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, Incoloy903)))
                                    .where('P', blocks(PLASTCRETE.get()))
                                    .where('L', blocks(CASING_GRATE.get()))
                                    .where('G', blocks(GTCABlocks.REINFORCED_GLASS.get()))
                                    .where("C", blocks(casing.get()))
                                    .where("O", blocks(casing.get())
                                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                            .or(autoAbilities(true, false, false))
                                    )
                                    .build()
                    )
                    .tooltips(
                            Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Circuit Factory Board"),
                            Component.translatable("gtca.machine.pcb1_desc.tooltip")
                    )
                    .workableCasingModel(
                            casingTexture,
                            overlayModel
                    )
                    .register();
        }
        if (tier == UHV) {
            return REGISTRATE.multiblock(name, holder -> new PCBFactoryMachine(holder, tier))
                    .langValue(lang)
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTCARecipeTypes.PCB_FACTORY)
                    .appearanceBlock(casing)
                    .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                    .pattern(definition ->
                            FactoryBlockPattern.start()
                                    .aisle("HDDHAAAAAAA", "HDDHAAAAAAA", "HDDHAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAA")
                                    .aisle("DDDDAAAAAAA", "D##DAAAAAAA", "D##DAAAAAAA", "HDDHAAAAAAA", "HDDHAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAA")
                                    .aisle("DDDDFOOOOOF", "D##DFOOOOOF", "D##DFCCCCCF", "D##DFCCCCCF", "DDDDFAAAAAF", "HDDHAAAAAAA", "AAAAAAAAAAA")
                                    .aisle("DDDDCPPPPPC", "D##DC#####C", "D##DC#####C", "D##DC#####C", "D##DCCCCCCC", "DDDDFAAAAAF", "HDDHAAAAAAA")
                                    .aisle("DDDDCPPPPPC", "D##DL#III#L", "D##DL#####L", "D##DC#####C", "D##DCCCCCCC", "DDDDFAAAAAF", "HDDHAAAAAAA")
                                    .aisle("DDDDCPPPPPC", "D##DL#III#L", "D##DL#####L", "D##DC#####C", "D##DCCCCCCC", "DDDDFFFFFFF", "HDDHAAAAAAA")
                                    .aisle("DDDDCPPPPPC", "D##DL#III#L", "D##DL#####L", "D##DC#####C", "D##DCGGGGGC", "DDDDFAAAAAF", "HDDHAAAAAAA")
                                    .aisle("DDDDCPPPPPC", "D##DC#####C", "D##DC#####C", "D##DC#####C", "D##DCGGGGGC", "DDDDFAAAAAF", "HDDHAAAAAAA")
                                    .aisle("DDDDFCCXCCF", "D##DFGGGGGF", "D##DFGGGGGF", "D##DFGGGGGF", "DDDDFFFFFFF", "HDDHAAAAAAA", "AAAAAAAAAAA")
                                    .aisle("DDDDAAAAAAA", "D##DAAAAAAA", "D##DAAAAAAA", "HDDHAAAAAAA", "HDDHAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAA")
                                    .aisle("HDDHAAAAAAA", "HDDHAAAAAAA", "HDDHAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAA", "AAAAAAAAAAA")
                                    .where('#', Predicates.air())
                                    .where('A', Predicates.any())
                                    .where("X", Predicates.controller(Predicates.blocks(definition.get())))
                                    .where('F', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, frame)))
                                    .where('H', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, frame2)))
                                    .where('I', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, Incoloy903)))
                                    .where('P', blocks(PLASTCRETE.get()))
                                    .where('L', blocks(CASING_GRATE.get()))
                                    .where('G', blocks(GTCABlocks.REINFORCED_GLASS.get()))
                                    .where("C", blocks(casing.get()))
                                    .where("D", blocks(casing2.get()))
                                    .where("O", blocks(casing.get())
                                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                            .or(autoAbilities(true, false, false))
                                    )
                                    .build()
                    )
                    .tooltips(
                            Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Circuit Factory Board"),
                            Component.translatable("gtca.machine.pcb1_desc.tooltip")
                    )
                    .workableCasingModel(
                            casingTexture,
                            overlayModel
                    )
                    .register();

        }
        if (tier == UEV) {
            return REGISTRATE.multiblock(name, holder -> new PCBFactoryMachine(holder, tier))
                    .langValue(lang)
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTCARecipeTypes.PCB_FACTORY)
                    .appearanceBlock(casing)
                    .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                    .pattern(definition ->
                            FactoryBlockPattern.start()
                                    .aisle("#CCCCC#", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                                    .aisle("CCOOOCC", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                                    .aisle("COOOOOC", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "##DDD##", "##DDD##", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                                    .aisle("COOOOOC", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "##D#D##", "#C###C#", "##D#D##", "##D#D##", "##DCD##", "##DCD##", "###C###", "###C###", "###C###", "###C###", "###C###", "#######")
                                    .aisle("COOOOOC", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "##D#D##", "#C###C#", "##D#D##", "##D#D##", "##D#D##", "##D#D##", "##D#D##", "##D#D##", "##D#D##", "##D#D##", "###C###", "###C###")
                                    .aisle("COOOOOC", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "##D#D##", "##D#D##", "##D#D##", "#C###C#", "##D#D##", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "##D#D##", "##D#D##", "###C###", "###C###")
                                    .aisle("COOOOOC", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "##D#D##", "##D#D##", "##D#D##", "#C###C#", "##D#D##", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "##D#D##", "##DCD##", "###C###", "#######")
                                    .aisle("COOOOOC", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "#C###C#", "##DDD##", "##DDD##", "##DDD##", "#C###C#", "#C###C#", "#C###C#", "#C#D#C#", "#C#D#C#", "#C#D#C#", "#C###C#", "#C###C#", "#C###C#", "##DDD##", "##DDD##", "#######", "#######")
                                    .aisle("CCOOOCC", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "#######", "#######", "#######", "##CCC##", "##CCC##", "##CCC##", "##C#C##", "##C#C##", "##C#C##", "##CCC##", "##CCC##", "##CCC##", "#######", "#######", "#######", "#######")
                                    .aisle("#CCXCC#", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                                    .where('#', Predicates.any())
                                    .where("X", Predicates.controller(Predicates.blocks(definition.get())))
                                    .where("C", blocks(casing.get()))
                                    .where("D", blocks(casing2.get()))
                                    .where("O", blocks(casing.get())
                                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                            .or(autoAbilities(true, false, false))
                                    )
                                    .build()
                    )
                    .tooltips(
                            Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Circuit Factory Board"),
                            Component.translatable("gtca.machine.pcb1_desc.tooltip")
                    )
                    .workableCasingModel(
                            casingTexture,
                            overlayModel
                    )
                    .register();
        } else {
            return null;
        }
    }

//    public static MultiblockMachineDefinition registerSpaceModule(String name, int tier,
//                                                                ResourceLocation casingTexture,
//                                                                ResourceLocation overlayModel,
//                                                                String lang,
//                                                                GTRecipeType recipeType,
//                                                                BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
//                                                                @Nullable Component... tooltips  ) {
//
//        if (tier == LuV) {
//            return REGISTRATE.multiblock(name, holder -> (MultiblockControllerMachine) factory.apply(holder, tier))
//                    .langValue(lang)
//                    .rotationState(RotationState.NON_Y_AXIS)
//                    .recipeType(recipeType)
//                    .appearanceBlock(GTCABlocks.SPACE_ELEVATOR_CASING)
//                    .recipeModifiers(true, GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK), SpacePumpMachine::recipeModifier)
//                    .pattern(definition ->
//                            FactoryBlockPattern.start()
//                                    .aisle("C", "C", "C", "C", "C")
//                                    .aisle("C", "C", "C", "X", "C")
//                                    .where('X', Predicates.controller(Predicates.blocks(definition.get())))
//                                    .where('C', blocks(GTCABlocks.SPACE_ELEVATOR_CASING.get()).or(autoAbilities(definition.getRecipeTypes())).or(Predicates.autoAbilities(false, false, false)))
//                                    .build()
//                    )
//                    .tooltips(tooltips)
//                    .workableCasingModel(
//                            casingTexture,
//                            overlayModel
//                    )
//                    .register();
//        }
//        if (tier == ZPM) {
//            return REGISTRATE.multiblock(name, holder -> new SpacePumpMachine(holder, tier))
//                    .langValue(lang)
//                    .rotationState(RotationState.NON_Y_AXIS)
//                    .recipeType(GTCARecipeTypes.SPACE_PUMP)
//                    .appearanceBlock(GTCABlocks.SPACE_ELEVATOR_CASING)
//                    .recipeModifiers(true, GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK), SpacePumpMachine::recipeModifier)
//                    .pattern(definition ->
//                            FactoryBlockPattern.start()
//                                    .aisle("C", "C", "C", "C", "C")
//                                    .aisle("C", "C", "C", "X", "C")
//                                    .where('X', Predicates.controller(Predicates.blocks(definition.get())))
//                                    .where('C', blocks(GTCABlocks.SPACE_ELEVATOR_CASING.get()).or(autoAbilities(definition.getRecipeTypes())).or(Predicates.autoAbilities(false, false, false)))
//                                    .build()
//                    )
//                    .tooltips(tooltips)
//                    .workableCasingModel(
//                            casingTexture,
//                            overlayModel
//                    )
//                    .register();
//        }
//        if (tier == UV) {
//            return REGISTRATE.multiblock(name, holder -> new SpacePumpMachine(holder, tier))
//                    .langValue(lang)
//                    .rotationState(RotationState.NON_Y_AXIS)
//                    .recipeType(GTCARecipeTypes.SPACE_PUMP)
//                    .appearanceBlock(GTCABlocks.SPACE_ELEVATOR_CASING)
//                    .recipeModifiers(true, GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK), SpacePumpMachine::recipeModifier)
//                    .pattern(definition ->
//                            FactoryBlockPattern.start()
//                                    .aisle("C", "C", "C", "C", "C")
//                                    .aisle("C", "C", "C", "X", "C")
//                                    .where('X', Predicates.controller(Predicates.blocks(definition.get())))
//                                    .where('C', blocks(GTCABlocks.SPACE_ELEVATOR_CASING.get()).or(autoAbilities(definition.getRecipeTypes())).or(Predicates.autoAbilities(false, false, false)))
//                                    .build()
//                    )
//                    .tooltips(tooltips)
//                    .workableCasingModel(
//                            casingTexture,
//                            overlayModel
//                    )
//                    .register();
//
//        } else {
//            return null;
//        }
//
//    }

    public static void init() {}
}
