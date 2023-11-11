package ru.project.monolith.task_manager.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.project.monolith.task_manager.entity.Task;
import ru.project.monolith.task_manager.entity.User;
import ru.project.monolith.task_manager.dto.TaskDto;
import ru.project.monolith.task_manager.repository.ColumnBoardRepository;
import ru.project.monolith.task_manager.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final ColumnBoardRepository columnBoardRepository;
    private final UserRepository userRepository;
    public Task toEntity(TaskDto dto) {
        return new Task
                (
                        dto.getId(),
                        dto.getName(),
                        dto.getDescription(),
                        columnBoardRepository.findById(dto.getColumnBoardId()).get(),
                        userRepository.findAllById(dto.getUserId())
                );
    }
    public TaskDto toDto(Task entity) {
        return new TaskDto
                (
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getColumnBoard().getId(),
                        entity.getUsers()
                                .stream()
                                .map(User::getId)
                                .toList()
                );
    }
    public List<Task> toEntity(List<TaskDto> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }
    public List<TaskDto> toDto(List<Task> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }
    public Task update(Task entity, TaskDto dto){
        Task task = toEntity(dto);
        if(task.getName() != null && !entity.getName().isEmpty()){
            entity.setName(task.getName());
        }
        if(task.getDescription() != null && !entity.getDescription().isEmpty()){
            entity.setDescription(task.getDescription());
        }
        if(task.getColumnBoard() != null) {
            entity.setColumnBoard(task.getColumnBoard());
        }
        if(task.getUsers() != null && task.getUsers().size() != 0) {
            entity.setUsers(task.getUsers());
        }
        return entity;
    }
}
