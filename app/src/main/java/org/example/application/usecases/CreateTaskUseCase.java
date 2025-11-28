package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetTaskResult;
import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;

public interface CreateTaskUseCase {
    GetTaskResult execute(String title, String description, Status status, Priority priority);
}
