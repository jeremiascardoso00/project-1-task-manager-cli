package org.example.infrastructure.persistence;

import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class JsonTaskRepository extends JsonRepository<Task> implements TaskRepository {

    public JsonTaskRepository(Path filePath) {
        super(filePath, Task.class);
    }

    @Override
    public Task save(Task newTask){

        Optional<List<Task>> result = loadAll();

        List<Task> taskList = Collections.emptyList();

        if (result.isPresent()){
            taskList = result.get();
        }


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
        Optional<List<Task>> result = loadAll();

        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<Task> findWithFilters(Predicate<Task> query, Comparator<Task> sort) {
        Optional<List<Task>> result = loadAll();

        return result
            .map(tasks -> tasks
                    .stream()
                    .filter(query)
                    .sorted(sort)
                    .toList())
            .orElse(Collections.emptyList());
    }
}
