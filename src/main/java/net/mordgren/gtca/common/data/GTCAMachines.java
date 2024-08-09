package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.registry.GTRegistration.REGISTRATE;

public class GTCAMachines {

    public static final MultiblockMachineDefinition steam_pressurizer = REGISTRATE.multiblock("steam_pressurizer", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTCARecipeTypes.STEAM_PRESSURIZER)
            .appearanceBlock(GTBlocks.MACHINE_CASING_ULV)
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

    public static void init() {}
}
