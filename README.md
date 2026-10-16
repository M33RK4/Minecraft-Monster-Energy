# Monster Energy (Minecraft mod)

A small NeoForge mod for Minecraft 1.21.1 that adds energy drinks to the game. Drink one and you'll mine and run faster for a while, but when it wears off, you crash.

**Heads up:** the mod isn't finished yet. Most of the textures are still placeholders and will be replaced, and the crafting recipes will change in the future too.

## What's in it

Eight flavors: Green, Blue, Red, White, Purple, Orange, Pink and Black. Each one leaves you with an Empty Can after you drink it.

A can gives you **Speed I** and **Haste I** for 3 minutes and fills a bit of hunger. You can drink it even on a full hunger bar.

Once the boost runs out you get **Slowness I** and **Hunger I** for 20 seconds. Drinking another can before then pushes the crash back, and milk gets rid of it completely.

## Installing

1. Install **NeoForge for Minecraft 1.21.1** if you don't have it yet.
2. Grab the `energydrinks-1.0.0.jar` file from the **Releases** page.
3. Drop it into your `mods` folder and start the game.

## Crafting

Throw a **Water Bottle**, **Sugar** and a **dye** anywhere in the crafting grid. The dye decides the flavor, so Red Dye makes Red Energy and so on. Green is the only odd one: it uses **Lime Dye**.

In creative you'll find them all in the **Energy Drinks** tab, and in the Food & Drinks tab too.

## Building it yourself

You need JDK 21. In this folder:

```
./gradlew build        # builds build/libs/energydrinks-1.0.0.jar (gradlew.bat on Windows)
./gradlew runClient    # starts the game with the mod loaded
```

To add a flavor, add it to `DrinkFlavor.java`, then add a texture, item model, recipe and a line in `en_us.json` (copying an existing one is easiest). Boost and crash lengths are in `DrinkConstants.java`.

## Credits

All the assets in this mod were either made by me or are free assets found online.

Stay caffeinated :D
