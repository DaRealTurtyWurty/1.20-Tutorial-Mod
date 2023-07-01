package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
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
}
