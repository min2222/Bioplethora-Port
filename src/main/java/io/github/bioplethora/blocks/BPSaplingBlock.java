package io.github.bioplethora.blocks;

import io.github.bioplethora.api.world.WorldgenUtils;
import io.github.bioplethora.enums.BioPlantType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BPSaplingBlock extends SaplingBlock {

    public BioPlantType type;
    public WorldgenUtils.NBTTree nbtTree;

    public BPSaplingBlock(BioPlantType type, WorldgenUtils.NBTTree nbtTree, Properties properties) {
        super(null, properties);
        this.type = type;
        this.nbtTree = nbtTree;
    }

    @Override
    public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, RandomSource rand) {
        if (state.getValue(STAGE) == 0) {
            world.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(world, rand, pos)) return;
            this.nbtTree.placeAt(world, world.getChunkSource().getGenerator(), pos, state, rand);
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return type.getWhitelist().get().contains(state.getBlock());
    }
}
