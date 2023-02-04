package org.game.userInterface.menuActions;

import java.util.List;

public interface MenuAction {
    public List<String> getOptions();

    public String getHeader();

    public MenuAction getRoot();

    MenuActionResult createAction(int optionIndex);
}
