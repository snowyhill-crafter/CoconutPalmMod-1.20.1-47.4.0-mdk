package com.snowyhill.coconutpalmmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HibiscusBlock extends BushBlock implements BonemealableBlock {

    private static final VoxelShape SHAPE = Shapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D));

    private final AbstractTreeGrower grower;

    public HibiscusBlock(AbstractTreeGrower grower, Properties properties) {
        super(properties);
        this.grower = grower;
    }

    @Override
    public VoxelShape getShape(BlockState state,
                               BlockGetter level,
                               BlockPos pos,
                               CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state,
                                 BlockGetter level,
                                 BlockPos pos) {

        return state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.DIRT);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level,
                                         BlockPos pos,
                                         BlockState state,
                                         boolean clientSide) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(net.minecraft.world.level.Level level,
                                     RandomSource random,
                                     BlockPos pos,
                                     BlockState state) {
        return random.nextFloat() < 0.9F;
    }

    @Override
    public void performBonemeal(ServerLevel level,
                                RandomSource random,
                                BlockPos pos,
                                BlockState state) {

        grower.growTree(level,
                level.getChunkSource().getGenerator(),
                pos,
                state,
                random);
    }
}