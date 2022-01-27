package io.github.bioplethora.registry;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.entity.creatures.*;
import io.github.bioplethora.entity.others.AltyrusSummoningEntity;
import io.github.bioplethora.entity.others.BellophiteShieldWaveEntity;
import io.github.bioplethora.entity.projectile.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BioplethoraEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Bioplethora.MOD_ID);

    //Ecoharmless
    public static final RegistryObject<EntityType<CuttlefishEntity>> CUTTLEFISH = ENTITIES.register("cuttlefish", () -> EntityType.Builder.of(CuttlefishEntity::new, EntityClassification.WATER_CREATURE).sized(0.55f, 1.15f).build(new ResourceLocation(Bioplethora.MOD_ID, "cuttlefish").toString()));

    //Plethoneutral
    public static final RegistryObject<EntityType<PeaguinEntity>> PEAGUIN = ENTITIES.register("peaguin", () -> EntityType.Builder.of(PeaguinEntity::new, EntityClassification.WATER_CREATURE).sized(1.2f, 1.4f).build(new ResourceLocation(Bioplethora.MOD_ID, "peaguin").toString()));
    public static final RegistryObject<EntityType<NandbriEntity>> NANDBRI = ENTITIES.register("nandbri", () -> EntityType.Builder.of(NandbriEntity::new, EntityClassification.MONSTER).sized(2.6f, 1.15f).build(new ResourceLocation(Bioplethora.MOD_ID, "nandbri").toString()));

    //Dangerum
    public static final RegistryObject<EntityType<AlphemEntity>> ALPHEM = ENTITIES.register("alphem", () -> EntityType.Builder.of(AlphemEntity::new, EntityClassification.MONSTER).sized(1.2f, 1.4f).build(new ResourceLocation(Bioplethora.MOD_ID, "alphem").toString()));
    public static final RegistryObject<EntityType<GaugalemEntity>> GAUGALEM = ENTITIES.register("gaugalem", () -> EntityType.Builder.of(GaugalemEntity::new, EntityClassification.MONSTER).sized(1.2f, 4.0f).build(new ResourceLocation(Bioplethora.MOD_ID, "gaugalem").toString()));
    public static final RegistryObject<EntityType<DwarfMossadileEntity>> DWARF_MOSSADILE = ENTITIES.register("dwarf_mossadile", () -> EntityType.Builder.of(DwarfMossadileEntity::new, EntityClassification.MONSTER).sized(1.4f, 0.5f).build(new ResourceLocation(Bioplethora.MOD_ID, "dwarf_mossadile").toString()));

    //Hellsent
    public static final RegistryObject<EntityType<CrephoxlEntity>> CREPHOXL = ENTITIES.register("crephoxl", () -> EntityType.Builder.of(CrephoxlEntity::new, EntityClassification.MONSTER).sized(3.5f, 5f).build(new ResourceLocation(Bioplethora.MOD_ID, "crephoxl").toString()));
    public static final RegistryObject<EntityType<BellophgolemEntity>> BELLOPHGOLEM = ENTITIES.register("bellophgolem", () -> EntityType.Builder.of(BellophgolemEntity::new, EntityClassification.MONSTER).sized(3.5f, 4.75f).build(new ResourceLocation(Bioplethora.MOD_ID, "bellophgolem").toString()));
    public static final RegistryObject<EntityType<HeliobladeEntity>> HELIOBLADE = ENTITIES.register("helioblade", () -> EntityType.Builder.of(HeliobladeEntity::new, EntityClassification.MONSTER).sized(0.6f, 1.8f).build(new ResourceLocation(Bioplethora.MOD_ID, "helioblade").toString()));
    //public static final RegistryObject<EntityType<TelemreyeEntity>> TELEMREYE = ENTITIES.register("telemreye", () -> EntityType.Builder.of(TelemreyeEntity::new, EntityClassification.MONSTER).sized(2.6f, 1.15f).build(new ResourceLocation(Bioplethora.MOD_ID, "telemreye").toString()));

    //Elderia
    public static final RegistryObject<EntityType<AltyrusEntity>> ALTYRUS = ENTITIES.register("altyrus", () -> EntityType.Builder.of(AltyrusEntity::new, EntityClassification.MONSTER).sized(3.5f, 4.75f).build(new ResourceLocation(Bioplethora.MOD_ID, "altyrus").toString()));
    public static final RegistryObject<EntityType<MyliothanEntity>> MYLIOTHAN = ENTITIES.register("myliothan", () -> EntityType.Builder.of(MyliothanEntity::new, EntityClassification.MONSTER).sized(25.5f, 6f).build(new ResourceLocation(Bioplethora.MOD_ID, "myliothan").toString()));

    //others ---
    //Projectiles
    public static final RegistryObject<EntityType<BellophiteClusterEntity>> BELLOPHITE_CLUSTER = ENTITIES.register("bellophite_cluster", () -> EntityType.Builder.<BellophiteClusterEntity>of(BellophiteClusterEntity::new, EntityClassification.MISC).sized(2.0F, 2.0F).clientTrackingRange(4).updateInterval(20)
            .build(new ResourceLocation(Bioplethora.MOD_ID, "bellophite_cluster").toString()));
    public static final RegistryObject<EntityType<BellophiteArrowEntity>> BELLOPHITE_ARROW = ENTITIES.register("bellophite_arrow", () -> EntityType.Builder.<BellophiteArrowEntity>of(BellophiteArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20)
            .build(new ResourceLocation(Bioplethora.MOD_ID, "textures/projectiles").toString()));
    public static final RegistryObject<EntityType<WindblazeEntity>> WINDBLAZE = ENTITIES.register("windblaze", () -> EntityType.Builder.<WindblazeEntity>of(WindblazeEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20)
            .build(new ResourceLocation(Bioplethora.MOD_ID, "windblaze").toString()));
    public static final RegistryObject<EntityType<UltimateBellophiteClusterEntity>> ULTIMATE_BELLOPHITE_CLUSTER = ENTITIES.register("ultimate_bellophite_cluster", () -> EntityType.Builder.<UltimateBellophiteClusterEntity>of(UltimateBellophiteClusterEntity::new, EntityClassification.MISC).sized(2.0F, 2.0F).clientTrackingRange(4).updateInterval(20)
            .build(new ResourceLocation(Bioplethora.MOD_ID, "ultimate_bellophite_cluster").toString()));
    public static final RegistryObject<EntityType<MagmaBombEntity>> MAGMA_BOMB = ENTITIES.register("magma_bomb", () -> EntityType.Builder.<MagmaBombEntity>of(MagmaBombEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20)
            .build(new ResourceLocation(Bioplethora.MOD_ID, "magma_bomb").toString()));
    public static final RegistryObject<EntityType<VermilionBladeProjectileEntity>> VERMILION_BLADE_PROJECTILE = ENTITIES.register("vermilion_blade_projectile", () -> EntityType.Builder.<VermilionBladeProjectileEntity>of(VermilionBladeProjectileEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20)
            .build(new ResourceLocation(Bioplethora.MOD_ID, "vermilion_blade_projectile").toString()));

    //Others
    public static final RegistryObject<EntityType<AltyrusSummoningEntity>> ALTYRUS_SUMMONING = ENTITIES.register("altyrus_summoning", () -> EntityType.Builder.of(AltyrusSummoningEntity::new, EntityClassification.MISC).sized(2.0F, 2.0F).clientTrackingRange(4).updateInterval(20)
            .build(new ResourceLocation(Bioplethora.MOD_ID, "altyrus_summoning").toString()));
    public static final RegistryObject<EntityType<BellophiteShieldWaveEntity>> BELLOPHITE_SHIELD_WAVE = ENTITIES.register("bellophite_shield_wave", () -> EntityType.Builder.of(BellophiteShieldWaveEntity::new, EntityClassification.MISC).clientTrackingRange(4).updateInterval(20)
            .build(new ResourceLocation(Bioplethora.MOD_ID, "bellophite_shield_wave").toString()));
}
