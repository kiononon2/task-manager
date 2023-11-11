package ru.project.monolith.task_manager.service;

import ru.project.monolith.task_manager.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> get();

    UserDto get(Long id);

    void create(UserDto entity);

    void update(Long id, UserDto entity);

    void delete(Long id);
}
