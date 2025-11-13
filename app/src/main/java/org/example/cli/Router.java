package org.example.cli;

import org.example.application.usecases.CreateTaskUseCase;
import org.example.infrastructure.JsonRepository;

import java.util.Scanner;

public class Router {
    private final Scanner inputScanner;

    public Router() {
        this.inputScanner = new Scanner(System.in);
    }

    public void StartListening() {

        System.out.println("Starting input scan");

        while (true) {
            System.out.println("Please select the action: ");
            System.out.println("1 - create");
            System.out.println("2 - delete");
            System.out.println("3 - list");

            System.out.println("> ");
            String command = this.inputScanner.nextLine();

            if (command.equals("exit")) break;
            switch (command) {
                case "1","create" -> SetupCreateHandler();
//                case "2","delete" -> System.out.println("delete");
//                case "3","list" -> service.listTasks().forEach(System.out::println);

                default -> System.out.println("Unknown command");
            }
        }
    }

    private void SetupCreateHandler() {
        //using jsonRepository
        JsonRepository repository = new JsonRepository();

        //using createTaskUseCase
        CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(repository);
        CreateCommandHandler commandHandler = new CreateCommandHandler(this.inputScanner,
                createTaskUseCase);
        commandHandler.handleCreate();

    }
}
