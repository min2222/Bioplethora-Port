package io.github.bioplethora.world.features;

import com.mojang.serialization.Codec;
import io.github.bioplethora.blocks.specific.EnredeKelpBlock;
import io.github.bioplethora.registry.BPBlocks;
import io.github.bioplethora.registry.worldgen.BPBiomes;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.fluid.Fluids;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class EndLandsSpongeFeature extends Feature<NoFeatureConfig> {

    public boolean isHighlands;

    public EndLandsSpongeFeature(Codec<NoFeatureConfig> pCodec, boolean isHighlands) {
        super(pCodec);
        this.isHighlands = isHighlands;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGen, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (isHighlands) {
            this.placeSponge(19 + rand.nextInt(12), 15, world, rand, pos);
        } else {
            this.placeSponge(15 + rand.nextInt(9), 7, world, rand, pos);
        }
        return true;
    }

    public void placeSponge(int radius, int randomYHeight, ISeedReader world, Random rand, BlockPos pos) {
        if (checkPlacement(world, pos)) {
            int yRand = 8 + rand.nextInt(randomYHeight);
            double radHelper = radius;
            for (int sy = -yRand; sy <= 0; sy++) {
                radHelper -= 0.5D;
                radius = (int) Math.abs(radHelper);
                addCircle(radius, world, rand, pos, sy, -yRand);
            }
        }
    }

    public void addCircle(int radius, ISeedReader world, Random rand, BlockPos pos, int sy, int minY) {
        for (int sx = -radius; sx <= radius; sx++) {
            for (int sz = -radius; sz <= radius; sz++) {
                if (sx * sx + sz * sz <= radius * radius) {
                    BlockPos.Mutable newPos = pos.offset(sx, sy, sz).mutable();
                    if (world.isEmptyBlock(newPos) || world.getBlockState(newPos).getBlock() instanceof LeavesBlock) {
                        setBlock(world, newPos, BPBlocks.ENDURION.get().defaultBlockState());
                    }
                }
            }
        }
        int rad2 = (radius - 1);
        for (int sx = -rad2; sx <= rad2; sx++) {
            for (int sz = -rad2; sz <= rad2; sz++) {
                if (sx * sx + sz * sz <= rad2 * rad2) {
                    BlockPos.Mutable newPos2 = pos.offset(sx, sy, sz).mutable();
                    if (sy == minY) {
                        if (world.isEmptyBlock(newPos2) || world.getBlockState(newPos2).getBlock() instanceof LeavesBlock) {
                            setBlock(world, newPos2, BPBlocks.ENDURION.get().defaultBlockState());
                        }
                    } else {
                        if (world.getBlockState(newPos2).getBlock() instanceof ILiquidContainer) {
                            ((ILiquidContainer) world.getBlockState(newPos2).getBlock()).placeLiquid(world, pos, world.getBlockState(newPos2), Fluids.WATER.getSource(false));
                        } else {
                            if (world.getBlockState(newPos2.above()).getBlock() instanceof EnredeKelpBlock) {
                                setBlock(world, newPos2, BPBlocks.ENREDE_KELP_PLANT.get().defaultBlockState());
                            } else {
                                setBlock(world, newPos2, Blocks.WATER.defaultBlockState());
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkPlacement(ISeedReader world, BlockPos pos) {
        int checkRad = 1;
        for (int x = -checkRad; x < checkRad; x++) {
            for (int z = -checkRad; z < checkRad; z++) {
                BlockPos.Mutable checkPos = pos.mutable().move(x, -1, z);
                if (world.isEmptyBlock(checkPos) || world.getBlockState(checkPos).getBlock() instanceof LeavesBlock) {
                    return false;
                }
            }
        }
        if (BPBiomes.getKey(world.getBiome(pos)) == Biomes.SMALL_END_ISLANDS) {
            for (int y = -7; y < 0; y++) {
                if (world.getBlockState(pos.offset(0, y, 0)).isAir()) {
                    return false;
                }
            }
        }
        return (world.getBlockState(pos.offset(0, -1, 0)).getBlock() == Blocks.END_STONE ||
                world.getBlockState(pos.offset(0, -1, 0)).getBlock() == BPBlocks.ENDURION.get());
    }
}
