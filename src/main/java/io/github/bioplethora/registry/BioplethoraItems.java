package io.github.bioplethora.registry;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.BioplethoraConfig;
import io.github.bioplethora.enums.BPArmorMaterials;
import io.github.bioplethora.enums.BPEntityClasses;
import io.github.bioplethora.enums.BPItemTier;
import io.github.bioplethora.item.BiopediaItem;
import io.github.bioplethora.item.BioplethoraSpawnEggItem;
import io.github.bioplethora.item.ExperimentalItem;
import io.github.bioplethora.item.armor.PeaguinScaleArmorItem;
import io.github.bioplethora.item.extras.AlphanumGemItem;
import io.github.bioplethora.item.functionals.SpiritFissionCharmItem;
import io.github.bioplethora.item.functionals.SpiritManipulationCharmItem;
import io.github.bioplethora.item.functionals.SwervingTotemItem;
import io.github.bioplethora.item.weapons.*;
import io.github.bioplethora.item.weapons.fleignarite_set.*;
import io.github.bioplethora.item.weapons.reinforced_fleignarite_set.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BioplethoraItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bioplethora.MOD_ID);

    //Variables
    public static boolean hellConfig = BioplethoraConfig.COMMON.hellMode.get();

    //Main Items
    public static final RegistryObject<Item> BIOPEDIA = ITEMS.register("biopedia", () -> new BiopediaItem(new Item.Properties().fireResistant().rarity(Rarity.RARE).stacksTo(1).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new ExperimentalItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.BOSS_WEAPON).stacksTo(1).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Vanilla mobs custom drops/items

    // Bioplethora mobs drops/items
    public static final RegistryObject<Item> CREPHOXL_FEATHER = ITEMS.register("crephoxl_feather", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> CREPHOXL_STICK = ITEMS.register("crephoxl_stick", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRI_SCALES = ITEMS.register("nandbri_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRI_FANG = ITEMS.register("nandbri_fang", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE = ITEMS.register("bellophite", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PEAGUIN_SCALES = ITEMS.register("peaguin_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ABYSSAL_SCALES = ITEMS.register("abyssal_scales", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_CORE_FRAGMENT = ITEMS.register("bellophite_core_fragment", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PRIMORDIAL_FRAGMENT = ITEMS.register("primordial_fragment", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WINDPIECE = ITEMS.register("windpiece", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WINDY_ESSENCE = ITEMS.register("windy_essence", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_SCALES = ITEMS.register("fleignarite_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE = ITEMS.register("reinforced_fleignarite", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ALPHANUM_GEM = ITEMS.register("alphanum_gem", () -> new AlphanumGemItem(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ALPHEM_KING_REMNANT = ITEMS.register("alphem_king_remnant", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> GREEN_GRYLYNEN_CRYSTAL = ITEMS.register("green_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> YELLOW_GRYLYNEN_CRYSTAL = ITEMS.register("yellow_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> RED_GRYLYNEN_CRYSTAL = ITEMS.register("red_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Vanilla mobs custom Weapons
    public static final RegistryObject<Item> PHANTOM_CHIME = ITEMS.register("phantom_chime", () -> new PhantomChimeItem(new Item.Properties().stacksTo(1).rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> MAGMA_BOMB = ITEMS.register("magma_bomb", () -> new MagmaBombItem(new Item.Properties().stacksTo(64).rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Bioplethora mobs Weapons
    public static final RegistryObject<Item> CREPHOXL_HAMMER = ITEMS.register("crephoxl_hammer", () -> new CrephoxlHammerItem(ItemTier.NETHERITE, hellConfig ? 12 - BioplethoraItems.netheriteDMG : 10 - BioplethoraItems.netheriteDMG, -3.30f, new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).durability(4508).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_SHIELD = ITEMS.register("bellophite_shield", () -> new BellophiteShieldItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).durability(5000).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_ARROW = ITEMS.register("bellophite_arrow", () -> new BellophiteArrowItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRIC_SHORTSWORD = ITEMS.register("nandbric_shortsword", () -> new NandbricShortswordItem(ItemTier.DIAMOND, 1, -2F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).durability(964).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> STELLAR_SCYTHE = ITEMS.register("stellar_scythe", () -> new StellarScytheItem(ItemTier.NETHERITE, hellConfig ? 11 - BioplethoraItems.netheriteDMG : 9 - BioplethoraItems.netheriteDMG, -2.5f, new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).durability(4508).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ARBITRARY_BALLISTA = ITEMS.register("arbitrary_ballista", () -> new ArbitraryBallistaItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).durability(640).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> VERMILION_BLADE = ITEMS.register("vermilion_blade", () -> new VermilionBladeItem(BPItemTier.VERMILION,  hellConfig ? 15 - BioplethoraItems.netheriteDMG : 11 - BioplethoraItems.netheriteDMG, -2.5f, new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.BOSS_WEAPON).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ABYSSAL_BLADE = ITEMS.register("abyssal_blade", () -> new AbyssalBladeItem(ItemTier.NETHERITE, hellConfig ? 13 - BioplethoraItems.netheriteDMG : 10 - BioplethoraItems.netheriteDMG, -2.7f, new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PRIMORDIAL_STAFF = ITEMS.register("primordial_staff", () -> new PrimordialStaffItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.BOSS_WEAPON).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> TOTEM_OF_SWERVING = ITEMS.register("totem_of_swerving", () -> new SwervingTotemItem(new Item.Properties().fireResistant().stacksTo(1).rarity(BioplethoraRarityTypes.BOSS_WEAPON).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WIND_ARROW = ITEMS.register("wind_arrow", () -> new WindArrowItem(new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> SPIRIT_FISSION_CHARM = ITEMS.register("spirit_fission_charm", () -> new SpiritFissionCharmItem(new Item.Properties().stacksTo(1).rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> SPIRIT_MANIPULATION_CHARM = ITEMS.register("spirit_manipulation_charm", () -> new SpiritManipulationCharmItem(new Item.Properties().stacksTo(1).rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> GREEN_CRYSTAL_SHIELD = ITEMS.register("green_crystal_shield", () -> new GrylynenShieldGreenItem(new Item.Properties().durability(740).rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> YELLOW_CRYSTAL_SHIELD = ITEMS.register("yellow_crystal_shield", () -> new GrylynenShieldYellowItem(new Item.Properties().durability(1125).rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Tool sets
    public static final RegistryObject<Item> FLEIGNARITE_SWORD = ITEMS.register("fleignarite_sword", () -> new FleignariteSwordItem(BPItemTier.FLEIGNARITE, 7 - BioplethoraItems.netheriteDMG, -2.4F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_SHOVEL = ITEMS.register("fleignarite_shovel", () -> new FleignariteShovelItem(BPItemTier.FLEIGNARITE, 5.0F - BioplethoraItems.netheriteDMG, -3.0F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_PICKAXE = ITEMS.register("fleignarite_pickaxe", () -> new FleignaritePickaxeItem(BPItemTier.FLEIGNARITE, 5 - BioplethoraItems.netheriteDMG, -2.8F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_AXE = ITEMS.register("fleignarite_axe", () -> new FleignariteAxeItem(BPItemTier.FLEIGNARITE, 9 - BioplethoraItems.netheriteDMG, -3.1F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_HOE = ITEMS.register("fleignarite_hoe", () -> new FleignariteHoeItem(BPItemTier.FLEIGNARITE, 1 - BioplethoraItems.netheriteDMG, -1.0F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_SWORD = ITEMS.register("reinforced_fleignarite_sword", () -> new ReinforcedFleignariteSwordItem(BPItemTier.REINFORCED_FLEIGNARITE, 10 - BioplethoraItems.netheriteDMG, -2.2F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_SHOVEL = ITEMS.register("reinforced_fleignarite_shovel", () -> new ReinforcedFleignariteShovelItem(BPItemTier.REINFORCED_FLEIGNARITE, 7.0F - BioplethoraItems.netheriteDMG, -3.0F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_PICKAXE = ITEMS.register("reinforced_fleignarite_pickaxe", () -> new ReinforcedFleignaritePickaxeItem(BPItemTier.REINFORCED_FLEIGNARITE, 7 - BioplethoraItems.netheriteDMG, -2.8F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_AXE = ITEMS.register("reinforced_fleignarite_axe", () -> new ReinforcedFleignariteAxeItem(BPItemTier.REINFORCED_FLEIGNARITE, 13 - BioplethoraItems.netheriteDMG, -2.8F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_HOE = ITEMS.register("reinforced_fleignarite_hoe", () -> new ReinforcedFleignariteHoeItem(BPItemTier.REINFORCED_FLEIGNARITE, 1 - BioplethoraItems.netheriteDMG, 0.0F, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Armor Items
    public static final RegistryObject<Item> PEAGUIN_SCALE_HELMET = ITEMS.register("peaguin_scale_helmet", () -> new PeaguinScaleArmorItem(BPArmorMaterials.PEAGUIN_SCALES, EquipmentSlotType.HEAD, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> FLEIGNARITE_HELMET = ITEMS.register("fleignarite_helmet", () -> new ArmorItem(BPArmorMaterials.FLEIGNARITE, EquipmentSlotType.HEAD, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_CHESTPLATE = ITEMS.register("fleignarite_chestplate", () -> new ArmorItem(BPArmorMaterials.FLEIGNARITE, EquipmentSlotType.CHEST, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_LEGGINGS = ITEMS.register("fleignarite_leggings", () -> new ArmorItem(BPArmorMaterials.FLEIGNARITE, EquipmentSlotType.LEGS, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_BOOTS = ITEMS.register("fleignarite_boots", () -> new ArmorItem(BPArmorMaterials.FLEIGNARITE, EquipmentSlotType.FEET, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_HELMET = ITEMS.register("reinforced_fleignarite_helmet", () -> new ArmorItem(BPArmorMaterials.REINFORCED_FLEIGNARITE, EquipmentSlotType.HEAD, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_CHESTPLATE = ITEMS.register("reinforced_fleignarite_chestplate", () -> new ArmorItem(BPArmorMaterials.REINFORCED_FLEIGNARITE, EquipmentSlotType.CHEST, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_LEGGINGS = ITEMS.register("reinforced_fleignarite_leggings", () -> new ArmorItem(BPArmorMaterials.REINFORCED_FLEIGNARITE, EquipmentSlotType.LEGS, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_BOOTS = ITEMS.register("reinforced_fleignarite_boots", () -> new ArmorItem(BPArmorMaterials.REINFORCED_FLEIGNARITE, EquipmentSlotType.FEET, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Food Items
    public static final RegistryObject<Item> RAW_CUTTLEFISH_MEAT = ITEMS.register("raw_cuttlefish_meat", () -> new Item(new Item.Properties().food(BioplethoraFoods.RAW_CUTTLEFISH_MEAT).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> COOKED_CUTTLEFISH_MEAT = ITEMS.register("cooked_cuttlefish_meat", () -> new Item(new Item.Properties().food(BioplethoraFoods.COOKED_CUTTLEFISH_MEAT).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> RAW_FLENTAIR = ITEMS.register("raw_flentair", () -> new Item(new Item.Properties().food(BioplethoraFoods.RAW_FLENTAIR).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> COOKED_FLENTAIR = ITEMS.register("cooked_flentair", () -> new Item(new Item.Properties().food(BioplethoraFoods.COOKED_FLENTAIR).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    //=================================================================
    //                  BIOPLETHORA SPAWN EGGS
    //=================================================================
    /** @ECOHARMLESS **/
    public static final RegistryObject<Item> CUTTLEFISH_SPAWN_EGG = ITEMS.register("cuttlefish_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.CUTTLEFISH, BPEntityClasses.ECOHARMLESS, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @PLETHONEUTRAL **/
    public static final RegistryObject<Item> NANDBRI_SPAWN_EGG = ITEMS.register("nandbri_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.NANDBRI, BPEntityClasses.PLETHONEUTRAL, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> PEAGUIN_SPAWN_EGG = ITEMS.register("peaguin_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.PEAGUIN, BPEntityClasses.PLETHONEUTRAL, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> CAVERN_FLEIGNAR_SPAWN_EGG = ITEMS.register("cavern_fleignar_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.CAVERN_FLEIGNAR, BPEntityClasses.PLETHONEUTRAL, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @DANGERUM **/
    public static final RegistryObject<Item> ALPHEM_SPAWN_EGG = ITEMS.register("alphem_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.ALPHEM, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> GAUGALEM_SPAWN_EGG = ITEMS.register("gaugalem_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.GAUGALEM, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> DWARF_MOSSADILE_SPAWN_EGG = ITEMS.register("dwarf_mossadile_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.DWARF_MOSSADILE, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> TRAPJAW_SPAWN_EGG = ITEMS.register("trapjaw_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.TRAPJAW, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    public static final RegistryObject<Item> WOODEN_GRYLYNEN_SPAWN_EGG = ITEMS.register("wooden_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.WOODEN_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> STONE_GRYLYNEN_SPAWN_EGG = ITEMS.register("stone_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.STONE_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> GOLDEN_GRYLYNEN_SPAWN_EGG = ITEMS.register("golden_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.GOLDEN_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> IRON_GRYLYNEN_SPAWN_EGG = ITEMS.register("iron_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.IRON_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> DIAMOND_GRYLYNEN_SPAWN_EGG = ITEMS.register("diamond_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.DIAMOND_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> NETHERITE_GRYLYNEN_SPAWN_EGG = ITEMS.register("netherite_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.NETHERITE_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @HELLSENT **/
    public static final RegistryObject<Item> CREPHOXL_SPAWN_EGG = ITEMS.register("crephoxl_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.CREPHOXL, BPEntityClasses.HELLSENT, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> BELLOPHGOLEM_SPAWN_EGG = ITEMS.register("bellophgolem_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.BELLOPHGOLEM, BPEntityClasses.HELLSENT, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> HELIOBLADE_SPAWN_EGG = ITEMS.register("helioblade_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.HELIOBLADE, BPEntityClasses.HELLSENT, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    /** @ELDERIA **/
    public static final RegistryObject<Item> ALTYRUS_SPAWN_EGG = ITEMS.register("altyrus_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.ALTYRUS, BPEntityClasses.ELDERIA, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> MYLIOTHAN_SPAWN_EGG = ITEMS.register("myliothan_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.MYLIOTHAN, BPEntityClasses.ELDERIA, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> ALPHEM_KING_SPAWN_EGG = ITEMS.register("alphem_king_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.ALPHEM_KING, BPEntityClasses.ELDERIA, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    public static int netheriteDMG = 5;
}
