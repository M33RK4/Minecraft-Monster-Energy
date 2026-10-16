package com.m33rk4.energydrinks.registry;

import com.m33rk4.energydrinks.EnergyDrinks;
import com.m33rk4.energydrinks.util.DrinkFlavor;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeTabs {
    private ModCreativeTabs() {}

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EnergyDrinks.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ENERGY_DRINKS_TAB = TABS.register(
            "energy_drinks",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.energydrinks"))
                    .icon(() -> new ItemStack(ModItems.drink(DrinkFlavor.GREEN).get()))
                    .displayItems((parameters, output) ->
                            ModItems.ITEMS.getEntries().forEach(entry -> output.accept(entry.get())))
                    .build());

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
        modEventBus.addListener(ModCreativeTabs::addToVanillaTabs);
    }

    // Puts the drinks in the normal Food & Drinks tab too.
    private static void addToVanillaTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            ModItems.allDrinks().values().forEach(event::accept);
        }
    }
}
