package org.example.infrastructure.handlers.board;

import org.example.application.usecases.models.responses.GetBoardResult;
import org.example.application.usecases.CreateBoardUseCase;

import java.util.Scanner;

public class CreateBoardCommandHandler {
    private final Scanner inputScanner;
    private final CreateBoardUseCase createBoardUseCase;

    public CreateBoardCommandHandler(Scanner inputScanner, CreateBoardUseCase createBoardUseCase) {
        this.inputScanner = inputScanner;
        this.createBoardUseCase = createBoardUseCase;
    }

    public void handleCreate() {
        System.out.println("Please write the title you want to use for the new Board");
        String title = inputScanner.nextLine();
        GetBoardResult result = createBoardUseCase.execute(title);
        if (result.isSuccess()) {
            System.out.println("Board created successfully: " + result.getItems().get(0).getName());
        } else {
            System.out.println("Failed to create board: " + result.getMessage());
        }
    }

}
