package net.mordgren.gtca.common.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ScrapBox extends Item {
    public ScrapBox(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!world.isClientSide) {
            List<Item> items = ForgeRegistries.ITEMS.getValues().stream().toList();
            Item randomItem = items.get(world.getRandom().nextInt(items.size()));
            ItemEntity drop = new ItemEntity(world, player.getX(), player.getY() + 1, player.getZ(), new ItemStack(randomItem));
            world.addFreshEntity(drop);

            stack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("gtca.item.scrapbox.tooltip").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
    }
}
