package com.snowyhill.coconutpalmmod.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;
import terrablender.worldgen.RegionUtils;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {


    // =========================================================
    // Tropical Beach用
    // =========================================================

    private static final Climate.Parameter WARM_TO_HOT =
            ParameterUtils.Temperature.span(
                    ParameterUtils.Temperature.WARM,
                    ParameterUtils.Temperature.HOT
            );


    private static final Climate.Parameter NEUTRAL_TO_HUMID =
            ParameterUtils.Humidity.span(
                    ParameterUtils.Humidity.NEUTRAL,
                    ParameterUtils.Humidity.HUMID
            );


    public ModOverworldRegion(
            ResourceLocation name,
            int weight
    ) {

        super(
                name,
                RegionType.OVERWORLD,
                weight
        );
    }


    @Override
    public void addBiomes(
            Registry<Biome> registry,
            Consumer<Pair<
                    Climate.ParameterPoint,
                    ResourceKey<Biome>
                    >> mapper
    ) {


        // =====================================================
        // Tropical Beach
        //
        // バニラBeachのParameterPointを利用して
        // 暖かいBeachだけを置換
        // =====================================================

        addModifiedVanillaOverworldBiomes(
                mapper,
                builder -> {

                    RegionUtils
                            .getVanillaParameterPoints(Biomes.BEACH)
                            .stream()

                            .filter(
                                    ModOverworldRegion::isTropicalClimate
                            )

                            .forEach(
                                    point ->
                                            builder.replaceBiome(
                                                    point,
                                                    ModBiomes.TROPICAL_BEACH
                                            )
                            );
                }
        );


        // =====================================================
        // Coconut Palm Forest
        // =====================================================

        VanillaParameterOverlayBuilder forestBuilder =
                new VanillaParameterOverlayBuilder();


        new ParameterUtils.ParameterPointListBuilder()

                // 暖かい ～ 暑い
                .temperature(
                        ParameterUtils.Temperature.WARM,
                        ParameterUtils.Temperature.HOT
                )

                // 湿潤 ～ 高湿度
                .humidity(
                        ParameterUtils.Humidity.WET,
                        ParameterUtils.Humidity.HUMID
                )

                // 海岸寄り限定
                .continentalness(
                        ParameterUtils.Continentalness.COAST,
                        ParameterUtils.Continentalness.NEAR_INLAND
                )

                // 平坦～比較的侵食された地形
                .erosion(
                        ParameterUtils.Erosion.EROSION_4,
                        ParameterUtils.Erosion.EROSION_5,
                        ParameterUtils.Erosion.EROSION_6
                )
                // 山岳系のWeirdnessを避ける
                .weirdness(
                        ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING,
                        ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING
                )
                // 地表バイオーム
                .depth(
                        ParameterUtils.Depth.SURFACE
                )
                .build()

                .forEach(
                        point ->
                                forestBuilder.add(
                                        point,
                                        ModBiomes.COCONUT_PALM_FOREST
                                )
                );


        // Palm ForestをTerraBlenderへ追加
        forestBuilder
                .build()
                .forEach(mapper);
    }


    // =========================================================
    // Tropical Beach判定
    // =========================================================

    private static boolean isTropicalClimate(
            Climate.ParameterPoint point
    ) {

        return isInside(
                point.temperature(),
                WARM_TO_HOT
        )
                &&
                isInside(
                        point.humidity(),
                        NEUTRAL_TO_HUMID
                );
    }


    private static boolean isInside(
            Climate.Parameter value,
            Climate.Parameter range
    ) {

        return value.min() >= range.min()
                &&
                value.max() <= range.max();
    }
}