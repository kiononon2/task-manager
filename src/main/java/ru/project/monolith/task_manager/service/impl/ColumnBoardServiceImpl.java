package ru.project.monolith.task_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.monolith.task_manager.dto.ColumnBoardDto;
import ru.project.monolith.task_manager.entity.ColumnBoard;
import ru.project.monolith.task_manager.exception.AdviceException;
import ru.project.monolith.task_manager.exception.ErrorStatus;
import ru.project.monolith.task_manager.mapper.ColumnBoardMapper;
import ru.project.monolith.task_manager.repository.ColumnBoardRepository;
import ru.project.monolith.task_manager.service.ColumnBoardService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ColumnBoardServiceImpl implements ColumnBoardService {
    private final ColumnBoardRepository repository;
    private final ColumnBoardMapper mapper;
    @Override
    public List<ColumnBoardDto> get() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public ColumnBoardDto get(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new AdviceException(ErrorStatus.ERROR_NOT_FOUND_ID)));
    }

    @Override
    public void create(ColumnBoardDto entity) {
        try {
            repository.save(mapper.toEntity(entity));
        } catch(RuntimeException e) {
            throw new AdviceException(ErrorStatus.ERROR_NULL_BODY);
        }
    }

    @Override
    public void update(Long id, ColumnBoardDto entity) {
        ColumnBoard columnBoard = mapper.toEntity(get(id));
        repository.save(mapper.update(columnBoard, entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
