package org.example.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Board {

    private final String id;
    private String name;
    private ArrayList<Task> taskArrayList;
    private final LocalDateTime createdAt;

    private Board(String id, String name, ArrayList<Task> taskArrayList){
        this.id = (id == null) ? UUID.randomUUID().toString(): id;
        this.name = name;
        this.taskArrayList = (taskArrayList == null) ? new ArrayList<>() : taskArrayList; ;
        this.createdAt = LocalDateTime.now();
    }

    public static Board newBoard(String name) {
        return new Board(null, name, null);
    }

    public static Board newBoard(String name, ArrayList<Task> taskArrayList) {
        return new Board(null, name, taskArrayList);
    }

    public static Board newBoard(String id, String name, ArrayList<Task> taskArrayList) {
        return new Board(id, name, taskArrayList);
    }

    public void addTask(Task task){
        this.taskArrayList.add(task);
    }

    public boolean removeTask(String taskId) {
         return taskArrayList.removeIf(task -> task.getId().equals(taskId));
    }

    public ArrayList<Task> updateTaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        return this.taskArrayList;
    }

    public ArrayList<Task> getAllTasks() {
        return this.taskArrayList;
    }

    public Task getTaskById(String id) {
        for (Task task : taskArrayList) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public Integer getTaskCount() {
        return this.taskArrayList.size();
    }

    public boolean containsTask(String taskId) {
        return getTaskById(taskId) != null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
                ", taskCount=" + taskArrayList.size() +
                ", createdAt=" + createdAt +
                '}';
    }

}
