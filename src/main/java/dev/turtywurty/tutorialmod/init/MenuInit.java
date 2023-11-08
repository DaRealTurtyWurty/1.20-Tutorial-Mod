package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.menu.ExampleEnergyGeneratorMenu;
import dev.turtywurty.tutorialmod.menu.ExampleFluidMenu;
import dev.turtywurty.tutorialmod.menu.ExampleMenu;
import dev.turtywurty.tutorialmod.menu.ExampleSidedInventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, TutorialMod.MODID);

    public static final RegistryObject<MenuType<ExampleMenu>> EXAMPLE_MENU = MENU_TYPES.register("example_menu",
            () -> IForgeMenuType.create(ExampleMenu::new));

    public static final RegistryObject<MenuType<ExampleEnergyGeneratorMenu>> EXAMPLE_ENERGY_GENERATOR_MENU = MENU_TYPES.register("example_energy_generator_menu",
            () -> IForgeMenuType.create(ExampleEnergyGeneratorMenu::new));

    public static final RegistryObject<MenuType<ExampleSidedInventoryMenu>> EXAMPLE_SIDED_INVENTORY_MENU = MENU_TYPES.register("example_sided_inventory_menu",
            () -> IForgeMenuType.create(ExampleSidedInventoryMenu::new));

    public static final RegistryObject<MenuType<ExampleFluidMenu>> EXAMPLE_FLUID_MENU = MENU_TYPES.register("example_fluid_menu",
            () -> IForgeMenuType.create(ExampleFluidMenu::new));
}
