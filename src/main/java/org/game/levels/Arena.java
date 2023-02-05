package org.game.levels;

import org.game.actions.GameAction;
import org.game.actions.GameEndAction;
import org.game.objects.entities.GameEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Arena {
    private List<GameEntity> entitiesQueue;
    private HashMap<String, GameEntity>  entitiesList;

    public Arena(List<GameEntity> entities) {
        entities.forEach(this::addObject);
    }

    public void addObject(GameEntity entity) {
        entitiesQueue.add(entity);
        entitiesList.put(entity.getId(), entity);
    }

    public boolean processTurn() {
        for (GameEntity entity: entitiesQueue) {
            if(!entity.hasAction()) {
                continue;
            }

            GameAction action = entity.getAction(entitiesQueue);

            if (action != null) {
                GameEntity actionReceiver = entitiesList.get(action.getReceiver());

                if(actionReceiver != null) {
                    GameAction receiverAction = actionReceiver.processAction(action, entitiesQueue);
                }

                if(action instanceof GameEndAction) {
                    return true;
                }
            }
        }
        return false;
    }
}
