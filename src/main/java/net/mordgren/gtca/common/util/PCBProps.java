package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import lombok.Getter;

public class PCBProps extends WorkableElectricMultiblockMachine implements ITieredMachine {


    public PCBProps(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.tier = tier;
    }

    @Getter
    public final int tier;
}
