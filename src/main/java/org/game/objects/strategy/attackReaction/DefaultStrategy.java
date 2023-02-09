package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.objects.entities.GameEntity;
import org.game.objects.entities.LevelMaster;

import java.util.List;

public class DefaultStrategy implements AttackReactionStrategy {

    @Override
    public GameAction processAttack(String sender, GameEntity receiver, AttackAction attackAction, List<GameEntity> entities) {
        int newHealth = Math.max(0, receiver.getHealth() - attackAction.getDamage());
        receiver.setHealth(newHealth);
        return null;
    }
}
