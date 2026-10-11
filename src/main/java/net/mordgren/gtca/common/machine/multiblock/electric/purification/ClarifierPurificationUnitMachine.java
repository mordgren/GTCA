package net.mordgren.gtca.common.machine.multiblock.electric.purification;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.List;


public class ClarifierPurificationUnitMachine extends PurificationUnitMachine {

    public static final int FILTER_CONSUME_CHANCE = 2_000; // 20% out of 10000

    public ClarifierPurificationUnitMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    protected int getWaterTier() {
        return 1;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        textList.add(Component.literal("Activated Carbon Filter consume chance: 20% / cycle")
                .withStyle(ChatFormatting.GRAY));
    }
}
