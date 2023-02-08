package org.game.util;

import java.util.Random;

public class RandDoubleGenerator implements IRandDoubleGenerator {
    private final Random random = new Random();
    @Override
    public double nextDouble(double bound) {
        return random.nextDouble(bound);
    }
}
