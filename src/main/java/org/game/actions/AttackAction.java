package org.game.actions;

public class AttackAction implements GameAction {

    private String sender;
    private String receiver;

    private int damage;

    public AttackAction (String sender, String receiver, int damage) {
        this.sender = sender;
        this.receiver = receiver;

        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public String getReceiver() {
        return receiver;
    }
}
