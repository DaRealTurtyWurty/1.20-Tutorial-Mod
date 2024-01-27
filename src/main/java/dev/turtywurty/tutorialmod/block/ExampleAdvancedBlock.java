package dev.turtywurty.tutorialmod.block;

import com.mojang.serialization.MapCodec;
import dev.turtywurty.tutorialmod.blockentity.ExampleAdvancedBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleAdvancedBlock extends HorizontalDirectionalBlock implements EntityBlock {
    private static final MapCodec<ExampleAdvancedBlock> CODEC = simpleCodec(ExampleAdvancedBlock::new);

    public ExampleAdvancedBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityInit.EXAMPLE_ADVANCED_BLOCK.get().create(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof ExampleAdvancedBlockEntity blockEntity) {
                int counter = player.isCrouching() ? blockEntity.getCounter() : blockEntity.incrementCounter();
                player.sendSystemMessage(Component.literal("BlockEntity has been used %d times".formatted(counter)));
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        }

        return super.use(state, level, pos, player, hand, hitResult);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
}
