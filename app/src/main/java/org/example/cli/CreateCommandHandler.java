package org.example.cli;

import org.example.application.usecases.CreateTaskUseCase;
import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;

import java.util.Scanner;



public class CreateCommandHandler {
    private final Scanner inputScanner;
    private final CreateTaskUseCase createTaskUseCase;

    public CreateCommandHandler(Scanner inputScanner, CreateTaskUseCase createTaskUseCase) {
        this.inputScanner = inputScanner;
        this.createTaskUseCase = createTaskUseCase;
    }

    public void handleCreate() {
        System.out.println("Please write the title you want to use for the new Task");
        String title = inputScanner.nextLine();

        System.out.println("Please write the description you want to use for the new Task");
        String description = inputScanner.nextLine();

        Status status = null;
        while (status == null){
            System.out.println("Please select the status: ");
            System.out.println("1 - TODO");
            System.out.println("2 - IN_PROGRESS");
            System.out.println("3 - DONE");

            status = switch (inputScanner.nextLine()) {
                case "1", "TODO" -> Status.TODO;
                case "2", "IN_PROGRESS" -> Status.IN_PROGRESS;
                case "3", "DONE" -> Status.DONE;
                default -> null;
            };
        }

        Priority priority = null;
        while (priority == null){
            System.out.println("Please select the priority: ");
            System.out.println("1 - HIGH");
            System.out.println("2 - MEDIUM");
            System.out.println("3 - LOW");

            priority = switch (inputScanner.nextLine()) {
                case "1", "HIGH" -> Priority.HIGH;
                case "2", "MEDIUM" -> Priority.MEDIUM;
                case "3", "LOW" -> Priority.LOW;
                default -> null;
            };
        }

        Task task = this.createTaskUseCase.Execute(title,description,status,priority);

    }
}
