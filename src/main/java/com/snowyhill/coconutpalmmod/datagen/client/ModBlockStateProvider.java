package com.snowyhill.coconutpalmmod.datagen.client;



import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CoconutPalmMod.MOD_ID, exFileHelper);
    }

    @Override
    //ここにブロックを追加し自動生成させる
    //logBlock((RotatedPillarBlock)とaxisBlock((RotatedPillarBlock)はアイテムJSONを生成しないので別途登録
    protected void registerStatesAndModels() {
        sapling(ModBlocks.COCONUT_PALM_SPROUTS);

        logBlock((RotatedPillarBlock) ModBlocks.COCONUT_PALM_LOG.get());
        item(ModBlocks.COCONUT_PALM_LOG);//ログブロックとアクシズブロックはアイテムモデルを自動生成しないし作っても消される。

        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_COCONUT_PALM_LOG.get());
        item(ModBlocks.STRIPPED_COCONUT_PALM_LOG);


        axisBlock((RotatedPillarBlock) ModBlocks.COCONUT_PALM_WOOD.get(),
                blockTexture(ModBlocks.COCONUT_PALM_LOG.get()),
                blockTexture(ModBlocks.COCONUT_PALM_LOG.get()));
        item(ModBlocks.COCONUT_PALM_WOOD);

        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_COCONUT_PALM_WOOD.get(),
                blockTexture(ModBlocks.STRIPPED_COCONUT_PALM_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_COCONUT_PALM_LOG.get()));
        item(ModBlocks.STRIPPED_COCONUT_PALM_WOOD);

        simpleBlockWithItem(ModBlocks.COCONUT_PALM_PLANKS);
        slabBlock((SlabBlock) ModBlocks.COCONUT_PALM_SLAB.get(),
                // 二つ重ねたときのテクスチャ
                blockTexture(ModBlocks.COCONUT_PALM_PLANKS.get()),
                // 単体のテクスチャ
                blockTexture(ModBlocks.COCONUT_PALM_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.COCONUT_PALM_STAIRS.get(),
                blockTexture(ModBlocks.COCONUT_PALM_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.COCONUT_PALM_FENCE.get(),
                blockTexture(ModBlocks.COCONUT_PALM_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.COCONUT_PALM_FENCE_GATE.get(),
                blockTexture(ModBlocks.COCONUT_PALM_PLANKS.get()));
        doorBlockWithRenderType((DoorBlock) ModBlocks.COCONUT_PALM_DOOR.get(),
                modLoc("block/coconut_palm_door_bottom"),
                modLoc("block/coconut_palm_door_top"),
                "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock)
                        ModBlocks.COCONUT_PALM_TRAPDOOR.get(),
                modLoc("block/coconut_palm_trapdoor"), true,
                "cutout");
        buttonBlock((ButtonBlock) ModBlocks.COCONUT_PALM_BUTTON.get(),
                blockTexture(ModBlocks.COCONUT_PALM_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)
                        ModBlocks.COCONUT_PALM_PRESSURE_PLATE.get(),
                blockTexture(ModBlocks.COCONUT_PALM_PLANKS.get()));

        simpleBlockWithItem(ModBlocks.COCONUT_FIBER_BLOCK);

        simpleBlock(
                ModBlocks.COCONUT_FIBER_CARPET.get(),
                models().withExistingParent("coconut_fiber_carpet", mcLoc("block/carpet"))
                        .texture("wool", modLoc("block/coconut_fiber_block"))
        );

        simpleBlockItem(
                ModBlocks.COCONUT_FIBER_CARPET.get(),
                models().withExistingParent("coconut_fiber_carpet", mcLoc("block/carpet"))
                        .texture("wool", modLoc("block/coconut_fiber_block"))
        );


        simpleBlockWithItem(
                ModBlocks.HIBISCUS_MAGENTA.get(),
                models().withExistingParent(
                                "hibiscus_magenta",
                                mcLoc("block/azalea")
                        )
                        .texture("top", modLoc("block/hibiscus_magenta_top"))
                        .texture("side", modLoc("block/hibiscus_magenta_side"))
                        .texture("plant", modLoc("block/hibiscus_plant"))
                        .renderType("cutout")
        );

        simpleBlockWithItem(
                ModBlocks.HIBISCUS_PINK.get(),
                models().withExistingParent(
                                "hibiscus_pink",
                                mcLoc("block/azalea")
                        )
                        .texture("top", modLoc("block/hibiscus_pink_top"))
                        .texture("side", modLoc("block/hibiscus_pink_side"))
                        .texture("plant", modLoc("block/hibiscus_plant"))
                        .renderType("cutout")
        );

        simpleBlockWithItem(
                ModBlocks.HIBISCUS_ORANGE.get(),
                models().withExistingParent(
                                "hibiscus_orange",
                                mcLoc("block/azalea")
                        )
                        .texture("top", modLoc("block/hibiscus_orange_top"))
                        .texture("side", modLoc("block/hibiscus_orange_side"))
                        .texture("plant", modLoc("block/hibiscus_plant"))
                        .renderType("cutout")
        );

        simpleLeaves(ModBlocks.HIBISCUS_LEAVES);
        simpleLeaves(ModBlocks.FLOWERING_HIBISCUS_MAGENTA_LEAVES);
        simpleLeaves(ModBlocks.FLOWERING_HIBISCUS_PINK_LEAVES);
        simpleLeaves(ModBlocks.FLOWERING_HIBISCUS_ORANGE_LEAVES);
        //horizontalBlock(ModBlocks.COCONUT_PALM_THATCH.get(),
                //models().cubeAll("coconut_palm_thatch", modLoc("block/coconut_palm_thatch")));
        //item(ModBlocks.COCONUT_PALM_THATCH);

        //slabBlock((SlabBlock) ModBlocks.COCONUT_PALM_THATCH_SLAB.get(),
                //blockTexture(ModBlocks.COCONUT_PALM_THATCH.get()),
                //blockTexture(ModBlocks.COCONUT_PALM_THATCH.get()));

        //stairsBlock((StairBlock) ModBlocks.COCONUT_PALM_THATCH_ROOF.get(),
                //blockTexture(ModBlocks.COCONUT_PALM_THATCH.get()));


        //simpleBlock(ModBlocks.COCONUT_PALM_BED.get(),
        //        models().withExistingParent("coconut_palm_bed", mcLoc("block/bed")));
        //item(ModBlocks.COCONUT_PALM_BED);
        //simpleLeaves(ModBlocks.COCONUT_PALM_LEAVES);

        //ModelFile AppleSaplingModel = models()
        //        .cross(regPath(ModBlocks.COCONUT_PALM_SPROUTS.get()), modLoc("block/coconut_palm_sprouts"))
        //        .renderType("cutout");
        //simpleBlock(ModBlocks.COCONUT_PALM_SPROUTS.get(), AppleSaplingModel);

        //simpleBlockWithItem(ModBlocks.GREEN_COCONUT_BLOCK);
        //simpleBlockWithItem(ModBlocks.MATURE_COCONUT_BLOCK);
    }

    private void simpleBlockWithItem(RegistryObject<Block> block){

        simpleBlockWithItem(block.get() , cubeAll(block.get()));
    }



    /** 登録パス（apple_sapling など） */
    private static String regPath(Block b) {
        return ForgeRegistries.BLOCKS.getKey(b).getPath();
    }

    // ブロック用のアイテムモデルを作成
    private void item(RegistryObject<Block> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(
                CoconutPalmMod.MOD_ID + ":block/" +
                        ForgeRegistries.BLOCKS.getKey(block.get()).getPath()
        ));
    }

    // 普通の葉ブロック
    private void simpleLeaves(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), models().singleTexture(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                new ResourceLocation("minecraft:block/leaves"),
                "all", blockTexture(block.get())).renderType("cutout"));
    }

    private void sapling(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }



}
