package dev.turtywurty.tutorialmod.block;

import dev.turtywurty.tutorialmod.blockentity.ExampleSyncedBlockEntity;
import dev.turtywurty.tutorialmod.blockentity.util.TickableBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleSyncedBlock extends Block implements EntityBlock {
    public ExampleSyncedBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityInit.EXAMPLE_SYNCED_BLOCK_ENTITY.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return TickableBlockEntity.getTickerHelper(level);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if(hand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;

        BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof ExampleSyncedBlockEntity blockEntity) {
            boolean isClient = level.isClientSide();
            if(isClient) {
                player.displayClientMessage(Component.literal("[Client] Ticks: " + blockEntity.getTickCounter()), true);
            } else {
                player.sendSystemMessage(Component.literal("[Server] Ticks: " + blockEntity.getTickCounter()));
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }
}
