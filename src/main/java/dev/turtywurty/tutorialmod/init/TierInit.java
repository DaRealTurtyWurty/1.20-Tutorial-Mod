package dev.turtywurty.tutorialmod.init;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class TierInit {
    public static final ForgeTier EXAMPLE = new ForgeTier(
            4,
            1800,
            1.5f,
            7,
            20,
            TagInit.NEEDS_EXAMPLE_TOOL,
            () -> Ingredient.of(ItemInit.EXAMPLE_ITEM::get)
    );
}
