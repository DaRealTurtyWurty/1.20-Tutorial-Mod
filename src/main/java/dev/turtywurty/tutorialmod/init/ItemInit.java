package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static dev.turtywurty.tutorialmod.init.CreativeTabInit.addToTab;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = addToTab(ITEMS.register("example_item",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
                    .rarity(Rarity.EPIC)
            )));

    public static final RegistryObject<BlockItem> EXAMPLE_BLOCK_ITEM = addToTab(ITEMS.register("example_block",
            () -> new BlockItem(BlockInit.EXAMPLE_BLOCK.get(),
                    new Item.Properties()
                            .rarity(Rarity.UNCOMMON)
            )));

    public static final RegistryObject<SwordItem> EXAMPLE_SWORD = addToTab(ITEMS.register("example_sword",
            () -> new SwordItem(
                    TierInit.EXAMPLE,
                    7,
                    2.5f,
                    new Item.Properties()
            )));

    public static final RegistryObject<PickaxeItem> EXAMPLE_PICKAXE = addToTab(ITEMS.register("example_pickaxe",
            () -> new PickaxeItem(
                    TierInit.EXAMPLE,
                    7,
                    2.5f,
                    new Item.Properties()
            )));

    public static final RegistryObject<ShovelItem> EXAMPLE_SHOVEL = addToTab(ITEMS.register("example_shovel",
            () -> new ShovelItem(
                    TierInit.EXAMPLE,
                    7,
                    2.5f,
                    new Item.Properties()
            )));

    public static final RegistryObject<AxeItem> EXAMPLE_AXE = addToTab(ITEMS.register("example_axe",
            () -> new AxeItem(
                    TierInit.EXAMPLE,
                    7,
                    2.5f,
                    new Item.Properties()
            )));

    public static final RegistryObject<HoeItem> EXAMPLE_HOE = addToTab(ITEMS.register("example_hoe",
            () -> new HoeItem(
                    TierInit.EXAMPLE,
                    7,
                    2.5f,
                    new Item.Properties()
            )));

    public static final RegistryObject<ArmorItem> EXAMPLE_HELMET = addToTab(ITEMS.register("example_helmet",
            () -> new ArmorItem(
                    ArmorMaterialInit.EXAMPLE,
                    ArmorItem.Type.HELMET,
                    new Item.Properties()
            )));

    public static final RegistryObject<ArmorItem> EXAMPLE_CHESTPLATE = addToTab(ITEMS.register("example_chestplate",
            () -> new ArmorItem(
                    ArmorMaterialInit.EXAMPLE,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()
            )));

    public static final RegistryObject<ArmorItem> EXAMPLE_LEGGINGS = addToTab(ITEMS.register("example_leggings",
            () -> new ArmorItem(
                    ArmorMaterialInit.EXAMPLE,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties()
            )));

    public static final RegistryObject<ArmorItem> EXAMPLE_BOOTS = addToTab(ITEMS.register("example_boots",
            () -> new ArmorItem(
                    ArmorMaterialInit.EXAMPLE,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties()
            )));

    public static final RegistryObject<ForgeSpawnEggItem> EXAMPLE_SPAWN_EGG = addToTab(ITEMS.register("example_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.EXAMPLE_ENTITY, 0xF0ABD1, 0xAE4C82, new Item.Properties())));

    public static final RegistryObject<ForgeSpawnEggItem> EXAMPLE_ANIMATED_SPAWN_EGG = addToTab(ITEMS.register("example_animated_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.EXAMPLE_ANIMATED_ENTITY, 0x82BA9C, 0x4C8E6C, new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_ORE_ITEM = addToTab(ITEMS.register("example_ore",
            () -> new BlockItem(BlockInit.EXAMPLE_ORE.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> DEEPSLATE_EXAMPLE_ORE_ITEM = addToTab(ITEMS.register("deepslate_example_ore",
            () -> new BlockItem(BlockInit.DEEPSLATE_EXAMPLE_ORE.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> NETHER_EXAMPLE_ORE_ITEM = addToTab(ITEMS.register("nether_example_ore",
            () -> new BlockItem(BlockInit.NETHER_EXAMPLE_ORE.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> END_EXAMPLE_ORE_ITEM = addToTab(ITEMS.register("end_example_ore",
            () -> new BlockItem(BlockInit.END_EXAMPLE_ORE.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_ADVANCED_BLOCK_ITEM = addToTab(ITEMS.register("example_advanced_block",
            () -> new BlockItem(BlockInit.EXAMPLE_ADVANCED_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_TICKING_BLOCK_ITEM = addToTab(ITEMS.register("example_ticking_block",
            () -> new BlockItem(BlockInit.EXAMPLE_TICKING_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_ITEM_CAP_BLOCK_ITEM = addToTab(ITEMS.register("example_item_cap_block",
            () -> new BlockItem(BlockInit.EXAMPLE_ITEM_CAP_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_SYNCED_BLOCK_ITEM = addToTab(ITEMS.register("example_synced_block",
            () -> new BlockItem(BlockInit.EXAMPLE_SYNCED_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_SCREEN_BLOCK_ITEM = addToTab(ITEMS.register("example_screen_block",
            () -> new BlockItem(BlockInit.EXAMPLE_SCREEN_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_MENU_BLOCK_ITEM = addToTab(ITEMS.register("example_menu_block",
            () -> new BlockItem(BlockInit.EXAMPLE_MENU_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_ENERGY_GENERATOR_BLOCK_ITEM = addToTab(ITEMS.register("example_energy_generator_block",
            () -> new BlockItem(BlockInit.EXAMPLE_ENERGY_GENERATOR_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_SIDED_INVENTORY_BLOCK_ITEM = addToTab(ITEMS.register("example_sided_inventory_block",
            () -> new BlockItem(BlockInit.EXAMPLE_SIDED_INVENTORY_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_FLUID_BLOCK_ITEM = addToTab(ITEMS.register("example_fluid_block",
            () -> new BlockItem(BlockInit.EXAMPLE_FLUID_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_BER_BLOCK_ITEM = addToTab(ITEMS.register("example_ber_block",
            () -> new BlockItem(BlockInit.EXAMPLE_BER_BLOCK.get(), new Item.Properties())));

    public static final RegistryObject<BlockItem> EXAMPLE_FLUID_BER_BLOCK_ITEM = addToTab(ITEMS.register("example_fluid_ber_block",
            () -> new BlockItem(BlockInit.EXAMPLE_FLUID_BER_BLOCK.get(), new Item.Properties())));
}
