package com.snowyhill.coconutpalmmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModPressurePlateBlock extends PressurePlateBlock {

    public ModPressurePlateBlock(Properties properties) {
        // Sensitivity は木の感圧板と同じ EVERYTHING
        super(Sensitivity.EVERYTHING, properties, BlockSetType.OAK);
    }

}
