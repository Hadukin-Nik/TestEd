package org.game.objects.strategy.attackReaction;

import org.game.actions.GameAction;

public interface AttackReactionStrategy {
    GameAction processAttack(String sender, String receiver);
}
