package com.snowyhill.coconutpalmmod.worldgen.features.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {

    // 1.20.1向け: MapCodec ではなく Codec
    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            foliagePlacerParts(instance).and(
                    IntProvider.codec(1, 8)
                            .fieldOf("frond_length")
                            .orElse(UniformInt.of(4, 4))
                            .forGetter(p -> p.frondLength)
            ).and(
                    Codec.INT
                            .fieldOf("frond_count")
                            .orElse(8)
                            .forGetter(p -> p.frondCount)
            ).apply(instance, PalmFoliagePlacer::new)
    );

    private final IntProvider frondLength;
    private final int frondCount;

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider frondLength, int frondCount) {
        super(radius, offset);
        this.frondLength = frondLength;
        this.frondCount = frondCount;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.PALM_FOLIAGE.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter setter, RandomSource random,
                                 TreeConfiguration config, int trunkHeight, FoliageAttachment attach,
                                 int foliageHeight, int foliageRadius, int offset) {

        BlockPos center = attach.pos();

        int[][] dirs = new int[][]{
                { 1,  0}, {-1,  0}, { 0,  1}, { 0, -1},
                { 1,  1}, { 1, -1}, {-1,  1}, {-1, -1}
        };

        int len = Math.max(1, this.frondLength.getMinValue());

        // 中心
        tryPlaceLeaf(level, setter, random, config, center);

        for (int i = 0; i < dirs.length; i++) {
            int[] d = dirs[i];
            int dx = Integer.signum(d[0]);
            int dz = Integer.signum(d[1]);
            boolean diagonal = (dx != 0 && dz != 0);

            // 斜めだけ少し短く
            int dirLen = Math.max(1, len - (diagonal ? 1 : 0));

            BlockPos prev = center;

            for (int k = 1; k <= dirLen; k++) {
                BlockPos cur = prev.offset(dx, 0, dz);

                // 斜めは毎ステップ左右1マスずつ接続
                if (diagonal) {
                    tryPlaceLeaf(level, setter, random, config, prev.offset(dx, 0, 0));
                    tryPlaceLeaf(level, setter, random, config, prev.offset(0, 0, dz));
                }

                // 葉先だけ少し下げる
                BlockPos place = (k == dirLen) ? cur.below() : cur;

                // 主軸の葉
                tryPlaceLeaf(level, setter, random, config, place);

                // 少し厚み
                tryPlaceLeaf(level, setter, random, config, place.above());

                prev = cur;
            }
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return true;
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 1;
    }
}