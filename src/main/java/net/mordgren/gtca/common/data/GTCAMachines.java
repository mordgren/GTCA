package net.mordgren.gtca.common.data;

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
import com.gregtechceu.gtceu.utils.FormattingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.blocks.GTCACasings;
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
import static com.gregtechceu.gtceu.common.data.GTMaterials.Tungsten;
import static net.mordgren.gtca.GTCA.GTCA_REGISTRATE;

public class GTCAMachines {


    /// STEAM PRESSURIZER ///
    public static final MultiblockMachineDefinition steam_pressurizer = GTCA_REGISTRATE.multiblock("steam_pressurizer", WorkableElectricMultiblockMachine::new)
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
    public static final MultiblockMachineDefinition ADVANCED_EBF = GTCA_REGISTRATE
            .multiblock("advanced_ebf", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.BLAST_RECIPES)
            .recipeModifiers(GTRecipeModifiers.SUBTICK_PARALLEL, GTRecipeModifiers.PARALLEL_HATCH, AEBFMod::aebfOverclock)
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
                    .where('X', blocks(GTCACasings.CASING_AEBF.get()).setMinGlobalLimited(9)
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
                        .where('E', GTMachines.ENERGY_INPUT_HATCH[GTValues.LV], Direction.NORTH)
                        .where('P', GTMachines.ITEM_IMPORT_BUS[GTValues.LV], Direction.WEST)
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
                    () -> new ItemLike[] { GTItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get() })
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

    public static final MultiblockMachineDefinition CHEMICAL_GENERATOR = registerChemicalGenerator(
            "chemical_generator", EV,
            CASING_INVAR_HEATPROOF, CASING_STAINLESS_STEEL_GEARBOX,
            GTCEu.id("block/casings/solid/machine_casing_heatproof"),
            GTCEu.id("block/multiblock/electric_blast_furnace"));


    public static MultiblockMachineDefinition registerChemicalGenerator(String name, int tier,
                                                                            Supplier<? extends Block> casing,
                                                                            Supplier<? extends Block> gear,
                                                                            ResourceLocation casingTexture,
                                                                            ResourceLocation overlayModel) {
        return GTCA_REGISTRATE.multiblock(name, holder -> new ChemGenProps(holder, tier))
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(GTCARecipeTypes.CHEMICAL_GENERATOR)
                .generator(true)
                .recipeModifier(ChemGenProps::recipeModifier, true)
                .appearanceBlock(casing)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("III", "PPP", "PPP", "III")
                        .aisle("III", "P#P", "P#P", "III")
                        .aisle("III", "PPP", "PPP", "III")
                        .aisle("III", "CSC", "CSC", "III")
                        .aisle("III", "FGF", "FGF", "III")
                        .aisle("IMI", "III", "IDI", "III")
                        .where('M', controller(blocks(definition.getBlock())))
                        .where('P', blocks(GTBlocks.CASING_PTFE_INERT.get()))
                        .where('#', blocks(GTBlocks.CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                        .where('C', blocks(GTBlocks.COIL_CUPRONICKEL.get()))
                        .where('S', blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                        .where('F', blocks(GTBlocks.FIREBOX_STEEL.get()))
                        .where('G', blocks(gear.get()))
                        .where('I', blocks(casing.get()).setMinGlobalLimited(3)
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
                        () -> new ItemLike[] { GTItems.MATERIAL_ITEMS.get(TagPrefix.dustTiny, GTMaterials.Ash).get() })
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

    /// REGISTRATION METHOD ///
    public static void init() {}

}


