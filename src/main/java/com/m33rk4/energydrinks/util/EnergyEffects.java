package com.m33rk4.energydrinks.util;

import com.m33rk4.energydrinks.registry.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public final class EnergyEffects {
    private EnergyEffects() {}

    public static void applyBoost(LivingEntity entity) {
        int duration = DrinkConstants.BOOST_DURATION;
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 0));
        entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, duration, 0));

        // Caffeinated is the boost timer. Since it's a normal effect it saves with the player,
        // survives relogging and milk clears it. No particles, but it still shows on the HUD.
        entity.addEffect(new MobEffectInstance(ModEffects.CAFFEINATED, duration, 0, false, false, true));
    }

    public static void applyCrash(LivingEntity entity) {
        int duration = DrinkConstants.CRASH_DURATION;
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 0));
        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, duration, 0));
    }
}
