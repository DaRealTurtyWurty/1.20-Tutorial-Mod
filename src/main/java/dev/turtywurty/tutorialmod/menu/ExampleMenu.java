package dev.turtywurty.tutorialmod.menu;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.blockentity.ExampleMenuBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.MenuInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ExampleMenu extends AbstractContainerMenu {
    private final ExampleMenuBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;

    // Client Constructor
    public ExampleMenu(int menuId, Inventory playerInv, FriendlyByteBuf additionalData) {
        this(menuId, playerInv, playerInv.player.level().getBlockEntity(additionalData.readBlockPos()));
    }

    // Server Constructor
    public ExampleMenu(int menuId, Inventory playerInv, BlockEntity blockEntity) {
        super(MenuInit.EXAMPLE_MENU.get(), menuId);
        if(blockEntity instanceof ExampleMenuBlockEntity be) {
            this.blockEntity = be;
        } else {
            throw new IllegalStateException("Incorrect block entity class (%s) passed into ExampleMenu!"
                    .formatted(blockEntity.getClass().getCanonicalName()));
        }

        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        createPlayerHotbar(playerInv);
        createPlayerInventory(playerInv);
        createBlockEntityInventory(be);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot fromSlot = getSlot(index);
        ItemStack fromStack = fromSlot.getItem();

        if(fromStack.getCount() <= 0)
            fromSlot.set(ItemStack.EMPTY);

        if(!fromSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack copyFromStack = fromStack.copy();

        if(index < 36) {
            // We are inside of the player's inventory
            if(!moveItemStackTo(fromStack, 36, 63, false))
                return ItemStack.EMPTY;
        } else if(index < 63) {
            // We are inside of our inventory
            if(!moveItemStackTo(fromStack, 0, 36, false))
                return ItemStack.EMPTY;
        } else {
            // We are not in the player's inventory and we are not in our inventory
            System.err.println("Invalid slot index: " + index);
            return ItemStack.EMPTY;
        }

        fromSlot.setChanged();
        fromSlot.onTake(player, fromStack);

        return copyFromStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.levelAccess, player, BlockInit.EXAMPLE_MENU_BLOCK.get());
    }

    private void createPlayerInventory(Inventory inv) {
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 9; column++) {
                addSlot(new Slot(inv, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
    }

    private void createPlayerHotbar(Inventory inv) {
        for(int column = 0; column < 9; column++) {
            addSlot(new Slot(inv, column, 8 + column * 18, 142));
        }
    }

    private void createBlockEntityInventory(ExampleMenuBlockEntity blockEntity) {
        blockEntity.getOptional().ifPresent(inventory -> {
            for(int row = 0; row < 3; row++) {
                for(int column = 0; column < 9; column++) {
                    addSlot(new SlotItemHandler(inventory, column + row * 9, 8 + column * 18, 18 + row * 18));
                }
            }
        });
    }

    public ExampleMenuBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
