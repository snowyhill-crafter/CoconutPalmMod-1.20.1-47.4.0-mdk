package com.snowyhill.coconutpalmmod.registry;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.block.*;
import com.snowyhill.coconutpalmmod.client.ModWoodTypes;
import com.snowyhill.coconutpalmmod.worldgen.tree.CoconutPalmTreeGrower;
import com.snowyhill.coconutpalmmod.worldgen.tree.HibiscusMagentaTreeGrower;
import com.snowyhill.coconutpalmmod.worldgen.tree.HibiscusOrangeTreeGrower;
import com.snowyhill.coconutpalmmod.worldgen.tree.HibiscusPinkTreeGrower;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CoconutPalmMod.MOD_ID);






    public static final RegistryObject<Block> COCONUT_PALM_LOG = BLOCKS.register("coconut_palm_log",
            () -> new ModStrippableLogBlock(
                    BlockBehaviour.Properties.copy(Blocks.OAK_LOG),
                    () -> ModBlocks.STRIPPED_COCONUT_PALM_LOG.get()
            )
    );
    // 樹皮はがし後（stripped）原木
    public static final RegistryObject<Block> STRIPPED_COCONUT_PALM_LOG = BLOCKS.register("stripped_coconut_palm_log",
            () -> new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG))
    );

    public static final RegistryObject<Block> COCONUT_PALM_WOOD = BLOCKS.register("coconut_palm_wood",
            () -> new ModStrippableLogBlock(
                    BlockBehaviour.Properties.copy(Blocks.OAK_WOOD),
                    () -> ModBlocks.STRIPPED_COCONUT_PALM_WOOD.get()
            )
    );
    public static final RegistryObject<Block> STRIPPED_COCONUT_PALM_WOOD = BLOCKS.register("stripped_coconut_palm_wood",
            () -> new ModLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD))
    );



    //クラフト
