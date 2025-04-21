package net.mordgren.gtca.common.data.recipes.permachine;

import com.gregtechceu.gtceu.common.data.GTItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.util.GTCAHelper;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.mordgren.gtca.common.data.GTCAMaterials.*;
import static net.mordgren.gtca.common.data.GTCARecipeTypes.*;

public class NanoForgeRecipes {

    public static void init(Consumer<FinishedRecipe> provider){

        NANOFORGE.recipeBuilder("carbon_nanites_nanoforge").duration(10000).EUt(VA[UEV])
                .inputItems(GTCAHelper.getItem("block", Carbon, 8))
                .inputItems(GTItems.SYSTEM_ON_CHIP, 64)
                .inputFluids(UUMatter.getFluid(200000))
                .notConsumable(GTCAHelper.getItem("lens", QuiteCertainCrystal, 1))
                .outputItems(GTCAItems.CarbonNanites, 64)
                .save(provider);

        NANOFORGE.recipeBuilder("silver_nanites_nanoforge").duration(15000).EUt(VA[UEV])
                .inputItems(GTCAHelper.getItem("block", Silver, 8))
                .inputItems(GTItems.SYSTEM_ON_CHIP, 16)
                .inputFluids(UUMatter.getFluid(200000))
                .notConsumable(GTCAHelper.getItem("lens", RadonPolymer, 1))
                .outputItems(GTCAItems.SilverNanites, 1)
                .save(provider);

        NANOFORGE.recipeBuilder("gold_nanites_nanoforge").duration(17500).EUt(VA[UIV])
                .inputItems(GTCAHelper.getItem("block", Gold, 8))
                .inputItems(GTItems.SYSTEM_ON_CHIP, 16)
                .inputFluids(UUMatter.getFluid(300000))
                .notConsumable(GTCAHelper.getItem("lens", QuiteCertainCrystal, 1))
                .outputItems(GTCAItems.GoldNanites, 1)
                .save(provider);

    }
}
