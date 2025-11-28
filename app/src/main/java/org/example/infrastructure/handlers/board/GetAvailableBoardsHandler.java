package org.example.infrastructure.handlers.board;

import org.example.application.usecases.GetBoardsUseCase;
import org.example.application.usecases.models.responses.GetBoardResult;
import org.example.application.queries.BoardQuery;
import org.example.domain.model.Board;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;

public class GetAvailableBoardsHandler {
    private final GetBoardsUseCase getBoardsUseCase;

    private final Scanner inputScanner;
    private List<Board> availableBoards;

    public GetAvailableBoardsHandler(Scanner inputScanner, GetBoardsUseCase getBoardsUseCase) {
        this.inputScanner = inputScanner;
        this.getBoardsUseCase = getBoardsUseCase;
        this.availableBoards = null;
    }

    public void handleGet() {
        Predicate<Board> filter = null;
        Comparator<Board> sorter = null;

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

        GetBoardResult getAvailableBoardsResult = getBoardsUseCase.execute(filter, sorter);
        System.out.println(getAvailableBoardsResult.getMessage());
        if (getAvailableBoardsResult.hasItems()) {
            System.out.println(getAvailableBoardsResult.getItems());
        } else {
            System.out.println(getAvailableBoardsResult.error("Boards not found"));
        }
    }

    private Predicate<Board> handleFilters() {
        Predicate<Board> filter = null;
        boolean continueFilter = true;

        while(continueFilter){
            System.out.println("Select the field you want to filter (Filters will be combined with AND):");
            System.out.println("1 - Search (Name)");
            System.out.println("2 - Date Range");
            System.out.println("0 - Done/Back");
            String response = inputScanner.nextLine();

            Predicate<Board> newFilter = null;

            switch (response) {
                case "1" -> {
                    System.out.println("Enter search text:");
                    String text = inputScanner.nextLine();
                    newFilter = BoardQuery.search(text);
                }
                case "2" -> {
                    try {
                        System.out.println("Enter start date (YYYY-MM-DD):");
                        LocalDate start = LocalDate.parse(inputScanner.nextLine());
                        System.out.println("Enter end date (YYYY-MM-DD):");
                        LocalDate end = LocalDate.parse(inputScanner.nextLine());
                        newFilter = BoardQuery.betweenDates(start, end);
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

    private Comparator<Board> handleSorting() {
        Comparator<Board> sorter = null;
        boolean continueSorting = true;

        while (continueSorting) {
            System.out.println("Select the field you want to sort by (Sorters will be chained):");
            System.out.println("1 - Name");
            System.out.println("2 - Date (Oldest first)");
            System.out.println("3 - Date (Newest first)");
            System.out.println("4 - Task Count (Low to High)");
            System.out.println("5 - Task Count (High to Low)");
            System.out.println("0 - Done/Back");
            String response = inputScanner.nextLine();

            Comparator<Board> newSorter = null;

            switch (response) {
                case "1" -> newSorter = BoardQuery.sortByName();
                case "2" -> newSorter = BoardQuery.sortByCreationDate();
                case "3" -> newSorter = BoardQuery.sortByCreationDateDesc();
                case "4" -> newSorter = BoardQuery.sortByTaskCount();
                case "5" -> newSorter = BoardQuery.sortByTaskCountDesc();
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
