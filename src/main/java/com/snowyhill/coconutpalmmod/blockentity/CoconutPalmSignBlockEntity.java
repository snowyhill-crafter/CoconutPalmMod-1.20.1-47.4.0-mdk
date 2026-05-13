package com.snowyhill.coconutpalmmod.blockentity;


import com.snowyhill.coconutpalmmod.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CoconutPalmSignBlockEntity extends SignBlockEntity {
    public CoconutPalmSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SIGN.get(), pos, state);
    }
}
