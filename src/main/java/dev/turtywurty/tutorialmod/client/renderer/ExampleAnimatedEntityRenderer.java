package dev.turtywurty.tutorialmod.client.renderer;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.client.model.ExampleAnimatedEntityModel;
import dev.turtywurty.tutorialmod.entity.ExampleAnimatedEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ExampleAnimatedEntityRenderer extends MobRenderer<ExampleAnimatedEntity, ExampleAnimatedEntityModel> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/entity/example_animated_entity.png");

    public ExampleAnimatedEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ExampleAnimatedEntityModel(ctx.bakeLayer(ExampleAnimatedEntityModel.LAYER_LOCATION)), 0.25F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ExampleAnimatedEntity entity) {
        return TEXTURE;
    }
}
