package com.snowyhill.coconutpalmmod.block;

import com.snowyhill.coconutpalmmod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MatureCoconutBlock extends BushBlock {

    private static final VoxelShape SHAPE = Shapes.box(0.25, 0.375, 0.25, 0.75, 0.875, 0.75);

    public MatureCoconutBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState above = level.getBlockState(pos.above());
        return !above.isAir();
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos,
                                Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        if (!this.canSurvive(state, level, pos)) {
            if (!level.isClientSide) {
                popResource(level, pos, new ItemStack(ModItems.MATURE_COCONUT.get()));
            }
            level.removeBlock(pos, false);
        }
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, isMoving);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!this.canSurvive(state, level, pos)) {
            popResource(level, pos, new ItemStack(ModItems.MATURE_COCONUT.get()));
            level.removeBlock(pos, false);
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!level.isClientSide) {
            dropResources(state, level, hit.getBlockPos());
            level.removeBlock(hit.getBlockPos(), false);
        }
        super.onProjectileHit(level, state, hit, projectile);
    }
}