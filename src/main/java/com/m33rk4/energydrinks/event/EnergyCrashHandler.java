package com.m33rk4.energydrinks.event;

import com.m33rk4.energydrinks.registry.ModEffects;
import com.m33rk4.energydrinks.util.EnergyEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.List;

// Gives you the crash when Caffeinated runs out.
// Drinking another can just refreshes Caffeinated, so the crash gets pushed back.
// Milk removes effects instead of letting them expire, so milk skips the crash.
//
// The crash waits until the end of the tick on purpose. Expired fires while Minecraft is
// still going through your effects, and adding new ones right then can crash the game.
public final class EnergyCrashHandler {
    private EnergyCrashHandler() {}

    private static final List<LivingEntity> PENDING_CRASHES = new ArrayList<>();

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null || instance.getEffect().value() != ModEffects.CAFFEINATED.get()) {
            return;
        }
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide()) {
            PENDING_CRASHES.add(entity);
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        if (PENDING_CRASHES.isEmpty()) {
            return;
        }
        List<LivingEntity> toCrash = new ArrayList<>(PENDING_CRASHES);
        PENDING_CRASHES.clear();
        for (LivingEntity entity : toCrash) {
            if (entity.isAlive() && !entity.isRemoved()) {
                EnergyEffects.applyCrash(entity);
            }
        }
    }
}
