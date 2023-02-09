package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.objects.entities.GameEntity;

import java.util.List;

public class ReactionsChain implements AttackReactionStrategy {
    private final List<AttackReactionStrategy> strategies;

    public ReactionsChain(List<AttackReactionStrategy> strategies) {
        this.strategies = strategies;
    }


    @Override
    public GameAction processAttack(String sender, GameEntity receiver, AttackAction attackAction, List<GameEntity> entities) {
        for (AttackReactionStrategy strategy: strategies) {
            GameAction reaction = strategy.processAttack(sender, receiver, attackAction, entities);
            if (reaction != null) {
                return reaction;
            }
        }
        return null;
    }
}
