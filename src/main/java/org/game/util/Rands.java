package org.game.util;

import java.util.UUID;

public class Rands {
    public static String getRandomID() {
        return UUID.randomUUID().toString();
    }
}
