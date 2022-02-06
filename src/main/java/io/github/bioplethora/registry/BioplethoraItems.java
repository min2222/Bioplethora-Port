package io.github.bioplethora.registry;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.enums.BPArmorMaterials;
import io.github.bioplethora.enums.BPItemTier;
import io.github.bioplethora.item.BiopediaItem;
import io.github.bioplethora.item.BioplethoraSpawnEggItem;
import io.github.bioplethora.item.armor.PeaguinScaleArmorItem;
import io.github.bioplethora.item.weapons.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BioplethoraItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bioplethora.MOD_ID);

    public static final RegistryObject<Item> BIOPEDIA = ITEMS.register("biopedia", () -> new BiopediaItem(new Item.Properties().fireResistant().rarity(Rarity.RARE).stacksTo(1).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new ExperimentalItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.BOSS_WEAPON).stacksTo(1).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Vanilla mobs custom drops/items

    // Bioplethora mobs drops/items
    public static final RegistryObject<Item> CREPHOXL_FEATHER = ITEMS.register("crephoxl_feather", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> CREPHOXL_STICK = ITEMS.register("crephoxl_stick", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRI_SCALES = ITEMS.register("nandbri_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE = ITEMS.register("bellophite", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PEAGUIN_SCALES = ITEMS.register("peaguin_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ABYSSAL_SCALES = ITEMS.register("abyssal_scales", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_CORE_FRAGMENT = ITEMS.register("bellophite_core_fragment", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PRIMORDIAL_FRAGMENT = ITEMS.register("primordial_fragment", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WINDPIECE = ITEMS.register("windpiece", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WINDY_ESSENCE = ITEMS.register("windy_essence", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> GREEN_GRYLYNEN_CRYSTAL = ITEMS.register("green_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> YELLOW_GRYLYNEN_CRYSTAL = ITEMS.register("yellow_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> RED_GRYLYNEN_CRYSTAL = ITEMS.register("red_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Vanilla mobs custom Weapons
    public static final RegistryObject<Item> PHANTOM_CHIME = ITEMS.register("phantom_chime", () -> new PhantomChimeItem(new Item.Properties().stacksTo(1).rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> MAGMA_BOMB = ITEMS.register("magma_bomb", () -> new MagmaBombItem(new Item.Properties().stacksTo(64).rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Bioplethora mobs Weapons
    public static final RegistryObject<Item> CREPHOXL_HAMMER = ITEMS.register("crephoxl_hammer", () -> new CrephoxlHammerItem(ItemTier.NETHERITE, 12-5, -3.30f, new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).durability(4508).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_SHIELD = ITEMS.register("bellophite_shield", () -> new BellophiteShieldItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).durability(5000).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_ARROW = ITEMS.register("bellophite_arrow", () -> new BellophiteArrowItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> STELLAR_SCYTHE = ITEMS.register("stellar_scythe", () -> new StellarScytheItem(ItemTier.NETHERITE, 11-5, -2.5f, new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).durability(4508).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ARBITRARY_BALLISTA = ITEMS.register("arbitrary_ballista", () -> new ArbitraryBallistaItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).durability(640).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> VERMILION_BLADE = ITEMS.register("vermilion_blade", () -> new VermilionBladeItem(BPItemTier.WEAPON_VERMILION, 17-5, -2.5f, new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.BOSS_WEAPON).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ABYSSAL_BLADE = ITEMS.register("abyssal_blade", () -> new AbyssalBladeItem(ItemTier.NETHERITE, 13-5, -2.7f, new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PRIMORDIAL_STAFF = ITEMS.register("primordial_staff", () -> new PrimordialStaffItem(new Item.Properties().fireResistant().rarity(BioplethoraRarityTypes.BOSS_WEAPON).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> TOTEM_OF_SWERVING = ITEMS.register("totem_of_swerving", () -> new SwervingTotemItem(new Item.Properties().fireResistant().stacksTo(1).rarity(BioplethoraRarityTypes.BOSS_WEAPON).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WIND_ARROW = ITEMS.register("wind_arrow", () -> new WindArrowItem(new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Armor Items
    public static final RegistryObject<Item> PEAGUIN_SCALE_HELMET = ITEMS.register("peaguin_scale_helmet", () -> new PeaguinScaleArmorItem(BPArmorMaterials.PEAGUIN_SCALES, EquipmentSlotType.HEAD, new Item.Properties().rarity(BioplethoraRarityTypes.SACRED).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    // Food Items
    public static final RegistryObject<Item> RAW_CUTTLEFISH_MEAT = ITEMS.register("raw_cuttlefish_meat", () -> new Item(new Item.Properties().food(BioplethoraFoods.RAW_CUTTLEFISH_MEAT).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> COOKED_CUTTLEFISH_MEAT = ITEMS.register("cooked_cuttlefish_meat", () -> new Item(new Item.Properties().food(BioplethoraFoods.COOKED_CUTTLEFISH_MEAT).tab(BioplethoraItemGroup.BioplethoraItemItemGroup)));

    //=================================================================
    //                  BIOPLETHORA SPAWN EGGS
    //=================================================================
    /** @ECOHARMLESS **/
    public static final RegistryObject<Item> CUTTLEFISH_SPAWN_EGG = ITEMS.register("cuttlefish_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.CUTTLEFISH, BioplethoraEntityClasses.ECOHARMLESS, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @PLETHONEUTRAL **/
    public static final RegistryObject<Item> NANDBRI_SPAWN_EGG = ITEMS.register("nandbri_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.NANDBRI, BioplethoraEntityClasses.PLETHONEUTRAL, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> PEAGUIN_SPAWN_EGG = ITEMS.register("peaguin_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.PEAGUIN, BioplethoraEntityClasses.PLETHONEUTRAL, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @DANGERUM **/
    public static final RegistryObject<Item> ALPHEM_SPAWN_EGG = ITEMS.register("alphem_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.ALPHEM, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> GAUGALEM_SPAWN_EGG = ITEMS.register("gaugalem_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.GAUGALEM, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> DWARF_MOSSADILE_SPAWN_EGG = ITEMS.register("dwarf_mossadile_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.DWARF_MOSSADILE, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    public static final RegistryObject<Item> WOODEN_GRYLYNEN_SPAWN_EGG = ITEMS.register("wooden_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.WOODEN_GRYLYNEN, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> STONE_GRYLYNEN_SPAWN_EGG = ITEMS.register("stone_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.STONE_GRYLYNEN, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> GOLDEN_GRYLYNEN_SPAWN_EGG = ITEMS.register("golden_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.GOLDEN_GRYLYNEN, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> IRON_GRYLYNEN_SPAWN_EGG = ITEMS.register("iron_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.IRON_GRYLYNEN, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> DIAMOND_GRYLYNEN_SPAWN_EGG = ITEMS.register("diamond_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.IRON_GRYLYNEN, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> NETHERITE_GRYLYNEN_SPAWN_EGG = ITEMS.register("netherite_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.IRON_GRYLYNEN, BioplethoraEntityClasses.DANGERUM, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @HELLSENT **/
    public static final RegistryObject<Item> CREPHOXL_SPAWN_EGG = ITEMS.register("crephoxl_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.CREPHOXL, BioplethoraEntityClasses.HELLSENT, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> BELLOPHGOLEM_SPAWN_EGG = ITEMS.register("bellophgolem_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.BELLOPHGOLEM, BioplethoraEntityClasses.HELLSENT, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> HELIOBLADE_SPAWN_EGG = ITEMS.register("helioblade_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.HELIOBLADE, BioplethoraEntityClasses.HELLSENT, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @ELDERIA **/
    public static final RegistryObject<Item> ALTYRUS_SPAWN_EGG = ITEMS.register("altyrus_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.ALTYRUS, BioplethoraEntityClasses.ELDERIA, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> MYLIOTHAN_SPAWN_EGG = ITEMS.register("myliothan_spawn_egg", () -> new BioplethoraSpawnEggItem(BioplethoraEntities.MYLIOTHAN, BioplethoraEntityClasses.ELDERIA, new Item.Properties().tab(BioplethoraItemGroup.BioplethoraSpawnEggsItemGroup)));
}
