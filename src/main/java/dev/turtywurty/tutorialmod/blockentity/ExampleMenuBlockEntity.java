package dev.turtywurty.tutorialmod.blockentity;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import dev.turtywurty.tutorialmod.menu.ExampleMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleMenuBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE =
            Component.translatable("container." + TutorialMod.MODID + ".example_menu_block");

    private final ItemStackHandler inventory = new ItemStackHandler(27) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            ExampleMenuBlockEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);

    public ExampleMenuBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.EXAMPLE_MENU_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        CompoundTag tutorialmodData = nbt.getCompound(TutorialMod.MODID);
        this.inventory.deserializeNBT(tutorialmodData.getCompound("Inventory"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        var tutorialmodData = new CompoundTag();
        tutorialmodData.put("Inventory", this.inventory.serializeNBT());
        nbt.put(TutorialMod.MODID, tutorialmodData);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? this.optional.cast() : super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    public LazyOptional<ItemStackHandler> getOptional() {
        return this.optional;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory.getStackInSlot(slot);
    }

    public void setStackInSlot(int slot, ItemStack stack) {
        this.inventory.setStackInSlot(slot, stack);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int menuId, @NotNull Inventory playerInv, @NotNull Player player) {
        return new ExampleMenu(menuId, playerInv, this);
    }
}