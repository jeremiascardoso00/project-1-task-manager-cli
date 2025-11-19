package org.example.application.usecases.models.responses;

import org.example.domain.model.Board;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GetAvailableBoardsResult {

    private final boolean success;
    private final String message;
    private final List<Board> boards;
    private final boolean hasBoards;

    private GetAvailableBoardsResult(boolean success, String message, List<Board> boards) {
        this.success = success;
        this.message = message;
        this.boards = boards != null ? boards : Collections.emptyList();
        this.hasBoards = this.boards != null && !this.boards.isEmpty();
    }

    // Factory methods
    public static GetAvailableBoardsResult success(List<Board> boards) {
        return new GetAvailableBoardsResult(true, "Boards loaded successfully", boards);
    }

    public static GetAvailableBoardsResult success(List<Board> boards, String message) {
        return new GetAvailableBoardsResult(true, message, boards);
    }

    public static GetAvailableBoardsResult empty(String message) {
        return new GetAvailableBoardsResult(true, message, Collections.emptyList());
    }

    public static GetAvailableBoardsResult error(String message) {
        return new GetAvailableBoardsResult(false, message, Collections.emptyList());
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public List<Board> getBoards() { return boards; }
    public boolean hasBoards() { return hasBoards; }

    // Optional-based methods
    public Optional<List<Board>> getBoardsOptional() {
        return hasBoards ? Optional.of(boards) : Optional.empty();
    }

    public Optional<Board> getFirstBoard() {
        return hasBoards ? Optional.of(boards.get(0)) : Optional.empty();
    }

    // Utility methods
    public boolean isEmpty() {
        return !hasBoards;
    }

    public int getCount() {
        return boards.size();
    }
}
