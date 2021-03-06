package com.rainbowpunch.jtdg.core.limiters.primitive;

import com.rainbowpunch.jtdg.core.limiters.Limiter;

import java.util.Random;
import java.util.function.Supplier;

public class FloatLimiter implements Limiter<Float> {
    @Override
    public Supplier<Float> generateSupplier(Random random) {
        return random::nextFloat;
    }
}