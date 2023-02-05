package org.game.objects.strategy.attack;

import org.game.actions.AttackAction;

public interface AttackStrategy {
    AttackAction createAttack(String sender, String receiver);
}
