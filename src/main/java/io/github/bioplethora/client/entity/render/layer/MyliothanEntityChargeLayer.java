package io.github.bioplethora.client.entity.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.entity.creatures.MyliothanEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class MyliothanEntityChargeLayer extends GeoLayerRenderer<MyliothanEntity> {

    private static final ResourceLocation MODEL = new ResourceLocation(Bioplethora.MOD_ID, "geo/myliothan.geo.json");

    public MyliothanEntityChargeLayer(IGeoRenderer<MyliothanEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, MyliothanEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderType glintRender =  RenderType.entityGlint();
        if(entityLivingBaseIn.isCharging()) {
            this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, glintRender, matrixStackIn, bufferIn, bufferIn.getBuffer(glintRender), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        }
    }
}