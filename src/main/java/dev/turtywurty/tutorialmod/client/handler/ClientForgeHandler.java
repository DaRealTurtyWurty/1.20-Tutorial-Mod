package dev.turtywurty.tutorialmod.client.handler;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.client.Keybindings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler {
    private static final Component EXAMPLE_KEY_PRESSED =
            Component.translatable("message." + TutorialMod.MODID + ".example_key_pressed");

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if(Keybindings.INSTANCE.exampleKey.consumeClick() && minecraft.player != null) {
            minecraft.player.displayClientMessage(EXAMPLE_KEY_PRESSED, false);
        }

        if(Keybindings.INSTANCE.examplePacketKey.consumeClick() && minecraft.player != null) {
            minecraft.player.displayClientMessage(EXAMPLE_KEY_PRESSED, true);
            // TODO: Send a packet to the server to spawn an entity
        }
    }
}
