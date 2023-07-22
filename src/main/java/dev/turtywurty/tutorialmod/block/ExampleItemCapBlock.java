package dev.turtywurty.tutorialmod.block;

import dev.turtywurty.tutorialmod.blockentity.ExampleItemCapBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleItemCapBlock extends Block implements EntityBlock {
    public ExampleItemCapBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityInit.EXAMPLE_ITEM_CAP_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof ExampleItemCapBlockEntity blockEntity) {
                ItemStack stack = player.getItemInHand(hand);
                ItemStackHandler inventory = blockEntity.getInventory();
                if(stack.isEmpty()) {
                    // Get the item from the block entity and give it to the player
                    if (inventory.getStackInSlot(0).isEmpty()) {
                        player.sendSystemMessage(Component.literal("No item in block entity!"));
                        return InteractionResult.SUCCESS;
                    }

                    ItemStack extracted = inventory.extractItem(0, player.isCrouching() ? inventory.getSlotLimit(0) : 1, false);
                    player.setItemInHand(hand, extracted);
                } else {
                    // Set the item in the block entity to the item in the player's hand (assuming the blockentity can store the item)
                    ItemStack toInsert = stack.copy();
                    toInsert.setCount(1);

                    ItemStack leftover = inventory.insertItem(0, toInsert, false);

                    ItemStack remainder = stack.copy();
                    remainder.setCount(remainder.getCount() - 1);
                    remainder.grow(leftover.getCount());
                    player.setItemInHand(hand, remainder);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if(!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof ExampleItemCapBlockEntity blockEntity) {
                ItemStackHandler inventory = blockEntity.getInventory();
                for (int index = 0; index < inventory.getSlots(); index++) {
                    ItemStack stack = inventory.getStackInSlot(index);
                    var entity = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack);
                    level.addFreshEntity(entity);
                }
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}
