package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.actions.GameEndAction;
import org.game.objects.entities.GameEntity;

public class DefaultPlayerStrategy extends DefaultStrategy {
    @Override
    public GameAction processAttack(String sender, GameEntity receiver, AttackAction attackAction) {
        super.processAttack(sender, receiver, attackAction);
        if (receiver.getHealth() == 0) {
            return new GameEndAction();
        }
        return null;
    }
}
