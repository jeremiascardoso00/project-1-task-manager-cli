package org.example.infrastructure.cli;

import org.example.infrastructure.handlers.task.CreateTaskCommandHandler;
import org.example.infrastructure.handlers.task.GetTaskListCommandHandler;

import java.util.Scanner;

public class TaskMenuHandler {
    private final Scanner inputScanner;
    private final CreateTaskCommandHandler createTaskCommandHandler;
    private final GetTaskListCommandHandler getTaskListCommandHandler;


    public TaskMenuHandler(Scanner inputScanner,
                           CreateTaskCommandHandler createTaskCommandHandler,
                           GetTaskListCommandHandler getTaskListCommandHandler) {
        this.inputScanner = inputScanner;
        this.createTaskCommandHandler = createTaskCommandHandler;
        this.getTaskListCommandHandler = getTaskListCommandHandler;
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
                case "2","delete" -> System.out.println("delete");
                case "3","list" -> getTaskListCommandHandler.handleGet();

                default -> System.out.println("Unknown command");
            }
        }
    }
}
