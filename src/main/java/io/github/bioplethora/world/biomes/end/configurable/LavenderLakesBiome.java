package io.github.bioplethora.world.biomes.end.configurable;

import io.github.bioplethora.registry.BPParticles;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;

public class LavenderLakesBiome {

    public static Biome make() {

        BiomeGenerationSettings.Builder biomeGenSettings = (new BiomeGenerationSettings.Builder());//.surfaceBuilder(surfaceBuilder);
        MobSpawnSettings.Builder mobspawninfo$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.endSpawns(mobspawninfo$builder);

        biomeGenSettings.addStructureStart(StructureFeatures.END_CITY);
        biomeGenSettings.addFeature(Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeGenSettings.addFeature(Decoration.VEGETAL_DECORATION, EndPlacements.CHORUS_PLANT);

        return(new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)//.biomeCategory(Biome.Category.THEEND).depth(0.1F).scale(0.2F)
                .temperature(0.5F).downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                                .waterColor(-6599759)
                                .waterFogColor(-13158998)
                                .fogColor(-12378263)
                                .skyColor(-12378263)
                                .ambientParticle(new AmbientParticleSettings(BPParticles.NIGHT_GAZE.get(), 0.04F))
                                .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD)
                                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D))
                                .build())
                .mobSpawnSettings(mobspawninfo$builder.build())
                .generationSettings(biomeGenSettings.build()).build();
    }
}
