package io.github.bioplethora.world;

/*import java.util.function.Supplier;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.api.world.LevelgenUtils;
import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.registry.BPParticles;
import io.github.bioplethora.registry.worldgen.BPConfiguredSurfaceBuilders;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

@Mod.EventBusSubscriber(modid = Bioplethora.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BiomeModificationManager {

    @SubscribeEvent
    public static void surfaceBuilderModification(final FMLServerAboutToStartEvent event) {
        MutableRegistry<Biome> biome = event.getServer().registryAccess().registryOrThrow(Registry.BIOME_REGISTRY);

        if (!BPConfig.WORLDGEN.createNewSpongeBiome.get()) {
            changeSurfaceBuilder(biome, Biomes.END_HIGHLANDS, () -> BPConfiguredSurfaceBuilders.ENDURION_SURFACE);
            changeSurfaceBuilder(biome, Biomes.END_MIDLANDS, () -> BPConfiguredSurfaceBuilders.ENDURION_SURFACE);
            changeSurfaceBuilder(biome, Biomes.SMALL_END_ISLANDS, () -> BPConfiguredSurfaceBuilders.ENDURION_SURFACE);
        }
    }

    @SubscribeEvent
    public static void changeOthers(final BiomeLoadingEvent event) {
        if (LevelgenUtils.getBiomeFromEvent(event, "end_highlands")) {
            event.setEffects(new BiomeSpecialEffects.Builder()
                    .waterColor(-6599759)
                    .waterFogColor(-13158998)
                    .fogColor(-12378263)
                    .skyColor(-12378263)
                    .ambientParticle(new AmbientParticleSettings(BPParticles.NIGHT_GAZE.get(), 0.04F))
                    .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD)
                    .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D))
                    .build());
        }
        if (LevelgenUtils.getBiomeFromEvent(event, "end_midlands") || LevelgenUtils.getBiomeFromEvent(event, "end_barrens")) {
            event.setEffects(new BiomeSpecialEffects.Builder()
                    .waterColor(-6599759)
                    .waterFogColor(-13158998)
                    .fogColor(-12378263)
                    .skyColor(-12378263)
                    .ambientParticle(new AmbientParticleSettings(BPParticles.NIGHT_GAZE.get(), 0.03F))
                    .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD)
                    .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D))
                    .build());
        }
    }

    public static void changeSurfaceBuilder(MutableRegistry<Biome> mutable, RegistryKey<Biome> biome, Supplier<ConfiguredSurfaceBuilder<?>> surface) {
        mutable.get(biome).getGenerationSettings().surfaceBuilder = surface;
    }
}*/
