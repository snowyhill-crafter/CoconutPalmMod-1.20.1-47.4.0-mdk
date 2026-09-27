package com.snowyhill.coconutpalmmod.worldgen.features;

import com.mojang.serialization.Codec;
import com.snowyhill.coconutpalmmod.worldgen.biome.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ShallowWaterFeature
        extends Feature<NoneFeatureConfiguration> {

    /*
     * このFeatureが対象にする水深。
     *
     * 3ブロック未満
     * → すでに浅いので変更しない
     *
     * 8ブロックより深い
     * → 沖なので変更しない
     */
    private static final int MIN_WATER_DEPTH = 3;
    private static final int MAX_WATER_DEPTH = 8;

    /*
     * 一度のFeature生成で作る浅瀬の半径。
     */
    private static final int MIN_RADIUS = 6;
    private static final int MAX_RADIUS = 10;

    /*
     * 一度に海底を持ち上げる最大量。
     *
     * 大きすぎると人工的な段差になるので4程度。
     */
    private static final int MAX_LIFT = 4;


    public ShallowWaterFeature(
            Codec<NoneFeatureConfiguration> codec
    ) {
        super(codec);
    }


    @Override
    public boolean place(
            FeaturePlaceContext<NoneFeatureConfiguration> context
    ) {

        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();

        int seaLevel = level.getSeaLevel();

        /*
         * 浅瀬パッチの大きさ。
         *
         * 半径6～10ブロック。
         */
        int radius =
                MIN_RADIUS
                        + random.nextInt(
                        MAX_RADIUS - MIN_RADIUS + 1
                );

        /*
         * パッチ中央部で目標とする水深。
         *
         * 2～3ブロック。
         */
        int targetDepth =
                2 + random.nextInt(2);


        boolean placedAny = false;

        BlockPos.MutableBlockPos mutable =
                new BlockPos.MutableBlockPos();


        for (int dx = -radius; dx <= radius; dx++) {

            for (int dz = -radius; dz <= radius; dz++) {

                double distance =
                        Math.sqrt(
                                dx * dx
                                        + dz * dz
                        );


                // 円形パッチの外側
                if (distance > radius) {
                    continue;
                }


                int x = origin.getX() + dx;
                int z = origin.getZ() + dz;


                /*
                 * 海面直下が水か確認。
                 *
                 * 海ではなく陸なら何もしない。
                 */
                mutable.set(
                        x,
                        seaLevel - 1,
                        z
                );

                if (!level.getFluidState(mutable)
                        .is(FluidTags.WATER)) {

                    continue;
                }


                /*
                 * Tropical Beach以外には
                 * 地形改変しない。
                 *
                 * Featureの円が隣接Biomeへ
                 * はみ出すのを防ぐ。
                 */
                if (!level.getBiome(mutable)
                        .is(ModBiomes.TROPICAL_BEACH)) {

                    continue;
                }


                /*
                 * OCEAN_FLOOR_WGは
                 * 海底のすぐ上のY座標を返すため、
                 *
                 * -1して海底ブロックそのものを取得。
                 */
                int oceanFloorY =
                        level.getHeight(
                                Heightmap.Types.OCEAN_FLOOR_WG,
                                x,
                                z
                        ) - 1;


                /*
                 * 水深。
                 *
                 * 海面の最後の水ブロックが
                 * seaLevel - 1。
                 */
                int waterDepth =
                        (seaLevel - 1)
                                - oceanFloorY;


                /*
                 * すでに浅い場所は変更しない。
                 */
                if (waterDepth < MIN_WATER_DEPTH) {
                    continue;
                }


                /*
                 * 深海まで埋め立てない。
                 */
                if (waterDepth > MAX_WATER_DEPTH) {
                    continue;
                }


                /*
                 * パッチ中心ほど強く盛り上げ、
                 * 外周では0に近づける。
                 *
                 * これによって丸い台地ではなく、
                 * なだらかな浅瀬にする。
                 */
                double falloff =
                        1.0D
                                - distance / radius;


                /*
                 * 本来必要な持ち上げ量。
                 */
                int fullLift =
                        waterDepth
                                - targetDepth;


                /*
                 * 中心からの距離によって
                 * 持ち上げ量を減らす。
                 */
                int lift =
                        (int) Math.floor(
                                fullLift * falloff
                        );


                /*
                 * 最大4ブロックまで。
                 */
                lift = Math.min(
                        lift,
                        MAX_LIFT
                );


                if (lift <= 0) {
                    continue;
                }


                /*
                 * 盛り上げ後の海底Y。
                 */
                int newFloorY =
                        oceanFloorY + lift;


                /*
                 * 水面まで埋めて陸地化しないよう、
                 * 最低1ブロックは水を残す。
                 */
                newFloorY =
                        Math.min(
                                newFloorY,
                                seaLevel - 2
                        );


                /*
                 * 実際に置くブロック数。
                 */
                int actualLift =
                        newFloorY
                                - oceanFloorY;


                if (actualLift <= 0) {
                    continue;
                }


                /*
                 * 海底を盛る。
                 *
                 * 上2層 = 砂
                 * それより下 = 砂岩
                 *
                 * 例：
                 *
                 * 水
                 * 水
                 * 砂
                 * 砂
                 * 砂岩
                 * 元の海底
                 */
                for (
                        int y = oceanFloorY + 1;
                        y <= newFloorY;
                        y++
                ) {

                    mutable.set(
                            x,
                            y,
                            z
                    );


                    /*
                     * 水以外を無理に潰さない。
                     */
                    if (!level.getFluidState(mutable)
                            .is(FluidTags.WATER)) {

                        break;
                    }


                    int blocksBelowTop =
                            newFloorY - y;


                    BlockState state;


                    /*
                     * 上2ブロックは砂。
                     */
                    if (blocksBelowTop <= 1) {

                        state =
                                Blocks.SAND
                                        .defaultBlockState();

                    } else {

                        /*
                         * それより下は砂岩。
                         */
                        state =
                                Blocks.SANDSTONE
                                        .defaultBlockState();
                    }


                    level.setBlock(
                            mutable,
                            state,
                            2
                    );

                    placedAny = true;
                }
            }
        }


        return placedAny;
    }
}