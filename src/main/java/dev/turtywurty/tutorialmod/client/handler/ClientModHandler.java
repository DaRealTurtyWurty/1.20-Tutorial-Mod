package dev.turtywurty.tutorialmod.client.handler;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.client.Keybindings;
import dev.turtywurty.tutorialmod.client.screen.ExampleEnergyGeneratorScreen;
import dev.turtywurty.tutorialmod.client.screen.ExampleMenuScreen;
import dev.turtywurty.tutorialmod.client.screen.ExampleSidedInventoryScreen;
import dev.turtywurty.tutorialmod.init.MenuInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MenuInit.EXAMPLE_MENU.get(), ExampleMenuScreen::new);
            MenuScreens.register(MenuInit.EXAMPLE_ENERGY_GENERATOR_MENU.get(), ExampleEnergyGeneratorScreen::new);
            MenuScreens.register(MenuInit.EXAMPLE_SIDED_INVENTORY_MENU.get(), ExampleSidedInventoryScreen::new);
        });
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(Keybindings.INSTANCE.exampleKey);
        event.register(Keybindings.INSTANCE.examplePacketKey);
    }
}
