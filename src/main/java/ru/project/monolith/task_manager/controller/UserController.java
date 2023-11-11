package ru.project.monolith.task_manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.project.monolith.task_manager.dto.UserDto;
import ru.project.monolith.task_manager.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;


    @GetMapping
    public List<UserDto> getAll(){
        return service.get();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping
    public void create(@RequestBody UserDto entity){
        service.create(entity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserDto entity){
        service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
