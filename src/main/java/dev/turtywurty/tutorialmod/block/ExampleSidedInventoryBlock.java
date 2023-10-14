package dev.turtywurty.tutorialmod.block;

import dev.turtywurty.tutorialmod.blockentity.ExampleSidedInventoryBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleSidedInventoryBlock extends Block implements EntityBlock {
    public ExampleSidedInventoryBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityInit.EXAMPLE_SIDED_INVENTORY_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof ExampleSidedInventoryBlockEntity blockEntity))
            return InteractionResult.PASS;

        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        // open screen
        if (player instanceof ServerPlayer sPlayer) {
            sPlayer.openMenu(blockEntity, pos);
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ExampleSidedInventoryBlockEntity blockEntity) {
                blockEntity.getTopOptional().ifPresent(inventory -> {
                    for (int index = 0; index < inventory.getSlots(); index++) {
                        Block.popResource(level, pos, inventory.getStackInSlot(index));
                    }
                });

                blockEntity.getSideOptional().ifPresent(inventory -> {
                    for (int index = 0; index < inventory.getSlots(); index++) {
                        Block.popResource(level, pos, inventory.getStackInSlot(index));
                    }
                });

                blockEntity.getBottomOptional().ifPresent(inventory -> {
                    for (int index = 0; index < inventory.getSlots(); index++) {
                        Block.popResource(level, pos, inventory.getStackInSlot(index));
                    }
                });
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }
}