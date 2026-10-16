package com.m33rk4.energydrinks.util;

// All the numbers you'd want to tweak. Times are in ticks (20 ticks = 1 second).
public final class DrinkConstants {
    private DrinkConstants() {}

    public static final int NUTRITION = 2;
    public static final float SATURATION_MODIFIER = 0.3F;

    public static final int BOOST_DURATION = 3 * 60 * 20;
    public static final int CRASH_DURATION = 20 * 20;

    public static final int DRINK_TIME = 32;
    public static final int MAX_STACK_SIZE = 16;

    public static String formatTicks(int ticks) {
        int seconds = ticks / 20;
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }
}
