package io.github.bioplethora.entity.ai.goals;

import io.github.bioplethora.entity.BPMonsterEntity;
import io.github.bioplethora.entity.creatures.AlphemKingEntity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;

public class AlphemKingSmashingGoal extends AlphemKingMeeleeGoal {

    public AlphemKingSmashingGoal(AlphemKingEntity entity, double animationLength, double attackBegin, double attackEnd) {
        super(entity, animationLength, attackBegin, attackEnd);
    }

    @Override
    public int attackPhaseReq() {
        return 2;
    }

    @Override
    public void doCIV(AlphemKingEntity attacker) {
        king.setSmashing(false);
    }

    public double reachSq(AlphemKingEntity attacker, LivingEntity target) {
        return getAttackReachSq(attacker, target);
    }

    public static double getAttackReachSq(BPMonsterEntity attacker, LivingEntity target) {
        return attacker.getBbWidth() * 3.5F * attacker.getBbWidth() * 3.5F + target.getBbWidth();
    }

    @Override
    public boolean canUse() {
        return AlphemKingSmashingGoal.checkIfValid(this, king, this.king.getTarget());
    }

    @Override
    public void start() {
        isInAttackState = true;
        this.king.setSmashing(true);
        this.king.setAggressive(true);
        this.animationProgress = 0;
    }

    @Override
    public void stop() {
        if (this.hasHit) {
            switchPhase();
            this.hasHit = false;
        }
        this.animationProgress = 0;
        this.isInAttackState = false;
        LivingEntity target = this.entity.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
            this.entity.setTarget(null);
        }
        king.setSmashing(false);
        this.entity.setAggressive(false);
    }

    @Override
    public void switchPhase() {
        this.king.attackPhase = 0;
    }
}
