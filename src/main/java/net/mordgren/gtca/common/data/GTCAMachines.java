package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IRotorHolderMachine;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
import com.gregtechceu.gtceu.common.data.GCyMBlocks;
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
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.*;
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
import net.mordgren.gtca.common.util.AEBFMod;
import net.mordgren.gtca.common.util.ChemGenProps;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Tungsten;
import static com.gregtechceu.gtceu.common.data.GTMaterials.TungstenCarbide;
import static net.mordgren.gtca.GTCARegistration.REGISTRATE;

public class GTCAMachines {
    public static void init() {}




    /// STEAM PRESSURIZER ///
    public static final MultiblockMachineDefinition STEAM_PRESSURIZER = REGISTRATE.multiblock("steam_pressurizer", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.STEAM_PRESSURIZER)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
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
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/implosion_compressor"),
                    true
            )
            .register();

    /// ADVANCED EBF ///


    public static final MultiblockMachineDefinition ADVANCED_EBF = REGISTRATE
            .multiblock("advanced_ebf", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.BLAST_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK), AEBFMod::aebfOverclock)
            .appearanceBlock(GTCACasings.CASING_AEBF)
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
                    .where('I', blocks(GCyMBlocks.HEAT_VENT.get()))
                    .where('X', blocks(GTCACasings.CASING_AEBF.get()).setMinGlobalLimited(158)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('H', abilities(PartAbility.MUFFLER))
                    .where('C', heatingCoils())
                    .where('#', air())
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
                        .where('X', GTCACasings.CASING_AEBF.getDefaultState())
                        .where('S', definition, Direction.SOUTH)
                        .where('I', GCyMBlocks.HEAT_VENT)
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
                    () -> new ItemLike[]{GTItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
            .workableCasingRenderer(
                    GTCA.id("block/casing/casing_aebf"),
                    GTCA.id("block/multiblock/aebf"),
                    true
            )
            .tooltips(
                    Component.translatable("gtceu.multiblock.parallelizable.tooltip"),
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Electric Blast Furnace")
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
            .compassSections(GTCompassSections.TIER[MV])
            .compassNodeSelf()
            .register();

    /// CHEMICAL GENERATOR ///
    public static final MultiblockMachineDefinition IV_CHEMICAL_GENERATOR = registerChemicalGenerator(
            "iv_chemical_generator", IV,
            GTCACasings.INCONEL718_CASING, CASING_TUNGSTENSTEEL_GEARBOX, FIREBOX_TUNGSTENSTEEL, CASING_TUNGSTENSTEEL_PIPE,
            GTCA.id("block/casing/inconel718_casing"),
            GTCA.id("block/multiblock/aebf"));

    public static final MultiblockMachineDefinition EV_CHEMICAL_GENERATOR = registerChemicalGenerator(
            "ev_chemical_generator", EV,
            GTCACasings.VITALLIUM_CASING, CASING_TITANIUM_GEARBOX, FIREBOX_TITANIUM, CASING_TITANIUM_PIPE,
            GTCA.id("block/casing/vitallium_casing"),
            GTCA.id("block/multiblock/aebf"));


    public static MultiblockMachineDefinition registerChemicalGenerator(String name, int tier,
                                                                        Supplier<? extends Block> casing,
                                                                        Supplier<? extends Block> gear,
                                                                        Supplier<? extends Block> firebox,
                                                                        Supplier<? extends Block> pipe,
                                                                        ResourceLocation casingTexture,
                                                                        ResourceLocation overlayModel) {
        return REGISTRATE.multiblock(name, holder -> new ChemGenProps(holder, tier))
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(GTCARecipeTypes.CHEMICAL_GENERATOR)
                .generator(true)
                .recipeModifier(ChemGenProps::recipeModifier, true)
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
                        () -> new ItemLike[]{GTItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get()})
                .workableCasingRenderer(casingTexture, overlayModel)
                .tooltips(
                        Component.translatable("gtceu.universal.tooltip.base_production_eut", V[tier]),
                        tier > EV ?
                                Component.translatable("gtceu.machine.large_combustion_engine.tooltip.boost_extreme",
                                        V[tier] * 4) :
                                Component.translatable("gtceu.machine.large_combustion_engine.tooltip.boost_regular",
                                        V[tier] * 3))
                .compassSections(GTCompassSections.TIER[EV])
                .compassNode("chemical_generator")
                .register();
    }

    public static final MultiblockMachineDefinition GREEN_HOUSE = REGISTRATE
            .multiblock("green_house", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.GREEN_HOUSE)
            .appearanceBlock(GTCACasings.CASING_GREENHOUSE)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "#AAA#", "#AAA#", "#BBB#", "#BBB#", "#BBB#", "#####")
                    .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "#BBB#")
                    .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "#BBB#")
                    .aisle("AAAAA", "ACCCA", "A###A", "B###B", "B###B", "B###B", "#BBB#")
                    .aisle("AADAA", "#AAA#", "#AAA#", "#BBB#", "#BBB#", "#BBB#", "#####")
                    .where('A', blocks(GTCACasings.CASING_GREENHOUSE.get()).setMinGlobalLimited(42)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false)))
                    .where('D', controller(blocks(definition.getBlock())))
                    .where('#', air())
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
                        .where('A', GTCACasings.CASING_GREENHOUSE.getDefaultState())
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
            .workableCasingRenderer(
                    GTCA.id("block/casing/casing_greenhouse"),
                    GTCA.id("block/multiblock/aebf"),
                    true
            )
            .compassSections(GTCompassSections.TIER[MV])
            .compassNodeSelf()
            .register();


    public static final MultiblockMachineDefinition POLYMERIZER = REGISTRATE
            .multiblock("polymerizer", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.POLYMERIZER)
            .appearanceBlock(GTCACasings.DURAL_CASING)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                    .aisle("CCCCC", "CHCHC", "CPPPC", "CHCHC", "CCCCC")
                    .aisle("CCXCC", "CGCGC", "CGCGC", "CGCGC", "CCCCC")
                    .where('X', controller(blocks(definition.getBlock())))
                    .where('C', blocks(GTCACasings.DURAL_CASING.get()).setMinGlobalLimited(55)
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
            .workableCasingRenderer(
                    GTCA.id("block/casing/dural_casing"),
                    GTCA.id("block/multiblock/aebf"),
                    true
            )
            .register();

    public static final MultiblockMachineDefinition SHD_TURBINE = REGISTRATE
            .multiblock("shd_turbine", holder -> new LargeTurbineMachine(holder, LuV))
            .rotationState(RotationState.ALL)
            .recipeType(GTCARecipeTypes.SHD_STEAM_TURBINE)
            .generator(true)
            .recipeModifier(LargeTurbineMachine::recipeModifier, true)
            .appearanceBlock(GTCACasings.SHD_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCC", "CHHC", "CCCC")
                    .aisle("CHHC", "RGGR", "CHHC")
                    .aisle("CCCC", "CSHC", "CCCC")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('G', blocks(GTCACasings.SHD_Gearbox.get()))
                    .where('C', blocks(GTCACasings.SHD_CASING.get()))
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
                    .where('H', blocks(GTCACasings.SHD_CASING.get())
                            .or(autoAbilities(definition.getRecipeTypes(), false, false, true, true, true, true))
                            .or(autoAbilities(true, true, false)))
                    .build())
            .recoveryItems(
                    () -> new ItemLike[] { GTItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get() })
            .workableCasingRenderer(
                    GTCA.id("block/casing/shd_casing"),
                    GTCA.id("block/multiblock/shd")
            )
            .tooltips(
                    Component.translatable("gtceu.universal.tooltip.base_production_eut", V[LuV] * 2),
                    Component.translatable("gtceu.multiblock.turbine.efficiency_tooltip", VNF[LuV]),
                    Component.translatable("gtca.machine.SC_desc.tooltip"),
                    Component.translatable("gtca.machine.SC_desc.tooltip2")
            )


            .compassSections(GTCompassSections.TIER[HV])
            .compassNodeSelf()
            .register();



    public static final MultiblockMachineDefinition EXTREME_HEAT_EXCHANGER = REGISTRATE.multiblock("extreme_heat_exchanger", WorkableElectricMultiblockMachine::new)
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
                            .where("W", blocks(GTCACasings.PRW_Casing.get()))
                            .where("G", blocks(GTCACasings.REINFORCED_GLASS.get()))
                            .where('#', air())
                            .build()
            )
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                    GTCEu.id("block/multiblock/implosion_compressor"),
                    true
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Heat Exchanger"),
                    Component.translatable("gtca.machine.EHE_desc.tooltip"),
                    Component.translatable("gtca.machine.EHE_desc.tooltip2")
            )
            .register();

    public static final MultiblockMachineDefinition MEGA_OIL_CRACKING_UNIT = REGISTRATE
            .multiblock("mega_oil_cracking_unit", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.CRACKING_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK), GTRecipeModifiers::crackerOverclock)
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
                    .where('#', Predicates.air())
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
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
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
            .compassSections(GTCompassSections.TIER[EV])
            .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition MEGA_LCR = REGISTRATE
            .multiblock("mega_lcr", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
            .appearanceBlock(CASING_PTFE_INERT)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK))
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
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    GTCA.id("block/multiblock/shd"),
                    true
            )
            .tooltips(
                    Component.translatable("gtceu.multiblock.parallelizable.tooltip"),
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Chemical Reactor / LCR")
            )
            .register();

    public static final MultiblockMachineDefinition INDUSTRIAL_COKE_OVEN = REGISTRATE.multiblock("industrial_coke_oven", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.INDUSTRIAL_COKE_OVEN)
            .appearanceBlock(GTCACasings.TANTALLOY61_CASING)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("CCC", "FFF", "CCC")
                            .aisle("CCC", "F#F", "CMC")
                            .aisle("CEC", "FFF", "CCC")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("F", blocks(FIREBOX_TUNGSTENSTEEL.get()))
                            .where('M', abilities(PartAbility.MUFFLER))
                            .where('#', Predicates.air())
                            .where("C", blocks(GTCACasings.TANTALLOY61_CASING.get()).setMinGlobalLimited(11)
                                  .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                  .or(autoAbilities(true, false, false)))
                            .build()
            )
            .workableCasingRenderer(
                    GTCA.id("block/casing/tantalloy61_casing"),
                    GTCEu.id("block/multiblock/implosion_compressor"),
                    true
            )
            .register();

    public static final MultiblockMachineDefinition THERMAL_REACTOR = REGISTRATE.multiblock("thermal_reactor", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.THERMAL_REACTOR)
            .appearanceBlock(GTCACasings.NIMONIC80A_CASING)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("#CCC#", "#NNN#", "#CCC#", "#####", "#####","#####","#####","#####","#####","#####")
                            .aisle("CCCCC", "N###N", "CCCCC", "#FDF#", "#F#F#","#FDF#","#F#F#","#FDF#","#F#F#","#FDF#")
                            .aisle("CCCCC", "N###N", "CCPCC", "#DPD#", "##P##","#DPD#","##P##","#DPD#","##P##","#DDD#")
                            .aisle("CCCCC", "N###N", "CCCCC", "#FDF#", "#F#F#","#FDF#","#F#F#","#FDF#","#F#F#","#FDF#")
                            .aisle("#CEC#", "#NNN#", "#CCC#", "#####", "#####","#####","#####","#####","#####","#####")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where('F', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, TungstenCarbide)))
                            .where('#', Predicates.air())
                            .where('D', blocks(GTCACasings.NIMONIC80A_CASING.get()))
                            .where('P', blocks(CASING_TITANIUM_PIPE.get()))
                            .where('N', blocks(COIL_CUPRONICKEL.get()))
                            .where("C", blocks(GTCACasings.NIMONIC80A_CASING.get()).setMinGlobalLimited(11)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, false, false)))
                            .build()
            )
            .workableCasingRenderer(
                    GTCA.id("block/casing/nimonic80a_casing"),
                    GTCA.id("block/multiblock/aebf"),
                    true
            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Thermal Catalyst Reactor")
            )
            .register();

    public static final MultiblockMachineDefinition COMET_CYCLOTRON = REGISTRATE.multiblock("comet", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.COMET_CYCLOTRON)
            .appearanceBlock(GTCACasings.COMET_CASING)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
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
                            .where('#', Predicates.air())
                            .where('C', blocks(GTCACasings.CYCLOTRON_COIL.get()))
                            .where("A", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("B", blocks(GTCACasings.COMET_CASING.get()).setMinGlobalLimited(112)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, true, false))
                                )
                            .build()
            )
                            .workableCasingRenderer(
                                    GTCA.id("block/casing/comet_casing"),
                                    GTCA.id("block/multiblock/comet"),
                                    true
                            )
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Particle Accelerator"),
                                Component.translatable("gtca.machine.comet_desc.tooltip"),
                                Component.translatable("gtca.machine.comet_desc.tooltip2")
            )
                            .register();

    public static final MultiblockMachineDefinition ISAMILL = REGISTRATE.multiblock("isamill", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.ISAMILL)
            .appearanceBlock(GTCACasings.ISAMILL_CASING)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("ACCCCCC", "ACCCCCC", "ACCCCCC")
                            .aisle("ACCCCCC", "AGGGGGC", "ACCCCCC")
                            .aisle("ACCCCCC", "ACECCCC", "ACCCCCC")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("G", blocks(GTCACasings.ISAMILL_GEARBOX.get()))
                            .where("A", blocks(GTCACasings.ISAMILL_AIR_INTAKE.get()))
                            .where("C", blocks(GTCACasings.ISAMILL_CASING.get()).setMinGlobalLimited(44)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, true, false))
                            )
                            .build()
            )
            .workableCasingRenderer(
                    GTCA.id("block/casing/isa_mill_casing"),
                    GTCA.id("block/multiblock/aebf"),
                    true
            )
            .register();

    public static final MultiblockMachineDefinition FLCR = REGISTRATE.multiblock("flotation_cell_regulator", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.FLCR)
            .appearanceBlock(GTCACasings.ISAMILL_CASING)
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("##CCC##", "##CCC##", "#######")
                            .aisle("#CCCCC#", "#CCCCC#", "#CCCCC#")
                            .aisle("CCCCCCC", "CCCCCCC", "#CCCCC#")
                            .aisle("CCCCCCC", "CCCCCCC", "#CCCCC#")
                            .aisle("CCCCCCC", "CCCCCCC", "#CCCCC#")
                            .aisle("#CCCCC#", "#CCCCC#", "#CCCCC#")
                            .aisle("##CCC##", "##CCC##", "#######")
                            .where("E", Predicates.controller(Predicates.blocks(definition.get())))
                            .where("G", blocks(GTCACasings.ISAMILL_GEARBOX.get()))
                            .where("A", blocks(GTCACasings.ISAMILL_AIR_INTAKE.get()))
                            .where("C", blocks(GTCACasings.ISAMILL_CASING.get()).setMinGlobalLimited(44)
                                    .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                                    .or(autoAbilities(true, true, false))
                            )
                            .build()
            )
            .workableCasingRenderer(
                    GTCA.id("block/casing/isa_mill_casing"),
                    GTCA.id("block/multiblock/aebf"),
                    true
            )
            .register();
}





