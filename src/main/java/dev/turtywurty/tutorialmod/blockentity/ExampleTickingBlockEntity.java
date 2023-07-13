package dev.turtywurty.tutorialmod.blockentity;

import dev.turtywurty.tutorialmod.blockentity.util.TickableBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ExampleTickingBlockEntity extends BlockEntity implements TickableBlockEntity {
    private int ticks;

    public ExampleTickingBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.EXAMPLE_TICKING_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide())
            return;

        if(this.ticks++ % 100 == 0) {
            Pig pig = EntityType.PIG.create(this.level);
            if (pig == null)
                return;

            pig.setPos(this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 1, this.worldPosition.getZ() + 0.5D);
            this.level.addFreshEntity(pig);
        }
    }
}
