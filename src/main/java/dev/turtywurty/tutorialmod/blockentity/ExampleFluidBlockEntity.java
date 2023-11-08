package dev.turtywurty.tutorialmod.blockentity;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.blockentity.util.TickableBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import dev.turtywurty.tutorialmod.menu.ExampleFluidMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleFluidBlockEntity extends BlockEntity implements TickableBlockEntity, MenuProvider {
    private static final Component TITLE =
            Component.translatable("container." + TutorialMod.MODID + ".example_fluid");

    private final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            ExampleFluidBlockEntity.this.sendUpdate();
        }
    };

    private final LazyOptional<ItemStackHandler> inventoryOptional = LazyOptional.of(() -> this.inventory);

    private final FluidTank fluidTank = new FluidTank(10000) {
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            ExampleFluidBlockEntity.this.sendUpdate();
        }
    };

    private final LazyOptional<FluidTank> fluidOptional = LazyOptional.of(() -> this.fluidTank);

    public ExampleFluidBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.EXAMPLE_FLUID_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide())
            return;

        ItemStack stack = this.inventory.getStackInSlot(0);
        if(stack.isEmpty())
            return;

        if(this.fluidTank.getFluidAmount() >= this.fluidTank.getCapacity())
            return;

        LazyOptional<IFluidHandlerItem> fluidHandler = stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM);
        fluidHandler.ifPresent(iFluidHandlerItem -> {
            if(!this.fluidTank.getFluid().isFluidEqual(iFluidHandlerItem.getFluidInTank(0)))
                return;

            int amountToDrain = this.fluidTank.getCapacity() - this.fluidTank.getFluidAmount();
            int amount = iFluidHandlerItem.drain(amountToDrain, IFluidHandler.FluidAction.SIMULATE).getAmount();
            if(amount > 0) {
                this.fluidTank.fill(iFluidHandlerItem.drain(amountToDrain, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);

                if(amount <= amountToDrain) {
                    this.inventory.setStackInSlot(0, iFluidHandlerItem.getContainer());
                }
            }
        });
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("Inventory", this.inventory.serializeNBT());
        pTag.put("FluidTank", this.fluidTank.writeToNBT(new CompoundTag()));
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        this.inventory.deserializeNBT(pTag.getCompound("Inventory"));
        this.fluidTank.readFromNBT(pTag.getCompound("FluidTank"));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return this.inventoryOptional.cast();

        if(cap == ForgeCapabilities.FLUID_HANDLER)
            return this.fluidOptional.cast();

        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.inventoryOptional.invalidate();
        this.fluidOptional.invalidate();
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new ExampleFluidMenu(pContainerId, pPlayerInventory, this);
    }

    private void sendUpdate() {
        setChanged();

        if (this.level != null)
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    public LazyOptional<ItemStackHandler> getInventoryOptional() {
        return this.inventoryOptional;
    }

    public LazyOptional<FluidTank> getFluidOptional() {
        return this.fluidOptional;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public FluidTank getFluidTank() {
        return this.fluidTank;
    }
}
