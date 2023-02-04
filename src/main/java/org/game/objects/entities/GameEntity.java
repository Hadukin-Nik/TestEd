package org.game.objects.entities;

import org.game.actions.GameAction;

import java.util.List;

public abstract class GameEntity {
    protected String id;
    protected String name;
    protected double health;
    protected double damage;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public boolean hasAction() {
        return true;
    }

    public String getGameLogState() {
        return String.format("The %s have %s health points", name, health);
    }
    public abstract GameAction getAction(List<GameEntity> objects);
    public abstract GameAction processAction(GameAction action, List<GameEntity> entities);
}