package dev.turtywurty.tutorialmod.blockentity.util;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

public interface TickableBlockEntity {
    void tick();

    static <T extends BlockEntity> BlockEntityTicker<T> getTickerHelper(Level level) {
        return getTickerHelper(level, false);
    }

    static <T extends BlockEntity> BlockEntityTicker<T> getTickerHelper(Level level, boolean allowClient) {
        return level.isClientSide() && !allowClient ? null : (level0, pos0, state0, blockEntity) -> ((TickableBlockEntity)blockEntity).tick();
    }
}
