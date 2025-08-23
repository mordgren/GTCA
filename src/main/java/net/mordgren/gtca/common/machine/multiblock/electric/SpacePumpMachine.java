package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

public class SpacePumpMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {


    public SpacePumpMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.tier = tier;
    }

    public final int tier;

}
