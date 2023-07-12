package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.blockentity.ExampleAdvancedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialMod.MODID);

    public static final RegistryObject<BlockEntityType<ExampleAdvancedBlockEntity>> EXAMPLE_ADVANCED_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("example_advanced_block_entity",
                    () -> BlockEntityType.Builder.of(ExampleAdvancedBlockEntity::new, BlockInit.EXAMPLE_BLOCK_ENTITY.get())
                            .build(null));
}
