package org.example.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private final String id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private final LocalDateTime createdAt;

    public Task(String id, String title, String description, Status status, Priority priority){
        this.id = (id == null) ? UUID.randomUUID().toString(): id;
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title.trim();
        this.description = (description == null) ? "" : description;

        if (status == null) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        this.status = status;

        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be empty");
        }
        this.priority = priority;

        createdAt = LocalDateTime.now();
    }

    public static Task newTask(String title, String description, Status status, Priority priority) {
        return new Task(null, title, description,status, priority);
    }
}