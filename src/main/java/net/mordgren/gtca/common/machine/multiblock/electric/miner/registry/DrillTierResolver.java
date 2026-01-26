package net.mordgren.gtca.common.machine.multiblock.electric.miner.registry;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.data.DrillMaterialTier;
import org.jetbrains.annotations.Nullable;

public final class DrillTierResolver {

    private DrillTierResolver() {}

    @Nullable
    public static DrillMaterialTier resolveTip(ItemStack stack) {
        return resolveByPrefix(stack, TagPrefix.toolHeadDrill);
    }

    @Nullable
    public static DrillMaterialTier resolveRod(ItemStack stack) {
        return resolveByPrefix(stack, TagPrefix.rodLong); // longRod
    }

    @Nullable
    private static DrillMaterialTier resolveByPrefix(ItemStack stack, TagPrefix expected) {
        if (stack == null || stack.isEmpty()) return null;

        Item item = stack.getItem();
        TagPrefix p = ChemicalHelper.getPrefix(item);
        if (p != expected) return null;

        MaterialEntry entry = ChemicalHelper.getMaterialEntry(item);
        if (entry == null) return null;

        Material m = entry.material();
        if (m == null || m == GTMaterials.NULL) return null;

        return DrillMaterialTier.fromMaterial(m);
    }
}