package com.snowyhill.coconutpalmmod.datagen.client;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.registry.ModEntities;
import com.snowyhill.coconutpalmmod.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

public class ENUSLanguageProvider extends LanguageProvider {
    public ENUSLanguageProvider(PackOutput output) {
        super(output, CoconutPalmMod.MOD_ID, Locale.US.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        addBlock(ModBlocks.COCONUT_PALM_SPROUTS, "Coconut Palm Sprouts");
        addBlock(ModBlocks.GREEN_COCONUT_BLOCK, "Green Coconut");
        addBlock(ModBlocks.MATURE_COCONUT_BLOCK, "Mature Coconut");
        addBlock(ModBlocks.COCONUT_PALM_LOG, "Coconut Palm Log");
        addBlock(ModBlocks.STRIPPED_COCONUT_PALM_LOG, "Stripped Coconut Palm Log");
        addBlock(ModBlocks.COCONUT_PALM_WOOD, "Coconut Palm Wood");
        addBlock(ModBlocks.STRIPPED_COCONUT_PALM_WOOD, "Stripped Coconut Palm Wood");


        addBlock(ModBlocks.COCONUT_PALM_PLANKS, "Coconut Palm Planks");
        addBlock(ModBlocks.COCONUT_PALM_SLAB, "Coconut Palm Slab");
        addBlock(ModBlocks.COCONUT_PALM_STAIRS, "Coconut Palm Stairs");
        addBlock(ModBlocks.COCONUT_PALM_FENCE, "Coconut Palm Fence");
        addBlock(ModBlocks.COCONUT_PALM_FENCE_GATE, "Coconut Palm Fence Gate");
        addBlock(ModBlocks.COCONUT_PALM_DOOR, "Coconut Palm Door");
        addBlock(ModBlocks.COCONUT_PALM_TRAPDOOR, "Coconut Palm Trapdoor");
        addBlock(ModBlocks.COCONUT_PALM_BUTTON, "Coconut Palm Button");
        addBlock(ModBlocks.COCONUT_PALM_PRESSURE_PLATE, "Coconut Palm Pressure Plate");

        addBlock(ModBlocks.COCONUT_PALM_SIGN, "Coconut Palm Sign");
        addBlock(ModBlocks.COCONUT_PALM_HANGING_SIGN, "Coconut Palm Hanging Sign");
        addItem(ModItems.COCONUT_PALM_BOAT, "Coconut Palm Boat");
        addItem(ModItems.COCONUT_PALM_CHEST_BOAT, "Coconut Palm Chest Boat");

        addItem(ModItems.GREEN_COCONUT, "Green Coconut");
        addItem(ModItems.MATURE_COCONUT, "Mature Coconut");
        addItem(ModItems.SLICED_COCONUT, "Sliced Coconut");
        addItem(ModItems.COCONUT_SHELL, "Coconut Shell");
        addItem(ModItems.COCONUT_FIBER, "Coconut Fiber");
        addBlock(ModBlocks.COCONUT_FIBER_BLOCK, "Coconut Fiber Block");
        addBlock(ModBlocks.COCONUT_FIBER_CARPET, "Coconut Fiber Carpet");
        addBlock(ModBlocks.COCONUT_PALM_LEAVES, "Coconut Palm Leaves");
        addItem(ModItems.COCONUT_PALM_LEAF, "Coconut Palm leaf");
        addItem(ModItems.COCONUT_PALM_THATCH_ITEM, "Coconut Palm Thatch");
        addItem(ModItems.COCONUT_PALM_THATCH_SLAB_ITEM, "Coconut Palm Thatch Slab");
        addItem(ModItems.COCONUT_PALM_THATCH_ROOF_ITEM, "Coconut Palm Thatch Roof");

        addBlock(ModBlocks.HIBISCUS_MAGENTA, "Magenta Hibiscus");
        addBlock(ModBlocks.HIBISCUS_PINK, "Pink Hibiscus");
        addBlock(ModBlocks.HIBISCUS_ORANGE, "Orange Hibiscus");
        addBlock(ModBlocks.HIBISCUS_LEAVES, "Hibiscus Leaves");
        addBlock(ModBlocks.FLOWERING_HIBISCUS_MAGENTA_LEAVES, "Magenta Hibiscus Leaves");
        addBlock(ModBlocks.FLOWERING_HIBISCUS_PINK_LEAVES, "Pink Hibiscus Leaves");
        addBlock(ModBlocks.FLOWERING_HIBISCUS_ORANGE_LEAVES, "Orange Hibiscus Leaves");
        addItem(ModItems.HIBISCUS_MAGENTA_FLOWER, "Magenta Hibiscus Flower");
        addItem(ModItems.HIBISCUS_PINK_FLOWER, "Pink Hibiscus Flower");
        addItem(ModItems.HIBISCUS_ORANGE_FLOWER, "Orange Hibiscus Flower");

        addEntityType(ModEntities.MOD_CHEST_BOAT, "Boat with Chest");
        add("biome.coconutpalmmod.tropical_beach", "Tropical Beach");
        add("biome.coconutpalmmod.coconut_palm_forest", "Coconut Palm Forest");
        add("creativetabs.Mod_tab", "coconutpalmmod");
        

    }
    
    
}