package io.github.bioplethora.event;

import io.github.bioplethora.api.IHurtSkillArmor;
import io.github.bioplethora.api.IReachWeapon;
import io.github.bioplethora.api.advancements.AdvancementUtils;
import io.github.bioplethora.api.world.BlockUtils;
import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.entity.creatures.AltyrusEntity;
import io.github.bioplethora.entity.creatures.ShachathEntity;
import io.github.bioplethora.entity.others.PrimordialRingEntity;
import io.github.bioplethora.event.helper.AlphemKingSpawnHelper;
import io.github.bioplethora.event.helper.ArrowMixinHelper;
import io.github.bioplethora.event.helper.BonemealBlocksHelper;
import io.github.bioplethora.event.helper.GrylynenSpawnHelper;
import io.github.bioplethora.event.helper.MobCapEventHelper;
import io.github.bioplethora.event.helper.RenderEventHelper;
import io.github.bioplethora.event.helper.ShachathCurseHelper;
import io.github.bioplethora.event.helper.TooltipEventHelper;
import io.github.bioplethora.item.ExperimentalItem;
import io.github.bioplethora.item.functionals.SwervingTotemItem;
import io.github.bioplethora.item.weapons.AbyssalBladeItem;
import io.github.bioplethora.item.weapons.FrostbiteMetalShieldItem;
import io.github.bioplethora.item.weapons.GrylynenShieldBaseItem;
import io.github.bioplethora.item.weapons.InfernalQuarterstaffItem;
import io.github.bioplethora.item.weapons.VermilionBladeItem;
import io.github.bioplethora.network.BPNetwork;
import io.github.bioplethora.network.functions.LeftSwingPacket;
import io.github.bioplethora.network.functions.RightSwingPacket;
import io.github.bioplethora.registry.BPBlocks;
import io.github.bioplethora.registry.BPEffects;
import io.github.bioplethora.registry.BPItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber
public class ServerWorldEvents {

