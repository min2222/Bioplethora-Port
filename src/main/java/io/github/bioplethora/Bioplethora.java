package io.github.bioplethora;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.data.BioAdvancementProvider;
import io.github.bioplethora.data.BioBlockModelProvider;
import io.github.bioplethora.data.BioBlockTagsProvider;
import io.github.bioplethora.data.BioBlockstateProvider;
import io.github.bioplethora.data.BioEntityTagsProvider;
import io.github.bioplethora.data.BioItemModelProvider;
import io.github.bioplethora.data.BioItemTagsProvider;
import io.github.bioplethora.data.BioLanguageProvider;
import io.github.bioplethora.data.BioLootTablesProvider;
import io.github.bioplethora.data.BioRecipeProvider;
import io.github.bioplethora.integration.BPCompatTOP;
import io.github.bioplethora.network.BPNetwork;
import io.github.bioplethora.registry.BPAttributes;
import io.github.bioplethora.registry.BPBlocks;
import io.github.bioplethora.registry.BPContainerTypes;
import io.github.bioplethora.registry.BPEffects;
import io.github.bioplethora.registry.BPEnchantments;
import io.github.bioplethora.registry.BPEntities;
import io.github.bioplethora.registry.BPExtras;
import io.github.bioplethora.registry.BPItems;
import io.github.bioplethora.registry.BPLootConditions;
import io.github.bioplethora.registry.BPParticles;
import io.github.bioplethora.registry.BPRecipes;
import io.github.bioplethora.registry.BPSoundEvents;
import io.github.bioplethora.registry.BPTileEntities;
import io.github.bioplethora.registry.BPVillagerTrades;
import io.github.bioplethora.registry.worldgen.BPBiomeModifiers;
import io.github.bioplethora.registry.worldgen.BPBiomes;
import io.github.bioplethora.registry.worldgen.BPConfiguredFeatures;
import io.github.bioplethora.registry.worldgen.BPConfiguredWorldCarvers;
import io.github.bioplethora.registry.worldgen.BPFeatures;
import io.github.bioplethora.registry.worldgen.BPPlacedFeatures;
import io.github.bioplethora.registry.worldgen.BPStructures;
import io.github.bioplethora.registry.worldgen.BPWorldCarvers;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import software.bernie.geckolib3.GeckoLib;

@Mod(Bioplethora.MOD_ID)
public class Bioplethora {

    public static Bioplethora instance;

    public static final String MOD_ID = "bioplethora";
    public static final String MOD_NAME = "Bioplethora";
    public static final Logger LOGGER = LogManager.getLogger();
    public static boolean HELL_MODE;

    public Bioplethora() {
        instance = this;
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        /* final step of registering elements like Items, Entities, etc. */

        BPEntities.ENTITIES.register(bus);
        BPItems.ITEMS.register(bus);
        BPBlocks.BLOCKS.register(bus);
        BPBlocks.BLOCK_ITEMS.register(bus);
        BPBiomes.BIOMES.register(bus);
        BPFeatures.FEATURES.register(bus);
        BPPlacedFeatures.PLACED_FEATURES.register(bus);
        BPConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        BPWorldCarvers.WORLD_CARVERS.register(bus);
        BPSoundEvents.SOUNDS.register(bus);
        BPParticles.PARTICLES.register(bus);
        BPEffects.EFFECTS.register(bus);
        BPEnchantments.ENCHANTMENTS.register(bus);
        BPStructures.STRUCTURES.register(bus);
        BPStructures.STRUCTURES_SET.register(bus);
        BPAttributes.ATTRIBUTES.register(bus);
        BPConfiguredWorldCarvers.CONFIGURED_CARVER.register(bus);
        BPBiomeModifiers.BIOME_MODIFIERS.register(bus);
        BPTileEntities.TILE_ENTITIES.register(bus);
        BPContainerTypes.CONTAINERS.register(bus);
        BPRecipes.RECIPE_SERIALIZERS.register(bus);
        BPRecipes.RECIPE_TYPES.register(bus);
        BPConfig.loadConfig(BPConfig.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve("bioplethora-common.toml").toString());
        BPConfig.loadConfig(BPConfig.WORLDGEN_SPEC, FMLPaths.CONFIGDIR.get().resolve("bioplethora-worldgen.toml").toString());

        bus.addListener(this::setup);
        bus.addListener(this::gatherData);
        bus.addListener(this::onModConfigEvent);
        bus.addListener(this::onInterModEnqueueEvent);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, BPVillagerTrades::onVillagerTrades);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, BPAttributes::useTrueDefenseAttribute);

        // register this class through the Minecraft Forge Event Bus
        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BPConfig.COMMON_SPEC, "bioplethora/common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BPConfig.WORLDGEN_SPEC, "bioplethora/worldgen.toml");
    }
    
    private void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        // Rebake the configs when they change
        if (config.getSpec() == BPConfig.COMMON_SPEC) {
        	HELL_MODE = BPConfig.COMMON.hellMode.get();
        }
    }

    private void onInterModEnqueueEvent(final InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("theoneprobe")) BPCompatTOP.register();
        //if (ModList.get().isLoaded("jeresources")) BPCompatJER.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Setting up [" + MOD_NAME + "], thank you for using this mod!");

        event.enqueueWork(BPBiomes::generateBiomes);

        BPLootConditions.registerConditions();

        BPNetwork.initializeNetwork();
        BPExtras.addExtras();
    }

    private void gatherData(final GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        final ExistingFileHelper efh = event.getExistingFileHelper();

        dataGenerator.addProvider(event.includeServer(), new BioBlockModelProvider(dataGenerator, MOD_ID, efh));
        dataGenerator.addProvider(event.includeServer(), new BioBlockstateProvider(dataGenerator, MOD_ID, efh));
        dataGenerator.addProvider(event.includeServer(), new BioItemModelProvider(dataGenerator, efh));
        dataGenerator.addProvider(event.includeServer(), new BioRecipeProvider(dataGenerator));
        dataGenerator.addProvider(event.includeServer(), new BioLootTablesProvider(dataGenerator));

        dataGenerator.addProvider(event.includeServer(), new BioBlockTagsProvider(dataGenerator, efh));
        dataGenerator.addProvider(event.includeServer(), new BioEntityTagsProvider(dataGenerator, efh));
        dataGenerator.addProvider(event.includeServer(), new BioItemTagsProvider(dataGenerator, new BioBlockTagsProvider(dataGenerator, efh), efh));

        dataGenerator.addProvider(event.includeServer(), new BioAdvancementProvider(dataGenerator, efh));

        dataGenerator.addProvider(event.includeServer(), new BioLanguageProvider(dataGenerator, MOD_ID, "en_us_test"));
    }
}
