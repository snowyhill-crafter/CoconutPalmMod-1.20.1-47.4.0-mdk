package com.snowyhill.coconutpalmmod.registry;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModTabs {
    //レジストリを作る
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CoconutPalmMod.MOD_ID);

    //レジストリにタブを追加
    public static final RegistryObject<CreativeModeTab> COCONUTPALMMOD_TAB = TABS.register("coconutpalmmod_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetabs.Mod_tab"))
                    .icon(ModItems.MATURE_COCONUT.get()::getDefaultInstance)
                    .displayItems(((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.COCONUT_PALM_LOG_ITEM.get());
                        pOutput.accept(ModItems.STRIPPED_COCONUT_PALM_LOG_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_WOOD_ITEM.get());
                        pOutput.accept(ModItems.STRIPPED_COCONUT_PALM_WOOD_ITEM.get());


                        pOutput.accept(ModItems.COCONUT_PALM_PLANKS_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_SLAB_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_STAIRS_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_FENCE_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_FENCE_GATE_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_DOOR_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_TRAPDOOR_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_BUTTON_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_PRESSURE_PLATE_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_SIGN_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_HANGING_SIGN_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_BOAT.get());
                        pOutput.accept(ModItems.COCONUT_PALM_CHEST_BOAT.get());
                        //pOutput.accept(ModItems.COCONUT_PALM_BED.get());

                        pOutput.accept(ModItems.GREEN_COCONUT.get());
                        pOutput.accept(ModItems.MATURE_COCONUT.get());
                        pOutput.accept(ModItems.COCONUT_PALM_LEAF.get());
                        pOutput.accept(ModItems.COCONUT_PALM_LEAVES_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_THATCH_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_THATCH_SLAB_ITEM.get());
                        pOutput.accept(ModItems.COCONUT_PALM_THATCH_ROOF_ITEM.get());

                      
                        
                        
                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
