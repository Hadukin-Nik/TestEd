package org.game.objects.entities;

import org.game.objects.strategy.attack.AttackStrategy;
import org.game.objects.strategy.attack.RandomAttackStrategy;
import org.game.objects.strategy.attackReaction.DodgeStrategy;

import java.util.ArrayList;
import java.util.List;

public class BotFactory {

    private static final AttackStrategy attackStrategy = new RandomAttackStrategy();
    private static final DodgeStrategy dodgeStrategy = new DodgeStrategy();

    public static Bot createSingleOrc(String name) {
        Bot bot = new Bot(name);
        bot.setAttackStrategy(attackStrategy);
        return bot;
    }

    public static List<Bot> createOrcs(int count) {
        List<Bot> res = createBots("Orc", count);
        res.forEach(bot -> bot.setAttackStrategy(attackStrategy));
        return res;
    }

    public static List<Bot> createGoblins(int count) {
        List<Bot> res = createBots("Goblin", count);
        res.forEach(bot -> {
            bot.setHealth(20.0);
            bot.setAttackStrategy(attackStrategy);
            bot.setAttackReactionStrategy(dodgeStrategy);
        });

        return res;
    }

    private static List<Bot> createBots(String name, int count) {
        List<Bot> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            res.add(new Bot(name + "#" + (i + 1)));
        }
        return res;
    }


}
