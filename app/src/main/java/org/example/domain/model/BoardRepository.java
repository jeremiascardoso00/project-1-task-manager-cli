package org.example.domain.model;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
    List<Board> findWithFilters(Predicate<Board> query, Comparator<Board> sort);
}
