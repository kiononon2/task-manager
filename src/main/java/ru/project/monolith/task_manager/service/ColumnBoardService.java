package ru.project.monolith.task_manager.service;

import ru.project.monolith.task_manager.dto.ColumnBoardDto;

import java.util.List;

public interface ColumnBoardService {
    List<ColumnBoardDto> get();

    ColumnBoardDto get(Long id);

    void create(ColumnBoardDto entity);

    void update(Long id, ColumnBoardDto entity);

    void delete(Long id);
}
