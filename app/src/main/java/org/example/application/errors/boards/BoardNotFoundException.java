package org.example.application.errors.boards;

import org.example.application.errors.EntityExceptions;

public class BoardNotFoundException extends EntityExceptions {
    public BoardNotFoundException(String boardId) {
        super("Board not found with ID: " + boardId, boardId, "BOARD_NOT_FOUND");
    }

    public BoardNotFoundException(String boardId, Throwable cause) {
        super("Board not found with ID: " + boardId, boardId, "BOARD_NOT_FOUND", cause);
    }}
