package net.mordgren.gtca.data.recipe.permachine;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.machine.multiblock.electric.SpacePumpMachine;

import java.util.function.Consumer;


public final class SpacePumpRecipes {

    private static final int DURATION = 9600;

    private SpacePumpRecipes() {}

    public static void init(Consumer<FinishedRecipe> provider) {
        add(provider, "oil", 1, GTMaterials.Oil.getFluid(1_400_000));
        add(provider, "lava", 2, GTMaterials.Lava.getFluid(1_800_000));
        add(provider, "mercury", 3, GTMaterials.Mercury.getFluid(896_000));

        add(provider, "sulfuric_acid", 4, GTMaterials.SulfuricAcid.getFluid(784_000));
        add(provider, "molten_iron", 5, GTMaterials.Iron.getFluid(896_000));
        add(provider, "oil_heavy", 6, GTMaterials.OilHeavy.getFluid(1_792_000));
        add(provider, "oil_light", 7, GTMaterials.OilLight.getFluid(780_000));
        add(provider, "molten_lead", 8, GTMaterials.Lead.getFluid(896_000));

        add(provider, "hydrogen", 9, GTMaterials.Hydrogen.getFluid(1_568_000));
        add(provider, "nitrogen", 10, GTMaterials.Nitrogen.getFluid(1_792_000));
        add(provider, "oxygen", 11, GTMaterials.Oxygen.getFluid(1_792_000));
        add(provider, "fluorine", 12, GTMaterials.Fluorine.getFluid(1_792_000));
        add(provider, "helium", 13, GTMaterials.Helium.getFluid(1_400_000));
        add(provider, "radon", 14, GTMaterials.Radon.getFluid(64_000));
        add(provider, "molten_copper", 15, GTMaterials.Copper.getFluid(672_000));
        add(provider, "molten_tin", 16, GTMaterials.Tin.getFluid(672_000));
        add(provider, "distilled_water", 17, GTMaterials.DistilledWater.getFluid(17_920_000));
    }

    private static void add(Consumer<FinishedRecipe> provider,
                            String id,
                            int circuit,
                            FluidStack output) {
        if (output == null || output.isEmpty()) {
            GTCA.LOGGER.warn("[SpacePump] Skipped recipe {} because fluid stack is empty", id);
            return;
        }

        GTCARecipeTypes.SPACE_PUMP.recipeBuilder("space_pump_" + id)
                .EUt(SpacePumpMachine.EU_PER_PARALLEL)
                .duration(DURATION)
                .circuitMeta(circuit)
                .outputFluids(output)
                .save(provider);
    }
}
