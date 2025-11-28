package org.example.application.usecases;

import org.example.application.usecases.models.responses.GetTaskResult;
import org.example.domain.model.*;

import java.time.LocalDateTime;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase{
    private TaskRepository taskRepository;
    private final GetTaskResult getTaskResult;

    public CreateTaskUseCaseImpl(TaskRepository taskRepository, GetTaskResult getTaskResult) {
        this.taskRepository = taskRepository;
        this.getTaskResult = getTaskResult;
    }

    @Override
    public GetTaskResult execute(String title, String description, Status status, Priority priority) {
        try {
            Task task = Task.newTask(null, title, description, status, priority);
            taskRepository.save(task);
            System.out.println("Task created at " + LocalDateTime.now() + ": " + title);
            return getTaskResult.success(task);
        } catch (IllegalArgumentException e) {
            return getTaskResult.invalidTask(e.getMessage(), null, e);
        } catch (Exception e) {
            return getTaskResult.error(e);
        }
    }
}
