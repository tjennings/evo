package com.evo;

import java.util.Random;

public class Randomizer {

    private static final Random random = new Random();
    private static float override = -1.0f;

    public static void override(float v) {
        override = v;
    }

    public static float nextFloat() {
        return (override == -1.0f ? random.nextFloat() : override);
    }
}
