package org.game.objects.strategy.attackReaction;

import org.game.actions.AttackAction;
import org.game.actions.DeathAction;
import org.game.actions.GameAction;
import org.game.objects.entities.GameEntity;
import org.game.objects.entities.LevelMaster;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultReadyEntityStrategy extends DefaultStrategy {
    LevelMaster levelMaster;

    public DefaultReadyEntityStrategy(LevelMaster levelMaster) {
        this.levelMaster = levelMaster;
    }

    @Override
    public GameAction processAttack(String sender, GameEntity receiver, AttackAction attackAction, List<GameEntity> entities) {
        HashMap<String, GameEntity> listfOfEntities = (HashMap<String, GameEntity>) entities.stream().collect(Collectors.toMap(GameEntity::getId, Function.identity()));

        super.processAttack(sender, receiver, attackAction, entities);
        if (receiver.getHealth() <= 0) {
            receiver.setCanMakeTurn(false);
            return new DeathAction(receiver.getId(), levelMaster.getId(), String.format("%s killed %s", listfOfEntities.get(sender).getName(), receiver.getName()));
        }
        return null;
    }
}
