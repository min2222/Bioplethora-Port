package io.github.bioplethora.entity.ai;

import io.github.bioplethora.entity.creatures.AltyrusEntity;
import io.github.bioplethora.entity.projectile.UltimateBellophiteClusterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class AltyrusRangedAttackGoal extends Goal {

    private final AltyrusEntity altyrus;
    public boolean goingUp;
    public int chargeTime;

    public AltyrusRangedAttackGoal(AltyrusEntity altyrusEntity) {
        this.altyrus = altyrusEntity;
    }

    public boolean canUse() {
        return (this.altyrus.getTarget() != null);
    }

    public void start() {
        this.chargeTime = 0;
    }

    public void stop() {
        this.altyrus.setCharging(false);
        this.goingUp = false;
    }

    public boolean canContinueToUse() {
        return this.altyrus.getTarget() != null;
    }

    public void tick() {
        LivingEntity target = this.altyrus.getTarget();

        if (target != null && target.distanceToSqr(this.altyrus) < 4096.0D /*&& this.altyrus.canSee(target)*/) {

            ++this.chargeTime;

            if (this.goingUp) {
                this.altyrus.setDeltaMovement(this.altyrus.getDeltaMovement().add(0, 0.005, 0));
            }

            World world = this.altyrus.level;
            BlockPos pos = new BlockPos((int) this.altyrus.getX(), (int) this.altyrus.getY(), (int) this.altyrus.getZ());

            Vector3d vector3d = this.altyrus.getViewVector(1.0F);
            double d2 = target.getX() - (this.altyrus.getX() + vector3d.x * 4.0D);
            double d3 = target.getY(0.5D) - (0.5D + this.altyrus.getY(0.5D));
            double d4 = target.getZ() - (this.altyrus.getZ() + vector3d.z * 4.0D);
            UltimateBellophiteClusterEntity ultimateBellophiteClusterEntity = new UltimateBellophiteClusterEntity(world, this.altyrus, d2, d3, d4);
            ultimateBellophiteClusterEntity.setPos(this.altyrus.getX() + vector3d.x * 4.0D, this.altyrus.getY(0.5D) + 0.5D, ultimateBellophiteClusterEntity.getZ() + vector3d.z * 4.0D);

            if (this.chargeTime == 10) {
                world.playSound(null, pos, SoundEvents.ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, (float) 1, (float) 1);
                this.goingUp = true;
            }

            if (this.chargeTime == 30) {
                world.playSound(null, pos, SoundEvents.SHULKER_SHOOT, SoundCategory.HOSTILE, (float) 1, (float) 1);
                world.addFreshEntity(ultimateBellophiteClusterEntity);
            }
            if (this.chargeTime == 35) {
                world.addFreshEntity(ultimateBellophiteClusterEntity);
            }
            if (this.chargeTime == 40) {
                world.addFreshEntity(ultimateBellophiteClusterEntity);
                this.goingUp = false;
            }
            if (this.chargeTime == 45) {
                world.addFreshEntity(ultimateBellophiteClusterEntity);
            }
            if (this.chargeTime == 50) {
                world.addFreshEntity(ultimateBellophiteClusterEntity);

                this.chargeTime = -100;
            }
        }

        this.altyrus.setCharging(this.chargeTime > 10);
    }
}
