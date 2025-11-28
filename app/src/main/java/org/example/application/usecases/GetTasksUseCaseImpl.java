package org.example.application.usecases;

import org.example.application.queries.TaskQuery;
import org.example.application.usecases.models.responses.GetTaskResult;
import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class GetTasksUseCaseImpl implements GetTasksUseCase{

    private final TaskRepository taskRepository;
    private final GetTaskResult resultFactory;


    public GetTasksUseCaseImpl(TaskRepository taskRepository, GetTaskResult resultFactory) {
        this.taskRepository = taskRepository;
        this.resultFactory = resultFactory;
    }

    @Override
    public GetTaskResult execute(Predicate<Task> filter, Comparator<Task> sorter) {
        try {
            List<Task> tasks;
            if (filter == null && sorter == null) {
                tasks = taskRepository.findAll();
            } else {
                if (filter == null) {
                    filter = task -> true;
                }
                if (sorter == null) {
                    sorter = TaskQuery.sortByCreationDateDesc();
                }
                tasks = taskRepository.findWithFilters(filter, sorter);
            }

            if (tasks == null || tasks.isEmpty()){
                return resultFactory.empty("No tasks available. Would you like to create one?");
            }

            return resultFactory.success(tasks, "Found " + tasks.size() + " tasks(s)\n");
        } catch (Exception e) {
            return resultFactory.error("Error loading tasks: " + e.getMessage());
        }
    }
}
