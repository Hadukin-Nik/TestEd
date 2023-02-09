package org.game.objects.entities;

import org.game.objects.strategy.attack.AttackStrategy;
import org.game.objects.strategy.attack.RandomAttackStrategy;
import org.game.objects.strategy.attackReaction.DefaultReadyEntityStrategy;
import org.game.objects.strategy.attackReaction.DefaultStrategy;
import org.game.objects.strategy.attackReaction.DodgeStrategy;
import org.game.objects.strategy.attackReaction.ReactionsChain;
import org.game.util.RandDoubleGenerator;

import java.util.ArrayList;
import java.util.List;

public class BotFactory {

    private static final AttackStrategy attackStrategy = new RandomAttackStrategy();
    private static DodgeStrategy dodgeStrategy = new DodgeStrategy(new RandDoubleGenerator(), 3.0);
    private static DefaultStrategy defaultStrategy = new DefaultStrategy();


    public static void setDefaultStrategy(LevelMaster levelMaster) {
        defaultStrategy = new DefaultReadyEntityStrategy(levelMaster);
    }

    public static Bot createSingleOrc(String name) {
        Bot bot = new Bot(name);
        initOrc(bot);
        return bot;
    }

    public static List<Bot> createOrcs(int count) {
        List<Bot> res = createBots("Orc", count);
        res.forEach(BotFactory::initOrc);
        return res;
    }

    public static List<Bot> createGoblins(int count) {
        List<Bot> res = createBots("Goblin", count);
        res.forEach(BotFactory::initGoblin);
        return res;
    }

    private static List<Bot> createBots(String name, int count) {
        List<Bot> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            res.add(new Bot(name + "#" + (i + 1)));
        }
        return res;
    }

    private static void initOrc(Bot bot) {
        bot.setAttackStrategy(attackStrategy);
        bot.setAttackReactionStrategy(defaultStrategy);
    }

    private static void initGoblin(Bot bot) {
        bot.setHealth(20);
        bot.setAttackStrategy(attackStrategy);
        bot.setAttackReactionStrategy(
                new ReactionsChain(List.of(dodgeStrategy, defaultStrategy))
        );
    }


}
