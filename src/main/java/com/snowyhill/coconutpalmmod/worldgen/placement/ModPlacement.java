package com.snowyhill.coconutpalmmod.worldgen.placement;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.worldgen.features.ModFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacement {

    public static final ResourceKey<PlacedFeature> COCONUT_PALM_TREE =
            createKey("coconut_palm_tree");




    public static void bootstap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures =
                context.lookup(Registries.CONFIGURED_FEATURE);


        // 木の配置情報を設定
        PlacementUtils.register(context, COCONUT_PALM_TREE,
                configuredFeatures.getOrThrow(ModFeatures.COCONUT_PALM_TREE_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(0, 0.2f, 1),
                        //1チャンク当たりの本数、追加の確率、追加されるときの本数
                        ModBlocks.COCONUT_PALM_SPROUTS.get()));

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
