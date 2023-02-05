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

    private HashMap<String, GameEntity> entitiesList;

    private List<GameEntity> actionListenersList;
    private HashMap<String, GameEntity> actionListeners;


    private BattleInterface battleInterface;

    public Arena(List<GameEntity> entities, List<GameEntity> actionListeners) {
        entitiesQueue = new ArrayList<>();
        entitiesList = new HashMap<>();
        entities.forEach(this::addEntityObject);

        this.actionListeners = new HashMap<>();
        this.actionListenersList = new ArrayList<>();
        actionListeners.forEach(this::addListenerObject);

        battleInterface = ((Player) entitiesQueue.stream().filter(obj -> obj instanceof Player).findAny().get()).getBattleInterface();
    }

    public void addEntityObject(GameEntity entity) {
        entitiesQueue.add(entity);
        entitiesList.put(entity.getId(), entity);
    }

    public void addListenerObject(GameEntity entity) {
        actionListenersList.add(entity);
        actionListeners.put(entity.getId(), entity);
    }

    public boolean processTurn() {
        for (GameEntity entity : entitiesQueue) {
            if (!entity.hasAction()) {
                continue;
            }

            GameAction action = entity.getAction(entitiesQueue);

            if (action != null) {
                battleInterface.printActions(action);

                GameEntity actionReceiver = entitiesList.get(action.getReceiver());

                if (actionReceiver != null) {
                    GameAction receiverAction = actionReceiver.processAction(action, entitiesQueue);
                    battleInterface.printActions(receiverAction);
                    if (receiverAction instanceof GameAction) {
                        battleInterface.printActions();
                        return true;
                    }
                }

                if (action instanceof GameEndAction) {
                    battleInterface.printActions();
                    return true;
                }
            }
        }
        return false;
    }
}
