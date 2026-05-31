package com.snowyhill.coconutpalmmod.worldgen.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.biome.Biomes; // ★追加
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class BeachGrassPatchPlacement extends PlacementFilter {

    public static final Codec<BeachGrassPatchPlacement> CODEC =
            Codec.INT.xmap(
                    BeachGrassPatchPlacement::new,
                    p -> p.minDistanceFromWater
            );

    private final int minDistanceFromWater;

    public BeachGrassPatchPlacement(int minDistanceFromWater) {
        this.minDistanceFromWater = minDistanceFromWater;
    }

    @Override
    protected boolean shouldPlace(PlacementContext context,
                                  RandomSource random,
                                  BlockPos pos) {

        LevelSimulatedReader level = context.getLevel();

        BlockPos groundPos = pos.below();

        // =========================================================
        // 水との距離チェック
        // =========================================================
        for (int x = -minDistanceFromWater; x <= minDistanceFromWater; x++) {
            for (int z = -minDistanceFromWater; z <= minDistanceFromWater; z++) {

                BlockPos checkPos = groundPos.offset(x, 0, z);

                if (level.isFluidAtPosition(
                        checkPos,
                        fluid -> fluid.is(FluidTags.WATER)
                )) {
                    return false;
                }

                if (level.isFluidAtPosition(
                        checkPos.below(),
                        fluid -> fluid.is(FluidTags.WATER)
                )) {
                    return false;
                }
            }
        }

        // =========================================================
        // ★追加：
        // 周囲に寒冷バイオームがある場合は生成拒否
        // =========================================================
        /*
        for (int x = -24; x <= 24; x += 8) {
            for (int z = -24; z <= 24; z += 8) {

                var biomeHolder = context.getLevel()
                        .getBiome(pos.offset(x, 0, z));

                // 寒冷系バイオームを検出したら拒否
                if (
                        biomeHolder.is(Biomes.TAIGA)
                                || biomeHolder.is(Biomes.SNOWY_TAIGA)
                                || biomeHolder.is(Biomes.GROVE)
                                || biomeHolder.is(Biomes.SNOWY_PLAINS)
                                || biomeHolder.is(Biomes.FROZEN_PEAKS)
                                || biomeHolder.is(Biomes.JAGGED_PEAKS)
                ) {
                    return false;
                }
            }
        }
*/
        return true;
    }

    @Override
    public PlacementModifierType<?> type() {
        return ModPlacementModifiers.BEACH_GRASS_PATCH.get();
    }

    public static BeachGrassPatchPlacement of(int minDistanceFromWater) {
        return new BeachGrassPatchPlacement(minDistanceFromWater);
    }
}