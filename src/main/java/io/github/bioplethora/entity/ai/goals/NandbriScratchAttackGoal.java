package io.github.bioplethora.entity.ai.goals;

import io.github.bioplethora.entity.BPMonsterEntity;
import io.github.bioplethora.entity.ai.gecko.GeckoMeleeGoal;
import io.github.bioplethora.entity.creatures.NandbriEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class NandbriScratchAttackGoal extends GeckoMeleeGoal<NandbriEntity> {
    public NandbriEntity nandbri = entity;

    public NandbriScratchAttackGoal(NandbriEntity entity, double animationLength, double attackBegin, double attackEnd) {
        super(entity, animationLength, attackBegin, attackEnd);
    }

    public static boolean checkIfValid(NandbriScratchAttackGoal goal, NandbriEntity attacker, LivingEntity target) {
        if(target == null) return false;
        if(target.isAlive() && !target.isSpectator()){
            if (target instanceof Player && ((Player) target).isCreative()) {
                attacker.setScratching(false);
                return false;
            }

            if (attacker.attackPhase != 1) {
                attacker.setScratching(false);
                return false;
            }

            if (attacker.getSpitting()) {
                attacker.setScratching(false);
                return false;
            }

            double distance = goal.nandbri.distanceToSqr(target.getX(), target.getY(), target.getZ());
            if (distance <= NandbriScratchAttackGoal.getAttackReachSqr(attacker, target)) return true;
        }
        attacker.setScratching(false);
        return false;
    }

    protected static double getAttackReachSqr(BPMonsterEntity attacker, LivingEntity target) {
        return attacker.getBbWidth() * 2F * attacker.getBbWidth() * 2F + target.getBbWidth();
    }

    @Override
    public boolean canUse() {
        if(Math.random() < 0.1) return false;
        return NandbriScratchAttackGoal.checkIfValid(this, nandbri, this.nandbri.getTarget());
    }

    @Override
    public boolean canContinueToUse() {
        if(Math.random() < 0.1) return true;
        return NandbriScratchAttackGoal.checkIfValid(this, nandbri, this.nandbri.getTarget());
    }

    @Override
    public void start() {
        this.nandbri.setScratching(true);
        this.nandbri.setAggressive(true);
        this.animationProgress = 0;
    }

    @Override
    public void stop() {
        LivingEntity target = this.nandbri.getTarget();
        if(!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
            this.nandbri.setTarget(null);
        }
        this.nandbri.setScratching(false);
        this.nandbri.setAggressive(false);

        if(this.hasHit) {
            switchPhase();
        }

        this.hasHit = false;
        this.animationProgress = 0;
    }

    public void switchPhase() {
        if(this.nandbri.attackPhase == 1) {
            this.nandbri.attackPhase = 0;
        }
    }

    @Override
    public void tick() {
        Level world = nandbri.level;
        this.baseTick();
        LivingEntity target = this.nandbri.getTarget();
        if (target != null) {
            this.nandbri.lookAt(target, 30.0F, 30.0F);
            double x = target.getX(), y = target.getY(), z = target.getZ();

            if (this.attackPredicate.apply(this.animationProgress, this.animationLength) && !this.hasHit) {
                target.hurt(DamageSource.mobAttack(this.nandbri), 9.0F);
                target.knockback(2, 2, 2);
                world.playSound(null, this.nandbri, SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1, 1);
                for (Entity entityIterator : world.getEntitiesOfClass(Entity.class, new AABB(x - (3 / 2d), y, z - (3 / 2d), x + (3 / 2d), y + (3 / 2d), z + (3 / 2d)))) {
                    if(entityIterator != nandbri && entityIterator != target) {
                        entityIterator.hurt(DamageSource.mobAttack(nandbri), 4);
                    }
                }
                this.hasHit = true;
            }

            if (this.animationProgress > this.animationLength) {
                this.animationProgress = 0;
                this.hasHit = false;

                switchPhase();
            }
        }
    }
}
