package net.mordgren.gtca.common.data.recipes.permachine;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAItems;
import net.mordgren.gtca.common.data.GTCAMaterials;
import net.mordgren.gtca.common.data.GTCARecipeTypes;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.GTValues.ZPM;

public class TMForgeRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        metals(provider);
    }
    public static void metals(Consumer<FinishedRecipe> provider) {
        GTCARecipeTypes.TEGMARK_FORGE.recipeBuilder("adamantium")
                .inputItems(GTCAItems.Proton.asStack(64))
                .inputItems(GTCAItems.Proton.asStack(55))
                .inputItems(GTCAItems.Electron.asStack(64))
                .inputItems(GTCAItems.Electron.asStack(55))
                .inputItems(GTCAItems.Neutron.asStack(64))
                .inputItems(GTCAItems.Neutron.asStack(64))
                .inputItems(GTCAItems.Neutron.asStack(48))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.Adamantium, 1))
                .stationResearch(b -> b
                        .researchStack(GTCAHelper.getItem("ingot",GTMaterials.Tungsten, 1))
                        .CWUt(32)
                        .EUt(VA[ZPM]))
                .duration(600).EUt(VA[ZPM]).save(provider);
    }
}
