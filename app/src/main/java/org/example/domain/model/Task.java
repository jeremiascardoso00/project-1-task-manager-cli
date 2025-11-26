package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;
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
        this.title = validateTitle(title).trim();
        this.description = validateDescription(description);
        this.status = validateStatus(status);
        this.priority = validatePriority(priority);
        this.createdAt = LocalDateTime.now();
    }

    @JsonCreator
    private Task(@JsonProperty("id") String id,
                 @JsonProperty("title") String title,
                 @JsonProperty("description") String description,
                 @JsonProperty("status") Status status,
                 @JsonProperty("priority") Priority priority,
                 @JsonProperty("createdAt") LocalDateTime createdAt){
        this.id = id;
        this.title = validateTitle(title).trim();
        this.description = validateDescription(description);
        this.status = validateStatus(status);
        this.priority = validatePriority(priority);
        this.createdAt = createdAt;
    }

    public static Task newTask(String id, String title, String description, Status status, Priority priority) {
        return new Task(id, title, description,status, priority);
    }

    //getters
    public String getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public Status getStatus() {
        return this.status;
    }
    public Priority getPriority() {
        return this.priority;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    //validation methods
    private String validateTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) throw new IllegalArgumentException("Title cannot be blank");
        return newTitle;
    }
    private String validateDescription(String newDescription) {
        return (newDescription == null) ? "": newDescription;
    }
    private Status validateStatus(Status newStatus) {
        if (newStatus == null) throw new IllegalArgumentException("Status cannot be empty");
        return newStatus;
    }
    private Priority validatePriority(Priority newPriority) {
        if (newPriority == null) throw new IllegalArgumentException("Priority cannot be empty");
        return newPriority;
    }

    //setters
    public void setTitle(String newTitle) {
        this.title = validateTitle(newTitle).trim();
    }
    public void setDescription(String newDescription) {
        this.description = validateDescription(newDescription);
    }
    public void setStatus(Status newStatus) {
        this.status = validateStatus(newStatus);
    }
    public void setPriority(Priority newPriority) {
        this.priority = validatePriority(newPriority);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}