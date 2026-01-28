package net.mordgren.gtca.common.data.machines;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IRotorHolderMachine;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.client.renderer.machine.GTCADynamicRenderHelpers;
import net.mordgren.gtca.common.data.*;
import net.mordgren.gtca.common.machine.multiblock.electric.*;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.SpaceElevatorDisplay;
import net.mordgren.gtca.common.registry.GTCARegistration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.BATCH_MODE;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.OC_NON_PERFECT_SUBTICK;
import static net.mordgren.gtca.common.data.machines.GTCAMachineUtils.*;
import static net.mordgren.gtca.common.registry.GTCARegistration.REGISTRATE;
import static net.mordgren.gtca.common.data.GTCAMaterials.*;


public class GTCAMachines {
    public static void init() {
        GTCARegistration.REGISTRATE.creativeModeTab(() -> GTCACreativeModTab.MAIN);
    }


    public static final MachineDefinition[] MATTER_FABRICATOR = registerSimpleMachines("matter_fabricator", GTCARecipeTypes.UU_MATTER_FABRICATOR, defaultTankSizeFunction, false, "UU-Matter Fabricator", GTValues.tiersBetween(EV, UHV));
    public static final MachineDefinition[] MATTER_AMPLIFICATOR = registerSimpleMachines("matter_amplificator", GTCARecipeTypes.UU_MATTER_AMPLIFICATOR, defaultTankSizeFunction, false, "UU-Matter Amplificator", GTValues.tiersBetween(EV, UHV));
    public static final MachineDefinition[] RECYCLER = registerSimpleMachines("recycler", GTCARecipeTypes.RECYCLER, defaultTankSizeFunction, false, "Recycler", GTValues.tiersBetween(LV, UHV));

    

    /// STEAM PRESSURIZER ///
    public static final MultiblockMachineDefinition STEAM_PRESSURIZER = REGISTRATE.multiblock("steam_pressurizer", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Steam Pressurizer")
            .recipeType(GTCARecipeTypes.STEAM_PRESSURIZER)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("BAAAB", "BCCCB")
                            .aisle("BAEAB", "AGGGA")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("A", blocks(GTBlocks.CASING_STEEL_SOLID.get()))
                            .where("G", blocks(GTBlocks.CASING_TEMPERED_GLASS.get()))
                            .where("C", blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                            .where("B", blocks(GTBlocks.CASING_STEEL_SOLID.get()).setMinGlobalLimited(2).or(Predicates.autoAbilities(definition.getRecipeTypes())))
                            .build()
            )
            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/implosion_compressor")
            )
            .register();

    /// ADVANCED EBF ///


    public static final MultiblockMachineDefinition ADVANCED_EBF = REGISTRATE
            .multiblock("advanced_ebf", CoilWorkableElectricMultiblockMachine::new)
            .langValue("Volcanus")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.BLAST_RECIPES)
            .recipeModifiers(BATCH_MODE,
                    OC_NON_PERFECT_SUBTICK, GTCARecipeModifiers::aebfOverclock)
            .appearanceBlock(GTCABlocks.CASING_AEBF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXXXX", "FFXXXFF", "F#####F", "F#####F", "F#####F", "FFXXXFF", "XXXVXXX", "##XXX##", "#######")
                    .aisle("XXXXXXX", "FXCCCXF", "##CCC##", "##III##", "##CCC##", "FXCCCXF", "XXXXXXX", "#XXXXX#", "##XXX##")
                    .aisle("XXXXXXX", "XCC#CCX", "#CC#CC#", "#I###I#", "#CC#CC#", "XCC#CCX", "XXXXXXX", "XXXHXXX", "#X###X#")
                    .aisle("XXXXXXX", "XC###CX", "#C###C#", "#I###I#", "#C###C#", "XC###CX", "VXXXXXV", "XXHHHXX", "#X###X#")
                    .aisle("XXXXXXX", "XCC#CCX", "#CC#CC#", "#I###I#", "#CC#CC#", "XCC#CCX", "XXXXXXX", "XXXHXXX", "#X###X#")
                    .aisle("XXXXXXX", "FXCCCXF", "##CCC##", "##III##", "##CCC##", "FXCCCXF", "XXXXXXX", "#XXXXX#", "##XXX##")
                    .aisle("XXXSXXX", "FFXXXFF", "F#####F", "F#####F", "F#####F", "FFXXXFF", "XXXVXXX", "##XXX##", "#######")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('F', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, Tungsten)))
                    .where('V', blocks(GTBlocks.CASING_EXTREME_ENGINE_INTAKE.get()))
                    .where('I', blocks(GCYMBlocks.HEAT_VENT.get()))
                    .where('X', blocks(GTCABlocks.CASING_AEBF.get()).setMinGlobalLimited(155)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('H', abilities(PartAbility.MUFFLER))
                    .where('C', heatingCoils())
                    .where('#', any())
                    .build()
            )
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("XXXMXEX", "FFXXXFF", "F#####F", "F#####F", "F#####F", "FFXXXFF", "XXXVXXX", "##XXX##", "#######")
                        .aisle("XXXXXXX", "FXCCCXF", "##CCC##", "##III##", "##CCC##", "FXCCCXF", "XXXXXXX", "#XXXXX#", "##XXX##")
                        .aisle("PXXXXXG", "XCC#CCX", "#CC#CC#", "#I###I#", "#CC#CC#", "XCC#CCX", "XXXXXXX", "XXXHXXX", "#X###X#")
                        .aisle("OXXXXXD", "XC###CX", "#C###C#", "#I###I#", "#C###C#", "XC###CX", "VXXXXXV", "XXHHHXX", "#X###X#")
                        .aisle("XXXXXXX", "XCC#CCX", "#CC#CC#", "#I###I#", "#CC#CC#", "XCC#CCX", "XXXXXXX", "XXXHXXX", "#X###X#")
                        .aisle("XXXXXXX", "FXCCCXF", "##CCC##", "##III##", "##CCC##", "FXCCCXF", "XXXXXXX", "#XXXXX#", "##XXX##")
                        .aisle("XXXSXXX", "FFXXXFF", "F#####F", "F#####F", "F#####F", "FFXXXFF", "XXXVXXX", "##XXX##", "#######")
                        .where('X', GTCABlocks.CASING_AEBF.getDefaultState())
                        .where('S', definition, Direction.SOUTH)
                        .where('I', GCYMBlocks.HEAT_VENT)
                        .where('V', CASING_EXTREME_ENGINE_INTAKE)
                        .where('F', ChemicalHelper.getBlock(TagPrefix.frameGt, Tungsten))
                        .where('#', Blocks.AIR.defaultBlockState())
                        .where('E', ENERGY_INPUT_HATCH[GTValues.LV], Direction.NORTH)
                        .where('P', ITEM_IMPORT_BUS[GTValues.LV], Direction.WEST)
                        .where('O', GTMachines.ITEM_EXPORT_BUS[GTValues.LV], Direction.WEST)
                        .where('G', GTMachines.FLUID_IMPORT_HATCH[GTValues.LV], Direction.EAST)
                        .where('D', GTMachines.FLUID_EXPORT_HATCH[GTValues.LV], Direction.EAST)
                        .where('H', GTMachines.MUFFLER_HATCH[GTValues.LV], Direction.UP)
                        .where('M', GTMachines.MAINTENANCE_HATCH, Direction.SOUTH);
                GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .sorted(Comparator.comparingInt(entry -> entry.getKey().getTier()))
                        .forEach(
                                coil -> shapeInfo.add(builder.shallowCopy().where('C', coil.getValue().get()).build()));
                return shapeInfo;
            })
            .recoveryItems(
                    () -> new ItemLike[]{
                            GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
            .workableCasingModel(
                    GTCA.id("block/casing/casing_aebf"),
                    GTCA.id("block/multiblock/aebf")
            )
            .tooltips(
                    Component.translatable("gtceu.multiblock.parallelizable.tooltip"),
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Electric Blast Furnace"),
                    Component.translatable("gtca.machine.AEBF_desc.tooltip1")
            )
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                            Component
                                    .translatable(
                                            FormattingUtil
                                                    .formatNumbers(coilMachine.getCoilType().getCoilTemperature() +
                                                            100L * Math.max(0, coilMachine.getTier() - GTValues.MV)) +
                                                    "K")
                                    .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                }
            })
//            .compassSections(GTCompassSections.TIER[MV])
//            .compassNodeSelf()
            .register();

    /// CHEMICAL GENERATOR ///
    public static final MultiblockMachineDefinition IV_CHEMICAL_GENERATOR = registerChemicalGenerator(
            "iv_chemical_generator", IV,
            GTCABlocks.INCONEL718_CASING, CASING_TUNGSTENSTEEL_GEARBOX, FIREBOX_TUNGSTENSTEEL, CASING_TUNGSTENSTEEL_PIPE,
            GTCA.id("block/casing/inconel718_casing"),
            GTCA.id("block/multiblock/aebf"), "Chemical Combustion Engine");

    public static final MultiblockMachineDefinition EV_CHEMICAL_GENERATOR = registerChemicalGenerator(
            "ev_chemical_generator", EV,
            GTCABlocks.VITALLIUM_CASING, CASING_TITANIUM_GEARBOX, FIREBOX_TITANIUM, CASING_TITANIUM_PIPE,
            GTCA.id("block/casing/vitallium_casing"),
            GTCA.id("block/multiblock/aebf"), "Extreme Chemical Combustion Engine");

    public static final MultiblockMachineDefinition GREEN_HOUSE = REGISTRATE
            .multiblock("green_house", WorkableElectricMultiblockMachine::new)
            .langValue("Green House")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.GREEN_HOUSE)
            .appearanceBlock(GTCABlocks.CASING_GREENHOUSE)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "FAAAF", "FAAAF", "FBBBF", "FBBBF", "FBBBF", "FFFFF")
                    .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "FBBBF")
                    .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "FBBBF")
                    .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "FBBBF")
                    .aisle("AADAA", "FAAAF", "FAAAF", "FBBBF", "FBBBF", "FBBBF", "FFFFF")
                    .where('A', blocks(GTCABlocks.CASING_GREENHOUSE.get()).setMinGlobalLimited(42)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where('D', controller(blocks(definition.getBlock())))
                    .where('#', air())
                    .where('F', any())
                    .where('C', blocks(Blocks.DIRT))
                    .where('B', blocks(CASING_TEMPERED_GLASS.get()))
                    .build()
            )
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("AAAAA", "#AAA#", "#AAA#", "#BBB#", "#BBB#", "#BBB#", "#####")
                        .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "#BBB#")
                        .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "#BBB#")
                        .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "#BBB#")
                        .aisle("FYDSL", "#AKA#", "#AAA#", "#BBB#", "#BBB#", "#BBB#", "#####")
                        .where('A', GTCABlocks.CASING_GREENHOUSE.getDefaultState())
                        .where('F', ITEM_IMPORT_BUS[GTValues.ULV], Direction.SOUTH)
                        .where('D', definition, Direction.SOUTH)
                        .where('Y', GTMachines.FLUID_IMPORT_HATCH[GTValues.ULV], Direction.SOUTH)
                        .where('S', GTMachines.ITEM_EXPORT_BUS[GTValues.ULV], Direction.SOUTH)
                        .where('L', ENERGY_INPUT_HATCH[GTValues.LV], Direction.SOUTH)
                        .where('K', GTMachines.MAINTENANCE_HATCH, Direction.SOUTH)
                        .where('C', Blocks.DIRT.defaultBlockState())
                        .where('B', CASING_TEMPERED_GLASS);

                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Green House")
            )
            .workableCasingModel(
                    GTCA.id("block/casing/casing_greenhouse"),
                    GTCA.id("block/multiblock/aebf")
            )
