package org.example.infrastructure.handlers.task;

import org.example.application.usecases.GetTasksUseCase;
import org.example.application.usecases.models.responses.GetTaskResult;

public class GetTaskListCommandHandler {

    private final GetTasksUseCase getTasksUseCase;

    public GetTaskListCommandHandler(GetTasksUseCase getTasksUseCase) {
        this.getTasksUseCase = getTasksUseCase;
    }

    public void handleGet() {
        GetTaskResult getTaskResult = getTasksUseCase.execute();

        System.out.println(getTaskResult.getMessage());

        if (getTaskResult.hasItems()) {
            System.out.println(getTaskResult.getItems());
        } else {
            System.out.println(getTaskResult.error("Tasks not found"));
        }
    }
}
