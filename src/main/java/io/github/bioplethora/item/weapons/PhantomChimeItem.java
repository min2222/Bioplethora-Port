package io.github.bioplethora.item.weapons;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PhantomChimeItem extends Item {

    public PhantomChimeItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {

        World world = context.getLevel();
        PlayerEntity entity = context.getPlayer();
        Hand hand = context.getHand();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        double x = pos.getX(), y = pos.getY(), z = pos.getZ();

        entity.getCooldowns().addCooldown(stack.getItem(), 120);
        entity.swing(hand);
        world.playSound(entity, pos, SoundEvents.PHANTOM_FLAP, SoundCategory.PLAYERS, 1, 1);

        List<Entity> nearEntities = world.getEntitiesOfClass(Entity.class, new AxisAlignedBB(x - (7 / 2d), y - (7 / 2d), z - (7 / 2d), x + (7 / 2d), y + (7 / 2d), z + (7 / 2d)), null).stream().sorted(new Object() {
                    Comparator<Entity> compareDistOf(double dx, double dy, double dz) {
                        return Comparator.comparing((entCnd -> entCnd.distanceToSqr(dx, dy, dz)));
                    }
                }.compareDistOf(x, y, z)).collect(Collectors.toList());
        for (Entity entityIterator : nearEntities) {
            if (entityIterator instanceof LivingEntity && entityIterator != entity) {
                ((LivingEntity) entityIterator).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60, 2));
                entityIterator.setDeltaMovement((entity.getDeltaMovement().x()), 1, (entity.getDeltaMovement().z()));
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("item.bioplethora.sacred_level.desc").withStyle(TextFormatting.AQUA));
        tooltip.add(new TranslationTextComponent("item.bioplethora.shift_reminder.desc").withStyle(TextFormatting.GRAY));

        tooltip.add(new TranslationTextComponent("item.bioplethora.phantom_chime.flapping_up.skill").withStyle(TextFormatting.GOLD));
        if (Screen.hasShiftDown() || Screen.hasControlDown()) {
            tooltip.add(new TranslationTextComponent("item.bioplethora.phantom_chime.flapping_up.desc").withStyle(TextFormatting.GRAY));
        }
    }
}
