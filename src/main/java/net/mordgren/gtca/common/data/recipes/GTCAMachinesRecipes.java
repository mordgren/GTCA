package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMachines;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.COMET_CYCLOTRON;

public class GTCAMachinesRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        regControllerRecipes(provider);
    }


    private static void regControllerRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, true, "steam_pressurizer",
                /// Output
                GTCAMachines.STEAM_PRESSURIZER.asStack(),
                /// Pattern
                "PRP", "CSC", "WHW",
                /// Ingredients definition
                'P', ELECTRIC_PUMP_HV.asStack(),
                'R', ELECTRIC_PISTON_HV.asStack(),
                'C', CustomTags.HV_CIRCUITS,
                'S', new UnificationEntry(TagPrefix.pipeLargeFluid, StainlessSteel),
                'W', new UnificationEntry(TagPrefix.cableGtSingle, Gold),
                'H', HULL[GTValues.HV].asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "advanced_ebf",
                /// Output
                GTCAMachines.ADVANCED_EBF.asStack(),
                /// Pattern
                "CEC", "EFE", "CFC",
                /// Ingredients definition
                'E', ELECTRIC_BLAST_FURNACE.asStack(),
                'F', FIELD_GENERATOR_LuV.asStack(),
                'C', CustomTags.LuV_CIRCUITS);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "ev_chemgen",
                /// Output
                GTCAMachines.EV_CHEMICAL_GENERATOR.asStack(),
                /// Pattern
                "PCP", "EME", "GWG",
                /// Ingredients definition
                'M', GTMachines.HULL[GTValues.EV].asStack(),
                'P', GTItems.ELECTRIC_PISTON_EV.asStack(),
                'E', ELECTRIC_PUMP_EV.asStack(),
                'C', CustomTags.IV_CIRCUITS,
                'W', new UnificationEntry(TagPrefix.cableGtSingle, GTMaterials.Aluminium),
                'G', new UnificationEntry(TagPrefix.gear, GTMaterials.Titanium));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "iv_chemgen",
                /// Output
                GTCAMachines.IV_CHEMICAL_GENERATOR.asStack(),
                /// Pattern
                "PCP", "EME", "GWG",
                /// Ingredients definition
                'M', GTMachines.HULL[GTValues.IV].asStack(),
                'P', GTItems.ELECTRIC_PISTON_IV.asStack(),
                'E', ELECTRIC_PUMP_IV.asStack(),
                'C', CustomTags.LuV_CIRCUITS,
                'W', new UnificationEntry(TagPrefix.cableGtSingle, GTMaterials.HSSG),
                'G', new UnificationEntry(TagPrefix.gear, GTMaterials.TungstenSteel));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "greenhouse",
                /// Output
                GTCAMachines.GREEN_HOUSE.asStack(),
                /// Pattern
                "GGG", "CHC", "PUP",
                /// Ingredients definition
                'H', GTMachines.HULL[GTValues.MV].asStack(),
                'P', GTItems.ELECTRIC_PISTON_MV.asStack(),
                'U', ELECTRIC_PUMP_MV.asStack(),
                'C', CustomTags.MV_CIRCUITS,
                'G', GTBlocks.CASING_TEMPERED_GLASS.asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "polymerizer",
                /// Output
                GTCAMachines.POLYMERIZER.asStack(),
                /// Pattern
                "UCU", "PHP", "UCU",
                /// Ingredients definition
                'H', GTMachines.HULL[GTValues.EV].asStack(),
                'U', ELECTRIC_PUMP_EV.asStack(),
                'C', CustomTags.EV_CIRCUITS,
                'P', new UnificationEntry(TagPrefix.pipeLargeFluid, Polytetrafluoroethylene));

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("mega_lcr").EUt(480).duration(72000)
                .circuitMeta(17)
                .inputItems(GTMachines.LARGE_CHEMICAL_REACTOR.asStack(64))
                .inputFluids(SolderingAlloy.getFluid(9216))
                .outputItems(GTCAMachines.MEGA_LCR.asStack())
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("mega_cracker").EUt(480).duration(72000)
                .circuitMeta(17)
                .inputItems(GTMachines.CRACKER.asStack(64))
                .inputFluids(SolderingAlloy.getFluid(9216))
                .outputItems(GTCAMachines.MEGA_OIL_CRACKING_UNIT.asStack())
                .save(provider);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "sc_turbine",
                /// Output
                GTCAMachines.SHD_TURBINE.asStack(),
                /// Pattern
                "CPC", "GHG", "LPL",
                /// Ingredients definition
                'H', GTMachines.HULL[GTValues.IV].asStack(),
                'P', new UnificationEntry(TagPrefix.plate, GTCAMaterials.MAR_M200),
                'C', CustomTags.LuV_CIRCUITS,
                'G', new UnificationEntry(TagPrefix.gear, GTCAMaterials.Moltech),
                'L', new UnificationEntry(TagPrefix.pipeLargeFluid, Naquadah));

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("sc_turbine_assembler").EUt(480).duration(300)
                .inputItems(GTCAHelper.getItem("gear", GTCAMaterials.Moltech, 2))
                .inputItems(GTCAHelper.getItem("pipeLargeFluid", Naquadah, 2))
                .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.MAR_M200, 2))
                .inputItems(CustomTags.LuV_CIRCUITS, 2)
                .inputItems(GTMachines.HULL[GTValues.IV].asStack(1))
                .outputItems(GTCAMachines.SHD_TURBINE.asStack())
                .save(provider);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "extreme_heat_exchanger",
                /// Output
                GTCAMachines.EXTREME_HEAT_EXCHANGER.asStack(),
                /// Pattern
                "VLV", "LHL", "PLP",
                /// Ingredients definition
                'V', GCyMBlocks.HEAT_VENT.asStack(),
                'H', GTMachines.HULL[GTValues.IV].asStack(),
                'P', new UnificationEntry(TagPrefix.plate, GTCAMaterials.MAR_M200),
                'L', new UnificationEntry(TagPrefix.pipeLargeFluid, TungstenSteel));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "industrial_coke_oven",
                /// Output
                GTCAMachines.INDUSTRIAL_COKE_OVEN.asStack(),
                /// Pattern
                "PCP", "HBH", "PCP",
                /// Ingredients definition
                'B', GTMachines.COKE_OVEN.asStack(),
                'H', GTMachines.HULL[GTValues.EV].asStack(),
                'P', new UnificationEntry(TagPrefix.plate, GTCAMaterials.Tantalloy61),
                'C', CustomTags.EV_CIRCUITS);

        VanillaRecipeHelper.addShapedRecipe(provider, true, "thermal_reactor",
                /// Output
                GTCAMachines.THERMAL_REACTOR.asStack(),
                /// Pattern
                "RHR", "PSP", "CXC",
                /// Ingredients definition
                'X', GTMachines.CHEMICAL_REACTOR[GTValues.EV].asStack(),
                'H', new UnificationEntry(TagPrefix.pipeHugeFluid, Titanium),
                'C', CustomTags.EV_CIRCUITS,
                'R', new UnificationEntry(TagPrefix.rotor, TungstenSteel),
                'P', ELECTRIC_PUMP_EV.asStack(),
                'S', new UnificationEntry(TagPrefix.spring, TungstenSteel));





    }
}

