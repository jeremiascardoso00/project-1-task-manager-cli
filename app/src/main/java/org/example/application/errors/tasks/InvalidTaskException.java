package org.example.application.errors.tasks;

import org.example.application.errors.EntityExceptions;

public class InvalidTaskException extends EntityExceptions {
    public InvalidTaskException(String taskId, String message) {
        super(message, taskId, "INVALID_TASK");
    }

    public InvalidTaskException(String taskId, String message, Throwable cause) {
        super(message, taskId, "INVALID_TASK", cause);
    }
}
