package com.snowyhill.coconutpalmmod.worldgen.features.decorator;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class CoconutPalmGroundDecorator extends TreeDecorator {

    public static final Codec<CoconutPalmGroundDecorator> CODEC =
            Codec.unit(CoconutPalmGroundDecorator::new);

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecorators.COCONUT_PALM_GROUND_DECORATOR.get();
    }

    @Override
    public void place(Context ctx) {

        RandomSource random = ctx.random();

        // 幹の一番下
        BlockPos trunkBase = ctx.logs().stream()
                .min((a, b) -> Integer.compare(a.getY(), b.getY()))
                .orElse(null);

        if (trunkBase == null) return;

        BlockPos center = trunkBase.below();

        int radius = 7 + random.nextInt(5);//草地のサイズとランダムな変形

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {

                double distance = Math.sqrt(x * x + z * z);

                // 円形＋ランダム崩し
                double noise =
                        (random.nextFloat() - 0.5F) * 3.5F;

                if (distance > radius + noise){
                    continue;
                }

                BlockPos groundPos = center.offset(x, 0, z);

                // 上方向10マスまで見て、一番上の砂を草地化
                for (int y = 10; y >= 0; y--) {

                    BlockPos checkPos = groundPos.above(y);

                    // 砂だけgrass_block化
                    if (ctx.level().isStateAtPosition(
                            checkPos,
                            state -> state.is(Blocks.SAND)
                                    || state.is(Blocks.RED_SAND)
                    )) {

                        // 表面 grass_block
                        ctx.setBlock(
                                checkPos,
                                Blocks.GRASS_BLOCK.defaultBlockState()
                        );

// ★追加：下1マスを dirt 化
                        BlockPos belowPos = checkPos.below();

                        if (ctx.level().isStateAtPosition(
                                belowPos,
                                state -> state.is(Blocks.SAND)
                                        || state.is(Blocks.RED_SAND)
                        )) {

                            ctx.setBlock(
                                    belowPos,
                                    Blocks.DIRT.defaultBlockState()
                            );
                        }

                        BlockPos abovePos = checkPos.above();

                        // 草生成
                        if (ctx.isAir(abovePos)
                                && random.nextFloat() < 0.30F) {

                            ctx.setBlock(
                                    abovePos,
                                    Blocks.GRASS.defaultBlockState()
                            );
                        }

                        // 一番上だけ変換したら終了
                        break;
                    }
                }
            }
        }
    }
}