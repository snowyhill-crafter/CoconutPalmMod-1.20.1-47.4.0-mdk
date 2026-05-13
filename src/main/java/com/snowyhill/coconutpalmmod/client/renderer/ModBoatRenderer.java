package com.snowyhill.coconutpalmmod.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;

import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import com.snowyhill.coconutpalmmod.entity.ModBoatEntity;
import com.snowyhill.coconutpalmmod.entity.ModChestBoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends BoatRenderer {
    private final Map<ModBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context context, boolean isChestBoat) {
        super(context, isChestBoat);

        this.boatResources = Stream.of(ModBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(
                type -> type,
                type -> Pair.of(
                        new ResourceLocation(CoconutPalmMod.MOD_ID, getTexturePath(type, isChestBoat)),

                        isChestBoat ? new ChestBoatModel(context.bakeLayer(ModelLayers.createChestBoatModelName(Boat.Type.OAK)))
                                : new BoatModel(context.bakeLayer(ModelLayers.createBoatModelName(Boat.Type.OAK)))
                )
        ));
    }

    private static String getTexturePath(ModBoatEntity.Type type, boolean isChestBoat) {
        return isChestBoat ? "textures/entity/chest_boat/" + type.getName() + ".png"
                : "textures/entity/boat/" + type.getName() + ".png";
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        // エンティティが持っているバリアント(APPLE or DARK_APPLE)を取得して、対応するリソースを返す
        if (boat instanceof ModBoatEntity modBoat) {
            return this.boatResources.get(modBoat.getModVariant());
        } else if (boat instanceof ModChestBoatEntity modChestBoat) {
            return this.boatResources.get(modChestBoat.getModVariant());
        }
        // 万が一のフォールバック
        return this.boatResources.get(ModBoatEntity.Type.COCONUT_PALM);
    }
}