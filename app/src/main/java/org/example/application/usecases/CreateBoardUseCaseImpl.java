package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetBoardResult;
import org.example.domain.model.Board;
import org.example.domain.model.BoardRepository;

public class CreateBoardUseCaseImpl implements CreateBoardUseCase {
    private BoardRepository boardRepository;
    private final GetBoardResult getBoardResult;

    public CreateBoardUseCaseImpl(BoardRepository boardRepository, GetBoardResult getBoardResult) {
        this.boardRepository = boardRepository;
        this.getBoardResult = getBoardResult;
    }

    @Override
    public GetBoardResult execute(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                return getBoardResult.invalidBoard("Board name cannot be empty", null);
            }
            Board board = boardRepository.save(Board.newBoard(name.trim()));
            return getBoardResult.success(board);
        } catch (Exception e) {
            return getBoardResult.error(e);
        }
    }
}
