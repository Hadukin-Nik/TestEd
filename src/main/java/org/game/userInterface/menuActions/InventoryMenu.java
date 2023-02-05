package org.game.userInterface.menuActions;

import org.game.objects.entities.Player;

import java.util.List;

public class InventoryMenu implements MenuAction{
    private MenuAction root;

    public InventoryMenu(Player player) {
    }

    @Override
    public List<String> getOptions() {
        return null;
    }

    @Override
    public String getHeader() {
        return null;
    }

    @Override
    public MenuAction getRoot() {
        return root;
    }

    public void setRoot(MenuAction root) {
        this.root = root;
    }

    @Override
    public MenuActionResult createAction(int optionIndex) {
        return new MenuActionResult(root);
    }
}
