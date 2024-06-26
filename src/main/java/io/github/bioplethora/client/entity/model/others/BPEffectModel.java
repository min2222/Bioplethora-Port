package io.github.bioplethora.client.entity.model.others;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.entity.others.BPEffectEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BPEffectModel extends AnimatedGeoModel<BPEffectEntity> {

    @Override
    public ResourceLocation getModelResource(BPEffectEntity entity) {
        return new ResourceLocation(Bioplethora.MOD_ID, "geo/others/" + entity.getEffectType().getModel().getModelString() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BPEffectEntity entity) {
        if (entity.getEffectType().getFrames() > 0) {
            return new ResourceLocation(Bioplethora.MOD_ID, "textures/entity/others/" + entity.getEffectType().getTexture() + "_" + entity.getFrameLevel() + ".png");
        } else {
            return new ResourceLocation(Bioplethora.MOD_ID, "textures/entity/others/" + entity.getEffectType().getTexture() + ".png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(BPEffectEntity entity) {
        return new ResourceLocation(Bioplethora.MOD_ID, "animations/others/bp_effect.animation.json");
    }

    /*
    @Override
    public void setLivingAnimations(InfernalQuarterstaffSlashEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("bone");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationY((extraData.netHeadYaw) * ((float) Math.PI / 270F));
    }*/
}
