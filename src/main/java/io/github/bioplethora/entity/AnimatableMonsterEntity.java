package io.github.bioplethora.entity;

import io.github.bioplethora.item.weapons.BellophgolemShieldItem;
import io.github.bioplethora.registry.BioplethoraItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class AnimatableMonsterEntity extends MonsterEntity implements IAnimatable {

    protected static final DataParameter<Boolean> MOVING = EntityDataManager.defineId(AnimatableMonsterEntity.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> ATTACKING = EntityDataManager.defineId(AnimatableMonsterEntity.class, DataSerializers.BOOLEAN);
    /*protected static final DataParameter<Boolean> SMASHING = EntityDataManager.defineId(AnimatableHostileEntity.class, DataSerializers.BOOLEAN);*/

    protected boolean isAnimationFinished = false;

    /**
     * @param type
     * @param worldIn
     */
    public AnimatableMonsterEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    abstract public void registerControllers(AnimationData data);

    @Override
    abstract public AnimationFactory getFactory();

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOVING, false);
        this.entityData.define(ATTACKING, false);
        /*this.entityData.define(SMASHING, false);*/
    }

    /*protected void damageShieldFor(PlayerEntity holder, float damage) {
        if (holder.getUseItem().isShield(holder)) {
            if (!this.level.isClientSide) {
                holder.awardStat(Stats.ITEM_USED.get(holder.getUseItem().getItem()));
            }
            if (damage >= 3.0F) {
                int i = 1 + MathHelper.floor(damage);
                Hand hand = holder.getActiveHand();
                holder.getActiveItemStack().damageItem(i, holder, (p_213833_1_) -> {
                    p_213833_1_.sendBreakAnimation(hand);
                    net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(holder, holder.getActiveItemStack(), hand);
                });
                if (holder.getActiveItemStack().isEmpty()) {
                    if (hand == Hand.MAIN_HAND) {
                        holder.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
                    } else {
                        holder.setItemStackToSlot(EquipmentSlotType.OFFHAND, ItemStack.EMPTY);
                    }
                    holder.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
                }
            }

        }
    }*/

    public boolean getMoving() {
        return this.entityData.get(MOVING);
    }

    public void setMoving(boolean moving) {
        this.entityData.set(MOVING, moving);
    }

    public boolean getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    /*public boolean getSmashing() {
        return this.entityData.get(SMASHING);
    }

    /*public void setSmashing(boolean smashing) {
        this.entityData.set(SMASHING, smashing);
    }*/

    //protected abstract boolean hurt();
}