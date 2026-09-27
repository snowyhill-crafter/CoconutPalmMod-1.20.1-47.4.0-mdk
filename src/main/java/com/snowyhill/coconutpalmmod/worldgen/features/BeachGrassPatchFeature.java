//package com.snowyhill.coconutpalmmod.worldgen.features;

////import com.mojang.serialization.Codec;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.WorldGenLevel;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
//import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

//public class BeachGrassPatchFeature extends Feature<NoneFeatureConfiguration> {

  //  public BeachGrassPatchFeature(Codec<NoneFeatureConfiguration> codec) {
  //      super(codec);
  //  }
//
//    @Override
//    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
//
//        WorldGenLevel level = context.level();
//
//        BlockPos origin = context.origin();
//
//        RandomSource random = context.random();
//
//        // =========================================================
//        // 草地サイズ
//        // =========================================================
//        int radius = 10 + random.nextInt(6);
//
//        // =========================================================
//        // 草地生成
//        // =========================================================
//        for (int x = -radius; x <= radius; x++) {
//            for (int z = -radius; z <= radius; z++) {
//
//                double distance = Math.sqrt(x * x + z * z);
//
//                double noise =
//                        (random.nextFloat() - 0.5F) * 1.0F;//草地の形状
//
//                if (distance > radius + noise) {
//                    continue;
//                }
//
//                BlockPos groundPos = origin.offset(x, 0, z);
//
//                // 上方向探索
//                for (int y = 10; y >= 0; y--) {
//
//                    BlockPos checkPos = groundPos.above(y);
//
//                    BlockState state =
//                            level.getBlockState(checkPos);
//
//                    // =====================================================
//                    // 砂だけ変換
//                    // =====================================================
//                    if (state.is(Blocks.SAND)
//                            || state.is(Blocks.RED_SAND)) {
//
//                        // =================================================
//                        // 表面 grass_block
//                        // =================================================
//                        level.setBlock(
//                                checkPos,
//                                Blocks.GRASS_BLOCK.defaultBlockState(),
//                                3
//                        );
//
//                        // =================================================
//                        // 下層 dirt化
//                        // =================================================
//                        for (int depth = 1; depth <= 2; depth++) {
//
//                            BlockPos dirtPos =
//                                    checkPos.below(depth);
//
//                            BlockState dirtState =
//                                    level.getBlockState(dirtPos);
//
//                            if (dirtState.is(Blocks.SAND)
//                                    || dirtState.is(Blocks.RED_SAND)) {
//
//                                level.setBlock(
//                                        dirtPos,
//                                        Blocks.DIRT.defaultBlockState(),
//                                        3
//                                );
//                            }
//                        }
//
//                        // =================================================
//                        // ★追加：
//                        // 周囲1マス下の砂も grass化
//                        // 坂道の砂露出防止
//                        // =================================================
//                        for (Direction dir : Direction.Plane.HORIZONTAL) {
//
//                            BlockPos sidePos =
//                                    checkPos.relative(dir).below();
//
//                            BlockState sideState =
//                                    level.getBlockState(sidePos);
//
//                            if (sideState.is(Blocks.SAND)
//                                    || sideState.is(Blocks.RED_SAND)) {
//
//                                // grass化
//                                level.setBlock(
//                                        sidePos,
//                                        Blocks.GRASS_BLOCK.defaultBlockState(),
//                                        3
//                                );
//
//                                // 下を dirt化
//                                for (int depth = 1; depth <= 2; depth++) {
//
//                                    BlockPos dirtPos =
//                                            sidePos.below(depth);
//
//                                    BlockState dirtState =
//                                            level.getBlockState(dirtPos);
//
//                                    if (dirtState.is(Blocks.SAND)
//                                            || dirtState.is(Blocks.RED_SAND)) {
//
//                                        level.setBlock(
//                                                dirtPos,
//                                                Blocks.DIRT.defaultBlockState(),
//                                                3
//                                        );
//                                    }
//                                }
//                            }
//                        }
//
//                        // =================================================
//                        // 草生成
//                        // =================================================
//                        BlockPos abovePos = checkPos.above();
//
//                        if (level.isEmptyBlock(abovePos)
//                                && random.nextFloat() < 0.30F) {
//
//                            level.setBlock(
//                                    abovePos,
//                                    Blocks.GRASS.defaultBlockState(),
//                                    3
//                            );
//                        }
//
//                        break;
//                    }
//                }
//            }
//        }
//
//        // =========================================================
//        // ココヤシ生成
//        // =========================================================
//        int palmCount = 2 + random.nextInt(3);
//
//        for (int i = 0; i < palmCount; i++) {
//
//            int offsetX =
//                    random.nextInt(radius * 2) - radius;
//
//            int offsetZ =
//                    random.nextInt(radius * 2) - radius;
//
//            BlockPos palmPos =
//                    origin.offset(offsetX, 0, offsetZ);
//
//            // 地面探索
//            while (level.isEmptyBlock(palmPos)
//                    && palmPos.getY() > level.getMinBuildHeight()) {
//
//                palmPos = palmPos.below();
//            }
//
//            // grass_block上限定
//            if (!level.getBlockState(palmPos)
//                    .is(Blocks.GRASS_BLOCK)) {
//                continue;
//            }
//
//            BlockPos placePos = palmPos.above();
//
//            // ConfiguredFeature取得
//            var configuredFeature =
//                    level.registryAccess()
//                            .registryOrThrow(Registries.CONFIGURED_FEATURE)
//                            .get(ModFeatures.COCONUT_PALM_TREE_KEY.location());
//
//            if (configuredFeature != null) {
//
//                configuredFeature.place(
//                        level,
//                        context.chunkGenerator(),
//                        random,
//                        placePos
//                );
//            }
//        }
//
//        return true;
//    }
//}