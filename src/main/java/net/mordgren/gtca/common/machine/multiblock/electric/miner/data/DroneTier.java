package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import net.minecraft.util.Mth;

public enum DroneTier {
    MK1_LV(1),
    MK2_MV(2),
    MK3_HV(3),
    MK4_EV(4),
    MK5_IV(5),
    MK6_LuV(6),
    MK7_ZPM(7),
    MK8_UV(8),
    MK9_UHV(9),
    MK10_UEV(10);

    private final int index;

    DroneTier(int index) {
        this.index = index;
    }


    public int index() {
        return index;
    }


    public double eutMultiplier() {
        return Math.sqrt(index);
    }


    public double timeMultiplier() {
        return 1.0 / Math.sqrt(index);
    }


    public int sizeAdd() {
        int shift = Math.max(0, Math.min(index - 1, 30)); // safety
        return (int)((1L << shift) - 1L);
    }

    public boolean isBetween(DroneTier min, DroneTier max) {
        return this.index >= min.index && this.index <= max.index;
    }
}
