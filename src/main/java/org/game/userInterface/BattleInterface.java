package org.game.userInterface;

import org.game.actions.AttackAction;
import org.game.actions.GameAction;
import org.game.actions.HealAction;
import org.game.objects.entities.GameEntity;
import org.game.objects.entities.Player;
import org.game.userInterface.menuActions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class BattleInterface {
    public static String USER_INPUT = ">: ";
    private UI_Printer printer;

    private final Scanner scanner;


    private List<String> storageOfMessages;

    public BattleInterface(UI_Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    public GameAction openFightInterface(List<GameEntity> entities) {
        if (!storageOfMessages.isEmpty()) {
            for (int i = 0; i < storageOfMessages.size(); i++) {
                printer.println(storageOfMessages.get(i));
            }

            storageOfMessages.clear();
        }

        Player player = (Player) entities.stream().filter(obj -> obj instanceof Player).findFirst().get();

        List<GameEntity> enemies = entities.stream()
                .filter(Predicate.not(obj -> obj instanceof Player))
                .toList();

        if (!enemies.isEmpty()) {
            this.printer.println("You see enemies:");
            entities.forEach(obj -> this.printer.println((obj.getGameLogState())));
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
                if (menuActionResult != null) {
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

    private void storeActionForPrint(String action) {
        if (storageOfMessages == null) {
            storageOfMessages = new ArrayList<>();
        }
        storageOfMessages.add(action);
    }

    public void printAction(GameAction action) {
        if (action instanceof AttackAction) {
            this.storeActionForPrint(action.getSender() + " kick " + action.getReceiver() + " with damage " + ((AttackAction) action).getDamage());
        } else if (action instanceof HealAction) {
            this.storeActionForPrint(action.getSender() + " healed " + action.getReceiver() + " on " + ((AttackAction) action).getDamage() + " health points");
        }
    }
}
