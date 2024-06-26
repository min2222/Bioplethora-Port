package io.github.bioplethora.data;

import java.util.stream.Collectors;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.blocks.SmallMushroomBlock;
import io.github.bioplethora.registry.BPBlocks;
import io.github.bioplethora.registry.BPItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

public class BPBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        dropSelf(BPBlocks.NANDBRI_SCALE_BLOCK.get());
        dropSelf(BPBlocks.BELLOPHITE_BLOCK.get());
        dropSelf(BPBlocks.BELLOPHITE_CORE_BLOCK.get());

        dropSelf(BPBlocks.MIRESTONE.get());

        add(BPBlocks.GREEN_GRYLYNEN_CRYSTAL_BLOCK.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.GREEN_GRYLYNEN_CRYSTAL_BLOCK.get()));
        add(BPBlocks.YELLOW_GRYLYNEN_CRYSTAL_BLOCK.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.YELLOW_GRYLYNEN_CRYSTAL_BLOCK.get()));
        add(BPBlocks.RED_GRYLYNEN_CRYSTAL_BLOCK.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.RED_GRYLYNEN_CRYSTAL_BLOCK.get()));

        dropSelf(BPBlocks.REINFORCING_TABLE.get());

        dropOther(BPBlocks.FLEIGNARITE_REMAINS.get(), BPItems.FLEIGNARITE_SCALES.get());
        dropOther(BPBlocks.FLEIGNARITE_SPLOTCH.get(), BPItems.FLEIGNARITE_SCALES.get());
        dropOther(BPBlocks.FLEIGNARITE_VINES.get(), BPItems.FLEIGNARITE_SCALES.get());
        dropOther(BPBlocks.FLEIGNARITE_VINES_PLANT.get(), BPItems.FLEIGNARITE_SCALES.get());

        // Nether Plants
        dropSelf(BPBlocks.CRYEA.get());
        add(BPBlocks.CRYEA_CARPET.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.CRYEA_CARPET.get()));

        add(BPBlocks.KYRIA.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.KYRIA.get()));
        add(BPBlocks.KYRIA_BELINE.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.KYRIA_BELINE.get()));
        add(BPBlocks.KYRIA_IDE_FAN.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.KYRIA_IDE_FAN.get()));

        dropMinishroom(BPBlocks.SOUL_MINISHROOM.get());

        dropSelf(BPBlocks.SOUL_BIGSHROOM.get());

        dropOther(BPBlocks.LAVA_SPIRE.get(), Blocks.AIR);
        add(BPBlocks.WARPED_DANCER.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.WARPED_DANCER.get()));

        add(BPBlocks.SOUL_SPROUTS.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.SOUL_SPROUTS.get()));
        add(BPBlocks.SOUL_TALL_GRASS.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.SOUL_TALL_GRASS.get()));

        dropSelf(BPBlocks.PINK_TWI.get());
        dropOther(BPBlocks.PINK_TWI_PLANT.get(), BPBlocks.PINK_TWI.get());
        dropSelf(BPBlocks.RED_TWI.get());
        dropOther(BPBlocks.RED_TWI_PLANT.get(), BPBlocks.RED_TWI.get());

        dropSelf(BPBlocks.SPIRIT_DANGLER.get());
        dropOther(BPBlocks.SPIRIT_DANGLER_PLANT.get(), BPBlocks.SPIRIT_DANGLER.get());

        dropSelf(BPBlocks.BASALT_SPELEOTHERM.get());
        dropOther(BPBlocks.BASALT_SPELEOTHERM_PLANT.get(), BPBlocks.BASALT_SPELEOTHERM.get());
        dropOther(BPBlocks.FIERY_BASALT_SPELEOTHERM.get(), BPBlocks.BASALT_SPELEOTHERM.get());

        dropSelf(BPBlocks.THONTUS_THISTLE.get());
        dropOther(BPBlocks.THONTUS_THISTLE_PLANT.get(), BPBlocks.THONTUS_THISTLE.get());
        dropOther(BPBlocks.BERRIED_THONTUS_THISTLE.get(), BPBlocks.THONTUS_THISTLE.get());

        dropSelf(BPBlocks.TURQUOISE_PENDENT.get());
        dropOther(BPBlocks.TURQUOISE_PENDENT_PLANT.get(), BPBlocks.TURQUOISE_PENDENT.get());
        dropOther(BPBlocks.BLOSSOMING_TURQUOISE_PENDENT.get(), BPBlocks.TURQUOISE_PENDENT.get());

        dropSelf(BPBlocks.CERISE_IVY.get());
        dropOther(BPBlocks.CERISE_IVY_PLANT.get(), BPBlocks.CERISE_IVY.get());
        dropOther(BPBlocks.SEEDED_CERISE_IVY.get(), BPBlocks.CERISE_IVY.get());

        dropSelf(BPBlocks.SOUL_ETERN.get());
        dropOther(BPBlocks.SOUL_ETERN_PLANT.get(), BPBlocks.SOUL_ETERN.get());
        dropOther(BPBlocks.FLOURISHED_SOUL_ETERN.get(), BPBlocks.SOUL_ETERN.get());

        // End Plants
        dropSelf(BPBlocks.ENDURION.get());
        dropSelf(BPBlocks.TENEDEBRIS.get());
        dropSelf(BPBlocks.CRYOSOIL.get());
        add(BPBlocks.CHORUS_MYCHRODEGIA.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.CHORUS_MYCHRODEGIA.get()));
        add(BPBlocks.IRION.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.CRYOSOIL.get()));

        add(BPBlocks.IRION_GRASS.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.IRION_GRASS.get()));
        add(BPBlocks.IRION_TALL_GRASS.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.IRION_TALL_GRASS.get()));

        add(BPBlocks.CHORUS_IDON.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.CHORUS_IDON.get()));
        add(BPBlocks.CHORUS_IDE_FAN.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.CHORUS_IDE_FAN.get()));

        add(BPBlocks.CYRA.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.CYRA.get()));

        add(BPBlocks.AZURLIA.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.AZURLIA.get()));
        add(BPBlocks.CHORUS_MYCHRODEGIA_PART.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.CHORUS_MYCHRODEGIA_PART.get()));
        dropSelf(BPBlocks.ARTAIRIUS.get());

        dropSelf(BPBlocks.BYRSS_FRUIT_BLOCK.get());
        add(BPBlocks.BYRSS_LANTERN_PLANT.get(), BPBlockLootTables::createLanternTable);

        dropSelf(BPBlocks.CHORUS_CITRUS_BLOCK.get());
        add(BPBlocks.CHORUS_LANTERN_PLANT.get(), BPBlockLootTables::createLanternTable);

        add(BPBlocks.ENREDE_CORSASCILE.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.ENREDE_CORSASCILE.get()));
        dropSelf(BPBlocks.ENREDE_KELP.get());
        dropOther(BPBlocks.ENREDE_KELP_PLANT.get(), BPBlocks.ENREDE_KELP.get());
        add(BPBlocks.OCHAIM_PURPLE.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.OCHAIM_PURPLE.get()));
        add(BPBlocks.OCHAIM_RED.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.OCHAIM_RED.get()));
        add(BPBlocks.OCHAIM_GREEN.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.OCHAIM_GREEN.get()));

        add(BPBlocks.FROSTEM.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.FROSTEM.get()));

        dropSelf(BPBlocks.SPINXELTHORN.get());
        dropOther(BPBlocks.SPINXELTHORN_PLANT.get(), BPBlocks.SPINXELTHORN.get());

        add(BPBlocks.GLACYNTH.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.GLACYNTH.get()));
        add(BPBlocks.GLACYNTH_PLANT.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.GLACYNTH.get()));

        add(BPBlocks.CELESTIA_BUD.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.CELESTIA_BUD.get()));
        add(BPBlocks.CELESTIA_BUD_PLANT.get(), (sTouch) -> createSingleItemTableWithSilkTouch(sTouch, BPBlocks.CELESTIA_BUD.get()));

        // Potted Plants
        dropPottedContents(BPBlocks.POTTED_LAVA_SPIRE.get());

        // Enivile woodset
        dropSelf(BPBlocks.ENIVILE_LOG.get());
        dropSelf(BPBlocks.ENIVILE_WOOD.get());
        dropSelf(BPBlocks.STRIPPED_ENIVILE_LOG.get());
        dropSelf(BPBlocks.STRIPPED_ENIVILE_WOOD.get());
        dropSelf(BPBlocks.ENIVILE_PLANKS.get());
        add(BPBlocks.ENIVILE_LEAVES_RED.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.ENIVILE_LEAVES_RED.get()));
        add(BPBlocks.ENIVILE_LEAVES_PINK.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.ENIVILE_LEAVES_PINK.get()));
        dropSelf(BPBlocks.ENIVILE_SAPLING.get());

        dropSelf(BPBlocks.ENIVILE_FENCE.get());
        dropSelf(BPBlocks.ENIVILE_FENCE_GATE.get());
        dropSelf(BPBlocks.ENIVILE_SLAB.get());
        dropSelf(BPBlocks.ENIVILE_PRESSURE_PLATE.get());
        dropSelf(BPBlocks.ENIVILE_STAIRS.get());
        dropSelf(BPBlocks.ENIVILE_BUTTON.get());
        dropSelf(BPBlocks.ENIVILE_SIGN.get());
        dropSelf(BPBlocks.ENIVILE_WALL_SIGN.get());
        add(BPBlocks.ENIVILE_DOOR.get(), BlockLoot::createDoorTable);
        dropSelf(BPBlocks.ENIVILE_TRAPDOOR.get());
        
        // Caerulwood woodset
        dropSelf(BPBlocks.CAERULWOOD_LOG.get());
        dropSelf(BPBlocks.CAERULWOOD_WOOD.get());
        dropSelf(BPBlocks.STRIPPED_CAERULWOOD_LOG.get());
        dropSelf(BPBlocks.STRIPPED_CAERULWOOD_WOOD.get());
        dropSelf(BPBlocks.CAERULWOOD_PLANKS.get());
        add(BPBlocks.CAERULWOOD_LEAVES.get(), (sTouch) -> createShearsOnlyDrop(BPBlocks.CAERULWOOD_LEAVES.get()));
        dropSelf(BPBlocks.CAERULWOOD_SAPLING.get());

        dropSelf(BPBlocks.CAERULWOOD_FENCE.get());
        dropSelf(BPBlocks.CAERULWOOD_FENCE_GATE.get());
        dropSelf(BPBlocks.CAERULWOOD_SLAB.get());
        dropSelf(BPBlocks.CAERULWOOD_PRESSURE_PLATE.get());
        dropSelf(BPBlocks.CAERULWOOD_STAIRS.get());
        dropSelf(BPBlocks.CAERULWOOD_BUTTON.get());
        dropSelf(BPBlocks.CAERULWOOD_SIGN.get());
        dropSelf(BPBlocks.CAERULWOOD_WALL_SIGN.get());
        add(BPBlocks.CAERULWOOD_DOOR.get(), BlockLoot::createDoorTable);
        dropSelf(BPBlocks.CAERULWOOD_TRAPDOOR.get());
        
        // Alphanum Stone Set
        dropSelf(BPBlocks.ALPHANUM.get());
        dropSelf(BPBlocks.ALPHANUM_BRICKS.get());
        dropSelf(BPBlocks.POLISHED_ALPHANUM.get());
        dropSelf(BPBlocks.ALPHANUM_PILLAR.get());
        dropSelf(BPBlocks.ALPHANUM_NUCLEUS.get());

        dropSelf(BPBlocks.ALPHANUM_STAIRS.get());
        dropSelf(BPBlocks.ALPHANUM_STAIRS_BRICKS.get());
        dropSelf(BPBlocks.POLISHED_ALPHANUM_STAIRS.get());
        dropSelf(BPBlocks.ALPHANUM_WALL.get());
        dropSelf(BPBlocks.ALPHANUM_WALL_BRICKS.get());
        dropSelf(BPBlocks.POLISHED_ALPHANUM_WALL.get());
        dropSelf(BPBlocks.ALPHANUM_SLAB.get());
        dropSelf(BPBlocks.ALPHANUM_SLAB_BRICKS.get());
        dropSelf(BPBlocks.POLISHED_ALPHANUM_SLAB.get());

        /*
        // Petrawood woodset
        dropSelf(BPBlocks.PETRAWOOD_LOG.get());
        dropSelf(BPBlocks.PETRAWOOD_WOOD.get());
        dropSelf(BPBlocks.STRIPPED_PETRAWOOD_LOG.get());
        dropSelf(BPBlocks.STRIPPED_PETRAWOOD_WOOD.get());
        dropSelf(BPBlocks.PETRAWOOD_PLANKS.get());
        add(BPBlocks.PETRAWOOD_LEAVES.get(), createShearsOnlyDrop(BPBlocks.PETRAWOOD_LEAVES.get()));
        //dropSelf(BPBlocks.PETRAWOOD_SAPLING.get());

        dropSelf(BPBlocks.PETRAWOOD_FENCE.get());
        dropSelf(BPBlocks.PETRAWOOD_FENCE_GATE.get());
        dropSelf(BPBlocks.PETRAWOOD_SLAB.get());
        dropSelf(BPBlocks.PETRAWOOD_PRESSURE_PLATE.get());
        dropSelf(BPBlocks.PETRAWOOD_STAIRS.get());
        dropSelf(BPBlocks.PETRAWOOD_BUTTON.get());
        //dropSelf(BPBlocks.PETRAWOOD_SIGN.get());
        //dropSelf(BPBlocks.PETRAWOOD_WALL_SIGN.get());
        //dropSelf(BPBlocks.PETRAWOOD_DOOR.get());
        //dropSelf(BPBlocks.PETRAWOOD_TRAPDOOR.get());
        */
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream().filter(b -> ForgeRegistries.BLOCKS.getKey(b).getNamespace().equals(Bioplethora.MOD_ID)).collect(Collectors.toList());
    }

    public void dropMinishroom(Block shroom) {
        this.add(shroom, (p_218478_0_) ->
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(applyExplosionDecay(shroom, LootItem.lootTableItem(p_218478_0_)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_218478_0_).setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(SmallMushroomBlock.MINISHROOMS, 1))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_218478_0_).setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(SmallMushroomBlock.MINISHROOMS, 2))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_218478_0_).setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(SmallMushroomBlock.MINISHROOMS, 3))))
                                ))
                        )
        );
    }

    public static LootTable.Builder createLanternTable(Block p_239829_0_) {
        return createSinglePropConditionTable(p_239829_0_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER);
    }
}
