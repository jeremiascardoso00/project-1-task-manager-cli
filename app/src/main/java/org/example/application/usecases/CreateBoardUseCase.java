package org.example.application.usecases;

import org.example.domain.model.Board;

public interface CreateBoardUseCase {
    Board execute(String name);
}
