package com.snowyhill.coconutpalmmod.datagen.server;



import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public  class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CoconutPalmMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.LOGS_THAT_BURN).add(
                ModBlocks.COCONUT_PALM_LOG.get(),
                ModBlocks.STRIPPED_COCONUT_PALM_LOG.get(),
                ModBlocks.COCONUT_PALM_WOOD.get(),
                ModBlocks.STRIPPED_COCONUT_PALM_WOOD.get()
        );

        this.tag(BlockTags.LEAVES)
                .add(
                        ModBlocks.COCONUT_PALM_LEAVES.get()
                );

        this.tag(BlockTags.LOGS)
                .add(
                        ModBlocks.COCONUT_PALM_LOG.get()
                );



        this.tag(ModTags.Blocks.COCONUT_PALM_LOG)
                .add(
                        ModBlocks.COCONUT_PALM_LOG.get(),
                        ModBlocks.STRIPPED_COCONUT_PALM_LOG.get(),
                        ModBlocks.COCONUT_PALM_WOOD.get(),
                        ModBlocks.STRIPPED_COCONUT_PALM_WOOD.get()
                );

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.COCONUT_PALM_THATCH.get(),
                ModBlocks.COCONUT_PALM_THATCH_ROOF.get(),
                ModBlocks.COCONUT_PALM_THATCH_SLAB.get()
        );

        // ←採掘速度に効くのはこっち
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                ModBlocks.COCONUT_PALM_SLAB.get(),
                ModBlocks.COCONUT_PALM_FENCE.get(),
                ModBlocks.COCONUT_PALM_FENCE_GATE.get(),
                ModBlocks.COCONUT_PALM_DOOR.get(),
                ModBlocks.COCONUT_PALM_TRAPDOOR.get(),
                ModBlocks.COCONUT_PALM_STAIRS.get(),
                ModBlocks.COCONUT_PALM_BUTTON.get(),
                ModBlocks.COCONUT_PALM_PRESSURE_PLATE.get(),

                ModBlocks.COCONUT_PALM_SIGN.get(),
                ModBlocks.COCONUT_PALM_WALL_SIGN.get(),
                ModBlocks.COCONUT_PALM_HANGING_SIGN.get(),
                ModBlocks.COCONUT_PALM_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.SAPLINGS).add(
                ModBlocks.COCONUT_PALM_SPROUTS.get()
        );


        this.tag(BlockTags.PLANKS).add(
                ModBlocks.COCONUT_PALM_PLANKS.get()
        );
        this.tag(BlockTags.SLABS).add(
                ModBlocks.COCONUT_PALM_SLAB.get(),
                ModBlocks.COCONUT_PALM_THATCH_SLAB.get()
        );

        this.tag(BlockTags.STAIRS).add(
                ModBlocks.COCONUT_PALM_STAIRS.get(),
                ModBlocks.COCONUT_PALM_THATCH_ROOF.get()
        );
        this.tag(BlockTags.FENCES).add(
                ModBlocks.COCONUT_PALM_FENCE.get()
        );
        this.tag(BlockTags.FENCE_GATES).add(
                ModBlocks.COCONUT_PALM_FENCE_GATE.get()
        );
        this.tag(BlockTags.DOORS).add(
                ModBlocks.COCONUT_PALM_DOOR.get()
          );
        this.tag(BlockTags.TRAPDOORS).add(
                ModBlocks.COCONUT_PALM_TRAPDOOR.get()
        );
        this.tag(BlockTags.BUTTONS).add(
                ModBlocks.COCONUT_PALM_BUTTON.get()
        );
        this.tag(BlockTags.PRESSURE_PLATES).add(
                ModBlocks.COCONUT_PALM_PRESSURE_PLATE.get()
        );

        // 看板系（バニラ準拠の分類）
        //tag(BlockTags.STANDING_SIGNS).add(ModBlocks.APPLE_SIGN.get());
        //tag(BlockTags.WALL_SIGNS).add(ModBlocks.APPLE_WALL_SIGN.get());

        //tag(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.APPLE_HANGING_SIGN.get());
        //tag(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.APPLE_WALL_HANGING_SIGN.get());

    }
}






