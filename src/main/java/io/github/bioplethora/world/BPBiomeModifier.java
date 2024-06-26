package io.github.bioplethora.world;

import com.mojang.serialization.Codec;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.registry.BPParticles;
import io.github.bioplethora.registry.worldgen.BPPlacedFeatures;
import io.github.bioplethora.world.EntitySpawnManager.BioplethoraMobSpawns;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BPBiomeModifier implements BiomeModifier {
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(Bioplethora.MOD_ID, "bp_modifiers"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Bioplethora.MOD_ID);

    public BPBiomeModifier() {
    	
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
        	if(biome.is(BiomeTags.IS_OVERWORLD)) {
        		BioplethoraMobSpawns.OVERWORLD_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && biome.is(BiomeTags.IS_FOREST)) {
        		BioplethoraMobSpawns.FOREST_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && biome.is(Tags.Biomes.IS_DRY)) {
        		BioplethoraMobSpawns.DESERT_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && biome.is(Tags.Biomes.IS_SWAMP)) {
        		BioplethoraMobSpawns.SWAMP_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && biome.is(BiomeTags.IS_JUNGLE)) {
        		BioplethoraMobSpawns.JUNGLE_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && (biome.is(Tags.Biomes.IS_CONIFEROUS) || biome.is(Tags.Biomes.IS_COLD))) {
        		BioplethoraMobSpawns.TAIGA_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && biome.is(Tags.Biomes.IS_COLD)) {
        		BioplethoraMobSpawns.ICY_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && biome.is(BiomeTags.IS_SAVANNA)) {
        		BioplethoraMobSpawns.SAVANNA_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && biome.is(BiomeTags.IS_RIVER)) {
        		BioplethoraMobSpawns.RIVER_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_OVERWORLD) && biome.is(BiomeTags.IS_OCEAN)) {
        		BioplethoraMobSpawns.OCEAN_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	if(biome.is(BiomeTags.IS_NETHER)) {
        		 if(biome.is(Biomes.BASALT_DELTAS)) {
        			 BioplethoraMobSpawns.BASALT_DELTAS_ENTITIES.accept(builder.getMobSpawnSettings());
        		 }
        		 if(biome.is(Biomes.NETHER_WASTES)) {
        			 BioplethoraMobSpawns.NETHER_WASTES_ENTITIES.accept(builder.getMobSpawnSettings());
        		 }
        		 if(biome.is(Biomes.WARPED_FOREST)) {
        			 BioplethoraMobSpawns.WARPED_FOREST_ENTITIES.accept(builder.getMobSpawnSettings());
        		 }
        		 if(biome.is(Biomes.CRIMSON_FOREST)) {
        			 BioplethoraMobSpawns.CRIMSON_FOREST_ENTITIES.accept(builder.getMobSpawnSettings());
        		 }
        		 if(biome.is(Biomes.SOUL_SAND_VALLEY)) {
        			 BioplethoraMobSpawns.SOUL_SAND_VALLEY_ENTITIES.accept(builder.getMobSpawnSettings());
        		 }
        	}
        	if(biome.is(BiomeTags.IS_END)) {
        		BioplethoraMobSpawns.END_ENTITIES.accept(builder.getMobSpawnSettings());
        	}
        	
        	if(biome.is(BiomeTags.IS_OVERWORLD)) {
        		builder.getGenerationSettings().addFeature(Decoration.UNDERGROUND_DECORATION, BPPlacedFeatures.FLEIGNARITE_REMAINS.getHolder().get());
        		builder.getGenerationSettings().addFeature(Decoration.UNDERGROUND_DECORATION, BPPlacedFeatures.FLEIGNARITE_VINES.getHolder().get());
        		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.FLEIGNARITE_REMAINS.getHolder().get());
        		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.FLEIGNARITE_VINES.getHolder().get());
        	}
        	if(biome.is(BiomeTags.IS_NETHER)) {
        		if(biome.is(Biomes.BASALT_DELTAS)) {
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.BASALT_SPELEOTHERM.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.LAVA_SPIRE.getHolder().get());
        		}
        		if(biome.is(Biomes.NETHER_WASTES)) {
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.THONTUS_THISTLE.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.LAVA_SPIRE.getHolder().get());
        		}
        		if(biome.is(Biomes.WARPED_FOREST)) {
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.WARPED_DANCER.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.TURQUOISE_PENDENT.getHolder().get());
        		}
        		if(biome.is(Biomes.CRIMSON_FOREST)) {
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CERISE_IVY.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.LAVA_SPIRE.getHolder().get());
        		}
        		if(biome.is(Biomes.SOUL_SAND_VALLEY)) {
        			
        			builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.SOUL_MINISHROOM.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.SOUL_BIGSHROOM.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.SOUL_SPROUTS.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.SOUL_TALL_GRASS.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.SPIRIT_DANGLER.getHolder().get());
            		builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.SOUL_ETERN.getHolder().get());
        		}
        	}
        	if(biome.is(Biomes.THE_END)) {
                if (BPConfig.WORLDGEN.cyraLakesEnd.get()) builder.getGenerationSettings().addFeature(Decoration.UNDERGROUND_DECORATION, BPPlacedFeatures.CYRA_LAKE.getHolder().get());
                if (!BPConfig.WORLDGEN.createNewSpongeBiome.get()) {
                    if (biome.is(Biomes.END_HIGHLANDS)) {
                        builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CHORUS_MYCHRODEGIA.getHolder().get());
                        builder.getSpecialEffects().waterColor(-6599759)
                        .waterFogColor(-13158998)
                        .fogColor(-12378263)
                        .skyColor(-12378263)
                        .ambientParticle(new AmbientParticleSettings(BPParticles.NIGHT_GAZE.get(), 0.04F))
                        .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD)
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D))
                        .build();

                        if (BPConfig.WORLDGEN.chorusVegetationHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.TOP_LAYER_MODIFICATION, BPPlacedFeatures.ENREDE_KELP.getHolder().get());

                        if (BPConfig.WORLDGEN.chorusLanternHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CHORUS_LANTERN_HIGHLANDS_PATCH.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CHORUS_IDON.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CHORUS_IDE_FAN.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.ENREDE_CORSASCILE.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.OCHAIM_PURPLE.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.OCHAIM_RED.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.OCHAIM_GREEN.getHolder().get());

                        if (BPConfig.WORLDGEN.endSpikeHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.LOCAL_MODIFICATIONS, BPPlacedFeatures.END_LAND_SPIKE_PATCH_HL.getHolder().get());
                        
                        if (BPConfig.WORLDGEN.endSpongeHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.LOCAL_MODIFICATIONS, BPPlacedFeatures.END_LAND_SPONGE_PATCH_HL.getHolder().get());
                    }

                    if (biome.is(Biomes.END_MIDLANDS) || biome.is(Biomes.END_BARRENS)) {
                        builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CHORUS_MYCHRODEGIA.getHolder().get());
                        builder.getSpecialEffects()
                        .waterColor(-6599759)
                        .waterFogColor(-13158998)
                        .fogColor(-12378263)
                        .skyColor(-12378263)
                        .ambientParticle(new AmbientParticleSettings(BPParticles.NIGHT_GAZE.get(), 0.03F))
                        .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD)
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D))
                        .build();

                        if (BPConfig.WORLDGEN.chorusVegetationHighlands.get()) builder.getGenerationSettings().addFeature(Decoration.TOP_LAYER_MODIFICATION, BPPlacedFeatures.ENREDE_KELP.getHolder().get());

                        if (BPConfig.WORLDGEN.chorusLanternMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CHORUS_LANTERN_MIDLANDS_PATCH.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CHORUS_IDON.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.CHORUS_IDE_FAN.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.ENREDE_KELP.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.ENREDE_CORSASCILE.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.OCHAIM_PURPLE.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.OCHAIM_RED.getHolder().get());
                        if (BPConfig.WORLDGEN.chorusVegetationMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.VEGETAL_DECORATION, BPPlacedFeatures.OCHAIM_GREEN.getHolder().get());

                        if (BPConfig.WORLDGEN.endSpikeMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.LOCAL_MODIFICATIONS, BPPlacedFeatures.END_LAND_SPIKE_PATCH_ML.getHolder().get());
                        if (BPConfig.WORLDGEN.endSpongeMidlands.get()) builder.getGenerationSettings().addFeature(Decoration.LOCAL_MODIFICATIONS, BPPlacedFeatures.END_LAND_SPONGE_PATCH_ML.getHolder().get());

                    }
                }
        	}
        }
    }
    
    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<BPBiomeModifier> makeCodec() {
        return Codec.unit(BPBiomeModifier::new);
    }
}
