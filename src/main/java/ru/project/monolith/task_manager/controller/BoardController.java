package ru.project.monolith.task_manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.monolith.task_manager.service.BoardService;
import ru.project.monolith.task_manager.dto.BoardDto;

import java.util.List;
@RestController
@RequestMapping("/api/v1/user/board")
@RequiredArgsConstructor
public class BoardController {
//    @Autowired
    private final BoardService service;


    @GetMapping
    public List<BoardDto> getAll(){
        return service.get();
    }

    @GetMapping("/{id}")
    public BoardDto getById(@PathVariable Long id){
        return service.get(id);

    }

    @PostMapping
    public void create(@RequestBody BoardDto entity){
        service.create(entity);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody BoardDto entity){
        service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
