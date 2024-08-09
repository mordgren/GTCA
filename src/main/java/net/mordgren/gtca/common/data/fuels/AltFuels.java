package net.mordgren.gtca.common.data.fuels;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import dev.arbor.gtnn.data.GTNNRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.mordgren.gtca.common.data.GTCAMaterials;
import dev.arbor.gtnn.block.PlantCasingBlock;
import java.util.function.Consumer;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static dev.arbor.gtnn.data.GTNNRecipeTypes.INSTANCE;

public class AltFuels {
    public static void init(Consumer<FinishedRecipe> provider) {
        e85Fuel(provider);
        dymethylEther(provider);
    }
    private static void e85Fuel(Consumer<FinishedRecipe> provider) {
        LARGE_CHEMICAL_RECIPES.recipeBuilder("e85fuel").EUt(480).duration(80)
                .inputFluids(Ethanol.getFluid(17000))
                .inputFluids(LightFuel.getFluid(3000))
                .inputFluids(EthylTertButylEther.getFluid(100))
                .outputFluids(GTCAMaterials.E85Fuel.getFluid(20000))
                .save(provider);

        INSTANCE.getCHEMICAL_PLANT_RECIPES().recipeBuilder("e85fuel").EUt(480).duration(60)
                .addCondition(GTNNRecipes.INSTANCE.setPlantCasing(PlantCasingBlock.STEEL))
                .inputFluids(Ethanol.getFluid(17050))
                .inputFluids(LightFuel.getFluid(3000))
                .inputFluids(EthylTertButylEther.getFluid(50))
                .outputFluids(GTCAMaterials.E85Fuel.getFluid(20000))
                .save(provider);

        COMBUSTION_GENERATOR_FUELS.recipeBuilder("e85fuel").EUt(-32).duration(90)
                .inputFluids(GTCAMaterials.E85Fuel.getFluid(2))
                .save(provider);
    }

    private static void dymethylEther(Consumer<FinishedRecipe> provider) {

        GAS_TURBINE_FUELS.recipeBuilder("dymethyl_ether").EUt(-32).duration(120)
                .inputFluids(GTCAMaterials.DymethylEther.getFluid(4))
                .save(provider);

        BLAST_RECIPES.recipeBuilder("aluminosilicate_catalyst_sio4").EUt(96).duration(60)
                .circuitMeta(3)
                .blastFurnaceTemp(3200)
                .inputFluids(Oxygen.getFluid(4000))
                .inputItems(getItem("dust", Aluminium, 1))
                .inputItems(getItem("dust", Silicon, 1))
                .outputItems(getItem("dust", GTCAMaterials.AluminosilicateCatalyst, 1))
                .save(provider);

        BLAST_RECIPES.recipeBuilder("aluminosilicate_catalyst_sio2").EUt(96).duration(60)
                .circuitMeta(3)
                .blastFurnaceTemp(3200)
                .inputFluids(Oxygen.getFluid(2000))
                .inputItems(getItem("dust", Aluminium, 1))
                .inputItems(getItem("dust", SiliconDioxide, 1))
                .outputItems(getItem("dust", GTCAMaterials.AluminosilicateCatalyst, 1))
                .save(provider);

        INSTANCE.getDEHYDRATOR_RECIPES().recipeBuilder("dymethyl_ether").EUt(1280).duration(240)
                .inputFluids(Methanol.getFluid(4000))
                .chancedInput((getItem("dust", GTCAMaterials.AluminosilicateCatalyst, 1)), 7400, 0)
                .chancedOutput((getItem("dust", Zeolite, 1)),2000,0)
                .outputFluids(GTCAMaterials.DymethylEther.getFluid(12000))
                .save(provider);
    }
    private static ItemStack getItem(String type, Material name, int amount) {
        return ChemicalHelper.get(TagPrefix.getPrefix(type), name, amount);
    }
}
