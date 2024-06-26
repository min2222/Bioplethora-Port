package io.github.bioplethora.world;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.registry.BPEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Used for spawning Bioplethora's mobs in vanilla biomes, spawning in Bioplethora's biomes will be handled inside the biome class itself.
 */
public class EntitySpawnManager {
    public static class BioplethoraMobSpawns {

        static Integer spawnMultiplier = BPConfig.COMMON.mobSpawnWeightMultiplier.get();
        static MobCategory creature = MobCategory.CREATURE;
        static MobCategory monster = MobCategory.MONSTER;
        static MobCategory ambient = MobCategory.AMBIENT;
        static MobCategory waterCreature = MobCategory.WATER_CREATURE;
        static MobCategory waterAmbient = MobCategory.WATER_AMBIENT;

        public static final Consumer<MobSpawnSettings.Builder> OVERWORLD_ENTITIES = (builder) -> {
            // Cavern Fleignar
            if (BPConfig.COMMON.spawnCavernFleignar.get()) {
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.CAVERN_FLEIGNAR.get(), 120 * spawnMultiplier, 4, 6));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> DESERT_ENTITIES = (builder) -> {
            // Nandbri
            if(BPConfig.COMMON.spawnNandbri.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.NANDBRI.get(), 3 * spawnMultiplier, 1, 1));
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.NANDBRI.get(), 30 * spawnMultiplier, 1, 1));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> SWAMP_ENTITIES = (builder) -> {
            // Trapjaw
            if (BPConfig.COMMON.spawnTrapjaw.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.TRAPJAW.get(), 5 * spawnMultiplier, 1, 1));
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.TRAPJAW.get(), 14 * spawnMultiplier, 1, 1));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> FOREST_ENTITIES = (builder) -> {
            //Crephoxl
            if (BPConfig.COMMON.spawnCrephoxl.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.CREPHOXL.get(), 10 * spawnMultiplier, 1, 1));
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.CREPHOXL.get(), 7 * spawnMultiplier, 1, 1));
            }

            //Alphem
            if (BPConfig.COMMON.spawnAlphem.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.ALPHEM.get(), 18 * spawnMultiplier, 4, 10));
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.ALPHEM.get(), 5 * spawnMultiplier, 4, 10));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> JUNGLE_ENTITIES = (builder) -> {
            //Crephoxl
            if (BPConfig.COMMON.spawnCrephoxl.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.CREPHOXL.get(), 10 * spawnMultiplier, 1, 1));
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.CREPHOXL.get(), 7 * spawnMultiplier, 1, 1));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> TAIGA_ENTITIES = (builder) -> {
            //FrostbiteGolem
            if (BPConfig.COMMON.spawnFrostbiteGolem.get()) {
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.FROSTBITE_GOLEM.get(), 5 * spawnMultiplier, 1, 1));
            }

            //Crephoxl
            if (BPConfig.COMMON.spawnCrephoxl.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.CREPHOXL.get(), 10 * spawnMultiplier, 1, 1));
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.CREPHOXL.get(), 7 * spawnMultiplier, 1, 1));
            }

            //Peaguin
            if (BPConfig.COMMON.spawnPeaguin.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.PEAGUIN.get(), 15 * spawnMultiplier, 3, 6));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> ICY_ENTITIES = (builder) -> {
            //FrostbiteGolem
            if (BPConfig.COMMON.spawnFrostbiteGolem.get()) {
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.FROSTBITE_GOLEM.get(), 5 * spawnMultiplier, 1, 1));
            }

            //Peaguin
            if (BPConfig.COMMON.spawnPeaguin.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.PEAGUIN.get(), 25 * spawnMultiplier, 3, 6));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> SAVANNA_ENTITIES = (builder) -> {
            //Alphem
            if (BPConfig.COMMON.spawnAlphem.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.ALPHEM.get(), 18 * spawnMultiplier, 4, 10));
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.ALPHEM.get(), 5 * spawnMultiplier, 4, 10));
            }

            //Dwarf Mossadile
            if (BPConfig.COMMON.spawnDwarfMossadile.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.DWARF_MOSSADILE.get(), 30 * BPConfig.COMMON.mobSpawnWeightMultiplier.get(), 4, 5));
            }

            // Trapjaw
            if(BPConfig.COMMON.spawnTrapjaw.get()) {
                builder.addSpawn(creature, new MobSpawnSettings.SpawnerData(BPEntities.TRAPJAW.get(), 4 * spawnMultiplier, 1, 1));
                builder.addSpawn(monster, new MobSpawnSettings.SpawnerData(BPEntities.TRAPJAW.get(), 14 * spawnMultiplier, 1, 1));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> RIVER_ENTITIES = (builder) -> {
            //Cuttlefish
            if (BPConfig.COMMON.spawnCuttlefish.get()) {
                builder.addSpawn(waterCreature, new MobSpawnSettings.SpawnerData(BPEntities.CUTTLEFISH.get(), 40 * BPConfig.COMMON.mobSpawnWeightMultiplier.get(), 1, 4));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> OCEAN_ENTITIES = (builder) -> {
            //Triggerfish
            if (BPConfig.COMMON.spawnTriggerfish.get()) {
                builder.addSpawn(waterAmbient, new MobSpawnSettings.SpawnerData(BPEntities.TRIGGERFISH.get(), 15 * BPConfig.COMMON.mobSpawnWeightMultiplier.get(), 1, 5));
            }

            //Myliothan
            if (BPConfig.COMMON.spawnMyliothan.get()) {
                builder.addSpawn(waterCreature, new MobSpawnSettings.SpawnerData(BPEntities.MYLIOTHAN.get(), 5 * BPConfig.COMMON.mobSpawnWeightMultiplier.get(), 1, 1));
            }
        };

        public static final Consumer<MobSpawnSettings.Builder> BASALT_DELTAS_ENTITIES = (builder) -> {
            //Fiery Eurydn
            createSpawn(builder, monster, BPEntities.FIERY_EURYDN, 8, 1, 3, BPConfig.COMMON.spawnFieryEurydn);
        };

        public static final Consumer<MobSpawnSettings.Builder> NETHER_WASTES_ENTITIES = (builder) -> {
            //Dwarf Mossadile
            createSpawn(builder, monster, BPEntities.DWARF_MOSSADILE, 45, 2, 3, BPConfig.COMMON.spawnDwarfMossadile);
        };

        public static final Consumer<MobSpawnSettings.Builder> WARPED_FOREST_ENTITIES = (builder) -> {
            //Dwarf Mossadile
            createSpawn(builder, monster, BPEntities.DWARF_MOSSADILE, 1, 1, 2, BPConfig.COMMON.spawnDwarfMossadile);
        };

        public static final Consumer<MobSpawnSettings.Builder> CRIMSON_FOREST_ENTITIES = (builder) -> {
            //Dwarf Mossadile
            createSpawn(builder, monster, BPEntities.DWARF_MOSSADILE, 4, 1, 3, BPConfig.COMMON.spawnDwarfMossadile);
        };

        public static final Consumer<MobSpawnSettings.Builder> SOUL_SAND_VALLEY_ENTITIES = (builder) -> {
            //Soul Eurydn
            createSpawn(builder, monster, BPEntities.SOUL_EURYDN, 6, 1, 3, BPConfig.COMMON.spawnSoulEurydn);
        };

        public static final Consumer<MobSpawnSettings.Builder> END_ENTITIES = (builder) -> {
            //Voidjaw
            createSpawn(builder, monster, BPEntities.VOIDJAW, 60, 1, 1, BPConfig.COMMON.spawnVoidjaw);

            //Gaugalem
            createSpawn(builder, monster, BPEntities.GAUGALEM, 50, 1, 1, BPConfig.COMMON.spawnGaugalem);

            //Onofish
            createSpawn(builder, monster, BPEntities.ONOFISH, 80, 2, 2, BPConfig.COMMON.spawnOnofish);

            //Triggerfish
            createSpawn(builder, monster, BPEntities.TRIGGERFISH, 5, 3, 5, BPConfig.COMMON.spawnTriggerfish);
        };
    }

    public static void createSpawn(MobSpawnSettings.Builder builder, MobCategory classification, Supplier<? extends EntityType<?>> entity, int initWeight, int minSpawn, int maxSpawn, @Nullable ForgeConfigSpec.ConfigValue<Boolean> config) {
        if (config != null) {
            if (config.get()) {
                builder.addSpawn(classification, new MobSpawnSettings.SpawnerData(entity.get(), initWeight * BPConfig.COMMON.mobSpawnWeightMultiplier.get(), minSpawn, maxSpawn));
            }
        } else {
            builder.addSpawn(classification, new MobSpawnSettings.SpawnerData(entity.get(), initWeight * BPConfig.COMMON.mobSpawnWeightMultiplier.get(), minSpawn, maxSpawn));
        }
    }
}
