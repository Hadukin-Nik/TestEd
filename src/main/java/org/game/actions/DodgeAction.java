package org.game.actions;

public class DodgeAction implements GameAction{
    private String sender;
    private String receiver;

    public DodgeAction(String receiver, String sender){
        this.sender = sender;
        this.receiver = receiver;
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
