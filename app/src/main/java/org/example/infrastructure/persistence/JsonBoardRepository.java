package org.example.infrastructure.persistence;

import org.example.domain.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonBoardRepository implements BoardRepository {

    @Override
    public Board save(Board newBoard){
        return newBoard;
    }

    @Override
    public List<Board> findAll(){
        List<Board> boards = createMockBoards();
        return boards != null? boards: Collections.emptyList();
    }

    public ArrayList<Task> getAllTasks() {

        ArrayList<Task> allTaskList = new ArrayList<>();
        Task task1 = Task.newTask(null,"Design Database", "Create ER diagram", Status.TODO, Priority.HIGH);
        Task task2 = Task.newTask(null,"Implement API", "REST endpoints", Status.IN_PROGRESS, Priority.MEDIUM);

        allTaskList.add(task1);
        allTaskList.add(task2);

        return allTaskList;
    }

    private List<Board> createMockBoards() {
        List<Board> mockBoards = new ArrayList<>();

        mockBoards.add(Board.newBoard("Backlog"));
        mockBoards.add(Board.newBoard("In Progress"));
        mockBoards.add(Board.newBoard("Review"));
        mockBoards.add(Board.newBoard("Done"));
        mockBoards.add(Board.newBoard("Urgent Tasks"));

        Task task1 = Task.newTask(null,"Design Database", "Create ER diagram", Status.TODO, Priority.HIGH);
        Task task2 = Task.newTask(null,"Implement API", "REST endpoints", Status.IN_PROGRESS, Priority.MEDIUM);

        mockBoards.get(0).addTask(task1);
        mockBoards.get(1).addTask(task2);

        return mockBoards;
    }
}
