package org.example.domain.model;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface TaskRepository {
    Task save(Task task);
    List<Task> findAll();
    List<Task> findWithFilters(Predicate<Task> filter,  Comparator<Task> sort);
}
