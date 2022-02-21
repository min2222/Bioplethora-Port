package io.github.bioplethora.entity.ai.tameable;

import io.github.bioplethora.entity.BPAnimalEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;

/**
 * Credits: WeirdNerd (Permission Granted)
 */
public abstract class BPAnimalMovableGoal extends BPAnimalGoal {

    protected Path path;

    @Override
    abstract public boolean canUse();

    protected boolean isExecutable(BPAnimalMovableGoal goal, BPAnimalEntity attacker, LivingEntity target) {
        if (target == null) return false;
        if (target.isAlive() && !target.isSpectator()) {
            if (target instanceof PlayerEntity && ((PlayerEntity) target).isCreative()) return false;

            double distance = goal.entity.distanceToSqr(target.getX(), target.getY(), target.getZ());
            goal.path = attacker.getNavigation().createPath(target, 0);

            return attacker.getSensing().canSee(target) && distance >= BPAnimalGoal.getAttackReachSq(attacker, target);
        }
        return false;
    }
}
