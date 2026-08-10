package com.snowyhill.coconutpalmmod.event;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModItems;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CoconutPalmMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void fuelEvent(FurnaceFuelBurnTimeEvent event) {

        if (event.getItemStack().is(ModItems.COCONUT_SHELL.get())) {
            event.setBurnTime(200);
        }

    }
}