package io.github.bioplethora.api.events;

import io.github.bioplethora.api.events.hooks.GrylynenSpawnEvent;
import io.github.bioplethora.api.events.hooks.TrueDefenseHurtEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;

public class BPHooks {

    public static boolean onTrueDefenseHurt(LivingEntity entity, DamageSource src) {
        return MinecraftForge.EVENT_BUS.post(new TrueDefenseHurtEvent(entity, src));
    }

    public static boolean onGrylynenSpawn(Player player) {
        return MinecraftForge.EVENT_BUS.post(new GrylynenSpawnEvent(player));
    }
}
