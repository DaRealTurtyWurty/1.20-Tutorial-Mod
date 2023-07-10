package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.entity.ExampleAnimatedEntity;
import dev.turtywurty.tutorialmod.entity.ExampleEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MODID);

    public static final RegistryObject<EntityType<ExampleEntity>> EXAMPLE_ENTITY = ENTITIES.register("example_entity",
            () -> EntityType.Builder.<ExampleEntity>of(ExampleEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(TutorialMod.MODID, "example_entity").toString())
    );
    public static final RegistryObject<EntityType<ExampleAnimatedEntity>> EXAMPLE_ANIMATED_ENTITY = ENTITIES.register("example_animated_entity",
            () -> EntityType.Builder.<ExampleAnimatedEntity>of(ExampleAnimatedEntity::new, MobCategory.CREATURE)
                    .sized(0.25f, 0.25f)
                    .build(new ResourceLocation(TutorialMod.MODID, "example_animated_entity").toString())
    );
}