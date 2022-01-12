package io.github.bioplethora.entity.ai;

import io.github.bioplethora.entity.creatures.AltyrusEntity;
import io.github.bioplethora.entity.creatures.BellophgolemEntity;
import io.github.bioplethora.registry.BioplethoraEntities;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class AltyrusSummonGolemGoal extends Goal {

    private final AltyrusEntity altyrus;
    public int summonTime;

    public AltyrusSummonGolemGoal(AltyrusEntity altyrusEntity) {
        this.altyrus = altyrusEntity;
    }

    public boolean canUse() {
        return this.altyrus.getTarget() != null;
    }

    public void start() {
        this.summonTime = 0;
    }

    public void stop() {
        this.altyrus.setSummoning(false);
    }

    public void tick() {
        LivingEntity livingentity = this.altyrus.getTarget();
        ServerWorld serverworld = (ServerWorld)this.altyrus.level;
        World world = this.altyrus.level;

        ++this.summonTime;

        if (this.summonTime > 400) {
            BlockPos blockpos = this.altyrus.blockPosition();

            BellophgolemEntity bellophgolemEntity = BioplethoraEntities.BELLOPHGOLEM.get().create(world);
            bellophgolemEntity.moveTo(blockpos, 0.0F, 0.0F);
            bellophgolemEntity.setOwner(this.altyrus);
            bellophgolemEntity.finalizeSpawn(serverworld, world.getCurrentDifficultyAt(blockpos), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);

            bellophgolemEntity.setHasLimitedLife(true);
            bellophgolemEntity.setExplodeOnExpiry(true);
            bellophgolemEntity.setLifeLimitBeforeDeath(200);

            serverworld.addFreshEntityWithPassengers(bellophgolemEntity);
            serverworld.addFreshEntityWithPassengers(bellophgolemEntity);

            this.summonTime = 0;
        }

        this.altyrus.setSummoning(this.summonTime > 360);
    }
}
