package org.game.userInterface.menuActions;

import java.util.List;

public interface MenuAction {
    List<String> getOptions();

    String getHeader();

    MenuAction getRoot();

    MenuActionResult createAction(int optionIndex);
}
