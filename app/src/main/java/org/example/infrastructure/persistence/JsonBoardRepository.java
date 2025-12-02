package org.example.infrastructure.persistence;

import org.example.domain.model.*;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class JsonBoardRepository extends JsonRepository<Board> implements BoardRepository {

    public JsonBoardRepository(Path filePath) {
        super(filePath, Board.class);
    }

    @Override
    public Board save(Board newBoard){

        Optional<List<Board>> result = loadAll();

        List<Board> boardList = Collections.emptyList();

        if (result.isPresent()){
            boardList = result.get();
        }

        var found = boardList.
                stream().
                anyMatch(item -> item.getId().
                        equals(newBoard.getId()));

        if (!found){
            boardList.add(newBoard);
        }

        saveAll(boardList);

        return newBoard;
    }

    @Override
    public List<Board> findAll(){
        Optional<List<Board>> result = loadAll();

        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<Board> findWithFilters(Predicate<Board> query, Comparator<Board> sort) {

        Optional<List<Board>> result = loadAll();

        return result
            .map(boards -> boards
                        .stream()
                        .filter(query)
                        .sorted(sort)
                        .toList())
            .orElse(Collections.emptyList());
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
