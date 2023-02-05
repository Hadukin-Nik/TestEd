package org.game.userInterface.menuActions;

import org.game.objects.Inventory;
import org.game.objects.entities.Player;

import java.util.List;

public class InventoryMenu implements MenuAction{
    private MenuAction root;
    private Inventory inventory;
    private Player player;

    public InventoryMenu(Player player) {
        this.player = player;
        this.inventory = player.getInventory();
    }

    @Override
    public List<String> getOptions() {
        return inventory.listItems();
    }

    @Override
    public String getHeader() {
        return "What item will you use?";
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
        return new MenuActionResult(inventory.use(inventory.listByType().get(optionIndex), player.getId(), player.getId()));
    }
}
