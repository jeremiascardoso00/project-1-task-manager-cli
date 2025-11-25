package org.example.domain.model;

import org.example.application.queries.TaskQuery;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
    List<Task> findWithFilters(TaskQuery query);
}
