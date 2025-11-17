package org.example.infrastructure;

import org.example.domain.model.*;

import java.util.ArrayList;

public class JsonBoardRepository implements BoardRepository {

    @Override
    public void Save(Board newBoard){
        System.out.println(newBoard);
    }

    public ArrayList<Task> getAllTasks() {

        ArrayList<Task> allTaskList = null;
        Task task1 = Task.newTask(null,"Design Database", "Create ER diagram", Status.TODO, Priority.HIGH);
        Task task2 = Task.newTask(null,"Implement API", "REST endpoints", Status.IN_PROGRESS, Priority.MEDIUM);

        allTaskList.add(task1);
        allTaskList.add(task2);

        return allTaskList;
    }
}
