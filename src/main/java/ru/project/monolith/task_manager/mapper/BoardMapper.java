package ru.project.monolith.task_manager.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.project.monolith.task_manager.entity.ColumnBoard;
import ru.project.monolith.task_manager.dto.BoardDto;
import ru.project.monolith.task_manager.entity.Board;
import ru.project.monolith.task_manager.repository.ColumnBoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoardMapper {
    private final ColumnBoardRepository columnBoardRepository;

    public Board toEntity(BoardDto dto) {
        return new Board
                (
                        dto.getId(),
                        dto.getTitle(),
                        columnBoardRepository.findAllById(dto.getColumnBoardId())
                );
    }

    public BoardDto toDto(Board entity) {
        return new BoardDto
                (
                        entity.getId(),
                        entity.getTitle(),
                        entity.getColumnBoards()
                                .stream()
                                .map(ColumnBoard::getId)
                                .toList()
                );
    }

    public List<Board> toEntity(List<BoardDto> dto) {
        return dto.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<BoardDto> toDto(List<Board> entity) {
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Board update(Board entity, BoardDto dto){
        Board board = toEntity(dto);
        if(board.getTitle() != null && !entity.getTitle().isEmpty()){
            entity.setTitle(board.getTitle());
        }
        if(board.getColumnBoards() != null && board.getColumnBoards().size() != 0){
            entity.setColumnBoards(board.getColumnBoards());
        }
        return entity;
    }

}
