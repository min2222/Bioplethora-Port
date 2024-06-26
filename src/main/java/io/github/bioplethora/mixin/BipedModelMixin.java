package io.github.bioplethora.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;

@Mixin(HumanoidModel.class)
public abstract class BipedModelMixin<T extends LivingEntity> extends AgeableListModel<T> {

    /**
    @Shadow public ModelRenderer rightArm;
    @Shadow public ModelRenderer leftArm;
    @Shadow public ModelRenderer head;
    @Shadow public ModelRenderer body;
    @Shadow public ModelRenderer rightLeg;
    @Shadow public ModelRenderer leftLeg;
    @Shadow public ModelRenderer hat;

    @Shadow protected abstract HandSide getAttackArm(T pEntity);
    @Shadow protected abstract ModelRenderer getArm(HandSide pSide);

    @Inject(method = "Lnet/minecraft/client/renderer/entity/model/BipedModel;poseRightArm(Lnet/minecraft/entity/LivingEntity;)V", at = @At(value = "HEAD"))
    private void poseRight(T living, CallbackInfo ci) {

        if (BPConfig.COMMON.enableCustomModelPositions.get()) {

            boolean IQMainHandR = living.getMainHandItem().getItem() instanceof InfernalQuarterstaffItem;
            boolean IQOffHandL = living.getOffhandItem().getItem() instanceof InfernalQuarterstaffItem;
            boolean IQBothHands = IQMainHandR && IQOffHandL;

            if (living instanceof Player) {
                if (!(living.getUseItem().getItem() instanceof ShieldItem)) {

                    if (IQMainHandR && !IQOffHandL) {
                        rightArm.xRot = rotateBone(69.5243F);
                        rightArm.yRot = rotateBone(24.6598F);
                        rightArm.zRot = rotateBone(44.0413F);
                        leftArm.zRot = rotateBone(72.2469F);
                        leftArm.zRot = rotateBone(9.5327F);
                        leftArm.zRot = rotateBone(3.0351F);
                    } else if (!IQMainHandR && IQOffHandL) {
                        leftArm.xRot = rotateBone(69.5243F);
                        leftArm.yRot = rotateBone(24.6598F);
                        leftArm.zRot = rotateBone(44.0413F);
                        rightArm.xRot = rotateBone(72.2469F);
                        rightArm.yRot = rotateBone(9.5327F);
                        rightArm.zRot = rotateBone(3.0351F);
                    } else if (IQBothHands) {
                        leftArm.xRot = 69.5243F * ((float)Math.PI / 180F);
                        leftArm.yRot = 24.6598F * ((float)Math.PI / 180F);
                        leftArm.zRot = 44.0413F * ((float)Math.PI / 180F);
                        rightArm.xRot = 69.5243F * ((float)Math.PI / 180F);
                        rightArm.yRot = 24.6598F * ((float)Math.PI / 180F);
                        rightArm.zRot = 44.0413F * ((float)Math.PI / 180F);
                    }
                }
            }
        }
    }

    @Inject(method = "Lnet/minecraft/client/renderer/entity/model/BipedModel;poseLeftArm(Lnet/minecraft/entity/LivingEntity;)V", at = @At(value = "HEAD"))
    private void poseLeft(T living, CallbackInfo ci) {

        if (BPConfig.COMMON.enableCustomModelPositions.get()) {

            boolean IQMainHandR = living.getMainHandItem().getItem() instanceof InfernalQuarterstaffItem;
            boolean IQOffHandL = living.getOffhandItem().getItem() instanceof InfernalQuarterstaffItem;
            boolean IQBothHands = IQMainHandR && IQOffHandL;

            if (living instanceof Player) {
                if (!(living.getUseItem().getItem() instanceof ShieldItem)) {

                    if (IQMainHandR && !IQOffHandL) {
                        rightArm.xRot = rotateBone(69.5243F);
                        rightArm.yRot = rotateBone(24.6598F);
                        rightArm.zRot = rotateBone(44.0413F);
                        leftArm.zRot = rotateBone(72.2469F);
                        leftArm.zRot = rotateBone(9.5327F);
                        leftArm.zRot = rotateBone(3.0351F);
                    } else if (!IQMainHandR && IQOffHandL) {
                        leftArm.xRot = rotateBone(69.5243F);
                        leftArm.yRot = rotateBone(24.6598F);
                        leftArm.zRot = rotateBone(44.0413F);
                        rightArm.xRot = rotateBone(72.2469F);
                        rightArm.yRot = rotateBone(9.5327F);
                        rightArm.zRot = rotateBone(3.0351F);
                    } else if (IQBothHands) {
                        leftArm.xRot = 69.5243F * ((float)Math.PI / 180F);
                        leftArm.yRot = 24.6598F * ((float)Math.PI / 180F);
                        leftArm.zRot = 44.0413F * ((float)Math.PI / 180F);
                        rightArm.xRot = 69.5243F * ((float)Math.PI / 180F);
                        rightArm.yRot = 24.6598F * ((float)Math.PI / 180F);
                        rightArm.zRot = 44.0413F * ((float)Math.PI / 180F);
                    }
                }
            }
        }
    }

    public float rotateBone(float degrees) {
       //return degrees * ((float)Math.PI / 180F);
        return (float) Math.toRadians(degrees);
    }

    @Inject(method = "Lnet/minecraft/client/renderer/entity/model/BipedModel;setupAttackAnimation(Lnet/minecraft/entity/LivingEntity;F)V", at = @At("HEAD"))
    public void setupAttack(T livingEntity, float a, CallbackInfo ci) {

        if (BPConfig.COMMON.enableCustomModelAnimations.get()) {
            if (livingEntity instanceof Player) {
                if (!(this.attackTime <= 0.0F)) {
                    if (livingEntity.getMainHandItem().getItem() instanceof InfernalQuarterstaffItem) {

                        HandSide handside = this.getAttackArm(livingEntity);
                        ModelRenderer modelrenderer = this.getArm(handside);
                        float f;

                        f = 1.0F - this.attackTime;
                        f = f * f;
                        f = f * f;
                        f = 1.0F - f;

                        float f1 = Mth.sin(f * (float) Math.PI);
                        float f2 = Mth.sin(this.attackTime * (float) Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;

                        modelrenderer.xRot = (float) ((double) modelrenderer.xRot - ((double) f1 * 1.2D + (double) f2)) * 2;
                        modelrenderer.xRot += Mth.sin(this.attackTime * (float) Math.PI) * 0.75F;
                    }
                }
            }
        }
    }**/
}
