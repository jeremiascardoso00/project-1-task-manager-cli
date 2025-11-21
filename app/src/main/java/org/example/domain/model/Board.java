package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Board {

    private final String id;
    private String name;
    private List<Task> allTasks;
    private final LocalDateTime createdAt;

    private Board(String id, String name, List<Task> taskList){
        this.id = (id == null) ? UUID.randomUUID().toString(): id;
        this.name = validateName(name);
        this.allTasks = (taskList == null) ? new ArrayList<>() : taskList;
        this.createdAt = LocalDateTime.now();
    }

    @JsonCreator
    private Board(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("allTasks") List<Task> taskList,
                  @JsonProperty("createdAt") LocalDateTime createdAt){
        this.id = id;
        this.name = validateName(name);
        this.allTasks =taskList;
        this.createdAt = createdAt;
    }

    public static Board newBoard(String name) {
        return new Board(null, name, null);
    }

    public static Board newBoard(String name, List<Task> taskList) {
        return new Board(null, name, taskList);
    }

    public static Board newBoard(String id, String name, List<Task> taskList) {
        return new Board(id, name, taskList);
    }

    private String validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Board name cannot be null");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Board name cannot be blank");
        }

        return name.trim();
    }

    public void addTask(Task task){
        this.allTasks.add(task);
    }

    public boolean removeTask(String taskId) {
         return allTasks.removeIf(task -> task.getId().equals(taskId));
    }

    public List<Task> updateTaskList(ArrayList<Task> taskArrayList) {
        this.allTasks = taskArrayList;
        return this.allTasks;
    }

    public Optional<Task> getTaskById(String id) {
        return this.allTasks.stream().
                filter(task -> task.getId().equals(id)).
                findFirst();
    }

    public Integer getTaskCount() {
        return this.allTasks.size();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getAllTasks() {
        return this.allTasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", taskCount=" + allTasks.size() +
                ", createdAt=" + createdAt +
                '}';
    }

}
