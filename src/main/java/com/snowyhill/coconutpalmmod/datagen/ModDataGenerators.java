package com.snowyhill.coconutpalmmod.datagen;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.datagen.client.ENUSLanguageProvider;
import com.snowyhill.coconutpalmmod.datagen.client.JAJPLanguageProvider;
import com.snowyhill.coconutpalmmod.datagen.client.ModBlockStateProvider;
import com.snowyhill.coconutpalmmod.datagen.client.ModItemModelProvider;
import com.snowyhill.coconutpalmmod.datagen.server.*;
import com.snowyhill.coconutpalmmod.datagen.server.loot.ModLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(
        modid = CoconutPalmMod.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ModDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        CompletableFuture<HolderLookup.Provider> lookUpProvider =
                event.getLookupProvider();

        // =========================================================
        // CLIENT
        // =========================================================

        // ItemModel
        generator.addProvider(
                event.includeClient(),
                new ModItemModelProvider(
                        packOutput,
                        existingFileHelper
                )
        );

        // BlockState
        generator.addProvider(
                event.includeClient(),
                new ModBlockStateProvider(
                        packOutput,
                        existingFileHelper
                )
        );

        // Language
        generator.addProvider(
                event.includeClient(),
                new JAJPLanguageProvider(packOutput)
        );

        generator.addProvider(
                event.includeClient(),
                new ENUSLanguageProvider(packOutput)
        );


        // =========================================================
        // SERVER / WORLDGEN
        // =========================================================

        // ConfiguredFeature
        // PlacedFeature
        // BiomeModifier
        // Custom Biome
        //
        // などのDynamic Registryを生成する
        ModWorldGenProvider worldGenProvider =
                generator.addProvider(
                        event.includeServer(),
                        new ModWorldGenProvider(
                                packOutput,
                                lookUpProvider
                        )
                );


        // =========================================================
        // BIOME TAGS
        // =========================================================
        //
        // ★重要
        //
        // event.getLookupProvider() ではなく、
        // ModWorldGenProviderで追加した
        // tropical_beach等を含むRegistryProviderを渡す
        //
        generator.addProvider(
                event.includeServer(),
                new ModBiomeTagsProvider(
                        packOutput,
                        worldGenProvider.getRegistryProvider(),
                        existingFileHelper
                )
        );


        // =========================================================
        // BLOCK TAGS
        // =========================================================

        ModBlockTagsProvider blockTagsProvider =
                generator.addProvider(
                        event.includeServer(),
                        new ModBlockTagsProvider(
                                packOutput,
                                lookUpProvider,
                                existingFileHelper
                        )
                );


        // =========================================================
        // ITEM TAGS
        // =========================================================

        generator.addProvider(
                event.includeServer(),
                new ModItemTagsProvider(
                        packOutput,
                        lookUpProvider,
                        blockTagsProvider.contentsGetter(),
                        CoconutPalmMod.MOD_ID,
                        existingFileHelper
                )
        );


        // =========================================================
        // RECIPES
        // =========================================================

        generator.addProvider(
                event.includeServer(),
                new ModRecipeProvider(packOutput)
        );


        // =========================================================
        // LOOT TABLES
        // =========================================================

        generator.addProvider(
                event.includeServer(),
                ModLootTables.create(
                        packOutput,
                        lookUpProvider
                )
        );


        // =========================================================
        // GLOBAL LOOT MODIFIER
        // =========================================================

        generator.addProvider(
                event.includeServer(),
                new ModGlobalLootModifierProvider(packOutput)
        );
    }
}