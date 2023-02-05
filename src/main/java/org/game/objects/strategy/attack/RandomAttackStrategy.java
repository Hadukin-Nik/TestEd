package org.game.objects.strategy.attack;

import org.game.actions.AttackAction;

import java.util.Random;

public class RandomAttackStrategy implements AttackStrategy {
    final int maxChanceAttack = 5;
    Random random = new Random();

    @Override
    public AttackAction createAttack(String sender, String receiver) {
        return new AttackAction(sender, receiver, random.nextInt(maxChanceAttack));
    }
}
