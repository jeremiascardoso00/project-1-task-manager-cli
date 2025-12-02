package org.example.application.usecases;

import org.example.application.queries.BoardQuery;
import org.example.application.usecases.models.responses.GetBoardResult;
import org.example.domain.model.Board;
import org.example.domain.model.BoardRepository;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class GetAvailableBoardsUseCaseImpl implements GetBoardsUseCase {

    private BoardRepository boardRepository;
    private final GetBoardResult resultFactory;


    public GetAvailableBoardsUseCaseImpl(BoardRepository boardRepository, GetBoardResult resultFactory) {
        this.boardRepository = boardRepository;
        this.resultFactory = resultFactory;
    }

    @Override
    public GetBoardResult execute(Predicate<Board> filter, Comparator<Board> sorter) {
        try {
            List<Board> boards;
            if (filter == null && sorter == null) {
                boards = this.boardRepository.findAll();
            } else {
                if (filter == null) {
                    filter = board -> true;
                }
                if (sorter == null) {
                    sorter = BoardQuery.sortByCreationDateDesc();
                }
                boards = this.boardRepository.findWithFilters(filter, sorter);
            }

            if (boards == null || boards.isEmpty()){
                return resultFactory.empty("No boards available. Would you like to create one?");
            }

            return resultFactory.success(boards, "Found " + boards.size() + " board(s)");
        } catch (Exception e) {
            return resultFactory.error("Error loading boards: " + e.getMessage());
        }
    }
}
