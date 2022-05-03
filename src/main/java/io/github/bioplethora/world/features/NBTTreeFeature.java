package io.github.bioplethora.world.features;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import io.github.bioplethora.Bioplethora;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public abstract class NBTTreeFeature extends Feature<NoFeatureConfig> {

    public NBTTreeFeature(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public abstract ImmutableList<String> getPossibleNBTTrees();

    public abstract boolean lowerYLevel(Random rand);

    public String getRandomNBTTree(Random rand) {
        return getPossibleNBTTrees().get(rand.nextInt(getPossibleNBTTrees().size()));
    }

    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {

        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(pos);

        int radius = 1;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (Math.abs(x * z) > radius && Math.abs(x * z) < radius * 2) {
                    blockpos$Mutable.set(pos).move(-x, -1, -z);
                    if (!world.getBlockState(blockpos$Mutable).canOcclude()) {
                        return false;
                    }
                }
            }
        }

        TemplateManager tManager = world.getLevel().getStructureManager();
        Template template = tManager.get(new ResourceLocation(Bioplethora.MOD_ID, "features/" + getRandomNBTTree(random)));

        if (template == null) {
            Bioplethora.LOGGER.warn("NBT does not exist!: " + new ResourceLocation(Bioplethora.MOD_ID, "features/" + getRandomNBTTree(random)));
            return false;
        }

        BlockPos halfOfNBT = new BlockPos(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
        BlockPos.Mutable placementLocation = lowerYLevel(random) ?
                blockpos$Mutable.set(pos).move(-halfOfNBT.getX(), -1, -halfOfNBT.getZ()) :
                blockpos$Mutable.set(pos).move(-halfOfNBT.getX(), 0, -halfOfNBT.getZ()
                );

        Rotation rotation = Rotation.getRandom(random);
        PlacementSettings placementsettings = new PlacementSettings().setRotation(rotation).setRotationPivot(halfOfNBT).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR).setIgnoreEntities(true);
        template.placeInWorldChunk(world, placementLocation, placementsettings, random);
        return true;
    }
}
