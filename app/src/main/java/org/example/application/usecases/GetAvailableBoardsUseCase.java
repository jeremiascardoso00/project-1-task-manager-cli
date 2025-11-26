package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetAvailableBoardsResult;

public interface GetAvailableBoardsUseCase {
    GetAvailableBoardsResult execute(Predicate<Board> filter, Comparator<Board> sorter);
}
