
package com.december.bikemanager.cli;

import java.util.Scanner;

import com.december.bikemanager.*;

public class CLIComponent {
    public String name = "Unnamed";
    public String menuNotice = "";
    public String lastInput = null;
    public String errorText = "";

    private Scanner scanner = null;

    public CLIComponent(Scanner scanner) {
        this.scanner = scanner;
    }

    public void println(Object... objs) {
        for (Object obj : objs) {
            System.out.print(obj);
        }
        System.out.println("");
    }

    public void print(Object... objs) {
        for (Object obj : objs) {
            System.out.print(obj);
        }
    }

    // public int readInt() {
    // return this.scanner.nextInt();
    // }

    public String readln() {
        String userInput = this.scanner.nextLine();
        lastInput = userInput;

        return userInput;
    }

    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name");

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public void init() {
        clearConsole();
    }

    public void render() {
        println("Render not implemented");
        println("Press Enter to return");
        readln();

        navBack();
    }

    public void clean() {

    }

    public void navNext(int page) {
        Bikemanager.navPanel(page);
    }

    public void navBack() {
        Bikemanager.navBack();
    }

    public void backToMenu() {
        Bikemanager.setPanel(0);
    }

}
