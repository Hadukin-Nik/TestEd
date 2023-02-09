package org.game;

import org.game.levels.Arena;
import org.game.objects.Inventory;
import org.game.objects.entities.*;
import org.game.userInterface.BattleInterface;
import org.game.userInterface.ConsoleUIPrinter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        startGame();
    }

    static void startGame() {
        List<GameEntity> objects = new ArrayList<>();
        LevelMaster levelMaster = new LevelMaster();
        Player player = new Player(
                new BattleInterface(new ConsoleUIPrinter(), System.in),
                new Inventory(10),
                levelMaster
        );
        List<GameEntity> enemies = new ArrayList<>();
        BotFactory.setDefaultStrategy(levelMaster);
        enemies.addAll(BotFactory.createGoblins(2));
        levelMaster.addAllies(Arrays.asList(player));
        levelMaster.addEnemies(enemies);

        objects.add(player);
        objects.addAll(enemies);

        Arena arena = new Arena(objects, levelMaster, Arrays.asList());

        boolean isDone = false;
        while(!isDone) {
            isDone = arena.processTurn();
        }
    }
}