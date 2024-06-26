package io.github.bioplethora.entity.ai.gecko;

import java.util.EnumSet;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

/**
 * Credits: WeirdNerd (Permission Granted)
 */
public class GeckoMoveToTargetGoal<E extends Mob> extends GeckoMovableGoal<E> {

    public final double speedMultiplier;
    public final int checkRate;

    public GeckoMoveToTargetGoal(E entity, double speedMultiplier, int checkRate) {
        this.entity = entity;
        this.speedMultiplier = speedMultiplier;
        this.checkRate = checkRate;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (RANDOM.nextInt(this.checkRate) == 0) return false;

        return this.isExecutable(this, this.entity, this.entity.getTarget());
    }

    @Override
    public boolean canContinueToUse() {
        if (RANDOM.nextInt(this.checkRate) == 0) return true;

        return this.isExecutable(this, this.entity, this.entity.getTarget());
    }

    @Override
    public void start() {
        this.entity.setAggressive(true);
        ((IGeckoBaseEntity) this.entity).setMoving(true);
        this.entity.getNavigation().moveTo(this.path, this.speedMultiplier);
    }

    @Override
    public void stop() {
        LivingEntity target = this.entity.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
            this.entity.setTarget(null);
        }
        this.entity.setAggressive(false);
        ((IGeckoBaseEntity) this.entity).setMoving(false);
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity target = this.entity.getTarget();
        if (target == null) return;

        this.entity.getLookControl().setLookAt(target, 30F, 30F);
        this.moveToTarget();
    }

    public void moveToTarget() {
        LivingEntity target = this.entity.getTarget();
        this.entity.getNavigation().moveTo(target, this.speedMultiplier);
    }
}
