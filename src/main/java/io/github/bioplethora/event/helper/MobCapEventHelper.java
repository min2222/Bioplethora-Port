package io.github.bioplethora.event.helper;

import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.entity.IMobCappedEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class MobCapEventHelper {

    public static void onEntityHurt(LivingHurtEvent event) {
        LivingEntity victim = event.getEntity();

        if (BPConfig.COMMON.allowMobCaps.get()) {
            if (victim instanceof IMobCappedEntity) {
                if (event.getSource() != DamageSource.OUT_OF_WORLD) {
                    if (event.getAmount() > ((IMobCappedEntity) victim).getMaxDamageCap()) {
                        event.setAmount(((IMobCappedEntity) victim).getMaxDamageCap());
                    }
                }
            }
        }
    }
}
