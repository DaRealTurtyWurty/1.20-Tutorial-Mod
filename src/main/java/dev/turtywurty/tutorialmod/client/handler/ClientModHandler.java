package dev.turtywurty.tutorialmod.client.handler;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.client.Keybindings;
import dev.turtywurty.tutorialmod.client.model.ExampleAnimatedEntityModel;
import dev.turtywurty.tutorialmod.client.model.ExampleEntityModel;
import dev.turtywurty.tutorialmod.client.renderer.ExampleAnimatedEntityRenderer;
import dev.turtywurty.tutorialmod.client.renderer.ExampleBER;
import dev.turtywurty.tutorialmod.client.renderer.ExampleEntityRenderer;
import dev.turtywurty.tutorialmod.client.renderer.ExampleFluidBER;
import dev.turtywurty.tutorialmod.client.screen.*;
import dev.turtywurty.tutorialmod.init.BlockEntityInit;
import dev.turtywurty.tutorialmod.init.EntityInit;
import dev.turtywurty.tutorialmod.init.MenuInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
            MenuScreens.register(MenuInit.EXAMPLE_FLUID_MENU.get(), ExampleFluidScreen::new);
            MenuScreens.register(MenuInit.EXAMPLE_BER_MENU.get(), ExampleBERScreen::new);
            MenuScreens.register(MenuInit.EXAMPLE_FLUID_BER_MENU.get(), ExampleFluidBERScreen::new);
        });
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(Keybindings.INSTANCE.exampleKey);
        event.register(Keybindings.INSTANCE.examplePacketKey);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Entities
        event.registerEntityRenderer(EntityInit.EXAMPLE_ENTITY.get(), ExampleEntityRenderer::new);
        event.registerEntityRenderer(EntityInit.EXAMPLE_ANIMATED_ENTITY.get(), ExampleAnimatedEntityRenderer::new);

        // Block Entities
        event.registerBlockEntityRenderer(BlockEntityInit.EXAMPLE_BER_BLOCK_ENTITY.get(), ExampleBER::new);
        event.registerBlockEntityRenderer(BlockEntityInit.EXAMPLE_FLUID_BER_BLOCK_ENTITY.get(), ExampleFluidBER::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ExampleEntityModel.LAYER_LOCATION, ExampleEntityModel::createBodyLayer);
        event.registerLayerDefinition(ExampleAnimatedEntityModel.LAYER_LOCATION, ExampleAnimatedEntityModel::createBodyLayer);
    }
}
