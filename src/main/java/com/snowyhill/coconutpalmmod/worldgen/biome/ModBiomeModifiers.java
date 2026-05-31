package com.snowyhill.coconutpalmmod.worldgen.biome;



import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.worldgen.placement.ModPlacement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;



public class ModBiomeModifiers {

//リソースキー

    // 木用キー
    public static final ResourceKey<BiomeModifier> ADD_COCONUT_PALM_TREE =
            createKey("add_coconut_palm_tree");

    public static final ResourceKey<BiomeModifier> ADD_HIBISCUS_BUSH =
            createKey("add_hibiscus_bush");

    public static final ResourceKey<BiomeModifier> JUNGLE_BUSH_KEY =
            createKey("add_jungle_bush");

    //バイオームに生成するメソッド
    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);



        TagKey<Biome> CoconutPalmTreeBiomeTag = TagKey.create(Registries.BIOME,
                new ResourceLocation("coconutpalmmod", "coconut_palm_tree_spawnable"));

        context.register(ADD_COCONUT_PALM_TREE,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(CoconutPalmTreeBiomeTag), // ✅ biomeタグとして扱う
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacement.COCONUT_PALM_TREE)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        TagKey<Biome> HibiscusBushBiomeTag = TagKey.create(Registries.BIOME,
                new ResourceLocation("coconutpalmmod", "hibiscus_bush_spawnable"));

        context.register(ADD_HIBISCUS_BUSH,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(HibiscusBushBiomeTag), // ✅ biomeタグとして扱う
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacement.HIBISCUS_BUSH)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );


        context.register(JUNGLE_BUSH_KEY,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(

                        context.lookup(Registries.BIOME)
                                .getOrThrow(ModBiomeTags.JUNGLE_BUSH_SPAWNABLE),

                        HolderSet.direct(
                                placedFeatures.getOrThrow(ModPlacement.JUNGLE_BUSH)
                        ),

                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

    }


    //登録用メソッド
    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
                new ResourceLocation(CoconutPalmMod.MOD_ID,name));
    }


}
