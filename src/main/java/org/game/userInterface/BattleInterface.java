package org.game.userInterface;

import org.game.actions.*;
import org.game.objects.entities.GameEntity;
import org.game.objects.entities.Player;
import org.game.userInterface.menuActions.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BattleInterface {
    public static String USER_INPUT = ">: ";
    private UIPrinter printer;

    private final Scanner scanner;


    private List<GameAction> storageOfMessages;
    private HashMap<String, GameEntity> entityHashMap;

    public BattleInterface(UIPrinter printer, InputStream input) {
        this.printer = printer;
        this.scanner = new Scanner(input);

        storageOfMessages = new ArrayList<>();
    }

    public GameAction openFightInterface(List<GameEntity> entities) {
        entityHashMap = new HashMap<>(entities.stream().collect(Collectors.toMap(GameEntity::getId, Function.identity())));

        printActions();

        Player player = (Player) entities.stream().filter(obj -> obj instanceof Player).findFirst().get();

        printer.println("Player health is: " + player.getHealth());

        List<GameEntity> enemies = entities.stream()
                .filter(Predicate.not(obj -> obj instanceof Player))
                .toList();

        if (!enemies.isEmpty()) {
            this.printer.println("You see enemies:");
            enemies.forEach(obj -> this.printer.println((obj.getGameLogState())));
        }
        RootMenu rootMenu = new RootMenu(new AttackMenu(player, enemies), new InventoryMenu(player));

        return getActionFromMenu(rootMenu);
    }

    private GameAction getActionFromMenu(MenuAction menuAction) {
        this.printer.println(menuAction.getHeader());

        List<String> options = menuAction.getOptions();

        for (int i = 0; i < options.size(); i++) {
            this.printer.println(String.format("[%s] %s", i + 1, options.get(i)));
        }

        MenuAction root = menuAction.getRoot();
        if(root != null) {
            this.printer.println("[-] Back");
        }

        this.printer.print(USER_INPUT);

        while(true) {
            while(!scanner.hasNextLine()) {

            }
            try {
                String inputStr = scanner.nextLine();
                if(inputStr.equals("-") && root != null) {
                    return getActionFromMenu(root);
                }

                int inputInt = Integer.parseInt(inputStr) - 1;
                if(inputInt < 0 || inputInt >= options.size()) {
                    printOptionError(options.size());
                    continue;
                }
                MenuActionResult menuActionResult = menuAction.createAction(inputInt);
                if (menuActionResult.getMenuAction() != null) {
                    return getActionFromMenu(menuActionResult.getMenuAction());
                }

                return menuActionResult.getGameAction();
            } catch (NumberFormatException nfx) {
                printOptionError(options.size());
            }

        }
    }


    void printOptionError(int optionSize) {
        this.printer.println(String.format("Input enumerator from 1 to %s", optionSize));
        this.printer.print(USER_INPUT);
    }

    public void printActions() {
        for (GameAction action:storageOfMessages) {
            if (action instanceof AttackAction) {
                int damage = ((AttackAction) action).getDamage();
                this.printer.println(entityHashMap.get(action.getSender()).getName() + " kick " + entityHashMap.get(action.getReceiver()).getName()  + " with damage " + damage);
            } else if (action instanceof HealAction) {
                int health = ((HealAction) action).getHealth();
                this.printer.println(entityHashMap.get(action.getSender()).getName()  + " healed " + entityHashMap.get(action.getReceiver()).getName() + " on " + health + " health points");
            } else if (action instanceof DodgeAction) {
                this.printer.println(entityHashMap.get(action.getSender()).getName()  + " dodged attack of " + entityHashMap.get(action.getReceiver()).getName());
            } else if (action instanceof GameEndAction) {
                this.printer.println(((GameEndAction) action).getMessage());
            }else if (action instanceof DeathAction) {
                this.printer.println(((DeathAction) action).getMessage());
            } else {
                this.printer.println("UNKNOWN ACTION TO PRINT");
            }
        }
        storageOfMessages.clear();
    }
    public void addActionLogToQueue(GameAction action) {
        storageOfMessages.add(action);
    }
    public void addActionLogToQueueAndPrint(GameAction action) {
        addActionLogToQueue(action);
        printActions();
    }


}

