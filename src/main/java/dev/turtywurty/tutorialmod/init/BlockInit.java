package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.block.*;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MODID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANVIL)
                    .mapColor(MapColor.COLOR_GREEN)
                    .strength(5.0f, 17f)
                    .instrument(NoteBlockInstrument.BANJO)
                    .lightLevel(state -> 10)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final RegistryObject<DropExperienceBlock> EXAMPLE_ORE = BLOCKS.register("example_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE),
                    UniformInt.of(4, 7)
            ));

    public static final RegistryObject<DropExperienceBlock> DEEPSLATE_EXAMPLE_ORE = BLOCKS.register("deepslate_example_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE),
                    UniformInt.of(4, 9)
            )
    );

    public static final RegistryObject<DropExperienceBlock> NETHER_EXAMPLE_ORE = BLOCKS.register("nether_example_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.NETHER_QUARTZ_ORE),
                    UniformInt.of(10, 15)
            )
    );

    public static final RegistryObject<DropExperienceBlock> END_EXAMPLE_ORE = BLOCKS.register("end_example_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.END_STONE),
                    UniformInt.of(10, 15)
            )
    );

    public static final RegistryObject<ExampleAdvancedBlock> EXAMPLE_ADVANCED_BLOCK = BLOCKS.register("example_advanced_block",
            () -> new ExampleAdvancedBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL)
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(5.0f, 15f)
            ));

    public static final RegistryObject<ExampleTickingBlock> EXAMPLE_TICKING_BLOCK = BLOCKS.register("example_ticking_block",
            () -> new ExampleTickingBlock(BlockBehaviour.Properties.copy(Blocks.BEEHIVE)
                    .mapColor(MapColor.WARPED_STEM)
                    .strength(2.0f, 8f)
            ));

    public static final RegistryObject<ExampleItemCapBlock> EXAMPLE_ITEM_CAP_BLOCK = BLOCKS.register("example_item_cap_block",
            () -> new ExampleItemCapBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)
                    .mapColor(MapColor.QUARTZ)
                    .strength(4f)
            ));

    public static final RegistryObject<ExampleSyncedBlock> EXAMPLE_SYNCED_BLOCK = BLOCKS.register("example_synced_block",
            () -> new ExampleSyncedBlock(BlockBehaviour.Properties.copy(Blocks.BELL)
                    .mapColor(MapColor.CLAY)
                    .strength(7f)
            ));

    public static final RegistryObject<ExampleScreenBlock> EXAMPLE_SCREEN_BLOCK = BLOCKS.register("example_screen_block",
            () -> new ExampleScreenBlock(BlockBehaviour.Properties.copy(Blocks.CACTUS)));

    public static final RegistryObject<ExampleMenuBlock> EXAMPLE_MENU_BLOCK = BLOCKS.register("example_menu_block",
            () -> new ExampleMenuBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));

    public static final RegistryObject<ExampleEnergyGeneratorBlock> EXAMPLE_ENERGY_GENERATOR_BLOCK = BLOCKS.register("example_energy_generator_block",
            () -> new ExampleEnergyGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL)));

    public static final RegistryObject<ExampleSidedInventoryBlock> EXAMPLE_SIDED_INVENTORY_BLOCK = BLOCKS.register("example_sided_inventory_block",
            () -> new ExampleSidedInventoryBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL)));

    public static final RegistryObject<ExampleFluidBlock> EXAMPLE_FLUID_BLOCK = BLOCKS.register("example_fluid_block",
            () -> new ExampleFluidBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL)));

    public static final RegistryObject<ExampleBERBlock> EXAMPLE_BER_BLOCK = BLOCKS.register("example_ber_block",
            () -> new ExampleBERBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL)));
}
