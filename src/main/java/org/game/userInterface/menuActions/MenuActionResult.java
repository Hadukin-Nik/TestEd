package org.game.userInterface.menuActions;

import org.game.actions.GameAction;

public class MenuActionResult {
    private MenuAction menuAction;
    private GameAction gameAction;

    public MenuActionResult(MenuAction menuAction) {
        this.menuAction = menuAction;
    }

    public MenuActionResult(GameAction menuAction) {
        this.gameAction = menuAction;
    }

    public MenuActionResult() {

    }

    public MenuAction getMenuAction() {
        return menuAction;
    }

    public GameAction getGameAction() {
        return gameAction;
    }
}
