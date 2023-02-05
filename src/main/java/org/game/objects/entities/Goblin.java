package org.game.objects.entities;

import org.game.actions.AttackAction;
import org.game.actions.DodgeAction;
import org.game.actions.GameAction;

import java.util.List;
import java.util.Random;

public class Goblin extends Bot{
    private double chanceOfDodge = 3;

    protected double minChanceDodge = 0.0;
    protected double maxChanceDodge = 10.0;

    public Goblin() {}


    public Goblin(double chanceOfDodge) {
        this.chanceOfDodge = Math.min(chanceOfDodge, maxChanceDodge);
        this.chanceOfDodge = Math.max(this.chanceOfDodge, minChanceDodge);
    }

    public Goblin(Bot bot, double chanceOfDodge) {
        super(bot);

        this.chanceOfDodge = Math.min(chanceOfDodge, maxChanceDodge);
        this.chanceOfDodge = Math.max(this.chanceOfDodge, minChanceDodge);
    }





    @Override
    public GameAction processAction(GameAction action, List<GameEntity> entities) {
        Random rand = new Random();

        if (action instanceof AttackAction && rand.nextDouble(maxChanceDodge) > chanceOfDodge) {
            health = Math.max(0.0, health - ((AttackAction) action).getDamage());
            return null;
        }

        return new DodgeAction(action.getSender(), action.getReceiver());
    }
}
