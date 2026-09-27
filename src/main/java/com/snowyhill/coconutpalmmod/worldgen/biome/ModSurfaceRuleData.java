package com.snowyhill.coconutpalmmod.worldgen.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRuleData {

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource makeRules() {

        // Tropical Beachだけに適用
        SurfaceRules.ConditionSource isTropicalBeach =
                SurfaceRules.isBiome(ModBiomes.TROPICAL_BEACH);

        SurfaceRules.RuleSource sand =
                makeStateRule(Blocks.SAND);

        SurfaceRules.RuleSource sandstone =
                makeStateRule(Blocks.SANDSTONE);


        // 表面から2ブロック以内
        SurfaceRules.ConditionSource sandLayer =
                SurfaceRules.stoneDepthCheck(
                        1,
                        false,
                        0,
                        CaveSurface.FLOOR
                );

        // 表面から3ブロック以内
        SurfaceRules.ConditionSource sandstoneLayer =
                SurfaceRules.stoneDepthCheck(
                        2,
                        false,
                        0,
                        CaveSurface.FLOOR
                );


        return SurfaceRules.ifTrue(
                isTropicalBeach,

                // 地表側だけに適用
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),

                        SurfaceRules.sequence(

                                // 1～2層目
                                SurfaceRules.ifTrue(
                                        sandLayer,
                                        sand
                                ),

                                // 3層目
                                SurfaceRules.ifTrue(
                                        sandstoneLayer,
                                        sandstone
                                )
                        )
                )
        );
    }
}