package org.game.levels;

import org.game.actions.GameAction;
import org.game.actions.GameEndAction;
import org.game.objects.entities.GameEntity;
import org.game.objects.entities.Player;
import org.game.userInterface.BattleInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Arena {
    private List<GameEntity> entitiesQueue;
    private HashMap<String, GameEntity>  entitiesList;

    private BattleInterface battleInterface;

    public Arena(List<GameEntity> entities) {
        entitiesQueue = new ArrayList<>();
        entitiesList = new HashMap<>();

        entities.forEach(this::addObject);

        battleInterface = ((Player) entitiesQueue.stream().filter(obj -> obj instanceof Player).findAny().get()).getBattleInterface();
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
                battleInterface.printAction(action);

                GameEntity actionReceiver = entitiesList.get(action.getReceiver());

                if(actionReceiver != null) {
                    GameAction receiverAction = actionReceiver.processAction(action, entitiesQueue);
                    battleInterface.printAction(receiverAction);
                }

                if(action instanceof GameEndAction) {
                    return true;
                }
            }
        }
        return false;
    }
}
