package com.snowyhill.coconutpalmmod.worldgen.biome;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.worldgen.placement.ModPlacement;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {

    public static final ResourceKey<Biome> TROPICAL_BEACH =
            ResourceKey.create(
                    Registries.BIOME,
                    new ResourceLocation(
                            CoconutPalmMod.MOD_ID,
                            "tropical_beach"
                    )
            );

    public static final ResourceKey<Biome> COCONUT_PALM_FOREST =
            ResourceKey.create(
                    Registries.BIOME,
                    new ResourceLocation(
                            CoconutPalmMod.MOD_ID,
                            "coconut_palm_forest"
                    )
            );


    public static void bootstrap(BootstapContext<Biome> context) {

        context.register(
                TROPICAL_BEACH,
                TropicalBeach(context)
        );

        context.register(
                COCONUT_PALM_FOREST,
                CoconutPalmForest(context)
        );
    }


    // =========================================================
    // Overworld共通生成
    // =========================================================

    private static void globalOverworldGeneration(
            BiomeGenerationSettings.Builder builder
    ) {

        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }


    // =========================================================
    // Tropical Beach
    // =========================================================

    private static Biome TropicalBeach(
            BootstapContext<Biome> context
    ) {

        MobSpawnSettings.Builder spawnBuilder =
                new MobSpawnSettings.Builder();

        // ヒツジ、ブタなど
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // ゾンビ、スケルトンなど
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // ウミガメ
        spawnBuilder.addSpawn(
                MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(
                        EntityType.TURTLE,
                        5,
                        2,
                        5
                )
        );


        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER)
                );


        globalOverworldGeneration(biomeBuilder);

        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultGrass(biomeBuilder);


        // ココヤシ
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.COCONUT_PALM_TREE
        );

        // ハイビスカス
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.HIBISCUS_MAGENTA_BUSH
        );

        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.HIBISCUS_PINK_BUSH
        );

        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.HIBISCUS_ORANGE_BUSH
        );

        // ジャングルブッシュ
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.JUNGLE_BUSH
        );

// =========================================================
// 浅瀬造成
// =========================================================

        biomeBuilder.addFeature(
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                ModPlacement.SHALLOW_WATER
        );

        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.TROPICAL_BEACH_CORAL
        );

        return new Biome.BiomeBuilder()

                .hasPrecipitation(true)

                .downfall(0.9f)

                .temperature(0.8f)

                .generationSettings(
                        biomeBuilder.build()
                )

                .mobSpawnSettings(
                        spawnBuilder.build()
                )

                .specialEffects(
                        new BiomeSpecialEffects.Builder()

                                .waterColor(0x43D5EE)

                                .waterFogColor(0x041F33)

                                .skyColor(0x7BA4FF)

                                .grassColorOverride(0x73A65A)

                                .foliageColorOverride(0x6FA24C)

                                .fogColor(0xC0D8FF)

                                .ambientMoodSound(
                                        AmbientMoodSettings.LEGACY_CAVE_SETTINGS
                                )

                                .backgroundMusic(
                                        Musics.createGameMusic(
                                                SoundEvents.MUSIC_BIOME_JUNGLE
                                        )
                                )

                                .build()
                )

                .build();
    }


    // =========================================================
    // Coconut Palm Forest
    // =========================================================

    private static Biome CoconutPalmForest(
            BootstapContext<Biome> context
    ) {

        MobSpawnSettings.Builder spawnBuilder =
                new MobSpawnSettings.Builder();


        // 通常の陸上動物
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        // 通常の敵対Mob
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        /*
         * Tropical Beachと違い
         * TURTLEは追加しない。
         */
        spawnBuilder.addSpawn(
                MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(EntityType.PARROT, 16, 2, 4)
        );

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER)
                );


        globalOverworldGeneration(biomeBuilder);

        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        BiomeDefaultFeatures.addDefaultGrass(biomeBuilder);


        // =====================================================
        // 植生
        // =====================================================

        // ココヤシ
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.COCONUT_PALM_TREE
        );

        // ハイビスカス：マゼンタ
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.HIBISCUS_MAGENTA_BUSH
        );

        // ハイビスカス：ピンク
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.HIBISCUS_PINK_BUSH
        );

        // ハイビスカス：オレンジ
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.HIBISCUS_ORANGE_BUSH
        );

        // ジャングルブッシュ
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacement.JUNGLE_BUSH
        );


        return new Biome.BiomeBuilder()

                .hasPrecipitation(true)

                // 高温多湿
                .downfall(0.9f)

                .temperature(0.9f)

                .generationSettings(
                        biomeBuilder.build()
                )

                .mobSpawnSettings(
                        spawnBuilder.build()
                )

                .specialEffects(
                        new BiomeSpecialEffects.Builder()

                                // Tropical Beachに近い南国色
                                .waterColor(0x43D5EE)

                                .waterFogColor(0x041F33)

                                .skyColor(0x7BA4FF)

                                // 草地なので緑を強める
                                .grassColorOverride(0x63A948)

                                .foliageColorOverride(0x59A33C)

                                .fogColor(0xC0D8FF)

                                .ambientMoodSound(
                                        AmbientMoodSettings.LEGACY_CAVE_SETTINGS
                                )

                                .backgroundMusic(
                                        Musics.createGameMusic(
                                                SoundEvents.MUSIC_BIOME_JUNGLE
                                        )
                                )

                                .build()
                )

                .build();
    }
}