package dev.turtywurty.tutorialmod.blockentity;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import dev.turtywurty.tutorialmod.menu.ExampleSidedInventoryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleSidedInventoryBlockEntity extends BlockEntity implements MenuProvider {
    private static final Component TITLE =
            Component.translatable("container." + TutorialMod.MODID + ".example_sided_inventory_block");

    private final ItemStackHandler topInventory = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> topOptional = LazyOptional.of(() -> this.topInventory);

    private final ItemStackHandler sideInventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> sideOptional = LazyOptional.of(() -> this.sideInventory);

    private final ItemStackHandler bottomInventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> bottomOptional = LazyOptional.of(() -> this.bottomInventory);

    public ExampleSidedInventoryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.EXAMPLE_SIDED_INVENTORY_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        CompoundTag tutorialmodData = nbt.getCompound(TutorialMod.MODID);

        if(tutorialmodData.contains("TopInventory", Tag.TAG_COMPOUND)) {
            this.topInventory.deserializeNBT(tutorialmodData.getCompound("TopInventory"));
        }

        if(tutorialmodData.contains("SideInventory", Tag.TAG_COMPOUND)) {
            this.sideInventory.deserializeNBT(tutorialmodData.getCompound("SideInventory"));
        }

        if(tutorialmodData.contains("BottomInventory", Tag.TAG_COMPOUND)) {
            this.bottomInventory.deserializeNBT(tutorialmodData.getCompound("BottomInventory"));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        var tutorialmodData = new CompoundTag();
        tutorialmodData.put("TopInventory", this.topInventory.serializeNBT());
        tutorialmodData.put("SideInventory", this.sideInventory.serializeNBT());
        tutorialmodData.put("BottomInventory", this.bottomInventory.serializeNBT());
        nbt.put(TutorialMod.MODID, tutorialmodData);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == Direction.UP)
                return this.topOptional.cast();

            if(side == Direction.DOWN)
                return this.bottomOptional.cast();

            return this.sideOptional.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.topOptional.invalidate();
        this.sideOptional.invalidate();
        this.bottomOptional.invalidate();
    }

    @Override
    public void setChanged() {
        super.setChanged();

        if (this.level != null && this.level.isClientSide())
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    public LazyOptional<ItemStackHandler> getTopOptional() {
        return this.topOptional;
    }

    public LazyOptional<ItemStackHandler> getSideOptional() {
        return this.sideOptional;
    }

    public LazyOptional<ItemStackHandler> getBottomOptional() {
        return this.bottomOptional;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new ExampleSidedInventoryMenu(pContainerId, pPlayerInventory, this);
    }
}
