package com.snowyhill.coconutpalmmod.datagen.server;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.registry.ModBlocks;
import com.snowyhill.coconutpalmmod.registry.ModItems;
import com.snowyhill.coconutpalmmod.tag.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Consumer;




public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }


    @Override
    //1.20.6protected void buildRecipes(RecipeOutput pRecipeOutput)
    protected void buildRecipes(Consumer<FinishedRecipe> pRecipeOutput)  {
            
    woodFromLogs(pRecipeOutput, ModBlocks.COCONUT_PALM_WOOD.get(),
                ModBlocks.COCONUT_PALM_LOG.get());

    woodFromLogs(pRecipeOutput, ModBlocks.STRIPPED_COCONUT_PALM_WOOD.get(),
                ModBlocks.STRIPPED_COCONUT_PALM_LOG.get());

    planksFromLog(pRecipeOutput,
                  ModBlocks.COCONUT_PALM_PLANKS.get(),

    ModTags.Items.COCONUT_PALM_LOG,4);

    slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS,
         ModBlocks.COCONUT_PALM_SLAB.get(),
                ModBlocks.COCONUT_PALM_PLANKS.get());

    stairs(pRecipeOutput,
           ModBlocks.COCONUT_PALM_STAIRS.get(),
                ModBlocks.COCONUT_PALM_PLANKS.get());

    fence(pRecipeOutput,
          ModBlocks.COCONUT_PALM_FENCE.get(),
                ModBlocks.COCONUT_PALM_PLANKS.get());

    fenceGate(pRecipeOutput,
              ModBlocks.COCONUT_PALM_FENCE_GATE.get(),
                ModBlocks.COCONUT_PALM_PLANKS.get());

    door(pRecipeOutput,
         ModBlocks.COCONUT_PALM_DOOR.get(),
                ModBlocks.COCONUT_PALM_PLANKS.get());

    trapdoor(pRecipeOutput,
             ModBlocks.COCONUT_PALM_TRAPDOOR.get(),
                ModBlocks.COCONUT_PALM_PLANKS.get());

    button(pRecipeOutput,
           ModBlocks.COCONUT_PALM_BUTTON.get(),
                ModBlocks.COCONUT_PALM_PLANKS.get());

    pressurePlate(pRecipeOutput,
                  ModBlocks.COCONUT_PALM_PRESSURE_PLATE.get(),
                ModBlocks.COCONUT_PALM_PLANKS.get());

        // --- 看板（3個） ---
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.COCONUT_PALM_SIGN_ITEM.get(), 3)
                .define('#', ModBlocks.COCONUT_PALM_PLANKS.get()) // ←あなたのリンゴ材の板ブロック
                .define('S', Items.STICK)
                .pattern("###")
                .pattern("###")
                .pattern(" S ")
                .unlockedBy("has_coconut_palm_planks", has(ModBlocks.COCONUT_PALM_PLANKS.get()))
                .save(pRecipeOutput);

        // --- 吊り看板（6個） ---
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.COCONUT_PALM_HANGING_SIGN_ITEM.get(), 6)
                .define('L', ModBlocks.STRIPPED_COCONUT_PALM_LOG.get()) // ←ストリップ済み丸太（同木種）
                .define('C', Items.CHAIN)
                .pattern("C C")
                .pattern("LLL")
                .pattern("LLL")
                .unlockedBy("has_stripped_coconut_palm_log", has(ModBlocks.STRIPPED_COCONUT_PALM_LOG.get()))
                .save(pRecipeOutput);

        // hibiscus flower → magenta dye
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        Items.MAGENTA_DYE,
                        1
                )
                .requires(ModItems.HIBISCUS_MAGENTA_FLOWER.get())
                .unlockedBy(
                        getHasName(ModItems.HIBISCUS_MAGENTA_FLOWER.get()),
                        has(ModItems.HIBISCUS_MAGENTA_FLOWER.get())
                )
                .save(
                        pRecipeOutput,
                        CoconutPalmMod.MOD_ID + ":magenta_dye_from_hibiscus_flower"
                );

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        Items.PINK_DYE,
                        1
                )
                .requires(ModItems.HIBISCUS_PINK_FLOWER.get())
                .unlockedBy(
                        getHasName(ModItems.HIBISCUS_PINK_FLOWER.get()),
                        has(ModItems.HIBISCUS_PINK_FLOWER.get())
                )
                .save(
                        pRecipeOutput,
                        CoconutPalmMod.MOD_ID + ":pink_dye_from_hibiscus_flower"
                );

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        Items.ORANGE_DYE,
                        1
                )
                .requires(ModItems.HIBISCUS_ORANGE_FLOWER.get())
                .unlockedBy(
                        getHasName(ModItems.HIBISCUS_ORANGE_FLOWER.get()),
                        has(ModItems.HIBISCUS_ORANGE_FLOWER.get())
                )
                .save(
                        pRecipeOutput,
                        CoconutPalmMod.MOD_ID + ":orange_dye_from_hibiscus_flower"
                );



        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.FOOD,
                        ModItems.SLICED_COCONUT.get(),
                        2
                )
                .requires(ModItems.MATURE_COCONUT.get())
                .unlockedBy(
                        "has_mature_coconut",
                        has(ModItems.MATURE_COCONUT.get())
                )
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.FOOD,
                        ModItems.COCONUT_SHELL.get(),
                        1
                )
                .requires(ModItems.SLICED_COCONUT.get())
                .unlockedBy(
                        "has_sliced_coconut",
                        has(ModItems.SLICED_COCONUT.get())
                )
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.TOOLS,
                        Items.BOWL,
                        1
                )
                .requires(ModItems.COCONUT_SHELL.get())
                .unlockedBy(
                        "has_coconut_shell",
                        has(ModItems.COCONUT_SHELL.get())
                )
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COCONUT_FIBER.get())
                .define('#', ModItems.COCONUT_SHELL.get())
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(ModItems.COCONUT_SHELL.get()), has(ModItems.COCONUT_SHELL.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_FIBER_BLOCK.get())
                .define('#', ModItems.COCONUT_FIBER.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(ModItems.COCONUT_FIBER.get()), has(ModItems.COCONUT_FIBER.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_FIBER_CARPET.get())
                .define('#', ModBlocks.COCONUT_FIBER_BLOCK.get())
                .pattern("###")
                .unlockedBy(getHasName(ModBlocks.COCONUT_FIBER_BLOCK.get()), has(ModBlocks.COCONUT_FIBER_BLOCK.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_PALM_THATCH.get())
                .define('#', ModItems.COCONUT_PALM_LEAF.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(ModItems.COCONUT_PALM_LEAF.get()), has(ModItems.COCONUT_PALM_LEAF.get()))
                .save(pRecipeOutput);


        slab(pRecipeOutput,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COCONUT_PALM_THATCH_SLAB.get(),
                ModBlocks.COCONUT_PALM_THATCH.get());

        stairs(pRecipeOutput,
                ModBlocks.COCONUT_PALM_THATCH_ROOF.get(),
                ModBlocks.COCONUT_PALM_THATCH.get());



       // ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.COCONUT_PALM_BED.get())
       //         .define('#', ModItems.COCONUT_PALM_LEAF.get())
       //         .define('P', ItemTags.PLANKS)
       //         .pattern("###")
       //         .pattern("PPP")
       //         .unlockedBy(getHasName(ModItems.COCONUT_PALM_LEAF.get()), has(ModItems.COCONUT_PALM_LEAF.get()))
       //         .save(pRecipeOutput);


    }
    // かまど用のレシピ
    protected static void oreSmelting(Consumer<FinishedRecipe> pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    // 溶鉱炉用のレシピ
    protected static void oreBlasting(Consumer<FinishedRecipe> pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pRecipeOutput, RecipeSerializer<?
            extends AbstractCookingRecipe> pSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput,
                            CoconutPalmMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> pRecipeOutput,
                                                  RecipeCategory pUnpackedCategory,
                                                  ItemLike pUnpacked,
                                                  RecipeCategory pPackedCategory,
                                                  ItemLike pPacked) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 9)
                .requires(pPacked).unlockedBy(getHasName(pPacked), has(pPacked)).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked).define('#', pUnpacked)
                .pattern("###").pattern("###").pattern("###")
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pRecipeOutput);
    }

    private static void stairs(Consumer<FinishedRecipe> pRecipeOutput, ItemLike pResult, ItemLike pIngredient) {
        stairBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pRecipeOutput);
    }
    private static void fence(Consumer<FinishedRecipe> pRecipeOutput, ItemLike pResult, ItemLike pIngredient) {
        fenceBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pRecipeOutput);
    }
    private static void fenceGate(Consumer<FinishedRecipe> pRecipeOutput, ItemLike pResult,
                                  ItemLike pIngredient) {
        fenceGateBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pRecipeOutput);
    }
    private static void door(Consumer<FinishedRecipe> pRecipeOutput, ItemLike pResult, ItemLike pIngredient) {
        doorBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pRecipeOutput);
    }
    private static void trapdoor(Consumer<FinishedRecipe> pRecipeOutput, ItemLike pResult,
                                 ItemLike pIngredient) {
        trapdoorBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pRecipeOutput);
    }
    private static void button(Consumer<FinishedRecipe> pRecipeOutput, ItemLike pResult, ItemLike pIngredient) {
        buttonBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pRecipeOutput);
    }
}