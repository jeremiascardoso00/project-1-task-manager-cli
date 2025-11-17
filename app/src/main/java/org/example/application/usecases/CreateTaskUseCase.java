package org.example.application.usecases;

import org.example.domain.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateTaskUseCase {
    private TaskRepository taskRepository;
    private BoardRepository boardRepository;

    public CreateTaskUseCase(TaskRepository taskRepository,
                             BoardRepository boardRepository) {
        this.taskRepository = taskRepository;
        this.boardRepository = boardRepository;
    }

    public Task Execute(String title, String description, Status status, Priority priority, Board selectedBoard) {

        Board board;

        Task task = Task.newTask(null,title,description,status,priority);

        if (selectedBoard == null) {
            board = Board.newBoard("", new ArrayList<Task>());
        } else {
            board = selectedBoard;
        }

        board.addTask(task);
        // create board repository

        taskRepository.Save(task);

        //debe ser un update
        boardRepository.Save(board);

        System.out.println("Task created at " + LocalDateTime.now() + ": " + title);

        return task;
    }
}
