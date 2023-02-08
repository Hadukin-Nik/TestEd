package org.game.objects.entities;

import org.game.actions.DeathAction;
import org.game.actions.GameAction;
import org.game.actions.GameEndAction;
import org.game.util.Rands;

import java.util.HashMap;
import java.util.List;

public class LevelMaster extends GameEntity{
    private int teamPlayer;
    private int teamEnemy;

    private HashMap<String, Integer> listOfEntities;

    public LevelMaster() {
        id = Rands.getRandomID();
        listOfEntities = new HashMap<>();
    }



    public void addAllies(List<GameEntity> gameEntity) {
        for(GameEntity g : gameEntity) {
            listOfEntities.put(g.getId(), 1);
            teamPlayer++;
        }
    }
    public void addEnemies(List<GameEntity> gameEntity) {
        for(GameEntity g : gameEntity) {
            listOfEntities.put(g.getId(), 2);
            teamEnemy++;
        }
    }
    @Override
    public GameAction getAction(List<GameEntity> objects) {
        return null;
    }

    @Override
    public GameAction processAction(GameAction action, List<GameEntity> entities) {
        String sender = action.getSender();

        if (action instanceof DeathAction) {
           if(listOfEntities.get(sender).intValue() == 1) {
               teamPlayer --;
           }

            if(listOfEntities.get(sender).intValue() == 2) {
                teamEnemy --;
            }
        }

        if (teamEnemy == 0) {
            return new GameEndAction("Player is win!");
        } else if (teamPlayer == 0) {
            return new GameEndAction("Game Over! \n Team of enemies wins");
        } else {
            return null;
        }
    }
}
