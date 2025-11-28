package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetBoardResult;
import org.example.domain.model.Board;

public interface CreateBoardUseCase {
    GetBoardResult execute(String name);
}
