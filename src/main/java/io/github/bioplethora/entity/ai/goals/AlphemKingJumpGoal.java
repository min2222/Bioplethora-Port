package io.github.bioplethora.entity.ai.goals;

import io.github.bioplethora.entity.creatures.AlphemKingEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AlphemKingJumpGoal extends Goal {

    private final AlphemKingEntity king;
    public int jumpTime;

    public AlphemKingJumpGoal(AlphemKingEntity kingEntity) {
        this.king = kingEntity;
    }

    public boolean canUse() {
        return this.king.getTarget() != null && !this.king.isPursuit() && !this.king.isPursuit() && !this.king.getAttacking() && !this.king.getAttacking2() && !this.king.getSmashing();
    }

    public void start() {
        this.jumpTime = 0;
    }

    public void stop() {
        this.king.setJumping(false);
    }

    public boolean canContinueToUse() {
        return this.king.getTarget() != null;
    }

    public void tick() {
        float moveVector = (float) Math.toRadians(this.king.vecOfTarget + 90 + this.king.getRandom().nextFloat() * 150 - 75);
        Vec3 getVector = this.king.getDeltaMovement().add(2.0F * Math.cos(moveVector), 0, 2.0F * Math.sin(moveVector));

        LivingEntity target = this.king.getTarget();

        if (target != null && target.distanceToSqr(this.king) < 4096.0D && this.king.hasLineOfSight(target)) {

            ++this.jumpTime;

            Level world = this.king.level;

            if (this.jumpTime == 520) {

                this.king.playSound(SoundEvents.WITHER_BREAK_BLOCK, 1.0F, 1.0F);
                this.king.setDeltaMovement(getVector.x(), 1.0, getVector.z());

                if (world instanceof ServerLevel) {
                    ((ServerLevel) world).sendParticles(ParticleTypes.POOF, this.king.getX(), this.king.getY(), this.king.getZ(), 75, 5, 2, 5, 0.01);
                }
            }

            if (this.jumpTime >= 560) {
                this.jumpTime = 0;
            }
        }

        this.king.setJumping(this.jumpTime > 500);
    }
}
