package org.game.objects.entities;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.actions.HealAction;

import java.util.List;

public class Player extends GameEntity {
    Player() {
        id = "PlayerID";
        name = "Player";

        health = 100;
        damage = 10;
    }

    @Override
    public GameAction getAction(List<GameEntity> objects) {
        return null;
    }

    @Override
    public GameAction processAction(GameAction action, List<GameEntity> entities) {
        if (action instanceof AttackAction) {
            health = Math.max(0.0, health - ((AttackAction) action).getDamage());
        } else if (action instanceof HealAction) {
            this.health = this.health + ((HealAction) action).getHealth();
        }

        return null;
    }
}
