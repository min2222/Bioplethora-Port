package io.github.bioplethora.registry;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.enchantments.AntibioEnchantment;
import io.github.bioplethora.enchantments.alphanum_obliterator.DevastatingBlastEnchantment;
import io.github.bioplethora.enchantments.gaidius.RackingEdgeEnchantment;
import io.github.bioplethora.enchantments.gaidius.SheerEnchantment;
import io.github.bioplethora.enchantments.gaidius.SoftshootingEnchantment;
import io.github.bioplethora.enums.BPEntityClasses;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Bioplethora.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BPEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Bioplethora.MOD_ID);

    public static final RegistryObject<Enchantment> ANTIBIO_ECOHARMLESS = ENCHANTMENTS.register("antibio_ecoharmless", () ->
            new AntibioEnchantment(Enchantment.Rarity.UNCOMMON, BPEntityClasses.ECOHARMLESS, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> ANTIBIO_PLETHONEUTRAL = ENCHANTMENTS.register("antibio_plethoneutral", () ->
            new AntibioEnchantment(Enchantment.Rarity.UNCOMMON, BPEntityClasses.PLETHONEUTRAL, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> ANTIBIO_DANGERUM = ENCHANTMENTS.register("antibio_dangerum", () ->
            new AntibioEnchantment(Enchantment.Rarity.RARE, BPEntityClasses.DANGERUM, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> ANTIBIO_HELLSENT = ENCHANTMENTS.register("antibio_hellsent", () ->
            new AntibioEnchantment(Enchantment.Rarity.RARE, BPEntityClasses.HELLSENT, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> ANTIBIO_ELDERIA = ENCHANTMENTS.register("antibio_elderia", () ->
            new AntibioEnchantment(Enchantment.Rarity.VERY_RARE, BPEntityClasses.ELDERIA, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> DEVASTATING_BLAST = ENCHANTMENTS.register("devastating_blast", () ->
            new DevastatingBlastEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static final RegistryObject<Enchantment> RACKING_EDGE = ENCHANTMENTS.register("racking_edge", () ->
            new RackingEdgeEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
    public static final RegistryObject<Enchantment> SHEER = ENCHANTMENTS.register("sheer", () ->
            new SheerEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
    public static final RegistryObject<Enchantment> SOFTSHOOTING = ENCHANTMENTS.register("softshooting", () ->
            new SoftshootingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    //public static final RegistryObject<Enchantment> HONED = ENCHANTMENTS.register("honed", () -> new HonedEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));

}