    @SubscribeEvent
    public static void playerTickDebug(TickEvent.PlayerTickEvent event) {
        /*
        if (event.player.getMainHandItem().getItem() == BPItems.TEST_ITEM.get()) {
            event.player.displayClientMessage(new StringTextComponent("xRot: " + event.player.xRot), true);
        } else {
            event.player.displayClientMessage(new StringTextComponent("yRot: " + event.player.yRot), true);
        }*/
    }

    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        BPNetwork.CHANNEL.sendToServer(new LeftSwingPacket());
    }


    /**
     * Off-Hand combat integration
     */
    @SubscribeEvent
    public static void onPlayerRightClick(PlayerInteractEvent.RightClickEmpty event) {

        if (ModList.get().isLoaded("offhandcombat")) {

            hitHandler(event.getEntity(), event.getItemStack());

            if (event.getItemStack().getItem() instanceof IReachWeapon) {
                if (event.getLevel().isClientSide()) {
                    BPNetwork.CHANNEL.sendToServer(new RightSwingPacket());
                }
            }
        }
    }

    public static void hitHandler(Player entity, ItemStack stack) {

        if (stack.getItem() instanceof IReachWeapon) {

            double range = ((IReachWeapon) stack.getItem()).getReachDistance();
            double distance = range * range;
            Vec3 vec = entity.getEyePosition(1);
            Vec3 vec1 = entity.getViewVector(1);
            Vec3 targetVec = vec.add(vec1.x * range, vec1.y * range, vec1.z * range);
            AABB aabb = entity.getBoundingBox().expandTowards(vec1.scale(range)).inflate(4.0D, 4.0D, 4.0D);
            EntityHitResult result = ProjectileUtil.getEntityHitResult(entity, vec, targetVec, aabb, EntitySelector.NO_CREATIVE_OR_SPECTATOR, distance);

            if ((result != null ? result.getEntity() : null) != null) {
                entity.attack(result.getEntity());
            }

            if (result == null) {
                if (stack.getItem() instanceof InfernalQuarterstaffItem) {
                    ((InfernalQuarterstaffItem) stack.getItem()).emptySwingHandler(stack, entity);
                }
            }

            if (stack.getItem() instanceof VermilionBladeItem) {
                ((VermilionBladeItem) stack.getItem()).emptySwingHandler(stack, entity);
            }

            if (stack.getItem() instanceof AbyssalBladeItem) {
                ((AbyssalBladeItem) stack.getItem()).shootHandler(entity, stack, 0);
                ((AbyssalBladeItem) stack.getItem()).shootHandler(entity, stack, -15);
                ((AbyssalBladeItem) stack.getItem()).shootHandler(entity, stack, 15);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {

        Player eventEntity = event.getEntity();

        if (eventEntity != null) {
            if (BPConfig.COMMON.hellMode.get() && BPConfig.COMMON.hellModeReminder.get()) {
                if (!eventEntity.level.isClientSide()) {
                    eventEntity.displayClientMessage(Component.literal("\u00A7cQuick Reminder: You are in Bioplethora Hell Mode. Most Bioplethora creatures will become stronger and more powerful."), (false));
                }
            }

            if (!eventEntity.level.isClientSide()) {
                AdvancementUtils.grantBioAdvancement(eventEntity, "bioplethora:bioplethora_startup");
            }

            if (BPConfig.COMMON.startupBiopedia.get()) {

                CompoundTag playerData = event.getEntity().getPersistentData();
                CompoundTag data = playerData.getCompound(Player.PERSISTED_NBT_TAG);

                if (!data.getBoolean("bioplethora.has_biopedia")) {
                    ItemStack stack = new ItemStack(BPItems.BIOPEDIA.get());
                    stack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(eventEntity, stack);
                    data.putBoolean("bioplethora.has_biopedia", true);
                    playerData.put(Player.PERSISTED_NBT_TAG, data);
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        TooltipEventHelper.onTooltip(event);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        GrylynenSpawnHelper.onBlockBreak(event);
    }

    @SubscribeEvent
    public static void performBonemealAction(BonemealEvent event) {
        BonemealBlocksHelper.performBonemealAction(event);
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {

        Entity defendantEnt = event.getEntity();
        Entity attackerEnt = event.getSource().getEntity();

        if (defendantEnt instanceof Player) {

            ItemStack getUseItem = ((Player) defendantEnt).getUseItem();
            Item getItem = getUseItem.getItem();

            boolean[] dmgEx = new boolean[]{
                    event.getSource() != DamageSource.IN_FIRE,
                    event.getSource() != DamageSource.LAVA,
                    event.getSource() != DamageSource.CACTUS,
                    event.getSource() != DamageSource.OUT_OF_WORLD
            };

            if (dmgEx[0] && dmgEx[1] && dmgEx[2] && dmgEx[3]) {
                if (getItem instanceof FrostbiteMetalShieldItem) {
                    ((FrostbiteMetalShieldItem) getItem).executeSkill(getUseItem, (LivingEntity) defendantEnt, defendantEnt.level);
                }

                if (getItem instanceof GrylynenShieldBaseItem) {

                    int recoveryAmount = ((GrylynenShieldBaseItem) getItem).getArmorBonus();
                    LivingEntity defendantLiving = (LivingEntity) defendantEnt;

                    ((GrylynenShieldBaseItem) getItem).blockingSkill(getUseItem, defendantLiving, attackerEnt, event.getEntity().level);

                    defendantLiving.getItemBySlot(EquipmentSlot.HEAD)
                            .setDamageValue(defendantLiving.getItemBySlot(EquipmentSlot.HEAD).getDamageValue() - recoveryAmount);
                    defendantLiving.getItemBySlot(EquipmentSlot.CHEST)
                            .setDamageValue(defendantLiving.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() - recoveryAmount);
                    defendantLiving.getItemBySlot(EquipmentSlot.LEGS)
                            .setDamageValue(defendantLiving.getItemBySlot(EquipmentSlot.LEGS).getDamageValue() - recoveryAmount);
                    defendantLiving.getItemBySlot(EquipmentSlot.FEET)
                            .setDamageValue(defendantLiving.getItemBySlot(EquipmentSlot.FEET).getDamageValue() - recoveryAmount);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        ShachathCurseHelper.onLivingDeath(event);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {

        boolean dsFire = (event.getSource() == DamageSource.IN_FIRE);
        boolean dsVoid = (event.getSource() == DamageSource.OUT_OF_WORLD);
        boolean dsFire2 = (event.getSource() == DamageSource.ON_FIRE);

        if (event.getEntity() instanceof LivingEntity) {

            LivingEntity living = (LivingEntity) event.getEntity();
            ItemStack sHead = living.getItemBySlot(EquipmentSlot.HEAD);
            ItemStack sChest = living.getItemBySlot(EquipmentSlot.CHEST);
            ItemStack sLegs = living.getItemBySlot(EquipmentSlot.LEGS);
            ItemStack sFeet = living.getItemBySlot(EquipmentSlot.FEET);

            if (event.getSource().getEntity() instanceof LivingEntity) {
                if (sHead.getItem() instanceof IHurtSkillArmor) {
                    ((IHurtSkillArmor) sHead.getItem()).onUserHurtWithArmor(living, (LivingEntity) event.getSource().getEntity(), sHead);
                }
                if (sChest.getItem() instanceof IHurtSkillArmor) {
                    ((IHurtSkillArmor) sChest.getItem()).onUserHurtWithArmor(living, (LivingEntity) event.getSource().getEntity(), sChest);
                }
                if (sLegs.getItem() instanceof IHurtSkillArmor) {
                    ((IHurtSkillArmor) sLegs.getItem()).onUserHurtWithArmor(living, (LivingEntity) event.getSource().getEntity(), sLegs);
                }
                if (sFeet.getItem() instanceof IHurtSkillArmor) {
                    ((IHurtSkillArmor) sFeet.getItem()).onUserHurtWithArmor(living, (LivingEntity) event.getSource().getEntity(), sFeet);
                }
            }
        }

        if (event.getSource().getEntity() instanceof LivingEntity && event.getEntity() instanceof LivingEntity) {
            if (((LivingEntity) event.getSource().getEntity()).hasEffect(BPEffects.SPIRIT_STRENGTHENING.get())) {
                float dmgCap = BPConfig.IN_HELLMODE ? 7 : 12;
                float floorReduction = Mth.ceil(((LivingEntity) event.getEntity()).getHealth() * 0.025F);
                float floor = Mth.floor(((LivingEntity) event.getEntity()).getHealth() * 0.05F);
                float armorReduction = Mth.ceil(((LivingEntity) event.getEntity()).getArmorValue() / 4F);
                float healthScaledDmg = Mth.clamp(floor - floorReduction, 0.0F, dmgCap);
                event.setAmount((event.getAmount() * 1.10F) + healthScaledDmg - armorReduction);
            }
        }

        if (event.getEntity() instanceof ShachathEntity) {

            ShachathEntity shachath = (ShachathEntity) event.getEntity();
            int shouldDodge = shachath.getRandom().nextInt(3);

            if (!dsFire && !dsVoid && !dsFire2) {
                if ((shouldDodge == 1) || (shouldDodge == 2)) {
                    shachath.teleportRandomly();
                    event.setCanceled(true);
                }
            }
        }

        MobCapEventHelper.onEntityHurt(event);
    }

    @SubscribeEvent
    public static void onProjectileHit(ProjectileImpactEvent event) {

        ArrowMixinHelper.onProjectileImpact(event);
        AlphemKingSpawnHelper.onProjectileImpact(event);

        Entity projectile = event.getEntity();
        HitResult result = event.getRayTraceResult();

        if (result instanceof EntityHitResult) {

            //=================================================
            //          Totem of Swerving Skill
            //=================================================
            boolean targetIsEntity = ((EntityHitResult) result).getEntity() instanceof LivingEntity;

            if (projectile instanceof AbstractArrow) {
                ((AbstractArrow) projectile).setPierceLevel((byte) 0);
            }

            if (!projectile.level.isClientSide && targetIsEntity) {
                LivingEntity user = ((LivingEntity) ((EntityHitResult) result).getEntity());

                int shouldDodge = user.getRandom().nextInt(3);

                if ((user.getOffhandItem().getItem() instanceof SwervingTotemItem) || (user.getMainHandItem().getItem() instanceof SwervingTotemItem)) {

                    if (shouldDodge == 1) {

                        boolean isNegVal = user.getRandom().nextBoolean();
                        int tpLoc = 1 + user.getRandom().nextInt(2);

                        user.level.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.GHAST_SHOOT, SoundSource.PLAYERS, 1, 1);
                        if (user.level instanceof ServerLevel) {
                            ((ServerLevel) user.level).sendParticles(ParticleTypes.POOF, user.getX(), user.getY(), user.getZ(), 50, 0.65, 0.65, 0.65, 0.01);
                        }

                        BlockPos blockpos = new BlockPos(user.getX() + (isNegVal ? tpLoc : -tpLoc), user.getY(), user.getZ() + (isNegVal ? tpLoc : -tpLoc));

                        if (!user.level.getBlockState(blockpos).getMaterial().blocksMotion()) {
                            user.teleportTo(blockpos.getX(), blockpos.getY(), blockpos.getZ());

                            event.setCanceled(true);
                        }
                    }
                }
            }

            //=================================================
            //            Mob Special Skills
            //=================================================
            boolean targetIsAltyrus = ((EntityHitResult) result).getEntity() instanceof AltyrusEntity;

            if (projectile instanceof AbstractArrow) {
                ((AbstractArrow) projectile).setPierceLevel((byte) 0);
            }

            if (!projectile.level.isClientSide() && targetIsAltyrus) {
                AltyrusEntity altyrus = ((AltyrusEntity) ((EntityHitResult) result).getEntity());
                int shouldDodge = altyrus.getRandom().nextInt(3);

                if (shouldDodge == 1 && !altyrus.isCharging() && !altyrus.isSummoning()) {

                    Vec3 projectilePos = event.getEntity().position();
                    Vec3 rVec = altyrus.getLookAngle().yRot(0.5F + (float) Math.PI).add(altyrus.position());
                    Vec3 lVec = altyrus.getLookAngle().yRot(0.5F + (float) Math.PI).add(altyrus.position());
                    BlockPos pos = new BlockPos((int) altyrus.getX(), (int) altyrus.getY(), (int) altyrus.getZ());

                    boolean rDir;

                    if (projectilePos.distanceTo(rVec) < projectilePos.distanceTo(lVec)) {
                        rDir = true;
                    } else if (projectilePos.distanceTo(rVec) > projectilePos.distanceTo(lVec)) {
                        rDir = false;
                    } else {
                        rDir = altyrus.getRandom().nextBoolean();
                    }

                    Vec3 vectorThingy = event.getEntity().getDeltaMovement().yRot((float) ((rDir ? 0.5F : -0.5F) * Math.PI)).normalize();

                    altyrus.setDodging(true);
                    altyrus.level.playSound(null, pos, altyrus.getDodgeSound(), SoundSource.HOSTILE, (float) 1, (float) 1);
                    altyrus.setDeltaMovement(altyrus.getDeltaMovement().add(vectorThingy.x() * 0.5F, 0, vectorThingy.z() * 0.5F));

                    event.setCanceled(true);
                }
            }

            boolean targetIsShachath = ((EntityHitResult) result).getEntity() instanceof ShachathEntity;

            if (!projectile.level.isClientSide && targetIsShachath) {
                ShachathEntity shachath = ((ShachathEntity) ((EntityHitResult) result).getEntity());
                int shouldDodge = shachath.getRandom().nextInt(3);

                if (projectile instanceof AbstractArrow) {
                    ((AbstractArrow) projectile).setPierceLevel((byte) 0);
                }

                if ((shouldDodge == 1) || (shouldDodge == 2)) {

                    Vec3 projectilePos = event.getEntity().position();
                    Vec3 rVec = shachath.getLookAngle().yRot(0.5F + (float) Math.PI).add(shachath.position());
                    Vec3 lVec = shachath.getLookAngle().yRot(0.5F + (float) Math.PI).add(shachath.position());
                    BlockPos pos = new BlockPos((int) shachath.getX(), (int) shachath.getY(), (int) shachath.getZ());

                    boolean rDir;

                    if (projectilePos.distanceTo(rVec) < projectilePos.distanceTo(lVec)) {
                        rDir = true;
                    } else if (projectilePos.distanceTo(rVec) > projectilePos.distanceTo(lVec)) {
                        rDir = false;
                    } else {
                        rDir = shachath.getRandom().nextBoolean();
                    }

                    Vec3 vectorThingy = event.getEntity().getDeltaMovement().yRot((float) ((rDir ? 0.5F : -0.5F) * Math.PI)).normalize();

                    shachath.setDeltaMovement(shachath.getDeltaMovement().add(vectorThingy.x() * 1F, 0, vectorThingy.z() * 1F));

                    event.setCanceled(true);
                }
            }

            boolean targetIsPrimordialRing = ((EntityHitResult) result).getEntity() instanceof PrimordialRingEntity;

            if (!projectile.level.isClientSide && targetIsPrimordialRing) {
                PrimordialRingEntity primordialRing = ((PrimordialRingEntity) ((EntityHitResult) result).getEntity());

                if (projectile instanceof AbstractArrow) {
                    ((AbstractArrow) projectile).setPierceLevel((byte) 0);
                }

                Vec3 projectilePos = event.getEntity().position();
                Vec3 rVec = primordialRing.getLookAngle().yRot(0.5F + (float) Math.PI).add(primordialRing.position());
                Vec3 lVec = primordialRing.getLookAngle().yRot(0.5F + (float) Math.PI).add(primordialRing.position());

                boolean rDir;

                if (projectilePos.distanceTo(rVec) < projectilePos.distanceTo(lVec)) {
                    rDir = true;
                } else if (projectilePos.distanceTo(rVec) > projectilePos.distanceTo(lVec)) {
                    rDir = false;
                } else {
                    rDir = primordialRing.getRandom().nextBoolean();
                }

                Vec3 vectorThingy = event.getEntity().getDeltaMovement().yRot((float) ((rDir ? 0.5F : -0.5F) * Math.PI)).normalize();

                primordialRing.setDeltaMovement(primordialRing.getDeltaMovement().add(vectorThingy.x() * 1F, 0, vectorThingy.z() * 1F));

                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        BlockPos pos = event.getPos();
        Level world = event.getLevel();
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        Player player = event.getEntity();

        if (player.getMainHandItem().getItem() instanceof ExperimentalItem) {
            BlockUtils.knockUpRandomNearbyBlocks(world, 0.5D, pos, 4, 2, 4, false, true);
        }

        if (block == BPBlocks.FIERY_BASALT_SPELEOTHERM.get()) {
            if (player.getMainHandItem().getItem() == Items.GLASS_BOTTLE) {
                player.swing(InteractionHand.MAIN_HAND);
                player.addItem(new ItemStack(BPItems.FIERY_SAP_BOTTLE.get()));
                world.setBlock(pos, BPBlocks.BASALT_SPELEOTHERM_PLANT.get().defaultBlockState(), 2);
                player.getMainHandItem().shrink(1);
            } else if (player.getOffhandItem().getItem() == Items.GLASS_BOTTLE) {
                player.swing(InteractionHand.OFF_HAND);
                player.addItem(new ItemStack(BPItems.FIERY_SAP_BOTTLE.get()));
                world.setBlock(pos, BPBlocks.BASALT_SPELEOTHERM_PLANT.get().defaultBlockState(), 2);
                player.getOffhandItem().shrink(1);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        RenderEventHelper.onPlayerTick(event);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        RenderEventHelper.onCameraSetup(event);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRenderingPlayer(RenderPlayerEvent event) {
        RenderEventHelper.onRenderingPlayer(event);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRenderingOverlay(RenderGuiOverlayEvent.Pre event) {
        RenderEventHelper.onRenderingOverlay(event);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onFogDensity(ViewportEvent.RenderFog event) {
        RenderEventHelper.onFogDensity(event);
    }
}
