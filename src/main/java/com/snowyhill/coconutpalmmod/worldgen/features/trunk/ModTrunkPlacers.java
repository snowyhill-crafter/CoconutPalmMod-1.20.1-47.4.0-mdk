package com.snowyhill.coconutpalmmod.worldgen.features.trunk;


import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacers {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, CoconutPalmMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<CoconutTrunkPlacer>> COCONUT_TRUNK =
            TRUNK_PLACERS.register("coconut_trunk",
                    () -> new TrunkPlacerType<>(CoconutTrunkPlacer.CODEC));
}