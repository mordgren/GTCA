package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import net.mordgren.gtca.common.data.GTCAMachines;
import java.util.function.Consumer;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.HULL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

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
    }

}

