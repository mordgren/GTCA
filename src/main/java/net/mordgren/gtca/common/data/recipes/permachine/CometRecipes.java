package net.mordgren.gtca.common.data.recipes.permachine;

import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.data.GTCAMaterials;

import java.util.function.Consumer;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.COMET_CYCLOTRON;

public class CometRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        Comet(provider);
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

        COMET_CYCLOTRON.recipeBuilder("hydrogen_plasma").EUt(30720).duration(2400)
                .circuitMeta(21)
                .inputFluids(Hydrogen.getFluid(1000))
                .inputItems(GTCAItems.HydrogenIon,1)
                .chancedOutput(GTCAItems.Proton.asStack(1), 1250, 0)
                .chancedOutput(GTCAItems.Neutron.asStack(1), 1250, 0)
                .chancedOutput(GTCAItems.Electron.asStack(1), 1250, 0)
                .chancedOutput(GTCAItems.UnknownParticle.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.UnknownParticle.asStack(1), 750, 0)
                .chancedOutput(GTCAItems.UnknownParticle.asStack(1), 750, 0)
                .outputFluids(GTCAMaterials.HydrogenPlasma.getFluid(100))
                .save(provider);
    }
}
