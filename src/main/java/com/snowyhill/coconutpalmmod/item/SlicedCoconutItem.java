package com.snowyhill.coconutpalmmod.item;

import com.snowyhill.coconutpalmmod.registry.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SlicedCoconutItem extends Item {

    public SlicedCoconutItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            ItemStack shell = new ItemStack(ModItems.COCONUT_SHELL.get());

            if (!player.getInventory().add(shell)) {
                player.drop(shell, false);
            }
        }

        return result;
    }
}