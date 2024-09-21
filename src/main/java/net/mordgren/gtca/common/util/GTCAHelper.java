package net.mordgren.gtca.common.util;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GTCAHelper {
    public static ItemStack getItem(String type, Material name, int amount) {
        return ChemicalHelper.get(TagPrefix.getPrefix(type), name, amount);
    }

    public static TagKey<Item> getByTag(String namespace, String tag) {
        return ItemTags.create(new ResourceLocation(namespace, tag));
    }
}
