package io.github.bioplethora.entity.creatures;

import javax.annotation.Nullable;

import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.entity.FloatingMonsterEntity;
import io.github.bioplethora.entity.IBioClassification;
import io.github.bioplethora.enums.BPEntityClasses;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class EurydnEntity extends FloatingMonsterEntity implements IAnimatable, FlyingAnimal, IBioClassification {

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public Variant variant;
    public int descendTimer;

    public EurydnEntity(EntityType<? extends Monster> type, Level worldIn, Variant variant) {
        super(type, worldIn);
        this.variant = variant;
        this.noPhysics = true;
        this.noCulling = true;
        this.xpReward = 4;
        this.moveControl = new MoveHelperController(this);
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.ARMOR, (BPConfig.IN_HELLMODE ? 6 : 4) * BPConfig.COMMON.mobArmorMultiplier.get())
                .add(Attributes.ATTACK_SPEED, 0.1)
                .add(Attributes.ATTACK_KNOCKBACK, 0)
                .add(Attributes.ATTACK_DAMAGE, 1)
                .add(Attributes.MAX_HEALTH, (BPConfig.IN_HELLMODE ? 28 : 24) * BPConfig.COMMON.mobHealthMultiplier.get())
                .add(Attributes.MOVEMENT_SPEED, (BPConfig.IN_HELLMODE ? 0.3 : 0.25) * BPConfig.COMMON.mobMovementSpeedMultiplier.get())
                .add(Attributes.FLYING_SPEED, (BPConfig.IN_HELLMODE ? 0.45 : 0.25) * BPConfig.COMMON.mobMovementSpeedMultiplier.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 32D);
    }

    @Override
    public BPEntityClasses getBioplethoraClass() {
        return BPEntityClasses.DANGERUM;
    }

    @Override
    public boolean hurt(DamageSource source, float v) {
        Entity at = source.getEntity();

        if (at instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) at;

            switch (variant) {
                case SOUL:
                    attacker.setSecondsOnFire(3);
                    attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60));
                case FIERY:
                    attacker.setSecondsOnFire(5);
            }
        }

        return super.hurt(source, v);
    }

    @Override
    public void move(MoverType pType, Vec3 pPos) {
        super.move(pType, pPos);
        this.checkInsideBlocks();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PHANTOM_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PHANTOM_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.PHANTOM_HURT;
    }

    @Override
	public float getVoicePitch() {
        return 1.5F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.2));
        this.goalSelector.addGoal(4, new MoveRandomGoal());
        this.goalSelector.addGoal(5, new FloatGoal(this));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        double dirX = Math.sin(Math.toRadians(this.yRot + 180));
        double dirZ = Math.cos(Math.toRadians(this.yRot));

        descendTimer++;
        if (descendTimer > 400) {
            this.setDeltaMovement(dirX * 0.65, this.getY() < 70 ? 0.15 : -0.25, dirZ * 0.65);
            this.level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), 0.1D, 0.1D, 0.1D);
        }
        if (descendTimer >= 460) {
            this.descendTimer = 0;
        }

        if (this.level.isClientSide) {
            float f = Mth.cos((float)(this.getId() * 3 + this.tickCount) * 0.13F + (float)Math.PI);
            float f1 = Mth.cos((float)(this.getId() * 3 + this.tickCount + 1) * 0.13F + (float)Math.PI);
            if (f > 0.0F && f1 <= 0.0F) {
                this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.PHANTOM_FLAP, this.getSoundSource(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
            }

            float f2 = Mth.cos(this.yRot * ((float)Math.PI / 180F)) * (1.3F + 0.21F);
            float f3 = Mth.sin(this.yRot * ((float)Math.PI / 180F)) * (1.3F + 0.21F);
            float f4 = (0.3F + f * 0.45F) * ((float)0.2F + 1.0F);

            if (this.variant == Variant.SOUL) {
                this.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getX() + (double) f2, this.getY() + (double) f4, this.getZ() + (double) f3, 0.0D, 0.0D, 0.0D);
                this.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getX() - (double) f2, this.getY() + (double) f4, this.getZ() - (double) f3, 0.0D, 0.0D, 0.0D);
            }
            if (this.variant == Variant.FIERY) {
                this.level.addParticle(ParticleTypes.FLAME, this.getX() + (double) f2, this.getY() + (double) f4, this.getZ() + (double) f3, 0.0D, 0.0D, 0.0D);
                this.level.addParticle(ParticleTypes.FLAME, this.getX() - (double) f2, this.getY() + (double) f4, this.getZ() - (double) f3, 0.0D, 0.0D, 0.0D);

            }
        }
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public int getMaxSpawnClusterSize() {
        return 3;
    }

    public enum Variant {
        SOUL,
        FIERY
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.eurydn.idle", EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "grylynen_controller", 0, this::predicate));
    }

	@Override
	public boolean isFlying() {
		return false;
	}
}
