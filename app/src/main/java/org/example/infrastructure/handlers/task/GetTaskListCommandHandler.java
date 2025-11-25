package org.example.infrastructure.handlers.task;

import org.example.application.usecases.GetTasksUseCase;
import org.example.application.usecases.models.responses.GetTaskResult;

import java.util.Objects;
import java.util.Scanner;

public class GetTaskListCommandHandler {

    private final Scanner inputScanner;
    private final GetTasksUseCase getTasksUseCase;

    public GetTaskListCommandHandler(Scanner inputScanner, GetTasksUseCase getTasksUseCase) {
        this.inputScanner = inputScanner;
        this.getTasksUseCase = getTasksUseCase;
    }

    public void handleGet() {
        String response;

        System.out.println("Do you want to filter the list?");
        System.out.println("1 - No");
        System.out.println("2 - Yes");
        response = inputScanner.nextLine();
        if (Objects.equals(response, "2") || response.toLowerCase().trim().equals("yes")) {
            handleFilters();
        }

        GetTaskResult getTaskResult = getTasksUseCase.execute();

        System.out.println(getTaskResult.getMessage());

        if (getTaskResult.hasItems()) {
            System.out.println(getTaskResult.getItems().toString() + "\n");
        } else {
            System.out.println(getTaskResult.error("Tasks not found"));
        }
    }

    private void handleFilters() {
        Boolean continueFilter = true;
        String response = "";


        while(continueFilter){
            if (response.equals("0")) continueFilter = false;

            System.out.println("Select the field you want to filter");
            System.out.println("1 - Title");
            System.out.println("2 - Description");
            System.out.println("3 - Status");
            System.out.println("4 - Priority");
            System.out.println("0 - Back");
            response = inputScanner.nextLine();

            switch (response) {
                case "1" -> {
                    System.out.println("Please write the title you need to search");
                    response = inputScanner.nextLine();

                }
                case "2" -> {}
                case "3" -> {}
                case "4" -> {}
                case "0" -> continueFilter = false;
                default -> System.out.println("Unknown command");
            }

        }
    }
}
