package com.snowyhill.coconutpalmmod.client;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;


public final class ModWoodTypes {
    // 名前を "appletreemod:apple" に変更する
    public static final WoodType COCONUT_PALM =
            WoodType.register(new WoodType(CoconutPalmMod.MOD_ID + ":coconut_palm", BlockSetType.OAK));


}