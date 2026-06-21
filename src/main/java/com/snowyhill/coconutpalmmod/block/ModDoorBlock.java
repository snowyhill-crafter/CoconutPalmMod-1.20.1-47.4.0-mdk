package com.snowyhill.coconutpalmmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModDoorBlock extends DoorBlock {

    public ModDoorBlock(Properties properties) {
        super(properties, BlockSetType.OAK);  // ← これが正しい
    }

}
