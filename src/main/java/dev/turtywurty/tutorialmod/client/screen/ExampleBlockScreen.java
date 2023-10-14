package dev.turtywurty.tutorialmod.client.screen;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.blockentity.ExampleScreenBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class ExampleBlockScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + TutorialMod.MODID + ".example_block_screen");
    private static final Component EXAMPLE_BUTTON =
            Component.translatable("gui." + TutorialMod.MODID + ".example_block_screen.button.example_button");

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TutorialMod.MODID, "textures/gui/example_block.png");

    private final BlockPos position;
    private final int imageWidth, imageHeight;

    private ExampleScreenBlockEntity blockEntity;
    private int leftPos, topPos;

    private Button button;

    public ExampleBlockScreen(BlockPos position) {
        super(TITLE);

        this.position = position;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if(this.minecraft == null) return;
        Level level = this.minecraft.level;
        if(level == null) return;

        BlockEntity be = level.getBlockEntity(this.position);
        if(be instanceof ExampleScreenBlockEntity blockEntity) {
            this.blockEntity = blockEntity;
        } else {
            System.err.printf("BlockEntity at %s is not of type ExampleScreenBlockEntity!\n", this.position);
            return;
        }

        this.button = addRenderableWidget(
                Button.builder(
                        EXAMPLE_BUTTON,
                        this::handleExampleButton)
                        .bounds(this.leftPos + 8, this.topPos + 20, 80, 20)
                        .tooltip(Tooltip.create(EXAMPLE_BUTTON))
                        .build());
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderTransparentBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font,
                TITLE,
                this.leftPos + 8,
                this.topPos + 8,
                0x404040,
                false);

        graphics.drawString(this.font,
                "Seconds Existed: %d".formatted(this.blockEntity.getSecondsExisted()),
                this.leftPos + 8,
                this.topPos + 44,
                0xFF0000,
                false);
    }

    private void handleExampleButton(Button button) {
        // logic here
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
