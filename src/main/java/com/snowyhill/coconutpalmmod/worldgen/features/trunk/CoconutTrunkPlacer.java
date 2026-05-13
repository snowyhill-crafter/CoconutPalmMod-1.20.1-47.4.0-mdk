package com.snowyhill.coconutpalmmod.worldgen.features.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.snowyhill.coconutpalmmod.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class CoconutTrunkPlacer extends TrunkPlacer {

    // 1.20.1 向け: MapCodec ではなく Codec
    public static final Codec<CoconutTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            trunkPlacerParts(instance).and(
                    Codec.INT.fieldOf("bend_length").orElse(3).forGetter(p -> p.bendLength)
            ).and(
                    IntProvider.codec(1, 32).fieldOf("bend_start_offset")
                            .orElse(UniformInt.of(4, 6)).forGetter(p -> p.bendStartOffset)
            ).apply(instance, CoconutTrunkPlacer::new)
    );

    private final int bendLength;
    private final IntProvider bendStartOffset;

    public CoconutTrunkPlacer(int baseHeight, int heightRandA, int heightRandB,
                              int bendLength, IntProvider bendStartOffset) {
        super(baseHeight, heightRandA, heightRandB);
        this.bendLength = bendLength;
        this.bendStartOffset = bendStartOffset;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacers.COCONUT_TRUNK.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level,
                                                            BiConsumer<BlockPos, BlockState> placer,
                                                            RandomSource random,
                                                            int height,
                                                            BlockPos start,
                                                            TreeConfiguration config) {
        setPalmBaseAt(level, placer, random, start.below(), config);

        int startOffset = Mth.clamp(this.bendStartOffset.sample(random), 1, Math.max(1, height - 1));
        int bendStartY = height - startOffset;

        Direction bendDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        final int bendEvery = 2;
        int bentSteps = 0;

        BlockPos.MutableBlockPos pos = start.mutable();
        for (int y = 0; y < height; y++) {
            placeLog(level, placer, random, pos, config);

            if (y >= bendStartY && bentSteps < bendLength
                    && ((y - bendStartY) % bendEvery) == 0) {
                pos.move(bendDir);
                bentSteps++;
            }

            pos.move(Direction.UP);
        }

        BlockPos top = pos.below().immutable();
        return List.of(new FoliagePlacer.FoliageAttachment(top, 0, false));
    }

    private void setPalmBaseAt(LevelSimulatedReader level,
                               BiConsumer<BlockPos, BlockState> placer,
                               RandomSource random,
                               BlockPos pos,
                               TreeConfiguration config) {
        Predicate<BlockState> isSandLike = s -> s.is(ModTags.Blocks.COCONUT_SPROUTS_PLANTABLE);
        if (level.isStateAtPosition(pos, isSandLike)) return;

        //setDirtAt(level, placer, random, pos, config); 強制的に土にするためコメントアウト
    }
}