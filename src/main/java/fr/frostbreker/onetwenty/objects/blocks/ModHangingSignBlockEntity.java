package fr.frostbreker.onetwenty.objects.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModHangingSignBlockEntity extends HangingSignBlockEntity {
    public ModHangingSignBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
    }

   /* @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get();
    } */
}
