package ru.project.monolith.task_manager.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.project.monolith.task_manager.dto.UserDto;
import ru.project.monolith.task_manager.entity.User;
import ru.project.monolith.task_manager.exception.AdviceException;
import ru.project.monolith.task_manager.exception.ErrorStatus;
import ru.project.monolith.task_manager.mapper.UserMapper;
import ru.project.monolith.task_manager.repository.UserRepository;
import ru.project.monolith.task_manager.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    @Override
    public List<UserDto> get() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public UserDto get(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new AdviceException(ErrorStatus.ERROR_NOT_FOUND_ID)));
    }

    @Override
    public void create(UserDto entity) {
        repository.save(mapper.toEntity(entity));
//        try {
//            repository.save(mapper.toEntity(entity));
//        } catch(RuntimeException e) {
//            throw new AdviceException(ErrorStatus.ERROR_NULL_BODY);
//        }
    }

    @Override
    public void update(Long id, UserDto entity) {
        User user = mapper.toEntity(get(id));
        repository.save(mapper.update(user, entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
