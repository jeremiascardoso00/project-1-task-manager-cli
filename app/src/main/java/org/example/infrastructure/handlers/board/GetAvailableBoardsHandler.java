package org.example.infrastructure.handlers.board;

import org.example.application.usecases.GetAvailableBoardsUseCase;
import org.example.application.usecases.models.responses.GetAvailableBoardsResult;
import org.example.domain.model.Board;

import java.util.List;

public class GetAvailableBoardsHandler {
    private final GetAvailableBoardsUseCase getAvailableBoardsUseCase;

    private List<Board> availableBoards;

    public GetAvailableBoardsHandler( GetAvailableBoardsUseCase getAvailableBoardsUseCase) {
        this.getAvailableBoardsUseCase = getAvailableBoardsUseCase;
        this.availableBoards = null;
    }

    public void handleGet() {
        GetAvailableBoardsResult getAvailableBoardsResult = getAvailableBoardsUseCase.execute();
        System.out.println(getAvailableBoardsResult.getMessage());
        if (getAvailableBoardsResult.hasItems()) {
            System.out.println(getAvailableBoardsResult.getItems());
        } else {
            System.out.println(getAvailableBoardsResult.error("Boards not found"));
        }
    }

}
