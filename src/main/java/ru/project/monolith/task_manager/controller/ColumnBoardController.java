package ru.project.monolith.task_manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.monolith.task_manager.dto.ColumnBoardDto;
import ru.project.monolith.task_manager.service.ColumnBoardService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/board/column")
@RequiredArgsConstructor
public class ColumnBoardController {
    @Autowired
    private ColumnBoardService service;


    @GetMapping
    public List<ColumnBoardDto> getAll(){
        return service.get();
    }

    @GetMapping("/{id}")
    public ColumnBoardDto getById(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping
    public void create(@RequestBody ColumnBoardDto entity){
        service.create(entity);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ColumnBoardDto entity){
        service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
