package ru.project.monolith.task_manager.service;

import ru.project.monolith.task_manager.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> get();

    BoardDto get(Long id);

    void create(BoardDto entity);

    void update(Long id, BoardDto entity);

    void delete(Long id);
}
