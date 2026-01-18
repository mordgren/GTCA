
package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

public class SpaceAssemblerMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {


    public final int moduleTier;

    private boolean enabledByElevator = false;

    public SpaceAssemblerMachine(IMachineBlockEntity holder, int moduleTier) {
        super(holder, moduleTier);
        this.moduleTier = moduleTier;
    }

    public void setEnabledByElevator(boolean enabled) {
        this.recipeLogic.setWorkingEnabled(enabled);


        if (this.recipeLogic != null) {
            this.recipeLogic.setWorkingEnabled(enabled);
        }
    }

    public boolean isEnabledByElevator() {
        return enabledByElevator;
    }

    @Override
    public int getTier() {
        return moduleTier;
    }
}

