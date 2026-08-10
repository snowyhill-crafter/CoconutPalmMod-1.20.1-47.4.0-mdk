package com.snowyhill.coconutpalmmod.event;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlockEntities;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.registry.ModItems;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.snowyhill.coconutpalmmod.client.renderer.ModBedRenderer;

@Mod.EventBusSubscriber(
        modid = CoconutPalmMod.MOD_ID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ClientModEvents {

    @SubscribeEvent
    public static void onBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, world, pos, tintIndex) -> {
                    if (world != null && pos != null) {
                        return BiomeColors.getAverageFoliageColor(world, pos);
                    }
                    return FoliageColor.getDefaultColor();
                },
                ModBlocks.COCONUT_PALM_LEAVES.get()
        );
    }

    @SubscribeEvent
    public static void onItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, tintIndex) -> FoliageColor.getDefaultColor(),
                ModItems.COCONUT_PALM_LEAVES_ITEM.get()
        );
    }




    //@SubscribeEvent
    //public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    //    event.registerBlockEntityRenderer(
    //            ModBlockEntities.COCONUT_BED_BE.get(),
    //            ModBedRenderer::new
   //     );
    //}
}