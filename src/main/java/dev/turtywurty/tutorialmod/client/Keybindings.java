package dev.turtywurty.tutorialmod.client;

import com.mojang.blaze3d.platform.InputConstants;
import dev.turtywurty.tutorialmod.TutorialMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class Keybindings {
    public static final Keybindings INSTANCE = new Keybindings();

    private Keybindings() {}

    private static final String CATEGORY = "key.categories." + TutorialMod.MODID;

    public final KeyMapping exampleKey = new KeyMapping(
            "key." + TutorialMod.MODID + ".example_key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_P, -1),
            CATEGORY
    );

    public final KeyMapping examplePacketKey = new KeyMapping(
            "key." + TutorialMod.MODID + ".example_packet_key",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_V, -1),
            CATEGORY
    );
}
