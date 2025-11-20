package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetTaskResult;
import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

import java.util.List;

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

            return GetTaskResult.success(tasks, "Found " + tasks.size() + " tasks(s)");
        } catch (Exception e) {
            return GetTaskResult.error("Error loading tasks: " + e.getMessage());
        }
    }
}
