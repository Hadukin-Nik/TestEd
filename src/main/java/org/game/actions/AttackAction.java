package org.game.actions;

public class AttackAction implements GameAction {

    private String sender;
    private String receiver;

    private double damage;

    public AttackAction (String sender, String receiver, double damage) {
        this.sender = sender;
        this.receiver = receiver;

        this.damage = damage;
    }
    public double getDamage() {
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
