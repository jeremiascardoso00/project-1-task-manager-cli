package org.example.cli;

import org.example.application.usecases.CreateTaskUseCase;
import org.example.domain.model.Board;
import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateCommandHandler {
    private final Scanner inputScanner;
    private final CreateTaskUseCase createTaskUseCase;
    private List<Board> availableBoards;

    public CreateCommandHandler(Scanner inputScanner, CreateTaskUseCase createTaskUseCase) {
        this.inputScanner = inputScanner;
        this.createTaskUseCase = createTaskUseCase;
        this.availableBoards = null;
    }

    public void handleCreate() {
        System.out.println("Please write the title you want to use for the new Task");
        String title = inputScanner.nextLine();

        System.out.println("Please write the description you want to use for the new Task");
        String description = inputScanner.nextLine();

        Status status = selectStatus();

        Priority priority = selectPriority();

        Board selectedBoard = selectBoard();

        Task task = this.createTaskUseCase.Execute(title, description, status, priority, selectedBoard);
        System.out.println("Task created successfully: " + task.getTitle());
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

        //now using mock, the must be a service call
        this.availableBoards = createMockBoards();

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

    private List<Board> createMockBoards() {
        List<Board> mockBoards = new ArrayList<>();

        mockBoards.add(Board.newBoard("Backlog"));
        mockBoards.add(Board.newBoard("In Progress"));
        mockBoards.add(Board.newBoard("Review"));
        mockBoards.add(Board.newBoard("Done"));
        mockBoards.add(Board.newBoard("Urgent Tasks"));

        Task task1 = Task.newTask(null,"Design Database", "Create ER diagram", Status.TODO, Priority.HIGH);
        Task task2 = Task.newTask(null,"Implement API", "REST endpoints", Status.IN_PROGRESS, Priority.MEDIUM);

        mockBoards.get(0).addTask(task1);
        mockBoards.get(1).addTask(task2);

        return mockBoards;
    }
}