package net.mordgren.gtca.common.util;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ScrapBox extends Item {
    public ScrapBox(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            List<Item> items = ForgeRegistries.ITEMS.getValues().stream().toList();
            Item randomItem = items.get(world.getRandom().nextInt(items.size()));
            ItemEntity drop = new ItemEntity(world, player.getX(), player.getY() + 1, player.getZ(), new ItemStack(randomItem));
            world.addFreshEntity(drop);

            player.getItemInHand(hand).hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }
}
