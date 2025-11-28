package org.example.application.errors.boards;

import org.example.application.errors.EntityExceptions;

import java.util.logging.Logger;

public class BoardExceptionFactory {
    private final Logger logger;

    public BoardExceptionFactory(Logger logger) {
        this.logger = logger;
    }

    public BoardNotFoundException boardNotFound(String boardId){
        BoardNotFoundException exception = new BoardNotFoundException(boardId);
        logException(exception);
        return exception;
    }
    public BoardNotFoundException boardNotFound(String boardId,  Throwable cause){
        BoardNotFoundException exception = new BoardNotFoundException(boardId,cause);
        logException(exception, cause);
        return exception;
    }

    public BoardPersistenceException persistenceError(String boardId, String operation){
        BoardPersistenceException exception = new BoardPersistenceException(boardId, operation);
        logException(exception);
        return exception;
    }
    public BoardPersistenceException persistenceError(String boardId, String operation, Throwable cause){
        BoardPersistenceException exception = new BoardPersistenceException(boardId,operation,cause);
        logException(exception, cause);
        return exception;
    }

    public InvalidBoardException invalidBoard(String boardId, String message){
        InvalidBoardException exception = new InvalidBoardException(boardId, message);
        logException(exception);
        return exception;
    }
    public InvalidBoardException invalidBoard(String boardId, String message, Throwable cause){
        InvalidBoardException exception = new InvalidBoardException(boardId,message,cause);
        logException(exception, cause);
        return exception;
    }


    private void logException(EntityExceptions exception) {
        logger.severe(String.format("[%s] Board ID: %s - %s",
                exception.getErrorCode(),
                exception.getEntityId(),
                exception.getMessage()));
    }

    private void logException(EntityExceptions exception, Throwable cause) {
        logger.severe(String.format("[%s] Board ID: %s - %s - Cause: %s",
                exception.getErrorCode(),
                exception.getEntityId(),
                exception.getMessage(),
                cause.getMessage()));

        if (logger.isLoggable(java.util.logging.Level.FINE)) {
            cause.printStackTrace();
        }
    }
}
