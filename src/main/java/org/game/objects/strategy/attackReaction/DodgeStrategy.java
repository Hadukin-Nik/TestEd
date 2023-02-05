package org.game.objects.strategy.attackReaction;

import org.game.actions.DodgeAction;
import org.game.actions.GameAction;

import java.util.Random;

public class DodgeStrategy implements AttackReactionStrategy {

    protected final double MIN_CHANCE_DODGE = 0.0;
    protected final double MAX_CHANCE_DODGE = 10.0;
    private double chanceOfDodge;

    private final Random rand = new Random();


    public DodgeStrategy() {
        this(3.0);
    }

    public DodgeStrategy(double chanceOfDodge) {
        this.chanceOfDodge = Math.min(chanceOfDodge, MAX_CHANCE_DODGE);
        this.chanceOfDodge = Math.max(this.chanceOfDodge, MIN_CHANCE_DODGE);
    }


    @Override
    public GameAction processAttack(String sender, String receiver) {
        if (rand.nextDouble(MAX_CHANCE_DODGE) < chanceOfDodge) {
            return new DodgeAction(sender, receiver);
        }
        return null;
    }
}
