package org.game.userInterface;

public enum UserBattleActions {
    ATTACK("Атаковать"),
    INVENTORY("Инвентарь"),
    PASS("Пропустить ход");

    private String str;

    UserBattleActions(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
