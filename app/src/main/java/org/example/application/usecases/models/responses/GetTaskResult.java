package org.example.application.usecases.models.responses;

import org.example.domain.model.Task;

import java.util.Collections;
import java.util.List;

public class GetTaskResult extends BaseGetResult<Task> {
    protected GetTaskResult(boolean success, String message, List<Task> items) {
        super(success, message, items);
    }

    // Factory methods
    public static GetTaskResult success(List<Task> tasks) {
        return new GetTaskResult(true, "Tasks loaded successfully", tasks);
    }

    public static GetTaskResult success(List<Task> tasks, String message) {
        return new GetTaskResult(true, message, tasks);
    }

    public static GetTaskResult empty(String message) {
        return new GetTaskResult(true, message, Collections.emptyList());
    }

    public static GetTaskResult error(String message) {
        return new GetTaskResult(false, message, Collections.emptyList());
    }
}
