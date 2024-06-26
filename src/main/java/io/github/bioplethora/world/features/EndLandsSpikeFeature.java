package io.github.bioplethora.world.features;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.Vec3;

public class EndLandsSpikeFeature extends Feature<NoneFeatureConfiguration> {

    public EndLandsSpikeFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
    	WorldGenLevel world = pContext.level();
    	BlockPos pos = pContext.origin();
    	
        boolean large = world.getRandom().nextInt(5) == 0;
        int tipMin = (int) ((large ? 25 : 10) * 0.7);
        int tipRand = (int) ((large ? 35 : 20) * 0.6);
        int radiusMin = large ? 4 : 2;
        int radiusRand = large ? 6 : 3;

        pos = new BlockPos(pos.getX(), 2, pos.getZ());
        while (world.isEmptyBlock(pos) && pos.getY() < 100) {
            pos = pos.above();
        }

        if (!world.getBlockState(pos.above()).is(Blocks.END_STONE)) {
            return false;
        }

        int tip = tipMin + world.getRandom().nextInt(tipRand);
        int topX = world.getRandom().nextInt(tip) - tip / 2;
        int topZ = world.getRandom().nextInt(tip) - tip / 2;

        int radius = radiusMin + world.getRandom().nextInt(radiusRand);
        Vec3 to = new Vec3(pos.getX() + topX, pos.getY() - tip, pos.getZ() + topZ);

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double fromCenter = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
                if(fromCenter <= radius) {
                    Vec3 from = new Vec3(pos.getX() + x, pos.getY(), pos.getZ() + z);

                    if(world.getBlockState(new BlockPos(from).below()).isAir()) {
                        continue;
                    }

                    Vec3 per = to.subtract(from).normalize();
                    Vec3 current = from.add(0, 0, 0);
                    double distance = from.distanceTo(to);

                    for (double i = 0; i < distance; i++) {
                        BlockPos targetPos = new BlockPos(current);
                        world.setBlock(targetPos, Blocks.END_STONE.defaultBlockState(), 3);
                        current = current.add(per);
                    }
                }
            }
        }
        return true;
    }
}
