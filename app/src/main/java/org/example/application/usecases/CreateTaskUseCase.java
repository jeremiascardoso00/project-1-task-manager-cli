package org.example.application.usecases;

import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;

public interface CreateTaskUseCase {
    Task execute(String title, String description, Status status, Priority priority);
}
