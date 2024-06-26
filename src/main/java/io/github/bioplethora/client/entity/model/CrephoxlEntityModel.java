package io.github.bioplethora.client.entity.model;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.entity.creatures.CrephoxlEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CrephoxlEntityModel extends AnimatedGeoModel<CrephoxlEntity> {

    @Override
    public ResourceLocation getModelResource(CrephoxlEntity entity) {
        return new ResourceLocation(Bioplethora.MOD_ID, "geo/crephoxl.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CrephoxlEntity entity) {
        return new ResourceLocation(Bioplethora.MOD_ID, "textures/entity/crephoxl.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CrephoxlEntity entity) {
        return new ResourceLocation(Bioplethora.MOD_ID, "animations/crephoxl.animation.json");
    }

    @Override
    public void setLivingAnimations(CrephoxlEntity entity, Integer uniqueID, @SuppressWarnings("rawtypes") AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        IBone head = this.getAnimationProcessor().getBone("neckbot");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX((extraData.headPitch) * ((float) Math.PI / 180F));
        head.setRotationY((extraData.netHeadYaw) * ((float) Math.PI / 270F));
    }
}
