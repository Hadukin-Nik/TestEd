package org.game.userInterface;

import org.game.actions.AttackAction;
import org.game.actions.DodgeAction;
import org.game.actions.GameAction;
import org.game.actions.HealAction;
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
    private UI_Printer printer;

    private final Scanner scanner;


    private List<GameAction> storageOfMessages;
    private HashMap<String, GameEntity> entityHashMap;

    public BattleInterface(UI_Printer printer, InputStream input) {
        this.printer = printer;
        this.scanner = new Scanner(input);

        storageOfMessages = new ArrayList<>();
    }

    public GameAction openFightInterface(List<GameEntity> entities) {
        entityHashMap = new HashMap<>(entities.stream().collect(Collectors.toMap(GameEntity::getId, Function.identity())));

        printAction();

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

    private void printAction() {
        for (GameAction action:storageOfMessages) {
            if (action instanceof AttackAction) {
                this.printer.println(entityHashMap.get(action.getSender()).getName() + " kick " + entityHashMap.get(action.getReceiver()).getName()  + " with damage " + ((AttackAction) action).getDamage());
            } else if (action instanceof HealAction) {
                this.printer.println(entityHashMap.get(action.getSender()).getName()  + " healed " + entityHashMap.get(action.getReceiver()).getName() + " on " + ((HealAction) action).getHealth() + " health points");
            } else if (action instanceof DodgeAction) {
                this.printer.println(entityHashMap.get(action.getSender()).getName()  + " dodged attack of " + entityHashMap.get(action.getReceiver()).getName());

            }
        }
        storageOfMessages.clear();
    }
    public void printAction(GameAction action) {
        storageOfMessages.add(action);
    }
}

