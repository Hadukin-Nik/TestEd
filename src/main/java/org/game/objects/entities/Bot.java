package org.game.objects.entities;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.util.Rands;

import java.util.List;
import java.util.Random;

public class Bot extends GameEntity{
    protected double maxChanceAttack = 5.0;
    protected double minChanceAttack = 0.0;
    public Bot() {
        id = Rands.getRandomID();
        name = "Empty Bot";
        health = 10;
    }

    public Bot(String name) {
        this();

        this.name = name;
    }

    public Bot(String name, double health) {
        this(name);

        this.health = health;
    }

    public Bot(Bot bot) {
        this(bot.name, bot.health);
    }

    public void setBorderForChanceAttack(double min, double max) {
        maxChanceAttack = Math.max(0, max);
        minChanceAttack = Math.max(0, min);
    }

    @Override
    public GameAction getAction(List<GameEntity> objects) {
        Random random = new Random();

        for (var obj: objects) {
            if (obj instanceof Player) {
                return new AttackAction(id, obj.getId(), random.nextDouble(maxChanceAttack));
            }
        }

        return null;
    }

    @Override
    public GameAction processAction(GameAction action, List<GameEntity> entities) {
        if (action instanceof AttackAction) {
            health = Math.max(0.0, health - ((AttackAction) action).getDamage());
        }

        return null;
    }
}
