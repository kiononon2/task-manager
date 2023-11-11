package ru.project.monolith.task_manager.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.project.monolith.task_manager.dto.ColumnBoardDto;
import ru.project.monolith.task_manager.entity.ColumnBoard;
import ru.project.monolith.task_manager.repository.BoardRepository;
import ru.project.monolith.task_manager.repository.TaskRepository;
import ru.project.monolith.task_manager.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ColumnBoardMapper {
    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;

    public ColumnBoard toEntity(ColumnBoardDto dto) {
        return new ColumnBoard
                (
                        dto.getId(),
                        dto.getTitle(),
                        boardRepository.findById(dto.getBoardId()).get(),
                        taskRepository.findAllById(dto.getTaskId())
                );
    }

    public ColumnBoardDto toDto(ColumnBoard entity) {
        return new ColumnBoardDto
                (
                        entity.getId(),
                        entity.getTitle(),
                        entity.getBoard().getId(),
                        entity.getTasks()
                                .stream()
                                .map(Task::getId)
                                .toList()
                );
    }

    public List<ColumnBoard> toEntity(List<ColumnBoardDto> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<ColumnBoardDto> toDto(List<ColumnBoard> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ColumnBoard update(ColumnBoard entity, ColumnBoardDto dto){
        ColumnBoard columnBoard = toEntity(dto);
        if(columnBoard.getTitle() != null && !entity.getTitle().isEmpty()){
            entity.setTitle(columnBoard.getTitle());
        }
        if(columnBoard.getBoard() != null){
            entity.setBoard(columnBoard.getBoard());
        }
        if(columnBoard.getTasks() != null && columnBoard.getTasks().size() != 0) {
            entity.setTasks(columnBoard.getTasks());
        }
        return entity;
    }
}
