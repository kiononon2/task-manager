package ru.project.monolith.task_manager.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdviceException extends RuntimeException{
    private ErrorStatus status;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public AdviceException(ErrorStatus status) {
        this.status = status;
    }
}
