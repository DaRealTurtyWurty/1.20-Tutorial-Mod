package dev.turtywurty.tutorialmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.blockentity.ExampleAnimatedBlockEntity;
import dev.turtywurty.tutorialmod.client.animation.ExampleAnimatedBlockAnimation;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ExampleAnimatedBlockModel extends AbstractAnimatableModel {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(new ResourceLocation(TutorialMod.MODID, "example_animated_block"), "main");
    public static final ResourceLocation TEXTURE_LOCATION =
            new ResourceLocation(TutorialMod.MODID, "textures/entity/block/example_animated_block.png");

    public ExampleAnimatedBlockModel(ModelPart root) {
        super(RenderType::entitySolid, root.getChild("root"));
    }

    public static LayerDefinition createMainLayer() {
        var meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        root.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 0.0F));

        PartDefinition spikes = root.addOrReplaceChild("spikes", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        spikes.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(15, 21).addBox(-0.5F, -4.5F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -6.5F, -3.5F));
        spikes.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(10, 21).addBox(-0.5F, -4.5F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -6.5F, -3.5F));
        spikes.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(5, 21).addBox(-0.5F, -4.5F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -6.5F, 3.5F));
        spikes.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 21).addBox(-0.5F, -4.5F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -6.5F, 3.5F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public void setupAnim(ExampleAnimatedBlockEntity blockEntity) {
        getRoot().getAllParts().forEach(ModelPart::resetPose);
        animate(blockEntity.getIdleAnimationState(), ExampleAnimatedBlockAnimation.IDLE, blockEntity.getTickCount());
        animate(blockEntity.getRotateAnimationState(), ExampleAnimatedBlockAnimation.ROTATE, blockEntity.getTickCount());
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay,
                               float pRed, float pGreen, float pBlue, float pAlpha) {
        getRoot().render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
