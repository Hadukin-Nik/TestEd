package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.DodgeAction;
import org.game.actions.GameAction;
import org.game.objects.entities.Bot;
import org.game.objects.entities.BotFactory;
import org.game.util.IRandDoubleGenerator;
import org.game.util.RandDoubleGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DodgeStrategyTest {

    class FakeRandom implements IRandDoubleGenerator {
        double fixedValue;
        @Override
        public double nextDouble(double bound) {
            return fixedValue;
        }
    }

    @Test
    void processAttackSuccessDodge() {
        FakeRandom fakeRandom = new FakeRandom();
        double dodgeChance = 3.0;
        DodgeStrategy dodgeStrategy = new DodgeStrategy(fakeRandom, dodgeChance);
        fakeRandom.fixedValue = dodgeChance - 1.0;
        Bot bot = BotFactory.createSingleOrc("testOrc");
        int initialHealth = bot.getHealth();
        int damage = 10;
        AttackAction attackAction = new AttackAction("", bot.getId(), damage);
        GameAction result = dodgeStrategy.processAttack("", bot, attackAction);
        assertEquals(initialHealth, bot.getHealth(), "Health shouldn't be changed");
        assertInstanceOf(DodgeAction.class, result, "Dodge action should be created");
    }

    @Test
    void processAttackFailDodge() {
        FakeRandom fakeRandom = new FakeRandom();
        double initDodge = 3.0;
        DodgeStrategy dodgeStrategy = new DodgeStrategy(fakeRandom, initDodge);
        fakeRandom.fixedValue = initDodge + 1.0;
        Bot bot = BotFactory.createSingleOrc("testOrc");
        int initialHealth = bot.getHealth();
        int damage = 10;
        AttackAction attackAction = new AttackAction("", bot.getId(), damage);
        GameAction result = dodgeStrategy.processAttack("", bot, attackAction);
        assertEquals(initialHealth, bot.getHealth(), "Health shouldn't be changed");
        assertNull(result, "Dodge action shouldn't be created");
    }
}