package org.game.userInterface;

public class ConsoleUI_Printer implements UI_Printer{
    @Override
    public void print(String str) {
        System.out.print(str);
    }

    @Override
    public void println(String str) {
        System.out.println(str);
    }
}
