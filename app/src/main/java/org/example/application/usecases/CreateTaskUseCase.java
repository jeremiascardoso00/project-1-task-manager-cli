package org.example.application.usecases;

import org.example.domain.model.Priority;
import org.example.domain.model.Status;
import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

import java.time.LocalDateTime;

public class CreateTaskUseCase {
    private TaskRepository taskRepository;

    public CreateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task Execute(String title, String description, Status status, Priority priority) {
        Task task = Task.newTask(title,description,status,priority);
        taskRepository.Save(task);

        System.out.println("Task created at " + LocalDateTime.now() + ": " + title);

        return task;
    }
}
