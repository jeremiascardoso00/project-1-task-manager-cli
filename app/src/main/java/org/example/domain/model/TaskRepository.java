package org.example.domain.model;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
}
