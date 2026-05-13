package com.snowyhill.coconutpalmmod.item;

import com.snowyhill.coconutpalmmod.entity.ModBoatEntity;
import com.snowyhill.coconutpalmmod.entity.ModChestBoatEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class ModBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final ModBoatEntity.Type type;
    private final boolean hasChest;

    public ModBoatItem(boolean hasChest, ModBoatEntity.Type type, Properties properties) {
        super(properties);
        this.hasChest = hasChest;
        this.type = type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);

        if (hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vec3 = player.getViewVector(1.0F);
            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec31 = player.getEyePosition();
                for (Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate((double) entity.getPickRadius());
                    if (aabb.contains(vec31)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (hitresult.getType() == HitResult.Type.BLOCK) {
                // 1. ボートエンティティの生成 (Boatとして取得)
                Boat boat = this.getBoat(level, hitresult);

                // 2. 重要：バリアント（APPLE か DARK_APPLE か）をセットする
                // これを行わないと、レンダラー側で常にデフォルト（APPLE）が選ばれてしまいます
                if (boat instanceof ModBoatEntity modBoat) {
                    modBoat.setVariant(this.type);
                } else if (boat instanceof ModChestBoatEntity modChestBoat) {
                    modChestBoat.setVariant(this.type);
                }

                boat.setYRot(player.getYRot());

                if (!level.noCollision(boat, boat.getBoundingBox())) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!level.isClientSide) {
                        level.addFreshEntity(boat);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, hitresult.getLocation());
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

    /**
     * hasChestフラグに基づいて、通常ボートまたはチェストボートのインスタンスを返します。
     * 戻り値を共通の親クラスである Boat にすることで、型エラーを回避しています。
     */
    private Boat getBoat(Level level, HitResult hitResult) {
        double x = hitResult.getLocation().x;
        double y = hitResult.getLocation().y;
        double z = hitResult.getLocation().z;

        if (this.hasChest) {
            return new ModChestBoatEntity(level, x, y, z);
        } else {
            return new ModBoatEntity(level, x, y, z);
        }
    }
}