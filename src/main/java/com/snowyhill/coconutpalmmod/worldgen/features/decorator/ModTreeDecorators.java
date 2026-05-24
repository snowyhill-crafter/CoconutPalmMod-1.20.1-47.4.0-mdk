package com.snowyhill.coconutpalmmod.worldgen.features.decorator;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> DECORATORS =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, CoconutPalmMod.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<CoconutPalmFruitDecorator>>
            COCONUT_PALM_FRUIT_DECORATOR =
            DECORATORS.register("coconut_palm_fruit",
                    () -> new TreeDecoratorType<>(CoconutPalmFruitDecorator.CODEC));

    public static final RegistryObject<TreeDecoratorType<CoconutPalmGroundDecorator>>
            COCONUT_PALM_GROUND_DECORATOR =
            DECORATORS.register("coconut_palm_ground",
                    () -> new TreeDecoratorType<>(CoconutPalmGroundDecorator.CODEC));
    // 起動時にイベントバスへ登録するコード（メインMod側で呼ぶ）
    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        DECORATORS.register(bus);
    }


}
