package com.snowyhill.coconutpalmmod.block;

import com.snowyhill.coconutpalmmod.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;

public class CoconutPalmBedBlock extends BedBlock {
    public CoconutPalmBedBlock() {
                super(DyeColor.WHITE, BlockBehaviour.Properties.copy(Blocks.WHITE_BED));
    }

    //@Override
    //public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    //    return ModBlockEntities.COCONUT_BED_BE.get().create(pos, state);
    //}




}