package io.github.bioplethora.enums;

import java.util.function.Supplier;

import io.github.bioplethora.registry.BPItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum BPItemTier implements Tier {

    FLEIGNARITE(2, 350, 7.0F, 3.0F, 12,
            () -> Ingredient.of(BPItems.FLEIGNARITE_SCALES.get())),
    REINFORCED_FLEIGNARITE(2540, 250, 9.0F, 4.0F, 22,
            () -> Ingredient.of(BPItems.REINFORCED_FLEIGNARITE.get())),

    VERMILION(5, 4231, 9.0F, 4.0F, 15,
            () -> Ingredient.of(BPItems.VERMILION_BLADE.get())),
    ;

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    BPItemTier(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = damage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    public float getSpeed() {
        return this.efficiency;
    }

    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public int getLevel() {
        return this.harvestLevel;
    }

    public int getUses() {
        return this.maxUses;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
}
