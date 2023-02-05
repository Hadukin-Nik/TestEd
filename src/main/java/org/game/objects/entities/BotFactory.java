package org.game.objects.entities;

import java.util.ArrayList;
import java.util.List;

public class BotFactory {

    public static List<Bot> createOrcs(int count) {
        List<Bot> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            res.add(new Bot("Orc#" + (i + 1)));
        }
        return res;
    }

    public static List<Bot> createGoblins(int count) {
        List<Bot> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            res.add(new Goblin(new Bot("Goblin#" + (i + 1), 20), 3.0));
        }
        return res;
    }
}
