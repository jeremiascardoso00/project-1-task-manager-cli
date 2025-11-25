package org.example.application.queries;

import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
public interface TaskQuery {

    //abstract method
    Stream<Task> apply(Stream<Task> taskStream);

    static TaskQuery byStatus(Status status) {
        return taskStream -> taskStream.filter(task -> task.getStatus() == status);
    }

    static TaskQuery byPriority(Priority priority) {
        //taskStream: parameter of the apply method
        // taskStream.filter() is the return object
        // so this implementation is having Stream<Task> in parameter and returning Stream<Task>
        // this is a implementation of the abstract class apply in a static method return
        return taskStream -> taskStream.filter(task -> task.getPriority() == priority);
    }

    static TaskQuery search (String searchParam) {
        if (searchParam == null || searchParam.trim().isEmpty()) {
            return stream -> stream;
        }

        return taskStream -> taskStream.filter(task -> {
            var description = task.getDescription();
            return task.getTitle().toLowerCase().contains(searchParam) || (description != null && description.toLowerCase().contains(searchParam));
        });
    }

    static TaskQuery sortByPriority() {
        return taskStream -> taskStream.sorted(Comparator.comparing(Task::getPriority));
    }
    static TaskQuery sortByPriorityDesc() {
        return taskStream -> taskStream.sorted(Comparator.comparing(Task::getPriority).reversed());
    }

    static TaskQuery sortByCreationDate() {
        return taskStream -> taskStream.sorted(Comparator.comparing(Task::getCreatedAt));
    }
    static TaskQuery sortByCreationDateDesc() {
        return taskStream -> taskStream.sorted(Comparator.comparing(Task::getCreatedAt).reversed());
    }
    static TaskQuery sortByTitle() {
        return stream -> stream.sorted(Comparator.comparing(Task::getTitle));
    }
    static TaskQuery betweenDates(LocalDate start, LocalDate end) {
        return stream -> stream.filter(task ->
                !task.getCreatedAt().toLocalDate().isBefore(start) &&
                        !task.getCreatedAt().toLocalDate().isAfter(end)
        );
    }

    default TaskQuery and(TaskQuery other) {
        return stream -> this.apply(other.apply(stream));
    }

    default TaskQuery or(TaskQuery other) {
        return stream -> {
            List<Task> result1 = this.apply(stream).collect(Collectors.toList());
            List<Task> result2 = other.apply(stream).collect(Collectors.toList());

            return Stream.concat(result1.stream(), result2.stream())
                    .distinct();
        };
    }
}


