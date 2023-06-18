package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.init.tiers.ModArmorMaterial;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorMaterialInit {
    public static final ModArmorMaterial EXAMPLE = new ModArmorMaterial(
            new int[] { 500, 1200, 600, 400 },
            new int[] { 11, 16, 15, 13 },
            20,
            SoundEvents.FOX_TELEPORT,
            () -> Ingredient.of(ItemInit.EXAMPLE_ITEM::get),
            "example",
            0.25f,
            0.15f
    );
}
