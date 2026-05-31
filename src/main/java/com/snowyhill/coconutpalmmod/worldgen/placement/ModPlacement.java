package com.snowyhill.coconutpalmmod.worldgen.placement;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.worldgen.features.ModFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;

public class ModPlacement {

    public static final ResourceKey<PlacedFeature> COCONUT_PALM_TREE =
            createKey("coconut_palm_tree");


    public static final ResourceKey<PlacedFeature> HIBISCUS_BUSH =
            createKey("hibiscus_bush");

    public static final ResourceKey<PlacedFeature> JUNGLE_BUSH =
            createKey("jungle_bush");

    public static void bootstap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures =
                context.lookup(Registries.CONFIGURED_FEATURE);


        // 木の配置情報を設定
        List<PlacementModifier> coconutPlacements = new java.util.ArrayList<>(
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(1, 0.1f, 1),//発生頻度
                        ModBlocks.COCONUT_PALM_SPROUTS.get()
                )
        );

        coconutPlacements.add(BeachGrassPatchPlacement.of(4));//水との距離

        PlacementUtils.register(context, COCONUT_PALM_TREE,
                configuredFeatures.getOrThrow(ModFeatures.BEACH_GRASS_PATCH_KEY),
                coconutPlacements);



        PlacementUtils.register(context, HIBISCUS_BUSH,
                configuredFeatures.getOrThrow(ModFeatures.HIBISCUS_BUSH_KEY),

                CountPlacement.of(2),//発生頻度

                InSquarePlacement.spread(),

                PlacementUtils.HEIGHTMAP,

                BlockPredicateFilter.forPredicate(
                        net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.matchesBlocks(
                                new BlockPos(0, -1, 0),
                                Blocks.GRASS_BLOCK
                        )
                ),

                BiomeFilter.biome()
        );


        PlacementUtils.register(context, JUNGLE_BUSH,

                configuredFeatures.getOrThrow(TreeFeatures.JUNGLE_BUSH),

                CountPlacement.of(3),//発生頻度

                InSquarePlacement.spread(),

                PlacementUtils.HEIGHTMAP,

                BlockPredicateFilter.forPredicate(
                        BlockPredicate.matchesBlocks(
                                new BlockPos(0, -1, 0),
                                Blocks.GRASS_BLOCK
                        )
                ),

                BiomeFilter.biome()
        );


    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                new ResourceLocation(CoconutPalmMod.MOD_ID, name));
    }


    //githubからこぴぺ


    private static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

}
