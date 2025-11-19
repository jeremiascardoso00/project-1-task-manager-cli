package org.example.infrastructure.cli;

import java.util.Scanner;

public class MainMenuHandler {

    private final  TaskMenuHandler taskMenuHandler;
    private final BoardMenuHandler boardMenuHandler;
    private final Scanner inputScanner;

    public MainMenuHandler(Scanner inputScanner,TaskMenuHandler taskMenuHandler, BoardMenuHandler boardMenuHandler){
        this.taskMenuHandler = taskMenuHandler;
        this.boardMenuHandler = boardMenuHandler;
        this.inputScanner = inputScanner;
    }

    public void showMenu() {
        Boolean continueLoop = true;
        while (continueLoop) {
            showMainMenu();
            String command = this.inputScanner.nextLine();
            if (command.equals("3")) continueLoop = false;
            switch (command) {
                case "1","boards" -> boardMenuHandler.showMenu();
                case "2","tasks" -> taskMenuHandler.showMenu();
                default -> System.out.println("Unknown command");
            }
        }
        System.out.println("Program exit.");
    }

    public void showMainMenu() {
        System.out.println("=== TASK MANAGER ===");
        System.out.println("1. Work with Boards");
        System.out.println("2. Work with Tasks");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }
}
