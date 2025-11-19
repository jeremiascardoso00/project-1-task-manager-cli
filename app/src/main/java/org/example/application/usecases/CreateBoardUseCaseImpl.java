package org.example.application.usecases;

import org.example.domain.model.Board;
import org.example.domain.model.BoardRepository;

public class CreateBoardUseCaseImpl implements CreateBoardUseCase {
    private BoardRepository boardRepository;

    public CreateBoardUseCaseImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board execute(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Board name cannot be empty");
        }

        return boardRepository.save(Board.newBoard(name.trim()));
    }
}
