package io.github.bioplethora.registry;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.config.BPConfig;
import io.github.bioplethora.entity.projectile.CryeanumGaidiusEntity;
import io.github.bioplethora.entity.projectile.EchoGaidiusEntity;
import io.github.bioplethora.entity.projectile.NetheriteObsidianGaidiusEntity;
import io.github.bioplethora.enums.BPArmorMaterials;
import io.github.bioplethora.enums.BPEntityClasses;
import io.github.bioplethora.enums.BPItemTier;
import io.github.bioplethora.item.BiopediaItem;
import io.github.bioplethora.item.BioplethoraSpawnEggItem;
import io.github.bioplethora.item.ExperimentalItem;
import io.github.bioplethora.item.armor.AquChestplateItem;
import io.github.bioplethora.item.armor.FleignariteArmorItem;
import io.github.bioplethora.item.armor.NandbricArmorItem;
import io.github.bioplethora.item.armor.PeaguinScaleArmorItem;
import io.github.bioplethora.item.armor.ReinforcedFleignariteArmorItem;
import io.github.bioplethora.item.extras.AlphanumGemItem;
import io.github.bioplethora.item.extras.WindyEssenceItem;
import io.github.bioplethora.item.functionals.SpiritFissionCharmItem;
import io.github.bioplethora.item.functionals.SpiritManipulationCharmItem;
import io.github.bioplethora.item.functionals.SpiritStrengtheningCharmItem;
import io.github.bioplethora.item.functionals.SwervingTotemItem;
import io.github.bioplethora.item.weapons.AbyssalBladeItem;
import io.github.bioplethora.item.weapons.AlphanumObliteratorItem;
import io.github.bioplethora.item.weapons.ArbitraryBallistaItem;
import io.github.bioplethora.item.weapons.CrephoxlHammerItem;
import io.github.bioplethora.item.weapons.FrostbiteMetalArrowItem;
import io.github.bioplethora.item.weapons.FrostbiteMetalShieldItem;
import io.github.bioplethora.item.weapons.GaidiusBaseItem;
import io.github.bioplethora.item.weapons.GrylynenShieldGreenItem;
import io.github.bioplethora.item.weapons.GrylynenShieldRedItem;
import io.github.bioplethora.item.weapons.GrylynenShieldYellowItem;
import io.github.bioplethora.item.weapons.InfernalQuarterstaffItem;
import io.github.bioplethora.item.weapons.MagmaBombItem;
import io.github.bioplethora.item.weapons.NandbricShortswordItem;
import io.github.bioplethora.item.weapons.PhantomChimeItem;
import io.github.bioplethora.item.weapons.PrimordialStaffItem;
import io.github.bioplethora.item.weapons.StellarScytheItem;
import io.github.bioplethora.item.weapons.VermilionBladeItem;
import io.github.bioplethora.item.weapons.WindArrowItem;
import io.github.bioplethora.item.weapons.fleignarite_set.FleignariteAxeItem;
import io.github.bioplethora.item.weapons.fleignarite_set.FleignariteHoeItem;
import io.github.bioplethora.item.weapons.fleignarite_set.FleignaritePickaxeItem;
import io.github.bioplethora.item.weapons.fleignarite_set.FleignariteShovelItem;
import io.github.bioplethora.item.weapons.fleignarite_set.FleignariteSwordItem;
import io.github.bioplethora.item.weapons.reinforced_fleignarite_set.ReinforcedFleignariteAxeItem;
import io.github.bioplethora.item.weapons.reinforced_fleignarite_set.ReinforcedFleignariteHoeItem;
import io.github.bioplethora.item.weapons.reinforced_fleignarite_set.ReinforcedFleignaritePickaxeItem;
import io.github.bioplethora.item.weapons.reinforced_fleignarite_set.ReinforcedFleignariteShovelItem;
import io.github.bioplethora.item.weapons.reinforced_fleignarite_set.ReinforcedFleignariteSwordItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BPItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bioplethora.MOD_ID);

    //Variables
    public static boolean hellConfig = BPConfig.IN_HELLMODE;

    //Main Items
    public static final RegistryObject<Item> BIOPEDIA = ITEMS.register("biopedia", () -> new BiopediaItem(new Item.Properties().fireResistant().rarity(Rarity.RARE).stacksTo(1).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new ExperimentalItem(new Item.Properties().fireResistant().rarity(BPRarityTypes.BOSS_WEAPON).stacksTo(1).tab(BPItemGroup.BioplethoraItemItemGroup)));

    // Bioplethora mobs drops/items
    public static final RegistryObject<Item> CREPHOXL_FEATHER = ITEMS.register("crephoxl_feather", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> CREPHOXL_STICK = ITEMS.register("crephoxl_stick", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRI_SCALES = ITEMS.register("nandbri_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRI_FANG = ITEMS.register("nandbri_fang", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE = ITEMS.register("frostbite_metal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PEAGUIN_SCALES = ITEMS.register("peaguin_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ABYSSAL_SCALES = ITEMS.register("abyssal_scales", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_CORE_FRAGMENT = ITEMS.register("frostbite_metal_core_fragment", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PRIMORDIAL_FRAGMENT = ITEMS.register("primordial_fragment", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WINDPIECE = ITEMS.register("windpiece", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WINDY_ESSENCE = ITEMS.register("windy_essence", () -> new WindyEssenceItem(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_SCALES = ITEMS.register("fleignarite_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE = ITEMS.register("reinforced_fleignarite", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ALPHANUM_GEM = ITEMS.register("alphanum_gem", () -> new AlphanumGemItem(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ALPHEM_KING_REMNANT = ITEMS.register("alphem_king_remnant", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FIERY_CUBE = ITEMS.register("fiery_cube", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> SOUL_CUBE = ITEMS.register("soul_cube", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> GREEN_GRYLYNEN_CRYSTAL = ITEMS.register("green_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> YELLOW_GRYLYNEN_CRYSTAL = ITEMS.register("yellow_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> RED_GRYLYNEN_CRYSTAL = ITEMS.register("red_grylynen_crystal", () -> new Item(new Item.Properties().stacksTo(64).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> INFERNAL_QUARTERSTAFF_BASE = ITEMS.register("infernal_quarterstaff_base", () -> new Item(new Item.Properties().stacksTo(16).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> INFERNAL_QUARTERSTAFF_BLADE = ITEMS.register("infernal_quarterstaff_blade", () -> new Item(new Item.Properties().stacksTo(16).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> INFERNAL_QUARTERSTAFF_DEACTIVATED = ITEMS.register("deactivated_infernal_quarterstaff", () -> new Item(new Item.Properties().stacksTo(1).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> CRYEANUM_PEARL = ITEMS.register("cryeanum_pearl", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> TRAPJAW_SCALES = ITEMS.register("trapjaw_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> VOIDJAW_SCALES = ITEMS.register("voidjaw_scales", () -> new Item(new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));

    // Bioplethora Fish Buckets
    public static final RegistryObject<Item> TRIGGERFISH_BUCKET = ITEMS.register("triggerfish_bucket", () -> new MobBucketItem(() -> BPEntities.TRIGGERFISH.get(), () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(64).tab(BPItemGroup.BioplethoraItemItemGroup)));

    // Vanilla mobs custom Weapons
    public static final RegistryObject<Item> PHANTOM_CHIME = ITEMS.register("phantom_chime", () -> new PhantomChimeItem(new Item.Properties().stacksTo(1).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> MAGMA_BOMB = ITEMS.register("magma_bomb", () -> new MagmaBombItem(new Item.Properties().stacksTo(64).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));

    // Bioplethora mobs Weapons
    public static final RegistryObject<Item> CREPHOXL_HAMMER = ITEMS.register("crephoxl_hammer", () -> new CrephoxlHammerItem(Tiers.NETHERITE, hellConfig ? 12 - BPItems.netheriteDMG : 10 - BPItems.netheriteDMG, -3.30f, new Item.Properties().fireResistant().rarity(BPRarityTypes.SACRED).durability(4508).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_SHIELD = ITEMS.register("frostbite_metal_shield", () -> new FrostbiteMetalShieldItem(new Item.Properties().fireResistant().rarity(BPRarityTypes.SACRED).durability(5000).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> BELLOPHITE_ARROW = ITEMS.register("frostbite_metal_arrow", () -> new FrostbiteMetalArrowItem(new Item.Properties().fireResistant().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRIC_SHORTSWORD = ITEMS.register("nandbric_shortsword", () -> new NandbricShortswordItem(Tiers.DIAMOND, 1, -2F, new Item.Properties().rarity(BPRarityTypes.SACRED).durability(964).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> STELLAR_SCYTHE = ITEMS.register("stellar_scythe", () -> new StellarScytheItem(Tiers.NETHERITE, hellConfig ? 11 - BPItems.netheriteDMG : 9 - BPItems.netheriteDMG, -2.5f, new Item.Properties().fireResistant().rarity(BPRarityTypes.SACRED).durability(4508).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ARBITRARY_BALLISTA = ITEMS.register("arbitrary_ballista", () -> new ArbitraryBallistaItem(new Item.Properties().fireResistant().rarity(BPRarityTypes.SACRED).durability(640).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> VERMILION_BLADE = ITEMS.register("vermilion_blade", () -> new VermilionBladeItem(BPItemTier.VERMILION,  hellConfig ? 15 - BPItems.netheriteDMG : 11 - BPItems.netheriteDMG, -2.5f, new Item.Properties().fireResistant().rarity(BPRarityTypes.BOSS_WEAPON).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ABYSSAL_BLADE = ITEMS.register("abyssal_blade", () -> new AbyssalBladeItem(Tiers.NETHERITE, hellConfig ? 13 - BPItems.netheriteDMG : 10 - BPItems.netheriteDMG, -2.7f, new Item.Properties().fireResistant().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> PRIMORDIAL_STAFF = ITEMS.register("primordial_staff", () -> new PrimordialStaffItem(new Item.Properties().fireResistant().stacksTo(1).rarity(BPRarityTypes.BOSS_WEAPON).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> TOTEM_OF_SWERVING = ITEMS.register("totem_of_swerving", () -> new SwervingTotemItem(new Item.Properties().fireResistant().stacksTo(1).rarity(BPRarityTypes.BOSS_WEAPON).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WIND_ARROW = ITEMS.register("wind_arrow", () -> new WindArrowItem(new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> SPIRIT_FISSION_CHARM = ITEMS.register("spirit_fission_charm", () -> new SpiritFissionCharmItem(new Item.Properties().stacksTo(1).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> SPIRIT_MANIPULATION_CHARM = ITEMS.register("spirit_manipulation_charm", () -> new SpiritManipulationCharmItem(new Item.Properties().stacksTo(1).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> SPIRIT_STRENGTHENING_CHARM = ITEMS.register("spirit_strengthening_charm", () -> new SpiritStrengtheningCharmItem(new Item.Properties().stacksTo(1).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> GREEN_CRYSTAL_SHIELD = ITEMS.register("green_crystal_shield", () -> new GrylynenShieldGreenItem(new Item.Properties().durability(740).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> YELLOW_CRYSTAL_SHIELD = ITEMS.register("yellow_crystal_shield", () -> new GrylynenShieldYellowItem(new Item.Properties().durability(1125).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> RED_CRYSTAL_SHIELD = ITEMS.register("red_crystal_shield", () -> new GrylynenShieldRedItem(new Item.Properties().durability(18575).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> ALPHANUM_OBLITERATOR = ITEMS.register("alphanum_obliterator", () -> new AlphanumObliteratorItem(new Item.Properties().durability(11500).rarity(BPRarityTypes.BOSS_WEAPON).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> INFERNAL_QUARTERSTAFF = ITEMS.register("infernal_quarterstaff", () -> new InfernalQuarterstaffItem(Tiers.NETHERITE, hellConfig ? 12 - BPItems.netheriteDMG : 8 - BPItems.netheriteDMG, -2.5f, new Item.Properties().durability(5200).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> CRYEANUM_GAIDIUS = ITEMS.register("cryeanum_gaidius", () -> new GaidiusBaseItem(Tiers.NETHERITE, hellConfig ? 8 - BPItems.netheriteDMG : 7 - BPItems.netheriteDMG, -3.0f,
            new GaidiusBaseItem.ItemProps().projectile(CryeanumGaidiusEntity::new).inaccuracy(1.5F).velocity(1.5F),
            new Item.Properties().durability(2240).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)
    ));
    public static final RegistryObject<Item> NETHERITE_OBSIDIAN_GAIDIUS = ITEMS.register("netherite_obsidian_gaidius", () -> new GaidiusBaseItem(Tiers.NETHERITE, hellConfig ? 14 - BPItems.netheriteDMG : 12 - BPItems.netheriteDMG, -2.8f,
            new GaidiusBaseItem.ItemProps().projectile(NetheriteObsidianGaidiusEntity::new).inaccuracy(2.0F).velocity(1.2F),
            new Item.Properties().durability(4255).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)
    ));
    public static final RegistryObject<Item> ECHO_GAIDIUS = ITEMS.register("echo_gaidius", () -> new GaidiusBaseItem(Tiers.NETHERITE, 7 - BPItems.netheriteDMG, -2.6f,
            new GaidiusBaseItem.ItemProps().projectile(EchoGaidiusEntity::new).inaccuracy(0.5F).velocity(2.5F),
            new Item.Properties().durability(1985).rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)
    ));

    // Tool sets
    public static final RegistryObject<Item> FLEIGNARITE_SWORD = ITEMS.register("fleignarite_sword", () -> new FleignariteSwordItem(BPItemTier.FLEIGNARITE, 7 - BPItems.netheriteDMG, -2.4F, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_SHOVEL = ITEMS.register("fleignarite_shovel", () -> new FleignariteShovelItem(BPItemTier.FLEIGNARITE, 5.0F - BPItems.netheriteDMG, -3.0F, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_PICKAXE = ITEMS.register("fleignarite_pickaxe", () -> new FleignaritePickaxeItem(BPItemTier.FLEIGNARITE, 5 - BPItems.netheriteDMG, -2.8F, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_AXE = ITEMS.register("fleignarite_axe", () -> new FleignariteAxeItem(BPItemTier.FLEIGNARITE, 9 - BPItems.netheriteDMG, -3.1F, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_HOE = ITEMS.register("fleignarite_hoe", () -> new FleignariteHoeItem(BPItemTier.FLEIGNARITE, 1 - BPItems.netheriteDMG, -1.0F, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_SWORD = ITEMS.register("reinforced_fleignarite_sword", () -> new ReinforcedFleignariteSwordItem(BPItemTier.REINFORCED_FLEIGNARITE, 10 - BPItems.netheriteDMG, -2.2F, new Item.Properties().rarity(BPRarityTypes.SACRED).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_SHOVEL = ITEMS.register("reinforced_fleignarite_shovel", () -> new ReinforcedFleignariteShovelItem(BPItemTier.REINFORCED_FLEIGNARITE, 7.0F - BPItems.netheriteDMG, -3.0F, new Item.Properties().rarity(BPRarityTypes.SACRED).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_PICKAXE = ITEMS.register("reinforced_fleignarite_pickaxe", () -> new ReinforcedFleignaritePickaxeItem(BPItemTier.REINFORCED_FLEIGNARITE, 7 - BPItems.netheriteDMG, -2.8F, new Item.Properties().rarity(BPRarityTypes.SACRED).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_AXE = ITEMS.register("reinforced_fleignarite_axe", () -> new ReinforcedFleignariteAxeItem(BPItemTier.REINFORCED_FLEIGNARITE, 13 - BPItems.netheriteDMG, -2.8F, new Item.Properties().rarity(BPRarityTypes.SACRED).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_HOE = ITEMS.register("reinforced_fleignarite_hoe", () -> new ReinforcedFleignariteHoeItem(BPItemTier.REINFORCED_FLEIGNARITE, 1 - BPItems.netheriteDMG, 0.0F, new Item.Properties().rarity(BPRarityTypes.SACRED).fireResistant().tab(BPItemGroup.BioplethoraItemItemGroup)));

    // Armor Items
    public static final RegistryObject<Item> PEAGUIN_SCALE_HELMET = ITEMS.register("peaguin_scale_helmet", () -> new PeaguinScaleArmorItem(BPArmorMaterials.PEAGUIN_SCALES, EquipmentSlot.HEAD, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> FLEIGNARITE_HELMET = ITEMS.register("fleignarite_helmet", () -> new FleignariteArmorItem(BPArmorMaterials.FLEIGNARITE, EquipmentSlot.HEAD, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_CHESTPLATE = ITEMS.register("fleignarite_chestplate", () -> new FleignariteArmorItem(BPArmorMaterials.FLEIGNARITE, EquipmentSlot.CHEST, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_LEGGINGS = ITEMS.register("fleignarite_leggings", () -> new FleignariteArmorItem(BPArmorMaterials.FLEIGNARITE, EquipmentSlot.LEGS, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> FLEIGNARITE_BOOTS = ITEMS.register("fleignarite_boots", () -> new FleignariteArmorItem(BPArmorMaterials.FLEIGNARITE, EquipmentSlot.FEET, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> NANDBRIC_HELMET = ITEMS.register("nandbric_helmet", () -> new NandbricArmorItem(BPArmorMaterials.NANDBRIC, EquipmentSlot.HEAD, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRIC_CHESTPLATE = ITEMS.register("nandbric_chestplate", () -> new NandbricArmorItem(BPArmorMaterials.NANDBRIC, EquipmentSlot.CHEST, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRIC_LEGGINGS = ITEMS.register("nandbric_leggings", () -> new NandbricArmorItem(BPArmorMaterials.NANDBRIC, EquipmentSlot.LEGS, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> NANDBRIC_BOOTS = ITEMS.register("nandbric_boots", () -> new NandbricArmorItem(BPArmorMaterials.NANDBRIC, EquipmentSlot.FEET, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_HELMET = ITEMS.register("reinforced_fleignarite_helmet", () -> new ReinforcedFleignariteArmorItem(BPArmorMaterials.REINFORCED_FLEIGNARITE, EquipmentSlot.HEAD, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_CHESTPLATE = ITEMS.register("reinforced_fleignarite_chestplate", () -> new ReinforcedFleignariteArmorItem(BPArmorMaterials.REINFORCED_FLEIGNARITE, EquipmentSlot.CHEST, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_LEGGINGS = ITEMS.register("reinforced_fleignarite_leggings", () -> new ReinforcedFleignariteArmorItem(BPArmorMaterials.REINFORCED_FLEIGNARITE, EquipmentSlot.LEGS, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> REINFORCED_FLEIGNARITE_BOOTS = ITEMS.register("reinforced_fleignarite_boots", () -> new ReinforcedFleignariteArmorItem(BPArmorMaterials.REINFORCED_FLEIGNARITE, EquipmentSlot.FEET, new Item.Properties().rarity(BPRarityTypes.SACRED).tab(BPItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> AQU_CHESTPLATE = ITEMS.register("aqu_chestplate", () -> new AquChestplateItem(BPArmorMaterials.AQU, EquipmentSlot.CHEST, new Item.Properties().rarity(BPRarityTypes.BOSS_WEAPON).fireResistant()));

    // Food Items
    public static final RegistryObject<Item> FIERY_SAP_BOTTLE = ITEMS.register("fiery_sap_bottle", () -> new HoneyBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(BPFoods.FIERY_SAP_BOTTLE).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> SOUL_SAP_BOTTLE = ITEMS.register("soul_sap_bottle", () -> new HoneyBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(BPFoods.SOUL_SAP_BOTTLE).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> WARPED_GRAPES = ITEMS.register("warped_grapes", () -> new Item(new Item.Properties().food(BPFoods.WARPED_GRAPES).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> CRIMSON_BITTERSWEET_SEEDS = ITEMS.register("crimson_bittersweet_seeds", () -> new Item(new Item.Properties().food(BPFoods.CRIMSON_BITTERSWEET_SEEDS).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> THONTUS_BERRIES = ITEMS.register("thontus_berries", () -> new Item(new Item.Properties().food(BPFoods.THONTUS_BERRIES).tab(BPItemGroup.BioplethoraItemItemGroup)));

    public static final RegistryObject<Item> RAW_CUTTLEFISH_MEAT = ITEMS.register("raw_cuttlefish_meat", () -> new Item(new Item.Properties().food(BPFoods.RAW_CUTTLEFISH_MEAT).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> COOKED_CUTTLEFISH_MEAT = ITEMS.register("cooked_cuttlefish_meat", () -> new Item(new Item.Properties().food(BPFoods.COOKED_CUTTLEFISH_MEAT).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> RAW_FLENTAIR = ITEMS.register("raw_flentair", () -> new Item(new Item.Properties().food(BPFoods.RAW_FLENTAIR).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> COOKED_FLENTAIR = ITEMS.register("cooked_flentair", () -> new Item(new Item.Properties().food(BPFoods.COOKED_FLENTAIR).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> RAW_MOSILE = ITEMS.register("raw_mosile", () -> new Item(new Item.Properties().food(BPFoods.RAW_MOSILE).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> COOKED_MOSILE = ITEMS.register("cooked_mosile", () -> new Item(new Item.Properties().food(BPFoods.COOKED_MOSILE).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> RAW_JAWFLESH = ITEMS.register("raw_jawflesh", () -> new Item(new Item.Properties().food(BPFoods.RAW_JAWFLESH).tab(BPItemGroup.BioplethoraItemItemGroup)));
    public static final RegistryObject<Item> COOKED_JAWFLESH = ITEMS.register("cooked_jawflesh", () -> new Item(new Item.Properties().food(BPFoods.COOKED_JAWFLESH).tab(BPItemGroup.BioplethoraItemItemGroup)));

    // Plant Items
    //FIXME no model
    public static  final RegistryObject<Item> SWIVELBLOOM = ITEMS.register("swivelbloom", () -> new Item(new Item.Properties()));

    //=================================================================
    //                  BIOPLETHORA SPAWN EGGS
    //=================================================================
    /** @ECOHARMLESS **/
    public static final RegistryObject<Item> CUTTLEFISH_SPAWN_EGG = ITEMS.register("cuttlefish_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.CUTTLEFISH, BPEntityClasses.ECOHARMLESS, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> ONOFISH_SPAWN_EGG = ITEMS.register("onofish_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.ONOFISH, BPEntityClasses.ECOHARMLESS, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> TRIGGERFISH_SPAWN_EGG = ITEMS.register("triggerfish_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.TRIGGERFISH, BPEntityClasses.ECOHARMLESS, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));

    public static final RegistryObject<Item> FIERY_EURYDN_SPAWN_EGG = ITEMS.register("fiery_eurydn_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.FIERY_EURYDN, BPEntityClasses.ECOHARMLESS, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> SOUL_EURYDN_SPAWN_EGG = ITEMS.register("soul_eurydn_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.SOUL_EURYDN, BPEntityClasses.ECOHARMLESS, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @PLETHONEUTRAL **/
    public static final RegistryObject<Item> NANDBRI_SPAWN_EGG = ITEMS.register("nandbri_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.NANDBRI, BPEntityClasses.PLETHONEUTRAL, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> PEAGUIN_SPAWN_EGG = ITEMS.register("peaguin_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.PEAGUIN, BPEntityClasses.PLETHONEUTRAL, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> CAVERN_FLEIGNAR_SPAWN_EGG = ITEMS.register("cavern_fleignar_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.CAVERN_FLEIGNAR, BPEntityClasses.PLETHONEUTRAL, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @DANGERUM **/
    public static final RegistryObject<Item> ALPHEM_SPAWN_EGG = ITEMS.register("alphem_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.ALPHEM, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> GAUGALEM_SPAWN_EGG = ITEMS.register("gaugalem_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.GAUGALEM, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> DWARF_MOSSADILE_SPAWN_EGG = ITEMS.register("dwarf_mossadile_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.DWARF_MOSSADILE, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> TRAPJAW_SPAWN_EGG = ITEMS.register("trapjaw_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.TRAPJAW, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> TERRAITH_SPAWN_EGG = ITEMS.register("terraith_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.TERRAITH, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> MYUTHINE_SPAWN_EGG = ITEMS.register("myuthine_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.MYUTHINE, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));

    public static final RegistryObject<Item> WOODEN_GRYLYNEN_SPAWN_EGG = ITEMS.register("wooden_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.WOODEN_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> STONE_GRYLYNEN_SPAWN_EGG = ITEMS.register("stone_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.STONE_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> GOLDEN_GRYLYNEN_SPAWN_EGG = ITEMS.register("golden_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.GOLDEN_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> IRON_GRYLYNEN_SPAWN_EGG = ITEMS.register("iron_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.IRON_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> DIAMOND_GRYLYNEN_SPAWN_EGG = ITEMS.register("diamond_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.DIAMOND_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> NETHERITE_GRYLYNEN_SPAWN_EGG = ITEMS.register("netherite_grylynen_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.NETHERITE_GRYLYNEN, BPEntityClasses.DANGERUM, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @HELLSENT **/
    public static final RegistryObject<Item> CREPHOXL_SPAWN_EGG = ITEMS.register("crephoxl_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.CREPHOXL, BPEntityClasses.HELLSENT, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> FROSTBITE_GOLEM_SPAWN_EGG = ITEMS.register("frostbite_golem_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.FROSTBITE_GOLEM, BPEntityClasses.HELLSENT, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> SHACHATH_SPAWN_EGG = ITEMS.register("shachath_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.SHACHATH, BPEntityClasses.HELLSENT, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> VOIDJAW_SPAWN_EGG = ITEMS.register("voidjaw_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.VOIDJAW, BPEntityClasses.HELLSENT, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));

    /** @ELDERIA **/
    public static final RegistryObject<Item> ALTYRUS_SPAWN_EGG = ITEMS.register("altyrus_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.ALTYRUS, BPEntityClasses.ELDERIA, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> MYLIOTHAN_SPAWN_EGG = ITEMS.register("myliothan_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.MYLIOTHAN, BPEntityClasses.ELDERIA, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));
    public static final RegistryObject<Item> ALPHEM_KING_SPAWN_EGG = ITEMS.register("alphem_king_spawn_egg", () -> new BioplethoraSpawnEggItem(BPEntities.ALPHEM_KING, BPEntityClasses.ELDERIA, new Item.Properties().tab(BPItemGroup.BioplethoraSpawnEggsItemGroup)));

    public static int netheriteDMG = 5;

}
