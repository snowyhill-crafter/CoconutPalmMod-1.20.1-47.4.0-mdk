package com.snowyhill.coconutpalmmod.datagen.server.loot;

import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.registry.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.COCONUT_PALM_SPROUTS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MATURE_COCONUT.get()))
                ));
        this.dropSelf(ModBlocks.COCONUT_PALM_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_COCONUT_PALM_LOG.get());
        this.dropSelf(ModBlocks.COCONUT_PALM_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_COCONUT_PALM_WOOD.get());
        //リンゴ木材
        this.dropSelf(ModBlocks.COCONUT_PALM_PLANKS.get());
        this.dropSelf(ModBlocks.COCONUT_PALM_STAIRS.get());
        this.dropSelf(ModBlocks.COCONUT_PALM_FENCE.get());
        this.dropSelf(ModBlocks.COCONUT_PALM_FENCE_GATE.get());
        this.dropSelf(ModBlocks.COCONUT_PALM_TRAPDOOR.get());
        this.dropSelf(ModBlocks.COCONUT_PALM_BUTTON.get());
        this.dropSelf(ModBlocks.COCONUT_PALM_PRESSURE_PLATE.get());
        //重ねたときに壊しても二つドロップさせる
        this.add(ModBlocks.COCONUT_PALM_SLAB.get(),
                createSlabItemTable(ModBlocks.COCONUT_PALM_SLAB.get()));
        //指定しないとドアが二つドロップしてしまうので対処した
        this.add(ModBlocks.COCONUT_PALM_DOOR.get(),
                createDoorTable(ModBlocks.COCONUT_PALM_DOOR.get()));
        // ORNAMENTAL_* 系の葉。素手だとドロップなし、ハサミで壊すと自分をドロップ

        // 立て看板 → sign をドロップ
        this.add(ModBlocks.COCONUT_PALM_SIGN.get(),
                block -> createSingleItemTable(ModItems.COCONUT_PALM_SIGN_ITEM.get()));

        // 壁看板 → sign をドロップ
        this.add(ModBlocks.COCONUT_PALM_WALL_SIGN.get(),
                block -> createSingleItemTable(ModItems.COCONUT_PALM_SIGN_ITEM.get()));

        // 吊り看板（天井） → hanging_sign をドロップ
        this.add(ModBlocks.COCONUT_PALM_HANGING_SIGN.get(),
                block -> createSingleItemTable(ModItems.COCONUT_PALM_HANGING_SIGN_ITEM.get()));

        // 吊り看板（壁） → hanging_sign をドロップ
        this.add(ModBlocks.COCONUT_PALM_WALL_HANGING_SIGN.get(),
                block -> createSingleItemTable(ModItems.COCONUT_PALM_HANGING_SIGN_ITEM.get()));

        //this.dropSelf(ModBlocks.COCONUT_PALM_BED.get());


        // COCONUT_PALM_LEAVES
        this.add(ModBlocks.COCONUT_PALM_LEAVES.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        // ハサミなら葉ブロックそのもの
                        .add(LootItem.lootTableItem(ModBlocks.COCONUT_PALM_LEAVES.get())
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))))
                        // それ以外は frond を 1～3 個
                        .add(LootItem.lootTableItem(ModItems.COCONUT_PALM_LEAF.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .when(InvertedLootItemCondition.invert(
                                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))
                                )))
                )
        );

        this.dropSelf(ModBlocks.COCONUT_PALM_THATCH.get());
        this.dropSelf(ModBlocks.COCONUT_PALM_THATCH_ROOF.get());
        this.add(ModBlocks.COCONUT_PALM_THATCH_SLAB.get(),
                createSlabItemTable(ModBlocks.COCONUT_PALM_THATCH_SLAB.get()));

        this.add(ModBlocks.COCONUT_PALM_FLOWER.get(), LootTable.lootTable());

        this.add(ModBlocks.GREEN_COCONUT_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.GREEN_COCONUT.get())
                        )));


        this.add(ModBlocks.MATURE_COCONUT_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MATURE_COCONUT.get())
                        )));

        this.dropSelf(ModBlocks.HIBISCUS.get());


        this.add(ModBlocks.HIBISCUS_LEAVES.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))

                        // ハサミなら茂みそのもの
                        .add(LootItem.lootTableItem(ModBlocks.HIBISCUS_LEAVES.get())
                                .when(MatchTool.toolMatches(
                                        ItemPredicate.Builder.item().of(Items.SHEARS)
                                )))

                        // 通常破壊なら低確率でHIBISCUS
                        .add(LootItem.lootTableItem(ModBlocks.HIBISCUS.get())
                                .when(LootItemRandomChanceCondition.randomChance(0.25F))
                                .when(InvertedLootItemCondition.invert(
                                        MatchTool.toolMatches(
                                                ItemPredicate.Builder.item().of(Items.SHEARS)
                                        )
                                )))
                )
        );



        this.add(ModBlocks.FLOWERING_HIBISCUS_LEAVES.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))

                        // ハサミなら葉そのもの
                        .add(LootItem.lootTableItem(ModBlocks.FLOWERING_HIBISCUS_LEAVES.get())
                                .when(MatchTool.toolMatches(
                                        ItemPredicate.Builder.item().of(Items.SHEARS)
                                )))

                        // 通常破壊なら hibiscus flower 確定
                        .add(LootItem.lootTableItem(ModItems.HIBISCUS_FLOWER.get())
                                .when(InvertedLootItemCondition.invert(
                                        MatchTool.toolMatches(
                                                ItemPredicate.Builder.item().of(Items.SHEARS)
                                        )
                                )))

                        // 通常破壊なら低確率でHIBISCUS
                        .add(LootItem.lootTableItem(ModBlocks.HIBISCUS.get())
                                .when(LootItemRandomChanceCondition.randomChance(0.25F))
                                .when(InvertedLootItemCondition.invert(
                                        MatchTool.toolMatches(
                                                ItemPredicate.Builder.item().of(Items.SHEARS)
                                        )
                                )))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    /** バニラの potted_xxx と同等: flower_pot 1個 + plant 1個（爆発時は中身のみ条件付き） */
    private static LootTable.Builder pottedPlantLoot(ItemLike plant) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.FLOWER_POT)))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(plant))
                        .when(ExplosionCondition.survivesExplosion()));
    }

}
