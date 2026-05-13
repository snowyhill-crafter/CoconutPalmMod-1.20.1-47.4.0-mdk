package com.snowyhill.coconutpalmmod.item;

import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class MatureCoconutItem extends Item {
    public MatureCoconutItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos clicked = ctx.getClickedPos();
        Direction face = ctx.getClickedFace();

        if (face == Direction.DOWN) return InteractionResult.PASS;

        BlockState clickedState = level.getBlockState(clicked);
        BlockPos placePos;
        BlockPos groundPos;

        if (clickedState.canBeReplaced()) {
            placePos = clicked;
            groundPos = clicked.below();
        } else {
            if (face != Direction.UP) return InteractionResult.PASS;
            placePos = clicked.above();
            groundPos = clicked;
        }

        BlockState ground = level.getBlockState(groundPos);
        if (!(ground.is(ModTags.Blocks.COCONUT_SPROUTS_PLANTABLE)
                || ground.is(Blocks.SAND)
                || ground.is(Blocks.RED_SAND)
                || ground.is(Blocks.DIRT)
                || ground.is(Blocks.GRASS_BLOCK))) {
            return InteractionResult.PASS;
        }

        BlockState placeState = level.getBlockState(placePos);
        if (!placeState.canBeReplaced()) {
            return InteractionResult.PASS;
        }

        BlockState sprouts = ModBlocks.COCONUT_PALM_SPROUTS.get().defaultBlockState();

        if (!level.isClientSide) {
            level.setBlock(placePos, sprouts, 3);

            Player player = ctx.getPlayer();
            ItemStack stack = ctx.getItemInHand();

            SoundType s = sprouts.getSoundType();
            level.playSound(null, placePos, s.getPlaceSound(), SoundSource.BLOCKS,
                    (s.getVolume() + 1f) / 2f, s.getPitch());
            level.gameEvent(player, GameEvent.BLOCK_PLACE, placePos);

            if (player == null || !player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}