package com.snowyhill.coconutpalmmod.worldgen.tree;

import com.snowyhill.coconutpalmmod.worldgen.features.ModFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class HibiscusOrangeTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(
            RandomSource random,
            boolean hasFlowers
    ) {
        return ModFeatures.HIBISCUS_ORANGE_BUSH_KEY;
    }
}