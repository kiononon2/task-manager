package ru.project.monolith.task_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.monolith.task_manager.service.BoardService;
import ru.project.monolith.task_manager.dto.BoardDto;
import ru.project.monolith.task_manager.entity.Board;
import ru.project.monolith.task_manager.exception.AdviceException;
import ru.project.monolith.task_manager.exception.ErrorStatus;
import ru.project.monolith.task_manager.mapper.BoardMapper;
import ru.project.monolith.task_manager.repository.BoardRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository repository;
    private final BoardMapper mapper;
    @Override
    public List<BoardDto> get() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public BoardDto get(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new AdviceException(ErrorStatus.ERROR_NOT_FOUND_ID)));
    }

    @Override
    public void create(BoardDto entity) {
        try {
            repository.save(mapper.toEntity(entity));
        } catch(RuntimeException e) {
            throw new AdviceException(ErrorStatus.ERROR_NULL_BODY);
        }
    }

    @Override
    public void update(Long id, BoardDto entity) {
        Board board = mapper.toEntity(get(id));
        repository.save(mapper.update(board, entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
