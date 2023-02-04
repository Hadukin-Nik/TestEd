package org.game.objects.entities;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.util.Rands;

import java.util.List;

public class Bot extends GameEntity{
    public Bot() {
        id = Rands.getRandomID();
        name = "Empty Bot";
    }

    @Override
    public GameAction getAction(List<GameEntity> objects) {
        for (var obj: objects) {
            if (obj instanceof Player) {
                return new AttackAction(id, obj.getId(), 1);
            }
        }

        return null;
    }

    @Override
    public GameAction processAction(GameAction action, List<GameEntity> entities) {
        if (action instanceof AttackAction) {
            health = Math.max(0.0, health - ((AttackAction) action).getDamage());
        }

        return null;
    }
}