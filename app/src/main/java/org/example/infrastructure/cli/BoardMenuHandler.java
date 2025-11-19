package org.example.infrastructure.cli;

import org.example.infrastructure.handlers.board.CreateBoardCommandHandler;
import org.example.infrastructure.handlers.board.GetAvailableBoardsHandler;

import java.util.Scanner;

public class BoardMenuHandler {
    private final Scanner inputScanner;
    private final CreateBoardCommandHandler createBoardCommandHandler;
    private final GetAvailableBoardsHandler getAvailableBoardsHandler;


    public BoardMenuHandler(Scanner inputScanner, CreateBoardCommandHandler createBoardCommandHandler, GetAvailableBoardsHandler getAvailableBoardsHandler) {
        this.inputScanner = inputScanner;
        this.createBoardCommandHandler = createBoardCommandHandler;
        this.getAvailableBoardsHandler = getAvailableBoardsHandler;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Please select the action: ");
            System.out.println("1 - create");
            System.out.println("2 - list");
            System.out.println("3 - back");

            System.out.println("> ");
            String command = this.inputScanner.nextLine();

            if (command.equals("3")) break;

            switch (command) {
                case "1","create" -> createBoardCommandHandler.handleCreate();
                case "2","list" -> getAvailableBoardsHandler.handleGet();

//                case "3","delete" -> System.out.println("delete");
                default -> System.out.println("Unknown command");
            }
        }
    }

}
