package com.snowyhill.coconutpalmmod.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModButtonBlock extends ButtonBlock {

    public ModButtonBlock(Properties properties) {
        super(properties, BlockSetType.OAK, 30, true);
        // 引数の意味：
        // properties : Block のプロパティ
        // BlockSetType.OAK : 木製ボタンの挙動
        // 30 : tick length（木のボタン押下時間）
        // true : mob が踏めるか
    }



    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20; // 木材と同じ
    }
}