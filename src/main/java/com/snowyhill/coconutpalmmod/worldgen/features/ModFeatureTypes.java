package com.snowyhill.coconutpalmmod.worldgen.features;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatureTypes {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(
                    ForgeRegistries.FEATURES,
                    CoconutPalmMod.MOD_ID
            );


    public static final RegistryObject<
            Feature<NoneFeatureConfiguration>
            > SHALLOW_WATER =

            FEATURES.register(
                    "shallow_water",
                    () ->
                            new ShallowWaterFeature(
                                    NoneFeatureConfiguration.CODEC
                            )
            );


    public static void register(IEventBus eventBus) {

        FEATURES.register(eventBus);
    }
}