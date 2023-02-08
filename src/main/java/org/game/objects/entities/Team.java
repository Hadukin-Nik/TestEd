package org.game.objects.entities;

import java.util.List;

public class Team {
    private String id;
    private String name;

    private List<GameEntity> entities;

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public List<GameEntity> getEntities() {
        return entities;
    }
}
