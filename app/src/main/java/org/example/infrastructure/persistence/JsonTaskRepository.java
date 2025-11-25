package org.example.infrastructure.persistence;

import org.example.application.queries.TaskQuery;
import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonTaskRepository extends JsonRepository<Task> implements TaskRepository {

    public JsonTaskRepository(Path filePath) {
        super(filePath, Task.class);
    }

    @Override
    public Task save(Task newTask){

        List<Task> taskList = loadAll();

        var found = false;
        for (int i=0; i < taskList.size(); i++){
            if (taskList.get(i).getId().equals(newTask.getId())){
                taskList.set(i, newTask);
                found= true;
                break;
            }
        }

        if (!found){
            taskList.add(newTask);
        }

        saveAll(taskList);

        return newTask;
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = loadAll();
        return tasks != null? tasks: Collections.emptyList();
    }

    @Override
    public List<Task> findWithFilters(TaskQuery query){

        List<Task> tasks = loadAll();

        Stream<Task> taskStream = tasks.stream();

        Stream<Task> filteredStream = query.apply(taskStream);

        return filteredStream.collect(Collectors.toList());
    }
}
