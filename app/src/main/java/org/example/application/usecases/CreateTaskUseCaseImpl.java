package org.example.application.usecases;

import org.example.domain.model.*;

import java.time.LocalDateTime;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase{
    private TaskRepository taskRepository;

    public CreateTaskUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(String title, String description, Status status, Priority priority) {

        Task task = Task.newTask(null,title,description,status,priority);

        taskRepository.save(task);

        System.out.println("Task created at " + LocalDateTime.now() + ": " + title);

        return task;
    }
}
