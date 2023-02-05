package org.game;

import org.game.levels.Arena;
import org.game.objects.Inventory;
import org.game.objects.entities.BotFactory;
import org.game.objects.entities.GameEntity;
import org.game.objects.entities.Player;
import org.game.userInterface.BattleInterface;
import org.game.userInterface.ConsoleUIPrinter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        startGame();
    }

    static void startGame() {
        List<GameEntity> objects = new ArrayList<>();
        objects.add(new Player(
                new BattleInterface(new ConsoleUIPrinter(), System.in),
                new Inventory(10)
        ));
        objects.addAll(BotFactory.createGoblins(2));
        Arena arena = new Arena(objects, List.of());

        boolean isDone = false;
        while(!isDone) {
            isDone = arena.processTurn();
        }
    }
}