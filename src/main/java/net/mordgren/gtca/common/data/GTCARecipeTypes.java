package net.mordgren.gtca.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;

public class GTCARecipeTypes {

    public static void init() {
    }

    public static final GTRecipeType STEAM_PRESSURIZER = register("steam_pressurizer", MULTIBLOCK)
            .setMaxIOSize(0, 0, 1, 1)
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMPRESSOR)
            .setEUIO(IO.IN);

    public static final GTRecipeType CHEMICAL_GENERATOR = register("chemical_generator", MULTIBLOCK)
            .setMaxIOSize(2,0,2,0)
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COMBUSTION)
            .setEUIO(IO.OUT);

    public static final GTRecipeType GREEN_HOUSE = register("green_house", MULTIBLOCK)
            .setMaxIOSize(3,4,1,0)
            .setSlotOverlay(true, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH)
            .setEUIO(IO.IN);

    public static final GTRecipeType POLYMERIZER = register("polymerizer", MULTIBLOCK)
            .setMaxIOSize(2,2,3,2)
            .setSlotOverlay(false,false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL)
            .setEUIO(IO.IN);

    public static final GTRecipeType EXTREME_HEAT_EXCHANGER = register("extreme_heat_exchanger", MULTIBLOCK)
            .setMaxIOSize(0,0,2,3)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING)
            .setEUIO(IO.NONE);

    public static final GTRecipeType SHD_STEAM_TURBINE = register("shd_turbine", MULTIBLOCK)
            .setMaxIOSize(0,0,1,1)
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.TURBINE)
            .setEUIO(IO.OUT);

    public static final GTRecipeType INDUSTRIAL_COKE_OVEN = register("industrial_coke_oven", MULTIBLOCK)
            .setMaxIOSize(2,9,1,1)
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressTexture.FillDirection.DOWN_TO_UP)
            .setSound(GTSoundEntries.FURNACE)
            .setEUIO(IO.IN);
}