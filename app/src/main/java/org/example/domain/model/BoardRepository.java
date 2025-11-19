package org.example.domain.model;

import java.util.List;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
}
