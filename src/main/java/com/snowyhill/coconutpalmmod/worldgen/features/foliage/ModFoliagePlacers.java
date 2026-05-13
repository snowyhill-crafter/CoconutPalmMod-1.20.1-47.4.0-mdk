package com.snowyhill.coconutpalmmod.worldgen.features.foliage;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, CoconutPalmMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE =
            FOLIAGE_PLACERS.register("palm_foliage",
                    () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
}
