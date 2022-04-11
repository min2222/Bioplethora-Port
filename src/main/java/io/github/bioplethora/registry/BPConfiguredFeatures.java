package io.github.bioplethora.registry;

import com.google.common.collect.ImmutableList;
import io.github.bioplethora.world.BPFeatureGeneration;
import io.github.bioplethora.world.feature_config.FleignariteSplotchConfig;
import io.github.bioplethora.world.feature_config.PendentBlocksFeatureConfig;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.Placement;

public class BPConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> FLEIGNARITE_REMAINS_CONFIG = BPFeatures.FLEIGNARITE_PATCH.get()
            .configured(new FleignariteSplotchConfig(BPBlocks.FLEIGNARITE_REMAINS.get().defaultBlockState(),
                    BPBlocks.FLEIGNARITE_SPLOTCH.get().defaultBlockState(),
                    BPFeatureGeneration.stoneBlockstates(),
                    ImmutableList.of(Blocks.CAVE_AIR.defaultBlockState(), Blocks.AIR.defaultBlockState()),
                    ImmutableList.of(Blocks.CAVE_AIR.defaultBlockState(), Blocks.AIR.defaultBlockState())))

                    .decorated(Placement.CARVING_MASK.configured(new CaveEdgeConfig(GenerationStage.Carving.AIR, 0.1F))
                            .count(FeatureSpread.of(14, 27))
                    );

    public static final ConfiguredFeature<?, ?> FLEIGNARITE_VINES_CONFIG = BPFeatures.PENDENT_FLEIGNARITE.get()
            .configured(new PendentBlocksFeatureConfig.Builder()
                    .setTopBlock(Blocks.STONE)
                    .setMiddleBlock(BPBlocks.FLEIGNARITE_VINES_PLANT.get())
                    .setFruitedBlock(BPBlocks.FLEIGNARITE_VINES_PLANT.get())
                    .setEndBlock(BPBlocks.FLEIGNARITE_VINES.get().defaultBlockState().setValue(AbstractTopPlantBlock.AGE, 23))
                    .setWhitelist(BPFeatureGeneration.stoneBlocks())
                    .setMinLength(1)
                    .setMaxLength(2)
                    .build())
            .range(128).squared().count(512);

    public static final ConfiguredFeature<?, ?> BASALT_SPELEOTHERM_CONFIG = BPFeatures.PENDENT_BLOCKS.get()
            .configured(new PendentBlocksFeatureConfig.Builder()
                    .setTopBlock(Blocks.BASALT)
                    .setMiddleBlock(BPBlocks.BASALT_SPELEOTHERM_PLANT.get())
                    .setFruitedBlock(BPBlocks.FIERY_BASALT_SPELEOTHERM.get())
                    .setEndBlock(BPBlocks.BASALT_SPELEOTHERM.get().defaultBlockState().setValue(AbstractTopPlantBlock.AGE, 23))
                    .setWhitelist(ImmutableList.of(Blocks.BASALT, Blocks.NETHERRACK, Blocks.BLACKSTONE))
                    .setMinLength(1)
                    .setMaxLength(8)
                    .build())
            .range(128).squared().count(128);
}
