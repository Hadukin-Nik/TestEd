package org.game.userInterface;

public enum UserBattleActions {
    ATTACK("Attack"),
    INVENTORY("Use inventory"),
    PASS("Pass");

    private String str;

    UserBattleActions(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
