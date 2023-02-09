package org.game.objects.entities;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.actions.HealAction;
import org.game.objects.Inventory;
import org.game.objects.strategy.attackReaction.AttackReactionStrategy;
import org.game.objects.strategy.attackReaction.DefaultReadyEntityStrategy;
import org.game.userInterface.BattleInterface;

import java.util.List;

public class Player extends GameEntity {
    private BattleInterface battleInterface;
    private Inventory inventory;

    private AttackReactionStrategy attackReactionStrategy;

    public Player() {
        id = "PlayerID";
        name = "Player";

        health = 100;
        damage = 10;

        inventory = new Inventory();
    }

    public Player(BattleInterface battleInterface, Inventory inventory, LevelMaster levelMaster) {
        this();

        this.battleInterface = battleInterface;
        this.inventory = inventory;
        this.attackReactionStrategy = new DefaultReadyEntityStrategy(levelMaster);

    }

    @Override
    public GameAction getAction(List<GameEntity> entities) {
        return battleInterface.openFightInterface(entities);
    }

    @Override
    public GameAction processAction(GameAction action, List<GameEntity> entities) {
        if (action instanceof AttackAction) {
            return attackReactionStrategy.processAttack(action.getSender(), this, (AttackAction) action, entities);
        } else if (action instanceof HealAction) {
            this.health = this.health + ((HealAction) action).getHealth();
        }

        return null;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public BattleInterface getBattleInterface() {
        return battleInterface;
    }
}
