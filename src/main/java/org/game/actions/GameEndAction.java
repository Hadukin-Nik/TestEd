package org.game.actions;

public class GameEndAction implements  GameAction {
    private String message;

    public GameEndAction(String message) {
        this.message = message;
    }
    @Override
    public String getSender() {
        return null;
    }

    @Override
    public String getReceiver() {
        return null;
    }

    public String getMessage() {
        return message;
    }

}
