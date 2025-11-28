package org.example.application.usecases.models.responses;

import org.example.application.errors.boards.BoardExceptionFactory;
import org.example.domain.model.Board;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class GetBoardResult extends BaseGetResult<Board>{

    private final BoardExceptionFactory exceptionFactory;
    private final Logger logger;

    public GetBoardResult(Logger logger) {
        super(false, "", Collections.emptyList());
        this.exceptionFactory = new BoardExceptionFactory(logger);
        this.logger = logger;
    }

    private GetBoardResult(boolean success, String message, List<Board> boards, Logger logger, BoardExceptionFactory exceptionFactory) {
        super(success, message, boards);
        this.exceptionFactory = exceptionFactory;
        this.logger = logger;
    }

    public GetBoardResult success(List<Board> boards) {
        return new GetBoardResult(true, "Boards loaded successfully", boards, logger, exceptionFactory);
    }

    public GetBoardResult success(List<Board> boards, String message) {
        return new GetBoardResult(true, message, boards, logger, exceptionFactory);
    }

    public GetBoardResult success(Board board) {
        return new GetBoardResult(true, "Boards loaded successfully", List.of(board), logger, exceptionFactory);
    }

    public GetBoardResult success(Board board, String message) {
        return new GetBoardResult(true, message, List.of(board), logger, exceptionFactory);
    }

    public GetBoardResult empty(String message) {
        return new GetBoardResult(true, message, Collections.emptyList(), logger, exceptionFactory);
    }

    public GetBoardResult empty() {
        return new GetBoardResult(true, "No tasks found", Collections.emptyList(), logger, exceptionFactory);
    }

    public GetBoardResult error(String message) {
        logger.warning("Operation failed: " + message);

        return new GetBoardResult(false, message, Collections.emptyList(), logger, exceptionFactory);
    }
    public GetBoardResult error(Exception exception) {
        logger.severe("Operation failed with exception: " + exception.getMessage());
        return new GetBoardResult(false, exception.getMessage(), Collections.emptyList(),
                logger, exceptionFactory);
    }

    public GetBoardResult invalidBoard(String message, String taskId) {
        return error(exceptionFactory.invalidBoard(message, taskId));
    }

    public GetBoardResult invalidBoard(String message, String taskId, Throwable cause) {
        return error(exceptionFactory.invalidBoard(message, taskId, cause));
    }

    public GetBoardResult persistenceError(String taskId, String operation) {
        return error(exceptionFactory.persistenceError(taskId, operation));
    }

    public GetBoardResult persistenceError(String taskId, String operation, Throwable cause) {
        return error(exceptionFactory.persistenceError(taskId, operation, cause));
    }

    public GetBoardResult taskNotFound(String taskId) {
        return error(exceptionFactory.boardNotFound(taskId));
    }

    public GetBoardResult taskNotFound(String taskId, Throwable cause) {
        return error(exceptionFactory.boardNotFound(taskId, cause));
    }
}
