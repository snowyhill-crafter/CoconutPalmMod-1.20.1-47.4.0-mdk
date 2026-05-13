package com.snowyhill.coconutpalmmod.blockentity;


import com.snowyhill.coconutpalmmod.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CoconutPalmHangingSignBlockEntity extends HangingSignBlockEntity {
    public CoconutPalmHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HANGING_SIGN.get(), pos, state);
    }
}
