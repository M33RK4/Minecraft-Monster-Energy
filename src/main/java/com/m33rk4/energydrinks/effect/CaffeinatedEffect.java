package com.m33rk4.energydrinks.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

// Doesn't do anything on its own, it's just a timer for the boost.
// When it runs out, EnergyCrashHandler hits you with the crash.
public class CaffeinatedEffect extends MobEffect {
    public CaffeinatedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x9BE15D);
    }
}
