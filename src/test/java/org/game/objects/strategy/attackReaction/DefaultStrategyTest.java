package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.objects.entities.Bot;
import org.game.objects.entities.BotFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultStrategyTest {

    @Test
    void processAttack() {
        DefaultStrategy defaultStrategy = new DefaultStrategy();
        Bot bot = BotFactory.createSingleOrc("testOrc");
        int initHealth = bot.getHealth();
        int damage = 10;
        AttackAction attackAction = new AttackAction("", bot.getId(), damage);
        GameAction reaction = defaultStrategy.processAttack("", bot, attackAction);
        assertNull(reaction, "Not expected any reaction for simple damage");
        assertEquals(initHealth - damage, bot.getHealth(), "Bot should receive damage on damage action");
    }
}