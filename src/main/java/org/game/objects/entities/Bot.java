package org.game.objects.entities;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.objects.strategy.attack.AttackStrategy;
import org.game.objects.strategy.attackReaction.AttackReactionStrategy;
import org.game.util.Rands;

import java.util.List;

public class Bot extends GameEntity{
    protected AttackStrategy attackStrategy;
    protected AttackReactionStrategy attackReactionStrategy;

    Bot() {
        id = Rands.getRandomID();
        name = "Empty Bot";
        health = 10;
    }

    Bot(String name) {
        this();

        this.name = name;
    }

    Bot(String name, double health) {
        this(name);

        this.health = health;
    }

    Bot(Bot bot) {
        this(bot.name, bot.health);
    }

    @Override
    public GameAction getAction(List<GameEntity> objects) {

        for (var obj: objects) {
            if (obj instanceof Player && attackStrategy != null) {
                return attackStrategy.createAttack(id, obj.id);
            }
        }

        return null;
    }

    @Override
    public GameAction processAction(GameAction action, List<GameEntity> entities) {
        if (action instanceof AttackAction && attackReactionStrategy != null) {
            GameAction attackReaction = attackReactionStrategy.processAttack(action.getSender(), id);
            if (attackReaction != null) {
                return attackReaction;
            }
            health = Math.max(0.0, health - ((AttackAction) action).getDamage());
        }

        return null;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public AttackReactionStrategy getAttackReactionStrategy() {
        return attackReactionStrategy;
    }

    public void setAttackReactionStrategy(AttackReactionStrategy attackReactionStrategy) {
        this.attackReactionStrategy = attackReactionStrategy;
    }
}