// 板材
    public static final RegistryObject<Block> COCONUT_PALM_PLANKS = BLOCKS.register(
            "coconut_palm_planks",
            () -> new ModPlanksBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    // ハーフブロック
    public static final RegistryObject<Block> COCONUT_PALM_SLAB = BLOCKS.register(
            "coconut_palm_slab",
            () -> new ModSlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB))
    );
    // 階段
    public static final RegistryObject<Block> COCONUT_PALM_STAIRS = BLOCKS.register(
            "coconut_palm_stairs",
            () -> new ModStairBlock(
                    () -> ModBlocks.COCONUT_PALM_PLANKS.get().defaultBlockState(), // ← Supplier<BlockState>
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            )
    );
    // フェンス
    public static final RegistryObject<Block> COCONUT_PALM_FENCE = BLOCKS.register(
            "coconut_palm_fence",
            () -> new ModFenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE))
    );
    // フェンスゲート
    public static final RegistryObject<Block> COCONUT_PALM_FENCE_GATE = BLOCKS.register(
            "coconut_palm_fence_gate",
            () -> new ModFenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE))
    );
    // ドア
    public static final RegistryObject<Block> COCONUT_PALM_DOOR = BLOCKS.register(
            "coconut_palm_door",
            () -> new ModDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR))
    );
    // トラップドア
    public static final RegistryObject<Block> COCONUT_PALM_TRAPDOOR = BLOCKS.register(
            "coconut_palm_trapdoor",
            () -> new ModTrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR))
    );
    // ボタン
    public static final RegistryObject<Block> COCONUT_PALM_BUTTON = BLOCKS.register(
            "coconut_palm_button",
            () -> new ModButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON))
    );
    // 感圧板
    public static final RegistryObject<Block> COCONUT_PALM_PRESSURE_PLATE = BLOCKS.register(
            "coconut_palm_pressure_plate",
            () -> new ModPressurePlateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE))
    );

    public static final RegistryObject<Block> COCONUT_PALM_SIGN = BLOCKS.register("coconut_palm_sign",
            () -> new ModStandingSignBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD)
                            .strength(1.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion() //光・描写を阻害しない
                            .noCollission(), //衝突無し
                    ModWoodTypes.COCONUT_PALM
            ));

    public static final RegistryObject<Block> COCONUT_PALM_WALL_SIGN = BLOCKS.register("coconut_palm_wall_sign",
            () -> new ModWallSignBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD)
                            .strength(1.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion() //光・描写を阻害しない
                            .noCollission(), //衝突無し
                    ModWoodTypes.COCONUT_PALM
            ));

    public static final RegistryObject<Block> COCONUT_PALM_HANGING_SIGN = BLOCKS.register("coconut_palm_hanging_sign",
            () -> new ModCeilingHangingSignBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD)
                            .strength(1.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion() //光・描写を阻害しない
                            .noCollission(), //衝突無し
                    ModWoodTypes.COCONUT_PALM
            ));

    public static final RegistryObject<Block> COCONUT_PALM_WALL_HANGING_SIGN = BLOCKS.register("coconut_palm_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD)
                            .strength(1.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion() //光・描写を阻害しない
                            .noCollission(), //衝突無し
                    ModWoodTypes.COCONUT_PALM
            ));

    //public static final RegistryObject<Block> COCONUT_PALM_BED = BLOCKS.register("coconut_palm_bed",
            //CoconutPalmBedBlock::new);

    public static final RegistryObject<Block> COCONUT_PALM_LEAVES = BLOCKS.register(
            "coconut_palm_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()));

    public static final RegistryObject<Block> COCONUT_PALM_THATCH = BLOCKS.register(
            "coconut_palm_thatch",
            () -> new ModThatchBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()));

    public static final RegistryObject<Block> COCONUT_PALM_THATCH_ROOF = BLOCKS.register(
            "coconut_palm_thatch_roof",
            () -> new ModThatchRoofBlock(
                    () -> ModBlocks.COCONUT_PALM_THATCH.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()));

    public static final RegistryObject<Block> COCONUT_PALM_THATCH_SLAB = BLOCKS.register(
            "coconut_palm_thatch_slab",
            () -> new ModThatchSlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> COCONUT_PALM_FLOWER = BLOCKS.register("coconut_palm_flower",
            () -> new CoconutPalmFlowerBlock(
                    BlockBehaviour.Properties.copy(Blocks.WHEAT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.CROP)
                            .randomTicks()
            ));

    public static final RegistryObject<Block> GREEN_COCONUT_BLOCK = BLOCKS.register("green_coconut_block",
            () -> new GreenCoconutBlock(
                    BlockBehaviour.Properties.copy(Blocks.WHEAT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.CROP)
                            .randomTicks()
            ));

    public static final RegistryObject<Block> MATURE_COCONUT_BLOCK = BLOCKS.register("mature_coconut_block",
            () -> new MatureCoconutBlock(
                    BlockBehaviour.Properties.copy(Blocks.WHEAT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.CROP)
                            .randomTicks()
            ));

    public static final RegistryObject<Block> COCONUT_FIBER_BLOCK = BLOCKS.register(
            "coconut_fiber_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));

    public static final RegistryObject<Block> COCONUT_FIBER_CARPET = BLOCKS.register(
            "coconut_fiber_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET))
    );

    //植えた状態
    public static final RegistryObject<Block> COCONUT_PALM_SPROUTS = BLOCKS.register(
            "coconut_palm_sprouts",
            () -> new SandSaplingBlock(
                    new CoconutPalmTreeGrower(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
            )
    );

    public static final RegistryObject<Block> HIBISCUS_MAGENTA = BLOCKS.register(
            "hibiscus_magenta",
            () -> new HibiscusBlock(
                    new HibiscusMagentaTreeGrower(),
                    BlockBehaviour.Properties.copy(Blocks.AZALEA)
            )
    );

    public static final RegistryObject<Block> HIBISCUS_PINK = BLOCKS.register(
            "hibiscus_pink",
            () -> new HibiscusBlock(
                    new HibiscusPinkTreeGrower(),
                    BlockBehaviour.Properties.copy(Blocks.AZALEA)
            )
    );

    public static final RegistryObject<Block> HIBISCUS_ORANGE = BLOCKS.register(
            "hibiscus_orange",
            () -> new HibiscusBlock(
                    new HibiscusOrangeTreeGrower(),
                    BlockBehaviour.Properties.copy(Blocks.AZALEA)
            )
    );

    public static final RegistryObject<Block> HIBISCUS_LEAVES = BLOCKS.register(
            "hibiscus_leaves",
            () -> new ModLeavesBlock(
                    BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES)
                            .randomTicks()
            )
    );



    public static final RegistryObject<Block> FLOWERING_HIBISCUS_MAGENTA_LEAVES = BLOCKS.register(
            "flowering_hibiscus_magenta_leaves",
            () -> new ModLeavesBlock(
                    BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES)
                            .randomTicks()
            )
    );

    public static final RegistryObject<Block> FLOWERING_HIBISCUS_PINK_LEAVES = BLOCKS.register(
            "flowering_hibiscus_pink_leaves",
            () -> new ModLeavesBlock(
                    BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES)
                            .randomTicks()
            )
    );

    public static final RegistryObject<Block> FLOWERING_HIBISCUS_ORANGE_LEAVES = BLOCKS.register(
            "flowering_hibiscus_orange_leaves",
            () -> new ModLeavesBlock(
                    BlockBehaviour.Properties.copy(Blocks.AZALEA_LEAVES)
                            .randomTicks()
            )
    );
    /* ブロックアイテム作成用メソッド */
   // private static <T extends Block> RegistryObject<T> registerBlockItem(String name,
    //                                                                     Supplier<T> supplier) {
        // レジストリにブロックを追加
       // RegistryObject<T> block = BLOCKS.register(name, supplier);
        // ブロックアイテムをアイテムレジストリに追加
     //   ModItems.ITEMS.register(name,
   //             () -> new BlockItem(block.get(), new Item.Properties()));
     //   return block;
    // イベントバスに登録
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}

