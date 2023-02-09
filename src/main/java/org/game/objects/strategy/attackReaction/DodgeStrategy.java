package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.DodgeAction;
import org.game.actions.GameAction;
import org.game.objects.entities.GameEntity;
import org.game.util.IRandDoubleGenerator;
import org.game.util.RandDoubleGenerator;

import java.util.List;
import java.util.Random;

public class DodgeStrategy implements AttackReactionStrategy {

    protected final double MIN_CHANCE_DODGE = 0.0;
    protected final double MAX_CHANCE_DODGE = 10.0;
    protected double chanceOfDodge;

    private final IRandDoubleGenerator rand;

    public DodgeStrategy(IRandDoubleGenerator rand, double chanceOfDodge) {
        this.chanceOfDodge = Math.min(chanceOfDodge, MAX_CHANCE_DODGE);
        this.chanceOfDodge = Math.max(this.chanceOfDodge, MIN_CHANCE_DODGE);
        this.rand = rand;
    }


    @Override
    public GameAction processAttack(String sender, GameEntity receiver, AttackAction attackAction, List<GameEntity> entities) {
        if (rand.nextDouble(MAX_CHANCE_DODGE) < chanceOfDodge) {
            return new DodgeAction(sender, receiver.getId());
        }
        return null;
    }
}
