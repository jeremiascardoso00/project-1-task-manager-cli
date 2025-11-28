package org.example.application.errors.boards;

import org.example.application.errors.EntityExceptions;

public class InvalidBoardException extends EntityExceptions {
    public InvalidBoardException(String boardId, String message) {
        super(message, boardId, "INVALID_BOARD");
    }

    public InvalidBoardException(String boardId, String message, Throwable cause) {
        super(message, boardId, "INVALID_BOARD", cause);
    }
}
