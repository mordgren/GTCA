package net.mordgren.gtca.data.recipe.permachine;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.mordgren.gtca.GTCA;
import net.mordgren.gtca.common.data.GTCARecipeTypes;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class SpacePumpRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {


        add(provider, "oil", LuV, 200, 1, GTMaterials.Oil.getFluid(1_400_000));
        add(provider, "lava", LuV, 200, 2, GTMaterials.Lava.getFluid(1_800_000));
        add(provider, "mercury", LuV, 200, 3, GTMaterials.Mercury.getFluid(896_000));


        add(provider, "sulfuric_acid", ZPM, 200, 4, GTMaterials.SulfuricAcid.getFluid(784_000));
        add(provider, "molten_iron", ZPM, 200, 5, GTMaterials.Iron.getFluid(896_000));
        add(provider, "oil_heavy", ZPM, 200, 6, GTMaterials.OilHeavy.getFluid(1_792_000));
        add(provider, "oil_light", ZPM, 200, 7, GTMaterials.OilLight.getFluid(780_000));
        add(provider, "molten_lead", ZPM, 200, 8, GTMaterials.Lead.getFluid(896_000));


        add(provider, "hydrogen", UV, 200, 9,  GTMaterials.Hydrogen.getFluid(1_568_000));
        add(provider, "nitrogen", UV, 200, 10, GTMaterials.Nitrogen.getFluid(1_792_000));
        add(provider, "oxygen", UV, 200, 11, GTMaterials.Oxygen.getFluid(1_792_000));
        add(provider, "fluorine", UV, 200, 12, GTMaterials.Fluorine.getFluid(1_792_000));
        add(provider, "helium", UV, 200, 13, GTMaterials.Helium.getFluid(1_400_000));
        add(provider, "radon", UV, 200, 14, GTMaterials.Radon.getFluid(64_000));
        add(provider, "molten_copper", UV, 200, 15, GTMaterials.Copper.getFluid(672_000));
        add(provider, "molten_tin", UV, 200, 16, GTMaterials.Tin.getFluid(672_000));
        add(provider, "distilled_water", UV, 200, 17, GTMaterials.DistilledWater.getFluid(17_920_000));
    }

    private static void add(Consumer<FinishedRecipe> provider,
                            String id,
                            int tier,
                            int duration,
                            int circuit,
                            FluidStack out) {
        if (out == null || out.isEmpty()) {
            GTCA.LOGGER.warn("[SpacePump] Skipped recipe {} because fluid stack is empty", id);
            return;
        }

        GTCARecipeTypes.SPACE_PUMP.recipeBuilder("space_pump_" + id)
                .EUt(VA[tier])
                .duration(duration)
                .circuitMeta(circuit)
                .outputFluids(out)
                .save(provider);
    }
}
