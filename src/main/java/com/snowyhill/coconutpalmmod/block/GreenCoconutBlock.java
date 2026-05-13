package com.snowyhill.coconutpalmmod.block;

import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.item.BoneMealItem;

public class GreenCoconutBlock extends BushBlock implements BonemealableBlock {

    private static final VoxelShape SHAPE = Shapes.box(0.25, 0.375, 0.25, 0.75, 0.875, 0.75);

    // 1tickあたりの成熟確率
    private static final float RIPE_CHANCE = 0.10f;

    public GreenCoconutBlock(Properties props) {
        super(props);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !level.getBlockState(pos.above()).isAir();
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos,
                                Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        if (!this.canSurvive(state, level, pos)) {
            if (!level.isClientSide) {
                popResource(level, pos, new ItemStack(ModItems.GREEN_COCONUT.get()));
            }
            level.removeBlock(pos, false);
        }
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, isMoving);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!this.canSurvive(state, level, pos)) {
            popResource(level, pos, new ItemStack(ModItems.GREEN_COCONUT.get()));
            level.removeBlock(pos, false);
            return;
        }

        if (random.nextFloat() < RIPE_CHANCE) {
            level.setBlock(pos, ModBlocks.MATURE_COCONUT_BLOCK.get().defaultBlockState(), 3);
        }

        super.randomTick(state, level, pos, random);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return this.canSurvive(state, level, pos);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (this.canSurvive(state, level, pos)) {
            level.setBlock(pos, ModBlocks.MATURE_COCONUT_BLOCK.get().defaultBlockState(), 3);
            BoneMealItem.addGrowthParticles(level, pos, 15);
        }
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
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