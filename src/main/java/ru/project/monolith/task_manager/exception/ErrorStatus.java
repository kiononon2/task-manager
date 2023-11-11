package ru.project.monolith.task_manager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorStatus {
    ERROR_NOT_FOUND_ID(HttpStatus.NOT_FOUND, "id board is not found"),
    ERROR_NULL_BODY(HttpStatus.NOT_ACCEPTABLE, "body is not acceptable");

    private HttpStatus httpStatus;
    private String message;
}
