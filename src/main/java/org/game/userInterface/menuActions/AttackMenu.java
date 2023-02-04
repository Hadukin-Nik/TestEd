package org.game.userInterface.menuActions;

import org.game.actions.AttackAction;
import org.game.objects.entities.GameEntity;
import org.game.objects.entities.Player;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class AttackMenu implements MenuAction{
    private MenuAction root;

    private Player player;
    private final List<GameEntity> enemies;

    public AttackMenu(Player player, List<GameEntity> enemies) {

        this.player = player;
        this.enemies = enemies;
    }

    public AttackMenu (List<GameEntity> enemies) {
        this.enemies = enemies;
    }
    public void setRoot(MenuAction menuAction) {
        this.root = menuAction;
    }
    @Override
    public List<String> getOptions() {
        return enemies.stream().map(GameEntity::getName).toList();
    }

    @Override
    public String getHeader() {
        return "Choose an enemy: ";
    }

    @Override
    public MenuAction getRoot() {
        return root;
    }

    @Override
    public MenuActionResult createAction(int optionIndex) {
        AttackAction attackAction = new AttackAction(player.getId(), enemies.get(optionIndex).getId(), player.getDamage());
        return new MenuActionResult(attackAction);
    }
}
