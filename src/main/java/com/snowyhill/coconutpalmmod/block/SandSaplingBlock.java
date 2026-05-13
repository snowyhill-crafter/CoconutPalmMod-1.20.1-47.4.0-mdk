package com.snowyhill.coconutpalmmod.block;

import com.snowyhill.coconutpalmmod.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class SandSaplingBlock extends SaplingBlock {

    public SandSaplingBlock(AbstractTreeGrower grower, Properties props) {
        super(grower, props);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModTags.Blocks.COCONUT_SPROUTS_PLANTABLE)
                || state.is(Blocks.SAND)
                || state.is(Blocks.RED_SAND)
                || state.is(Blocks.DIRT)
                || state.is(Blocks.GRASS_BLOCK)
                || super.mayPlaceOn(state, level, pos);
    }
}