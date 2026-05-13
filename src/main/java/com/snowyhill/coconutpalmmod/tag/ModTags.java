package com.snowyhill.coconutpalmmod.tag;



import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

        public static class Blocks {
            public static final TagKey<Block> COCONUT_PALM_LOG = tag("coconut_palm_log");
            // 追加：砂・赤砂・土・草。将来拡張もここに追加）
            public static final TagKey<Block> COCONUT_SPROUTS_PLANTABLE =
                    tag("coconut_sprouts_plantable");
                        private static TagKey<Block> tag(String name) {
                return BlockTags.create(
                        ResourceLocation.fromNamespaceAndPath(CoconutPalmMod.MOD_ID, name)
                );
            }
        }

        public static class Items {
            public static final TagKey<Item> COCONUT_PALM_LOG = tag("coconut_palm_log");
                        private static TagKey<Item> tag(String name) {
                return ItemTags.create(
                        ResourceLocation.fromNamespaceAndPath(CoconutPalmMod.MOD_ID, name)
                );
            }
        }

}