//            .compassSections(GTCompassSections.TIER[MV])
//            .compassNodeSelf()
            .register();


    public static final MultiblockMachineDefinition POLYMERIZER = REGISTRATE
            .multiblock("polymerizer", WorkableElectricMultiblockMachine::new)
            .langValue("Polymerizer")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.POLYMERIZER)
            .appearanceBlock(GTCABlocks.DURAL_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                    .aisle("CCCCC", "CHCHC", "CPPPC", "CHCHC", "CCCCC")
                    .aisle("CCXCC", "CGCGC", "CGCGC", "CGCGC", "CCCCC")
                    .where('X', controller(blocks(definition.getBlock())))
                    .where('C', blocks(GTCABlocks.DURAL_CASING.get()).setMinGlobalLimited(55)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where('H', blocks(HERMETIC_CASING_HV.get()))
                    .where('P', blocks(CASING_STEEL_PIPE.get()))
                    .where('G', blocks(CASING_LAMINATED_GLASS.get()))
                    .build()
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Polymerizer")
            )
            .workableCasingModel(
                    GTCA.id("block/casing/dural_casing"),
                    GTCA.id("block/multiblock/aebf")
            )
            .register();

    public static final MultiblockMachineDefinition SHD_TURBINE = REGISTRATE
            .multiblock("shd_turbine", holder -> new LargeTurbineMachine(holder, LuV))
            .langValue("Super Critical Steam Turbine")
            .rotationState(RotationState.ALL)
            .recipeType(GTCARecipeTypes.SHD_STEAM_TURBINE)
            .generator(true)
            .recipeModifier(LargeTurbineMachine::recipeModifier, true)
            .appearanceBlock(GTCABlocks.SHD_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCC", "CHHC", "CCCC")
                    .aisle("CHHC", "RGGR", "CHHC")
                    .aisle("CCCC", "CSHC", "CCCC")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('G', blocks(GTCABlocks.SHD_Gearbox.get()))
                    .where('C', blocks(GTCABlocks.SHD_CASING.get()))
                    .where('R',
                            new TraceabilityPredicate(
                                    new SimplePredicate(
                                            state -> MetaMachine.getMachine(state.getWorld(),
                                                    state.getPos()) instanceof IRotorHolderMachine rotorHolder &&
                                                    state.getWorld()
                                                            .getBlockState(state.getPos()
                                                                    .relative(rotorHolder.self().getFrontFacing()))
                                                            .isAir(),
                                            () -> PartAbility.ROTOR_HOLDER.getAllBlocks().stream()
                                                    .map(BlockInfo::fromBlock).toArray(BlockInfo[]::new)))
                                    .addTooltips(Component.translatable("gtceu.multiblock.pattern.clear_amount_3"))
                                    .addTooltips(Component.translatable("gtceu.multiblock.pattern.error.limited.1",
                                            VN[LuV]))
                                    .setExactLimit(1)
                                    .or(abilities(PartAbility.OUTPUT_ENERGY)).setExactLimit(1))
                    .where('H', blocks(GTCABlocks.SHD_CASING.get())
                            .or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true))
                            .or(autoAbilities(true, false, false)))
                    .build())
            .recoveryItems(
                    () -> new ItemLike[]{
                            GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
            .workableCasingModel(
                    GTCA.id("block/casing/shd_casing"),
                    GTCA.id("block/multiblock/shd")
            )
            .tooltips(
                    Component.translatable("gtceu.universal.tooltip.base_production_eut", V[LuV] * 2),
                    Component.translatable("gtceu.multiblock.turbine.efficiency_tooltip", VNF[LuV]),
                    Component.translatable("gtca.machine.SC_desc.tooltip"),
                    Component.translatable("gtca.machine.SC_desc.tooltip2")
            )


//            .compassSections(GTCompassSections.TIER[HV])
//            .compassNodeSelf()
            .register();


    public static final MultiblockMachineDefinition EXTREME_HEAT_EXCHANGER = REGISTRATE.multiblock("extreme_heat_exchanger", WorkableElectricMultiblockMachine::new)
            .langValue("Extreme Heat Exchanger")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.EXTREME_HEAT_EXCHANGER)
            .appearanceBlock(CASING_TUNGSTENSTEEL_ROBUST)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("#CCC#", "#CCC#", "#CCC#", "#CCC#", "#CCC#", "#CCC#")
                            .aisle("CCCCC", "GPPPG", "GPPPG", "GPPPG", "GPPPG", "CCCCC")
                            .aisle("CCCCC", "GWWWG", "GWWWG", "GWWWG", "GWWWG", "CCCCC")
                            .aisle("CCCCC", "GPPPG", "GPPPG", "GPPPG", "GPPPG", "CCCCC")
                            .aisle("CCCCC", "GWWWG", "GWWWG", "GWWWG", "GWWWG", "CCCCC")
                            .aisle("CCCCC", "GPPPG", "GPPPG", "GPPPG", "GPPPG", "CCCCC")
                            .aisle("CCCCC", "GWWWG", "GWWWG", "GWWWG", "GWWWG", "CCCCC")
                            .aisle("CCCCC", "GPPPG", "GPPPG", "GPPPG", "GPPPG", "CCCCC")
                            .aisle("CCCCC", "GWWWG", "GWWWG", "GWWWG", "GWWWG", "CCCCC")
                            .aisle("CCCCC", "GPPPG", "GPPPG", "GPPPG", "GPPPG", "CCCCC")
                            .aisle("#CEC#", "#CCC#", "#CCC#", "#CCC#", "#CCC#", "#CCC#")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("C", blocks(CASING_TUNGSTENSTEEL_ROBUST.get()).setMinGlobalLimited(115).or(Predicates.autoAbilities(definition.getRecipeTypes())).or(autoAbilities(true, false, false)))
                            .where("P", blocks(CASING_TUNGSTENSTEEL_PIPE.get()))
                            .where("W", blocks(GTCABlocks.PRW_Casing.get()))
                            .where("G", blocks(GTCABlocks.REINFORCED_GLASS.get()))
                            .where('#', any())
                            .build()
            )
            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                    GTCEu.id("block/multiblock/implosion_compressor")
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Heat Exchanger"),
                    Component.translatable("gtca.machine.EHE_desc.tooltip"),
                    Component.translatable("gtca.machine.EHE_desc.tooltip2")
            )
            .register();

    public static final MultiblockMachineDefinition MEGA_OIL_CRACKING_UNIT = REGISTRATE
            .multiblock("mega_oil_cracking_unit", CoilWorkableElectricMultiblockMachine::new)
            .langValue("Mega Oil Cracking Unit")
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.CRACKING_RECIPES)
            .recipeModifiers(BATCH_MODE,
                    OC_NON_PERFECT_SUBTICK, GTRecipeModifiers::crackerOverclock)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("HHHHHHHHHHHHH", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#")
                    .aisle("HHHHHHHHHHHHH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HHGGGGGGGGGHH")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#HGGGGGGGGGH#")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C###C###H#", "#H#C#C#C#C#H#", "#H#C###C###H#", "#G#C#C#C#C#G#", "#HGGGHHHGGGH#")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C#C#C#C#H#", "#O#C#C#C#C#I#", "#H#C#C#C#C#H#", "#G#C#C#C#C#G#", "#HGGGHAHGGGH#")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C###C###H#", "#H#C#C#C#C#H#", "#H#C###C###H#", "#G#C#C#C#C#G#", "#HGGGHHHGGGH#")
                    .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#HGGGGGGGGGH#")
                    .aisle("HHHHHHHHHHHHH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HHGGGGGGGGGHH")
                    .aisle("HHHHHHXHHHHHH", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#")
                    .where('X', Predicates.controller(blocks(definition.get())))
                    .where('H', blocks(CASING_STAINLESS_CLEAN.get()).setMinGlobalLimited(12)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes(), true, false, true, false, false, false))
                            .or(Predicates.autoAbilities(true, false, true)))
                    .where('#', Predicates.any())
                    .where('C', Predicates.heatingCoils())
                    .where('G', blocks(CASING_LAMINATED_GLASS.get()))
                    .where('I', abilities(PartAbility.IMPORT_FLUIDS))
                    .where('A', abilities(PartAbility.IMPORT_FLUIDS))
                    .where('O', abilities(PartAbility.EXPORT_FLUIDS))
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("HHHHHHHHHHHHH", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#")
                        .aisle("HHHHHHHHHHHHH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HHGGGGGGGGGHH")
                        .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#HGGGGGGGGGH#")
                        .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C###C###H#", "#H#C#C#C#C#H#", "#H#C###C###H#", "#G#C#C#C#C#G#", "#HGGGHHHGGGH#")
                        .aisle("EHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C#C#C#C#H#", "#O#C#C#C#C#I#", "#H#C#C#C#C#H#", "#G#C#C#C#C#G#", "#HGGGHAHGGGH#")
                        .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#H#C###C###H#", "#H#C#C#C#C#H#", "#H#C###C###H#", "#G#C#C#C#C#G#", "#HGGGHHHGGGH#")
                        .aisle("HHHHHHHHHHHHH", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#G#C#C#C#C#G#", "#HGGGGGGGGGH#")
                        .aisle("HHHHHHHHHHHHH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HGGGGGGGGGGGH", "HHGGGGGGGGGHH")
                        .aisle("HHMHTHXHHHHHH", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#", "#H#########H#")
                        .where('X', definition, Direction.SOUTH)
                        .where('H', CASING_STAINLESS_CLEAN.getDefaultState())
                        .where('E', ENERGY_INPUT_HATCH[GTValues.LV], Direction.WEST)
                        .where('T', ITEM_IMPORT_BUS[GTValues.LV], Direction.SOUTH)
                        .where('I', FLUID_IMPORT_HATCH[GTValues.LV], Direction.EAST)
                        .where('A', FLUID_IMPORT_HATCH[GTValues.LV], Direction.UP)
                        .where('O', FLUID_EXPORT_HATCH[GTValues.LV], Direction.WEST)
                        .where('M', MAINTENANCE_HATCH, Direction.SOUTH)
                        .where('G', CASING_LAMINATED_GLASS.getDefaultState())
                        .where('#', Blocks.AIR.defaultBlockState());
                GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .sorted(Comparator.comparingInt(entry -> entry.getKey().getTier()))
                        .forEach(
                                coil -> shapeInfo.add(builder.shallowCopy().where('C', coil.getValue().get()).build()));
                return shapeInfo;
            })
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/cracking_unit"))
            .tooltips(
                    Component.translatable("gtceu.multiblock.parallelizable.tooltip"),
                    Component.translatable("gtceu.machine.cracker.tooltip.1")
            )
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.cracking_unit.energy",
                            100 - 10 * coilMachine.getCoilTier()));
                }
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Oil Cracker")
            )
