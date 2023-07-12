package dev.turtywurty.tutorialmod.blockentity;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ExampleAdvancedBlockEntity extends BlockEntity {
    private int counter;

    public ExampleAdvancedBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.EXAMPLE_ADVANCED_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);

        CompoundTag tutorialmodData = nbt.getCompound(TutorialMod.MODID);
        this.counter = tutorialmodData.getInt("Counter");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var tutorialmodData = new CompoundTag();
        tutorialmodData.putInt("Counter", this.counter);
        nbt.put(TutorialMod.MODID, tutorialmodData);
    }

    public int incrementCounter() {
        this.counter++;
        setChanged();
        return this.counter;
    }

    public int getCounter() {
        return this.counter;
    }
}
