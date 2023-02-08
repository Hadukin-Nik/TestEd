package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.objects.entities.GameEntity;

public class DefaultStrategy implements AttackReactionStrategy {
    @Override
    public GameAction processAttack(String sender, GameEntity receiver, AttackAction attackAction) {
        int newHealth = Math.max(0, receiver.getHealth() - attackAction.getDamage());
        receiver.setHealth(newHealth);
        return null;
    }
}