//            .compassSections(GTCompassSections.TIER[EV])
//            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition MEGA_LCR = REGISTRATE
            .multiblock("mega_lcr", WorkableElectricMultiblockMachine::new)
            .langValue("Chemical Reaction Chamber")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
            .appearanceBlock(CASING_PTFE_INERT)
            .recipeModifiers(BATCH_MODE,
                    OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "G#N#G", "G#P#G", "G#N#G", "CCCCC")
                            .aisle("CCCCC", "CGCGC", "CGEGC", "CGCGC", "CCCCC")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("N", blocks(COIL_CUPRONICKEL.get()))
                            .where("G", blocks(CASING_LAMINATED_GLASS.get()))
                            .where("P", blocks(CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                            .where('#', Predicates.air())
                            .where('C', blocks(CASING_PTFE_INERT.get()).setMinGlobalLimited(100)
                                    .or(autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, false, true)))
                            .build()
            )
            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    GTCA.id("block/multiblock/shd")
            )
            .tooltips(
                    Component.translatable("gtceu.multiblock.parallelizable.tooltip"),
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Chemical Reactor / LCR")
            )
            .register();

    public static final MultiblockMachineDefinition INDUSTRIAL_COKE_OVEN = REGISTRATE.multiblock("industrial_coke_oven", WorkableElectricMultiblockMachine::new)
            .langValue("Industrial Coke Oven")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.INDUSTRIAL_COKE_OVEN)
            .appearanceBlock(GTCABlocks.TANTALLOY61_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("CCC", "FFF", "CCC")
                            .aisle("CCC", "F#F", "CMC")
                            .aisle("CEC", "FFF", "CCC")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("F", blocks(FIREBOX_TUNGSTENSTEEL.get()))
                            .where('M', abilities(PartAbility.MUFFLER))
                            .where('#', Predicates.air())
                            .where("C", blocks(GTCABlocks.TANTALLOY61_CASING.get()).setMinGlobalLimited(9)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, false, false)))
                            .build()
            )
            .workableCasingModel(
                    GTCA.id("block/casing/tantalloy61_casing"),
                    GTCEu.id("block/multiblock/implosion_compressor")
            )
            .register();

    public static final MultiblockMachineDefinition THERMAL_REACTOR = REGISTRATE.multiblock("thermal_reactor", CoilWorkableElectricMultiblockMachine::new)
            .langValue("Thermal Reaction Chamber")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.THERMAL_REACTOR)
            .appearanceBlock(GTCABlocks.NIMONIC80A_CASING)
            .recipeModifier(GTRecipeModifiers::ebfOverclock)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("#CCC#", "#NNN#", "#CCC#", "#####", "#####", "#####", "#####", "#####", "#####", "#####")
                            .aisle("CCCCC", "N###N", "CCCCC", "#FDF#", "#F#F#", "#FDF#", "#F#F#", "#FDF#", "#F#F#", "#FDF#")
                            .aisle("CCCCC", "N###N", "CCPCC", "#DPD#", "##P##", "#DPD#", "##P##", "#DPD#", "##P##", "#DDD#")
                            .aisle("CCCCC", "N###N", "CCCCC", "#FDF#", "#F#F#", "#FDF#", "#F#F#", "#FDF#", "#F#F#", "#FDF#")
                            .aisle("#CEC#", "#NNN#", "#CCC#", "#####", "#####", "#####", "#####", "#####", "#####", "#####")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where('F', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, TungstenCarbide)))
                            .where('#', Predicates.any())
                            .where('D', blocks(GTCABlocks.NIMONIC80A_CASING.get()))
                            .where('P', blocks(CASING_TITANIUM_PIPE.get()))
                            .where('N', heatingCoils())
                            .where("C", blocks(GTCABlocks.NIMONIC80A_CASING.get()).setMinGlobalLimited(11)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, false, false)))
                            .build()
            ).shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("#MCE#", "#NNN#", "#CCC#", "#####", "#####", "#####", "#####", "#####", "#####", "#####")
                        .aisle("ICCCK", "N###N", "CCCCC", "#FDF#", "#F#F#", "#FDF#", "#F#F#", "#FDF#", "#F#F#", "#FDF#")
                        .aisle("OCCCL", "N###N", "CCPCC", "#DPD#", "##P##", "#DPD#", "##P##", "#DPD#", "##P##", "#DDD#")
                        .aisle("CCCCC", "N###N", "CCCCC", "#FDF#", "#F#F#", "#FDF#", "#F#F#", "#FDF#", "#F#F#", "#FDF#")
                        .aisle("#CSC#", "#NNN#", "#CCC#", "#####", "#####", "#####", "#####", "#####", "#####", "#####")
                        .where('C', GTCABlocks.NIMONIC80A_CASING.getDefaultState())
                        .where('D', GTCABlocks.NIMONIC80A_CASING.getDefaultState())
                        .where('F', ChemicalHelper.getBlock(TagPrefix.frameGt, TungstenCarbide))
                        .where('P', CASING_TITANIUM_PIPE.getDefaultState())
                        .where('S', definition, Direction.SOUTH)
                        .where('#', Blocks.AIR.defaultBlockState())
                        .where('E', ENERGY_INPUT_HATCH[GTValues.LV], Direction.NORTH)
                        .where('I', ITEM_IMPORT_BUS[GTValues.LV], Direction.WEST)
                        .where('O', ITEM_EXPORT_BUS[GTValues.LV], Direction.WEST)
                        .where('K', FLUID_IMPORT_HATCH[GTValues.LV], Direction.EAST)
                        .where('L', FLUID_EXPORT_HATCH[GTValues.LV], Direction.EAST)
                        .where('M', MAINTENANCE_HATCH, Direction.NORTH);
                GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .sorted(Comparator.comparingInt(entry -> entry.getKey().getTier()))
                        .forEach(
                                coil -> shapeInfo.add(builder.shallowCopy().where('N', coil.getValue().get()).build()));
                return shapeInfo;
            })
            .workableCasingModel(
                    GTCA.id("block/casing/nimonic80a_casing"),
                    GTCA.id("block/multiblock/aebf")
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Thermal Catalyst Reactor")
            )
            .additionalDisplay((controller, components) -> {
                if (controller instanceof CoilWorkableElectricMultiblockMachine coilMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.blast_furnace.max_temperature",
                            Component
                                    .translatable(
                                            FormattingUtil
                                                    .formatNumbers(coilMachine.getCoilType().getCoilTemperature() +
                                                            100L * Math.max(0, coilMachine.getTier() - GTValues.MV)) +
                                                    "K")
                                    .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                }
            })
