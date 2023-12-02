package dev.turtywurty.tutorialmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.turtywurty.tutorialmod.blockentity.ExampleBERBlockEntity;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.NotNull;

public class ExampleBER implements BlockEntityRenderer<ExampleBERBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public ExampleBER(BlockEntityRendererProvider.Context ctx) {
        this.context = ctx;
    }

    @Override
    public void render(@NotNull ExampleBERBlockEntity pBlockEntity, float pPartialTick, @NotNull PoseStack pPoseStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemStack stack = pBlockEntity.getInventory().getStackInSlot(0);
        if(stack.isEmpty())
            return;

        Level level = pBlockEntity.getLevel();
        if (level == null)
            return;

        BlockPos pos = pBlockEntity.getBlockPos().above();

        double offset = Math.sin((pBlockEntity.getLevel().getGameTime() + pPartialTick) / 10.0) / 6.0;
        double rotation = Math.sin((pBlockEntity.getLevel().getGameTime() + pPartialTick) / 20.0) * 40.0;
        double scale = 0.75 + Math.sin((pBlockEntity.getLevel().getGameTime() + pPartialTick) / 10.0) / 4.0;

        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 1.3 + offset, 0.5);
        pPoseStack.scale((float) scale, (float) scale, (float) scale);
        pPoseStack.mulPose(Axis.YP.rotationDegrees((float) rotation));
        this.context.getItemRenderer().renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                LightTexture.pack(
                        level.getBrightness(LightLayer.BLOCK, pos),
                        level.getBrightness(LightLayer.SKY, pos)),
                pPackedOverlay,
                pPoseStack,
                pBuffer,
                level,
                0
        );

        Font font = context.getFont();
        pPoseStack.scale(0.05f, -0.05f, 0.05f);
        pPoseStack.translate(-10f + font.width(stack.getCount() + "") / 2f, -15.0f, 0.0f);
        font.drawInBatch(
                stack.getCount() + "",
                0f,
                0f,
                0xECECEC,
                false,
                pPoseStack.last().pose(),
                pBuffer,
                Font.DisplayMode.NORMAL,
                0,
                LightTexture.pack(
                        level.getBrightness(LightLayer.BLOCK, pos),
                        level.getBrightness(LightLayer.SKY, pos))
        );

        pPoseStack.popPose();
    }
}
