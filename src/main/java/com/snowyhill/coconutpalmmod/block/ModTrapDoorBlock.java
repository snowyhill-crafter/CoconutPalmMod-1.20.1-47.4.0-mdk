package com.snowyhill.coconutpalmmod.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModTrapDoorBlock extends TrapDoorBlock {

    public ModTrapDoorBlock(Properties properties) {
        super(properties, BlockSetType.OAK);  // ← 木材のトラップドア
    }



    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20; // 拡散速度
    }
}