//            .compassSections(GTCompassSections.TIER[MV])
//            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition COMET_CYCLOTRON = REGISTRATE.multiblock("comet", WorkableElectricMultiblockMachine::new)
            .langValue("COMET Cyclotron™")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.COMET_CYCLOTRON)
            .appearanceBlock(GTCABlocks.COMET_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("###############", "######BBB######", "###############")
                            .aisle("######BBB######", "####BBCCCBB####", "######BBB######")
                            .aisle("####BB###BB####", "###BCCBABCCB###", "####BB###BB####")
                            .aisle("###B#######B###", "##BCBB###BBCB##", "###B#######B###")
                            .aisle("##B#########B##", "#BCB#######BCB#", "##B#########B##")
                            .aisle("##B#########B##", "#BCB#######BCB#", "##B#########B##")
                            .aisle("#B###########B#", "BCB#########BCB", "#B###########B#")
                            .aisle("#B###########B#", "BCB#########BCB", "#B###########B#")
                            .aisle("#B###########B#", "BCB#########BCB", "#B###########B#")
                            .aisle("##B#########B##", "#BCB#######BCB#", "##B#########B##")
                            .aisle("##B#########B##", "#BCB#######BCB#", "##B#########B##")
                            .aisle("###B#######B###", "##BCBB###BBCB##", "###B#######B###")
                            .aisle("####BB###BB####", "###BCCBBBCCB###", "####BB###BB####")
                            .aisle("######BBB######", "####BBCCCBB####", "######BBB######")
                            .aisle("###############", "######BBB######", "###############")
                            .where('#', Predicates.any())
                            .where('C', blocks(GTCABlocks.CYCLOTRON_COIL.get()))
                            .where("A", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("B", blocks(GTCABlocks.COMET_CASING.get()).setMinGlobalLimited(112)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, false, false))
                            )
                            .build()
            )
            .workableCasingModel(
                    GTCA.id("block/casing/comet_casing"),
                    GTCA.id("block/multiblock/comet")
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Particle Accelerator"),
                    Component.translatable("gtca.machine.comet_desc.tooltip"),
                    Component.translatable("gtca.machine.comet_desc.tooltip2")
            )
            .register();

    public static final MultiblockMachineDefinition ISAMILL = REGISTRATE.multiblock("isamill", WorkableElectricMultiblockMachine::new)
            .langValue("IsaMill Grinding Machine")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.ISAMILL)
            .appearanceBlock(GTCABlocks.ISAMILL_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("ACCCCCC", "ACCCCCC", "ACCCCCC")
                            .aisle("ACCCCCC", "AGGGGGC", "ACCCCCC")
                            .aisle("ACCCCCC", "ACECCCC", "ACCCCCC")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("G", blocks(GTCABlocks.ISAMILL_GEARBOX.get()))
                            .where("A", blocks(GTCABlocks.ISAMILL_AIR_INTAKE.get()))
                            .where("C", blocks(GTCABlocks.ISAMILL_CASING.get()).setMinGlobalLimited(40)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, true, false))
                            )
                            .build()
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Grinding Machine"),
                    Component.translatable("gtca.machine.isamill_desc.tooltip")
            )

            .workableCasingModel(
                    GTCA.id("block/casing/isa_mill_casing"),
                    GTCA.id("block/multiblock/aebf")
            )
            .register();

    public static final MultiblockMachineDefinition FLCR = REGISTRATE.multiblock("flotation_cell_regulator", WorkableElectricMultiblockMachine::new)
            .langValue("Flotation Cell Regulator")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.FLCR)
            .appearanceBlock(GTCABlocks.FLCR_CASING_TYPE_II)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("##CCC##", "##CCC##", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                            .aisle("#CCCCC#", "#CCCCC#", "###F###", "###F###", "###F###", "###F###", "###F###", "###F###", "#######")
                            .aisle("CCCCCCC", "CCCCCCC", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##FFF##", "###C###")
                            .aisle("CCCCCCC", "CCCCCCC", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#FF#FF#", "##CCC##")
                            .aisle("CCCCCCC", "CCCCCCC", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##FFF##", "###E###")
                            .aisle("#CCCCC#", "#CCCCC#", "###F###", "###F###", "###F###", "###F###", "###F###", "###F###", "#######")
                            .aisle("##CCC##", "##CCC##", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("F", blocks(GTCABlocks.FLCR_CASING_TYPE_I.get()))
                            .where('#', Predicates.any())
                            .where("C", blocks(GTCABlocks.FLCR_CASING_TYPE_II.get()).setMinGlobalLimited(44)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, true, false))
                            )
                            .build()
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Flotation cell"),
                    Component.translatable("gtca.machine.flcr_desc.tooltip")
            )
            .workableCasingModel(
                    GTCA.id("block/casing/flcr_2"),
                    GTCA.id("block/multiblock/aebf")
            )
            .register();

    public static final MultiblockMachineDefinition TEGMARK_FORGE = REGISTRATE.multiblock("tegmark_forge", WorkableElectricMultiblockMachine::new)
            .langValue("Tegmark® Forge")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.TEGMARK_FORGE)
            .appearanceBlock(GTCABlocks.P_N_PROTECTIVE_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("#############################", "#############################", "#############BB##############", "############B##B#############", "############B##B#############", "#############BB##############", "#############################")
                            .aisle("#############################", "#############################", "############BBBB#############", "############BCCB#############", "############BCCB#############", "############BBBB#############", "#############################")
                            .aisle("###########D####D############", "###########DBBBBD############", "###########DBEEBD############", "###########DEFFED############", "###########DEFFED############", "###########DBFFBD############", "###########DBCCBD############")
                            .aisle("#############################", "#############BB##############", "############BEEB#############", "############EFFE#############", "############EFFE#############", "############BFFB#############", "###########DBCCBD############")
                            .aisle("#############################", "#############BB##############", "############BEEB#############", "############EFFE#############", "############EFFE#############", "############BFFB#############", "###########DBCCBD############")
                            .aisle("###########D####D############", "###########DBBBBD############", "###########DBEEBD############", "###########DEFFED############", "###########DEFFED############", "###########DBFFBD############", "###########DBCCBD############")
                            .aisle("#############################", "#############################", "############BBBB#############", "############BGGB#############", "############BGGB#############", "############BBBB#############", "#############################")
                            .aisle("#############################", "#############################", "#############################", "############EGGE#############", "############EGGE#############", "#############CC##############", "#############################")
                            .aisle("#############################", "#############################", "#############################", "############EGGE#############", "############EGGE#############", "#############CC##############", "#############################")
                            .aisle("####D##D############D##D#####", "####D##D############D##D#####", "####DHHD#####BB#####D##D#####", "####DHHD####BGGB####D##D#####", "####DHHD####BGGB####D##D#####", "####DHHD#####CC#####D##D#####", "####DDDD############DDDD#####")
                            .aisle("#############################", "####B##B#####BB#####B##B#####", "###BBBBBB###BEEB###BBBBBB####", "##BBEEEEBEEBBIIBBEEBEEEEBB###", "##BBEEEEBEEBBIIBBEEBEEEEBB###", "###BBBBBB###BCCB###BBBBBB####", "####BBBB############BBBB#####")
                            .aisle("#############################", "####BBBB####BEEB####BBBB#####", "##BBEEEEBBBBEIIEBBBBEEEEBB###", "###CFFFFGGGGIIIIGGGGFFFFC####", "###CFFFFGGGGIIIIGGGGFFFFC####", "##BBFFFFBCCCCIICCCCBFFFFBB###", "####CCCC#####CC#####CCCC#####")
                            .aisle("#############################", "####BPPB####BEEB####BBBB#####", "##BBEEEEBBBBEIIEBBBBEEEEBB###", "###CFFFFGGGGIIIIGGGGFFFFC####", "###CFFFFGGGGIIIIGGGGFFFFC####", "##BBFFFFBCCCCIICCCCBFFFFBB###", "####CCCC#####CC#####CCCC#####")
                            .aisle("#############################", "####B##B#####BB#####B##B#####", "###BBBBBB###BEEB###BBBBBB####", "##BBEEEEBEEBBIIBBEEBEEEEBB###", "##BBEEEEBEEBBIIBBEEBEEEEBB###", "###BBBBBB###BCCB###BBBBBB####", "####BBBB############BBBB#####")
                            .aisle("####D##D############D##D#####", "####D##D############D##D#####", "####DBBD#####BB#####D##D#####", "####DJLD####BGGB####D##D#####", "####DOKD####BGGB####D##D#####", "####DBBD#####CC#####D##D#####", "####DDDD############DDDD#####")
                            .aisle("#############################", "#############################", "#############################", "############EGGE#############", "############EGGE#############", "#############CC##############", "#############################")
                            .aisle("#############################", "#############################", "#############################", "############EGGE#############", "############EGGE#############", "#############CC##############", "#############################")
                            .aisle("#############################", "#############################", "############BBBB#############", "############BGGB#############", "############BGGB#############", "############BBBB#############", "#############################")
                            .aisle("###########D####D############", "###########DBBBBD############", "###########DBEEBD############", "###########DEFFED############", "###########DEFFED############", "###########DBFFBD############", "###########DBCCBD############")
                            .aisle("#############################", "#############BB##############", "###########BBEEB#############", "###########BEFFE#############", "###########MEFFE#############", "###########BBFFB#############", "###########DBCCBD############")
                            .aisle("#############################", "#############BB##############", "###########BBEEB#############", "###########BEFFE#############", "###########MEFFE#############", "###########BBFFB#############", "###########DBCCBD############")
                            .aisle("###########D####D############", "###########DBBBBD############", "###########DBEEBD############", "###########DEFFED############", "###########DEFFED############", "###########DBFFBD############", "###########DBCCBD############")
                            .aisle("#############################", "#############################", "############BBBB#############", "############BCCB#############", "############BCCB#############", "############BBBB#############", "#############################")
                            .aisle("#############################", "#############################", "#############BB##############", "############B##B#############", "############B##B#############", "#############BB##############", "#############################")
                            .where('#', Predicates.any())
                            .where('B', blocks(GTCABlocks.P_N_PROTECTIVE_CASING.get()))
                            .where('C', blocks(GTCABlocks.BORSILICATE_REINFORCED_IRIDIUM_GLASS.get()))
                            .where('D', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, MAR_CE_M200)))
                            .where('O', abilities(PartAbility.MAINTENANCE))
                            .where('E', blocks(GTCABlocks.ULTRA_INDUCTIVE_CASING.get()))
                            .where('F', blocks(GTCABlocks.P_N_E_CAPACITOR.get()))
                            .where('G', blocks(GTCABlocks.P_N_E_LASER_ACTIVATOR.get()))
                            .where('H', blocks(ITEM_IMPORT_BUS[ULV].getBlock()))
                            .where('I', blocks(GTCABlocks.STABILIZED_TRANSMUTATION_CORE.get()))
                            .where('J', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('K', abilities(PartAbility.EXPORT_ITEMS))
                            .where('L', abilities(PartAbility.DATA_ACCESS).or(abilities(PartAbility.OPTICAL_DATA_RECEPTION)))
                            .where('M', abilities(PartAbility.IMPORT_FLUIDS))
                            .where('P', abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1).or(blocks(GTCABlocks.P_N_PROTECTIVE_CASING.get())))
                            .build()
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Tegmark Forge"),
                    Component.translatable("gtca.machine.tegmark1_desc.tooltip"),
                    Component.translatable("gtca.machine.tegmark2_desc.tooltip")
            )
            .workableCasingModel(
                    GTCA.id("block/casing/p_n_casing"),
                    GTCA.id("block/multiblock/tegmark_forge")
            )
            .register();

    public static final MultiblockMachineDefinition NANOFORGE = REGISTRATE.multiblock("nano_forge", WorkableElectricMultiblockMachine::new)
            .langValue("Nano Forge")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.NANOFORGE)
            .appearanceBlock(GTCABlocks.RADIANT_NAQUADAH_ALLOY_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("##KKKKK##", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CDC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########")
                            .aisle("#KKKKKKK#", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CDC###", "###CBC###", "##CB#BC##", "##CB#BC##", "##CB#BC##", "##CB#BC##", "##BB#BB##", "##DD#DD##", "##BB#BB##", "##BB#BB##", "##BB#BB##", "##BB#BB##", "##DD#DD##", "##BB#BB##", "##CB#BC##", "##CB#BC##", "##CB#BC##", "##CB#BC##", "###CDC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CDC###", "###CBC###", "####C####", "####C####", "####C####", "####C####", "####C####")
                            .aisle("KKKKKKKKK", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "###B#B###", "###B#B###", "##B###B##", "##B###B##", "##B###B##", "##D###D##", "##B###B##", "#B#####B#", "#B#####B#", "##B###B##", "##D###D##", "##B###B##", "##B###B##", "##B###B##", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "####B####", "####B####", "####B####", "####B####", "####B####")
                            .aisle("KKKKKKKKK", "##B###B##", "##B###B##", "##B###B##", "##B###B##", "###B#B###", "###D#D###", "###B#B###", "###B#B###", "###B#B###", "##B###B##", "##B###B##", "##B###B##", "##D###D##", "##B###B##", "#B#####B#", "#B#####B#", "##B###B##", "##D###D##", "##B###B##", "##B###B##", "##B###B##", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "####B####", "####B####", "####B####", "####B####", "####B####")
                            .aisle("KKKKKKKKK", "##B###B##", "##B###B##", "##B###B##", "##B###B##", "###B#B###", "###D#D###", "###B#B###", "###B#B###", "###B#B###", "##B###B##", "##B###B##", "##B###B##", "##D###D##", "##B###B##", "#B#####B#", "#B#####B#", "##B###B##", "##D###D##", "##B###B##", "##B###B##", "##B###B##", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "####B####", "####B####", "####B####", "####B####", "####B####")
                            .aisle("KKKKKKKKK", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "###B#B###", "###B#B###", "##B###B##", "##B###B##", "##B###B##", "##D###D##", "##B###B##", "#B#####B#", "#B#####B#", "##B###B##", "##D###D##", "##B###B##", "##B###B##", "##B###B##", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###B#B###", "###D#D###", "###B#B###", "####B####", "####B####", "####B####", "####B####", "####B####")
                            .aisle("#KKKKKKK#", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CDC###", "###CBC###", "##CB#BC##", "##CB#BC##", "##CB#BC##", "##CB#BC##", "##BB#BB##", "##DD#DD##", "##BB#BB##", "##BB#BB##", "##BB#BB##", "##BB#BB##", "##DD#DD##", "##BB#BB##", "##CB#BC##", "##CB#BC##", "##CB#BC##", "##CB#BC##", "###CDC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CDC###", "###CBC###", "####C####", "####C####", "####C####", "####C####", "####C####")
                            .aisle("##KKJKK##", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CDC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "###CBC###", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########", "#########")
                            .where('#', Predicates.any())
                            .where('B', blocks(GTCABlocks.RADIANT_NAQUADAH_ALLOY_CASING.get()))
                            .where('C', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, StellarAlloy)))
                            .where('D', blocks(GTCABlocks.RADIANT_NAQUADAH_ALLOY_AIR_INTAKE_CASING.get()))
                            .where('J', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('K', blocks(GTCABlocks.RADIANT_NAQUADAH_ALLOY_CASING.get()).setMinGlobalLimited(40).or(autoAbilities(definition.getRecipeTypes())).or(Predicates.autoAbilities(true, false, false)))
                            .build()
            )

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Nanite Fabricator"),
                    Component.translatable("gtca.machine.nanoforge1_desc.tooltip")

            )

            .workableCasingModel(
                    GTCA.id("block/casing/rnac_casing"),
                    GTCA.id("block/multiblock/nanoforge")
            )
            .register();


    /// TEST PCB FACTORY///
    public static final MultiblockMachineDefinition PCB_FACTORY_MKI = registerPcbFactory(
            "pcb_factory_mki", UV,
            GTCABlocks.BASIC_PHOTOLITHOGRAPHIC_FRAMEWORK_CASING, null, Neutronex, null,
            GTCA.id("block/casing/bpf_casing"),
            GTCA.id("block/multiblock/pcb_factory"), "PCB Factory MKI");

    public static final MultiblockMachineDefinition PCB_FACTORY_MKII = registerPcbFactory(
            "pcb_factory_mkii", UHV,
            GTCABlocks.BASIC_PHOTOLITHOGRAPHIC_FRAMEWORK_CASING, GTCABlocks.REINFORCED_PHOTOLITHOGRAPHIC_FRAMEWORK_CASING, Neutronex, Duranium,
            GTCA.id("block/casing/bpf_casing"),
            GTCA.id("block/multiblock/pcb_factory"), "PCB Factory MKII");

    public static final MultiblockMachineDefinition PCB_FACTORY_MKIII = registerPcbFactory(
            "pcb_factory_mkiii", UEV,
            GTCABlocks.RADIANT_PROOF_PHOTOLITHOGRAPHIC_FRAMEWORK_CASING, GTCABlocks.RADIANT_NAQUADAH_ALLOY_CASING, null, null,
            GTCA.id("block/casing/radiant_proof_reinforced_casing"),
            GTCA.id("block/multiblock/pcb_factory"), "PCB Factory MKIII");

    /// space elevator and modules ///


    public static final MultiblockMachineDefinition SPACE_MINER_MKI = registerSpaceMiner(
            "space_miner_mki", LuV,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_miner"), "Space Miner MK I");

    public static final MultiblockMachineDefinition SPACE_MINER_MKII = registerSpaceMiner(
            "space_miner_mkii", ZPM,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_miner"), "Space Miner MK II");

    public static final MultiblockMachineDefinition SPACE_MINER_MKIII = registerSpaceMiner(
            "space_miner_mkiii", UV,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_miner"), "Space Miner MK III");


    public static MultiblockMachineDefinition registerSpaceMiner(String name, int tier,
                                                                 Supplier<? extends Block> casing,
                                                                 ResourceLocation casingTexture,
                                                                 ResourceLocation overlayModel,
                                                                 String lang) {

        // один общий билдер, чтобы не копипастить 3 раза
        if (tier != LuV && tier != ZPM && tier != UV) return null;

        return REGISTRATE.multiblock(name, holder -> new SpaceMinerMachine(holder, tier))
                .langValue(lang)
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(GTCARecipeTypes.SPACE_MINER)
                .appearanceBlock(GTCABlocks.SPACE_ELEVATOR_CASING)
                .recipeModifier(OC_NON_PERFECT_SUBTICK)
                .pattern(definition ->
                        FactoryBlockPattern.start()
                                .aisle("C", "C", "C", "C", "C")
                                .aisle("C", "C", "C", "X", "C")
                                .where('X', Predicates.controller(Predicates.blocks(definition.get())))
                                .where('C', blocks(GTCABlocks.SPACE_ELEVATOR_CASING.get()).or(Predicates.abilities(
                                        PartAbility.IMPORT_ITEMS,
                                        PartAbility.EXPORT_ITEMS,
                                        PartAbility.IMPORT_FLUIDS,
                                        PartAbility.COMPUTATION_DATA_RECEPTION
                                )).or(Predicates.autoAbilities(false, false, false)))
                                .build())

                .tooltips(
                        Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Space Mining Module"),
                        Component.translatable("gtca.machine.space_miner_desc.tooltip")
                )
                .workableCasingModel(
                        casingTexture,
                        overlayModel
                )
                .register();
    }


    public static final MultiblockMachineDefinition SPACE_PUMP_MKI = registerSpacePump(
            "space_pump_mki", LuV,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_pump"), "Space Pump MK I");

    public static final MultiblockMachineDefinition SPACE_PUMP_MKII = registerSpacePump(
            "space_pump_mkii", ZPM,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_pump"), "Space Pump MK II");

    public static final MultiblockMachineDefinition SPACE_PUMP_MKIII = registerSpacePump(
            "space_pump_mkiii", UV,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_pump"), "Space Pump MK III");

    public static MultiblockMachineDefinition registerSpacePump(String name, int tier,
                                                                Supplier<? extends Block> casing,
                                                                ResourceLocation casingTexture,
                                                                ResourceLocation overlayModel,
                                                                String lang) {

        if (tier != LuV && tier != ZPM && tier != UV) return null;

        return REGISTRATE.multiblock(name, holder -> new SpacePumpMachine(holder, tier))
                .langValue(lang)
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(GTCARecipeTypes.SPACE_PUMP)
                .appearanceBlock(GTCABlocks.SPACE_ELEVATOR_CASING)
                .recipeModifiers(true, OC_NON_PERFECT_SUBTICK)
                .pattern(definition ->
                        FactoryBlockPattern.start()
                                .aisle("C", "C", "C", "C", "C")
                                .aisle("C", "C", "C", "X", "C")
                                .where('X', Predicates.controller(Predicates.blocks(definition.get())))
                                .where('C', blocks(GTCABlocks.SPACE_ELEVATOR_CASING.get()).or(Predicates.abilities(
                                        PartAbility.EXPORT_FLUIDS
                                )).or(Predicates.autoAbilities(false, false, false)))
                                .build())

                .tooltips(
                        Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Space Pump Module"),
                        Component.translatable("gtca.machine.space_pump_desc.tooltip")
                )
                .workableCasingModel(
                        casingTexture,
                        overlayModel
                )
                .register();
    }


    public static final MultiblockMachineDefinition SPACE_ASSEMBLER_MKI = registerSpaceAssembler(
            "space_assembler_mki", LuV,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_assembler"), "Space Assembler MK I");

    public static final MultiblockMachineDefinition SPACE_ASSEMBLER_MKII = registerSpaceAssembler(
            "space_assembler_mkii", ZPM,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_assembler"), "Space Assembler MK II");

    public static final MultiblockMachineDefinition SPACE_ASSEMBLER_MKIII = registerSpaceAssembler(
            "space_assembler_mkiii", UV,
            GTCABlocks.SPACE_ELEVATOR_CASING,
            GTCA.id("block/casing/space_elevator_base_casing"),
            GTCA.id("block/multiblock/space_assembler"), "Space Assembler MK III");

    public static MultiblockMachineDefinition registerSpaceAssembler(String name, int tier,
                                                                     Supplier<? extends Block> casing,
                                                                     ResourceLocation casingTexture,
                                                                     ResourceLocation overlayModel,
                                                                     String lang) {

        if (tier != LuV && tier != ZPM && tier != UV) return null;

        return REGISTRATE.multiblock(name, holder -> new SpaceAssemblerMachine(holder, tier))
                .langValue(lang)
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(GTCARecipeTypes.SPACE_ASSEMBLER)
                .appearanceBlock(GTCABlocks.SPACE_ELEVATOR_CASING)
                .recipeModifier(OC_NON_PERFECT_SUBTICK)
                .pattern(definition ->
                        FactoryBlockPattern.start()
                                .aisle("C", "C", "C", "C", "C")
                                .aisle("C", "C", "C", "X", "C")
                                .where('X', Predicates.controller(Predicates.blocks(definition.get())))
                                .where('C', blocks(GTCABlocks.SPACE_ELEVATOR_CASING.get()).or(Predicates.abilities(
                                        PartAbility.IMPORT_ITEMS,
                                        PartAbility.EXPORT_ITEMS,
                                        PartAbility.IMPORT_FLUIDS
                                )).or(Predicates.autoAbilities(false, false, false)))
                                .build())
                .tooltips(
                        Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Space Assembler Module"),
                        Component.translatable("gtca.machine.space_assembler_desc.tooltip")
                )
                .workableCasingModel(
                        casingTexture,
                        overlayModel
                )
                .register();
    }

    public static final MultiblockMachineDefinition SPACE_ELEVATOR =
            REGISTRATE.multiblock("space_elevator", holder -> new SpaceElevatorMachine(holder))
            .langValue("Space Elevator")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.SPACE_ELEVATOR)
                    .additionalDisplay((machine, list) -> {
                        if (machine instanceof SpaceElevatorMachine se) {
                            SpaceElevatorDisplay.addDisplay(se, list);
                        }
                    })
            .appearanceBlock(GTCABlocks.SPACE_ELEVATOR_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                    .aisle("###############BBBBB###############", "###############CC#CC###############", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("############BBBBBBBBBBB############", "############CCCCC#CCCCC############", "###############D###D###############", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("##########BBBBBBBBBBBBBBB##########", "##########CCCCCCC#CCCCCCC##########", "############DDDDE#EDDDD############", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("########BBBBBBBBBBBBBBBBBBB########", "#########CCCCCCD###DCCCCCC#########", "##########DD###DE#ED###DD##########", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("#######BBBBBBBBBBBBBBBBBBBBB#######", "#########CCC###########CCC#########", "###############D###D###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("######BBBBBBBBBBBBBBBBBBBBBBB######", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("#####BBBBBBBBBBBBBBBBBBBBBBBBB#####", "###################################", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "################E#E################", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("####BBBBBBBBBBBBBBBBBBBBBBBBBBB####", "#######E###################E#######", "#######E###################E#######", "###################################", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("###BBBBBBBBBBBBZ#Z#ZBBBBBBBBBBBB###", "###############Z#Z#Z###############", "########E######Z#Z#Z######E########", "########E######Z#Z#Z######E########", "########E######Z#Z#Z######E########", "###################################", "###################################", "###################################", "###################################", "##############FDE#EDF##############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("###BBBBBBBBBBBBZ#Z#ZBBBBBBBBBBBB###", "###CC##########Z#Z#Z##########CC###", "###############Z#Z#Z###############", "#########E#####Z#Z#Z#####E#########", "#########E#####Z#Z#Z#####E#########", "#########E###############E#########", "###################################", "###################################", "###################################", "############FF#######FF############", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("##BBBBBBBBBBBBB#####BBBBBBBBBBBBB##", "##CCC##########C#C#C##########CCC##", "###D###########################D###", "###################################", "##########E#############E##########", "##########E#############E##########", "##########E#############E##########", "##########E#############E##########", "##########E#############E##########", "##########EF###########FE##########", "###################################", "###################################", "###################################", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("##BBBBBBBBBBBBB#####BBBBBBBBBBBBB##", "##CCC##########C#C#C##########CCC##", "###D###########################D###", "###################################", "###################################", "###################################", "###################################", "###################################", "###########E###########E###########", "##########FE###########EF##########", "###########E###########E###########", "###########E###########E###########", "###########E###########E###########", "###########E###########E###########", "###################################", "###################################", "###################################", "##############F#####F##############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#", "#CCC##########CCCCCCC##########CCC#", "##D#############################D##", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "#########F###############F#########", "###################################", "###################################", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############EF#######FE############", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#", "#CCC#########CCCCCCCCC#########CCC#", "##D#############################D##", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "#########F###############F#########", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "#############E#######E#############", "############FE#######EF############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "###################################", "###################################", "###################################", "###################################", "###################################", "##############F#####F##############", "###################################", "###################################", "###################################", "###################################", "###################################", "################CCC################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#", "#CCC########CCCCCCCCCCC########CCC#", "##D#############OOO#############D##", "################DDD################", "################DDD################", "###################################", "###################################", "###################################", "###################################", "########F#################F########", "###################################", "###################################", "###################################", "###################################", "################CCC################", "###################################", "###################################", "###########F###########F###########", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "################CCC################", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "#############FE#####EF#############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "###############C###C###############", "#################E#################", "#################E#################", "#################E#################", "#################E#################", "################CCC################")
                	.aisle("BBBBBBBBZZ##BBBOOOOOBBB##ZZBBBBBBBB", "CCCD####ZZCCCCCOOOOOCCCCCZZ####DCCC", "#DDDD###ZZ#####OOGDO#####ZZ###DDDD#", "####DD##ZZ#####DDGDD#####ZZ##DD####", "#####DD#ZZ#####DDGDD#####ZZ#DD#####", "######D########D#G#D########D######", "######DD#######D#G#D#######DD######", "#######D#######D#G#D#######D#######", "#######D#######D#G#D#######D#######", "#######DD######DDGDD######DD#######", "########D#######DGD#######D########", "########D#######DGD#######D########", "########DD######DGD######DD########", "#########D######DGD######D#########", "#########D#####CDGDC#####D#########", "#########DD######G######DD#########", "##########D######G######D##########", "##########D######G######D##########", "##########DD#####G#####DD##########", "###########D#####G#####D###########", "###########D#####G#####D###########", "###########D#####G#####D###########", "###########D#####G#####D###########", "###########D#####G#####D###########", "###########D###C#D#C###D###########", "###########D#####D#####D###########", "###########D#####D#####D###########", "###########DD####D####DD###########", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "#################D#################", "##############C##D##C##############", "#################D#################", "#################D#################", "#################D#################", "#################D#################", "###############C#D#C###############")
                	.aisle("BBBBBBBB####BBBOOOOOBBB####BBBBBBBB", "CCC#########CCCOOOOOCCC#########CCC", "##EE##########OO###OO##########EE##", "###EEE########DD###DD########EEE###", "####EEE#######DD###DD#######EEE####", "#####EE#####################EE#####", "#####EEE###################EEE#####", "######EE###################EE######", "######EE###################EE######", "######EEE######D###D######EEE######", "#######EE######D###D######EE#######", "#######EE######D###D######EE#######", "########EE#####D###D#####EE########", "########EE#####D###D#####EE########", "#########E####CD###DC####E#########", "#########EE#############EE#########", "#########EE#############EE#########", "#########EE#############EE#########", "##########EE###########EE##########", "##########EE###########EE##########", "###########E###########E###########", "###########E###########E###########", "###########E###########E###########", "###########E###########E###########", "###########E##C#####C##E###########", "###########E###########E###########", "###########E###########E###########", "###########EE#########EE###########", "###########EE#########EE###########", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "#############C#######C#############", "###################################", "###################################", "###################################", "###################################", "##############C#####C##############")
                	.aisle("BBBBBBBBZZ##BBBOOOOOBBB##ZZBBBBBBBB", "########ZZCCCCCOOOOOCCCCCZZ########", "########ZZ####DG###GO####ZZ########", "########ZZ####DG###GD####ZZ########", "########ZZ####DG###GD####ZZ########", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "##############CG###GC##############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "###############G###G###############", "##############CD###DC##############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "###############D###D###############", "#############C#D###D#C#############", "##############ED###DE##############", "##############ED###DE##############", "##############ED###DE##############", "##############ED###DE##############", "##############CD###DC##############")
                	.aisle("BBBBBBBB####BBBOOOOOBBB####BBBBBBBB", "CCC#########CCCOOOOOCCC#########CCC", "##EE##########OO###OO##########EE##", "###EEE########DD###DD########EEE###", "####EEE#######DD###DD#######EEE####", "#####EE#####################EE#####", "#####EEE###################EEE#####", "######EE###################EE######", "######EE###################EE######", "######EEE######D###D######EEE######", "#######EE######D###D######EE#######", "#######EE######D###D######EE#######", "########EE#####D###D#####EE########", "########EE#####D###D#####EE########", "#########E####CD###DC####E#########", "#########EE#############EE#########", "#########EE#############EE#########", "#########EE#############EE#########", "##########EE###########EE##########", "##########EE###########EE##########", "###########E###########E###########", "###########E###########E###########", "###########E###########E###########", "###########E###########E###########", "###########E##C#####C##E###########", "###########E###########E###########", "###########E###########E###########", "###########EE#########EE###########", "###########EE#########EE###########", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "#############C#######C#############", "###################################", "###################################", "###################################", "###################################", "##############C#####C##############")
                	.aisle("BBBBBBBBZZ##BBBOOOOOBBB##ZZBBBBBBBB", "CCCD####ZZCCCCCOOOOOCCCCCZZ####DCCC", "#DDDD###ZZ#####OOGOO#####ZZ###DDDD#", "####DD##ZZ#####DDGDD#####ZZ##DD####", "#####DD#ZZ#####DDGDD#####ZZ#DD#####", "######D########D#G#D########D######", "######DD#######D#G#D#######DD######", "#######D#######D#G#D#######D#######", "#######D#######D#G#D#######D#######", "#######DD######DDGDD######DD#######", "########D#######DGD#######D########", "########D#######DGD#######D########", "########DD######DGD######DD########", "#########D######DGD######D#########", "#########D#####CDGDC#####D#########", "#########DD######G######DD#########", "##########D######G######D##########", "##########D######G######D##########", "##########DD#####G#####DD##########", "###########D#####G#####D###########", "###########D#####G#####D###########", "###########D#####G#####D###########", "###########D#####G#####D###########", "###########D#####G#####D###########", "###########D###C#D#C###D###########", "###########D#####D#####D###########", "###########D#####D#####D###########", "###########DD####D####DD###########", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "############D####D####D############", "#################D#################", "##############C##D##C##############", "#################D#################", "#################D#################", "#################D#################", "#################D#################", "###############C#D#C###############")
                	.aisle("#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#", "#CCC########CCCCCCCCCCC########CCC#", "##D#############OOO#############D##", "################DPD################", "################DDD################", "###################################", "###################################", "###################################", "###################################", "########F#################F########", "###################################", "###################################", "###################################", "###################################", "################CCC################", "###################################", "###################################", "###########F###########F###########", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "################CCC################", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "#############FE#####EF#############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "##############E#####E##############", "###############C###C###############", "#################E#################", "#################E#################", "#################E#################", "#################E#################", "################CCC################")
                	.aisle("#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#", "#CCC#########CCCCCCCCC#########CCC#", "##D#############################D##", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "#########F###############F#########", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "#############E#######E#############", "############FE#######EF############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "#############E#######E#############", "###################################", "###################################", "###################################", "###################################", "###################################", "##############F#####F##############", "###################################", "###################################", "###################################", "###################################", "###################################", "################CCC################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("#BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#", "#CCC##########CCCCCCC##########CCC#", "##D#############################D##", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "#########F###############F#########", "###################################", "###################################", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############E#########E############", "############EF#######FE############", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("##BBBBBBBBBBBBB#####BBBBBBBBBBBBB##", "##CCC##########C#C#C##########CCC##", "###D###########################D###", "###################################", "###################################", "###################################", "###################################", "###################################", "###########E###########E###########", "##########FE###########EF##########", "###########E###########E###########", "###########E###########E###########", "###########E###########E###########", "###########E###########E###########", "###################################", "###################################", "###################################", "##############F#####F##############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("##BBBBBBBBBBBBB#####BBBBBBBBBBBBB##", "##CCC##########C#C#C##########CCC##", "###D###########################D###", "###################################", "##########E#############E##########", "##########E#############E##########", "##########E#############E##########", "##########E#############E##########", "##########E#############E##########", "##########EF###########FE##########", "###################################", "###################################", "###################################", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("###BBBBBBBBBBBBZ#Z#ZBBBBBBBBBBBB###", "###CC##########Z#Z#Z##########CC###", "###############Z#Z#Z###############", "#########E#####Z#Z#Z#####E#########", "#########E#####Z#Z#Z#####E#########", "#########E###############E#########", "###################################", "###################################", "###################################", "############FF#######FF############", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("###BBBBBBBBBBBBZ#Z#ZBBBBBBBBBBBB###", "###############Z#Z#Z###############", "########E######Z#Z#Z######E########", "########E######Z#Z#Z######E########", "########E######Z#Z#Z######E########", "###################################", "###################################", "###################################", "###################################", "##############FDE#EDF##############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("####BBBBBBBBBBBBBBBBBBBBBBBBBBB####", "#######E###################E#######", "#######E###################E#######", "###################################", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("#####BBBBBBBBBBBBBBBBBBBBBBBBB#####", "###################################", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "################E#E################", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("######BBBBBBBBBBBBBBBBBBBBBBB######", "###################################", "###################################", "###############DE#ED###############", "###############DE#ED###############", "################E#E################", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("#######BBBBBBBBBBBBBBBBBBBBB#######", "#########CCC###########CCC#########", "###############D###D###############", "###############DE#ED###############", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("########BBBBBBBBBBBBBBBBBBB########", "#########CCCCCCD###DCCCCCC#########", "##########DD###DE#ED###DD##########", "################E#E################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("##########BBBBBBBBBBBBBBB##########", "##########CCCCCCC#CCCCCCC##########", "############DDDDE#EDDDD############", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("############BBBBBBBBBBB############", "############CCCCC#CCCCC############", "###############D###D###############", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                	.aisle("###############BBBBB###############", "###############CC#CC###############", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################", "###################################")
                            .where('#', Predicates.any())
                            .where('B', blocks(GTCABlocks.ULTRA_HIGH_STRENGTH_CONCRETE_FLOOR.get()))
                            .where('D', blocks(GTCABlocks.SPACE_ELEVATOR_CASING.get()))
                            .where('Z', Predicates.any())
                            .where('G', blocks(
                                    GTCABlocks.SPACE_ELEVATOR_MOTOR_MK1.get(),
                                    GTCABlocks.SPACE_ELEVATOR_MOTOR_MK2.get(),
                                    GTCABlocks.SPACE_ELEVATOR_MOTOR_MK3.get(),
                                    GTCABlocks.SPACE_ELEVATOR_MOTOR_MK4.get(),
                                    GTCABlocks.SPACE_ELEVATOR_MOTOR_MK5.get()))
                            .where('O', blocks(GTCABlocks.SPACE_ELEVATOR_CASING.get()).or(autoAbilities(definition.getRecipeTypes())).or(Predicates.autoAbilities(false, false, false)))
                            .where('E', blocks(GTCABlocks.SPACE_ELEVATOR_SUPPORT_STRUCTURE.get()))
                            .where('C', blocks(GTCABlocks.SPACE_ELEVATOR_INTERNAL_STRUCTURE.get()))
                            .where('F', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, Neutronium)))
                            .where('P', Predicates.controller(Predicates.blocks(definition.get())))
                            .build()
            )

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Space Elevator"),
                    Component.translatable("gtca.machine.space_elevator_desc.tooltip"),
                    Component.translatable("gtca.machine.space_elevator1_desc.tooltip"),
                    Component.translatable("gtca.machine.space_elevator2_desc.tooltip"),
                    Component.translatable("gtca.machine.space_elevator3_desc.tooltip")
            )


            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCA.id("block/casing/space_elevator_base_casing"),
                    GTCA.id("block/multiblock/space_elevator")).andThen(d -> d.addDynamicRenderer(GTCADynamicRenderHelpers::getSpaceElevatorRenderer)))
            .hasBER(true)
            .register();

    public static final MultiblockMachineDefinition CLARIFIER_PURIFICATION_UNIT = REGISTRATE.multiblock("clarifier_purification_unit", WorkableElectricMultiblockMachine::new)
            .langValue("Clarifier Purification Unit")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.CLARIFIER_PURIFICATION_UNIT)
            .appearanceBlock(GTCABlocks.REINFORCED_STERILE_WATER_PLANT_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("###BBBBB###", "###BCBCB###", "###BBBBB###", "###########")
                        	.aisle("##BBBBBBB##", "##B#####B##", "##B#####B##", "###########")
                        	.aisle("#BBBBBBBBB#", "#B#######B#", "#B#######B#", "###########")
                        	.aisle("BBBBDDDBBBB", "B###EEE###B", "B#########B", "###########")
                        	.aisle("BBBDDDDDBBB", "F##E###E##F", "B#########B", "####EEE####")
                        	.aisle("BBBDDDDDBBB", "B##E#G#E##B", "B####G####B", "EEEEEGE####")
                        	.aisle("BBBDDDDDBBB", "F##E###E##F", "B#########B", "####EEE####")
                        	.aisle("BBBBDDDBBBB", "B###EEE###B", "B#########B", "###########")
                        	.aisle("#BBBBBBBBB#", "#B#######B#", "#B#######B#", "###########")
                        	.aisle("##BBBBBBB##", "##B#####B##", "##B#####B##", "###########")
                        	.aisle("###BBBBB###", "###BFHFB###", "###BBBBB###", "###########")
                            .where('#', Predicates.any())
                            .where('B', blocks(GTCABlocks.REINFORCED_STERILE_WATER_PLANT_CASING.get()))
                            .where('D', blocks(GTBlocks.FILTER_CASING.get()))
                            .where('G', blocks(GTBlocks.CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                            .where('E', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, Iridium)))
                            .where('H', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('F', blocks(GTCABlocks.REINFORCED_STERILE_WATER_PLANT_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), true, false, true, false, true, false))
                                    .or(Predicates.autoAbilities(true, false, false)))
                            .where('C', blocks(GTCABlocks.REINFORCED_STERILE_WATER_PLANT_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), false, false, false, true, false, true)))
                            .build()
            )

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Purification Unit"),
                    Component.translatable("gtca.machine.purification_unit_desc.tooltip"),
                    Component.translatable("gtca.machine.purification_unit1_desc.tooltip"),
                    Component.translatable("gtca.machine.purification_unit2_desc.tooltip"),
                    Component.translatable("gtca.machine.purification_unit3_desc.tooltip")
            )
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCA.id("block/casing/purification/reinforced_sterile_water_platn_casing"),
                    GTCA.id("block/multiblock/purification_multiblock")).andThen(d -> d.addDynamicRenderer(GTCADynamicRenderHelpers::getPurificationWaterRenderer)))
            .hasBER(true)
            .register();

    public static final MultiblockMachineDefinition OZONATION_PURIFICATION_UNIT = REGISTRATE.multiblock("ozonation_purification_unit", WorkableElectricMultiblockMachine::new)
            .langValue("Ozonation Purification Unit")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.OZONATION_PURIFICATION_UNIT)
            .appearanceBlock(GTCABlocks.INERT_FILTRATION_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                    .aisle("#####BHB#", "#####BHB#", "#####BHB#", "#####BHB#", "#####BBB#", "#####BBB#", "######B##", "######B##", "#########", "#########", "#########")
                	.aisle("C###BBBBB", "C###B###B", "C###B###B", "DDDDB###B", "DEEDB###B", "DDDDB###B", "#####B#B#", "#####B#B#", "######B##", "######B##", "######B##")
                	.aisle("####BBBBF", "##GGB###F", "##G#B###F", "DDDDB###B", "D##DB###B", "DEEDB###B", "####B###B", "####B###B", "#####B#B#", "#####B#B#", "#####BBB#")
                	.aisle("C###BBBBB", "C###B#E#B", "C###B#E#B", "DDDDB#E#B", "DEEDB###B", "DDDDB###B", "#####B#B#", "#####B#B#", "######B##", "######B##", "######B##")
                	.aisle("#####BIB#", "#####B#B#", "#####B#B#", "#####B#B#", "#####BBB#", "#####BBB#", "######B##", "######B##", "#########", "#########", "#########")
                            .where('#', Predicates.any())
                            .where('B', blocks(GTCABlocks.INERT_FILTRATION_CASING.get()))
                            .where('D', blocks(GTCABlocks.REACTIVE_GAS_CONTANTMENT_CASING.get()))
                            .where('E', blocks(GTCABlocks.BORSILICATE_YTTRIUM_GLASS.get()))
                            .where('G', blocks(GTBlocks.CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                            .where('C', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, TungstenSteel)))
                            .where('I', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('H', blocks(GTCABlocks.INERT_FILTRATION_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), true, false, false, false, true, false))
                                    .or(Predicates.autoAbilities(true, false, false)))
                            .where('F', blocks(GTCABlocks.INERT_FILTRATION_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), false, false, false, true, false, true)))
                            .build()
            )

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Purification Unit"),
                    Component.translatable("gtca.machine.ozonation_purification_unit_desc.tooltip"),
                    Component.translatable("gtca.machine.ozonation_purification_unit1_desc.tooltip"),
                    Component.translatable("gtca.machine.ozonation_purification_unit2_desc.tooltip"),
                    Component.translatable("gtca.machine.ozonation_purification_unit3_desc.tooltip")
            )
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCA.id("block/casing/purification/inert_filtration_casing"),
                    GTCA.id("block/multiblock/purification_multiblock")))
            .register();

    public static final MultiblockMachineDefinition FLOCCULATION_PURIFICATION_UNIT = REGISTRATE.multiblock("flocculation_purification_unit", WorkableElectricMultiblockMachine::new)
            .langValue("Flocculation Purification Unit")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.FLOCCULATION_PURIFICATION_UNIT)
            .appearanceBlock(GTCABlocks.SLICK_STERILE_FLOCCULATION_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                        	.aisle("AA#####AA", "AA#####AA", "AA#####AA", "AA#####AA", "AA#####AA")
                        	.aisle("ABBCCCBBA", "ABBBBBBBA", "ABBBBBBBA", "ADD###DDA", "A#######A")
                        	.aisle("#BEEEEEB#", "#BD###DB#", "#BD###DB#", "#DD###DD#", "#########")
                        	.aisle("#BEFFFEB#", "#G#####G#", "#B#####B#", "#########", "#########")
                        	.aisle("#BEFFFEB#", "#G#####G#", "#B#####B#", "#########", "#########")
                        	.aisle("#BEFFFEB#", "#G#####G#", "#B#####B#", "#########", "#########")
                        	.aisle("#BEEEEEB#", "#B#####B#", "#B#####B#", "#########", "#########")
                        	.aisle("#BBBBBBB#", "#BHHIHHB#", "#BBBBBBB#", "#########", "#########")
                            .where('#', Predicates.any())
                            .where('A', blocks(GTCABlocks.STERILE_WATER_PLANT_CASING.get()))
                            .where('B', blocks(GTCABlocks.SLICK_STERILE_FLOCCULATION_CASING.get()))
                            .where('E', blocks(GTCABlocks.REINFORCED_STERILE_WATER_PLANT_CASING.get()))
                            .where('G', blocks(GTCABlocks.BORSILICATE_YTTRIUM_GLASS.get()))
                            .where('F', blocks(GTBlocks.FILTER_CASING.get()))
                            .where('D', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, Adamantium)))
                            .where('I', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('H', blocks(GTCABlocks.SLICK_STERILE_FLOCCULATION_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), true, false, false, false, true, false))
                                    .or(Predicates.autoAbilities(true, false, false)))
                            .where('C', blocks(GTCABlocks.SLICK_STERILE_FLOCCULATION_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), false, false, false, true, false, true)))

                            .build()
            )

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Purification Unit"),
                    Component.translatable("gtca.machine.flocculation_purification_unit_desc.tooltip"),
                    Component.translatable("gtca.machine.flocculation_purification_unit1_desc.tooltip"),
                    Component.translatable("gtca.machine.flocculation_purification_unit2_desc.tooltip"),
                    Component.translatable("gtca.machine.flocculation_purification_unit3_desc.tooltip")
            )
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCA.id("block/casing/purification/slick_sterile_flocculation_casing"),
                    GTCA.id("block/multiblock/purification_multiblock")))
            .register();

    public static final MultiblockMachineDefinition PH_NEUTRALIZATION_PURIFICATION_UNIT = REGISTRATE.multiblock("ph_neutralization_purification_unit", WorkableElectricMultiblockMachine::new)
            .langValue("Ph Neutralization Purification Unit")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.PH_NEUTRALIZATION_PURIFICATION_UNIT)
            .appearanceBlock(GTCABlocks.STABILIZED_WATER_PLANT_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                        	.aisle("ABBBA#####ABBBA", "ABCBA#####ABCBA", "ABCBA#####ABCBA", "ABCBA#####ABCBA", "ABCBA#####ABCBA", "ABBBA#####ABBBA", "###############")
                        	.aisle("BBBBB#####BBBBB", "B###BDDDDDB###B", "B###B#####B###B", "B###B#####B###B", "B###B#####B###B", "B###B#####B###B", "#BBB#######BBB#")
                        	.aisle("BBBBBEEEEEBBBBB", "C#############C", "C###BFFFFFB###C", "C###B#####B###C", "C###B#####B###C", "B###B#####B###B", "#BBB#######BBB#")
                        	.aisle("BBBBB#####BBBBB", "B###BEEGEEB###B", "B###B#####B###B", "B###B#####B###B", "B###B#####B###B", "B###B#####B###B", "#BBB#######BBB#")
                        	.aisle("ABBBA#####ABBBA", "ABCBA#####ABCBA", "ABCBA#####ABCBA", "ABCBA#####ABCBA", "ABCBA#####ABCBA", "ABBBA#####ABBBA", "###############")
                            .where('#', Predicates.any())
                            .where('A', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, NaquadahAlloy)))
                            .where('B', blocks(GTCABlocks.INERT_NEUTRALIZATION_INTERT_CASING.get()))
                            .where('E', blocks(GTCABlocks.STABILIZED_WATER_PLANT_CASING.get()))
                            .where('C', blocks(GTCABlocks.BORSILICATE_YTTRIUM_GLASS.get()))
                            .where('G', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('F', blocks(GTCABlocks.INERT_NEUTRALIZATION_INTERT_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), true, false, true, false, true, false))
                                    .or(Predicates.autoAbilities(true, false, false)))
                            .where('D', blocks(GTCABlocks.INERT_NEUTRALIZATION_INTERT_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), false, false, false, true, false, true)))

                            .build()
            )

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Purification Unit"),
                    Component.translatable("gtca.machine.ph_neutralization_purification_unit_desc.tooltip"),
                    Component.translatable("gtca.machine.ph_neutralization_purification_unit1_desc.tooltip"),
                    Component.translatable("gtca.machine.ph_neutralization_purification_unit2_desc.tooltip"),
                    Component.translatable("gtca.machine.ph_neutralization_purification_unit3_desc.tooltip")
            )
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCA.id("block/casing/purification/stabilized_water_plant_casing"),
                    GTCA.id("block/multiblock/purification_multiblock")))
            .register();

    public static final MultiblockMachineDefinition EXTREME_TEMPERATURE_FLOCCULATION_PURIFICATION_UNIT = REGISTRATE.multiblock("extreme_temperature_purification_unit", WorkableElectricMultiblockMachine::new)
            .langValue("Extreme Temperature Purification Unit")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.EXTREME_TEMPERATURE_FLOCCULATION_PURIFICATION_UNIT)
            .appearanceBlock(GTCABlocks.HEAT_RESISTANT_TRINIUM_PLATED_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#############BBBBB#####")
                            .aisle("###########BBBBBBBBB###", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#############BBBBB#####", "###########BB#####BB###")
                            .aisle("##########BBBBBBBBBBB##", "##########B#########B##", "##########B#########B##", "##########B#########B##", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "###########BB#####BB###", "##########B#########B##")
                            .aisle("#########BBBBBBBBBBBBB#", "#########B###########B#", "#########B###########B#", "#########B###########B#", "##########B#########B##", "##########B#########B##", "##########B#########B##", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "##########B#########B##", "#########B###########B#")
                            .aisle("#########BBBBBBBBBBBBB#", "#########B###########B#", "#########B###########B#", "#########B###########B#", "##########B#########B##", "##########B#########B##", "##########B#########B##", "###########B#######B###", "###########B#######B###", "###########B#######B###", "###########B#######B###", "###########B#######B###", "###########B#######B###", "##########B#########B##", "#########B###########B#")
                            .aisle("DEEED###BBBBBBBBBBBBBBB", "D###D###B#############B", "D###D###B#############B", "D###D###B#############B", "D###D####B###########B#", "D###D####B###########B#", "D###D####B###########B#", "D###D#####B#########B##", "D###D#####B#########B##", "FFFFF#####B#########B##", "##########B#########B##", "##########B#########B##", "##########B#########B##", "#########B###########B#", "########B#############B")
                            .aisle("EGGGF###BBBBBBBBBBBBBBB", "#HHHFFFFB#############B", "#HHH####B#############B", "#HHH####B#############B", "#HHH#####B###########B#", "#HHH#####B###########B#", "#HHH#####B###########B#", "#HHH######B#########B##", "#HHH######B#########B##", "MFFFF#####B#########B##", "##########B#########B##", "##########B#########B##", "##########B#########B##", "#########B###########B#", "########B#############B")
                            .aisle("EGGGFFFFBBBBBBBBBBBBBBB", "#HIH####B#############B", "#HIHFFFFB#############B", "#HIH####B#############B", "#HIH#####B###########B#", "#HIH#####B###########B#", "#HIH#####B###########B#", "#HIH######B#########B##", "#HIH######B#########B##", "MFFFF#####B#########B##", "##########B#########B##", "##########B#########B##", "##########B#########B##", "#########B###########B#", "########B#############B")
                            .aisle("EGGGF###BBBBBBBBBBBBBBB", "#HHHFFFFB#############B", "#HHH####B#############B", "#HHH####B#############B", "#HHH#####B###########B#", "#HHH#####B###########B#", "#HHH#####B###########B#", "#HHH######B#########B##", "#HHH######B#########B##", "MFFFF#####B#########B##", "##########B#########B##", "##########B#########B##", "##########B#########B##", "#########B###########B#", "########B#############B")
                            .aisle("DEJED###BBBBBBBBBBBBBBB", "D###D###B#############B", "D###D###B#############B", "D###D###B#############B", "D###D####B###########B#", "D###D####B###########B#", "D###D####B###########B#", "D###D#####B#########B##", "D###D#####B#########B##", "FFFFF#####B#########B##", "##########B#########B##", "##########B#########B##", "##########B#########B##", "#########B###########B#", "########B#############B")
                            .aisle("#########BBBBBBBBBBBBB#", "#########B###########B#", "#########B###########B#", "#########B###########B#", "##########B#########B##", "##########B#########B##", "##########B#########B##", "###########B#######B###", "###########B#######B###", "###########B#######B###", "###########B#######B###", "###########B#######B###", "###########B#######B###", "##########B#########B##", "#########B###########B#")
                            .aisle("#########BBBBBBBBBBBBB#", "#########B###########B#", "#########B###########B#", "#########B###########B#", "##########B#########B##", "##########B#########B##", "##########B#########B##", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "##########B#########B##", "#########B###########B#")
                            .aisle("##########BBBBBBBBBBB##", "##########B#########B##", "##########B#########B##", "##########B#########B##", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "###########BB#####BB###", "##########B#########B##")
                            .aisle("###########BBBBBBBBB###", "###########BB#####BB###", "###########BB#####BB###", "###########BB#####BB###", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#############BBBBB#####", "###########BB#####BB###")
                            .aisle("#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#############BBBBB#####", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#######################", "#############BBBBB#####")
                            .where('#', Predicates.any())
                            .where('D', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, StellarAlloy)))
                            .where('F', blocks(GTCABlocks.HEAT_RESISTANT_TRINIUM_PLATED_CASING.get()))
                            .where('G', blocks(GTBlocks.SUPERCONDUCTING_COIL.get()))
                            .where('I', blocks(GTCABlocks.NEONITE.get()))
                            .where('H', blocks(GTCABlocks.BORSILICATE_REINFORCED_IRIDIUM_GLASS.get()))
                            .where('J', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('B', blocks(GTCABlocks.REINFORCED_STERILE_WATER_PLANT_CASING.get()))
                            .where('E', blocks(GTCABlocks.HEAT_RESISTANT_TRINIUM_PLATED_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), true, false, false, false, true, false))
                                    .or(Predicates.autoAbilities(true, false, false)))
                            .where('M', blocks(GTCABlocks.HEAT_RESISTANT_TRINIUM_PLATED_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), false, false, false, false, false, true)))

                            .build()
            )

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Purification Unit"),
                    Component.translatable("gtca.machine.extreme_temperature_purification_unit_desc.tooltip"),
                    Component.translatable("gtca.machine.extreme_temperature_purification_unit1_desc.tooltip"),
                    Component.translatable("gtca.machine.extreme_temperature_purification_unit2_desc.tooltip"),
                    Component.translatable("gtca.machine.extreme_temperature_purification_unit3_desc.tooltip")
            )
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCA.id("block/casing/purification/heat_resistant_trinium_plated_casing"),
                    GTCA.id("block/multiblock/purification_multiblock")))
            .register();

    public static final MultiblockMachineDefinition HIGH_ENERGY_LASER_PURIFICATION_UNIT = REGISTRATE.multiblock("high_energy_laser_purification_unit", WorkableElectricMultiblockMachine::new)
            .langValue("High Energy Laser Purification Unit")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.HIGH_ENERGY_LASER_PURIFICATION_UNIT)
            .appearanceBlock(GTCABlocks.NAQUADRIA_REINFORCED_WATERPLANT_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                        	.aisle("#####BBB#####", "#####CCC#####", "#############", "#############", "#############", "#############", "#############", "#####CCC#####", "#############")
                        	.aisle("###DDDDDDD###", "###CCEEECC###", "#####EEE#####", "#####EEE#####", "#####EEE#####", "#####EEE#####", "#####EEE#####", "###CCDDDCC###", "#####DDD#####")
                        	.aisle("#DDDDDDDDDDD#", "#CCEEE#EEECC#", "###EEE#EEE###", "###EEE#EEE###", "###EEE#EEE###", "###EEE#EEE###", "###EEE#EEE###", "#CCDDDFDDDCC#", "###DDDDDDD###")
                        	.aisle("DDDDDDDDDDDDD", "CEEE#####EEEC", "#EEE#####EEE#", "#EEE#####EEE#", "#EEE#####EEE#", "#EEE#####EEE#", "#EEE#####EEE#", "CDDDFFFFFDDDC", "#DDDDDDDDDDD#")
                        	.aisle("DDDDDDDDDDDDD", "CE#########EC", "#E#########E#", "#E#########E#", "#E#########E#", "#E#########E#", "#E#########E#", "CDFFFFFFFFFDC", "#DDDDDGDDDDD#")
                        	.aisle("DDDDDDDDDDDDD", "CEEE#####EEEC", "#EEE#####EEE#", "#EEE#####EEE#", "#EEE#####EEE#", "#EEE#####EEE#", "#EEE#####EEE#", "CDDDFFFFFDDDC", "#DDDDDDDDDDD#")
                        	.aisle("#DDDDDDDDDDD#", "#CCEEE#EEECC#", "###EEE#EEE###", "###EEE#EEE###", "###EEE#EEE###", "###EEE#EEE###", "###EEE#EEE###", "#CCDDDFDDDCC#", "###DDDDDDD###")
                        	.aisle("###GGDDDGG###", "###CCEEECC###", "#####EEE#####", "#####EEE#####", "#####EEE#####", "#####EEE#####", "#####EEE#####", "###CCDDDCC###", "#####DDD#####")
                        	.aisle("#####GHG#####", "#####CCC#####", "#############", "#############", "#############", "#############", "#############", "#####CCC#####", "#############")
                            .where('#', Predicates.any())
                            .where('C', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, StellarAlloy)))
                            .where('D', blocks(GTCABlocks.NAQUADRIA_REINFORCED_WATERPLANT_CASING.get()))
                            .where('F', blocks(GTCABlocks.HIGH_ENERGY_ULTRAVIOLET_EMITTER_CASING.get()))
                            .where('E', blocks(GTCABlocks.NEUTRONIUM_FUSED_GLASS.get()))
                            .where('H', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('G', blocks(GTCABlocks.NAQUADRIA_REINFORCED_WATERPLANT_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), true, false, true, false, true, false))
                                    .or(Predicates.autoAbilities(true, false, false)))
                            .where('B', blocks(GTCABlocks.NAQUADRIA_REINFORCED_WATERPLANT_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), false, false, false, false, false, true)))
                            .build()
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Purification Unit"),
                    Component.translatable("gtca.machine.high_energy_laser_purification_unit_desc.tooltip"),
                    Component.translatable("gtca.machine.high_energy_laser_purification_unit1_desc.tooltip"),
                    Component.translatable("gtca.machine.high_energy_laser_purification_unit2_desc.tooltip"),
                    Component.translatable("gtca.machine.high_energy_laser_purification_unit3_desc.tooltip")
            )
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCA.id("block/casing/purification/naquadria_renforced_waterplant_casing"),
                    GTCA.id("block/multiblock/purification_multiblock")))
            .register();

    public static final MultiblockMachineDefinition RESIDUAL_DECONTAMINANT_DEGASSER_PURIFICATION_UNIT  = REGISTRATE.multiblock("residual_decontamiant_degasser_purification_unit", WorkableElectricMultiblockMachine::new)
            .langValue("Residual Decontamiant Degasser Purification Unit")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.RESIDUAL_DECONTAMINANT_DEGASSER_PURIFICATION_UNIT)
            .appearanceBlock(GTCABlocks.HEAT_RESISTANT_TRINIUM_PLATED_CASING)
            .recipeModifier(OC_NON_PERFECT_SUBTICK)
            .pattern(definition ->
                    FactoryBlockPattern.start()
	.aisle("######BBBBB######", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "######BBBBB######")
	.aisle("####BBBBBBBBB####", "######BBBBBCC####", "#########CC######", "########C########", "######CC#########", "#####C###########", "#################", "#################", "#################", "###########C#####", "#########CC######", "########C########", "######CC#########", "#####C###########", "#################", "#################", "#################", "###########C#####", "#########CC######", "########C########", "######CC#########", "#####C###########", "#################", "######BBBBB######", "####BBBBBBBBB####")
	.aisle("##BBBBBBBBBBBBB##", "####BBBBBBBBB####", "######DDDDD######", "######DDDDD######", "######DDDDD######", "#######DDD#######", "####C###D########", "#################", "############C####", "#################", "#################", "#################", "#################", "#################", "####C############", "#################", "############C####", "#################", "########D########", "#######DDD#######", "######DDDDD######", "######DDDDD######", "###CC#DDDDD######", "####BBBBBBBBBC###", "###BBBBBBBBBBB###")
	.aisle("##BBBBBBBBBBBBB##", "###BBBBBBBBBBB###", "####DD#####DD####", "####DD#####DD####", "#####D#####D#####", "#####DD###DD#####", "#####DDD#DDD#####", "###C##DDDDD##C###", "#######DDD#######", "#################", "#################", "#################", "#################", "#################", "#################", "###C#########C###", "#######DDD#######", "######DDDDD######", "#####DDD#DDD#####", "#####DD###DD#####", "#####D#####D#####", "####DD#####DD####", "####DD#####DD#C##", "##CBBBBBBBBBBB###", "##BBBBBBBBBBBBB##")
	.aisle("#BBBBBBBBBBBBBBB#", "#CBBBBBBBBBBBBB##", "###D#########D###", "###D#########D###", "####D#######D####", "####D#######D####", "####D#######D#C##", "####DD#####DD####", "##C##DD###DD#####", "######DDDDD######", "#######DDD#######", "#################", "#################", "#################", "#######DDD####C##", "######DDDDD######", "##C##DD###DD#####", "####DD#####DD####", "####D#######D####", "####D#######D####", "####D#######D####", "###D#########D###", "###D#########DC##", "##BBBBBBBBBBBBB##", "#BBBBBBBBBBBBBBB#")
	.aisle("#BBBBBBBBBBBBBBB#", "#CBBBBBBBBBBBBB##", "###D#########D###", "###D#########D###", "###D#########D###", "###D#########D#C#", "###D#########D###", "####D#######D####", "####D#######D####", "#C###D#####D#####", "######D###D######", "#######DDD#######", "#################", "#######DDD#####C#", "######D###D######", "#####D#####D#####", "####D#######D####", "#C##D#######D####", "###D#########D###", "###D#########D###", "###D#########D###", "###D#########D#C#", "###D#########D###", "##BBBBBBBBBBBBB##", "#BBBBBBBBBBBBBBB#")
	.aisle("BBBBBBBBBBBBBBBBB", "#BBBBBBBBBBBBBBB#", "#CD###########D##", "##D###########D##", "##D###########DC#", "###D#########D###", "###D#########D###", "###D#########D###", "####D#######D####", "####D#######D####", "#C###D#####D#####", "######DDDDD######", "#######DDD#####C#", "######DDDDD######", "#####D#####D#####", "####D#######D####", "####D#######D####", "###D#########D###", "#C#D#########D###", "###D#########D###", "##D###########DC#", "##D###########D##", "##D###########D##", "#BBBBBBBBBBBBBBB#", "BBBBBBBBBBBBBBBBB")
	.aisle("BBBBBBBBBBBBBBBBB", "#BBBBBBBBBBBBBBB#", "#CD###########D##", "##D###########D##", "##D###########DC#", "##D###########D##", "###D#########D###", "###D#########D###", "###D#########D###", "####D#######D####", "#C##D#######D####", "#####DD###DD#####", "######DDDDD####C#", "#####DD###DD#####", "####D#######D####", "####D#######D####", "###D#########D###", "###D#########D###", "#C#D#########D###", "##D###########D##", "##D###########DC#", "##D###########D##", "##D###########D##", "#BBBBBBBBBBBBBBB#", "BBBBBBBBBBBBBBBBB")
	.aisle("BBBBBBBBBBBBBBBBB", "#BBBBBBBBBBBBBBB#", "##D###########D##", "#CD###########DC#", "##D###########D##", "##D###########D##", "##D###########D##", "###D#########D###", "###D#########D###", "####D#######D####", "####D#######D####", "#C###DD###DD###C#", "######DDDDD######", "#####DD###DD#####", "####D#######D####", "####D#######D####", "###D#########D###", "###D#########D###", "##D###########D##", "#CD###########DC#", "##D###########D##", "##D###########D##", "##D###########D##", "#BBBBBBBBBBBBBBB#", "BBBBBBBBBBBBBBBBB")
	.aisle("BBBBBBBBBBBBBBBBB", "#BBBBBBBBBBBBBBB#", "##D###########DC#", "##D###########D##", "#CD###########D##", "##D###########D##", "###D#########D###", "###D#########D###", "###D#########D###", "####D#######D####", "####D#######D##C#", "#####DD###DD#####", "#C####DDDDD######", "#####DD###DD#####", "####D#######D####", "####D#######D####", "###D#########D###", "###D#########D###", "###D#########D#C#", "##D###########D##", "#CD###########D##", "##D###########D##", "##D###########D##", "#BBBBBBBBBBBBBBB#", "BBBBBBBBBBBBBBBBB")
	.aisle("BBBBBBBBBBBBBBBBB", "#BBBBBBBBBBBBBBB#", "##D###########DC#", "##D###########D##", "#CD###########D##", "###D#########D###", "###D#########D###", "###D#########D###", "####D#######D####", "####D#######D####", "#####D#####D###C#", "######DDDDD######", "#C#####DDD#######", "######DDDDD######", "#####D#####D#####", "####D#######D####", "####D#######D####", "###D#########D###", "###D#########D#C#", "###D#########D###", "#CD###########D##", "##D###########D##", "##D###########D##", "#BBBBBBBBBBBBBBB#", "BBBBBBBBBBBBBBBBB")
	.aisle("#BBBBBBBBBBBBBBB#", "##BBBBBBBBBBBBBC#", "###D#########D###", "###D#########D###", "###D#########D###", "#C#D#########D###", "###D#########D###", "####D#######D####", "####D#######D####", "#####D#####D###C#", "######D###D######", "#######DDD#######", "#################", "#C#####DDD#######", "######D###D######", "#####D#####D#####", "####D#######D####", "####D#######D##C#", "###D#########D###", "###D#########D###", "###D#########D###", "#C#D#########D###", "###D#########D###", "##BBBBBBBBBBBBB##", "#BBBBBBBBBBBBBBB#")
	.aisle("#BBBBBBBBBBBBBBB#", "##BBBBBBBBBBBBBC#", "###D#########D###", "###D#########D###", "####D#######D####", "####D#######D####", "##C#D#######D####", "####DD#####DD####", "#####DD###DD##C##", "######DDDDD######", "#######DDD#######", "#################", "#################", "#################", "##C####DDD#######", "######DDDDD######", "#####DD###DD##C##", "####DD#####DD####", "####D#######D####", "####D#######D####", "####D#######D####", "###D#########D###", "##CD#########D###", "##BBBBBBBBBBBBB##", "#BBBBBBBBBBBBBBB#")
	.aisle("##BBBBBBBBBBBBB##", "###BBBBBBBBBBB###", "####DD#####DD####", "####DD#####DD####", "#####D#####D#####", "#####DD###DD#####", "#####DDD#DDD#####", "###C##DDDDD##C###", "#######DDD#######", "#################", "#################", "#################", "#################", "#################", "#################", "###C#########C###", "#######DDD#######", "######DDDDD######", "#####DDD#DDD#####", "#####DD###DD#####", "#####D#####D#####", "####DD#####DD####", "##C#DD#####DD####", "###BBBBBBBBBBBC##", "##BBBBBBBBBBBBB##")
	.aisle("##BBBBBBBBBBBBB##", "####BBBBBBBBB####", "######DDDDD######", "######DDDDD######", "######DDDDD######", "#######DDD#######", "########D###C####", "#################", "####C############", "#################", "#################", "#################", "#################", "#################", "############C####", "#################", "####C############", "#################", "########D########", "#######DDD#######", "######DDDDD######", "######DDDDD######", "######DDDDD#CC###", "###CBBBBBBBBB####", "###BBBBBBBBBBB###")
	.aisle("####BBBBBBBBB####", "####CCBBEBB######", "######CC#########", "########C########", "#########CC######", "###########C#####", "#################", "#################", "#################", "#####C###########", "######CC#########", "########C########", "#########CC######", "###########C#####", "#################", "#################", "#################", "#####C###########", "######CC#########", "########C########", "#########CC######", "###########C#####", "#################", "######BBBBB######", "####BBBBBBBBB####")
	.aisle("######BBBBB######", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "#################", "######BBBBB######")
                            .where('#', Predicates.any())
                            .where('C', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, CelestialTungsten)))
                            .where('D', blocks(GTCABlocks.OMNI_GLASS.get()))
                            .where('E', Predicates.controller(Predicates.blocks(definition.get())))
                            .where('B', blocks(GTCABlocks.HEAT_RESISTANT_TRINIUM_PLATED_CASING.get())
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes(), true, false, false, true, true, true))
                                    .or(Predicates.autoAbilities(true, false, false)))
                            .build()
            )

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Purification Unit"),
                    Component.translatable("gtca.machine.residual_decontamiant_degasser_purification_unit_desc.tooltip"),
                    Component.translatable("gtca.machine.residual_decontamiant_degasser_purification_unit1_desc.tooltip"),
                    Component.translatable("gtca.machine.residual_decontamiant_degasser_purification_unit3_desc.tooltip"),
                    Component.translatable("gtca.machine.residual_decontamiant_degasser_purification_unit4_desc.tooltip")
            )
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCA.id("block/casing/purification/heat_resistant_trinium_plated_casing"),
                    GTCA.id("block/multiblock/purification_multiblock")))
            .register();
}













