package org.game.actions;

public class DeathAction implements GameAction {
    private String sender;
    private String receiver;

    private String message;

    public DeathAction(String sender, String receiver, String message){
        this.sender = sender;
        this.receiver = receiver;

        this.message = message;
    }
    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }
}
