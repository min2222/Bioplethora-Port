package io.github.bioplethora.item.functionals;

import io.github.bioplethora.item.ItemSettings;
import io.github.bioplethora.registry.BPEffects;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class SpiritFissionCharmItem extends ActivatableItem {

    public SpiritFissionCharmItem(Properties properties) {
        super(properties, true);
    }

    @Override
    public void activatedTick(ItemStack pStack, World pLevel, Entity pEntity) {
        super.activatedTick(pStack, pLevel, pEntity);

        if (pEntity instanceof PlayerEntity) {
            ((PlayerEntity) pEntity).addEffect(new EffectInstance(BPEffects.SPIRIT_FISSION.get(), 10, 0));
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        ItemSettings.sacredLevelText(tooltip);

        tooltip.add(new TranslationTextComponent("item.bioplethora.spirit_fission_charm.spirit_fission.skill").withStyle(ItemSettings.SKILL_NAME_COLOR));
        if (Screen.hasShiftDown() || Screen.hasControlDown()) {
            tooltip.add(new TranslationTextComponent("item.bioplethora.spirit_fission_charm.spirit_fission.desc").withStyle(ItemSettings.SKILL_DESC_COLOR));
        }
    }
}
