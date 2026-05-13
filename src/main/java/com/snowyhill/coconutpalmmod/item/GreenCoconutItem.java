package com.snowyhill.coconutpalmmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class GreenCoconutItem extends Item {
    public GreenCoconutItem(Properties props) { super(props); }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK; // 飲むアニメーション
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32; // 標準の飲用時間
    }
}
