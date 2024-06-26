package io.github.bioplethora.event.helper;

import io.github.bioplethora.api.advancements.AdvancementUtils;
import io.github.bioplethora.api.events.BPHooks;
import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.entity.creatures.GrylynenEntity;
import io.github.bioplethora.entity.others.GrylynenCoreBombEntity;
import io.github.bioplethora.registry.BPEffects;
import io.github.bioplethora.registry.BPEntities;
import io.github.bioplethora.registry.BPTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;

public class GrylynenSpawnHelper {

    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        LevelAccessor ILevel = event.getLevel();
        if ((ILevel instanceof Level) && !(event.getPlayer().hasEffect(BPEffects.SPIRIT_FISSION.get()))) {

            Level world = (Level) event.getLevel();
            double x = event.getPos().getX(), y = event.getPos().getY(), z = event.getPos().getZ();
            BlockPos pos = new BlockPos(x, y, z);

            if (!event.getPlayer().isCreative() && checkSpawnRules()) {

                if (getTaggedBlock(BPTags.Blocks.WOODEN_GRYLYNEN_SPAWNABLE, world, pos)) {
                    if (Math.random() < (!BPConfig.IN_HELLMODE ? 0.025 : 0.045) && BPConfig.COMMON.spawnWoodenGrylynen.get()) {
                        summonGrylynenCore(event.getPlayer(), BPEntities.WOODEN_GRYLYNEN.get().create(world), world, pos);
                    }
                }

                if (getTaggedBlock(BPTags.Blocks.STONE_GRYLYNEN_SPAWNABLE, world, pos)) {
                    if (Math.random() < (!BPConfig.IN_HELLMODE ? 0.025 : 0.045) && BPConfig.COMMON.spawnStoneGrylynen.get()) {
                        summonGrylynenCore(event.getPlayer(), BPEntities.STONE_GRYLYNEN.get().create(world), world, pos);
                    }
                }

                if (getTaggedBlock(BPTags.Blocks.GOLDEN_GRYLYNEN_SPAWNABLE, world, pos)) {
                    if (Math.random() < (!BPConfig.IN_HELLMODE ? 0.075 : 0.1) && BPConfig.COMMON.spawnGoldenGrylynen.get()) {
                        world.destroyBlock(pos, false);
                        summonGrylynenCore(event.getPlayer(), BPEntities.GOLDEN_GRYLYNEN.get().create(world), world, pos);
                    }
                }

                if (getTaggedBlock(BPTags.Blocks.IRON_GRYLYNEN_SPAWNABLE, world, pos)) {
                    if (Math.random() < (!BPConfig.IN_HELLMODE ? 0.075 : 0.1) && BPConfig.COMMON.spawnIronGrylynen.get()) {
                        world.destroyBlock(pos, false);
                        summonGrylynenCore(event.getPlayer(), BPEntities.IRON_GRYLYNEN.get().create(world), world, pos);
                    }
                }

                if (getTaggedBlock(BPTags.Blocks.DIAMOND_GRYLYNEN_SPAWNABLE, world, pos)) {
                    if (Math.random() < (!BPConfig.IN_HELLMODE ? 0.1 : 0.1025) && BPConfig.COMMON.spawnDiamondGrylynen.get()) {
                        world.destroyBlock(pos, false);
                        summonGrylynenCore(event.getPlayer(), BPEntities.DIAMOND_GRYLYNEN.get().create(world), world, pos);
                    }
                }

                if (getTaggedBlock(BPTags.Blocks.NETHERITE_GRYLYNEN_SPAWNABLE, world, pos)) {
                    if (Math.random() < (!BPConfig.IN_HELLMODE ? 0.1 : 0.1025) && BPConfig.COMMON.spawnNetheriteGrylynen.get()) {
                        world.destroyBlock(pos, false);
                        summonGrylynenCore(event.getPlayer(), BPEntities.NETHERITE_GRYLYNEN.get().create(world), world, pos);
                    }
                }
            }
        }
    }

    public static boolean checkSpawnRules() {
        if (BPConfig.COMMON.grylynenIsOnlyHellmode.get()) {
            return BPConfig.IN_HELLMODE;
        } else {
            return true;
        }
    }

    public static boolean getTaggedBlock(TagKey<Block> tag, Level world, BlockPos pos) {
        return world.getBlockState(pos).is(tag);
    }

    public static void summonGrylynenCore(Player summoner, GrylynenEntity grylynen, Level world, BlockPos centerPos) {

        if (BPHooks.onGrylynenSpawn(summoner)) return;

        GrylynenCoreBombEntity core = BPEntities.GRYLYNEN_CORE_BOMB.get().create(world);
        if (!(world.getDifficulty() == Difficulty.PEACEFUL)) {

            AdvancementUtils.grantBioAdvancement(summoner, "grylynen_summon");

            if (!world.isClientSide()) {
                ServerLevel serverworld = (ServerLevel) world;
                core.moveTo(centerPos, 0.0F, 0.0F);
                core.setTier(grylynen.getGrylynenTier());
                serverworld.addFreshEntity(core);
            }
        }
    }

    public static void breakSurroundingBlocks(Level world, BlockPos centerPos) {

        grylynenDestroyBlocks(world, centerPos, 1, 1, 1, true);
    }

    public static void destroyBlockAt(BlockPos pos, Level world) {
        if (!getTaggedBlock(BPTags.Blocks.GRYLYNEN_UNBREAKABLE, world, pos)) {
            world.destroyBlock(pos, true);
        }
    }

    public static void grylynenDestroyBlocks(Level world, BlockPos point, int radiusX, int radiusY, int radiusZ, boolean shouldDrop) {

        for (int radY = point.getY() - radiusX; radY <= point.getY() + radiusY; radY++) {
            for (int radX = point.getX() - radiusX; radX <= point.getX() + radiusX; radX++) {
                for (int radZ = point.getZ() - radiusZ; radZ <= point.getZ() + radiusZ; radZ++) {

                    BlockPos pos = new BlockPos(radX, radY, radZ);
                    BlockState blockState = world.getBlockState(pos);
                    Block block = blockState.getBlock();

                    destroyBlockAt(pos, world);
                }
            }
        }
    }
}
