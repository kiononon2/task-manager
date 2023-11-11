package ru.project.monolith.task_manager.service;

import ru.project.monolith.task_manager.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> get();

    TaskDto get(Long id);

    void create(TaskDto entity);

    void update(Long id, TaskDto entity);

    void delete(Long id);
}
