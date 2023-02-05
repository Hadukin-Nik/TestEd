package org.game.userInterface;

import org.game.objects.Inventory;
import org.game.objects.entities.Bot;
import org.game.objects.entities.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BattleInterfaceTest {
    class FakePrinter implements UIPrinter {
        List<String> out = new ArrayList<>();

        @Override
        public String toString() {
            return out.stream().collect(Collectors.joining(""));
        }

        @Override
        public void print(String str) {
            out.add(str);
        }

        @Override
        public void println(String str) {
            out.add(str + "\n");
        }
    }

    private String rootOptions =
                    "Choose a turn: \n" +
                    "[1] Attack\n" +
                    "[2] Use inventory\n" +
                    "[3] Pass\n" +
            BattleInterface.USER_INPUT;

    @Test
    void printStartScreenForPass() {
        FakePrinter fakePrinter = new FakePrinter();
        Player player = new Player();
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("3".getBytes(StandardCharsets.UTF_8));
        BattleInterface battleInterface = new BattleInterface(fakePrinter, fakeInput);
        Bot bot = new Bot("testBot");



        battleInterface.openFightInterface(List.of(bot, player));
        String expectedResult = "Player health is: 100.0\n" +
                "You see enemies:\n" +
                String.format("The %s have %s health points\n", bot.getName(), bot.getHealth()) +
                rootOptions;
        assertEquals(expectedResult, fakePrinter.toString());
    }

    @Test
    void printStartScreenForAttack() {
        FakePrinter fakePrinter = new FakePrinter();
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("1\n1".getBytes(StandardCharsets.UTF_8));
        Player player = new Player();
        BattleInterface battleInterface = new BattleInterface(fakePrinter, fakeInput);
        Bot bot = new Bot("testBot");

        battleInterface.openFightInterface(List.of(bot, player));
        String expectedResult = "Player health is: 100.0\n" +
                "You see enemies:\n" +
                String.format("The %s have %s health points\n", bot.getName(), bot.getHealth()) +
                rootOptions +
                "Choose an enemy: \n" +
                String.format("[1] %s\n", bot.getName()) +
                "[-] Back\n" +
                BattleInterface.USER_INPUT;
        assertEquals(expectedResult, fakePrinter.toString());
    }

    @Test
    void printStartScreenForHealth() {
        FakePrinter fakePrinter = new FakePrinter();
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("2\n-\n3".getBytes(StandardCharsets.UTF_8));
        Player player = new Player(null, new Inventory(3));
        BattleInterface battleInterface = new BattleInterface(fakePrinter, fakeInput);
        Bot bot = new Bot("testBot");

        battleInterface.openFightInterface(List.of(bot, player));
        String expectedResult = "Player health is: 100.0\n" +
                "You see enemies:\n" +
                String.format("The %s have %s health points\n", bot.getName(), bot.getHealth()) +
                rootOptions +
                "What item will you use?\n" +
                "[1] Small healing potion: 3\n" +
                "[-] Back\n" +
                BattleInterface.USER_INPUT + rootOptions;
        assertEquals(expectedResult, fakePrinter.toString());
    }

    @Test
    void printStartScreenAfterBack(){
        FakePrinter fakePrinter = new FakePrinter();
        Player player = new Player();
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("1\n-\n3".getBytes(StandardCharsets.UTF_8));
        BattleInterface battleInterface = new BattleInterface(fakePrinter, fakeInput);
        Bot bot = new Bot("testBot");

        battleInterface.openFightInterface(List.of(bot, player));
        String expectedResult = "Player health is: 100.0\n" +
                "You see enemies:\n" +
                String.format("The %s have %s health points\n", bot.getName(), bot.getHealth()) +
                rootOptions +
                "Choose an enemy: \n" +
                String.format("[1] %s\n", bot.getName()) +
                "[-] Back\n" +
                BattleInterface.USER_INPUT +
                rootOptions;
        assertEquals(expectedResult, fakePrinter.toString());
    }
}