package org.game.actions;

public class HealAction implements GameAction {
    private String sender;
    private String receiver;

    private int health;

    public HealAction(String sender, String receiver, int health) {
        this.sender = sender;
        this.receiver = receiver;
        this.health = health;
    }
    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public String getReceiver() {
        return receiver;
    }

    public int getHealth() {
        return health;
    }
}
