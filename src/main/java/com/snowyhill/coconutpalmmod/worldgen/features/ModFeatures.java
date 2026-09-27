package com.snowyhill.coconutpalmmod.worldgen.features;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.worldgen.features.decorator.CoconutPalmFruitDecorator;
import com.snowyhill.coconutpalmmod.worldgen.features.foliage.PalmFoliagePlacer;
import com.snowyhill.coconutpalmmod.worldgen.features.trunk.CoconutTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static net.minecraft.data.worldgen.features.FeatureUtils.createKey;

public class ModFeatures {

    // =========================================================
    // Deferred Register
    // =========================================================

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(
                    ForgeRegistries.FEATURES,
                    CoconutPalmMod.MOD_ID
            );

    // =========================================================
    // Feature登録
    // =========================================================

//    public static final RegistryObject<Feature<NoneFeatureConfiguration>>
//            BEACH_GRASS_PATCH =
//            FEATURES.register(
//                    "beach_grass_patch",
//                    () -> new BeachGrassPatchFeature(
//                            NoneFeatureConfiguration.CODEC
//                    )
//            );

    // =========================================================
    // ConfiguredFeature Keys
    // =========================================================

//    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>
//            BEACH_GRASS_PATCH_KEY =
//            registerKey("beach_grass_patch");

    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>
            COCONUT_PALM_TREE_KEY =
            registerKey("coconut_palm_tree");

    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>
            HIBISCUS_MAGENTA_BUSH_KEY =
            registerKey("hibiscus_magenta_bush");

    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>
            HIBISCUS_PINK_BUSH_KEY =
            registerKey("hibiscus_pink_bush");

    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>
            HIBISCUS_ORANGE_BUSH_KEY =
            registerKey("hibiscus_orange_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SHALLOW_WATER_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    new ResourceLocation(
                            CoconutPalmMod.MOD_ID,
                            "shallow_water"
                    )
            );

    // =========================================================
    // bootstrap
    // =========================================================

    public static void bootstrap(BootstapContext<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> context) {

        // =====================================================
        // BEACH GRASS PATCH
        // =====================================================

//        FeatureUtils.register(
//                context,
//                BEACH_GRASS_PATCH_KEY,
//                BEACH_GRASS_PATCH.get(),
//                FeatureConfiguration.NONE
//        );

        // =====================================================
        // COCONUT PALM TREE
        // =====================================================

        FeatureUtils.register(
                context,
                COCONUT_PALM_TREE_KEY,
                Feature.TREE,

                new TreeConfiguration.TreeConfigurationBuilder(

                        BlockStateProvider.simple(
                                ModBlocks.COCONUT_PALM_LOG.get()
                        ),

                        // 幹。調整によっては「幹の横かつ葉の下」が無くて実ができにくくなる点に注意
                        new CoconutTrunkPlacer(
                                7,               // baseHeight（6〜8あたり）
                                2,               // heightRandA
                                1,               // heightRandB
                                3,               // bendLength（横ずれ回数：2〜4で控えめ）
                                UniformInt.of(4, 4) // bendStartOffset（上から4〜6で曲げ始め）
                        ),

                        // 葉
                        BlockStateProvider.simple(
                                ModBlocks.COCONUT_PALM_LEAVES.get()
                        ),

                        // 葉配置
                        new PalmFoliagePlacer(
                                ConstantInt.of(1),     // radius（未使用でもOK）
                                ConstantInt.of(2),     // offset（未使用でもOK）
                                UniformInt.of(3, 3),   // frond_length：フロンドの長さ
                                8                      // frond_count：本数（4～6推奨）
                        ),

                        new TwoLayersFeatureSize(
                                1,
                                0,
                                1
                        )

                )

                        // ココナッツ追加
                        .decorators(List.of(
                                new CoconutPalmFruitDecorator(0.75F)
                        ))

                        .ignoreVines()
                        .build()
        );

        // =====================================================
        // HIBISCUS BUSH
        // =====================================================

        FeatureUtils.register(
                context,
                HIBISCUS_MAGENTA_BUSH_KEY,
                Feature.TREE,

                new TreeConfiguration.TreeConfigurationBuilder(

                        BlockStateProvider.simple(
                                Blocks.JUNGLE_LOG
                        ),

                        new StraightTrunkPlacer(
                                1,
                                0,
                                0
                        ),

                        new WeightedStateProvider(
                                SimpleWeightedRandomList.<BlockState>builder()

                                        .add(
                                                ModBlocks.HIBISCUS_LEAVES.get()
                                                        .defaultBlockState()
                                                        .setValue(
                                                                LeavesBlock.PERSISTENT,
                                                                false
                                                        ),
                                                1
                                        )

                                        .add(
                                                ModBlocks.FLOWERING_HIBISCUS_MAGENTA_LEAVES.get()
                                                        .defaultBlockState()
                                                        .setValue(
                                                                LeavesBlock.PERSISTENT,
                                                                false
                                                        ),
                                                1
                                        )
                                        .build()
                        ),
                        new BlobFoliagePlacer(
                                ConstantInt.of(2),
                                ConstantInt.of(0),
                                1
                        ),
                        new TwoLayersFeatureSize(
                                1,
                                0,
                                1
                        ))
                        .dirt(
                                BlockStateProvider.simple(
                                        Blocks.GRASS_BLOCK
                                ))
                        .ignoreVines()
                        .build()
        );

        FeatureUtils.register(
                context,
                HIBISCUS_PINK_BUSH_KEY,
                Feature.TREE,

                new TreeConfiguration.TreeConfigurationBuilder(

                        BlockStateProvider.simple(
                                Blocks.JUNGLE_LOG
                        ),

                        new StraightTrunkPlacer(
                                1,
                                0,
                                0
                        ),

                        new WeightedStateProvider(
                                SimpleWeightedRandomList.<BlockState>builder()

                                        .add(
                                                ModBlocks.HIBISCUS_LEAVES.get()
                                                        .defaultBlockState()
                                                        .setValue(
                                                                LeavesBlock.PERSISTENT,
                                                                false
                                                        ),
                                                1
                                        )

                                        .add(
                                                ModBlocks.FLOWERING_HIBISCUS_PINK_LEAVES.get()
                                                        .defaultBlockState()
                                                        .setValue(
                                                                LeavesBlock.PERSISTENT,
                                                                false
                                                        ),
                                                1
                                        )
                                        .build()
                        ),
                        new BlobFoliagePlacer(
                                ConstantInt.of(2),
                                ConstantInt.of(0),
                                1
                        ),
                        new TwoLayersFeatureSize(
                                1,
                                0,
                                1
                        ))
                        .dirt(
                                BlockStateProvider.simple(
                                        Blocks.GRASS_BLOCK
                                ))
                        .ignoreVines()
                        .build()
        );

        FeatureUtils.register(
                context,
                HIBISCUS_ORANGE_BUSH_KEY,
                Feature.TREE,

                new TreeConfiguration.TreeConfigurationBuilder(

                        BlockStateProvider.simple(
                                Blocks.JUNGLE_LOG
                        ),

                        new StraightTrunkPlacer(
                                1,
                                0,
                                0
                        ),

                        new WeightedStateProvider(
                                SimpleWeightedRandomList.<BlockState>builder()

                                        .add(
                                                ModBlocks.HIBISCUS_LEAVES.get()
                                                        .defaultBlockState()
                                                        .setValue(
                                                                LeavesBlock.PERSISTENT,
                                                                false
                                                        ),
                                                1
                                        )

                                        .add(
                                                ModBlocks.FLOWERING_HIBISCUS_ORANGE_LEAVES.get()
                                                        .defaultBlockState()
                                                        .setValue(
                                                                LeavesBlock.PERSISTENT,
                                                                false
                                                        ),
                                                1
                                        )
                                        .build()
                        ),
                        new BlobFoliagePlacer(
                                ConstantInt.of(2),
                                ConstantInt.of(0),
                                1
                        ),
                        new TwoLayersFeatureSize(
                                1,
                                0,
                                1
                        ))
                        .dirt(
                                BlockStateProvider.simple(
                                        Blocks.GRASS_BLOCK
                                ))
                        .ignoreVines()
                        .build()
        );

        FeatureUtils.register(
                context,
                SHALLOW_WATER_KEY,
                ModFeatureTypes.SHALLOW_WATER.get()
        );


    }

    // =========================================================
    // helper
    // =========================================================

    public static ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>>
    registerKey(String name) {

        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                new ResourceLocation(
                        CoconutPalmMod.MOD_ID,
                        name
                )
        );
    }
}