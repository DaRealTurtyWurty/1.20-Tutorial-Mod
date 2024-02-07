package dev.turtywurty.tutorialmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.turtywurty.tutorialmod.blockentity.ExampleAnimatedBlockEntity;
import dev.turtywurty.tutorialmod.client.model.ExampleAnimatedBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class ExampleAnimatedBER implements BlockEntityRenderer<ExampleAnimatedBlockEntity> {
    private final BlockEntityRendererProvider.Context context;
    private final ExampleAnimatedBlockModel model;

    public ExampleAnimatedBER(BlockEntityRendererProvider.Context context) {
        this.context = context;
        this.model = new ExampleAnimatedBlockModel(context.bakeLayer(ExampleAnimatedBlockModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull ExampleAnimatedBlockEntity pBlockEntity, float pPartialTick, @NotNull PoseStack pPoseStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
        pPoseStack.translate(0.5, -1.5, -0.5);
        this.model.setupAnim(pBlockEntity);
        this.model.renderToBuffer(pPoseStack, pBuffer.getBuffer(this.model.renderType(ExampleAnimatedBlockModel.TEXTURE_LOCATION)),
                pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 0.35, 0.5);
        pPoseStack.scale(0.25F, 0.25F, 0.25F);
        if(pBlockEntity.isRotating()) {
            pPoseStack.mulPose(Axis.YN.rotationDegrees((pBlockEntity.getLevel().getGameTime() + pPartialTick) * 12));
        }
        this.context.getItemRenderer().renderStatic(Items.APPLE.getDefaultInstance(), ItemDisplayContext.FIXED, pPackedLight, pPackedOverlay, pPoseStack, pBuffer, pBlockEntity.getLevel(), 0);
        pPoseStack.popPose();
    }
}
