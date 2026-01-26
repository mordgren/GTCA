package net.mordgren.gtca.common.machine.multiblock.electric.miner.data;

import net.minecraft.world.item.ItemStack;
import net.mordgren.gtca.common.data.GTCAItems;

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

    public DrillMaterialTier drillTier() {
        return requiredDrillTier();
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
    public boolean matchesDrillTier(DrillMaterialTier tier) {
        return tier == requiredDrillTier();
    }

    public DrillMaterialTier requiredDrillTier() {
        return switch (this) {
            case MK1_LV, MK2_MV -> DrillMaterialTier.STEEL;
            case MK3_HV, MK4_EV -> DrillMaterialTier.TITANIUM;
            case MK5_IV, MK6_LuV -> DrillMaterialTier.TUNGSTEN_STEEL;
            case MK7_ZPM, MK8_UV -> DrillMaterialTier.NAQUADAH;
            case MK9_UHV -> DrillMaterialTier.NAQUADAH_ALLOY;
            case MK10_UEV -> DrillMaterialTier.NEUTRONIUM;
        };
    }
    public ItemStack droneStack(int count) {
        return count <= 0 ? ItemStack.EMPTY : switch (this) {
            case MK1_LV -> new ItemStack(GTCAItems.MinerDroneLV.get(), count);
            case MK2_MV -> new ItemStack(GTCAItems.MinerDroneMV.get(), count);
            case MK3_HV -> new ItemStack(GTCAItems.MinerDroneHV.get(), count);
            case MK4_EV -> new ItemStack(GTCAItems.MinerDroneEV.get(), count);
            case MK5_IV -> new ItemStack(GTCAItems.MinerDroneIV.get(), count);
            case MK6_LuV -> new ItemStack(GTCAItems.MinerDroneLuV.get(), count);
            case MK7_ZPM -> new ItemStack(GTCAItems.MinerDroneZPM.get(), count);
            case MK8_UV -> new ItemStack(GTCAItems.MinerDroneUV.get(), count);
            case MK9_UHV -> new ItemStack(GTCAItems.MinerDroneUHV.get(), count);
            case MK10_UEV -> new ItemStack(GTCAItems.MinerDroneUEV.get(), count);
        };
    }
}
