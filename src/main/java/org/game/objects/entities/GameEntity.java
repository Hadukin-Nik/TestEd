package org.game.objects.entities;

import org.game.actions.GameAction;
import org.game.userInterface.BattleInterface;

import java.util.List;

public abstract class GameEntity {
    protected String id;
    protected String name;
    protected int health;
    protected int damage;

    private boolean canMakeTurn = true;

    protected BattleInterface battleInterface;
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean hasAction() {
        return canMakeTurn;
    }

    public void setCanMakeTurn(boolean opportunity) {
        canMakeTurn = opportunity;
    }

    public String getGameLogState() {
        return String.format("The %s have %s health points", name, health);
    }
    public abstract GameAction getAction(List<GameEntity> objects);
    public abstract GameAction processAction(GameAction action, List<GameEntity> entities);
}
