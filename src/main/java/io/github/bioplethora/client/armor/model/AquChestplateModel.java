package io.github.bioplethora.client.armor.model;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.item.armor.AquChestplateItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AquChestplateModel extends AnimatedGeoModel<AquChestplateItem> {

    @Override
    public ResourceLocation getModelResource(AquChestplateItem object) {
        return new ResourceLocation(Bioplethora.MOD_ID, "geo/armors/aqu_chestplate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AquChestplateItem object) {
        return new ResourceLocation(Bioplethora.MOD_ID, "textures/models/armor/aqu_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AquChestplateItem animatable) {
        return new ResourceLocation(Bioplethora.MOD_ID, "animations/armors/aqu_chestplate.animation.json");
    }
}
