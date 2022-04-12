package io.github.bioplethora.item.weapons;

import io.github.bioplethora.api.world.EntityUtils;
import io.github.bioplethora.item.ItemSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class GrylynenShieldRedItem extends GrylynenShieldBaseItem {

    public GrylynenShieldRedItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldownReduction() {
        return 60;
    }

    @Override
    public int getArmorBonus() {
        return 2;
    }

    @Override
    public void blockingSkill(ItemStack stack, LivingEntity user, Entity attacker, World world) {
        if (EntityUtils.isLiving(attacker)) {
            EntityUtils.knockbackAwayFromUser(3, user, (LivingEntity) attacker);
        }
        if (Math.random() <= 0.75) {
            user.heal(2F);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        ItemSettings.sacredLevelText(tooltip);

        tooltip.add(new TranslationTextComponent("item.bioplethora.red_grylynen_shield.red_crystal_energy.skill").withStyle(ItemSettings.SKILL_NAME_COLOR));
        if (Screen.hasShiftDown() || Screen.hasControlDown()) {
            tooltip.add(new TranslationTextComponent("item.bioplethora.red_grylynen_shield.red_crystal_energy.desc").withStyle(ItemSettings.SKILL_DESC_COLOR));
        }
    }
}