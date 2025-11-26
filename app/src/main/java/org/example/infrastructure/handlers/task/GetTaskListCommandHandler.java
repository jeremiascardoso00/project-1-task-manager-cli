package org.example.infrastructure.handlers.task;

import org.example.application.usecases.GetTasksUseCase;
import org.example.application.usecases.models.responses.GetTaskResult;

import org.example.application.queries.TaskQuery;
import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;

public class GetTaskListCommandHandler {

    private final Scanner inputScanner;
    private final GetTasksUseCase getTasksUseCase;

    public GetTaskListCommandHandler(Scanner inputScanner, GetTasksUseCase getTasksUseCase) {
        this.inputScanner = inputScanner;
        this.getTasksUseCase = getTasksUseCase;
    }

    public void handleGet() {
        Predicate<Task> filter = null;
        Comparator<Task> sorter = null;

        System.out.println("Do you want to filter the list?");
        System.out.println("1 - No");
        System.out.println("2 - Yes");
        String response = inputScanner.nextLine();
        
        if (Objects.equals(response, "2") || response.toLowerCase().trim().equals("yes")) {
            filter = handleFilters();
        }

        System.out.println("Do you want to sort the list?");
        System.out.println("1 - No");
        System.out.println("2 - Yes");
        response = inputScanner.nextLine();

        if (Objects.equals(response, "2") || response.toLowerCase().trim().equals("yes")) {
            sorter = handleSorting();
        }

        GetTaskResult getTaskResult = getTasksUseCase.execute(filter, sorter);

        System.out.println(getTaskResult.getMessage());

        if (getTaskResult.hasItems()) {
            System.out.println(getTaskResult.getItems().toString() + "\n");
        } else {
            System.out.println(getTaskResult.error("Tasks not found"));
        }
    }

    private Predicate<Task> handleFilters() {
        Predicate<Task> filter = null;
        boolean continueFilter = true;

        while(continueFilter){
            System.out.println("Select the field you want to filter (Filters will be combined with AND):");
            System.out.println("1 - Search (Title/Description)");
            System.out.println("2 - Status");
            System.out.println("3 - Priority");
            System.out.println("4 - Date Range");
            System.out.println("0 - Done/Back");
            String response = inputScanner.nextLine();

            Predicate<Task> newFilter = null;

            switch (response) {
                case "1" -> {
                    System.out.println("Enter search text:");
                    String text = inputScanner.nextLine();
                    newFilter = TaskQuery.search(text);
                }
                case "2" -> {
                    System.out.println("Enter status (TODO, IN_PROGRESS, DONE):");
                    try {
                        Status status = Status.valueOf(inputScanner.nextLine().toUpperCase());
                        newFilter = TaskQuery.byStatus(status);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status.");
                    }
                }
                case "3" -> {
                    System.out.println("Enter priority (LOW, MEDIUM, HIGH):");
                    try {
                        Priority priority = Priority.valueOf(inputScanner.nextLine().toUpperCase());
                        newFilter = TaskQuery.byPriority(priority);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid priority.");
                    }
                }
                case "4" -> {
                    try {
                        System.out.println("Enter start date (YYYY-MM-DD):");
                        LocalDate start = LocalDate.parse(inputScanner.nextLine());
                        System.out.println("Enter end date (YYYY-MM-DD):");
                        LocalDate end = LocalDate.parse(inputScanner.nextLine());
                        newFilter = TaskQuery.betweenDates(start, end);
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                }
                case "0" -> continueFilter = false;
                default -> System.out.println("Unknown command");
            }

            if (newFilter != null) {
                if (filter == null) {
                    filter = newFilter;
                } else {
                    filter = filter.and(newFilter);
                }
                System.out.println("Filter added.");
            }
        }
        return filter;
    }

    private Comparator<Task> handleSorting() {
        Comparator<Task> sorter = null;
        boolean continueSorting = true;

        while (continueSorting) {
            System.out.println("Select the field you want to sort by (Sorters will be chained):");
            System.out.println("1 - Priority (Low to High)");
            System.out.println("2 - Priority (High to Low)");
            System.out.println("3 - Date (Oldest first)");
            System.out.println("4 - Date (Newest first)");
            System.out.println("5 - Title");
            System.out.println("0 - Done/Back");
            String response = inputScanner.nextLine();

            Comparator<Task> newSorter = null;

            switch (response) {
                case "1" -> newSorter = TaskQuery.sortByPriority();
                case "2" -> newSorter = TaskQuery.sortByPriorityDesc();
                case "3" -> newSorter = TaskQuery.sortByCreationDate();
                case "4" -> newSorter = TaskQuery.sortByCreationDateDesc();
                case "5" -> newSorter = TaskQuery.sortByTitle();
                case "0" -> continueSorting = false;
                default -> System.out.println("Unknown command");
            }

            if (newSorter != null) {
                if (sorter == null) {
                    sorter = newSorter;
                } else {
                    sorter = sorter.thenComparing(newSorter);
                }
                System.out.println("Sorter added.");
            }
        }
        return sorter;
    }
}
