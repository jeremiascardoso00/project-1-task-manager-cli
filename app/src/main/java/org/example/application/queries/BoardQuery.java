package org.example.application.queries;

import org.example.domain.model.Board;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

public final class BoardQuery {

    private BoardQuery() {};

    public static Predicate<Board> search(String searchParam) {
        if (searchParam == null || searchParam.trim().isEmpty()) {
            return board -> false;
        }
        return board -> board.getName().toLowerCase().contains(searchParam.toLowerCase());
    }

    public static Predicate<Board> betweenDates(LocalDate start, LocalDate end) {
        return board ->
                !board.getCreatedAt().toLocalDate().isBefore(start) &&
                        !board.getCreatedAt().toLocalDate().isAfter(end);
    }

    public static Comparator<Board> sortByName() {
        return Comparator.comparing(Board::getName);
    }

    public static Comparator<Board> sortByCreationDate() {
        return Comparator.comparing(Board::getCreatedAt);
    }

    public static Comparator<Board> sortByCreationDateDesc() {
        return Comparator.comparing(Board::getCreatedAt).reversed();
    }

    public static Comparator<Board> sortByTaskCount() {
        return Comparator.comparing(Board::getTaskCount);
    }
    
    public static Comparator<Board> sortByTaskCountDesc() {
        return Comparator.comparing(Board::getTaskCount).reversed();
    }
}
