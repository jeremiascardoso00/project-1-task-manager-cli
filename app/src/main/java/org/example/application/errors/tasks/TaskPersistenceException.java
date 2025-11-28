package org.example.application.errors.tasks;

import org.example.application.errors.EntityExceptions;

public class TaskPersistenceException extends EntityExceptions {
    public TaskPersistenceException(String taskId, String operation) {
        super("Persistence error during " + operation + " for task: " + taskId,
                taskId, "PERSISTENCE_ERROR");
    }

    public TaskPersistenceException(String taskId, String operation, Throwable cause) {
        super("Persistence error during " + operation + " for task: " + taskId,
                taskId, "PERSISTENCE_ERROR", cause);
    }
}
