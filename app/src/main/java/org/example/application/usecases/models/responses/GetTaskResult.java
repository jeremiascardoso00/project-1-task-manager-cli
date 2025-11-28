package org.example.application.usecases.models.responses;

import org.example.application.errors.tasks.TaskExceptionFactory;
import org.example.domain.model.Task;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class GetTaskResult extends BaseGetResult<Task> {
    private final TaskExceptionFactory exceptionFactory;
    private final Logger logger;

    public GetTaskResult(Logger logger) {
        super(false, null, Collections.emptyList());
        this.logger = logger;
        this.exceptionFactory = new TaskExceptionFactory(logger);
    }

    private GetTaskResult(boolean success, String message, List<Task> items,
                          Logger logger, TaskExceptionFactory exceptionFactory) {
        super(success, message, items);
        this.logger = logger;
        this.exceptionFactory = exceptionFactory;
    }


    public GetTaskResult success(List<Task> tasks) {
        return new GetTaskResult(true, "Tasks loaded successfully", tasks, logger, exceptionFactory);
    }

    public GetTaskResult success(List<Task> tasks, String message) {
        return new GetTaskResult(true, message, tasks, logger, exceptionFactory);
    }

    public GetTaskResult success(Task task) {
        return new GetTaskResult(true, "Task loaded successfully",
                List.of(task), logger, exceptionFactory);
    }

    public GetTaskResult success(Task task, String message) {
        return new GetTaskResult(true, message, List.of(task), logger, exceptionFactory);
    }

    public GetTaskResult empty(String message) {
        return new GetTaskResult(true, message, Collections.emptyList(), logger, exceptionFactory);
    }

    public GetTaskResult empty() {
        return new GetTaskResult(true, "No tasks found", Collections.emptyList(),
                logger, exceptionFactory);
    }

    public GetTaskResult error(String message) {
        logger.warning("Operation failed: " + message);
        return new GetTaskResult(false, message, Collections.emptyList(), logger, exceptionFactory);
    }

    public GetTaskResult error(Exception exception) {
        logger.severe("Operation failed with exception: " + exception.getMessage());
        return new GetTaskResult(false, exception.getMessage(), Collections.emptyList(),
                logger, exceptionFactory);
    }


    public GetTaskResult invalidTask(String message, String taskId) {
        return error(exceptionFactory.invalidTask(message, taskId));
    }

    public GetTaskResult invalidTask(String message, String taskId, Throwable cause) {
        return error(exceptionFactory.invalidTask(message, taskId, cause));
    }

    public GetTaskResult persistenceError(String taskId, String operation) {
        return error(exceptionFactory.persistenceError(taskId, operation));
    }

    public GetTaskResult persistenceError(String taskId, String operation, Throwable cause) {
        return error(exceptionFactory.persistenceError(taskId, operation, cause));
    }

    public GetTaskResult taskNotFound(String taskId) {
        return error(exceptionFactory.taskNotFound(taskId));
    }

    public GetTaskResult taskNotFound(String taskId, Throwable cause) {
        return error(exceptionFactory.taskNotFound(taskId, cause));
    }

    public GetTaskResult fromBoolean(boolean success, String successMessage, String errorMessage) {
        if (success) {
            return this.success(Collections.emptyList(), successMessage);
        } else {
            return this.error(errorMessage);
        }
    }

    public GetTaskResult fromOptional(java.util.Optional<Task> task, String taskId) {
        if (task.isPresent()) {
            return this.success(task.get(), "Task found successfully");
        } else {
            return this.taskNotFound(taskId);
        }
    }
}