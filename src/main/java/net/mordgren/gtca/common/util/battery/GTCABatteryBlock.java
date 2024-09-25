package net.mordgren.gtca.common.util.battery;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.AppearanceBlock;
import com.gregtechceu.gtceu.api.machine.multiblock.IBatteryData;
import lombok.Getter;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public class GTCABatteryBlock extends AppearanceBlock {

    @Getter
    private final IBatteryData data;

    public GTCABatteryBlock(Properties properties, IBatteryData data) {
        super(properties);
        this.data = data;
    }

    public enum BatteryPartType implements StringRepresentable, IBatteryData {

        PROTON_CELL(GTValues.LuV, 4_915_200_000L * 6),
        ELECTRON_CELL(GTValues.ZPM, 19_660_800_000L * 6),
        QUARK_ENTANGLEMENT(GTValues.UV, 78_643_200_000L * 6),
        GRAVITON_ANOMALY(GTValues.UHV, 1_024_000_000_000_000L * 6),
        ;

        private final int tier;
        private final long capacity;

        BatteryPartType() {
            this.tier = -1;
            this.capacity = 0;
        }

        BatteryPartType(int tier, long capacity) {
            this.tier = tier;
            this.capacity = capacity;
        }

        @Override
        public int getTier() {
            return tier;
        }

        @Override
        public long getCapacity() {
            return capacity;
        }

        // must be separately named because of reobf issue
        @NotNull
        @Override
        public String getBatteryName() {
            return name().toLowerCase();
        }

        @NotNull
        @Override
        public String getSerializedName() {
            return getBatteryName();
        }
    }
}