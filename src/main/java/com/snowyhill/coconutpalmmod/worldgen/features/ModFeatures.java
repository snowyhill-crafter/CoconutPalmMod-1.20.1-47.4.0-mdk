package com.snowyhill.coconutpalmmod.worldgen.features;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.worldgen.features.decorator.CoconutPalmFruitDecorator;
import com.snowyhill.coconutpalmmod.worldgen.features.decorator.CoconutPalmGroundDecorator;
import com.snowyhill.coconutpalmmod.worldgen.features.foliage.PalmFoliagePlacer;
import com.snowyhill.coconutpalmmod.worldgen.features.trunk.CoconutTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.block.Blocks;
import java.util.List;

public class ModFeatures {


    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT_PALM_TREE_KEY =
            createKey("coconut_palm_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> HIBISCUS_BUSH_KEY =
            createKey("hibiscus_bush");





    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        FeatureUtils.register(context, COCONUT_PALM_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        // 幹ブロック
                        BlockStateProvider.simple(ModBlocks.COCONUT_PALM_LOG.get()),

                        // ★ 自作トランクプレーサー（曲がる一本幹＆付点は先端1個）
                        new CoconutTrunkPlacer(
                                7,               // baseHeight（6〜8あたり）
                                2,               // heightRandA
                                1,               // heightRandB
                                3,               // bendLength（横ずれ回数：2〜4で控えめ）
                                UniformInt.of(4, 4) // bendStartOffset（上から4〜6で曲げ始め）
                        ),

                        // 葉ブロック
                        BlockStateProvider.simple(ModBlocks.COCONUT_PALM_LEAVES.get()),


                        // 自作：先端から放射状の“フロンド” "distance": "8"で葉が落ちにくくした
                        new PalmFoliagePlacer(
                                ConstantInt.of(1),     // radius（未使用でもOK）
                                ConstantInt.of(2),     // offset（未使用でもOK）
                                UniformInt.of(3, 3),   // frond_length：フロンドの長さ
                                8                      // frond_count：本数（4～6推奨）
                        ),
                        new TwoLayersFeatureSize(1, 0, 1)
                )
                        .ignoreVines()
                        .decorators(List.of(
                                new CoconutPalmFruitDecorator(0.75F), // 実が多すぎるなら 0.10〜0.16Fで調整
                                new CoconutPalmGroundDecorator()))
                        .build()
        );


        FeatureUtils.register(context, HIBISCUS_BUSH_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(

                        BlockStateProvider.simple(Blocks.OAK_LOG),

                        new StraightTrunkPlacer(
                                1,
                                0,
                                0
                        ),

                        new net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider(
                                net.minecraft.util.random.SimpleWeightedRandomList.<net.minecraft.world.level.block.state.BlockState>builder()

                                        .add(
                                                ModBlocks.HIBISCUS_LEAVES.get()
                                                        .defaultBlockState()
                                                        .setValue(net.minecraft.world.level.block.LeavesBlock.PERSISTENT, false),
                                                1
                                        )

                                        .add(
                                                ModBlocks.FLOWERING_HIBISCUS_LEAVES.get()
                                                        .defaultBlockState()
                                                        .setValue(net.minecraft.world.level.block.LeavesBlock.PERSISTENT, false),
                                                1
                                        )

                                        .build()
                        ),

                        new BlobFoliagePlacer(
                                ConstantInt.of(2),
                                ConstantInt.of(0),
                                1
                        ),

                        new TwoLayersFeatureSize(1, 0, 1)

                )
                        .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK))
                        .ignoreVines()
                        .build()
        );




    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                new ResourceLocation(CoconutPalmMod.MOD_ID, name));
    }
}
