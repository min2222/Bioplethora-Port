package io.github.bioplethora.registry;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.blocks.BellophiteCoreBlock;
import io.github.bioplethora.blocks.tile_entities.ReinforcingTableBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BioplethoraBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Bioplethora.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bioplethora.MOD_ID);

    public static final RegistryObject<Block> REINFORCING_TABLE = registerBlock("reinforcing_table", () -> new ReinforcingTableBlock(AbstractBlock.Properties.of(Material.METAL)
            .strength(20.0F, 35).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()), null);

    public static final RegistryObject<Block> NANDBRI_SCALE_BLOCK = registerBlock("nandbri_scale_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.COLOR_LIGHT_GRAY)
            .strength(0.8F, 0.8F).harvestTool(ToolType.AXE).harvestLevel(3).sound(SoundType.BONE_BLOCK)), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<Block> BELLOPHITE_BLOCK = registerFireResBlock("bellophite_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops()
            .strength(50.0F, 1200.0F).harvestTool(ToolType.PICKAXE).sound(SoundType.NETHERITE_BLOCK)), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<Block> BELLOPHITE_CORE_BLOCK = registerFireResBlock("bellophite_core_block", () -> new BellophiteCoreBlock(AbstractBlock.Properties.of(Material.GLASS)
            .strength(0.3F).sound(SoundType.GLASS).noOcclusion()), BioplethoraItemGroup.BioplethoraItemItemGroup);

    public static final RegistryObject<Block> GREEN_GRYLYNEN_CRYSTAL_BLOCK = registerFireResBlock("green_grylynen_crystal_block", () -> new Block(AbstractBlock.Properties.of(Material.GLASS)
            .strength(0.3F).sound(SoundType.NETHER_GOLD_ORE).noOcclusion().lightLevel((level) -> 7)), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<Block> YELLOW_GRYLYNEN_CRYSTAL_BLOCK = registerFireResBlock("yellow_grylynen_crystal_block", () -> new Block(AbstractBlock.Properties.of(Material.GLASS)
            .strength(0.4F).sound(SoundType.NETHER_GOLD_ORE).noOcclusion().lightLevel((level) -> 9)), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<Block> RED_GRYLYNEN_CRYSTAL_BLOCK = registerFireResBlock("red_grylynen_crystal_block", () -> new Block(AbstractBlock.Properties.of(Material.GLASS)
            .strength(0.5F).sound(SoundType.NETHER_GOLD_ORE).noOcclusion().lightLevel((level) -> 12)), BioplethoraItemGroup.BioplethoraItemItemGroup);

    public static final RegistryObject<Block> MIRESTONE = registerBlock("mirestone", () -> new Block(AbstractBlock.Properties.of(Material.STONE)
            .strength(1.2F, 4.8F).harvestTool(ToolType.SHOVEL).harvestTool(ToolType.PICKAXE).sound(SoundType.GRAVEL).noOcclusion()), null);

    // Alphanum Set
    public static final RegistryObject<Block> ALPHANUM = registerBlock("alphanum", () -> new Block(AbstractBlock.Properties.of(Material.STONE)
            .strength(50.0F, 1200.0F).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.NETHER_GOLD_ORE).noOcclusion()), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<Block> ALPHANUM_BRICKS = registerBlock("alphanum_bricks", () ->
            new Block(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<Block> POLISHED_ALPHANUM = registerBlock("polished_alphanum", () ->
            new Block(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<RotatedPillarBlock> ALPHANUM_PILLAR = registerBlock("alphanum_pillar", () ->
            new RotatedPillarBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);

    public static final RegistryObject<RotatedPillarBlock> ALPHANUM_NUCLEUS = registerBlock("alphanum_nucleus", () ->
            new RotatedPillarBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get()).lightLevel((level) -> 8)), BioplethoraItemGroup.BioplethoraItemItemGroup);

    public static final RegistryObject<StairsBlock> ALPHANUM_STAIRS = registerBlock("alphanum_stairs", () ->
            new StairsBlock(BioplethoraBlocks.ALPHANUM.get().defaultBlockState(), AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<WallBlock> ALPHANUM_WALL = registerBlock("alphanum_wall", () ->
            new WallBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<SlabBlock> ALPHANUM_SLAB = registerBlock("alphanum_slab", () ->
            new SlabBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<StairsBlock> ALPHANUM_STAIRS_BRICKS = registerBlock("alphanum_brick_stairs", () ->
            new StairsBlock(BioplethoraBlocks.ALPHANUM_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<WallBlock> ALPHANUM_WALL_BRICKS = registerBlock("alphanum_brick_wall", () ->
            new WallBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<SlabBlock> ALPHANUM_SLAB_BRICKS = registerBlock("alphanum_brick_slab", () ->
            new SlabBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<StairsBlock> POLISHED_ALPHANUM_STAIRS = registerBlock("polished_alphanum_stairs", () ->
            new StairsBlock(BioplethoraBlocks.POLISHED_ALPHANUM.get().defaultBlockState(), AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<WallBlock> POLISHED_ALPHANUM_WALL = registerBlock("polished_alphanum_wall", () ->
            new WallBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);
    public static final RegistryObject<SlabBlock> POLISHED_ALPHANUM_SLAB = registerBlock("polished_alphanum_slab", () ->
            new SlabBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.ALPHANUM.get())), BioplethoraItemGroup.BioplethoraItemItemGroup);

    // todo: Petrawood set for Rocky Woodlands biome
    public static final RegistryObject<RotatedPillarBlock> PETRAWOOD_LOG = registerBlock("petrawood_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_HYPHAE)
            .strength(2.4F).sound(SoundType.WOOD).noOcclusion()), null);
    public static final RegistryObject<RotatedPillarBlock> PETRAWOOD_WOOD = registerBlock("petrawood_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_HYPHAE)
            .strength(2.4F).sound(SoundType.WOOD).noOcclusion()), null);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_PETRAWOOD_LOG = registerBlock("stripped_petrawood_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.PETRAWOOD_LOG.get())
            .strength(2.4F).sound(SoundType.WOOD).noOcclusion()), null);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_PETRAWOOD_WOOD = registerBlock("stripped_petrawood_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.PETRAWOOD_WOOD.get())
            .strength(2.4F).sound(SoundType.WOOD).noOcclusion()), null);
    public static final RegistryObject<Block> PETRAWOOD_PLANKS = registerBlock("petrawood_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.CRIMSON_PLANKS)
            .strength(2.4F).sound(SoundType.WOOD).noOcclusion()), null);
    public static final RegistryObject<LeavesBlock> PETRAWOOD_LEAVES = registerBlock("petrawood_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)
            .strength(2.4F).sound(SoundType.GRASS).noOcclusion()), null);

    public static final RegistryObject<FenceBlock> PETRAWOOD_FENCE = registerBlock("petrawood_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.PETRAWOOD_PLANKS.get())), null);
    public static final RegistryObject<FenceGateBlock> PETRAWOOD_FENCE_GATE = registerBlock("petrawood_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.PETRAWOOD_PLANKS.get())), null);
    public static final RegistryObject<SlabBlock> PETRAWOOD_SLAB = registerBlock("petrawood_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.PETRAWOOD_PLANKS.get())), null);
    public static final RegistryObject<PressurePlateBlock> PETRAWOOD_PRESSURE_PLATE = registerBlock("petrawood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(BioplethoraBlocks.PETRAWOOD_PLANKS.get())), null);
    public static final RegistryObject<StairsBlock> PETRAWOOD_STAIRS = registerBlock("petrawood_stairs", () -> new StairsBlock(BioplethoraBlocks.PETRAWOOD_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(BioplethoraBlocks.PETRAWOOD_PLANKS.get())), null);
    public static final RegistryObject<WoodButtonBlock> PETRAWOOD_BUTTON = registerBlock("petrawood_button", () -> new WoodButtonBlock(AbstractBlock.Properties.copy(BioplethoraBlocks.PETRAWOOD_PLANKS.get())), null);

    //=================================================================================
    //                       REGULAR BLOCK CONSTRUCTORS
    //=================================================================================
    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        return registerBlock(name, supplier, itemGroup, true);
    }

    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, boolean generateItem) {
        return registerBlock(name, supplier, itemGroup, 64, generateItem);
    }

    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, int stackSize, boolean generateItem) {
        RegistryObject<B> block = BioplethoraBlocks.BLOCKS.register(name, supplier);
        if (generateItem)
            BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(itemGroup).stacksTo(stackSize)));
        return block;
    }

    //=================================================================================
    //                      FIRE RESISTANT BLOCK CONSTRUCTORS
    //=================================================================================
    public static <B extends Block> RegistryObject<B> registerFireResBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        return registerFireResBlock(name, supplier, itemGroup, true);
    }

    public static <B extends Block> RegistryObject<B> registerFireResBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, boolean generateItem) {
        return registerFireResBlock(name, supplier, itemGroup, 64, generateItem);
    }

    public static <B extends Block> RegistryObject<B> registerFireResBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup, int stackSize, boolean generateItem) {
        RegistryObject<B> block = BioplethoraBlocks.BLOCKS.register(name, supplier);
        if (generateItem)
            BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(itemGroup).stacksTo(stackSize).fireResistant()));
        return block;
    }
}