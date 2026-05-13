package com.snowyhill.coconutpalmmod.datagen.client;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CoconutPalmMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //ここにアイテムを追加して自動生成させる
        basicItem(ModItems.GREEN_COCONUT.get());
        basicItem(ModItems.MATURE_COCONUT.get());
        basicItem(ModItems.COCONUT_PALM_LEAF.get());


        itemWithBlock(ModBlocks.COCONUT_PALM_SLAB);
        itemWithBlock(ModBlocks.COCONUT_PALM_STAIRS);
        itemWithBlock(ModBlocks.COCONUT_PALM_FENCE_GATE);
        itemWithBlock(ModBlocks.COCONUT_PALM_PRESSURE_PLATE);
        basicItem(ModBlocks.COCONUT_PALM_DOOR.get().asItem());
        trapdoor(ModBlocks.COCONUT_PALM_TRAPDOOR);
        fence(ModBlocks.COCONUT_PALM_FENCE,
                ModBlocks.COCONUT_PALM_PLANKS);
        button(ModBlocks.COCONUT_PALM_BUTTON,
                ModBlocks.COCONUT_PALM_PLANKS);
        // 看板（手持ちアイテム）
        singleTexture("coconut_palm_sign",
                new ResourceLocation("item/generated"),
                "layer0",
                modLoc("item/coconut_palm_sign"));

        // 吊り看板（手持ちアイテム）
        singleTexture("coconut_palm_hanging_sign",
                new ResourceLocation("item/generated"),
                "layer0",
                modLoc("item/coconut_palm_hanging_sign"));

        // 通常のボート
        basicItem(ModItems.COCONUT_PALM_BOAT.get());

        // チェスト付きボート
        basicItem(ModItems.COCONUT_PALM_CHEST_BOAT.get());


        sapling(ModBlocks.COCONUT_PALM_SPROUTS);


    }

    public void itemWithBlock(RegistryObject<Block> block) {
        this.getBuilder(ForgeRegistries.BLOCKS.getKey(block.get()).getPath())
                .parent(new ModelFile.UncheckedModelFile(
                        CoconutPalmMod.MOD_ID + ":block/" +
                                ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    public void trapdoor(RegistryObject<Block> block) {
        this.getBuilder(ForgeRegistries.BLOCKS.getKey(block.get()).getPath())
                .parent(new ModelFile.UncheckedModelFile(
                        CoconutPalmMod.MOD_ID + ":block/" +
                                ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }
    public void fence(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(CoconutPalmMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void button(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(CoconutPalmMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    private void sapling(RegistryObject<Block> block) {
        this.withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CoconutPalmMod.MOD_ID,"block/" + block.getId().getPath()));
    }



}
