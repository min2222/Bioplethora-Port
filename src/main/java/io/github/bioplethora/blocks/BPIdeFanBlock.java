package io.github.bioplethora.blocks;

import javax.annotation.Nullable;

import io.github.bioplethora.enums.BioPlantShape;
import io.github.bioplethora.enums.BioPlantType;
import io.github.bioplethora.registry.BPBlockstateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BPIdeFanBlock extends BPPlantBlock {
    public static final BooleanProperty BUDDED = BPBlockstateProperties.BUDDED;

    public BPIdeFanBlock(BioPlantType type, BioPlantShape shape, Properties properties) {
        super(type, shape, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BUDDED, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return pContext.getLevel().random.nextInt(3) == 1 ?
                super.getStateForPlacement(pContext).setValue(BUDDED, true) : super.getStateForPlacement(pContext);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        super.entityInside(pState, pLevel, pPos, pEntity);
        if (pState.getValue(BUDDED)) {
            pEntity.playSound(this.soundType.getPlaceSound(), 1.0F, 1.0F);
            pLevel.setBlock(pPos, pState.setValue(BUDDED, false), 2);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BUDDED);
    }
}
