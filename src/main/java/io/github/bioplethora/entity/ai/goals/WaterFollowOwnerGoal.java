package io.github.bioplethora.entity.ai.goals;

import java.util.EnumSet;

import io.github.bioplethora.entity.WaterAndLandAnimalEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

public class WaterFollowOwnerGoal extends Goal {
    private final WaterAndLandAnimalEntity tamable;
    private LivingEntity owner;
    private final LevelReader level;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;
    private final boolean canFly;

    public WaterFollowOwnerGoal(WaterAndLandAnimalEntity tamable, double speedModifier, float startDistance, float stopDistance, boolean canFly) {
        this.tamable = tamable;
        this.level = tamable.level;
        this.speedModifier = speedModifier;
        this.navigation = tamable.getNavigation();
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
        this.canFly = canFly;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.tamable.getOwner();
        if (livingentity == null) {
            return false;
        } else if (livingentity.isSpectator()) {
            return false;
        } else if (this.tamable.isOrderedToSit()) {
            return false;
        } else if (this.tamable.distanceToSqr(livingentity) < (double)(this.startDistance * this.startDistance)) {
            return false;
        } else {
            this.owner = livingentity;
            return true;
        }
    }

    public boolean canContinueToUse() {
        if (this.navigation.isDone()) {
            return false;
        } else if (this.tamable.isOrderedToSit()) {
            return false;
        } else {
            return !(this.tamable.distanceToSqr(this.owner) <= (double)(this.stopDistance * this.stopDistance));
        }
    }

    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tamable.getPathfindingMalus(BlockPathTypes.WATER);
        this.tamable.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.tamable.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    public void tick() {
        this.tamable.getLookControl().setLookAt(this.owner, 10.0F, (float)this.tamable.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.tamable.isLeashed() && !this.tamable.isPassenger()) {
                if (this.tamable.distanceToSqr(this.owner) >= 144.0D) {
                    this.teleportToOwner();
                } else {
                    this.tamable.getMoveControl().setWantedPosition(this.owner.getX(), this.owner.getY(), this.owner.getZ(), 1.5D);
                }
            }
        }
    }

    private void teleportToOwner() {
        BlockPos blockpos = this.owner.blockPosition();

        for(int i = 0; i < 10; ++i) {
            int j = this.randomIntInclusive(-3, 3);
            int k = this.randomIntInclusive(-1, 1);
            int l = this.randomIntInclusive(-3, 3);
            boolean flag = this.maybeTeleportTo(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
            if (flag) {
                return;
            }
        }

    }

    private boolean maybeTeleportTo(int p_226328_1_, int p_226328_2_, int p_226328_3_) {
        if (Math.abs((double)p_226328_1_ - this.owner.getX()) < 2.0D && Math.abs((double)p_226328_3_ - this.owner.getZ()) < 2.0D) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(p_226328_1_, p_226328_2_, p_226328_3_))) {
            return false;
        } else {
            this.tamable.moveTo((double)p_226328_1_ + 0.5D, p_226328_2_, (double)p_226328_3_ + 0.5D, this.tamable.yRot, this.tamable.xRot);
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos p_226329_1_) {
        BlockPathTypes pathnodetype = WalkNodeEvaluator.getBlockPathTypeStatic(this.level, p_226329_1_.mutable());
        if (pathnodetype != BlockPathTypes.WALKABLE) {
            return false;
        } else {
            BlockState blockstate = this.level.getBlockState(p_226329_1_.below());
            if (!this.canFly && blockstate.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos blockpos = p_226329_1_.subtract(this.tamable.blockPosition());
                return this.level.noCollision(this.tamable, this.tamable.getBoundingBox().move(blockpos));
            }
        }
    }

    private int randomIntInclusive(int p_226327_1_, int p_226327_2_) {
        return this.tamable.getRandom().nextInt(p_226327_2_ - p_226327_1_ + 1) + p_226327_1_;
    }
}
