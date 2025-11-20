package org.example.infrastructure.persistence;

import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

import java.nio.file.Path;
import java.util.List;

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

        if (found){
            taskList.add(newTask);
        }

        saveAll(taskList);

        return newTask;
    }

    @Override
    public List<Task> findAll() {
        return loadAll();
    }
}
