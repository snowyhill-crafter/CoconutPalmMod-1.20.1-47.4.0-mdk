package com.snowyhill.coconutpalmmod.datagen.client;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.registry.ModEntities;
import com.snowyhill.coconutpalmmod.registry.ModItems;
import com.snowyhill.coconutpalmmod.worldgen.tree.CoconutPalmTreeGrower;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

public class JAJPLanguageProvider extends LanguageProvider {
    public JAJPLanguageProvider(PackOutput output) {
        super(output, CoconutPalmMod.MOD_ID, Locale.JAPAN.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        addBlock(ModBlocks.COCONUT_PALM_SPROUTS, "ココヤシの苗木");

        addBlock(ModBlocks.GREEN_COCONUT_BLOCK, "未熟なココヤシの実");
        addBlock(ModBlocks.MATURE_COCONUT_BLOCK, "熟れたココヤシの実");
        addBlock(ModBlocks.COCONUT_PALM_LOG, "ココヤシの原木");
        addBlock(ModBlocks.STRIPPED_COCONUT_PALM_LOG, "樹皮を剥いだココヤシの原木");
        addBlock(ModBlocks.COCONUT_PALM_WOOD, "ココヤシの木");
        addBlock(ModBlocks.STRIPPED_COCONUT_PALM_WOOD, "樹皮を剥いだココヤシの木");


        addBlock(ModBlocks.COCONUT_PALM_PLANKS, "ココヤシの板材");
        addBlock(ModBlocks.COCONUT_PALM_SLAB, "ココヤシのハーフブロック");
        addBlock(ModBlocks.COCONUT_PALM_STAIRS, "ココヤシの階段");
        addBlock(ModBlocks.COCONUT_PALM_FENCE, "ココヤシのフェンス");
        addBlock(ModBlocks.COCONUT_PALM_FENCE_GATE, "ココヤシのフェンスゲート");
        addBlock(ModBlocks.COCONUT_PALM_DOOR, "ココヤシのドア");
        addBlock(ModBlocks.COCONUT_PALM_TRAPDOOR, "ココヤシのトラップドア");
        addBlock(ModBlocks.COCONUT_PALM_BUTTON, "ココヤシのボタン");
        addBlock(ModBlocks.COCONUT_PALM_PRESSURE_PLATE, "ココヤシの感圧板");

        addBlock(ModBlocks.COCONUT_PALM_SIGN, "ココヤシの看板");
        addBlock(ModBlocks.COCONUT_PALM_HANGING_SIGN, "ココヤシの吊り看板");
        addItem(ModItems.COCONUT_PALM_BOAT, "ココヤシのボート");
        addItem(ModItems.COCONUT_PALM_CHEST_BOAT, "チェスト付きのココヤシのボート");

        addItem(ModItems.GREEN_COCONUT, "緑色のココナッツ");
        addItem(ModItems.MATURE_COCONUT, "熟れたココナッツ");
        addBlock(ModBlocks.COCONUT_PALM_LEAVES, "ココヤシの葉");
        addItem(ModItems.COCONUT_PALM_LEAF, "切り出したココヤシの葉");
        addItem(ModItems.COCONUT_PALM_THATCH_ITEM, "ココヤシの葉葺き");
        addItem(ModItems.COCONUT_PALM_THATCH_SLAB_ITEM, "ココヤシの葉葺きのハーフブロック");
        addItem(ModItems.COCONUT_PALM_THATCH_ROOF_ITEM, "ココヤシの葉葺きの屋根");

        addEntityType(ModEntities.MOD_CHEST_BOAT, "チェスト付きのボート");
        add("creativetabs.Mod_tab", "ココヤシの木MOD");


    }
}