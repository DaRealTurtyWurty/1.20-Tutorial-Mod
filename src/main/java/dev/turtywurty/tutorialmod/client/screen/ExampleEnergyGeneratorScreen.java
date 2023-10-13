package dev.turtywurty.tutorialmod.client.screen;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.menu.ExampleEnergyGeneratorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class ExampleEnergyGeneratorScreen extends AbstractContainerScreen<ExampleEnergyGeneratorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TutorialMod.MODID, "textures/gui/example_energy_generator_screen.png");

    public ExampleEnergyGeneratorScreen(ExampleEnergyGeneratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        renderTransparentBackground(pGuiGraphics);
        pGuiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        int energyScaled = this.menu.getEnergyStoredScaled();

        // AARRGGBB

        // background
        pGuiGraphics.fill(
                this.leftPos + 115,
                this.topPos + 20,
                this.leftPos + 131,
                this.topPos + 60,
                0xFF555555);

        // foreground
        pGuiGraphics.fill(
                this.leftPos + 116,
                this.topPos + 21 + (38 - energyScaled),
                this.leftPos + 130,
                this.topPos + 59,
                0xFFCC2222
        );
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);

        int energyStored = this.menu.getEnergy();
        int maxEnergy = this.menu.getMaxEnergy();

        Component text = Component.literal("Energy: " + energyStored + " / " + maxEnergy);
        if(isHovering(115, 20, 16, 40, pMouseX, pMouseY)) {
            pGuiGraphics.renderTooltip(this.font, text, pMouseX, pMouseY);
        }
    }
}
