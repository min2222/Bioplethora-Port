package io.github.bioplethora.entity.projectile;

import io.github.bioplethora.registry.BioplethoraEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
* Credited author:
* AzureDoom [MCDoom mod]
*/

public class BellophiteClusterEntity extends DamagingProjectileEntity implements IAnimatable {

    public double xPower;
    public double yPower;
    public double zPower;
    private final AnimationFactory factory = new AnimationFactory(this);

    public BellophiteClusterEntity(EntityType<? extends DamagingProjectileEntity> type, World world) {
        super(type, world);
    }

    @OnlyIn(Dist.CLIENT)
    public BellophiteClusterEntity(World p_i1768_1_, double p_i1768_2_, double p_i1768_4_, double p_i1768_6_, double p_i1768_8_, double p_i1768_10_, double p_i1768_12_) {
        super(BioplethoraEntities.BELLOPHITE_CLUSTER.get(), p_i1768_2_, p_i1768_4_, p_i1768_6_, p_i1768_8_, p_i1768_10_, p_i1768_12_, p_i1768_1_);
    }

    public BellophiteClusterEntity(World p_i1769_1_, LivingEntity p_i1769_2_, double p_i1769_3_, double p_i1769_5_, double p_i1769_7_) {
        super(BioplethoraEntities.BELLOPHITE_CLUSTER.get(), p_i1769_2_, p_i1769_3_, p_i1769_5_, p_i1769_7_, p_i1769_1_);
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bellophite_cluster.main", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "bellophiteclustercontroller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        Entity entity = this.getOwner();
        if (result.getType() != RayTraceResult.Type.ENTITY || !((EntityRayTraceResult) result).getEntity().is(entity)) {
            if (!this.level.isClientSide) {
                this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 1.5F, Explosion.Mode.BREAK);
                this.remove();
            }
        }
        if(this.level instanceof ServerWorld) {
            List<Entity> nearEntities = this.level
                    .getEntitiesOfClass(Entity.class, new AxisAlignedBB(this.getX() - (5 / 2d), this.getY(), this.getZ() - (5 / 2d), this.getX() + (5 / 2d), this.getY() + (5 / 2d), this.getZ() + (5 / 2d)), null)
                    .stream().sorted(new Object() {
                        Comparator<Entity> compareDistOf(double dx, double dy, double dz) {
                            return Comparator.comparing((entCnd -> entCnd.distanceToSqr(dx, dy, dz)));
                        }
                    }.compareDistOf(this.getX(), this.getY(), this.getZ())).collect(Collectors.toList());
            for (Entity entityIterator : nearEntities) {
                if (entityIterator instanceof LivingEntity && entityIterator != this.getOwner()) {
                    ((LivingEntity) entityIterator).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60, 2));
                    ((LivingEntity) entityIterator).addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 60, 2));
                    ((LivingEntity) entityIterator).addEffect(new EffectInstance(Effects.WEAKNESS, 60, 1));
                }
            }
        }
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entityHitResult.getType() != RayTraceResult.Type.ENTITY || !((EntityRayTraceResult) entityHitResult).getEntity().is(entity)) {
            if (!this.level.isClientSide) {
                this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 1.5F, Explosion.Mode.BREAK);
                this.remove();
            }
        }
        if(this.level instanceof ServerWorld) {
            List<Entity> nearEntities = this.level
                    .getEntitiesOfClass(Entity.class, new AxisAlignedBB(this.getX() - (7 / 2d), this.getY() - (7 / 2d), this.getZ() - (7 / 2d), this.getX() + (7 / 2d), this.getY() + (7 / 2d), this.getZ() + (7 / 2d)), null)
                    .stream().sorted(new Object() {
                        Comparator<Entity> compareDistOf(double dx, double dy, double dz) {
                            return Comparator.comparing((entCnd -> entCnd.distanceToSqr(dx, dy, dz)));
                        }
                    }.compareDistOf(this.getX(), this.getY(), this.getZ())).collect(Collectors.toList());
            for (Entity entityIterator : nearEntities) {
                if (entityIterator instanceof LivingEntity && entityIterator != this.getOwner()) {
                    ((LivingEntity) entityIterator).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60, 2));
                    ((LivingEntity) entityIterator).addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 60, 2));
                    ((LivingEntity) entityIterator).addEffect(new EffectInstance(Effects.WEAKNESS, 60, 1));
                }
            }
        }
    }

    @Override
    public void tick() {
        if (this.level instanceof ServerWorld) {
            ((ServerWorld) this.level).sendParticles(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), (int) 3, 0.4, 0.4, 0.4, 0.1);
        }
        Entity entity = this.getOwner();
        if (this.level.isClientSide || (entity == null || !entity.removed) && this.level.hasChunkAt(this.blockPosition())) {
            super.tick();
            if (this.shouldBurn()) {
                this.setSecondsOnFire(1);
            }

            RayTraceResult raytraceresult = ProjectileHelper.getHitResult(this, this::canHitEntity);
            if (raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onHit(raytraceresult);
            }

            this.checkInsideBlocks();
            Vector3d vector3d = this.getDeltaMovement();
            double d0 = this.getX() + vector3d.x;
            double d1 = this.getY() + vector3d.y;
            double d2 = this.getZ() + vector3d.z;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getInertia();
            if (this.isInWater()) {
                for(int i = 0; i < 4; ++i) {
                    float f1 = 0.25F;
                    this.level.addParticle(ParticleTypes.BUBBLE, d0 - vector3d.x * 0.25D, d1 - vector3d.y * 0.25D, d2 - vector3d.z * 0.25D, vector3d.x, vector3d.y, vector3d.z);
                }

                f = 0.8F;
            }

            this.setDeltaMovement(vector3d.add(this.xPower, this.yPower, this.zPower).scale((double)f));
            this.level.addParticle(this.getTrailParticle(), d0, d1 + 0.5D, d2, 0.0D, 0.0D, 0.0D);
            this.setPos(d0, d1, d2);
        } else {
            this.remove();
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean shouldBurn() {
        return false;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }
}