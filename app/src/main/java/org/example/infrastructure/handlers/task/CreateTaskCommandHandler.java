package org.example.infrastructure.handlers.task;

import org.example.application.usecases.CreateTaskUseCase;
import org.example.application.usecases.GetBoardsUseCase;
import org.example.application.usecases.models.responses.GetBoardResult;
import org.example.application.usecases.models.responses.GetTaskResult;
import org.example.domain.model.Board;
import org.example.domain.model.Priority;
import org.example.domain.model.Status;

import java.util.List;
import java.util.Scanner;

public class CreateTaskCommandHandler {
    private final Scanner inputScanner;
    private final CreateTaskUseCase createTaskUseCase;
    private final GetBoardsUseCase getBoardsUseCase;

    private List<Board> availableBoards;

    public CreateTaskCommandHandler(Scanner inputScanner, CreateTaskUseCase createTaskUseCase, GetBoardsUseCase getBoardsUseCase) {
        this.inputScanner = inputScanner;
        this.createTaskUseCase = createTaskUseCase;
        this.getBoardsUseCase = getBoardsUseCase;
        this.availableBoards = null;
    }

    public void handleCreate() {
        System.out.println("Please write the title you want to use for the new Task");
        String title = inputScanner.nextLine();

        System.out.println("Please write the description you want to use for the new Task");
        String description = inputScanner.nextLine();

        Status status = selectStatus();

        Priority priority = selectPriority();

        GetBoardResult getAvailableBoardsResult = getBoardsUseCase.execute(null, null);

        if (getAvailableBoardsResult.hasItems()) {
            this.availableBoards = getAvailableBoardsResult.getItems();
            selectBoard();
        }

        GetTaskResult result = this.createTaskUseCase.execute(title, description, status, priority);
        if (result.isSuccess()) {
            System.out.println("Task created successfully: " + result.getItems().get(0).getTitle());
        } else {
            System.out.println("Failed to create task: " + result.getMessage());
        }
    }

    private Status selectStatus() {
        Status status = null;
        while (status == null) {
            System.out.println("Please select the status: ");
            System.out.println("1 - TODO");
            System.out.println("2 - IN_PROGRESS");
            System.out.println("3 - DONE");

            status = switch (inputScanner.nextLine().trim().toUpperCase()) {
                case "1", "TODO" -> Status.TODO;
                case "2", "IN_PROGRESS" -> Status.IN_PROGRESS;
                case "3", "DONE" -> Status.DONE;
                default -> {
                    System.out.println("Invalid status. Please try again.");
                    yield null;
                }
            };
        }
        return status;
    }

    private Priority selectPriority() {
        Priority priority = null;
        while (priority == null) {
            System.out.println("Please select the priority: ");
            System.out.println("1 - HIGH");
            System.out.println("2 - MEDIUM");
            System.out.println("3 - LOW");

            priority = switch (inputScanner.nextLine().trim().toUpperCase()) {
                case "1", "HIGH" -> Priority.HIGH;
                case "2", "MEDIUM" -> Priority.MEDIUM;
                case "3", "LOW" -> Priority.LOW;
                default -> {
                    System.out.println("Invalid priority. Please try again.");
                    yield null;
                }
            };
        }
        return priority;
    }

    private Board selectBoard() {

        System.out.println("Available boards:");
        for (int i = 0; i < availableBoards.size(); i++) {
            Board board = availableBoards.get(i);
            System.out.println((i + 1) + " - " + board.getName() + " (Tasks: " + board.getTaskCount() + ")");
        }

        Board selectedBoard = null;
        while (selectedBoard == null) {
            System.out.println("Please select the board by number: ");

            try {
                String input = inputScanner.nextLine().trim();
                int boardIndex = Integer.parseInt(input) - 1;

                if (boardIndex >= 0 && boardIndex < availableBoards.size()) {
                    selectedBoard = availableBoards.get(boardIndex);
                } else {
                    System.out.println("Invalid board number. Please select a number between 1 and " + availableBoards.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return selectedBoard;
    }
}