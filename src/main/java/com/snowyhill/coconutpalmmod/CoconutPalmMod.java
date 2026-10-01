package com.snowyhill.coconutpalmmod;

import com.mojang.logging.LogUtils;
import com.snowyhill.coconutpalmmod.registry.*;
import com.snowyhill.coconutpalmmod.worldgen.biome.ModOverworldRegion;
import com.snowyhill.coconutpalmmod.worldgen.biome.ModSurfaceRuleData;
import com.snowyhill.coconutpalmmod.worldgen.features.ModFeatureTypes;
import com.snowyhill.coconutpalmmod.worldgen.features.ModFeatures;
import com.snowyhill.coconutpalmmod.worldgen.features.decorator.ModTreeDecorators;
import com.snowyhill.coconutpalmmod.worldgen.features.foliage.ModFoliagePlacers;
import com.snowyhill.coconutpalmmod.worldgen.features.trunk.ModTrunkPlacers;
import com.snowyhill.coconutpalmmod.worldgen.placement.ModPlacementModifiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;


@Mod(CoconutPalmMod.MOD_ID)
public class CoconutPalmMod
{

    public static final String MOD_ID = "coconutpalmmod";
    private static final Logger LOGGER = LogUtils.getLogger();


    public CoconutPalmMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        //アイテムレジストリとイベントバスに登録　ModItemsから情報を得ている。
        ModItems.ITEMS.register(modEventBus);
        //クリエイティブタブをイベントバスに登録
        ModTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        // ブロックレジストリをイベントバスに登録
        ModBlocks.BLOCKS.register(modEventBus);
        ModTreeDecorators.DECORATORS.register(modEventBus);
        ModFoliagePlacers.FOLIAGE_PLACERS.register(modEventBus);
        ModTrunkPlacers.TRUNK_PLACERS.register(modEventBus);
        ModPlacementModifiers.PLACEMENT_MODIFIERS.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModFeatureTypes.register(modEventBus);
    }



    private void commonSetup(final FMLCommonSetupEvent event)
    {
        //生成頻度
        event.enqueueWork(() -> {

            Regions.register(new ModOverworldRegion(
                    new ResourceLocation(CoconutPalmMod.MOD_ID, "overworld"),
                    7
            ));

            SurfaceRuleManager.addSurfaceRules(
                    SurfaceRuleManager.RuleCategory.OVERWORLD,
                    MOD_ID,
                    ModSurfaceRuleData.makeRules()
            );

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(
                    ModBlocks.HIBISCUS_MAGENTA.getId(),
                    ModBlocks.POTTED_HIBISCUS_MAGENTA
            );

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(
                    ModBlocks.HIBISCUS_PINK.getId(),
                    ModBlocks.POTTED_HIBISCUS_PINK
            );

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(
                    ModBlocks.HIBISCUS_ORANGE.getId(),
                    ModBlocks.POTTED_HIBISCUS_ORANGE
            );

        });
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

      @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
