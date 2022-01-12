package io.github.bioplethora.entity.creatures;

import io.github.bioplethora.config.BioplethoraConfig;
import io.github.bioplethora.entity.IBioplethoraEntityClass;
import io.github.bioplethora.entity.SummonableMonsterEntity;
import io.github.bioplethora.entity.ai.CopyTargetOwnerGoal;
import io.github.bioplethora.entity.ai.WindblazeRangedAttackGoal;
import io.github.bioplethora.entity.ai.monster.MonsterAnimatableMeleeGoal;
import io.github.bioplethora.entity.ai.monster.MonsterAnimatableMoveToTargetGoal;
import io.github.bioplethora.util.BioplethoraEntityClasses;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Iterator;

public class AlphemEntity extends SummonableMonsterEntity implements IAnimatable, IBioplethoraEntityClass {

    private static final DataParameter<Boolean> DATA_IS_CHARGING = EntityDataManager.defineId(AlphemEntity.class, DataSerializers.BOOLEAN);
    private final AnimationFactory factory = new AnimationFactory(this);

    public AlphemEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.noCulling = true;
    }

    @Override
    public BioplethoraEntityClasses getBioplethoraClass() {
        return BioplethoraEntityClasses.DANGERUM;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createLivingAttributes()
                .add(Attributes.ARMOR, 4 * BioplethoraConfig.COMMON.mobArmorMultiplier.get())
                .add(Attributes.ATTACK_SPEED, 10)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 7 * BioplethoraConfig.COMMON.mobMeeleeDamageMultiplier.get())
                .add(Attributes.MAX_HEALTH, 20 * BioplethoraConfig.COMMON.mobHealthMultiplier.get())
                .add(Attributes.MOVEMENT_SPEED, 0.25 * BioplethoraConfig.COMMON.mobMovementSpeedMultiplier.get())
                .add(Attributes.FOLLOW_RANGE, 64D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 24.0F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, AnimalEntity.class, 24.0F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, BellophgolemEntity.class, 24.0F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, AltyrusEntity.class, 24.0F));
        this.goalSelector.addGoal(2, new MonsterAnimatableMoveToTargetGoal(this, 1.6, 8));
        this.goalSelector.addGoal(2, new MonsterAnimatableMeleeGoal(this, 40, 0.5, 0.6));
        this.goalSelector.addGoal(3, new WindblazeRangedAttackGoal(this));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new SwimGoal(this));
        this.goalSelector.addGoal(6, new FollowMobGoal(this, (float) 1, 10, 5));
        this.targetSelector.addGoal(1, new CopyTargetOwnerGoal(this, this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, BellophgolemEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AltyrusEntity.class, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(AlphemEntity.class));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.dead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem.death", true));
            return PlayState.CONTINUE;
        }

        if (this.getAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem.attack", true));
            return PlayState.CONTINUE;
        }

        if (this.isCharging()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem.attack", true));
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem.walking", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "alphemcontroller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld iServerWorld, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData iLivingEntityData, @Nullable CompoundNBT compoundNBT) {
        iLivingEntityData = super.finalizeSpawn(iServerWorld, difficultyInstance, spawnReason, iLivingEntityData, compoundNBT);

        if (iServerWorld instanceof ServerWorld && BioplethoraConfig.COMMON.hellMode.get()) {
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(10 * BioplethoraConfig.COMMON.mobMeeleeDamageMultiplier.get());
            this.getAttribute(Attributes.ARMOR).setBaseValue(6.5 * BioplethoraConfig.COMMON.mobArmorMultiplier.get());
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40 * BioplethoraConfig.COMMON.mobHealthMultiplier.get());
            this.setHealth(40 * BioplethoraConfig.COMMON.mobHealthMultiplier.get());
        }

        if (BioplethoraConfig.COMMON.hellMode.get()) {
            if (Math.random() < 0.5) {
                if (!this.level.isClientSide())
                    if (Math.random() < 0.5) {
                        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                    }
                    this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
            } else {

                if (!this.level.isClientSide())
                    if (Math.random() < 0.5) {
                        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                    }
                    this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_AXE));
            }
        } else {
            if (Math.random() < 0.5) {
                if (!this.level.isClientSide())
                    this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
            } else {
                if (!this.level.isClientSide())
                    this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.WOODEN_AXE));
            }
        }

        return iLivingEntityData;
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);

        Entity sourceEnt = source.getEntity();

        if (sourceEnt instanceof ServerPlayerEntity) {
            Advancement adv = ((ServerPlayerEntity) sourceEnt).server.getAdvancements().getAdvancement(new ResourceLocation("bioplethora:alphem_kill"));

            assert adv != null;
            AdvancementProgress advProg = ((ServerPlayerEntity) sourceEnt).getAdvancements().getOrStartProgress(adv);

            if (!advProg.isDone()) {
                Iterator iterator = advProg.getRemainingCriteria().iterator();
                while (iterator.hasNext()) {
                    ((ServerPlayerEntity) sourceEnt).getAdvancements().award(adv, (String) iterator.next());
                }
            }
        }
    }

    @Override
    public net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.GENERIC_HURT;
    }

    @Override
    public net.minecraft.util.SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isCharging() {
        return this.entityData.get(DATA_IS_CHARGING);
    }

    public void setCharging(boolean charging) {
        this.entityData.set(DATA_IS_CHARGING, charging);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_CHARGING, false);
    }
}
