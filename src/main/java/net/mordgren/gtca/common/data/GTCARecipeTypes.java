package net.mordgren.gtca.common.data;


import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;

public class GTCARecipeTypes {

    public static final GTRecipeType STEAM_PRESSURIZER = register("steam_pressurizer", MULTIBLOCK)
            .setMaxIOSize(0, 0, 1, 1)
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING)
            .setEUIO(IO.IN);

    public static void init() {
    }
}