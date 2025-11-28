package org.example.application.errors.tasks;

import org.example.application.errors.EntityExceptions;

public class TaskNotFoundException extends EntityExceptions {
    public TaskNotFoundException(String taskId) {
        super("Task not found with ID: " + taskId, taskId, "TASK_NOT_FOUND");
    }

    public TaskNotFoundException(String taskId, Throwable cause) {
        super("Task not found with ID: " + taskId, taskId, "TASK_NOT_FOUND", cause);
    }
}
