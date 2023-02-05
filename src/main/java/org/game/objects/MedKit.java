package org.game.objects;

import org.game.actions.GameAction;
import org.game.actions.HealAction;

public enum MedKit implements IItem{
    SMALL_HEALTH_POTION("Small healing potion", 10),
    MIDDLE_HEALTH_POTION("Middle healing potion", 20),
    BIG_HEALTH_POTION("Bid healing potion", 30);


    private String name;

    private int healPower;

    MedKit(String name, int healPower) {
        this.name = name;

        this.healPower = healPower;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GameAction use(String sender, String receiver) {
        return new HealAction(sender, receiver, healPower);
    }
}
