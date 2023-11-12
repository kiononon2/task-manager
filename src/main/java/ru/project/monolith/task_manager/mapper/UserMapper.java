package ru.project.monolith.task_manager.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.project.monolith.task_manager.entity.Task;
import ru.project.monolith.task_manager.entity.User;
import ru.project.monolith.task_manager.dto.UserDto;
import ru.project.monolith.task_manager.repository.BoardRepository;
import ru.project.monolith.task_manager.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;
    public User toEntity(UserDto dto) {
        return new User
                (
                        dto.getId(),
                        dto.getUsername(),
                        dto.getPassword(),
                        dto.getEmail(),
                        taskRepository.findAllById(dto.getTaskId()),
                        boardRepository.findById(dto.getBoardId()).get()
                );
    }
    public UserDto toDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getTask()
                        .stream()
                        .map(Task::getId)
                        .toList(),
                entity.getBoard().getId()
        );
    }
    public List<User> toEntity(List<UserDto> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }
    public List<UserDto> toDto(List<User> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }
    public User update(User entity, UserDto dto){
        User user = toEntity(dto);
        if(user.getUsername() != null && !entity.getUsername().isEmpty()){
            entity.setUsername(user.getUsername());
        }
        if(user.getPassword() != null && !entity.getPassword().isEmpty()){
            entity.setPassword(user.getPassword());
        }
        if(user.getEmail() != null && !entity.getEmail().isEmpty()) {
            entity.setEmail(user.getEmail());
        }
        if(user.getTask() != null && user.getTask().size() != 0) {
            entity.setTask(user.getTask());
        }
        if(user.getBoard() != null){
            entity.setBoard(user.getBoard());
        }
        return entity;
    }

}
