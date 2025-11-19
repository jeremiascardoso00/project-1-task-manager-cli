package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetAvailableBoardsResult;
import org.example.domain.model.Board;
import org.example.domain.model.BoardRepository;

import java.util.List;


public class GetAvailableBoardsUseCaseImpl implements GetAvailableBoardsUseCase {

    private BoardRepository boardRepository;

    public GetAvailableBoardsUseCaseImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public GetAvailableBoardsResult execute() {

        try {
            //only have one user by te moment, so all the boards are her/his/they property
            this.boardRepository.findAll();

            List<Board> boards = this.boardRepository.findAll();

            if (boards == null || boards.isEmpty()){
                return GetAvailableBoardsResult.empty("No boards available. Would you like to create one?");
            }

            return GetAvailableBoardsResult.success(boards, "Found " + boards.size() + " board(s)");
        } catch (Exception e) {
            return GetAvailableBoardsResult.error("Error loading boards: " + e.getMessage());
        }
    }
}
