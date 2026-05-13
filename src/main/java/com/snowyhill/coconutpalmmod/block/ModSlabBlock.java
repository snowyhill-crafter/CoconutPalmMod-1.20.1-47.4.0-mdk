package com.snowyhill.coconutpalmmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModSlabBlock extends SlabBlock {

    public ModSlabBlock(Properties props) {
        super(props);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter getter, BlockPos pos, Direction dir) {
        return 5;  // Planks系スラブは普通この値
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter getter, BlockPos pos, Direction dir) {
        return 20; // 拡散速度は20
    }
}