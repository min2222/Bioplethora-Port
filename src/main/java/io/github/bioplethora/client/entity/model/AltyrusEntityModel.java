package io.github.bioplethora.client.entity.model;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.api.IAdvancedGeoModel;
import io.github.bioplethora.entity.creatures.AltyrusEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class AltyrusEntityModel extends AnimatedGeoModel<AltyrusEntity> implements IAdvancedGeoModel<AltyrusEntity> {

    @Override
    public ResourceLocation getModelResource(AltyrusEntity entity) {
        return new ResourceLocation(Bioplethora.MOD_ID, "geo/altyrus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AltyrusEntity entity) {
        return new ResourceLocation(Bioplethora.MOD_ID, "textures/entity/altyrus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AltyrusEntity entity) {
        return new ResourceLocation(Bioplethora.MOD_ID, "animations/altyrus.animation.json");
    }

    @Override
    public void setLivingAnimations(AltyrusEntity entity, Integer uniqueID, @SuppressWarnings("rawtypes") AnimationEvent event) {
        super.setLivingAnimations(entity, uniqueID, event);

        IBone head = getB("head"), altyrus = getB("altyrus");
        IBone ringsCenter = getB("rings_crenter"), bodyBot = getB("bodybot");
        IBone htr = getB("hand_top_right"), htl = getB("hand_top_left"), hbr = getB("hand_bottom_right"), hbl = getB("hand_bottom_left");

        EntityModelData extraData = (EntityModelData) event.getExtraDataOfType(EntityModelData.class).get(0);

        if (entity.isCharging()) {
            adaptHeadOnLook(extraData, altyrus);
        } else {
            adaptHeadOnLook(extraData, head);
        }

        float tickCountNeg = entity.ageInTicks - (float) entity.tickCount;
        float lerpHelper = Mth.lerp(tickCountNeg, entity.prevHurtTime, entity.hurtTime) / entity.hurtDuration;
        float pi = (float) Math.PI;

        if (entity.prevHurtTime > 0 && !Minecraft.getInstance().isPaused()) {
            lerpHelper = lerpHelper * lerpHelper * lerpHelper;
            altyrus.setRotationX(altyrus.getRotationX() + -Mth.cos(lerpHelper * pi) * 0.30f);
            head.setRotationX(head.getRotationX() + -Mth.sin(lerpHelper * pi) * 0.30f);
            ringsCenter.setRotationZ(ringsCenter.getRotationZ() + -Mth.cos(lerpHelper * pi) * 0.75f);
            bodyBot.setRotationX(bodyBot.getRotationX() + Mth.sin(lerpHelper * pi) * 0.45f);

            htr.setRotationX(htr.getRotationX() + -Mth.cos(lerpHelper * pi) * 0.30f);
            htl.setRotationX(htl.getRotationX() + -Mth.cos(lerpHelper * pi) * 0.30f);
            hbr.setRotationX(hbr.getRotationX() + -Mth.cos(lerpHelper * pi) * -0.30f);
            hbl.setRotationX(hbl.getRotationX() + -Mth.cos(lerpHelper * pi) * -0.30f);
        }
    }

    public IBone getB(String bone) {
        return this.getAnimationProcessor().getBone(bone);
    }
}
