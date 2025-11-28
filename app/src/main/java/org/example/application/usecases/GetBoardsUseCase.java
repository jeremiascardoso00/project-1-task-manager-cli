package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetBoardResult;
import org.example.domain.model.Board;

import java.util.Comparator;
import java.util.function.Predicate;

public interface GetBoardsUseCase {
    GetBoardResult execute(Predicate<Board> filter, Comparator<Board> sorter);
}
