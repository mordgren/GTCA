package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

public class SpaceMinerMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {


    public SpaceMinerMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.tier = tier;
    }

    public final int tier;

}
