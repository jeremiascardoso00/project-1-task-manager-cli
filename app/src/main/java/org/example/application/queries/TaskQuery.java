package org.example.application.queries;

import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

public final class TaskQuery {
    public static Predicate<Task> byStatus(Status status) {
        return task -> task.getStatus().equals(status);
    }

    public static Predicate<Task> byPriority(Priority priority) {
        return task -> task.getPriority() == priority;
    }

    public static Predicate<Task> search (String searchParam) {
        if (searchParam == null || searchParam.trim().isEmpty()) {
            return task -> false;
        }

        return task -> {
            var description = task.getDescription();
            return task.getTitle().toLowerCase().contains(searchParam) || (description != null && description.toLowerCase().contains(searchParam));
        };
    }

    public static Predicate<Task> betweenDates(LocalDate start, LocalDate end) {
        return task ->
                !task.getCreatedAt().toLocalDate().isBefore(start) &&
                        !task.getCreatedAt().toLocalDate().isAfter(end);
    }

    public static Comparator<Task> sortByPriority() {
        return Comparator.comparing(Task::getPriority);

    }
    public static Comparator<Task> sortByPriorityDesc() {
        return Comparator.comparing(Task::getPriority).reversed();
    }

    public static Comparator<Task> sortByCreationDate() {
        return Comparator.comparing(Task::getCreatedAt);
    }
    public static Comparator<Task> sortByCreationDateDesc() {
        return Comparator.comparing(Task::getCreatedAt).reversed();
    }
    public static Comparator<Task> sortByTitle() {
        return Comparator.comparing(Task::getTitle);
    }
}
