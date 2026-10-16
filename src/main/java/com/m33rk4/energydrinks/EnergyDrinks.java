package com.m33rk4.energydrinks;

import com.m33rk4.energydrinks.event.EnergyCrashHandler;
import com.m33rk4.energydrinks.registry.ModCreativeTabs;
import com.m33rk4.energydrinks.registry.ModEffects;
import com.m33rk4.energydrinks.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(EnergyDrinks.MOD_ID)
public class EnergyDrinks {
    public static final String MOD_ID = "energydrinks";

    public EnergyDrinks(IEventBus modEventBus, ModContainer modContainer) {
        ModEffects.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        // The crash stuff goes on the game bus, not the mod bus.
        NeoForge.EVENT_BUS.register(EnergyCrashHandler.class);
    }
}
