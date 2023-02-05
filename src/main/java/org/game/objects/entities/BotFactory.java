package org.game.objects.entities;

import java.util.ArrayList;
import java.util.List;

public class BotFactory {

    public static List<Bot> createOrcs(int count) {
        List<Bot> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            res.add(new Bot("Bot#" + i));
        }
        return res;
    }
}
