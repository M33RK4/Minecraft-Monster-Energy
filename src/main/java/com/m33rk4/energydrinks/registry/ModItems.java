package com.m33rk4.energydrinks.registry;

import com.m33rk4.energydrinks.EnergyDrinks;
import com.m33rk4.energydrinks.item.EnergyDrinkItem;
import com.m33rk4.energydrinks.util.DrinkConstants;
import com.m33rk4.energydrinks.util.DrinkFlavor;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class ModItems {
    private ModItems() {}

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EnergyDrinks.MOD_ID);

    public static final DeferredItem<Item> EMPTY_CAN =
            ITEMS.registerSimpleItem("empty_can", new Item.Properties());

    // One can per flavor in DrinkFlavor.
    private static final Map<DrinkFlavor, DeferredItem<EnergyDrinkItem>> DRINKS = new EnumMap<>(DrinkFlavor.class);

    static {
        for (DrinkFlavor flavor : DrinkFlavor.values()) {
            DRINKS.put(flavor, ITEMS.registerItem(
                    flavor.itemId(),
                    props -> new EnergyDrinkItem(flavor, props),
                    new Item.Properties().stacksTo(DrinkConstants.MAX_STACK_SIZE)));
        }
    }

    public static DeferredItem<EnergyDrinkItem> drink(DrinkFlavor flavor) {
        return DRINKS.get(flavor);
    }

    public static Map<DrinkFlavor, DeferredItem<EnergyDrinkItem>> allDrinks() {
        return Collections.unmodifiableMap(DRINKS);
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
