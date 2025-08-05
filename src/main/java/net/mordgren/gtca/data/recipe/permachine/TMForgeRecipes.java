package net.mordgren.gtca.data.recipe.permachine;

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
                .inputFluids(GTMaterials.Tungsten.getFluid(1536))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.Adamantium, 12))
                .stationResearch(b -> b
                        .researchStack(GTCAHelper.getItem("ingot",GTMaterials.Tungsten, 1))
                        .CWUt(74)
                        .EUt(VA[122880]))
                .duration(600).EUt(VA[122880]).save(provider);


        GTCARecipeTypes.TEGMARK_FORGE.recipeBuilder("ohriharucon")
                .inputItems(GTCAItems.Proton.asStack(64))
                .inputItems(GTCAItems.Proton.asStack(35))
                .inputItems(GTCAItems.Electron.asStack(64))
                .inputItems(GTCAItems.Electron.asStack(64))
                .inputItems(GTCAItems.Electron.asStack(21))
                .inputItems(GTCAItems.Neutron.asStack(64))
                .inputItems(GTCAItems.Neutron.asStack(26))
                .inputFluids(GTMaterials.Palladium.getFluid(1728))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.Ohriharukon, 14))
                .stationResearch(b -> b
                        .researchStack(GTCAHelper.getItem("ingot",GTMaterials.Palladium, 1))
                        .CWUt(60)
                        .EUt(VA[ZPM]))
                .duration(510).EUt(VA[ZPM]).save(provider);

        GTCARecipeTypes.TEGMARK_FORGE.recipeBuilder("orundum")
                .inputItems(GTCAItems.Proton.asStack(38))
                .inputItems(GTCAItems.Electron.asStack(38))
                .inputItems(GTCAItems.Neutron.asStack(64))
                .inputItems(GTCAItems.Neutron.asStack(34))
                .inputFluids(GTMaterials.Titanium.getFluid(720))
                .outputItems(GTCAHelper.getItem("ingot", GTCAMaterials.Orundum, 5))
                .stationResearch(b -> b
                        .researchStack(GTCAHelper.getItem("ingot",GTMaterials.Titanium, 1))
                        .CWUt(60)
                        .EUt(VA[ZPM]))
                .duration(290).EUt(VA[ZPM]).save(provider);


    }


}
