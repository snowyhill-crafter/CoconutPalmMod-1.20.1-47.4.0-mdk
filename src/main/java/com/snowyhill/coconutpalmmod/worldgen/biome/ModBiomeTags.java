package com.snowyhill.coconutpalmmod.worldgen.biome;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {
    public static final TagKey<Biome> COCONUT_PALM_TREE_SPAWNABLE = create("coconut_palm_tree_spawnable");
    public static final TagKey<Biome> HIBISCUS_BUSH_SPAWNABLE = create("hibiscus_bush_spawnable");
    public static final TagKey<Biome> JUNGLE_BUSH_SPAWNABLE = create("jungle_bush_spawnable");


    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(CoconutPalmMod.MOD_ID, name));
    }
}