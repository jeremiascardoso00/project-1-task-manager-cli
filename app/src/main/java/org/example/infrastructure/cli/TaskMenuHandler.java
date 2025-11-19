package org.example.infrastructure.cli;

import org.example.infrastructure.handlers.task.CreateTaskCommandHandler;

import java.util.Scanner;

public class TaskMenuHandler {
    private final Scanner inputScanner;
    private final CreateTaskCommandHandler createTaskCommandHandler;

    public TaskMenuHandler(Scanner inputScanner, CreateTaskCommandHandler createTaskCommandHandler) {
        this.inputScanner = inputScanner;
        this.createTaskCommandHandler = createTaskCommandHandler;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Please select the action: ");
            System.out.println("1 - create");
            System.out.println("2 - delete");
            System.out.println("3 - list");

            System.out.println("> ");
            String command = this.inputScanner.nextLine();

            if (command.equals("exit")) break;
            switch (command) {
                case "1","create" -> createTaskCommandHandler.handleCreate();
//                case "2","delete" -> System.out.println("delete");
//                case "3","list" -> Arrays.asList(1).forEach(System.out::println);

                default -> System.out.println("Unknown command");
            }
        }
    }
}
