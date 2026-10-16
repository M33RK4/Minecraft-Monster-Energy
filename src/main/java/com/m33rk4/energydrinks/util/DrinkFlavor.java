package com.m33rk4.energydrinks.util;

import net.minecraft.world.item.DyeColor;

// Want a new flavor? Add it here, then add a texture, model, recipe and a name in en_us.json.
// The item and creative tab pick it up by themselves.
public enum DrinkFlavor {
    GREEN("green", DyeColor.LIME),
    BLUE("blue", DyeColor.BLUE),
    RED("red", DyeColor.RED),
    WHITE("white", DyeColor.WHITE),
    PURPLE("purple", DyeColor.PURPLE),
    ORANGE("orange", DyeColor.ORANGE),
    PINK("pink", DyeColor.PINK),
    BLACK("black", DyeColor.BLACK);

    private final String colorName;
    private final DyeColor dye;

    DrinkFlavor(String colorName, DyeColor dye) {
        this.colorName = colorName;
        this.dye = dye;
    }

    public String itemId() {
        return colorName + "_energy";
    }

    public DyeColor dye() {
        return dye;
    }
}
