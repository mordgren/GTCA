package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraft.world.item.ItemStack;

public class GTCAHelper {
    public static ItemStack getItem(String type, Material name, int amount) {
        return ChemicalHelper.get(TagPrefix.getPrefix(type), name, amount);
    }
}
