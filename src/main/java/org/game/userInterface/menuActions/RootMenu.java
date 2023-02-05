package org.game.userInterface.menuActions;

import org.game.userInterface.UserBattleActions;

import java.util.Arrays;
import java.util.List;

public class RootMenu implements MenuAction{

    private final AttackMenu attackMenu;
    private final InventoryMenu inventoryMenu;

    public RootMenu(AttackMenu attackMenu, InventoryMenu inventoryMenu) {
        this.attackMenu = attackMenu;
        this.inventoryMenu = inventoryMenu;

        attackMenu.setRoot(this);
        inventoryMenu.setRoot(this);
    }

    @Override
    public List<String> getOptions() {
        return Arrays.stream(UserBattleActions.values()).map(UserBattleActions::getStr).toList();
    }

    @Override
    public String getHeader() {
        return "Choose a turn: ";
    }

    @Override
    public MenuAction getRoot() {
        return null;
    }

    @Override
    public MenuActionResult createAction(int optionIndex) {
        UserBattleActions action = UserBattleActions.values()[optionIndex];

        switch (action) {
            case ATTACK -> {
                return new MenuActionResult(attackMenu);
            }
            case INVENTORY -> {
                return new MenuActionResult(inventoryMenu);
            }
            case PASS -> {
                return new MenuActionResult();
            }
        }

        throw new RuntimeException("Uknown action type provided");
    }
}
