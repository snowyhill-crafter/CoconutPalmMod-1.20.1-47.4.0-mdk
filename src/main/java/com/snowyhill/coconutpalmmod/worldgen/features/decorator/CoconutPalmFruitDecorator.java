package com.snowyhill.coconutpalmmod.worldgen.features.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class CoconutPalmFruitDecorator extends TreeDecorator {

    // 1.20.1向け: MapCodec ではなく Codec
    public static final Codec<CoconutPalmFruitDecorator> CODEC =
            RecordCodecBuilder.create(inst ->
                    inst.group(
                            Codec.FLOAT.fieldOf("chance").forGetter(d -> d.chance)
                    ).apply(inst, CoconutPalmFruitDecorator::new)
            );

    private final float chance;

    public CoconutPalmFruitDecorator(float chance) {
        this.chance = chance;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecorators.COCONUT_PALM_FRUIT_DECORATOR.get();
    }

    @Override
    public void place(Context ctx) {
        var rand = ctx.random();

        for (BlockPos leafPos : ctx.leaves()) {
            if (rand.nextFloat() >= this.chance) continue;

            BlockPos fruitPos = leafPos.below();
            if (!ctx.isAir(fruitPos)) continue;

            if (hasAdjCoconutLog(ctx, fruitPos) || hasAdjCoconutLog(ctx, leafPos)) {
                BlockState coconut = ModBlocks.COCONUT_PALM_FLOWER.get().defaultBlockState();
                ctx.setBlock(fruitPos, coconut);
            }
        }
    }

    private static boolean hasAdjCoconutLog(Context ctx, BlockPos pos) {
        var level = ctx.level();

        for (Direction d : Direction.Plane.HORIZONTAL) {
            BlockPos adj = pos.relative(d);
            if (level.isStateAtPosition(adj, s -> s.is(ModBlocks.COCONUT_PALM_LOG.get()))) {
                return true;
            }
        }

        return false;
    }
}