package com.snowyhill.coconutpalmmod.registry;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.entity.ModBoatEntity;
import com.snowyhill.coconutpalmmod.item.GreenCoconutItem;
import com.snowyhill.coconutpalmmod.item.MatureCoconutItem;
import com.snowyhill.coconutpalmmod.item.ModBoatItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    // レジストリの作成
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CoconutPalmMod.MOD_ID);

    // レジストリにアイテムを追加

    public static final RegistryObject<Item> COCONUT_PALM_LOG_ITEM = ITEMS.register(
            "coconut_palm_log",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_LOG.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> STRIPPED_COCONUT_PALM_LOG_ITEM = ITEMS.register(
            "stripped_coconut_palm_log",
            () -> new BlockItem(ModBlocks.STRIPPED_COCONUT_PALM_LOG.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_WOOD_ITEM = ITEMS.register(
            "coconut_palm_wood",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_WOOD.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> STRIPPED_COCONUT_PALM_WOOD_ITEM = ITEMS.register(
            "stripped_coconut_palm_wood",
            () -> new BlockItem(ModBlocks.STRIPPED_COCONUT_PALM_WOOD.get(), new Item.Properties())
    );




    public static final RegistryObject<Item> COCONUT_PALM_PLANKS_ITEM = ITEMS.register(
            "coconut_palm_planks",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_PLANKS.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_SLAB_ITEM = ITEMS.register(
            "coconut_palm_slab",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_SLAB.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_STAIRS_ITEM = ITEMS.register(
            "coconut_palm_stairs",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_STAIRS.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_FENCE_ITEM = ITEMS.register(
            "coconut_palm_fence",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_FENCE.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_FENCE_GATE_ITEM = ITEMS.register(
            "coconut_palm_fence_gate",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_FENCE_GATE.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_DOOR_ITEM = ITEMS.register(
            "coconut_palm_door",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_DOOR.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_TRAPDOOR_ITEM = ITEMS.register(
            "coconut_palm_trapdoor",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_TRAPDOOR.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_BUTTON_ITEM = ITEMS.register(
            "coconut_palm_button",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_BUTTON.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_PRESSURE_PLATE_ITEM = ITEMS.register(
            "coconut_palm_pressure_plate",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_PRESSURE_PLATE.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_SIGN_ITEM = ITEMS.register("coconut_palm_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16),
                    ModBlocks.COCONUT_PALM_SIGN.get(), ModBlocks.COCONUT_PALM_WALL_SIGN.get()));

    public static final RegistryObject<Item> COCONUT_PALM_HANGING_SIGN_ITEM = ITEMS.register("coconut_palm_hanging_sign",
            () -> new HangingSignItem(ModBlocks.COCONUT_PALM_HANGING_SIGN.get(), ModBlocks.COCONUT_PALM_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));



    public static final RegistryObject<Item> COCONUT_PALM_BOAT = ITEMS.register("coconut_palm_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.COCONUT_PALM, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> COCONUT_PALM_CHEST_BOAT = ITEMS.register("coconut_palm_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.COCONUT_PALM, new Item.Properties().stacksTo(1)));

    //public static final RegistryObject<Item> COCONUT_PALM_BED = ITEMS.register("coconut_palm_bed",
            //() -> new BedItem(ModBlocks.COCONUT_PALM_BED.get(), new Item.Properties()));

    public static final RegistryObject<Item> COCONUT_PALM_LEAVES_ITEM = ITEMS.register(
            "coconut_palm_leaves",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_LEAVES.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_THATCH_ITEM = ITEMS.register(
            "coconut_palm_thatch",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_THATCH.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_THATCH_SLAB_ITEM = ITEMS.register(
            "coconut_palm_thatch_slab",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_THATCH_SLAB.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_PALM_THATCH_ROOF_ITEM = ITEMS.register(
            "coconut_palm_thatch_roof",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_THATCH_ROOF.get(), new Item.Properties())
    );





    public static final RegistryObject<Item> COCONUT_PALM_FLOWER_ITEM = ITEMS.register(
            "coconut_palm_flower",
            () -> new BlockItem(ModBlocks.COCONUT_PALM_FLOWER.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> GREEN_COCONUT_BLOCK_ITEM = ITEMS.register(
            "green_coconut_block",
            () -> new BlockItem(ModBlocks.GREEN_COCONUT_BLOCK.get(), new Item.Properties())
    );


    public static final RegistryObject<Item> MATURE_COCONUT_BLOCK_ITEM = ITEMS.register(
            "mature_coconut_block",
            () -> new BlockItem(ModBlocks.MATURE_COCONUT_BLOCK.get(), new Item.Properties())
    );


    // 飲める緑のココナッツ（Food）
    public static final RegistryObject<Item> GREEN_COCONUT = ITEMS.register(
            "green_coconut",
            () -> new GreenCoconutItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)        // 満腹度（お好みで）
                            .saturationMod(0.6f)  // 満腹度
                            .build()))
    );

    // 植え付け用の熟れたココナッツ）
    public static final RegistryObject<Item> MATURE_COCONUT = ITEMS.register(
            "mature_coconut",
            () -> new MatureCoconutItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)        // 満腹度（お好みで）
                            .build()))


    );

    public static final RegistryObject<Item> COCONUT_PALM_LEAF = ITEMS.register(
            "coconut_palm_leaf",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HIBISCUS_ITEM = ITEMS.register(
            "hibiscus",
            () -> new BlockItem(ModBlocks.HIBISCUS.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> HIBISCUS_LEAVES_ITEM = ITEMS.register(
            "hibiscus_leaves",
            () -> new BlockItem(ModBlocks.HIBISCUS_LEAVES.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> FLOWERING_HIBISCUS_LEAVES_ITEM = ITEMS.register(
            "flowering_hibiscus_leaves",
            () -> new BlockItem(ModBlocks.FLOWERING_HIBISCUS_LEAVES.get(), new Item.Properties())
    );

    public static final RegistryObject<Item> HIBISCUS_FLOWER = ITEMS.register(
            "hibiscus_flower",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        // レジストリをイベントバスに登録
        ITEMS.register(eventBus);
    }
}