package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.objects.entities.GameEntity;

public interface AttackReactionStrategy {
    GameAction processAttack(String sender, GameEntity receiver, AttackAction attackAction);
}
