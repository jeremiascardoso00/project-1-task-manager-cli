package org.example.infrastructure.persistence;

import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

public class JsonTaskRepository implements TaskRepository {

    @Override
    public Task save(Task newTask){
        return newTask;
    }
}
