package ru.project.monolith.task_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.monolith.task_manager.dto.TaskDto;
import ru.project.monolith.task_manager.entity.Task;
import ru.project.monolith.task_manager.exception.AdviceException;
import ru.project.monolith.task_manager.exception.ErrorStatus;
import ru.project.monolith.task_manager.mapper.TaskMapper;
import ru.project.monolith.task_manager.repository.TaskRepository;
import ru.project.monolith.task_manager.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;
    @Override
    public List<TaskDto> get() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public TaskDto get(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new AdviceException(ErrorStatus.ERROR_NOT_FOUND_ID)));
    }

    @Override
    public void create(TaskDto entity) {
        try {
            repository.save(mapper.toEntity(entity));
        } catch(RuntimeException e) {
            throw new AdviceException(ErrorStatus.ERROR_NULL_BODY);
        }
    }

    @Override
    public void update(Long id, TaskDto entity) {
        Task task = mapper.toEntity(get(id));
        repository.save(mapper.update(task, entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
