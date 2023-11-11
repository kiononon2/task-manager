package ru.project.monolith.task_manager.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColumnBoardDto {
    private Long id;
    private String title;
    private Long boardId;
    private List<Long> taskId = new ArrayList<>();
}
