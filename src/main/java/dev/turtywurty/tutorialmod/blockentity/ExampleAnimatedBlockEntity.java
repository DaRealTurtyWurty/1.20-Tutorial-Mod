package dev.turtywurty.tutorialmod.blockentity;

import dev.turtywurty.tutorialmod.blockentity.util.TickableBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleAnimatedBlockEntity extends BlockEntity implements TickableBlockEntity {
    private final AnimationState idleAnimation = new AnimationState();
    private final AnimationState rotateAnimation = new AnimationState();

    private int tickCount;
    private boolean isRotating;

    public ExampleAnimatedBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.EXAMPLE_ANIMATED_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void tick() {
        if(this.level != null && this.level.isClientSide()) {
            this.idleAnimation.animateWhen(!this.isRotating, this.tickCount);
            this.rotateAnimation.animateWhen(this.isRotating, this.tickCount);

            this.tickCount++;

            if(this.tickCount == Integer.MAX_VALUE) {
                this.tickCount = 0;
            }
        }

        // Tick Logic
    }

    public int getTickCount() {
        return this.tickCount;
    }

    public boolean isRotating() {
        return this.isRotating;
    }

    public AnimationState getIdleAnimationState() {
        return this.idleAnimation;
    }

    public AnimationState getRotateAnimationState() {
        return this.rotateAnimation;
    }

    public void updateRotateState() {
        this.isRotating = !this.isRotating;
        update();
    }

    private void update() {
        setChanged();
        if(this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putBoolean("IsRotating", this.isRotating);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        this.isRotating = pTag.getBoolean("IsRotating");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }
}
