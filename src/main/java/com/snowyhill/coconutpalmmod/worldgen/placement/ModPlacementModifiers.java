package com.snowyhill.coconutpalmmod.worldgen.placement;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacementModifiers {

    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS =
            DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE,
                    CoconutPalmMod.MOD_ID);

    public static final RegistryObject<PlacementModifierType<BeachGrassPatchPlacement>>
            BEACH_GRASS_PATCH =
            PLACEMENT_MODIFIERS.register("beach_grass_patch",
                    () -> () -> BeachGrassPatchPlacement.CODEC);
}