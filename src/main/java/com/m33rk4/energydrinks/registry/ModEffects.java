package com.m33rk4.energydrinks.registry;

import com.m33rk4.energydrinks.EnergyDrinks;
import com.m33rk4.energydrinks.effect.CaffeinatedEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEffects {
    private ModEffects() {}

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, EnergyDrinks.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> CAFFEINATED =
            MOB_EFFECTS.register("caffeinated", CaffeinatedEffect::new);

    public static void register(IEventBus modEventBus) {
        MOB_EFFECTS.register(modEventBus);
    }
}
