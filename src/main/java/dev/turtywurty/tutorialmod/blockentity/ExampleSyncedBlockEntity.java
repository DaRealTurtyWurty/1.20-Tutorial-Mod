package dev.turtywurty.tutorialmod.blockentity;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.blockentity.util.TickableBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleSyncedBlockEntity extends BlockEntity implements TickableBlockEntity {
    private int tickCounter = 0;

    public ExampleSyncedBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.EXAMPLE_SYNCED_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void tick() {
        if(this.level == null || this.level.isClientSide())
            return;

        this.tickCounter++;
        setChanged();
        // sync to the client
        this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var tutorialmodData = new CompoundTag();
        tutorialmodData.putInt("TickCounter", this.tickCounter);
        nbt.put(TutorialMod.MODID, tutorialmodData);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);

        CompoundTag tutorialmodData = nbt.getCompound(TutorialMod.MODID);
        this.tickCounter = tutorialmodData.getInt("TickCounter");
    }

    public int getTickCounter() {
        return this.tickCounter;
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
}
