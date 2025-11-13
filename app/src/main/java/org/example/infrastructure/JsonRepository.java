package org.example.infrastructure;

import org.example.domain.model.Task;
import org.example.domain.model.TaskRepository;

public class JsonRepository implements TaskRepository {

    @Override
    public void Save(Task newTask){
        System.out.println(newTask);
    }
}
