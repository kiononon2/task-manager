package ru.project.monolith.task_manager.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.project.monolith.task_manager.exception.AdviceException;
import ru.project.monolith.task_manager.exception.ErrorResponse;

@RestControllerAdvice
public class AdviceController {


    @ExceptionHandler
    public ErrorResponse handler(AdviceException exception) {
        return new ErrorResponse
                (
                        exception.getStatus().getMessage(),
                        exception.getStatus().getHttpStatus(),
                        exception.getLocalDateTime()
                );
    }
}
