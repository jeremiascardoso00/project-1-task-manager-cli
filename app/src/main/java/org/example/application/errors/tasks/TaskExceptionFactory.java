package org.example.application.errors.tasks;

import org.example.application.errors.EntityExceptions;

import java.util.logging.Logger;

public class TaskExceptionFactory {

    private final Logger logger;

    public TaskExceptionFactory(Logger logger) {
        this.logger = logger;
    }

    public TaskNotFoundException taskNotFound(String taskId){
        TaskNotFoundException exception = new TaskNotFoundException(taskId);
        logException(exception);
        return exception;
    }
    public TaskNotFoundException taskNotFound(String taskId,  Throwable cause){
        TaskNotFoundException exception = new TaskNotFoundException(taskId,cause);
        logException(exception, cause);
        return exception;
    }

    public TaskPersistenceException persistenceError(String taskId, String operation){
        TaskPersistenceException exception = new TaskPersistenceException(taskId, operation);
        logException(exception);
        return exception;
    }
    public TaskPersistenceException persistenceError(String taskId, String operation, Throwable cause){
        TaskPersistenceException exception = new TaskPersistenceException(taskId,operation,cause);
        logException(exception, cause);
        return exception;
    }

    public InvalidTaskException invalidTask(String taskId, String message){
        InvalidTaskException exception = new InvalidTaskException(taskId, message);
        logException(exception);
        return exception;
    }
    public InvalidTaskException invalidTask(String taskId, String message, Throwable cause){
        InvalidTaskException exception = new InvalidTaskException(taskId,message,cause);
        logException(exception, cause);
        return exception;
    }


    private void logException(EntityExceptions exception) {
        logger.severe(String.format("[%s] Task ID: %s - %s",
                exception.getErrorCode(),
                exception.getEntityId(),
                exception.getMessage()));
    }

    private void logException(EntityExceptions exception, Throwable cause) {
        logger.severe(String.format("[%s] Task ID: %s - %s - Cause: %s",
                exception.getErrorCode(),
                exception.getEntityId(),
                exception.getMessage(),
                cause.getMessage()));

        if (logger.isLoggable(java.util.logging.Level.FINE)) {
            cause.printStackTrace();
        }
    }
}
