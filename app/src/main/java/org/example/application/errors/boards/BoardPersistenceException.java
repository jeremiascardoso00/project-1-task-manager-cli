package org.example.application.errors.boards;

import org.example.application.errors.EntityExceptions;

public class BoardPersistenceException extends EntityExceptions {
    public BoardPersistenceException(String boardId, String operation) {
        super("Persistence error during " + operation + " for board: " + boardId,
                boardId, "PERSISTENCE_ERROR");
    }

    public BoardPersistenceException(String boardId, String operation, Throwable cause) {
        super("Persistence error during " + operation + " for board: " + boardId,
                boardId, "PERSISTENCE_ERROR", cause);
    }}
