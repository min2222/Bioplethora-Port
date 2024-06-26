package io.github.bioplethora.entity.creatures;

import javax.annotation.Nullable;

import io.github.bioplethora.api.mixin.IPlayerEntityMixin;
import io.github.bioplethora.api.world.BlockUtils;
import io.github.bioplethora.api.world.EffectUtils;
import io.github.bioplethora.api.world.EntityUtils;
import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.entity.BPMonsterEntity;
import io.github.bioplethora.entity.IBioClassification;
import io.github.bioplethora.entity.IMobCappedEntity;
import io.github.bioplethora.entity.ai.gecko.GeckoMoveToTargetGoal;
import io.github.bioplethora.entity.ai.goals.AlphemKingJumpGoal;
import io.github.bioplethora.entity.ai.goals.AlphemKingMeeleeGoal;
import io.github.bioplethora.entity.ai.goals.AlphemKingPursuitGoal;
import io.github.bioplethora.entity.ai.goals.AlphemKingRangedAttackGoal;
import io.github.bioplethora.entity.ai.goals.AlphemKingRoarGoal;
import io.github.bioplethora.entity.ai.goals.AlphemKingSecondMeeleeGoal;
import io.github.bioplethora.entity.ai.goals.AlphemKingSmashingGoal;
import io.github.bioplethora.entity.others.AlphanumShardEntity;
import io.github.bioplethora.entity.others.BPEffectEntity;
import io.github.bioplethora.entity.others.part.BPPartEntity;
import io.github.bioplethora.enums.BPEffectTypes;
import io.github.bioplethora.enums.BPEntityClasses;
import io.github.bioplethora.registry.BPAttributes;
import io.github.bioplethora.registry.BPEntities;
import io.github.bioplethora.registry.BPParticles;
import io.github.bioplethora.registry.BPSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
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

public class AlphemKingEntity extends BPMonsterEntity implements IAnimatable, IBioClassification, IMobCappedEntity {

    protected static final EntityDataAccessor<Boolean> WAKING = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> ATTACKING2 = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> SMASHING = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> ROARING = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    // TODO: 16/02/2022 Add an animation for shooting/charging. Add a new and stronger projectile to replace the Windblaze projectile.
    protected static final EntityDataAccessor<Boolean> CHARGING = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> JUMPING = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    // TODO: 16/02/2022 Add a ramming animation and a ramming AI goal.
    protected static final EntityDataAccessor<Boolean> RAMMING = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> BARRIERED = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> BERSERKED = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> PURSUIT = SynchedEntityData.defineId(AlphemKingEntity.class, EntityDataSerializers.BOOLEAN);

