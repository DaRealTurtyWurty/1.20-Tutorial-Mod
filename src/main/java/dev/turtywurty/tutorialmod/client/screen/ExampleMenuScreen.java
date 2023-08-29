package dev.turtywurty.tutorialmod.client.screen;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.menu.ExampleMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class ExampleMenuScreen extends AbstractContainerScreen<ExampleMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TutorialMod.MODID, "textures/gui/example_menu_screen.png");

    public ExampleMenuScreen(ExampleMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        renderBackground(guiGraphics);
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
