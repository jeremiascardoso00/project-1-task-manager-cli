package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetTaskResult;
import org.example.domain.model.Task;

import java.util.Comparator;
import java.util.function.Predicate;

public interface GetTasksUseCase {
    GetTaskResult execute(Predicate<Task> filter, Comparator<Task> sorter);
}
