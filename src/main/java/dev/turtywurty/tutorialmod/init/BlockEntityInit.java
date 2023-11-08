package dev.turtywurty.tutorialmod.init;

import com.google.common.base.Equivalence;
import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.blockentity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TutorialMod.MODID);

    public static final RegistryObject<BlockEntityType<ExampleAdvancedBlockEntity>> EXAMPLE_ADVANCED_BLOCK =
            BLOCK_ENTITIES.register("example_advanced_block",
                    () -> BlockEntityType.Builder.of(ExampleAdvancedBlockEntity::new, BlockInit.EXAMPLE_ADVANCED_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ExampleTickingBlockEntity>> EXAMPLE_TICKING_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("example_ticking_block",
                    () -> BlockEntityType.Builder.of(ExampleTickingBlockEntity::new, BlockInit.EXAMPLE_TICKING_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ExampleItemCapBlockEntity>> EXAMPLE_ITEM_CAP_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("example_item_cap_block",
                    () -> BlockEntityType.Builder.of(ExampleItemCapBlockEntity::new, BlockInit.EXAMPLE_ITEM_CAP_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ExampleSyncedBlockEntity>> EXAMPLE_SYNCED_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("example_synced_block",
                    () -> BlockEntityType.Builder.of(ExampleSyncedBlockEntity::new, BlockInit.EXAMPLE_SYNCED_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ExampleScreenBlockEntity>> EXAMPLE_SCREEN_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("example_screen_block",
                    () -> BlockEntityType.Builder.of(ExampleScreenBlockEntity::new, BlockInit.EXAMPLE_SCREEN_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ExampleMenuBlockEntity>> EXAMPLE_MENU_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("example_menu_block",
                    () -> BlockEntityType.Builder.of(ExampleMenuBlockEntity::new, BlockInit.EXAMPLE_MENU_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ExampleEnergyGeneratorBlockEntity>> EXAMPLE_ENERGY_GENERATOR_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("example_energy_generator_block",
                    () -> BlockEntityType.Builder.of(ExampleEnergyGeneratorBlockEntity::new, BlockInit.EXAMPLE_ENERGY_GENERATOR_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ExampleSidedInventoryBlockEntity>> EXAMPLE_SIDED_INVENTORY_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("example_sided_inventory_block",
                    () -> BlockEntityType.Builder.of(ExampleSidedInventoryBlockEntity::new, BlockInit.EXAMPLE_SIDED_INVENTORY_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ExampleFluidBlockEntity>> EXAMPLE_FLUID_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("example_fluid_block",
                    () -> BlockEntityType.Builder.of(ExampleFluidBlockEntity::new, BlockInit.EXAMPLE_FLUID_BLOCK.get())
                            .build(null));
}
