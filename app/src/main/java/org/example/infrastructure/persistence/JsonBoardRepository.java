package org.example.infrastructure.persistence;

import org.example.domain.model.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonBoardRepository extends JsonRepository<Board> implements BoardRepository {

    public JsonBoardRepository(Path filePath) {
        super(filePath, Board.class);
    }

    @Override
    public Board save(Board newBoard){

        List<Board> boardList = loadAll();
        System.out.println(boardList);

        var found = boardList.
                stream().
                anyMatch(item -> item.getId().
                        equals(newBoard.getId()));

        System.out.println(found);

        if (!found){
            boardList.add(newBoard);
        }

        saveAll(boardList);

        return newBoard;
    }

    @Override
    public List<Board> findAll(){
        List<Board> boards = loadAll();
        System.out.println(boards);
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

}
