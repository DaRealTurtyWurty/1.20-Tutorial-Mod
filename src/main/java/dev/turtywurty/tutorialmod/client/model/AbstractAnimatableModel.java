package dev.turtywurty.tutorialmod.client.model;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * A model that can be animated (usable with anything, not just limited to entities like {@link HierarchicalModel})
 */
public abstract class AbstractAnimatableModel extends Model {
    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    private final ModelPart root;

    /**
     * Creates a new animatable model
     *
     * @param pRenderType The render type of the model
     * @param root        The root part of the model
     */
    public AbstractAnimatableModel(Function<ResourceLocation, RenderType> pRenderType, ModelPart root) {
        super(pRenderType);
        this.root = root;
    }

    /**
     * Animates the model with the given animation definition (sequence of keyframes)
     *
     * @param pModel               The model to animate
     * @param pAnimationDefinition The animation definition
     * @param pAccumulatedTime     The accumulated time in milliseconds
     * @param pScale               The scale of the animation
     * @param pAnimationVecCache   The animation vector cache
     */
    public static void animate(AbstractAnimatableModel pModel, AnimationDefinition pAnimationDefinition, long pAccumulatedTime, float pScale, Vector3f pAnimationVecCache) {
        float elapsedSeconds = getElapsedSeconds(pAnimationDefinition, pAccumulatedTime);

        for (Map.Entry<String, List<AnimationChannel>> entry : pAnimationDefinition.boneAnimations().entrySet()) {
            // Find a part with the name of the entry
            Optional<ModelPart> optPart = pModel.getAnyDescendantWithName(entry.getKey());
            if (optPart.isEmpty())
                continue;

            ModelPart part = optPart.get();
            List<AnimationChannel> animations = entry.getValue();
            for (AnimationChannel animation : animations) {
                if (animation == null)
                    continue;

                // Get the keyframes of the animation
                Keyframe[] keyframes = animation.keyframes();

                // Find the current and next keyframes by comparing if the elapsed time is less than the timestamp
                int foundIndex = Mth.binarySearch(
                        0,
                        keyframes.length,
                        threshold -> elapsedSeconds <= keyframes[threshold].timestamp());
                int currentFrameIdx = Math.max(0, foundIndex - 1);
                int nextFrameIdx = Math.min(keyframes.length - 1, currentFrameIdx + 1);

                // Get the current and next keyframes
                Keyframe currentFrame = keyframes[currentFrameIdx];
                Keyframe nextFrame = keyframes[nextFrameIdx];

                // Calculate the progress between the current and next keyframes
                float timeLeft = elapsedSeconds - currentFrame.timestamp();
                float progress = Mth.clamp(
                        timeLeft / (nextFrame.timestamp() - currentFrame.timestamp()),
                        0.0F,
                        1.0F);

                // Interpolate the keyframes
                nextFrame.interpolation().apply(
                        pAnimationVecCache,
                        progress,
                        keyframes,
                        currentFrameIdx,
                        nextFrameIdx,
                        pScale);

                // Apply the interpolated keyframes to the part
                animation.target().apply(part, pAnimationVecCache);
            }
        }
    }

    protected void animate(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks) {
        animate(pAnimationState, pAnimationDefinition, pAgeInTicks, 1.0F);
    }

    protected void animate(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks, float pSpeed) {
        pAnimationState.updateTime(pAgeInTicks, pSpeed);
        pAnimationState.ifStarted((state) ->
                animate(this, pAnimationDefinition, state.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE));
    }

    /**
     * Gets the elapsed seconds of the animation
     *
     * @param pAnimationDefinition The animation definition
     * @param pAccumulatedTime     The accumulated time in milliseconds
     * @return The elapsed seconds
     */
    public static float getElapsedSeconds(AnimationDefinition pAnimationDefinition, long pAccumulatedTime) {
        float secondsAccumulated = (float) pAccumulatedTime / 1000.0F;

        return pAnimationDefinition.looping()
                ? secondsAccumulated % pAnimationDefinition.lengthInSeconds()
                : secondsAccumulated;
    }

    /**
     * Gets the root part of the model
     *
     * @return The root part
     */
    public ModelPart getRoot() {
        return this.root;
    }

    /**
     * Gets a descendant part of the model with the given name
     *
     * @param name The name of the descendant
     * @return The descendant part
     */
    public Optional<ModelPart> getAnyDescendantWithName(String name) {
        return name.equals("root") ? Optional.of(getRoot()) : getRoot()
                .getAllParts()
                .filter(part -> part.hasChild(name))
                .findFirst()
                .map(part -> part.getChild(name));
    }
}