    public BossEvent.BossBarColor bossColor = BossEvent.BossBarColor.GREEN;
    private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), bossColor, BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(true).setPlayBossMusic(true));
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public boolean explodedOnDeath = false;
    public double healthRegenTimer = 0;
    public int summonShardTimer;
    public int attackPhase;
    public int wakeTimer;
    public int barrierTimer;
    public float vecOfTarget;
    public boolean inWall;
    public int dyingExpTimer;

    // Extras - For modded mobs that picks up mobs.
    public int pickupTimer;

    /*
    public final KingPartEntity[] subEntities;
    public final KingPartEntity core;
    */

    public AlphemKingEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        this.noCulling = true;
        this.xpReward = 20;
        this.lookControl = new AlphemKingLookControl(this);
        /*
        core = new KingPartEntity<>(this, "core", 1.6f, 1.6f);
        subEntities = new KingPartEntity[]{core};
        */
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.ARMOR, 14.5 * BPConfig.COMMON.mobArmorMultiplier.get())
                .add(Attributes.ATTACK_SPEED, 0.001)
                .add(Attributes.ATTACK_DAMAGE, 30 * BPConfig.COMMON.mobMeeleeDamageMultiplier.get())
                .add(Attributes.ATTACK_KNOCKBACK, 7.0D)
                .add(Attributes.MAX_HEALTH, 395 * BPConfig.COMMON.mobHealthMultiplier.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.5)
                .add(Attributes.MOVEMENT_SPEED, 0.25F * BPConfig.COMMON.mobMovementSpeedMultiplier.get())
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(BPAttributes.TRUE_DEFENSE.get(), 2D);
    }

    @Override
    public BPEntityClasses getBioplethoraClass() {
        return BPEntityClasses.ELDERIA;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 24.0F));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0F));
        this.goalSelector.addGoal(1, new AlphemKingRoarGoal(this));
        this.goalSelector.addGoal(2, new GeckoMoveToTargetGoal<AlphemKingEntity>(this, 1.15F, 8) {
            @Override
            public boolean canUse() {
                if (RANDOM.nextInt(this.checkRate) == 0) return false;
                if (entity.isBusy()) {
                    return this.isExecutable(this, this.entity, this.entity.getTarget());
                } else {
                    return false;
                }
            }
            @Override
            public boolean canContinueToUse() {
                return this.canUse();
            }
            @Override
            public void moveToTarget() {
                Vec3 vector3d = DefaultRandomPos.getPosAway(entity, 16, 7, entity.getTarget().position());
                Path pathT = null;
                if (vector3d != null) {
                    pathT = entity.getNavigation().createPath(vector3d.x, vector3d.y, vector3d.z, 0);
                }
                if (this.entity.isCharging() && pathT != null) {
                    entity.getNavigation().moveTo(pathT, this.speedMultiplier);
                } else {
                    super.moveToTarget();
                }
            }
        });
        // 1st phase meelee
        this.goalSelector.addGoal(2, new AlphemKingMeeleeGoal(this, 80, 0.5, 0.6));
        // 2nd phase meelee
        this.goalSelector.addGoal(2, new AlphemKingSecondMeeleeGoal(this, 80, 0.3, 0.4));
        // 3rd phase meelee
        this.goalSelector.addGoal(2, new AlphemKingSmashingGoal(this, 120, 0.8, 0.9));
        this.goalSelector.addGoal(3, new AlphemKingPursuitGoal(this));
        this.goalSelector.addGoal(4, new AlphemKingRangedAttackGoal(this));
        this.goalSelector.addGoal(5, new AlphemKingJumpGoal(this));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FloatGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FrostbiteGolemEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AltyrusEntity.class, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<AlphemKingEntity> controller = new AnimationController<>(this, "alphem_king_controller", 0, this::predicate);
        data.addAnimationController(controller);
    }

    public boolean isBusy() {
        return !this.getAttacking() && !this.getAttacking() && !this.getAttacking2() && !this.getSmashing() && !this.isPursuit();
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (this.isDeadOrDying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.death", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.getWaking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.waking", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.getRoaring()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.roar", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.isPursuit()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.pursuit", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.getSmashing()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.attack_2", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.getAttacking2()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.attack_1", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.getAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.attack_0", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (event.isMoving() && this.isBerserked()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.walk_berserk", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (event.isMoving() && !this.isBerserked()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.walk", EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alphem_king.idle", EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (worldIn instanceof ServerLevel && BPConfig.COMMON.hellMode.get()) {
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(35 * BPConfig.COMMON.mobMeeleeDamageMultiplier.get());
            this.getAttribute(Attributes.ARMOR).setBaseValue(21.5 * BPConfig.COMMON.mobArmorMultiplier.get());
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(550 * BPConfig.COMMON.mobHealthMultiplier.get());
            this.setHealth(380 * BPConfig.COMMON.mobHealthMultiplier.get());
        }
        if (reason == MobSpawnType.MOB_SUMMONED) {
            this.setWaking(true);
            this.setNoAi(true);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void push(double pX, double pY, double pZ) {
        if (this.isBusy()) {
            super.push(pX, pY, pZ);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWaking() || this.isNoAi()) {
            ++wakeTimer;
            if (wakeTimer == 30) {
                this.playSound(SoundEvents.ANVIL_LAND, 1.0F, 1.0F);
            }
            if (wakeTimer == 50) {
                this.playSound(SoundEvents.ANVIL_LAND, 1.0F, 1.0F);
            }
            if (wakeTimer == 100) {
                this.playSound(SoundEvents.ELDER_GUARDIAN_CURSE, 1.0F, 0.5F);
                if (level instanceof ServerLevel) {
                    ((ServerLevel) level).sendParticles(BPParticles.KINGS_ROAR.get(), getX(), getY() + 0.5D, getZ(), 1, 0, 0, 0, 0);
                }
            }
            if (wakeTimer >= 140) {
                this.setNoAi(false);
                this.setWaking(false);
            }
        }

        int areaint = 32;
        AABB aabb = new AABB(this.getX() - areaint, this.getY() - areaint, this.getZ() - areaint, this.getX() + areaint, this.getY() + areaint, this.getZ() + areaint);
        for (AlphemEntity alphem : level.getEntitiesOfClass(AlphemEntity.class, aabb)) {
            if (!(alphem.getOwner() instanceof AlphemKingEntity)) {
                alphem.kill();
            }
        }
        for (CavernFleignarEntity cavernFleignar : level.getEntitiesOfClass(CavernFleignarEntity.class, aabb)) {
            cavernFleignar.kill();
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        float moveVector = (float) Math.toRadians(this.vecOfTarget + 90 + this.getRandom().nextFloat() * 150 - 75);
        Vec3 getVector = this.getDeltaMovement().add(1.75F * Math.cos(moveVector), 0, 1.75F * Math.sin(moveVector));
        if (pSource != DamageSource.OUT_OF_WORLD) {
            if (this.getWaking()) {
                this.playSound(SoundEvents.ANVIL_LAND, 1.0F, 1.5F);
                return false;
            }
            if (this.isBarriered()) {
                this.playSound(SoundEvents.GLASS_BREAK, 1.5F, 1.0F);
                this.setBarriered(false);
                return false;
            }
        }
        if (!this.getAttacking() && !this.getAttacking2() && !this.getSmashing()) {
            this.setDeltaMovement(getVector.x(), 0.5, getVector.z());
        }
        return super.hurt(pSource, pAmount);
    }

    public void aiStep() {
        super.aiStep();

        // Extras - For modded mobs that picks up mobs.
        float moveVector = (float) Math.toRadians(this.vecOfTarget + 90 + this.getRandom().nextFloat() * 150 - 75);
        Vec3 getVector = this.getDeltaMovement().add(2.5F * Math.cos(moveVector), 0, 2.5F * Math.sin(moveVector));
        if (this.isPassenger()) {
            ++pickupTimer;
            if (pickupTimer == 40) {
                this.heal(10.0F);
                this.playSound(SoundEvents.ANVIL_LAND, 1.5F, 0.75F);
                level.explode(this, getX(), getY(), getZ(), 3.0F, Explosion.BlockInteraction.NONE);
                this.stopRiding();
                this.setDeltaMovement(getVector.x(), 1.35, getVector.z());
                pickupTimer = 0;
            }
        } else {
            pickupTimer = 0;
        }

        Level world = this.level;

        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());

        if (!this.isBarriered()) {
            ++barrierTimer;
            if (barrierTimer == 150) {
                barrierTimer = 0;
                this.setBarriered(true);
            }

            if (getTarget() != null) {
                vecOfTarget = (float) (Math.atan2(getTarget().getZ() - getZ(), getTarget().getX() - getX()) * (180 / Math.PI) + 90);
            }
        }

        if (this.getTarget() != null) {
            summonShardTimer++;
            if (this.summonShardTimer == 40) {
                summonShard(BPConfig.IN_HELLMODE ? 5 + getRandom().nextInt(6) : 4 + getRandom().nextInt(4));
                this.summonShardTimer = 0;
            }
        }

        this.setBerserked(this.getHealth() <= this.getMaxHealth() / 2);

        if (this.isBerserked()) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
            world.addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);

            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * BPConfig.COMMON.mobMovementSpeedMultiplier.get());
            if (!BPConfig.IN_HELLMODE) {
                this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(37.0F * BPConfig.COMMON.mobMeeleeDamageMultiplier.get());
            } else {
                this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(45.0F * BPConfig.COMMON.mobMeeleeDamageMultiplier.get());
            }

            if (!(this.getHealth() <= 5)) {
                ++healthRegenTimer;
                if (healthRegenTimer == 20) {
                    this.heal( 2 + this.getRandom().nextInt(2));
                    healthRegenTimer = 0;
                }
            }

        } else {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.20F * BPConfig.COMMON.mobMovementSpeedMultiplier.get());
            if (!BPConfig.IN_HELLMODE) {
                this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(30.0F * BPConfig.COMMON.mobMeeleeDamageMultiplier.get());
            } else {
                this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(35.0F * BPConfig.COMMON.mobMeeleeDamageMultiplier.get());
            }

            if (!(this.getHealth() <= 5)) {
                ++healthRegenTimer;
                if (healthRegenTimer == 60) {
                    this.heal(2F);
                    healthRegenTimer = 0;
                }
            }
        }

        if (this.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
            this.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        if (this.hasEffect(MobEffects.WEAKNESS)) {
            this.removeEffect(MobEffects.WEAKNESS);
        }
        if (this.hasEffect(MobEffects.POISON)) {
            this.removeEffect(MobEffects.POISON);
        }
        if (this.hasEffect(MobEffects.WITHER)) {
            this.removeEffect(MobEffects.WITHER);
        }

        if (!this.level.isClientSide) {
            this.inWall = this.checkWalls(this.getBoundingBox());
        }
    }

    private boolean checkWalls(AABB pArea) {
        if (this.isBerserked()) {
            int i = Mth.floor(pArea.minX);
            int j = Mth.floor(pArea.minY);
            int k = Mth.floor(pArea.minZ);
            int l = Mth.floor(pArea.maxX);
            int i1 = Mth.floor(pArea.maxY);
            int j1 = Mth.floor(pArea.maxZ);
            boolean flag = false;
            boolean flag1 = false;

            for (int k1 = i; k1 <= l; ++k1) {
                for (int l1 = j; l1 <= i1; ++l1) {
                    for (int i2 = k; i2 <= j1; ++i2) {
                        BlockPos blockpos = new BlockPos(k1, l1, i2);
                        BlockState blockstate = this.level.getBlockState(blockpos);
                        Block block = blockstate.getBlock();
                        if (!blockstate.isAir() && blockstate.getMaterial() != Material.FIRE) {
                            if (net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.level, blockpos, this) && !blockstate.is(BlockTags.DRAGON_IMMUNE)) {
                                flag1 = this.level.removeBlock(blockpos, false) || flag1;
                            } else {
                                flag = true;
                            }
                        }
                    }
                }
            }

            if (flag1) {
                BlockPos blockpos1 = new BlockPos(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(i1 - j + 1), k + this.random.nextInt(j1 - k + 1));
                this.playSound(SoundEvents.WITHER_BREAK_BLOCK, 1.0F, 1.0F);
                this.level.levelEvent(2008, blockpos1, 0);
            }

            return flag;
        } else {
            return false;
        }
    }

    public void summonShard(int amount) {
        for (int i = 0; i < amount; i++) {

            double xPos = getRandom().nextBoolean() ? getX() + getRandom().nextInt(24) : getX() - getRandom().nextInt(24);
            double zPos = getRandom().nextBoolean() ? getZ() + getRandom().nextInt(24) : getZ() - getRandom().nextInt(24);

            AlphanumShardEntity shard = BPEntities.ALPHANUM_SHARD.get().create(this.level);
            if (this.getTarget() != null) {
                shard.setTarget(this.getTarget());
            }
            shard.setOwner(this);

            shard.setPos(xPos, getGroundPos(level, (int) xPos, (int) zPos).getY(), zPos);

            this.level.addFreshEntity(shard);
        }
    }

    public static BlockPos getGroundPos(BlockGetter pLevel, int pX, int pZ) {
    	
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos(pX, pLevel.getMaxBuildHeight(), pZ);
        do {
            blockpos$mutable.move(Direction.DOWN);
        } while(pLevel.getBlockState(blockpos$mutable).isAir() && blockpos$mutable.getY() > pLevel.getMinBuildHeight());

        BlockPos blockpos = blockpos$mutable.below();
        if (pLevel.getBlockState(blockpos).isPathfindable(pLevel, blockpos, PathComputationType.LAND)) {
            return blockpos;
        }

        return blockpos$mutable.immutable().above();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
        ((IPlayerEntityMixin) player).setAlphanumCurse(true);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
        ((IPlayerEntityMixin) player).setAlphanumCurse(false);
    }

    public float hurtScalable(LivingEntity ent, float val, float hmVal) {
        return (float) ((BPConfig.IN_HELLMODE ? hmVal : val) + (Math.sqrt(ent.getMaxHealth() * 1.25)));
    }

    public void createAttackParticleEffect(BlockPos pos, int flag) {
        for (int i = 0; i < 180; i++) {
            if (flag == 0) {
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX(), pos.getY() + 0.5, pos.getZ(), Math.sin(i) * 0.12, 0.02, Math.cos(i) * 0.12);
                level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, pos.getX(), pos.getY() + 0.75, pos.getZ(), Math.sin(i) * 0.25, 0.5, Math.cos(i) * 0.25);
            }
            if (flag == 1) {
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX(), pos.getY() + 0.5, pos.getZ(), Math.sin(i) / 4, 0.02, Math.cos(i) / 4);
            }
        }
        if (flag == 1) {
            if (!level.isClientSide()) {
                EffectUtils.createParticleBall((ServerLevel) level, ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX(), pos.getY() + 0.5, pos.getZ(), 0.1, 3);
            }
        }
    }

    public void phaseAttack(Level world) {
        double d0 = -Mth.sin(this.yBodyRot * ((float)Math.PI / 180F)) * 4;
        double d1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F)) * 4;
        BlockPos areaPos = new BlockPos(getX() + d0, getY(), getZ() + d1);

        for (LivingEntity areaEnt : world.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(5, 0.5, 5).move(areaPos))) {
            if (areaEnt != this) {
                areaEnt.hurt(DamageSource.mobAttack(this), hurtScalable(areaEnt, 9, 11));
                areaEnt.hurt(DamageSource.explosion(this), hurtScalable(areaEnt, 9, 11));
                this.knockbackNoRes(areaEnt, 1.0F, Mth.sin(this.yRot * ((float) Math.PI / 180F)), -Mth.cos(this.yRot * ((float) Math.PI / 180F)));
                areaEnt.setDeltaMovement(this.getDeltaMovement().add(0, 1.5 - areaEnt.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE), 0));
                areaEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
            }
        }
        this.playSound(SoundEvents.WITHER_BREAK_BLOCK, 1.0F, 1.0F);
        this.createAttackParticleEffect(areaPos, 0);
        BlockUtils.knockUpRandomNearbyBlocks(world, 0.3D, areaPos.below(), 3, 1, 3, false, true);
        if (world instanceof ServerLevel) {
            ((ServerLevel) world).sendParticles(ParticleTypes.POOF,areaPos.getX(), areaPos.getY(), areaPos.getZ(), 25, 0.45, 0.45, 0.45, 0.01);
        }
        EntityUtils.shakeNearbyPlayersScreen(this, 32, 5);
    }

    public void phaseAttack2(Level world) {
        double d0 = -Mth.sin(this.yBodyRot * ((float)Math.PI / 180F)) * 4;
        double d1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F)) * 4;
        BlockPos areaPos = new BlockPos(getX() + d0, getY(), getZ() + d1);
        for (LivingEntity areaEnt : world.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(10, 0.5, 10).move(areaPos))) {
            if (areaEnt != this) {
                areaEnt.hurt(DamageSource.mobAttack(this), hurtScalable(areaEnt, 7, 10));
                areaEnt.hurt(DamageSource.explosion(this), hurtScalable(areaEnt, 7, 10));
                this.knockbackNoRes(areaEnt, 2.5F, Mth.sin(this.yRot * ((float) Math.PI / 180F)), -Mth.cos(this.yRot * ((float) Math.PI / 180F)));
                areaEnt.setDeltaMovement(this.getDeltaMovement().add(0, 0.75, 0));
                areaEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
            }
        }
        if (world instanceof ServerLevel) {
            ((ServerLevel) world).sendParticles(ParticleTypes.CLOUD, areaPos.getX(), areaPos.getY(), areaPos.getZ(), 55, 0.75, 0.75, 0.75, 0.01);
        }
    }

    public void phaseSmashing(Level world) {

        float f1 = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
        double d0 = -Mth.sin(this.yBodyRot * ((float)Math.PI / 180F)) * 4;
        double d1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F)) * 4;
        BlockPos areaPos = new BlockPos(getX() + d0, getY(), getZ() + d1);

        for (LivingEntity areaEnt : world.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(15, 0.5, 15).move(areaPos))) {
            if (areaEnt != this) {
                areaEnt.hurt(DamageSource.mobAttack(this), hurtScalable(areaEnt, 13, 17));
                this.knockbackNoRes(areaEnt, f1 * 0.5F, Mth.sin(this.yRot * ((float) Math.PI / 180F)), -Mth.cos(this.yRot * ((float) Math.PI / 180F)));
                areaEnt.setDeltaMovement(this.getDeltaMovement().add(0, 1.5, 0));
                areaEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
            }
        }

        world.explode(this, areaPos.getX(), areaPos.getY(), areaPos.getZ(), 3.0F, Explosion.BlockInteraction.NONE);
        this.playSound(SoundEvents.WITHER_BREAK_BLOCK, 1.0F, 1.0F);
        this.createAttackParticleEffect(areaPos, 1);
        BlockUtils.knockUpRandomNearbyBlocks(world, 0.5D, areaPos.below(), 6, 2, 6, false, true);

        if (world instanceof ServerLevel) {
            ((ServerLevel) world).sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, areaPos.getX(), areaPos.getY(), areaPos.getZ(), 75, 0.4, 1.5, 0.4, 0.001);
        }

        EntityUtils.shakeNearbyPlayersScreen(this, 32, 10);
    }

    public void knockbackNoRes(LivingEntity target, float pStrength, double pRatioX, double pRatioZ) {
        net.minecraftforge.event.entity.living.LivingKnockBackEvent event = net.minecraftforge.common.ForgeHooks.onLivingKnockBack(this, pStrength, pRatioX, pRatioZ);
        if (event.isCanceled()) return;
        pStrength = event.getStrength();
        pRatioX = event.getRatioX();
        pRatioZ = event.getRatioZ();
        if (!(pStrength <= 0.0F)) {
            target.hasImpulse = true;
            Vec3 vector3d = target.getDeltaMovement();
            Vec3 vector3d1 = (new Vec3(pRatioX, 0.0D, pRatioZ)).normalize().scale(pStrength);
            target.setDeltaMovement(vector3d.x / 2.0D - vector3d1.x, target.isOnGround() ? Math.min(0.4D, vector3d.y / 2.0D + (double) pStrength) : vector3d.y, vector3d.z / 2.0D - vector3d1.z);
            if (target instanceof Player) target.hurtMarked = true;
        }
    }

    @Override
    protected BodyRotationControl createBodyControl() {
        return new AlphemKingBodyRotationControl(this);
    }

    @Override
    protected void tickDeath() {

        ++this.deathTime;

        int areaint = 256;
        AABB aabb = new AABB(
                this.getX() - areaint, this.getY() - areaint, this.getZ() - areaint,
                this.getX() + areaint, this.getY() + areaint, this.getZ() + areaint
        );

        if (this.dyingExpTimer++ == 6) {
            this.dropExperience(Mth.floor((float)12000 * 0.08F));
            dyingExpTimer = 0;
        }

        if (this.deathTime == 50) {
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ANVIL_PLACE, SoundSource.HOSTILE, 1.0F, 1.0F);
        }

        if (this.deathTime == 100) {

            if (!explodedOnDeath) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITHER_BREAK_BLOCK, SoundSource.HOSTILE, 1.0F, 1.0F);
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 1.0F, 1.0F);

                explodedOnDeath = true;
            }
            for (BPEffectEntity effect : level.getEntitiesOfClass(BPEffectEntity.class, aabb)) {
                if (effect.getEffectType() == BPEffectTypes.ALPHANUM_FIRE && effect.getOwner() == this) {
                    effect.discard();
                }
            }
            this.discard();

            for (int i = 0; i < 100; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;

                this.level.addParticle(ParticleTypes.EXPLOSION, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
                this.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
                this.level.addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
            }
        }
    }

    private void dropExperience(int pAmount) {
        while(pAmount > 0) {
            int i = ExperienceOrb.getExperienceValue(pAmount);
            pAmount -= i;
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY(), this.getZ(), i));
        }
    }

    @Override
    public void swing(InteractionHand pHand, boolean pUpdateSelf) {
        super.swing(pHand, pUpdateSelf);
        Level world = level;

        if (this.attackPhase == 0) {
            this.phaseAttack(world);
        }

        if (this.attackPhase == 1) {
            this.phaseAttack2(world);
        }

        if (this.attackPhase == 2) {
            this.phaseSmashing( world);
        }
    }

    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof LivingEntity) {
            float healValue = isBerserked() ? hurtScalable(((LivingEntity) entity), 17, 20) : hurtScalable(((LivingEntity) entity), 10, 14);
            this.heal(healValue);
        }
        return super.doHurtTarget(entity);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BPSoundEvents.FROSTBITE_GOLEM_IDLE.get();
    }

    @Override
    public SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 0.15f, 1);
    }

    public SoundEvent getRoarSound() {
        return BPSoundEvents.ALPHEM_KING_ROAR.get();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    public void checkDespawn() {
    }

    @Override
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_, DamageSource pSource) {
        return false;
    }

    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WAKING, true);
        this.entityData.define(ATTACKING2, false);
        this.entityData.define(SMASHING, false);
        this.entityData.define(ROARING, false);
        this.entityData.define(CHARGING, false);
        this.entityData.define(JUMPING, false);
        this.entityData.define(RAMMING, false);
        this.entityData.define(BARRIERED, false);
        this.entityData.define(BERSERKED, false);
        this.entityData.define(PURSUIT, false);
    }

    public boolean getWaking() {
        return this.entityData.get(WAKING);
    }

    public void setWaking(boolean sleeping) {
        this.entityData.set(WAKING, sleeping);
    }

    public boolean getAttacking2() {
        return this.entityData.get(ATTACKING2);
    }

    public void setAttacking2(boolean attacking2) {
        this.entityData.set(ATTACKING2, attacking2);
    }

    public boolean getSmashing() {
        return this.entityData.get(SMASHING);
    }

    public void setSmashing(boolean smashing) {
        this.entityData.set(SMASHING, smashing);
    }

    public boolean getRoaring() {
        return this.entityData.get(ROARING);
    }

    public void setRoaring(boolean smashing) {
        this.entityData.set(ROARING, smashing);
    }

    public boolean isCharging() {
        return this.entityData.get(CHARGING);
    }

    public void setCharging(boolean charging) {
        this.entityData.set(CHARGING, charging);
    }

    public boolean isJumping() {
        return this.entityData.get(JUMPING);
    }

    public void setJumping(boolean jumping) {
        this.entityData.set(JUMPING, jumping);
    }

    public boolean isRamming() {
        return this.entityData.get(RAMMING);
    }

    public void setRamming(boolean ramming) {
        this.entityData.set(RAMMING, ramming);
    }

    public boolean isBarriered() {
        return this.entityData.get(BARRIERED);
    }

    public void setBarriered(boolean barriered) {
        this.entityData.set(BARRIERED, barriered);
    }

    public boolean isBerserked() {
        return this.entityData.get(BERSERKED);
    }

    public void setBerserked(boolean berserked) {
        this.entityData.set(BERSERKED, berserked);
    }

    public boolean isPursuit() {
        return this.entityData.get(PURSUIT);
    }

    public void setPursuit(boolean pursuit) {
        this.entityData.set(PURSUIT, pursuit);
    }
    @Override
    public int getMaxDamageCap() {
        return BPConfig.COMMON.alphemKingMobCap.get();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("hasNotAwaken", this.getWaking());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setWaking(pCompound.getBoolean("hasNotAwaken"));
    }

    public static class AlphemKingLookControl extends LookControl {
        private final AlphemKingEntity king;

        public AlphemKingLookControl(AlphemKingEntity p_i1613_1_) {
            super(p_i1613_1_);
            this.king = p_i1613_1_;
        }

        @Override
        public void tick() {
            if (this.king.isBusy()) {
                super.tick();
            }
        }
    }

    public static class AlphemKingBodyRotationControl extends BodyRotationControl {
        private final AlphemKingEntity king;

        public AlphemKingBodyRotationControl(AlphemKingEntity p_i50334_1_) {
            super(p_i50334_1_);
            this.king = p_i50334_1_;
        }

        @Override
        public void clientTick() {
            if (this.king.isBusy()) {
                super.clientTick();
            }
        }
    }

    public static class KingPartEntity<T extends Mob> extends BPPartEntity<T> {

        public KingPartEntity(T parent, String name, float width, float height) {
            super(parent, name, width, height);
        }

        @Override
        public boolean hurt(DamageSource pSource, float pAmount) {
            return super.hurt(pSource, pAmount * 2.0F);
        }
    }
}