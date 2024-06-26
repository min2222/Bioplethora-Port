package io.github.bioplethora.world.features;

import com.mojang.serialization.Codec;

import io.github.bioplethora.world.featureconfigs.PendentBlocksFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class PendentBlocksFeature extends Feature<PendentBlocksFeatureConfig> {
    private static final Direction[] DIRECTIONS = Direction.values();

    public PendentBlocksFeature(Codec<PendentBlocksFeatureConfig> config) {
        super(config);
    }

    public boolean place(FeaturePlaceContext<PendentBlocksFeatureConfig> context) {
    	WorldGenLevel world = context.level();
    	BlockPos pos = context.origin();
    	PendentBlocksFeatureConfig config = context.config();
    	RandomSource rand = context.random();
        if (!world.isEmptyBlock(pos)) {
            return false;
        } else {
            BlockState blockstate = world.getBlockState(pos.above());
            if (!config.getWhitelist().contains(blockstate.getBlock())) {
                return false;
            } else {
                this.generateTopPart(world, rand, pos, config);
                this.generatePendentsInSurroundings(world, rand, pos, config);
                return true;
            }
        }
    }

    protected void generateTopPart(LevelAccessor world, RandomSource rand, BlockPos pos, PendentBlocksFeatureConfig config) {
        world.setBlock(pos, config.getTopBlockProvider().getState(rand, pos), 2);
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos mutable2 = new BlockPos.MutableBlockPos();

        for (int i = 0; i < 200; ++i) {
            mutable.setWithOffset(pos, rand.nextInt(6) - rand.nextInt(6), rand.nextInt(2) - rand.nextInt(5), rand.nextInt(6) - rand.nextInt(6));
            if (world.isEmptyBlock(mutable)) {
                int j = 0;

                for (Direction direction : DIRECTIONS) {
                    BlockState blockstate = world.getBlockState(mutable2.setWithOffset(mutable, direction));
                    if (config.getWhitelist().contains(blockstate.getBlock())) {
                        ++j;
                    }

                    if (j > 1) {
                        break;
                    }
                }

                if (j == 1) {
                    world.setBlock(mutable, config.getTopBlockProvider().getState(rand, mutable), 2);
                }
            }
        }

    }

    protected void generatePendentsInSurroundings(LevelAccessor world, RandomSource rand, BlockPos pos, PendentBlocksFeatureConfig config) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < 100; ++i) {
            mutable.setWithOffset(pos, rand.nextInt(8) - rand.nextInt(8), rand.nextInt(2) - rand.nextInt(7), rand.nextInt(8) - rand.nextInt(8));
            if (world.isEmptyBlock(mutable)) {
                BlockState blockstate = world.getBlockState(mutable.above());
                if (config.getWhitelist().contains(blockstate.getBlock())) {
                    int length = Mth.nextInt(rand, config.getMinimalYSize(), config.getMaximalYSize());
                    if (rand.nextInt(6) == 0) {
                        length *= 2;
                    }

                    if (rand.nextInt(5) == 0) {
                        length = 1;
                    }
                    generateLength(world, mutable, length, rand, config);
                }
            }
        }

    }

    public static void generateLength(LevelAccessor world, BlockPos.MutableBlockPos mutable, int length, RandomSource rand, PendentBlocksFeatureConfig config) {
        for (int i = 0; i <= length; ++i) {
            if (world.isEmptyBlock(mutable)) {
                if (i == length || !world.isEmptyBlock(mutable.below())) {
                    world.setBlock(mutable, config.getEndBlockProvider().getState(rand, mutable), 2);
                    break;
                }

                int k = rand.nextInt(4);
                if (k == 1) {
                    world.setBlock(mutable, config.getFruitedBlockProvider().getState(rand, mutable), 2);
                } else {
                    world.setBlock(mutable, config.getMiddleBlockProvider().getState(rand, mutable), 2);
                }
            }

            mutable.move(Direction.DOWN);
        }
    }
}
