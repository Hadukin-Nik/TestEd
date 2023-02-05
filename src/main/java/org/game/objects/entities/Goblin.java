package org.game.objects.entities;

import org.game.actions.AttackAction;
import org.game.actions.DodgeAction;
import org.game.actions.GameAction;

import java.util.List;
import java.util.Random;

public class Goblin extends Bot{
    private double chanceOfDodge = 3;
    protected double maxChance = 10.0;
    protected double minChance = 0.0;

    public Goblin() {}

    public Goblin(String name, double chanceOfDodge) {
        super(name);
        this.chanceOfDodge = Math.min(chanceOfDodge, maxChance);
        this.chanceOfDodge = Math.max(this.chanceOfDodge, minChance);
    }

    public Goblin(Bot bot, double chanceOfDodge) {
        super(bot);
        this.chanceOfDodge = Math.min(chanceOfDodge, maxChance);
        this.chanceOfDodge = Math.max(this.chanceOfDodge, minChance);
    }

    public void setBorderForDodge(double min, double max) {
        maxChance = Math.max(0, max);
        minChance = Math.max(0, min);
    }



    @Override
    public GameAction processAction(GameAction action, List<GameEntity> entities) {
        Random rand = new Random();

        if (action instanceof AttackAction && rand.nextDouble(maxChance) > chanceOfDodge) {
            health = Math.max(0.0, health - ((AttackAction) action).getDamage());
            return null;
        }

        return new DodgeAction(action.getSender(), action.getReceiver());
    }
}
