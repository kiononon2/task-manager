package ru.project.monolith.task_manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.project.monolith.task_manager.dto.TaskDto;
import ru.project.monolith.task_manager.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/board/column/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;


    @GetMapping
    public List<TaskDto> getAll(){
        return service.get();
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping
    public void create(@RequestBody TaskDto entity){
        service.create(entity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody TaskDto entity){
        service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
