package com.snowyhill.coconutpalmmod.registry;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.blockentity.CoconutPalmHangingSignBlockEntity;
import com.snowyhill.coconutpalmmod.blockentity.CoconutPalmSignBlockEntity;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CoconutPalmMod.MOD_ID);

    /* ========= 通常の看板 ========= */

    public static final RegistryObject<BlockEntityType<CoconutPalmSignBlockEntity>> SIGN =
            BLOCK_ENTITIES.register("coconut_palm_sign",
                    () -> BlockEntityType.Builder.of(
                            CoconutPalmSignBlockEntity::new,
                            ModBlocks.COCONUT_PALM_SIGN.get(),
                            ModBlocks.COCONUT_PALM_WALL_SIGN.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<CoconutPalmHangingSignBlockEntity>> HANGING_SIGN =
            BLOCK_ENTITIES.register("coconut_palm_hanging_sign",
                    () -> BlockEntityType.Builder.of(
                            CoconutPalmHangingSignBlockEntity::new,
                            ModBlocks.COCONUT_PALM_HANGING_SIGN.get(),
                            ModBlocks.COCONUT_PALM_WALL_HANGING_SIGN.get()
                    ).build(null));


   // public static final RegistryObject<BlockEntityType<BedBlockEntity>> COCONUT_BED_BE =
   //         BLOCK_ENTITIES.register("coconut_palm_bed",
   //                 () -> BlockEntityType.Builder.of(BedBlockEntity::new, ModBlocks.COCONUT_PALM_BED.get()).build(null));
    /* ========= register ========= */

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
