package org.example.application.usecases;

import org.example.application.queries.TaskQuery;
import org.example.application.usecases.models.responses.GetTaskResult;
import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class GetTasksUseCaseImpl implements GetTasksUseCase{

    private final TaskRepository taskRepository;

    public GetTasksUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public GetTaskResult execute() {
        try {
            List<Task> tasks = taskRepository.findAll();

            if (tasks == null || tasks.isEmpty()){
                return GetTaskResult.empty("No tasks available. Would you like to create one?");
            }

            Predicate<Task> byStatus = TaskQuery.byStatus(Status.DONE);
            Predicate<Task> byPriority = TaskQuery.byStatus(Status.DONE);
            byStatus.and(byPriority);

            Predicate<Task> filter = TaskQuery.byStatus(Status.DONE)
                    .and(TaskQuery.byPriority(Priority.LOW));


            Comparator<Task> sort = TaskQuery.sortByPriority().thenComparing(TaskQuery.sortByPriority());

            taskRepository.findWithFilters(filter,sort);

            return GetTaskResult.success(tasks, "Found " + tasks.size() + " tasks(s)\n");
        } catch (Exception e) {
            return GetTaskResult.error("Error loading tasks: " + e.getMessage());
        }
    }
}
