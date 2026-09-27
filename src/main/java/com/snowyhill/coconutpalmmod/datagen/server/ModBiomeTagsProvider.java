package com.snowyhill.coconutpalmmod.datagen.server;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.worldgen.biome.ModBiomeTags;
import com.snowyhill.coconutpalmmod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {

    public ModBiomeTagsProvider(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> lookupProvider,
                                ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CoconutPalmMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModBiomeTags.
                COCONUT_PALM_TREE_SPAWNABLE)

                .add(Biomes.WARM_OCEAN)
                .add(Biomes.LUKEWARM_OCEAN);


        tag(ModBiomeTags.
                HIBISCUS_BUSH_SPAWNABLE)

                .add(Biomes.WARM_OCEAN)
                .add(Biomes.LUKEWARM_OCEAN);

        tag(ModBiomeTags.JUNGLE_BUSH_SPAWNABLE)

                .add(Biomes.WARM_OCEAN)
                .add(Biomes.LUKEWARM_OCEAN);

    }

}