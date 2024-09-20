package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCACasings;
import net.mordgren.gtca.common.data.materials.GTCAItems;
import net.mordgren.gtca.common.data.GTCAMachines;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLER_RECIPES;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.COMET_CYCLOTRON;

public class CometRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        common(provider);
        Comet(provider);

    }


    private static void common(Consumer<FinishedRecipe> provider) {

            ASSEMBLER_RECIPES.recipeBuilder("comet_controller").EUt(7680).duration(6000)
                    .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.Incoloy020, 8))
                    .inputItems(GTCAHelper.getItem("gear", GTCAMaterials.Tantalloy61, 2))
                    .inputItems(GTCAHelper.getItem("screw", GTCAMaterials.IncoloyMA323, 16))
                    .inputItems(CustomTags.IV_CIRCUITS, 16)
                    .inputItems(GTMachines.HULL[GTValues.IV],2)
                    .inputItems(GTCACasings.CYCLOTRON_COIL, 2)
                    .inputFluids(GTCAMaterials.Incoloy020.getFluid(1296))
                    .outputItems(GTCAMachines.COMET_CYCLOTRON.asStack())
                    .save(provider);

             ASSEMBLER_RECIPES.recipeBuilder("cyclotron_coil").EUt(7680).duration(2400)
                     .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.IncoloyMA323, 8))
                     .inputItems(GTCAHelper.getItem("bolt", GTCAMaterials.Tantalloy61, 16))
                     .inputItems(GTCAHelper.getItem("screw",  GTCAMaterials.Incoloy020, 32))
                     .inputItems(GTBlocks.COIL_NICHROME, 1)
                     .inputItems(GTItems.VOLTAGE_COIL_IV,8)
                     .inputItems(GTItems.FIELD_GENERATOR_EV, 1)
                     .inputFluids(GTCAMaterials.HG1223.getFluid(720))
                     .outputItems(GTCACasings.CYCLOTRON_COIL, 1)
                     .save(provider);

            ASSEMBLER_RECIPES.recipeBuilder("comet_casing").EUt(1920).duration(1200)
                    .inputItems(GTBlocks.CASING_ALUMINIUM_FROSTPROOF, 1)
                    .inputItems(GTItems.VOLTAGE_COIL_EV,4)
                    .inputItems(GTCAHelper.getItem("plate", GTCAMaterials.IncoloyDS, 8))
                    .inputItems(GTCAHelper.getItem("screw", GTCAMaterials.Inconel690, 16))
                    .inputItems(GTCAHelper.getItem("longRod", GTCAMaterials.EglinSteel, 4))
                    .inputItems(GTItems.ELECTRIC_PISTON_HV,2)
                    .inputFluids(GTCAMaterials.ZirconiumCarbide.getFluid(1152))
                    .outputItems(GTCACasings.COMET_CASING, 1)
                    .save(provider);
    }
    private static void Comet(Consumer<FinishedRecipe> provider) {

            COMET_CYCLOTRON.recipeBuilder("hydrogen_ion_simple").EUt(30720).duration(400)
                    .circuitMeta(24)
                    .inputFluids(Hydrogen.getFluid(1000))
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .chancedOutput(GTCAItems.HydrogenIon.asStack(1), 500, 0)
                    .save(provider);

        COMET_CYCLOTRON.recipeBuilder("helium_ion_simple").EUt(122880).duration(1600)
                .circuitMeta(2)
                .inputFluids(Helium.getFluid(FluidStorageKeys.PLASMA, 2))
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 250, 0)
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 250, 0)
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 250, 0)
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 250, 0)
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 250, 0)
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 250, 0)
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 250, 0)
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 260, 0)
                .chancedOutput(GTCAItems.HeliumIon.asStack(1), 275, 0)
                .save(provider);

        COMET_CYCLOTRON.recipeBuilder("proton_simple").EUt(30720).duration(400)
                .circuitMeta(20)
                .inputFluids(Hydrogen.getFluid(200))
                .inputItems(GTCAItems.HydrogenIon,1)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.Proton.asStack(1), 750, 0)
                .save(provider);

        COMET_CYCLOTRON.recipeBuilder("unknown").EUt(30720).duration(1600)
                .circuitMeta(21)
                .inputFluids(Hydrogen.getFluid(1000))
                .inputItems(GTCAItems.HydrogenIon,1)
                .chancedOutput(GTCAItems.Proton.asStack(1), 1250, 0)
                .chancedOutput(GTCAItems.Neutron.asStack(1), 1250, 0)
                .chancedOutput(GTCAItems.Electron.asStack(1), 1250, 0)
                .chancedOutput(GTCAItems.UnknownParticle.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.UnknownParticle.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.UnknownParticle.asStack(1), 750, 0)
                .save(provider);


        COMET_CYCLOTRON.recipeBuilder("anomaly").EUt(491520).duration(500)
                .circuitMeta(24)
                .inputFluids(Duranium.getFluid(40))
                .inputItems(GTCAItems.UnknownParticle,1)
                .chancedOutput(GTCAItems.QuantumAnomaly.asStack(1), 100, 0)
                .save(provider);






    }

}
