package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.DeathAction;
import org.game.actions.GameAction;
import org.game.actions.GameEndAction;
import org.game.objects.entities.GameEntity;
import org.game.objects.entities.LevelMaster;

public class DefaultPlayerStrategy extends DefaultStrategy {
    LevelMaster levelMaster;

    public DefaultPlayerStrategy(LevelMaster levelMaster) {
        this.levelMaster = levelMaster;
    }

    @Override
    public GameAction processAttack(String sender, GameEntity receiver, AttackAction attackAction) {
        super.processAttack(sender, receiver, attackAction);
        if (receiver.getHealth() <= 0) {
            return new DeathAction(receiver.getId(), levelMaster.getId(), String.format("%s killed %s", sender, receiver.getName()));
        }
        return null;
    }
}
